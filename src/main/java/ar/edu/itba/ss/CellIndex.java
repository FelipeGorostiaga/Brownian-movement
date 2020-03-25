package ar.edu.itba.ss;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static ar.edu.itba.ss.App.M;
import static ar.edu.itba.ss.CommandParser.RC;
import static ar.edu.itba.ss.CommandParser.periodicContour;
import static ar.edu.itba.ss.FileParser.L;
import static ar.edu.itba.ss.FileParser.particles;

class CellIndex {

    static List<List<Particle>> cells;

    static void cellIndexAlgorithm() {
        for(Particle p: particles) {
            p.setNeighbours(new TreeSet<Particle>());
        }
        for (List<Particle> cell : cells) {
            for (Particle p : cell) {
                double cellX = Math.floor(p.getX() / (L / M));
                double cellY = Math.floor(p.getY() / (L / M));
                checkNeighbourCells(p, cellX, cellY);
                checkNeighbourCells(p, cellX, cellY + 1);
                checkNeighbourCells(p, cellX + 1, cellY + 1);
                checkNeighbourCells(p, cellX + 1, cellY);
                checkNeighbourCells(p, cellX + 1, cellY - 1);
            }
        }
    }

    private static void checkNeighbourCells(Particle p, double cellX, double cellY) {
        if(periodicContour) {
            if(cellX >= M) cellX = 0;
            if(cellY >= M) cellY = 0;
            if(cellX == -1) cellX = M - 1;
            if(cellY == -1) cellY = M - 1;
        }
        else {
            if(cellX < 0 || cellY < 0 || cellX >= M || cellY >= M) return;
        }
        int currentCell = (int) (cellY * M + cellX);
        List<Particle> particlesInCurrentCell = cells.get(currentCell);
        for(Particle neighbourCellParticle: particlesInCurrentCell) {
            if(!neighbourCellParticle.equals(p)) {
                double distance;
                if(periodicContour) {
                    distance = p.calculatePeriodicDistance(neighbourCellParticle);
                }
                else {
                    distance = p.calculateDistance(neighbourCellParticle);
                }
                if(distance < RC) {
                    p.addNeighbour(neighbourCellParticle);
                    neighbourCellParticle.addNeighbour(p);
                }
            }else {
                // adds itself as neighbour
                p.addNeighbour(p);
            }
        }
    }

    static void populateCells() {
        cells = new ArrayList<>();
        for(int i = 0; i < (M * M) ; i++){
            cells.add(new ArrayList<Particle>());
        }
        for(Particle p : particles) {
            double cellX = Math.floor(p.getX() / (L / M));
            double cellY = Math.floor(p.getY() / (L / M));
            int cellNumber = (int) (cellY * M + cellX);
            List <Particle> cell = cells.get(cellNumber);
            cell.add(p);
        }
    }
    

}
