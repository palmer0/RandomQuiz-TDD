package es.ulpgc.eite.da.randomquiz.question;

import java.lang.ref.WeakReference;


public interface QuestionContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayQuestionData(QuestionViewModel viewModel);
    void navigateToCheatScreen();

  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void onResumeCalled();
    void trueButtonClicked();
    void falseButtonClicked();
    void cheatButtonClicked();
    void nextButtonClicked();

    void onCreateCalled();
    void onRecreateCalled();
  }

  interface Model {

    String getCorrectResultText();
    String getIncorrectResultText();

    void setQuizAnswers(boolean[] answers);

    void setQuizQuestions(String[] questions);

    String getCurrentQuestion();
    boolean getCurrentAnswer();
    boolean isLastQuestion();
    void incrQuizIndex();
    void setCurrentIndex(int index);


    String[] getQuizQuestions();
    boolean[] getQuizAnswers();
  }

}