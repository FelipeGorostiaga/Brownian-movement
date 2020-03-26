package ar.edu.itba.ss;

import static ar.edu.itba.ss.Generator.L;
import static ar.edu.itba.ss.Generator.particles;

class BrownianMotion {

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
                p1.setVx(-1 * p1.getVx());
                break;
            case HORIZONTAL_WALL:
                p1.setVy(-1 * p1.getVy());
                break;
            case PARTICLE:
                double sigma = p1.getRadius() + p2.getRadius();
                double DX = p1.getX() - p2.getX();
                double DY = p1.getY() - p2.getY();
                double DVX = p1.getVx() - p2.getVx();
                double DVY = p1.getVy() - p2.getVy();
                double VR = DVX * DX + DVY * DY;
                double J = (2 * p1.getMass() * p2.getMass() * VR) / (sigma * (p1.getMass() + p2.getMass()));
                double JX = (J * DX) / sigma;
                double JY = (J * DY) / sigma;
                // p1
                p1.setVx(p1.getVx() + JX / p1.getMass());
                p1.setVy(p1.getY() + JY / p1.getMass());
                // p2
                p2.setVx(p2.getVx() - JX / p2.getMass());
                p2.setVy(p2.getY() - JY / p2.getMass());
                break;
        }
    }

}
