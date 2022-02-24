package business;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Solver {

  public static ProblemOutputParameters solve(ProblemInputParameters inputParameters) {
    Client anyClient = inputParameters.getClients().stream().findAny().orElseThrow();
    Pizza pizza = new Pizza(anyClient.getLikedIngredients());
    return new ProblemOutputParameters(pizza);
  }
}
