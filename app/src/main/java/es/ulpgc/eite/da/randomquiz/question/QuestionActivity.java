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

        //Log.e(TAG, "onCreate");

        linkLayoutViews();
        setupButtonLabels();
        setupButtonListeners();

        // do the setup
        QuestionScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }
    
    private void linkLayoutViews() {

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);
        questionText = findViewById(R.id.questionText);
        resultText = findViewById(R.id.resultText);
    }

    private void setupButtonListeners() {
        trueButton.setOnClickListener(v -> presenter.trueButtonClicked());
        falseButton.setOnClickListener(v -> presenter.falseButtonClicked());
        cheatButton.setOnClickListener(v -> presenter.cheatButtonClicked());
        nextButton.setOnClickListener(v -> presenter.nextButtonClicked());
    }
    
    private void setupButtonLabels() {

        trueButton.setText(getTrueButtonLabel());
        falseButton.setText(getFalseButtonLabel());
        cheatButton.setText(getCheatButtonLabel());
        nextButton.setText(getNextButtonLabel()); 
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.e(TAG, "onResume");

        // do some work
        presenter.onResumeCalled();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");

        presenter.onPauseCalled();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");

        presenter.onDestroyCalled();
    }

    @Override
    public void navigateToCheatScreen() {
        Intent intent = new Intent(this, CheatActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void displayQuestionData(QuestionViewModel viewModel) {
        //Log.e(TAG, "displayQuestionData");

        // deal with the data
        questionText.setText(viewModel.questionText);
        resultText.setText(viewModel.resultText);

        trueButton.setEnabled(viewModel.trueButton);
        falseButton.setEnabled(viewModel.falseButton);
        cheatButton.setEnabled(viewModel.cheatButton);
        nextButton.setEnabled(viewModel.nextButton);

    }


    private String getCheatButtonLabel() {
        return getResources().getString(R.string.cheat_label);
    }

    private String getNextButtonLabel() {
        return getResources().getString(R.string.next_label);
    }

    private String getFalseButtonLabel() {
        return getResources().getString(R.string.false_label);
    }

    private String getTrueButtonLabel() {
        return getResources().getString(R.string.true_label);
    }

    @Override
    public void injectPresenter(QuestionContract.Presenter presenter) {
        this.presenter = presenter;
    }


}
