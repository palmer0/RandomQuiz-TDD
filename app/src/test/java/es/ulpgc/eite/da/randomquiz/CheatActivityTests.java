package es.ulpgc.eite.da.randomquiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.da.randomquiz.app.AppMediator;
import es.ulpgc.eite.da.randomquiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.randomquiz.app.QuestionToCheatState;
import es.ulpgc.eite.da.randomquiz.cheat.CheatActivity;


@RunWith(RobolectricTestRunner.class)
//@Config(manifest=Config.NONE)
@Config(sdk = 28)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheatActivityTests {



    private boolean[] quizAnswers = {
        true, // 1
        false, // 2
        false, // 3
        true, // 4
        true, // 5
        true, // 6
        false, // 7
        true, // 8
        false, // 9
        false, // 10
        true, // 11
        true, // 12
        true, // 13
        false, // 14
        true, // 15
        true, // 16
        false, // 17
        false, // 18
        false, // 19
        true // 20
    };



    @Test
    public void test01ActivityInitialization() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);


        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerText);
            TextView warningTextView = activity.findViewById(R.id.confirmationText);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text), // expected
                warningTextView.getText().toString() // actual
            );

            // Verify the empty answer is shown
            assertEquals(
                activity.getString(R.string.empty_text), // expected
                answerTextView.getText().toString() // actual
            );

            // Verify the buttons are enabled
            assertTrue(yesButton.isEnabled());
            assertTrue(noButton.isEnabled());
        });
    }


    @Test
    public void test02ShowAnswerTrue() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerText);
            TextView warningTextView = activity.findViewById(R.id.confirmationText);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Press Yes button
            yesButton.performClick();

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text),
                warningTextView.getText().toString()
            );

            // Verify the True answer is shown
            assertNotEquals(
                activity.getString(R.string.empty_text),
                answerTextView.getText().toString());
            /*List<String> expectedList = Arrays.asList(
                activity.getString(R.string.false_text),
                activity.getString(R.string.true_text)
            );
            assertTrue(expectedList.contains(answerTextView.getText().toString()));*/


            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test03ShowAnswerFalse() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[1]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerText);
            TextView warningTextView = activity.findViewById(R.id.confirmationText);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Press Yes button
            yesButton.performClick();

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text),
                warningTextView.getText().toString()
            );

            // Verify the answer is shown
            assertNotEquals(
                activity.getString(R.string.empty_text),
                answerTextView.getText().toString());
            /*List<String> expectedList = Arrays.asList(
                activity.getString(R.string.false_text),
                activity.getString(R.string.true_text)
            );
            assertTrue(expectedList.contains(answerTextView.getText().toString()));*/

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test04RotateScreen() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerText);
            TextView warningTextView = activity.findViewById(R.id.confirmationText);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Press Yes button
            yesButton.performClick();

            // Rotate screen
            activity.recreate();

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text),
                warningTextView.getText().toString()
            );

            // Verify the answer is shown
            assertNotEquals(
                activity.getString(R.string.empty_text),
                answerTextView.getText().toString());
            /*List<String> expectedList = Arrays.asList(
                activity.getString(R.string.false_text),
                activity.getString(R.string.true_text)
            );
            assertTrue(expectedList.contains(answerTextView.getText().toString()));*/

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test05ReturnResultNo() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);


        scenario.onActivity(activity -> {
            Button noButton = activity.findViewById(R.id.noButton);

            // Press No button
            noButton.performClick();

            CheatToQuestionState savedState =
                AppMediator.getInstance().getCheatToQuestionState();

            assertFalse(savedState.cheated);
        });
    }


    @Test
    public void test06ReturnResultYes() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {
            Button yesButton = activity.findViewById(R.id.yesButton);

            // Press Yes button
            yesButton.performClick();

            // Go back to QuestionActivity
            activity.onBackPressed();

            CheatToQuestionState savedState =
                    AppMediator.getInstance().getCheatToQuestionState();

            assertTrue(savedState.cheated);
        });
    }


    @Test
    public void test07BackPressed() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {

            // Go back to QuestionActivity
            activity.onBackPressed();

            CheatToQuestionState savedState =
                AppMediator.getInstance().getCheatToQuestionState();

            assertNull(savedState);
        });
    }

}
