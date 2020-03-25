package ar.edu.itba.ss;

import static ar.edu.itba.ss.Generator.L;
import static ar.edu.itba.ss.Generator.particles;

class BrownianMotion {

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
            return  (0 + p.getRadius() - p.getY())  / p.getVy();
        }
        return Double.POSITIVE_INFINITY;
    }

    static double calculateParticleCollisionTime(Particle p1, Particle p2) {
        double sigma = p1.getRadius() + p2.getRadius();
        double DX = p1.getX() - p2.getX();
        double DY = p1.getY() - p2.getY();
        double DVX = p1.getVx() - p2.getVx();
        double DVY = p1.getVy() - p2.getVy();
        double RR = Math.pow(DX, 2) + Math.pow(DY, 2);
        double VV = Math.pow(DVX, 2) + Math.pow(DVY, 2);
        double VR = DVX * DX + DVY * DY;
        double d = Math.pow(VR, 2) - VV * RR - Math.pow(sigma, 2);
        if(VR >= 0 || d < 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (-1) * ((VR + Math.sqrt(d)) / VV );
    }

    static void calculateNewPositions(double time) {
        for(Particle p: particles) {
            p.setX(p.getY() + p.getVx() * time);
            p.setY(p.getY() + p.getVy() * time);
        }
    }

    static void calculateNewVelocity(Particle p1, Particle p2, Collision collisionType) {

        switch(collisionType) {
            case VERTICAL_WALL:
                break;
            case HORIZONTAL_WALL:
                break;
            case PARTICLE:
                break;
        }


    }


}
