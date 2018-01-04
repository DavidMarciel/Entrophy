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

    //deviation
    static private float proportion = 0;
    static private double MAXIMUM_RANGE_DISTANCE = Dimens.MAXIMUM_RANGE_DISTANCE;
    static private int VERTICAL_Y_DEVIATION = Dimens.VERTICAL_Y_DEVIATION;
    static private int VERTICAL_X_DEVIATION = Dimens.VERTICAL_X_DEVIATION;
    static private int HORIZONTAL_Y_DEVIATION = Dimens.HORIZONTAL_Y_DEVIATION;
    static private int HORIZONTAL_X_DEVIATION = Dimens.HORIZONTAL_X_DEVIATION;
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
        textView.setText( String.valueOf( value));
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
        textView.setText( String.valueOf( value));
        textView.setTextColor(color);

        setLetterType(context, letterType);
    }

    public void move() {
        mov.move();
    }

    public void setLetterType(Context context, int letterTypeAsInt) {
        this.letterType = letterTypeAsInt;

        Typeface letterType = TypeFaces.getTipeFace(context, letterTypeAsInt);

        if(mov != null && mov.getMovementType() == Move.LINE_BOUNCE_MOVE) {     //los movimientos mas dificiles
            textView.setTypeface(letterType, Typeface.BOLD);                    //de encontrar se ven mejor
        }
        else{
            textView.setTypeface(letterType);
        }

    }

    public float getX() {
        if (isOrientationVertical)
            return textView.getX()/ proportion;
        else
            return textView.getY()/ proportion;
    }

    public float getY() {
        if (isOrientationVertical)
            return textView.getY()/ proportion;
        else
            return textView.getX()/ proportion;
    }

    public void setX(float x) {
        if (isOrientationVertical)
            textView.setX(x* proportion);
        else
            textView.setY(x* proportion);
    }

    public void setY(float y) {
        if (isOrientationVertical)
            textView.setY(y * proportion);
        else
            textView.setX(y * proportion);
    }

    public static boolean isOrientationVertical() {
        return isOrientationVertical;
    }

    public static void setIsOrientationVertical(boolean isOrientationVertical, Context context) {
        Character.isOrientationVertical = isOrientationVertical;
        proportion = Dimens.getProportion(context);
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
            xDifference = event.getX() / proportion - this.getX() - VERTICAL_X_DEVIATION;
            yDifference = event.getY() / proportion - this.getY() - VERTICAL_Y_DEVIATION;
        }
        else{
            xDifference = event.getX() / proportion - this.getY() - HORIZONTAL_X_DEVIATION;
            yDifference = event.getY() / proportion - this.getX() - HORIZONTAL_Y_DEVIATION;
        }

        double distance = xDifference * xDifference + yDifference * yDifference;
        distance = Math.sqrt(distance);

        return distance < MAXIMUM_RANGE_DISTANCE;
    }

    public boolean equals(Character otro){

        if (this.getValue() == otro.getValue()
                && this.getColor() == otro.getColor()) {
            return true;
        }
        else return false;

    }

    public boolean containedBy(Character[] items){

        for (Character item : items) {
            if (this.getValue() == item.getValue()
                    && this.getColor() == item.getColor()) {
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
     * @param relativeLayout RelativeLayout en el que se mostrará
     */
    public void showIn(RelativeLayout relativeLayout){
        relativeLayout.addView(textView);
    }

    public void setMove(int movementType) {

        mov = Move.getMovement(movementType);
        mov.setCaracter(this);
    }
}