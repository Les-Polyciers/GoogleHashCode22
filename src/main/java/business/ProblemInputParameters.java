package business;

import lombok.Getter;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class ProblemInputParameters {

  private final Set<Client> clients;

  public ProblemInputParameters(InputStream input) {
    try (Scanner scanner = new Scanner(input).useDelimiter("\n")) {
      int nbClients = Integer.parseInt(scanner.next());
      this.clients =
          Stream.generate(() -> new Client(scanner.next(), scanner.next()))
              .limit(nbClients)
              .collect(Collectors.toUnmodifiableSet());
    }
  }
}
