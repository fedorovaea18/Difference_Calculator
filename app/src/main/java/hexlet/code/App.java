package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;
import com.fasterxml.jackson.databind.ObjectMapper;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<String> {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Override
    public String call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String contentFile1 = Files.readString(path1);
        String contentFile2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(contentFile1, Map.class);
        Map<String, Object> map2 = mapper.readValue(contentFile2, Map.class);
        String diff = Differ.generate(map1, map2);
        System.out.println(diff);
        return diff;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
