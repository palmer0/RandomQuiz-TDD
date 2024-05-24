package es.ulpgc.eite.da.randomquiz.cheat;

/**
 * Created by Luis on March, 2021
 */
public class CheatModel implements CheatContract.Model {

  public static String TAG = "RandomQuiz.CheatModel";


  private String falseAnswerText, trueAnswerText;


  public CheatModel() {

  }

  public void setFalseAnswerText(String label) {
    falseAnswerText = label;
  }

  public void setTrueAnswerText(String label) {
    trueAnswerText = label;
  }

  @Override
  public String getFalseAnswerText() {
    return falseAnswerText;
  }


  @Override
  public String getTrueAnswerText() {
    return trueAnswerText;
  }
}