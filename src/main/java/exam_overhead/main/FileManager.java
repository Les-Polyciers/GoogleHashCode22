package exam_overhead.main;

import business.ProblemInputParameters;
import business.ProblemOutputParameters;
import business.Solver;
import exam_overhead.ecriture.Output;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@UtilityClass
public final class FileManager {

  public static void computeAllInputFiles() throws URISyntaxException, IOException {

    for (File inputFile : getAllInputFiles()) {
      computeFile(inputFile);
    }
  }

  private static File[] getAllInputFiles() throws URISyntaxException {

    @Nullable URL inputDataLocation = Main.class.getClassLoader().getResource("inputData");
    URI inputDataFolder = Objects.requireNonNull(inputDataLocation).toURI();
    @Nullable File[] inputFiles = new File(inputDataFolder).listFiles();
    return Objects.requireNonNull(inputFiles);
  }

  private static void computeFile(File file) throws IOException, URISyntaxException {
    InputStream input = new FileInputStream(file);

    ProblemInputParameters inputParameters = new ProblemInputParameters(input);
    ProblemOutputParameters outputParameters = Solver.solve(inputParameters);

    DataSetFileName dataSetFileName = new DataSetFileName(file.getName());

    Output.write(outputParameters.createOutput(), dataSetFileName);
  }
}
