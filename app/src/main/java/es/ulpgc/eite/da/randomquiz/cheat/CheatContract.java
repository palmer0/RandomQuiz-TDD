package es.ulpgc.eite.da.randomquiz.cheat;

import java.lang.ref.WeakReference;


public interface CheatContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayCheatData(CheatViewModel viewModel);

        void finishView();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onPauseCalled();

        void onDestroyCalled();

        void yesButtonClicked();

        void noButtonClicked();

        void onCreateCalled();

        void onRecreateCalled();

        void onResumeCalled();
    }

    interface Model {

        String getEmptyAnswerText();

        String getFalseAnswerText();

        String getTrueAnswerText();
    }
}