package es.ulpgc.eite.da.randomquiz.cheat;

/**
 * Created by Luis on March, 2021
 */
public class CheatModel implements CheatContract.Model {

  public static String TAG = "RandomQuiz.CheatModel";

  private String falseLabel, trueLabel;


  public CheatModel() {

  }

  public void setFalseLabel(String label) {
    falseLabel = label;
  }

  public void setTrueLabel(String label) {
    trueLabel = label;
  }

  @Override
  public String getFalseLabel() {
    return falseLabel;
  }


  @Override
  public String getTrueLabel() {
    return trueLabel;
  }
}