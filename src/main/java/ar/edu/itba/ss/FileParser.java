package ar.edu.itba.ss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static ar.edu.itba.ss.CommandParser.N;

public class FileParser {

    static double L;
    // dynamic file x y theta
    static Queue<Particle> particles = new LinkedList<>();

    static void parseDynamicFile() throws FileNotFoundException{
        File dynamicFile = new File(CommandParser.dynamicFilePath);
        Scanner sc = new Scanner(dynamicFile);
        L = sc.nextDouble();
        for (int i = 0; i < N; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double theta = sc.nextDouble();
            particles.add(new Particle(i + 1, x, y, theta));
        }
    }
}
