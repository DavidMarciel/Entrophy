package com.example.david.ent2.Handlers;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Movements.Move;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SnakeHandler extends Handler {


    public SnakeHandler(Character[] letters) {
        super(letters);
        concreteInitialization();
    }

    @Override
    public int getType() {
        return SNAKE_HANDLER;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds(MAXIMUM_SPEED);
        initPoints();

        //añades el movimiento unicamente en la ultima letra, el resto la seguiran
        letters[letters.length-1].setMove(Move.SNAKE_MOVE);
    }

    /**Mueve todas las letters, se mueven de forma que unas siguen xSpeed otras,
     * excepto la primera, que utiliza un movimiento
     *
     */
    @Override
    public void move() {
        for (int i = 0; i < letters.length-1 ; i++) {

            int goTo = (i*(STEPS +1)+1);

            letters[i].setX(points.get(goTo).getX());
            letters[i].setY(points.get(goTo).getY());

        }
        moveLastLetter(letters[letters.length-1]);
    }

    /**Mueve unicamente la primera letra, el resto la seguirán
     * aunque sea la primera letra de la fila (la guia) es la ultima en la lista
     *
     * @param letter letra xSpeed la que seguirán el resto (última en la lista)
     */
    private void moveLastLetter(Character letter) {

        letter.move();
        points.add(new Point(letter.getX(), letter.getY()));
        points.remove(0);

    }

}