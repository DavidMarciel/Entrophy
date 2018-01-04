package com.example.david.ent2.Movements;

/**
 * Created by david on 20/06/2014.
 */
public class VerticalBlockBounceMove extends Move {

    private static boolean goToBottom = true;

    private static final float SPEED = 6 /3.5f;

    public VerticalBlockBounceMove() {
        super();
    }

    @Override
    protected void concreteInitialization() {

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

        if( y < Y_MIN_SCREEN_SIZE ) goToBottom = true;
        else if(Y_MAX_SCREEN_SIZE < y) goToBottom = false;

        if(goToBottom){
            y += SPEED;
        }
        else{
            y -= SPEED;
        }

        cr.setY(y);
    }

}
