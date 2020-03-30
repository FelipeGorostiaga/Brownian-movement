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
        double dX = p2.getX() - p1.getX();
        double dY = p2.getY() - p1.getY();
        double dVx = p2.getVx() - p1.getVx();
        double dVy = p2.getVy() - p1.getVy();

        double dVdR = dVx*dX + dVy*dY;

        if (Double.compare(dVdR, 0) >= 0){
            return Double.POSITIVE_INFINITY;
        }

        double dVdV = Math.pow(dVx, 2) + Math.pow(dVy, 2);

        double dRdR = Math.pow(dX, 2) + Math.pow(dY, 2);

        double sigma = p1.getRadius() + p2.getRadius();

        double d = Math.pow(dVdR, 2) - dVdV * (dRdR - Math.pow(sigma ,2));

        if (Double.compare(d, 0) < 0){
            return Double.POSITIVE_INFINITY;
        }
        return ((-1) * ((dVdR + Math.sqrt(d)) / dVdV));
    }

    static void calculateNewPositions(double time) {
        for(Particle p: particles) {
            p.setX(p.getX() + p.getVx() * time);
            p.setY(p.getY() + p.getVy() * time);
        }
    }

    static void calculateNewVelocity(Particle p1, Particle p2, Collision collisionType) {

        if(collisionType.equals(Collision.PARTICLE)) {
            System.out.println("Collision between particle " + p1.getId() + " and " + p2.getId());
        }
        else {
            System.out.println("Collision against wall " + p1.getId());
        }

        switch(collisionType) {
            case VERTICAL_WALL:
                p1.setVx(-1 * p1.getVx());
                break;
            case HORIZONTAL_WALL:
                p1.setVy(-1 * p1.getVy());
                break;
            case PARTICLE:
                double dX = p2.getX() - p1.getX();
                double dY = p2.getY() - p1.getY();
                double dVx = p2.getVx() - p1.getVx();
                double dVy = p2.getVy() - p1.getVy();

                double dVdR = dVx*dX + dVy*dY;
                double sigma = p1.getRadius() + p2.getRadius();

                double J = (2*p1.getMass()*p2.getMass()*dVdR) / (sigma * (p1.getMass() + p2.getMass()));
                double Jx = J * dX / sigma;
                double Jy = J * dY / sigma;

                p1.setVx(p1.getVx() + Jx/p1.getMass());
                p1.setVy(p1.getVy() + Jy/p1.getMass());

                p2.setVx(p2.getVx() - Jx/p2.getMass());
                p2.setVy(p2.getVy() - Jy/p2.getMass());
                break;
        }
    }

}
