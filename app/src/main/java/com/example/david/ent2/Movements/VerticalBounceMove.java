package com.example.david.ent2.Movements;

/**
 * Created by david on 20/06/2014.
 */
public class VerticalBounceMove extends Move {

    boolean goingBottom = false;

    final float SPEED = 5 /3.5f;

    public VerticalBounceMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return VERTICAL_BOUNCE_MOVE;
    }

    @Override
    public void move() {
        bounceMove();
    }

    @Override
    protected void concreteInitialization() {

    }

    @Override
    public String getInfo() {

        String s = super.getInfo();
        s+= "\nMove de Rebote Vertical, ";
        s+= "\nvelocidad: "+ SPEED;

        return s;
    }

    private void bounceMove() {

        if(goingBottom){
            y += SPEED;
        }
        else{
            y -= SPEED;
        }
        if( y < Y_MIN_SCREEN_SIZE) goingBottom = true;
        else if(Y_MAX_SCREEN_SIZE < y) goingBottom = false;

        cr.setY(y);

    }

}
