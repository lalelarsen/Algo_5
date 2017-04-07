/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo_studypointexercise_5_swarm;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Frederik
 */
public class Algo_StudypointExercise_5_Swarm {

    //constants
    static int numberOfUnits = 5;
    double WUpper = 1.;
    double WLower = 0;
    int iterations = 100;
    double learningC1 = 2.0;
    double learningC2 = 2.0;
    
    public void run(){
        ArrayList<Unit> units = new ArrayList();
        Random r = new Random();
        double rangeMin = -2;
        double rangeMax = 2;
        
        double globalBest = 0;
        double globalX = Double.MAX_VALUE;
        double globalY = Double.MAX_VALUE;
        
        //initalize units
        for (int i = 0; i < numberOfUnits; i++) {
            double x = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double y = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double velX = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double velY = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            
            Unit u = new Unit(x, y, velX, velY);
            
            units.add(u);
        }
        
      
        double[] bestStats = new double[numberOfUnits];
        double[] valueStats = new double[numberOfUnits];
        double[][] bestLocation = new double[2][numberOfUnits];
        
        for (int i = 0; i < numberOfUnits; i++) {
            Unit u = units.get(i);
            valueStats[i] = u.findValue();
            bestStats[i] = valueStats[i];
            bestLocation[0][i] = u.getX();
            bestLocation[1][i] = u.getY();
        }
        
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < numberOfUnits; j++) {
                Unit u = units.get(i);
                
                double value = u.findValue();
                if(valueStats[j] < bestStats[j]){
                    bestStats[j] = valueStats[j];
                    bestLocation[0][j] = u.getX();
                    bestLocation[1][j] = u.getY();
                }
                
                int bestValue = bestUnit(valueStats);
                
                if(globalBest == 0 || valueStats[bestValue] < globalBest){
                    globalBest = valueStats[bestValue];
                    globalX = bestLocation[0][bestValue];
                    globalY = bestLocation[1][bestValue];
                }
                
                double w = WUpper - (((double)iterations) / iterations) * (WUpper - WLower);
                
                for (int k = 0; k < numberOfUnits; k++) {
                    double A = r.nextDouble();
                    double B = r.nextDouble();
                    Unit un = units.get(k);
                    
                    double velX = (w * un.getVelocityX()) + (A * learningC1) * (bestLocation[i][0]-un.getX()) + (B * learningC2) * (globalBest - un.getX());
                    double velY = (w * un.getVelocityY()) + (A * learningC1) * (bestLocation[i][1]-un.getY()) + (B * learningC2) * (globalBest - un.getY());
                    
                    units.get(k).setVelocityX(velX);
                    units.get(k).setVelocityY(velY);
                    
                }
                
                for (int k = 0; k < numberOfUnits; k++) {
                    Unit un = units.get(k);
                    valueStats[k] = un.findValue();
                    
                }
                
                double worst = valueStats[worstUnit(valueStats)];
                double best = valueStats[bestUnit(valueStats)];
                
                System.out.println("worst: " + worst + "        best: " + best);
                
            }
        } 
    }
    public static void main(String[] args) {
        Algo_StudypointExercise_5_Swarm a = new Algo_StudypointExercise_5_Swarm();
        a.run();
    }
    
    public static int bestUnit(double[] values){
        int pos = 0;
        for (int i = 0; i < numberOfUnits; i++) {
            if(values[i] < values[pos]){
                pos = i;
            }
        }
        return pos;
    }
    
    public static int worstUnit(double[] values){
        int pos = 0;
        for (int i = 0; i < numberOfUnits; i++) {
            if(values[i] > values[pos]){
                pos = i;
            }
        }
        return pos;
    }
}
