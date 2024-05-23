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

import android.content.pm.ActivityInfo;
import android.os.RemoteException;

import androidx.test.uiautomator.UiDevice;

public class QuizSteps {


  private static final int DELAY_IN_SECS = 0 * 1000;

  public void iniciarPantallaQuestion() {


    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }

  }

  public void mostrarPregunta(String q) {
    onView(withId(R.id.questionText)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.questionText)).check(matches(withText(q)));
  }

  public void ocultarResultado(String r) {
    //onView(withId(R.id.questionText)).check(matches(not(isCompletelyDisplayed())));
    //onView(withId(R.id.replyText)).check(matches(isCompletelyDisplayed()));
    //onView(withId(R.id.replyText)).check(matches(withText("???")));
    onView(withId(R.id.resultText)).check(matches(withText(r)));
  }


  public void ocultarRespuesta(String r) {
    //onView(withId(R.id.answerText)).check(matches(isCompletelyDisplayed()));
    //onView(withId(R.id.answerText)).check(matches(withText("???")));
    onView(withId(R.id.answerText)).check(matches(withText(r)));
  }

  public void mostrarBotonesTrueYFalseYCheatActivados() {
    onView(withId(R.id.trueButton)).check(matches(isEnabled()));
    onView(withId(R.id.falseButton)).check(matches(isEnabled()));
    onView(withId(R.id.cheatButton)).check(matches(isEnabled()));
  }

  public void mostrarBotonNextDesactivado() {
    onView(withId(R.id.nextButton)).check(matches(not(isEnabled())));
  }

//  public void pulsarBoton(String b, String tb) {
//
//    //String tb = activity.getString(R.string.true_button_text);
//    //int button = (b.equals("True")) ? R.id.trueButton : R.id.falseButton;
//    int button = (b.equals(tb)) ? R.id.trueButton : R.id.falseButton;
//    onView(withId(button)).check(matches(isCompletelyDisplayed()));
//    onView(withId(button)).perform(click());
//
//    try {
//      Thread.sleep(DELAY_IN_SECS);
//    } catch (InterruptedException e) {
//    }
//  }


  public void pulsarBoton(int button) {

    onView(withId(button)).check(matches(isCompletelyDisplayed()));
    onView(withId(button)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

//  public void mostrarResultadoARespuesta(String r, String a) {
//    onView(withId(R.id.replyText)).check(matches(isCompletelyDisplayed()));
//    onView(withId(R.id.replyText)).check(matches(withText(a)));
//  }


  public void mostrarResultadoARespuesta(String reply) {
    onView(withId(R.id.resultText)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.resultText)).check(matches(withText(reply)));
  }


  public void mostrarBotonesTrueYFalseYCheatDesactivados() {
    onView(withId(R.id.trueButton)).check(matches(not(isEnabled())));
    onView(withId(R.id.falseButton)).check(matches(not(isEnabled())));
    onView(withId(R.id.cheatButton)).check(matches(not(isEnabled())));

  }

  public void mostrarBotonNextActivado() {
    onView(withId(R.id.nextButton)).check(matches(isEnabled()));
  }

  public void pulsarBotonCheat() {

    onView(withId(R.id.cheatButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.cheatButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void iniciarPantallaCheat() {
    //getInstrumentation().waitForIdleSync();

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void mostrarMensajeWarning(String w) {
    onView(withId(R.id.confirmationText)).check(matches(isCompletelyDisplayed()));
    //onView(withId(R.id.confirmationText))
    //    .check(matches(withText("Are you sure?")));
    onView(withId(R.id.confirmationText)).check(matches(withText(w)));
  }

  public void mostrarBotonesYesYNoActivados() {
    onView(withId(R.id.yesButton)).check(matches(isEnabled()));
    onView(withId(R.id.noButton)).check(matches(isEnabled()));
  }

  public void pulsarBotonNo() {

    onView(withId(R.id.noButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.noButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
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

  public void pulsarBotonYes() {

    onView(withId(R.id.yesButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.yesButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

//  public void mostrarRespuestaAPregunta(String a, String q) {
//    onView(withId(R.id.answerText)).check(matches(isCompletelyDisplayed()));
//    onView(withId(R.id.answerText)).check(matches(withText(a)));
//  }


  public void mostrarRespuestaAPregunta(String answer) {

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

    //getInstrumentation().waitForIdleSync();
    pressBack();
  }

  public void pulsarBotonNext() {

    onView(withId(R.id.nextButton)).check(matches(isCompletelyDisplayed()));
    onView(withId(R.id.nextButton)).perform(click());

    try {
      Thread.sleep(DELAY_IN_SECS);
    } catch (InterruptedException e) {
    }
  }

  public void girarPantalla(int orientation) {


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