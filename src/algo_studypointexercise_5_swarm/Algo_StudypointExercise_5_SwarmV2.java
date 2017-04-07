/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo_studypointexercise_5_swarm;

import static algo_studypointexercise_5_swarm.Algo_StudypointExercise_5_Swarm.bestUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Frederik
 */
public class Algo_StudypointExercise_5_SwarmV2 {

    static int numberOfUnits;
    int iterations;

    public Algo_StudypointExercise_5_SwarmV2(int units, int iterations) {
        numberOfUnits = units;
        this.iterations = iterations;
    }

    //constants
    double WUpper = 1.;
    double WLower = 0;
    double learningC1 = 2.0;
    double learningC2 = 2.0;

    public void run() {
        ArrayList<UnitV2> units = new ArrayList();
        Random r = new Random();
        double rangeMin = -2;
        double rangeMax = 2;

        double globalBest = 0;
        double globalX = Double.MAX_VALUE;
        double globalY = Double.MAX_VALUE;
        double globalU = Double.MAX_VALUE;
        double globalW = Double.MAX_VALUE;

        //initalize units
        for (int i = 0; i < numberOfUnits; i++) {
            double x = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double y = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double u = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double w = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            
            double velX = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double velY = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double velU = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double velW = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            
            UnitV2 un = new UnitV2(x, y, velX, velY,u,w,velU,velW);

            units.add(un);
        }

        double[] bestStats = new double[numberOfUnits];
        double[] valueStats = new double[numberOfUnits];
        double[][] bestLocation = new double[4][numberOfUnits];

        for (int i = 0; i < numberOfUnits; i++) {
            UnitV2 u = units.get(i);
            valueStats[i] = u.findValue();
            bestStats[i] = valueStats[i];
            bestLocation[0][i] = u.getX();
            bestLocation[1][i] = u.getY();
            bestLocation[2][i] = u.getU();
            bestLocation[3][i] = u.getW();
        }

        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < numberOfUnits; j++) {
                UnitV2 u = units.get(j);

                double value = u.findValue();
                if (valueStats[j] < bestStats[j]) {
                    bestStats[j] = valueStats[j];
                    bestLocation[0][j] = u.getX();
                    bestLocation[1][j] = u.getY();
                    bestLocation[2][j] = u.getU();
                    bestLocation[3][j] = u.getW();
                }

                int bestValue = bestUnit(valueStats);

                if (globalBest == 0 || valueStats[bestValue] < globalBest) {
                    globalBest = valueStats[bestValue];
                    globalX = bestLocation[0][bestValue];
                    globalY = bestLocation[1][bestValue];
                    globalU = bestLocation[2][bestValue];
                    globalW = bestLocation[3][bestValue];
                }

                double w = WUpper - (((double) iterations) / iterations) * (WUpper - WLower);

                for (int k = 0; k < numberOfUnits; k++) {
                    double A = r.nextDouble();
                    double B = r.nextDouble();
                    UnitV2 un = units.get(k);

                    double velX = (w * un.getVelocityX()) + (A * learningC1) * (bestLocation[0][k] - un.getX()) + (B * learningC2) * (globalBest - un.getX());
                    double velY = (w * un.getVelocityY()) + (A * learningC1) * (bestLocation[1][k] - un.getY()) + (B * learningC2) * (globalBest - un.getY());
                    double velU = (w * un.getVelocityU()) + (A * learningC1) * (bestLocation[2][k] - un.getU()) + (B * learningC2) * (globalBest - un.getU());
                    double velW = (w * un.getVelocityW()) + (A * learningC1) * (bestLocation[3][k] - un.getW()) + (B * learningC2) * (globalBest - un.getW());

                    units.get(k).setVelocityX(velX);
                    units.get(k).setVelocityY(velY);
                    units.get(k).setVelocityU(velU);
                    units.get(k).setVelocityW(velW);

                    units.get(k).x += units.get(k).velocityX;
                    units.get(k).y += units.get(k).velocityY;
                    units.get(k).u += units.get(k).velocityU;
                    units.get(k).w += units.get(k).velocityW;
                }

                for (int k = 0; k < numberOfUnits; k++) {
                    UnitV2 un = units.get(k);
                    valueStats[k] = un.findValue();

                }

            }
        }
        
        double best = valueStats[bestUnit(valueStats)];
        double bestX = bestLocation[0][bestUnit(valueStats)];
        double bestY = bestLocation[1][bestUnit(valueStats)];
        double bestU = bestLocation[2][bestUnit(valueStats)];
        double bestW = bestLocation[3][bestUnit(valueStats)];
        
        System.out.println("cords: x: " + bestX + "  y: " + bestY);
        System.out.println("u: " + bestU + "  w: " + bestW);
        System.out.println("value: " + best);
    }

    public static void main(String[] args) {
        Algo_StudypointExercise_5_SwarmV2 a = new Algo_StudypointExercise_5_SwarmV2(5, 100);
        Algo_StudypointExercise_5_SwarmV2 b = new Algo_StudypointExercise_5_SwarmV2(10, 100);
        Algo_StudypointExercise_5_SwarmV2 c = new Algo_StudypointExercise_5_SwarmV2(20, 100);

        System.out.println("Opgave B");
        
        System.out.println("-----5 units----100 iterations---");
        a.run();

        System.out.println("-----10 units----100 iterations---");
        b.run();

        System.out.println("-----20 units----100 iterations---");
        c.run();
    }

    public static int bestUnit(double[] values) {
        int pos = 0;
        for (int i = 0; i < numberOfUnits; i++) {
            if (values[i] < values[pos]) {
                pos = i;
            }
        }
        return pos;
    }

}
