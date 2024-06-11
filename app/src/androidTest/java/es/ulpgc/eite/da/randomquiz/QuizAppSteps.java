package es.ulpgc.eite.da.randomquiz;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.not;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.RemoteException;

import androidx.test.uiautomator.UiDevice;

public class QuizAppSteps {


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

  private static final int DELAY_IN_SECS = 0 * 1000;

  private final Activity activity;

  public QuizAppSteps(Activity activity) {
    this.activity = activity;
  }


  public void iniciarPantallaQuestion() {


    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }

  }

  public void mostrarPregunta(int quizIndex) {
    String question = quizQuestions[quizIndex];
    onView(withId(R.id.questionText)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.questionText)).check(matches(withText(question)));
  }


  public void ocultarResultado() {
    String text = activity.getString(R.string.empty_text);
    onView(withId(R.id.resultText)).check(matches(withText(text)));
  }

  public void ocultarRespuesta() {
    String text =  activity.getString(R.string.empty_text);
    onView(withId(R.id.answerText)).check(matches(withText(text)));
  }


  public void mostrarBotonesTrueYFalseYCheatActivados() {
    onView(withId(R.id.trueButton)).check(matches(isEnabled()));
    onView(withId(R.id.falseButton)).check(matches(isEnabled()));
    onView(withId(R.id.cheatButton)).check(matches(isEnabled()));
  }

  public void mostrarBotonNextDesactivado() {
    onView(withId(R.id.nextButton)).check(matches(not(isEnabled())));
  }

  public void pulsarBotonCheat() {

    onView(withId(R.id.cheatButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.cheatButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }


  public void pulsarBotonNo() {

    onView(withId(R.id.noButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.noButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }


  public void pulsarBotonYes() {

    onView(withId(R.id.yesButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.yesButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void pulsarBotonTrue() {

    onView(withId(R.id.trueButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.trueButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }


  public void pulsarBotonNext() {

    onView(withId(R.id.nextButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.nextButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }


  public void mostrarResultadoARespuesta() {
    String text = activity.getString(R.string.empty_text);
    onView(withId(R.id.resultText)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.resultText)).check(matches(not(withText(text))));
  }

  public void mostrarBotonesTrueYFalseYCheatDesactivados() {
    onView(withId(R.id.trueButton)).check(matches(not(isEnabled())));
    onView(withId(R.id.falseButton)).check(matches(not(isEnabled())));
    onView(withId(R.id.cheatButton)).check(matches(not(isEnabled())));

  }

  public void mostrarBotonNextActivado() {
    onView(withId(R.id.nextButton)).check(matches(isEnabled()));
  }


  public void iniciarPantallaCheat() {

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void mostrarMensajeWarning() {
    String text = activity.getString(R.string.confirmation_text);
    onView(withId(R.id.confirmationText)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.confirmationText)).check(matches(withText(text)));
  }


  public void mostrarBotonesYesYNoActivados() {
    onView(withId(R.id.yesButton)).check(matches(isEnabled()));
    onView(withId(R.id.noButton)).check(matches(isEnabled()));
  }


  public void finalizarPantallaCheat() {
    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void resumirPantallaQuestion() {
    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void mostrarRespuestaAPregunta(int quizIndex) {

    String answer = (quizAnswers[quizIndex-1])
        ? activity.getString(R.string.true_text)
        : activity.getString(R.string.false_text);

    onView(withId(R.id.answerText)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.answerText)).check(matches(withText(answer)));
  }

  public void mostrarBotonesYesYNoDesactivados() {
    onView(withId(R.id.yesButton)).check(matches(not(isEnabled())));
    onView(withId(R.id.noButton)).check(matches(not(isEnabled())));

  }

  public void pulsarBotonBack() {
    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }

    pressBack();
  }


  public void girarPantalla() {

    int orientation = activity.getRequestedOrientation();

    try {

      UiDevice device = UiDevice.getInstance(getInstrumentation());

      if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        device.setOrientationNatural();

      } else {
        device.setOrientationLeft();
      }

    } catch (RemoteException e) {
    }

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

}