package com.example.david.ent2.Movements;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LateralWindMove extends Move {

    private float speed;

    final float MAXIMUM_SPEED = 8 /3.5f;    //17
    final float MINIMUM_SPEED = 3 /3.5f;     //5

    public LateralWindMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return LATERAL_WIND_MOVE;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds();
    }

    private void initSpeeds() {

        speed = (float) ((Math.random()* MAXIMUM_SPEED) )+ MINIMUM_SPEED;

    }

    @Override
    public void move() {
        //speed debe ser un random con valor max 70
        //es muy chulo

        if( x < X_MAX_SCREEN_SIZE) x += speed;
        else x %= MAXIMUM_SPEED;

        cr.setX(x);
    }

    @Override
    public String getInfo() {

        String s = super.getInfo();
        s+= "\nMove Matrix, ";
        s+= "\nvalores de avance speed : "+ speed;
        s+= "\nvelocidad máxima: "+ MAXIMUM_SPEED;

        return s;
    }

}
