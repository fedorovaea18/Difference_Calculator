package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Override
    public void run() {
    }

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }
}
