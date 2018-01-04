package com.example.david.ent2.Movements;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LaserMove extends Move {

    private float xSpeed;
    private float ySpeed;

    private static final int STEPS = 20;
    private final float MAXIMUM_SPEED = STEPS /7+1;

    @Override
    public int getMovementType() {
        return LASER_MOVE;
    }

    @Override
    protected void concreteInitialization() {

        initSpeeds(MAXIMUM_SPEED);
        startInRange();
    }

    private void initSpeeds(double maximumSpeed) {

        double xExtraSpeed = (Math.random()* maximumSpeed);
        double yExtraSpeed = Math.sqrt( maximumSpeed*maximumSpeed - xExtraSpeed*xExtraSpeed);


        if(Math.random()>0.5d)
            xSpeed = (float) xExtraSpeed;
        else xSpeed = (float) -xExtraSpeed;

        if(Math.random()>0.5d)
            ySpeed = (float) yExtraSpeed;
        else ySpeed = (float) -yExtraSpeed;
    }

    private void startInRange() {
        if(x < X_MIN_SCREEN_SIZE) x = X_MIN_SCREEN_SIZE;
        if(x > X_MAX_SCREEN_SIZE) x = X_MAX_SCREEN_SIZE;
        if(y < Y_MIN_SCREEN_SIZE) y = Y_MIN_SCREEN_SIZE;
        if(y > Y_MAX_SCREEN_SIZE) y = Y_MAX_SCREEN_SIZE;
    }

    public LaserMove() {
        super();
    }

    private void initSpeeds() {
        xSpeed = (float) ((Math.random()* MAXIMUM_SPEED) - MAXIMUM_SPEED /2);
        ySpeed = (float) ((Math.random()* MAXIMUM_SPEED) - MAXIMUM_SPEED /2);
    }

    @Override
    public void move() {

        verticalMove();
        horizontalMove();
    }

    @Override
    public String getInfo() {
        String s = super.getInfo();
        s+= "\nMove en Linea Recta, ";
        s+= "\nvalores de avance xSpeed/ySpeed : "+ xSpeed +","+ ySpeed;
        s+= "\nvelocidad máxima: "+ MAXIMUM_SPEED;
        return s;
    }

    private void verticalMove() {

        if( cr.getY() < Y_MIN_SCREEN_SIZE || Y_MAX_SCREEN_SIZE < cr.getY()   ) {
            ySpeed = -ySpeed;
        }
        cr.setY(y+= ySpeed);
    }

    private void horizontalMove() {

        if( cr.getX() < X_MIN_SCREEN_SIZE || X_MAX_SCREEN_SIZE < cr.getX()) {
            xSpeed = -xSpeed;
        }
        cr.setX(x+= xSpeed);
    }

}
