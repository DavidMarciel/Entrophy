package com.example.david.ent2.Handlers;


import android.view.MotionEvent;

import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Letters.Character;

import java.util.ArrayList;


/**
 * Created by david on 11/06/2014.
 */
public abstract class Handler {

    protected static final int STEPS = 20; //anteriormente 6 //comodo en 3
    protected static final int MAXIMUM_SPEED = STEPS /7+1; //anteriormente 6 //comodo en 3

    //propiedades de pantalla
    static float Y_MAX_SCREEN_SIZE = Dimens.Y_MAX_SCREEN_SIZE;//1400;
    static float X_MAX_SCREEN_SIZE = Dimens.X_MAX_SCREEN_SIZE;//1050; //900;
    static float Y_MIN_SCREEN_SIZE = Dimens.Y_MIN_SCREEN_SIZE;// 100;
    static float X_MIN_SCREEN_SIZE = Dimens.X_MIN_SCREEN_SIZE;//50;

    //orientacion
    static final boolean ORIENTATION_HORIZONTAL = false;   //landscape --      dimension X
    static final boolean ORIENTATION_VERTICAL = true;      //portrait |        dimension Y

    //propiedades de movimiento
    public static final int STATIC_HANDLER = 0;
    public static final int LASER_HANDLER = 1;
    public static final int SNAKE_HANDLER = 2;
    public static final int INFINITE_BLOCK_HANDLER = 3;

    //atributos
    protected Character[] letters;

    protected float xSpeed;
    protected float ySpeed;
    protected ArrayList<Point> points;

    public abstract int getType();

    protected abstract void concreteInitialization();

    public abstract void move();

    public Handler(Character[] letters){
        this.letters = letters;
    }


    public void tap(MotionEvent event, int tabNumber, ArrayList<Character>[] hittedCharacteres){

        for (Character letter : letters) {

            if (letter.inRange(event)) {

                hittedCharacteres[tabNumber].add(letter);

            }
        }
    }

    public ArrayList<Character> tap(MotionEvent event){

        ArrayList<Character> selected = new ArrayList<>();

        for (Character letter : letters) {

            if (letter.inRange(event)) {

                selected.add(letter);

            }
        }
        return selected;
    }

    public void tapAndSignal(MotionEvent event, int numPulsacion, ArrayList<Character>[] hittedCharacters) {

        for (Character letter : letters) {

            if (letter.inRange(event)) {
                letter.selected();

                hittedCharacters[numPulsacion].add(letter);

            } else letter.notSelected();
        }
    }


    protected void verticalMove(Character c) {

        if( c.getY() < Y_MIN_SCREEN_SIZE || Y_MAX_SCREEN_SIZE < c.getY()   ) {
            ySpeed = -ySpeed;
        }

        c.setY(c.getY() + ySpeed);
    }

    protected void horizontalMove(Character c) {

        if( c.getX() < X_MIN_SCREEN_SIZE || X_MAX_SCREEN_SIZE < c.getX())
            xSpeed = -xSpeed;

        c.setX(c.getX() + xSpeed);
    }

    protected void initSpeeds(double maximumSpeed) {

        double xExtraSpeed = (Math.random()* maximumSpeed);
        double yExtraSpeed = Math.sqrt( maximumSpeed*maximumSpeed - xExtraSpeed*xExtraSpeed);


        if(Math.random()>0.5d)
            xSpeed = (float) xExtraSpeed;
        else xSpeed = (float) -xExtraSpeed;

        if(Math.random()>0.5d)
            ySpeed = (float) yExtraSpeed;
        else ySpeed = (float) -yExtraSpeed;
    }

    protected void initPoints() {

        points = new ArrayList<>();

        for (int i = 0; i < letters.length-1 ; i++) {

            points.add(new Point(letters[i].getX(), letters[i].getY()));

            float x1 = letters[i].getX();
            float y1 = letters[i].getY();

            float x2 = letters[i+1].getX();
            float y2 = letters[i+1].getY();

            float xDistance = x2-x1;
            float yDistance = y2-y1;

            //crea tantos points entre letters (incluye los de las propias letters) como se pida
            for (int j = 0; j < STEPS; j++) {

                points.add(new Point(x1+ xDistance *j/ STEPS, y1+ yDistance *j/ STEPS));

            }

        }
    }

    private void putInRange(Character c) {
        if(c.getX() < X_MIN_SCREEN_SIZE) c.setX(X_MIN_SCREEN_SIZE);
        if(c.getX() > X_MAX_SCREEN_SIZE) c.setX(X_MAX_SCREEN_SIZE);
        if(c.getY() < Y_MIN_SCREEN_SIZE) c.setY(Y_MIN_SCREEN_SIZE);
        if(c.getY() > Y_MAX_SCREEN_SIZE) c.setY(Y_MAX_SCREEN_SIZE);
    }

    public void update() {
        for (Character letter : letters) {
            letter.update();
        }
    }
}
