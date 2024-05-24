package es.ulpgc.eite.da.randomquiz;


import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import es.ulpgc.eite.da.randomquiz.question.QuestionActivity;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("ALL")
public class QuizTests {


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


    @Rule
    public ActivityTestRule<QuestionActivity> testRule =
            new ActivityTestRule<>(QuestionActivity.class);

    public QuizSteps steps = new QuizSteps();



    private Activity activity;


    @Before
    public void setUp() {

        try {

            UiDevice device = UiDevice.getInstance(getInstrumentation());
            device.setOrientationNatural();

        } catch (RemoteException e) {
        }

        testRule.launchActivity(new Intent());
        activity = testRule.getActivity();


    }

    @After
    public void tearDown() {

        try {

            UiDevice device = UiDevice.getInstance(getInstrumentation());
            device.setOrientationNatural();

        } catch (RemoteException e) {
        }

        testRule.finishActivity();
    }


    
    @Test
    public void test01_ResponderAPreguntaEnPantallaQuestion() {
        TextView questionTextView = activity.findViewById(R.id.questionText);
        String question = questionTextView.getText().toString();
        int quizIndex = Integer.parseInt(question.substring(0, 2));

        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton "<button>"
        steps.pulsarBoton(R.id.trueButton);
        // And mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuestaDistintoATexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();
        // When girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        // Then mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuestaDistintoATexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();


//        Examples:
//        | question           | button | answer | result    |
//        | Question #1: True  | True   | True   | Correct   |
//        | Question #1: True  | False  | True   | Incorrect |
        
    }

    
    @Test
    public void test02_PasarAPantallaCheatSinResponderAPreguntaEnPantallaQuestion() {
        TextView questionTextView = activity.findViewById(R.id.questionText);
        String question = questionTextView.getText().toString();
        int quizIndex = Integer.parseInt(question.substring(0, 2));

        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBoton(R.id.cheatButton);
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning(activity.getString(R.string.confirmation_text));
        // And ocultar respuesta
        steps.ocultarRespuestaMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // When girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        // Then mostrar mensaje Warning
        steps.mostrarMensajeWarning(activity.getString(R.string.confirmation_text));
        // And ocultar respuesta
        steps.ocultarRespuestaMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // And pulsar boton Back
        steps.pulsarBotonBack();

//        Examples:
//      | question          |
//      | Question #1: True |
            
    }


//    Scenario Outline: volver a pantalla Question sin mostrar respuesta en pantalla Cheat
//
//    Given iniciar pantalla Question
//    And mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    And pulsar boton Cheat
//    And iniciar pantalla Cheat
//    And mostrar mensaje Warning
//    And ocultar respuesta
//    And mostrar botones Yes y No activados
//    And pulsar boton No
//    And finalizar pantalla Cheat
//    And resumir pantalla Question
//    And mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    When girar pantalla
//    Then mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//
//    Examples:
//        | question          |
//        | Question #1: True |

    @Test
    public void test03_VolverAPantallaquizQuestionsinMostrarRespuestaEnPantallaCheat() {
        TextView questionTextView = activity.findViewById(R.id.questionText);
        String question = questionTextView.getText().toString();
        int quizIndex = Integer.parseInt(question.substring(0, 2));

        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBoton(R.id.cheatButton);
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning(activity.getString(R.string.confirmation_text));
        // And ocultar respuesta
        steps.ocultarRespuestaMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // When pulsar boton No
        steps.pulsarBoton(R.id.noButton);
        // Then finalizar pantalla Cheat
        steps.finalizarPantallaCheat();
        // And resumir pantalla Question
        steps.resumirPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();


        //    When girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        //    Then mostrar pregunta "<question>"
        //    And ocultar resultado
        //    And mostrar botones True y False y Cheat activados
        //    And mostrar boton Next desactivado

//        Examples:
//      | question          |
//      | Question #1: True |
        
    }


//    Scenario Outline: mostrar respuesta en pantalla Cheat
//
//    Given iniciar pantalla Question
//    And mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    And pulsar boton Cheat
//    And iniciar pantalla Cheat
//    And mostrar mensaje Warning
//    And ocultar respuesta
//    And mostrar botones Yes y No activados
//    And pulsar boton Yes
//    And mostrar respuesta "<answer>" a pregunta "<question>"
//    And mostrar botones Yes y No desactivados
//    When girar pantalla
//    Then mostrar respuesta "<answer>" a pregunta "<question>"
//    And mostrar botones Yes y No desactivados
//    And pulsar boton Back
//
//    Examples:
//        | question          | answer |
//        | Question #1: True | True   |


    @Test
    public void test04_MostrarRespuestaEnPantallaCheat() {
        TextView questionTextView = activity.findViewById(R.id.questionText);
        String question = questionTextView.getText().toString();
        int quizIndex = Integer.parseInt(question.substring(0, 2));

        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBoton(R.id.cheatButton);
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning(activity.getString(R.string.confirmation_text));
        // And ocultar respuesta
        steps.ocultarRespuestaMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // When pulsar boton Yes
        steps.pulsarBoton(R.id.yesButton);
        // Then mostrar respuesta "<answer>" a pregunta "<question>"
        String answer = (quizAnswers[0])
            ? activity.getString(R.string.true_text)
            : activity.getString(R.string.false_text);
        steps.mostrarRespuestaAPregunta(answer);
        // And mostrar botones Yes y No desactivados
        steps.mostrarBotonesYesYNoDesactivados();

        // When girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        // Then mostrar respuesta "<answer>" a pregunta "<question>"
        steps.mostrarRespuestaAPregunta(answer);
        // And mostrar botones Yes y No desactivados
        steps.mostrarBotonesYesYNoDesactivados();

        // And pulsar boton Back
        steps.pulsarBotonBack();

//        Examples:
//      | question          | answer |
//      | Question #1: True | True   |
        
    }


//    Scenario Outline: volver a pantalla Question mostrando respuesta en pantalla Cheat
//
//    Given iniciar pantalla Question
//    And mostrar pregunta "<question1>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    And pulsar boton Cheat
//    And iniciar pantalla Cheat
//    And mostrar mensaje Warning
//    And ocultar respuesta
//    And mostrar botones Yes y No activados
//    And pulsar boton Yes
//    And mostrar botones Yes y No desactivados
//    And mostrar respuesta "<answer>" a pregunta "<question1>"
//    And girar pantalla
//    And pulsar boton Back
//    And finalizar pantalla Cheat
//    And resumir pantalla Question
//    And mostrar pregunta "<question2>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    When girar pantalla
//    Then mostrar pregunta "<question2>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado

    @Test
    public void test05_VolverAPantallaQuestionMostrandoRespuestaEnPantallaCheat() {
        TextView questionTextView = activity.findViewById(R.id.questionText);
        String question = questionTextView.getText().toString();
        int quizIndex = Integer.parseInt(question.substring(0, 2));

        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question1>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBoton(R.id.cheatButton);
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning(activity.getString(R.string.confirmation_text));
        // And ocultar respuesta
        steps.ocultarRespuestaMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // And pulsar boton Yes
        steps.pulsarBoton(R.id.yesButton);
        // And mostrar botones Yes y No desactivados
        steps.mostrarBotonesYesYNoDesactivados();
        // And mostrar respuesta "<answer>" a pregunta "<question1>"
        String answer = (quizAnswers[0])
            ? activity.getString(R.string.true_text)
            : activity.getString(R.string.false_text);
        steps.mostrarRespuestaAPregunta(answer);
        // When pulsar boton Back
        steps.pulsarBotonBack();
        // Then finalizar pantalla Cheat
        steps.finalizarPantallaCheat();
        // And resumir pantalla Question
        steps.resumirPantallaQuestion();
        // And mostrar pregunta "<question2>"
        steps.mostrarPregunta(quizQuestions[quizIndex]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();

        // When girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        // And mostrar pregunta "<question2>"
        steps.mostrarPregunta(quizQuestions[quizIndex]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();


//        Examples:
//      | question1         | answer | question2          |
//      | Question #1: True | True   | Question #2: False |
            
    }

    @Test
    public void test06_PasarASiguientePreguntaEnPantallaQuestion() {
        TextView questionTextView = activity.findViewById(R.id.questionText);
        String question = questionTextView.getText().toString();
        int quizIndex = Integer.parseInt(question.substring(0, 2));

        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question1>"
        steps.mostrarPregunta(quizQuestions[quizIndex-1]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton "<button>"
        steps.pulsarBoton(R.id.trueButton);
        // And mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuestaDistintoATexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();
        // And girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        // And mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuestaDistintoATexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();
        // And pulsar boton Next
        steps.pulsarBoton(R.id.nextButton);
        // And mostrar pregunta "<question2>"
        steps.mostrarPregunta(quizQuestions[quizIndex]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // When girar pantalla
        steps.girarPantalla(activity.getRequestedOrientation());
        // Then mostrar pregunta "<question2>"
        steps.mostrarPregunta(quizQuestions[quizIndex]);
        // And ocultar resultado
        steps.ocultarResultadoMostrandoTexto(activity.getString(R.string.empty_text));
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();

//        Examples:
//      | question1         | button  | answer  | result    | question2          |
//      | Question #1: True | True    | True    | Correct   | Question #2: False |
//      | Question #1: True | False   | True    | Incorrect | Question #2: False |
            
    }
}