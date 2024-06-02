package es.ulpgc.eite.da.randomquiz.cheat;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.randomquiz.app.AppMediator;
import es.ulpgc.eite.da.randomquiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.randomquiz.app.QuestionToCheatState;


public class CheatPresenter implements CheatContract.Presenter {

    public static String TAG = "RandomQuiz.CheatPresenter";

    private WeakReference<CheatContract.View> view;
    private CheatContract.Model model;
    private CheatState state;
    private AppMediator mediator;

    public CheatPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        state = new CheatState();
        state.answerText= model.getEmptyAnswerText();
        //mediator.setCheatState(state);

    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        state = mediator.getCheatState();
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        view.get().displayCheatData(state);
    }


    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        mediator.setCheatState(state);
    }


    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled()");

        // Reset current state
        //mediator.resetCheatState();
    }


    @Override
    public void yesButtonClicked() {

        // set passed state
        QuestionToCheatState savedState = mediator.getQuestionToCheatState();
        if (savedState != null) {

            CheatToQuestionState newState = new CheatToQuestionState(true);
            mediator.setCheatToQuestionState(newState);

            if (savedState.answer) {
                state.answerText = model.getTrueAnswerText();
            } else {
                state.answerText = model.getFalseAnswerText();
            }

            state.yesButton = false;
            state.noButton = false;

            view.get().displayCheatData(state);
        }
    }

    @Override
    public void noButtonClicked() {
        CheatToQuestionState newState = new CheatToQuestionState(false);
        mediator.setCheatToQuestionState(newState);
        view.get().finishView();
    }

    @Override
    public void injectView(WeakReference<CheatContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CheatContract.Model model) {
        this.model = model;
    }
}
