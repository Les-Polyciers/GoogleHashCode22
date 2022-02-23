package exam_overhead.main;

import business.Client;
import business.Solver;
import exam_overhead.ecriture.Output;
import exam_overhead.lecture.Input;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Set;

@UtilityClass
public final class FileManager {

  public static void computeAllInputFiles() throws URISyntaxException, IOException {

    URL inputDataFolder =
        Objects.requireNonNull(Main.class.getClassLoader().getResource("inputData"));

    File[] inputFiles = Objects.requireNonNull(new File(inputDataFolder.toURI()).listFiles());

    for (File inputFile : inputFiles) {
      computeFile(inputFile);
    }
  }

  public static void computeFile(File file) throws IOException, URISyntaxException {
    InputStream input = new FileInputStream(file);

    // Les deux lignes de m�tier
    Set<Client> clients = Input.read(input);
    String output = Solver.solve(clients);

    DataSetFileName dataSetFileName = new DataSetFileName(file.getName());

    Output.write(output, dataSetFileName);
  }
}
