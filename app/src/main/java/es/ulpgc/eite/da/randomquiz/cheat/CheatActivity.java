package es.ulpgc.eite.da.randomquiz.cheat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.randomquiz.R;


public class CheatActivity
    extends AppCompatActivity implements CheatContract.View {

  //public static String TAG = CheatActivity.class.getSimpleName();
  public static String TAG = "RandomQuiz.CheatActivity";

  CheatContract.Presenter presenter;

  TextView confirmationText, answerText;
  Button yesButton, noButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);
    setTitle(R.string.cheat_screen_title);

    yesButton = findViewById(R.id.yesButton);
    noButton = findViewById(R.id.noButton);
    confirmationText = findViewById(R.id.confirmationText);
    answerText = findViewById(R.id.answerText);

    yesButton.setOnClickListener(v -> presenter.yesButtonClicked());
    noButton.setOnClickListener(v -> presenter.noButtonClicked());

    yesButton.setText(getYesLabel());
    noButton.setText(getNoLabel());
    confirmationText.setText(getConfirmationLabel());

    // do the setup
    CheatScreen.configure(this);

    /*
    // do some work
    presenter.onCreateCalled();
    */

    if(savedInstanceState == null){
      presenter.onCreateCalled();

    } else {
      presenter.onRecreateCalled();
    }
  }


  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.onResumeCalled();
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();

    //presenter.onDestroyCalled();
  }


  @Override
  public void displayCheatData(CheatViewModel viewModel) {
    Log.e(TAG, "displayCheatData");

    // deal with the data
    answerText.setText(viewModel.answerText);

    yesButton.setEnabled(viewModel.yesButton);
    noButton.setEnabled(viewModel.noButton);
  }

  @Override
  public void finishView(){
    finish();
  }

  private String getYesLabel() {
    return getResources().getString(R.string.yes_label);
  }

  private String getNoLabel() {
    return getResources().getString(R.string.no_label);
  }

  private String getConfirmationLabel() {
    return getResources().getString(R.string.confirmation_text);
  }

  /*
  @Override
  public String getFalseLabel() {
    return getResources().getString(R.string.false_label);
  }


  @Override
  public String getTrueLabel() {
    return getResources().getString(R.string.true_label);
  }
  */

  @Override
  public void injectPresenter(CheatContract.Presenter presenter) {
    this.presenter = presenter;
  }

}
