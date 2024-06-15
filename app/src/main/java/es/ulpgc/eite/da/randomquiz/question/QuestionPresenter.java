package es.ulpgc.eite.da.randomquiz.question;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.randomquiz.app.AppMediator;
import es.ulpgc.eite.da.randomquiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.randomquiz.app.QuestionToCheatState;

public class QuestionPresenter implements QuestionContract.Presenter {

    public static String TAG = "RandomQuiz.QuestionPresenter";

    private WeakReference<QuestionContract.View> view;
    private QuestionState state;
    private QuestionContract.Model model;
    private AppMediator mediator;

    public QuestionPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        // init the state
        state = new QuestionState();
        state.resultText=model.getEmptyResultText();

        // update the state from model
        state.quizQuestions = model.getQuizQuestions();
        state.quizAnswers = model.getQuizAnswers();
        state.questionText = model.getCurrentQuestion();

    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        // update the state
        state = mediator.getQuestionState();

        // update the model from state
        model.setQuizQuestions(state.quizQuestions);
        model.setQuizAnswers(state.quizAnswers);
        model.setCurrentIndex(state.quizIndex);

    }


    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        // set passed state
        CheatToQuestionState savedState = mediator.getCheatToQuestionState();
        if (savedState != null) {

            if (savedState.cheated) {
                nextButtonClicked();
                return;
            }
        }

        //refresh the view with updated data
        view.get().displayQuestionData(state);

    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        mediator.setQuestionState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled");

    }


    private void updateQuestionData(boolean userAnswer) {

        boolean currentAnswer = model.getCurrentAnswer();

        if (currentAnswer == userAnswer) {
            state.resultText = model.getCorrectResultText();
        } else {
            state.resultText = model.getIncorrectResultText();
        }

        state.falseButton = false;
        state.trueButton = false;
        state.cheatButton = false;

        if (model.isLastQuestion()) {
            state.nextButton = false;
        } else {
            state.nextButton = true;
        }

        view.get().displayQuestionData(state);
    }

    @Override
    public void trueButtonClicked() {
        Log.e(TAG, "trueButtonClicked");

        updateQuestionData(true);
    }

    @Override
    public void falseButtonClicked() {
        Log.e(TAG, "falseButtonClicked");

        updateQuestionData(false);
    }


    @Override
    public void cheatButtonClicked() {
        Log.e(TAG, "cheatButtonClicked");

        boolean answer = model.getCurrentAnswer();
        //boolean answer = model.getCurrentAnswer(state.quizIndex);
        QuestionToCheatState newState = new QuestionToCheatState(answer);
        mediator.setQuestionToCheatState(newState);
        view.get().navigateToCheatScreen();
    }

    @Override
    public void nextButtonClicked() {
        Log.e(TAG, "nextButtonClicked");

        // update the current state
        state.quizIndex++;
        model.incrQuizIndex();

        state.questionText = model.getCurrentQuestion();
        state.resultText = model.getEmptyResultText();

        state.falseButton = true;
        state.trueButton = true;
        state.cheatButton = true;
        state.nextButton = false;

        view.get().displayQuestionData(state);

    }

    @Override
    public void injectView(WeakReference<QuestionContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(QuestionContract.Model model) {
        this.model = model;
    }

}
