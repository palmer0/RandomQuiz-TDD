package es.ulpgc.eite.da.randomquiz.cheat;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.randomquiz.app.AppMediator;
import es.ulpgc.eite.da.randomquiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.randomquiz.app.QuestionToCheatState;


public class CheatPresenter implements CheatContract.Presenter {

  //public static String TAG = CheatPresenter.class.getSimpleName();
  public static String TAG = "RandomQuiz.CheatPresenter";

  private WeakReference<CheatContract.View> view;
  private CheatContract.Model model;
  private CheatState state;
  private AppMediator mediator;

  public CheatPresenter(AppMediator mediator) {
    this.mediator = mediator;
    //state = mediator.getCheatState();
  }


  @Override
  public void onCreateCalled() {
    Log.e(TAG, "onCreateCalled");

    state = new CheatState();
    mediator.setCheatState(state);

    //view.get().displayCheatData(state);

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

  /*
  @Override
  public void onDestroyCalled() {
    Log.e(TAG, "onDestroyCalled()");

    // Reset current state in Mediator
    mediator.resetCheatState();
  }
  */

  /*
  @Override
  public void onCreateCalled() {
    Log.e(TAG, "onCreateCalled()");

    mediator.resetCheatState();
  }

  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled()");

    view.get().displayCheatData(state);
  }
  */

  @Override
  public void yesButtonClicked() {

    // set passed state
    QuestionToCheatState savedState = mediator.getQuestionToCheatState();
    //QuestionToCheatState savedState = getDataFromQuestionScreen();
    if(savedState != null) {

      CheatToQuestionState newState = new CheatToQuestionState(true);
      //passDataToQuestionScreen(newState);
      mediator.setCheatToQuestionState(newState);

      /*
      if(savedState.answer) {
        state.answerText = view.get().getTrueLabel();
      } else {
        state.answerText = view.get().getFalseLabel();
      }
      */

      if(savedState.answer) {
        state.answerText = model.getTrueLabel();
      } else {
        state.answerText = model.getFalseLabel();
      }

      state.yesButton = false;
      state.noButton = false;

      view.get().displayCheatData(state);
    }
  }

  @Override
  public void noButtonClicked() {
    CheatToQuestionState newState = new CheatToQuestionState(false);
    //passDataToQuestionScreen(newState);
    mediator.setCheatToQuestionState(newState);
    view.get().finishView();
  }


//  private void passDataToQuestionScreen(CheatToQuestionState state) {
//    mediator.setCheatToQuestionState(state);
//  }

//  private QuestionToCheatState getDataFromQuestionScreen() {
//    return mediator.getQuestionToCheatState();
//  }

  @Override
  public void injectView(WeakReference<CheatContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(CheatContract.Model model) {
    this.model = model;
  }
}
