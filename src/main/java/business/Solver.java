package business;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public final class Solver {

  public static String solve(Set<Client> clients) {

    Client anyClient = clients.stream().findAny().orElseThrow();
    Pizza pizza = Pizza.fromClient(anyClient);
    return pizza.createOutput();
  }
}
