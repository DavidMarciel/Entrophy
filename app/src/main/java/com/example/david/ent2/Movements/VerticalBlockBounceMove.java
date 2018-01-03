package com.example.david.ent2.Movements;

/**
 * Created by david on 20/06/2014.
 */
public class VerticalBlockBounceMove extends Move {

    static boolean goToBottom = true;

    static final float SPEED = 6 /3.5f;
    private float Y_INITIAL;

    public VerticalBlockBounceMove() {
        super();
    }

    @Override
    protected void concreteInitialization() {
        Y_INITIAL = cr.getY();
    }

    @Override
    public int getMovementType() {
        return VERTICAL_BLOCK_BOUNCE_MOVE;
    }

    @Override
    public String getInfo() {

        String s = super.getInfo();
        s+= "\nMove de Bloque Rebote Horizontal, ";
        s+= "\nvelocidad: "+ SPEED;

        return s;

    }

    @Override
    public void move() {
            bounceMove();
    }

    private void bounceMove() {

        if( y < Y_MIN_SCREEN_SIZE /*+ Y_INITIAL - 150*/ /*-800*/ ) goToBottom = true;
        else if(Y_MAX_SCREEN_SIZE /*+ Y_INITIAL - 700*/ /*-800*/ < y) goToBottom = false;

        if(goToBottom){
            y += SPEED;
        }
        else{
            y -= SPEED;
        }

        cr.setY(y);
    }

}
