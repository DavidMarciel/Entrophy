package com.example.david.ent2.Handlers;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Movements.Move;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HandlerLaser extends Handler {

//    final float MAXIMUM_SPEED = 7; //anteriormente 6 //comodo en 3

    public HandlerLaser(Character[] letras) {
        super(letras);
        concreteInitialization();
    }

    @Override
    public int getType() {
        return LASER_HANDLER;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds(MAXIMUM_SPEED);
        initPoints();

        //añades el movimiento unicamente en la ultima letra, el resto la seguiran
        letters[letters.length-1].setMove(Move.LASER_MOVE);
    }

    /**Mueve todas las letters, se mueven de forma que unas siguen xSpeed otras,
     * excepto la primera, que utiliza un movimiento
     *
     */
    @Override
    public void move() {
        for (int i = 0; i < letters.length-1 ; i++) {

            int ir_a = (i*(STEPS +1)+1);

            letters[i].setX(points.get(ir_a).getX());
            letters[i].setY(points.get(ir_a).getY());

        }
        mueveUltimaLetra(letters[letters.length-1]);
    }

    /**Mueve unicamente la primera letra, el resto la seguirán
     * aunque sea la primera letra de la fila (la guia) es la ultima en la lista
     *
     * @param letra letra xSpeed la que seguirán el resto (última en la lista)
     */
    private void mueveUltimaLetra(Character letra) {

        letra.move();
        points.add(new Point(letra.getX(), letra.getY()));
        points.remove(0);


    }

}