package es.ulpgc.eite.da.randomquiz.cheat;

/**
 * Created by Luis on March, 2021
 */
public class CheatModel implements CheatContract.Model {

    public static String TAG = "RandomQuiz.CheatModel";


    private String falseAnswerText, trueAnswerText;
    private String emptyAnswerText;

    @Override
    public void setEmptyAnswerText(String text) {
        this.emptyAnswerText = text;
    }

    @Override
    public String getEmptyAnswerText() {
        return emptyAnswerText;
    }

    @Override
    public String getFalseAnswerText() {
        return falseAnswerText;
    }

    public void setFalseAnswerText(String label) {
        falseAnswerText = label;
    }

    @Override
    public String getTrueAnswerText() {
        return trueAnswerText;
    }

    public void setTrueAnswerText(String label) {
        trueAnswerText = label;
    }
}