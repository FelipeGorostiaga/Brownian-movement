package ar.edu.itba.ss;

import static ar.edu.itba.ss.Generator.L;
import static ar.edu.itba.ss.Generator.particles;

public class BrownianMotion {

    // calculate new positions

    // calculate new velocities

    // calculate tc

        // calculate vertical walls tc

        // calculate horizontal walls tc

        // calculate particle collision tc



    static double calculateVerticalCollisionTime(Particle p) {

        if (p.getVx() > 0) {
            return (L - p.getRadius() - p.getX()) / p.getVx();
        }
        else if (p.getVx() < 0) {
            return (0 + p.getRadius() - p.getX()) / p.getVx();
        }
        return Double.POSITIVE_INFINITY;
    }

    static double calculateHorizontalCollisionTime(Particle p) {
        if (p.getVy() > 0){
            return  (L - p.getRadius() - p.getY()) / p.getVy();
        }
        else if (p.getVy() < 0){
            return  (p.getRadius() - p.getY())  / p.getVy();
        }
        return Double.POSITIVE_INFINITY;
    }

    static void calculateNewPositions(double time) {
        for(Particle p: particles) {
            p.setX(p.getY() + p.getVx() * time);
            p.setY(p.getY() + p.getVy() * time);
        }
    }


}
