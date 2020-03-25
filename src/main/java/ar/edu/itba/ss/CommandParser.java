package ar.edu.itba.ss;

import org.apache.commons.cli.*;

class CommandParser {

    static int T;
    static double TEMPERATURE = 0;
    static int N;

    private static Options createOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Show help menu");
        options.addOption("t", "time", true, "Time to run simulation");
        options.addOption("n","particles", true, "Number of particles");
        options.addOption("T", "temperature", true, "Initial temperature of the system");
        return options;
    }

    private static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Brownian Motion", options);
        System.exit(0);
    }

    static void parseCommandLine(String[] args) {
        Options options = createOptions();
        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("h")) printHelp(options);
            if(cmd.hasOption("t")) T = Integer.parseInt(cmd.getOptionValue("t"));
            if(cmd.hasOption("n")) N = Integer.parseInt(cmd.getOptionValue("n"));
            if(cmd.hasOption("T")) TEMPERATURE = Double.parseDouble(cmd.getOptionValue("T"));

        }catch (Exception e) {
            System.out.println("Invalid command format");
            printHelp(options);
        }
    }

}
