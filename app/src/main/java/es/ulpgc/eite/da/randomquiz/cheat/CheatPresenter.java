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
    private CheatViewModel viewModel;
    private AppMediator mediator;

    public CheatPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        // init the screen state
        state = new CheatState();
        //state.answerText= model.getEmptyAnswerText();
        //mediator.setCheatState(state);

        // get the saved state from previous screen
        QuestionToCheatState savedState = mediator.getQuestionToCheatState();
        if (savedState != null) {

            // update the current state
            state.answer = savedState.answer;
        }

        // init the view model
        viewModel = new CheatViewModel();
        viewModel.answerText = model.getEmptyAnswerText();
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        // restore the screen state
        state = mediator.getCheatState();

        // restore the view model
        viewModel = new CheatViewModel();
        if(state.cheated) {
            updateAnswerData();

        }else{

            viewModel.answerText = model.getEmptyAnswerText();
        }
    }

    private void updateAnswerData() {

        // update the view model
        if (state.answer) {
            viewModel.answerText = model.getTrueAnswerText();

        } else {
            viewModel.answerText = model.getFalseAnswerText();
        }

        viewModel.yesButton = false;
        viewModel.noButton = false;

        // refresh the display with updated data
        view.get().displayCheatData(viewModel);
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        //view.get().displayCheatData(state);

        // refresh the display with updated data
        view.get().displayCheatData(viewModel);
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
        Log.e(TAG, "yesButtonClicked");

        // save the state to previous screen
        saveStateToPreviousScreen(true);

        // update the current state
        state.cheated = true;

        updateAnswerData();

    }

    /*
    @Override
    public void yesButtonClicked() {
        Log.e(TAG, "yesButtonClicked");

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
    */

    @Override
    public void noButtonClicked() {
        Log.e(TAG, "noButtonClicked");

        //CheatToQuestionState newState = new CheatToQuestionState(false);
        //mediator.setCheatToQuestionState(newState);

        saveStateToPreviousScreen(false);
        view.get().finishView();
    }

    private void saveStateToPreviousScreen(boolean cheated) {
        Log.e(TAG, "saveStateToPreviousScreen");

        CheatToQuestionState newState = new CheatToQuestionState(cheated);
        mediator.setCheatToQuestionState(newState);
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
