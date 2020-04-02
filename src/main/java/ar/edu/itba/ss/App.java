package ar.edu.itba.ss;

import java.io.File;
import java.io.PrintWriter;

import static ar.edu.itba.ss.BrownianMotion.*;
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
        int collisionCant = 0;
        outputToFile(0,writer);


        for(double t = 0 ; t < T ; ) {
            Collision collisionType = null;
            Particle pi = null;
            Particle pj = null;
            double tc = Double.POSITIVE_INFINITY;
            for(Particle p : particles) {
                double horizontalWallTime = calculateHorizontalCollisionTime(p);
                double verticalWallTime = calculateVerticalCollisionTime(p);
                if(horizontalWallTime < tc) {
                    tc = horizontalWallTime;
                    pi = p;
                    collisionType = Collision.HORIZONTAL_WALL;
                }
                if(verticalWallTime < tc) {
                    tc = verticalWallTime;
                    pi = p;
                    collisionType = Collision.VERTICAL_WALL;
                }
                for(Particle p2 : particles) {
                    if(p.getId() < p2.getId()) {
                        double collisionTime = calculateParticleCollisionTime(p, p2);
                        if(collisionTime < tc) {
                            tc = collisionTime;
                            pi = p;
                            pj = p2;
                            collisionType = Collision.PARTICLE;
                        }
                    }
                }
            }
            calculateNewPositions(tc);
            calculateNewVelocity(pi, pj, collisionType);
            t += tc;
            printState(t);
            outputToFile(t, writer);
            collisionCant++;
        }
        writer.close();
        final long endTime = System.currentTimeMillis();
//        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
//        System.out.println("Total collisions: " + collisionCant);
//        System.out.println("Collision frequency: " + (double)(collisionCant)/T);
    }

    private static void printState(double time) {
        System.out.println(N + 1);
        System.out.println(time);
        for (Particle p : particles){
            System.out.println(p.getX() + "\t" + p.getY() + "\t" + p.getVx() + "\t" + p.getVy() + "\t" + p.getRadius());
        }
    }


    private static void outputToFile(double time, PrintWriter writer) {
        writer.println(N + 1);
        writer.println(time);
        for (Particle p : particles) {
            writer.println(p.getId() + " " + p.getX() + " " + p.getY() + " " + p.getVx() + " "
                            + p.getVy() + " " + p.getRadius());
        }
    }
}
