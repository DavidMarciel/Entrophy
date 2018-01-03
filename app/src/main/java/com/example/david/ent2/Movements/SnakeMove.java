package com.example.david.ent2.Movements;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SnakeMove extends Move {

    private int time = 0;
    private float xSpeed;
    private float ySpeed;

    protected static final int STEPS = 20;
    protected static final int MAXIMUM_SPEED = STEPS /7+1;

    private static final float ANGULO_MAX_GIRO = 30;

    public SnakeMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return SNAKE_MOVE;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds(MAXIMUM_SPEED);
    }

    @Override
    public void move() {

        if (time % (57 * STEPS / 7) == 0) {
//        if(time%57==0){
            changeSpeeds();
        }

        verticalMove();
        horizontalMove();
        time++;
    }


    private void changeSpeeds() {

        double extraAngle = (random() * ANGULO_MAX_GIRO);    // (-30,+30)
        if (random() < 0.5d) extraAngle = -extraAngle;

        //datos actuales
        double actualAngle = atan2(ySpeed, xSpeed) * 180 / PI;   //paso el angulo xSpeed grados para trabajar
        double actualModule = sqrt(xSpeed * xSpeed + ySpeed * ySpeed);

        //angulo modificado
        double angle = actualAngle + extraAngle;
        angle = angle * PI / 180;                     //paso el angulo xSpeed radianes para utilizar


        Log.v("Angulo", "Angulo actual: " + actualAngle + " angulo extra: " + extraAngle + " angulo final: " + angle * 180 / PI);
//        Log.v("Angulo", "Valor xSpeed: "+ xSpeed+" valor ySpeed: "+ ySpeed);

        xSpeed = (float) (cos(angle) * actualModule);
        ySpeed = (float) (sin(angle) * actualModule);

//        Log.v("Angulo", "Valor xSpeed: "+ xSpeed+" valor ySpeed: "+ ySpeed);
//        Log.v("Angulo", "inicial cos: "+ cos(anguloActual)+ "sen: "+sin(anguloActual));
//        Log.v("Angulo", "final cos: "+ cos(angulo)+ "sen: "+sin(angulo));

    }

    protected void initSpeeds(double VELOCIDAD_MAXIMA) {

        double xExtraSpeed = (Math.random()* VELOCIDAD_MAXIMA);
        double yExtraSpeed = Math.sqrt( VELOCIDAD_MAXIMA*VELOCIDAD_MAXIMA - xExtraSpeed*xExtraSpeed);


        if(Math.random()>0.5d)
            xSpeed = (float) xExtraSpeed;
        else xSpeed = (float) -xExtraSpeed;

        if(Math.random()>0.5d)
            ySpeed = (float) yExtraSpeed;
        else ySpeed = (float) -yExtraSpeed;
    }

    protected void verticalMove() {

        if( cr.getY() < Y_MIN_SCREEN_SIZE || Y_MAX_SCREEN_SIZE < cr.getY()   ) {
            ySpeed = -ySpeed;
        }
        cr.setY(y+= ySpeed);
    }

    protected void horizontalMove() {

        if( cr.getX() < X_MIN_SCREEN_SIZE || X_MAX_SCREEN_SIZE < cr.getX()) {
            xSpeed = -xSpeed;
        }
        cr.setX(x+= xSpeed);
    }


}
