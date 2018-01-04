package com.example.david.ent2.Movements;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LineBounceMove extends Move {

    private float xSpeed;
    private float ySpeed;

    private final float VELOCIDAD_MAXIMA = 3 /3.5f;

    public LineBounceMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return LINE_BOUNCE_MOVE;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds();
        putInRange();
    }

    private void putInRange() {
        if(x < X_MIN_SCREEN_SIZE) x = X_MIN_SCREEN_SIZE;
        if(x > X_MAX_SCREEN_SIZE) x = X_MAX_SCREEN_SIZE;
        if(y < Y_MIN_SCREEN_SIZE) y = Y_MIN_SCREEN_SIZE;
        if(y > Y_MAX_SCREEN_SIZE) y = Y_MAX_SCREEN_SIZE;
    }


    private void initSpeeds() {
        xSpeed = (float) ((Math.random()* VELOCIDAD_MAXIMA) - VELOCIDAD_MAXIMA/2);
        ySpeed = (float) ((Math.random()* VELOCIDAD_MAXIMA) - VELOCIDAD_MAXIMA/2);
    }

    @Override
    public void move() {
        horizontalMove();
        verticalMove();
    }

    @Override
    public String getInfo() {
        String s = super.getInfo();
        s+= "\nMove en Linea Recta, ";
        s+= "\nvalores de avance xSpeed/ySpeed : "+ xSpeed +","+ ySpeed;
        s+= "\nvelocidad máxima: "+ VELOCIDAD_MAXIMA;
        return s;
    }

    private void verticalMove() {

        if( y < Y_MIN_SCREEN_SIZE || Y_MAX_SCREEN_SIZE < y   ) {
            ySpeed = -ySpeed;
        }
        cr.setY( y += ySpeed);
    }

    private void horizontalMove() {

        if( x < X_MIN_SCREEN_SIZE || X_MAX_SCREEN_SIZE < x)
            xSpeed = -xSpeed;

        cr.setX( x += xSpeed);
    }


}
