package es.ulpgc.eite.da.randomquiz.question;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.randomquiz.R;
import es.ulpgc.eite.da.randomquiz.cheat.CheatActivity;


public class QuestionActivity
    extends AppCompatActivity implements QuestionContract.View {

  public static String TAG = "RandomQuiz.QuestionActivity";

  QuestionContract.Presenter presenter;

  TextView questionText, resultText;
  Button trueButton, falseButton, cheatButton, nextButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);
    setTitle(R.string.question_screen_title);

    //Log.e(TAG, "onCreate()");

    trueButton = findViewById(R.id.trueButton);
    falseButton = findViewById(R.id.falseButton);
    cheatButton = findViewById(R.id.cheatButton);
    nextButton = findViewById(R.id.nextButton);
    questionText = findViewById(R.id.questionText);
    resultText = findViewById(R.id.resultText);

    trueButton.setOnClickListener(v -> presenter.trueButtonClicked());
    falseButton.setOnClickListener(v -> presenter.falseButtonClicked());
    cheatButton.setOnClickListener(v -> presenter.cheatButtonClicked());
    nextButton.setOnClickListener(v -> presenter.nextButtonClicked());

    trueButton.setText(getTrueLabel());
    falseButton.setText(getFalseLabel());
    cheatButton.setText(getCheatLabel());
    nextButton.setText(getNextLabel());

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

    // do the setup
    QuestionScreen.configure(this);

    if(savedInstanceState == null) {
      presenter.onCreateCalled();

    } else {
      presenter.onRecreateCalled();
    }
  }


  @Override
  protected void onResume() {
    super.onResume();
    //Log.e(TAG, "onResume()");

    // do some work
    presenter.onResumeCalled();
  }


  @Override
  protected void onPause() {
    super.onPause();
    Log.e(TAG, "onPause()");

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.e(TAG, "onDestroy()");

  }

  @Override
  public void navigateToCheatScreen() {
    Intent intent = new Intent(this, CheatActivity.class);
    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }


  @Override
  public void displayQuestionData(QuestionViewModel viewModel) {
    //Log.e(TAG, "displayQuestionData()");

    // deal with the data
    questionText.setText(viewModel.questionText);
    resultText.setText(viewModel.resultText);

    trueButton.setEnabled(viewModel.trueButton);
    falseButton.setEnabled(viewModel.falseButton);
    cheatButton.setEnabled(viewModel.cheatButton);
    nextButton.setEnabled(viewModel.nextButton);

  }


  private String getCheatLabel() {
    return getResources().getString(R.string.cheat_label);
  }

  private String getNextLabel() {
    return getResources().getString(R.string.next_label);
  }

  private String getFalseLabel() {
    return getResources().getString(R.string.false_label);
  }

  private String getTrueLabel() {
    return getResources().getString(R.string.true_label);
  }

  /*
  @Override
  public String getIncorrectLabel() {
    return getResources().getString(R.string.incorrect_label);
  }

  @Override
  public String getCorrectLabel() {
    return getResources().getString(R.string.correct_label);
  }
  */

  @Override
  public void injectPresenter(QuestionContract.Presenter presenter) {
    this.presenter = presenter;
  }


}
