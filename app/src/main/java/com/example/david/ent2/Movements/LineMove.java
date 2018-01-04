package com.example.david.ent2.Movements;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LineMove extends Move {

    private float ySpeed;
    private float xSpeed;

    private final float VELOCIDAD_MAXIMA = 6 /3.5f; //inicialmente 6

    @Override
    public int getMovementType() {
        return LINE_MOVE;
    }

    @Override
    protected void concreteInitialization() {
        inicializaVelocidades();
    }

    public LineMove() {
        super();

    }

    private void inicializaVelocidades() {

        ySpeed = (float) ((Math.random()* VELOCIDAD_MAXIMA) - VELOCIDAD_MAXIMA/2);
        xSpeed = (float) ((Math.random()* VELOCIDAD_MAXIMA) - VELOCIDAD_MAXIMA/2);

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
        s+= "\nvalores de avance ySpeed/xSpeed : "+ ySpeed +","+ xSpeed;
        s+= "\nvelocidad máxima: "+ VELOCIDAD_MAXIMA;

        return s;
    }

    private void verticalMove() {
        if( Y_MIN_SCREEN_SIZE <= y && y <= Y_MAX_SCREEN_SIZE) y += ySpeed;
        else if( y < Y_MIN_SCREEN_SIZE) y = Y_MAX_SCREEN_SIZE;
        else if(Y_MAX_SCREEN_SIZE < y) y = Y_MIN_SCREEN_SIZE;
        cr.setY(y);
    }

    private void horizontalMove() {
        if( X_MIN_SCREEN_SIZE <= x && x <= X_MAX_SCREEN_SIZE) x += xSpeed;
        else if( x < X_MIN_SCREEN_SIZE) x = X_MAX_SCREEN_SIZE;
        else if(X_MAX_SCREEN_SIZE < x) x = X_MIN_SCREEN_SIZE;
        cr.setX(x);
    }


}
