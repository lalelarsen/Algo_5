/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo_studypointexercise_5_swarm;

/**
 *
 * @author Frederik
 */
public class UnitV2 {

    double x;
    double y;
    double velocityX;
    double velocityY;
    double u;
    double w;
    double velocityU;
    double velocityW;
    
    public double findValue() {
        return 2 * x * Math.exp(-x*x-y*y-(u-1)*(u-1) - w * w);
    }

    public UnitV2(double x, double y, double velocityX, double velocityY, double u, double w, double velocityU, double velocityW) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.u = u;
        this.w = w;
        this.velocityU = velocityU;
        this.velocityW = velocityW;
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

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getVelocityU() {
        return velocityU;
    }

    public void setVelocityU(double velocityU) {
        this.velocityU = velocityU;
    }

    public double getVelocityW() {
        return velocityW;
    }

    public void setVelocityW(double velocityW) {
        this.velocityW = velocityW;
    }
    
    
}
