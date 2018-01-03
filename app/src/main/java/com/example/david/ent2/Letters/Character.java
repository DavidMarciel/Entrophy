package com.example.david.ent2.Letters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Movements.Move;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Character {

    //orientacion
    static final boolean ORIENTATION_HORIZONTAL = false;   //landscape --      dimension X
    static final boolean ORIENTATION_VERTICAL = true;      //portrait |        dimension Y
    private static boolean isOrientationVertical = ORIENTATION_VERTICAL;

    //desvio
    static private Dimens dimens = Dimens.getMedidas();
    static private float PROPORTION = dimens.PROPORTION;
    static private double MAXIMUM_RANGE_DISTANCE = dimens.MAXIMUM_RANGE_DISTANCE;
    static private int VERTICAL_Y_DEVIATION = dimens.VERTICAL_Y_DEVIATION;
    static private int VERTICAL_X_DEVIATION = dimens.VERTICAL_X_DEVIATION;
    static private int HORIZONTAL_Y_DEVIATION = dimens.HORIZONTAL_Y_DEVIATION;
    static private int HORIZONTAL_X_DEVIATION = dimens.HORIZONTAL_X_DEVIATION;
    //static private double MAXIMUM_LOW_RANGE_DISTANCE = dimens.MAXIMUM_LOW_RANGE_DISTANCE;

    //movimiento
    private Move mov;

    //objetos propios
    private TextView textView;
    private int color;
    private char value;
    private int letterType;

    public Character(char value, int color){
        this.value = value;
        this.color = color;
    }

    public Character(char value, RelativeLayout relativeLayout, final Context context, int color,
                     float porcentualIncrease, float x, int y, int movementType, int letterType) {
        this.color = color;
        this.value = value;
//        this.letterType = letterType;
        textView = new TextView(context);
        textView.setText(value + "");
        textView.setTextColor(color);
        textView.setTextSize(18 * (1f + porcentualIncrease));
        setX(x);
        setY(y);

        relativeLayout.addView(textView);

        mov = Move.getMovement(movementType);
        mov.setCaracter(this);

        setLetterType(context, letterType);

    }

    public Character(char letter, int color, int letterType, Context context) {
        this.value = letter;
        this.color = color;

        textView = new TextView(context);
        textView.setText(value + "");
        textView.setTextColor(color);

        setLetterType(context, letterType);
    }

    public void move() {
        mov.move();
    }

    public void setLetterType(Context context, int letterTypeAsInt) {
        this.letterType = letterTypeAsInt;

        Typeface letterType = TypeFaces.getTipeFace(context, letterTypeAsInt);

        if(mov != null &&
                mov.getMovementType() == Move.LINE_BOUNCE_MOVE) {   //los movimientos mas dificiles
            textView.setTypeface(letterType, Typeface.BOLD);          //de encontrar se ven mejor
        }
        else{
            textView.setTypeface(letterType);
        }

    }

    public float getX() {
        if (isOrientationVertical)
            return textView.getX()/ PROPORTION;
        else
            return textView.getY()/ PROPORTION;
    }

    public float getY() {
        if (isOrientationVertical)
            return textView.getY()/ PROPORTION;
        else
            return textView.getX()/ PROPORTION;
    }

    public void setX(float x) {
        if (isOrientationVertical)
            textView.setX(x* PROPORTION);
        else
            textView.setY(x* PROPORTION);
    }

    public void setY(float y) {
        if (isOrientationVertical)
            textView.setY(y * PROPORTION);
        else
            textView.setX(y * PROPORTION);
    }

    public static boolean isIsOrientationVertical() {
        return isOrientationVertical;
    }

    public static void setIsOrientationVertical(boolean isOrientationVertical) {
        Character.isOrientationVertical = isOrientationVertical;
    }

    public void selected() {
            textView.setTextColor(Color.RED);
    }

    public void notSelected() {
            textView.setTextColor(color);
    }

    public char getValue() {
        return value;
    }

    public int getColor() {
        return color;
    }

    public String printInfo() {

        String s = "Valor: " + getValue();
        s += "\n color: " + color;
        s += "\n posicion x,y : " + getX() + "," + getY();
        s += "\n tipoMovimiento: " + mov.getInfo();

        return s;
    }

    public void update() {
        mov.update();
    }

    /**Este método devuelve true si el caracter está cerca del lugar tapHandler,
     * el rango es una constante (MAXIMUM_RANGE_DISTANCE) definida en esta clase y
     * obtenida xSpeed su vez del objeto "Dimens" utilizado.
     *
     * @param event evento pulsación en el tablero
     * @return true si el caracter está dentro del rango para el evento pulsación recibido
     */
    public boolean inRange(MotionEvent event) {

        float xDifference ;
        float yDifference;   //el - 120 es para calibrarlo

        if(isOrientationVertical) {
            xDifference = event.getX() / PROPORTION - this.getX() - VERTICAL_X_DEVIATION;
            yDifference = event.getY() / PROPORTION - this.getY() - VERTICAL_Y_DEVIATION;
        }
        else{
            xDifference = event.getX() / PROPORTION - this.getY() - HORIZONTAL_X_DEVIATION;
            yDifference = event.getY() / PROPORTION - this.getX() - HORIZONTAL_Y_DEVIATION;
        }

        double distance = xDifference * xDifference + yDifference * yDifference;
        distance = Math.sqrt(distance);

        if (distance < MAXIMUM_RANGE_DISTANCE) return true;
        else return false;
    }

    public boolean equals(Character otro){

        if (this.getValue() == otro.getValue()
                && this.getColor() == otro.getColor()) {
            return true;
        }
        else return false;

    }

    public boolean containedBy(Character[] items){

        for(int i = 0; i< items.length; i++) {
            if (this.getValue() == items[i].getValue()
                    && this.getColor() == items[i].getColor()) {
                return true;
            }
        }
        return false;

    }

    public int getLetterType() {
        return letterType;
    }

    public Typeface getTypeFace(Context context) {
        return TypeFaces.getTipeFace(context, letterType);
    }

    /**Anade el caracter para ser mostrado en el RelativeLayout pasado como parámetro
     *
     * @param rl RelativeLayout en el que se mostrará
     */
    public void show(RelativeLayout rl){
        rl.addView(textView);
    }

    public void setMove(int movementType) {

        mov = Move.getMovement(movementType);
        mov.setCaracter(this);
    }
}