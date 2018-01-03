package com.example.david.ent2.Movements;

/**
 * Created by david on 19/06/2014.
 */
public class StaticMove extends Move {


    public StaticMove() {
        super();
    }

    @Override
    public int getMovementType() {
        return STATIC_MOVE;
    }

    @Override
    public String getInfo() {

        String s = super.getInfo();
        s+= "\nMove Estatico(no se move), ";

        return s;
    }

    @Override
    protected void concreteInitialization() {

    }

    @Override
    public void move() {

    }

}
