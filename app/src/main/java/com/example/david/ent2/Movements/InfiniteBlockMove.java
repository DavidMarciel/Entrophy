package com.example.david.ent2.Movements;

/**
 * Created by david on 17/03/2015.
 */
public class InfiniteBlockMove extends Move {

    static private float ySpeed;
    static private float xSpeed;

    private final float MAXIMUM_SPEED = 6 /3.5f; //inicialmente 6

    public InfiniteBlockMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return INFINITE_BLOCK_MOVE;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds();
    }


    private void initSpeeds() {

        ySpeed = (float) ((Math.random()* MAXIMUM_SPEED) - MAXIMUM_SPEED /2);
        xSpeed = (float) ((Math.random()* MAXIMUM_SPEED) - MAXIMUM_SPEED /2);

    }

    @Override
    public void move() {
        horizontalMove();
        verticalMove();
    }


    @Override
    public String getInfo() {

        String s = super.getInfo();
        s+= "\nMove en bloque infinito, ";
        s+= "\nvalores de avance ySpeed/xSpeed : "+ ySpeed +","+ xSpeed;
        s+= "\nvelocidad máxima: "+ MAXIMUM_SPEED;

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
