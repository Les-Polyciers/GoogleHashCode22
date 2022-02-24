package exam_overhead.main;

import business.ProblemInputParameters;
import business.ProblemOutputParameters;
import business.Solver;
import exam_overhead.ecriture.Output;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

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
    ProblemInputParameters inputParameters = new ProblemInputParameters(input);
    ProblemOutputParameters outputParameters = Solver.solve(inputParameters);

    DataSetFileName dataSetFileName = new DataSetFileName(file.getName());

    Output.write(outputParameters.createOutput(), dataSetFileName);
  }
}
