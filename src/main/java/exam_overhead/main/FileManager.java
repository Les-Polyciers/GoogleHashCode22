package exam_overhead.main;

import exam_overhead.ecriture.Output;
import exam_overhead.lecture.Input;
import exam_solution.Client;
import exam_solution.Solver;
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

    // Les deux lignes de métier
    Set<Client> clients = Input.read(input);
    String output = Solver.solve(clients);

    String fileName = file.getName();
    DataSetFileName dataSetFileName = new DataSetFileName(fileName);

    Output.write(output, dataSetFileName);
  }
}
