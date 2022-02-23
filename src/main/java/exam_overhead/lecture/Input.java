package exam_overhead.lecture;

import exam_solution.Client;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public final class Input {

  public static Set<Client> read(InputStream input) {
    try (Scanner scanner = new Scanner(input).useDelimiter("\n")) {
      int nbClients = Integer.parseInt(scanner.next());
      return IntStream.iterate(0, i -> i < nbClients, i -> i + 2)
          .mapToObj(i -> new Client(scanner.next(), scanner.next()))
          .collect(Collectors.toUnmodifiableSet());
    }
  }
}
