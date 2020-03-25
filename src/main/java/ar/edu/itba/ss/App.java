package ar.edu.itba.ss;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static ar.edu.itba.ss.CellIndex.cellIndexAlgorithm;
import static ar.edu.itba.ss.CellIndex.populateCells;
import static ar.edu.itba.ss.CommandParser.*;
import static ar.edu.itba.ss.FileParser.*;
import static ar.edu.itba.ss.OffLattice.calculateNewPosition;
import static ar.edu.itba.ss.OffLattice.calculateNewTheta;

public class App {

    static int M;

    public static void main( String[] args ) {
        long startTime = System.currentTimeMillis();
        CommandParser.parseCommandLine(args);
        try {
            parseDynamicFile();
        } catch (Exception e) {
            System.out.println("Invalid file name...");
            System.exit(1);
        }
        File file = new File("output.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output.txt", "UTF-8");
        } catch (Exception e) {
            System.out.println("Couldn't write output to file...");
            System.exit(1);
        }

        M = (int)Math.floor(L/RC);
        System.out.println("Time: " + T);
        System.out.println("M: " + M);
        System.out.println("Speed of particles: " + V);

        populateCells();
        outputToFile(0, writer);
        for(int i = 1 ; i <= T ; i++) {
            // calculate neighbours
            cellIndexAlgorithm();
            // recalculate new values (x, y, theta)
            Map<Particle, Double> angleMap = new HashMap<>();
            for(Particle p : particles) {
                calculateNewPosition(p);
                double theta = calculateNewTheta(p);
                angleMap.put(p, theta);
            }
            for(Particle p: particles) {
                p.setTheta(angleMap.get(p));
            }
            // store values in file
            outputToFile(i, writer);
            // recalculate new particles in cell
            populateCells();
        }
        writer.close();
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
    }

    private static void outputToFile(int time, PrintWriter writer) {
        writer.println(N);
        writer.println("time: " + time);
        for (Particle p : particles) {
            writer.println(p.getId() + " " + p.getX() + " " + p.getY() + " " + Math.cos(p.getTheta()) * V + " " + Math.sin(p.getTheta()) * V);
        }

    }
}
