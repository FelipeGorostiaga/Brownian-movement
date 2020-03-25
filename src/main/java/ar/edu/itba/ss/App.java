package ar.edu.itba.ss;

import java.io.File;
import java.io.PrintWriter;
import static ar.edu.itba.ss.CommandParser.N;
import static ar.edu.itba.ss.CommandParser.T;
import static ar.edu.itba.ss.Generator.generateParticles;
import static ar.edu.itba.ss.Generator.particles;


public class App {

    public static void main( String[] args ) {
        long startTime = System.currentTimeMillis();
        CommandParser.parseCommandLine(args);

        File file = new File("output.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output.txt", "UTF-8");
        } catch (Exception e) {
            System.out.println("Couldn't write output to file...");
            System.exit(1);
        }

        generateParticles();

        for(double t = 0 ; t < T ; ) {


        }





        writer.close();
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
    }

    private static void outputToFile(int time, PrintWriter writer) {
        writer.println(N);
        writer.println("time: " + time);
        for (Particle p : particles) {
            writer.println(p.getId() + " " + p.getX() + " " + p.getY() + " " + p.getVx() + " "
                            + p.getVy() + " " + p.getRadius());
        }
    }
}
