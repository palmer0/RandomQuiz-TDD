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
    //private QuestionViewModel viewModel;
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
        //mediator.setQuestionState(state);

        // update the state from model
        state.quizQuestions = model.getQuizQuestions();
        state.quizAnswers = model.getQuizAnswers();

        /*
        // init the view model
        viewModel = new QuestionViewModel();
        viewModel.resultText = model.getEmptyResultText();
        */
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        // update the state
        state = mediator.getQuestionState();

        // update the model from state
        model.setQuizQuestions(state.quizQuestions);
        model.setQuizAnswers(state.quizAnswers);

        /*
        // restore the view model
        viewModel = new QuestionViewModel();
        if(state.answered) {
            updateQuestionData();

        }else{

            viewModel.resultText = model.getEmptyResultText();
        }
        */
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

        // call the model
        model.setCurrentIndex(state.quizIndex);

        state.questionText = model.getCurrentQuestion();
        //state.questionText = model.getCurrentQuestion(state.quizIndex);

        view.get().displayQuestionData(state);

        /*
        // update the view model
        viewModel.questionText = model.getCurrentQuestion();

        // refresh display with updated data
        view.get().displayQuestionData(viewModel);
        */

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
        //boolean currentAnswer = model.getCurrentAnswer(state.quizIndex);

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

        /*
        if(model.isLastQuestion(state.quizIndex)) {
          state.nextButton = false;
        } else {
          state.nextButton = true;
        }
        */

        view.get().displayQuestionData(state);
    }

    /*
    private void updateQuestionData() {

        boolean currentAnswer = model.getCurrentAnswer();
        //boolean currentAnswer = model.getCurrentAnswer(state.quizIndex);

        if (currentAnswer == state.userAnswer) {
            viewModel.resultText = model.getCorrectResultText();
        } else {
            viewModel.resultText = model.getIncorrectResultText();
        }

        viewModel.falseButton = false;
        viewModel.trueButton = false;
        viewModel.cheatButton = false;

        if (model.isLastQuestion()) {
            viewModel.nextButton = false;
        } else {
            viewModel.nextButton = true;
        }

        // refresh display with updated data
        view.get().displayQuestionData(viewModel);
    }
    */


    @Override
    public void trueButtonClicked() {
        Log.e(TAG, "trueButtonClicked");

        updateQuestionData(true);
        //selectedButtonClicked(true);
    }

    @Override
    public void falseButtonClicked() {
        Log.e(TAG, "falseButtonClicked");

        updateQuestionData(false);
        //selectedButtonClicked(false);
    }

    /*
    private void selectedButtonClicked(boolean button) {

        // update the current state
        state.answered = true;
        state.userAnswer = button;

        updateQuestionData();
    }
    */

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

        /*
        viewModel.questionText = model.getCurrentQuestion();
        viewModel.resultText = model.getEmptyResultText();

        viewModel.falseButton = true;
        viewModel.trueButton = true;
        viewModel.cheatButton = true;
        viewModel.nextButton = false;

        view.get().displayQuestionData(viewModel);
        */


        state.questionText = model.getCurrentQuestion();
        //state.questionText = model.getCurrentQuestion(state.quizIndex);
        //state.resultText = "";
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
