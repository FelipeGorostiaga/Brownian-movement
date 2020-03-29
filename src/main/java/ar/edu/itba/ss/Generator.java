package ar.edu.itba.ss;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.itba.ss.CommandParser.N;
import static ar.edu.itba.ss.CommandParser.TEMPERATURE;

class Generator {

    static List<Particle> particles = new ArrayList<>();
    static double L = 0.5;
    static double R1 = 0.005;
    static double R2 = 0.05;
    static double M1 = 0.1;
    static double M2 = 100;
    static double V1 = 0.1;

    static void generateParticles() {
        particles.add(new Particle(1, L/2, L/2, 0.0, 0.0, M2, R2));
        for(int i = 0 ; i < N ; i++) {
            double x, y;
            double vx, vy;
            do {
                x = R1 + (L - 2 * R1) * Math.random();
                y = R1 + (L - 2 * R1) * Math.random();
            } while (!isValidPosition(x, y));

            if(TEMPERATURE != 0) {
                double averageVelocity = TEMPERATURE / N;
                vx = averageVelocity / Math.sqrt(2);
                vy = averageVelocity / Math.sqrt(2);
                if(Math.random() < 0.5) vx *= -1;
                if(Math.random() < 0.5) vy *= -1;
            }
            else {
                vx = 2 * V1 * Math.random() - V1;
                vy = 2 * V1 * Math.random() - V1;
            }
            particles.add(new Particle(i + 2, x, y, vx, vy, M1, R1));
        }
    }


    private static boolean isValidPosition(double x, double y) {
        for (Particle p: particles){
            if(!((Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2)) > Math.pow(p.getRadius() + R1, 2))) {
                return false;
            }
        }
        return true;
    }

}
