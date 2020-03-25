package ar.edu.itba.ss;

import java.util.Objects;
import java.util.Set;

import static ar.edu.itba.ss.FileParser.L;


public class Particle {

    private int id;
    private double x;
    private double y;
    private double mass;
    private double radius;
    private double vx;
    private double vy;
    private Set<Particle> neighbours;

    Particle(int id, double x, double y, double vx, double vy, double mass, double radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.radius = radius;
        this.vx = vx;
        this.vy = vy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    Set<Particle> getNeighbours() {
        return neighbours;
    }

    void setNeighbours(Set<Particle> neighbours) {
        this.neighbours = neighbours;
    }

    void addNeighbour(Particle neighbour){
        this.neighbours.add(neighbour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Particle { id = " + id +
                ", x = " + x +
                ", y = " + y +
                ", vx = " + vx +
                ", vy = " + vy +
                ", mass = " + mass +
                ", radius = " + radius +
                '}';
    }

    double calculateDistance(Particle particle){
        return Math.sqrt(Math.pow(x - particle.getX(), 2) + Math.pow(y - particle.getY(), 2));
    }

    double calculatePeriodicDistance(Particle particle) {
        double xDistance = Math.abs(this.x - particle.x);
        if (xDistance > (L / 2)) {
            xDistance = L - xDistance;
        }
        double yDistance = Math.abs(this.y - particle.y);
        if (yDistance > (L / 2)) {
            yDistance = L - yDistance;
        }
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

}
