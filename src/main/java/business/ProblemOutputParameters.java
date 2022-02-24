package business;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProblemOutputParameters {

  private final Pizza pizza;

  public String createOutput() {
    return pizza.createOutput();
  }
}
