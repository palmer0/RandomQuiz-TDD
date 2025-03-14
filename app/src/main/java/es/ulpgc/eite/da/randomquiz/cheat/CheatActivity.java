package es.ulpgc.eite.da.randomquiz.cheat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.randomquiz.R;


public class CheatActivity
    extends AppCompatActivity implements CheatContract.View {

    public static String TAG = "RandomQuiz.CheatActivity";

    CheatContract.Presenter presenter;

    TextView confirmationText, answerText;
    Button yesButton, noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setTitle(R.string.cheat_screen_title);

        linkLayoutViews();
        setupButtonLabels();
        setupButtonListeners();

        // do the setup
        CheatScreen.configure(this);

        // do some work
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }


    private void linkLayoutViews() {

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        confirmationText = findViewById(R.id.confirmationText);
        answerText = findViewById(R.id.answerText);
    }

    private void setupButtonListeners() {
        yesButton.setOnClickListener(v -> presenter.yesButtonClicked());
        noButton.setOnClickListener(v -> presenter.noButtonClicked());
    }

    private void setupButtonLabels() {

        yesButton.setText(getYesButtonLabel());
        noButton.setText(getNoButtonLabel());
        confirmationText.setText(getConfirmationButtonLabel());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.onResumeCalled();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPauseCalled();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroyCalled();
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
    public void finishView() {
        finish();
    }

    private String getYesButtonLabel() {
        return getResources().getString(R.string.yes_label);
    }

    private String getNoButtonLabel() {
        return getResources().getString(R.string.no_label);
    }

    private String getConfirmationButtonLabel() {
        return getResources().getString(R.string.confirmation_text);
    }


    @Override
    public void injectPresenter(CheatContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
