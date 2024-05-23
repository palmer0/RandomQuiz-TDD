package es.ulpgc.eite.da.randomquiz.app;

import java.util.Objects;

public class QuestionToCheatState {

  public boolean answer;

  public QuestionToCheatState(boolean answer) {
    this.answer = answer;
  }

//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj) return true;
//    if (obj == null || getClass() != obj.getClass()) return false;
//    QuestionToCheatState that = (QuestionToCheatState) obj;
//    return answer == that.answer;
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(answer);
//  }

}
