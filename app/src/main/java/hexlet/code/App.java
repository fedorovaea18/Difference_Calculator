package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionRequested;

    @Override
    public void run() {
        if (helpRequested) {
            CommandLine.usage(this, System.out);
        } else if (versionRequested) {
            CommandLine versionCommand = new CommandLine(this.getClass());
            versionCommand.printVersionHelp(System.out);
        } else {
            // Add your logic for comparing two configuration files here
        }
    }

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }
}
