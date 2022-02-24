package exam_overhead.main;

import business.best_score_first.BestScoreFirst;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

  public static void main(String[] args) throws URISyntaxException, IOException {
    new FileManager(new BestScoreFirst()).computeAllInputFiles();
  }
}
