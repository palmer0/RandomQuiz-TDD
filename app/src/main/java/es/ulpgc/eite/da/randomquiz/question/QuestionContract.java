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

        void onPauseCalled();

        void onDestroyCalled();

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

        String getCurrentQuestion();

        boolean getCurrentAnswer();

        boolean isLastQuestion();

        void incrQuizIndex();

        void setCurrentIndex(int index);

        void setEmptyResultText(String text);

        String getEmptyResultText();

        String[] getQuizQuestions();

        void setQuizQuestions(String[] questions);

        boolean[] getQuizAnswers();

        void setQuizAnswers(boolean[] answers);
    }

}