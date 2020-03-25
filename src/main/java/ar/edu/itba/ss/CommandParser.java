package ar.edu.itba.ss;

import org.apache.commons.cli.*;

class CommandParser {

    static String dynamicFilePath;
    static double RC;
    static int T;
    static int N;
    static double V = 0.03;
    static double alpha;
    static boolean periodicContour = true;

    private static Options createOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Show help menu");
        options.addOption("t", "time", true, "Time to run simulation");
        options.addOption("n","particles", true, "Number of particles");
        //options.addOption("l","area length", true, "Length of the area");
        options.addOption("d","dynamic", true, "Dynamic file path");
        options.addOption("v","velocity", true, "Velocity of particles");
        options.addOption("rc", "radius", true, "Interaction radius");
        options.addOption("a", "alpha", true, "Enclosed random angle noise");
        //options.addOption("p", "periodic contour", false, "Activate periodic contour distance");
        return options;
    }

    private static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Off Latice", options);
        System.exit(0);
    }

    static void parseCommandLine(String[] args) {
        Options options = createOptions();
        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("h")) printHelp(options);
            if(cmd.hasOption("t")) T = Integer.parseInt(cmd.getOptionValue("t"));
            //if(cmd.hasOption("l")) L = Integer.parseInt(cmd.getOptionValue("l"));
            if(cmd.hasOption("n")) N = Integer.parseInt(cmd.getOptionValue("n"));
            if(cmd.hasOption("a")) alpha = Double.parseDouble(cmd.getOptionValue("a"));
            if(cmd.hasOption("v")) V = Double.parseDouble(cmd.getOptionValue("v"));
            if (!cmd.hasOption("d")) {
                System.out.println("You must specify a dynamic file!");
                printHelp(options);
            }
            dynamicFilePath = cmd.getOptionValue("d");
            if(cmd.hasOption("rc")) RC = Double.parseDouble(cmd.getOptionValue("rc"));
            //if(cmd.hasOption("p")) periodicContour = true;

        }catch (Exception e) {
            System.out.println("Invalid command format");
            printHelp(options);
        }
    }

}
