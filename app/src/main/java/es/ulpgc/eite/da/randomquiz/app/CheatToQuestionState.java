package es.ulpgc.eite.da.randomquiz.app;

import java.util.Objects;

public class CheatToQuestionState {

  public boolean cheated;

  public CheatToQuestionState(Boolean cheated) {
    this.cheated = cheated;
  }

//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj) return true;
//    if (obj == null || getClass() != obj.getClass()) return false;
//    CheatToQuestionState that = (CheatToQuestionState) obj;
//    return cheated == that.cheated;
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(cheated);
//  }
}
