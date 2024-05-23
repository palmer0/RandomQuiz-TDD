package es.ulpgc.eite.da.randomquiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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


    private String[] quizQuestions = {
        "01) Christian Bale played Batman in 'The Dark Knight Rises'?", // 1
        "02) The Gremlins movie was released in 1986?",  // 2
        "03) Brad Pitt played Danny Ocean in Ocean's Eleven, Ocean's Twelve and Ocean's Thirteen?",  // 3
        "04) A spoon full of sugar' came from the 1964 movie Mary Poppins?",  // 4
        "05) The song 'I don't want to miss a thing' featured in Armageddon?", // 5
        "06) Will Smith has a son called Jaden?", // 6
        "07) Mark Ruffalo played Teddy Daniels in Shutter Island?", // 7
        "08) Mike Myers starred in the 'Cat in the Hat' 2003 children's movie?", // 8
        "09) Ryan Reynolds is married to Scarlett Johansson?", // 9
        "10) The movie 'White House Down' was released in 2014?",  // 10
        "11) Michael Douglas starred in Basic Instinct, Falling Down and The Game?", // 11
        "12) Colin Firth won an Oscar for his performance in the historical movie 'The King's Speech'?",  // 12
        "13) Cameron Diaz and Ashton Kutcher starred in the movie 'What happens in Vegas'?", // 13
        "14) Arnold Schwarzenegger played lead roles in Rocky, Rambo and Judge Dredd?", // 14
        "15) The Titanic movie featured the song 'My Heart Will Go On'?", // 15
        "16) Eddie Murphy narrates the voice of Donkey in the Shrek movies?", // 16
        "17) Nicole Kidman played Poison Ivy in 'Batman and Robin'?", // 17
        "18) The Lara Croft: Tomb Raider movie was released in 2003?", // 18
        "19) Hallie Berry played the character Rogue in X Men?", // 19
        "20) The Teenage Mutant Ninja Turtles are named after famous artists?" // 20
    };

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

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[0]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);
        */

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

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

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[0]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);
        */

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

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
            List<String> expectedList = Arrays.asList(
                activity.getString(R.string.false_text),
                activity.getString(R.string.true_text)
            );
            assertTrue(expectedList.contains(answerTextView.getText().toString()));
            /*assertEquals(
                activity.getString(R.string.true_text),
                answerTextView.getText().toString()
            );*/

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test02ShowAnswerFalse() {

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[1]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);
        */

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[1]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

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
            List<String> expectedList = Arrays.asList(
                activity.getString(R.string.false_text),
                activity.getString(R.string.true_text)
            );
            assertTrue(expectedList.contains(answerTextView.getText().toString()));
            /*assertEquals(
                activity.getString(R.string.false_text),
                answerTextView.getText().toString()
            );*/

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test03RotateScreen() {

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[0]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);
        */

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

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
            List<String> expectedList = Arrays.asList(
                activity.getString(R.string.false_text),
                activity.getString(R.string.true_text)
            );
            assertTrue(expectedList.contains(answerTextView.getText().toString()));
            /*assertEquals(
                activity.getString(R.string.true_text),
                answerTextView.getText().toString()
            );*/

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test04ReturnResultNo() {

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[0]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);
        */

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        scenario.onActivity(activity -> {
            Button noButton = activity.findViewById(R.id.noButton);

            // Press No button
            noButton.performClick();

            /*
            ShadowActivity shadowActivity = shadowOf(activity);
            Intent resultIntent = shadowActivity.getResultIntent();
            boolean answerCheated = resultIntent.getBooleanExtra(
                CheatActivity.EXTRA_CHEATED, false
            );

            // QuestionActivity should be resumed with CheatActivity intent
            assertEquals(RESULT_OK, shadowActivity.getResultCode());
            assertFalse(answerCheated);
            */

            CheatToQuestionState savedState =
                AppMediator.getInstance().getCheatToQuestionState();

            assertFalse(savedState.cheated);
        });
    }


    @Test
    public void test05ReturnResultYes() {

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[0]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);
        */

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

            /*
            ShadowActivity shadowActivity = shadowOf(activity);
            Intent resultIntent = shadowActivity.getResultIntent();
            boolean answerCheated = resultIntent.getBooleanExtra(
                CheatActivity.EXTRA_CHEATED, false
            );

            // QuestionActivity should be resumed with CheatActivity intent
            assertEquals(RESULT_OK, shadowActivity.getResultCode());
            assertTrue(answerCheated);
            */

            CheatToQuestionState savedState =
                    AppMediator.getInstance().getCheatToQuestionState();

            assertTrue(savedState.cheated);
        });
    }


    @Test
    public void test06BackPressed() {

        /*
        int[] answersArray = ApplicationProvider
            .getApplicationContext()
            .getResources().getIntArray(R.array.answers_array);

        Intent intent = new Intent(
            ApplicationProvider.getApplicationContext(),
            CheatActivity.class
        );
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[0]);

        ActivityScenario<CheatActivity> scenario = ActivityScenario.launch(intent);
        */

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {

            // Go back to QuestionActivity
            activity.onBackPressed();

            /*
            ShadowActivity shadowActivity = shadowOf(activity);
            Intent resultIntent = shadowActivity.getResultIntent();
            boolean answerCheated = resultIntent.getBooleanExtra(
                CheatActivity.EXTRA_CHEATED, false
            );

            // QuestionActivity should be resumed with CheatActivity intent
            assertEquals(RESULT_OK, shadowActivity.getResultCode());
            assertFalse(answerCheated);
            */

            CheatToQuestionState savedState =
                AppMediator.getInstance().getCheatToQuestionState();

            assertNull(savedState);
        });
    }

}
