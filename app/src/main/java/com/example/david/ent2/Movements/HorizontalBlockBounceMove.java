package com.example.david.ent2.Movements;

/**
 * Created by david on 20/06/2014.
 */
public class HorizontalBlockBounceMove extends Move {

    private static boolean startToTheRight = true;

    private static final float SPEED = 5 /3.5f;

    public HorizontalBlockBounceMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return HORIZONTAL_BLOCK_BOUNCE_MOVE;
    }

    @Override
    protected void concreteInitialization() {

    }

    @Override
    public void move() {

        if( x < X_MIN_SCREEN_SIZE ) startToTheRight = true;
        else if(X_MAX_SCREEN_SIZE < x) startToTheRight = false;

        if(startToTheRight){
            x += SPEED;
        }
        else{
            x -= SPEED;
        }

        cr.setX(x);
    }

    @Override
    public String getInfo() {

        String s = super.getInfo();
        s+= "\nMove de Bloque Rebote Horizontal, ";
        s+= "\nvelocidad: "+ SPEED;

        return s;

    }

}
