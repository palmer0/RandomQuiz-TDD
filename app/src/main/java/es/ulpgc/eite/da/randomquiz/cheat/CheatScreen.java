package es.ulpgc.eite.da.randomquiz.cheat;


import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.randomquiz.R;
import es.ulpgc.eite.da.randomquiz.app.AppMediator;


public class CheatScreen {


  public static void configure(CheatContract.View view) {

    WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = AppMediator.getInstance();
    CheatContract.Presenter presenter = new CheatPresenter(mediator);

    CheatModel model = new CheatModel();
    model.setTrueAnswerText(context.get().getString(R.string.true_label));
    model.setFalseAnswerText(context.get().getString(R.string.false_label));

    presenter.injectView(new WeakReference<>(view));
    presenter.injectModel(model);
    view.injectPresenter(presenter);

  }

}
