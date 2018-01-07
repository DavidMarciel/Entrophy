package com.example.david.ent2.Dimens;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**Contiene las medidas de los diferentes modelos de telefono utilizados
 * Created by david on 03/09/2014.
 */
public class Dimens {

    //caracteristicas de la pantalla
    public static final float Y_MAX_SCREEN_SIZE = 1080;//1400;
    public static final float X_MAX_SCREEN_SIZE = 630;//1050; //900;
    public static final float Y_MIN_SCREEN_SIZE = 0;// 100;
    public static final float X_MIN_SCREEN_SIZE = 0;//50;
    public static final float SCREEN_X = X_MAX_SCREEN_SIZE - X_MIN_SCREEN_SIZE;
    public static final float SCREEN_Y = Y_MAX_SCREEN_SIZE - Y_MIN_SCREEN_SIZE;

    //caracteristicas del desvio
    public static final int VERTICAL_Y_DEVIATION = 60;
    public static final int VERTICAL_X_DEVIATION = 7;
    public static final int HORIZONTAL_Y_DEVIATION = 70;
    public static final int HORIZONTAL_X_DEVIATION = 12;
    public static final double MAXIMUM_RANGE_DISTANCE = 77.0;
    public static final double MAXIMUM_LOW_RANGE_DISTANCE = 10.0;


    //caracteristicas de disposicion de letters
    public static final int CHINEESE_LETTERS_POSITION_X = 0;
    public static final int CHINEESE_LETTERS_ADDITION_X = 48;
    public static final int CHINEESE_LETTERS_POSITION_Y = 0;
    public static final int CHINEESE_LETTERS_ADDITION_Y = 40;

    public static final int DEVANAGARI_POSITION_X = 17;
    public static final int DEVANAGARI_POSITION_ADDITION_X = 57;
    public static final int DEVANAGARI_POSITION_Y = 150;
    public static final int DEVANAGARI_POSITION_ADDITION_Y = 77;

    public static final int ARABIAN_POSITION_X = 440;
    public static final int ARABIAN_POSITION_ADDITION_X = 57;
    public static final int ARABIAN_POSITION_Y = 75;
    public static final int ARABIAN_POSITION_ADDITION_Y = 67;

    public static final int HEBREAN_POSITION_X = 250;
    public static final int HEBREAN_POSITION_ADDITION_X = 85;
    public static final int HEBREAN_POSITION_Y = 650;
    public static final int HEBREAN_POSITION_ADDITION_Y = 100;

    public static final int CIRILICAl_POSITION_X = 34;
    public static final int CIRILICAL_POSITION_ADDITION_X = 57;
    public static final int CIRILICAl_POSITION_Y = 400;
    public static final int CIRILICAL_POSITION_ADDITION_Y = 70;

    public static final int LATIN_LETTERS_POSITION_1 = 50;
    public static final int LATIN_LETTERS_POSITION_2 = 125;
    public static final int LATIN_LETTERS_POSITION_3 = 225;
    public static final int LATIN_LETTERS_POSITION_4 = 325;
    public static final int LATIN_LETTERS_POSITION_5 = 475;
    public static final int LATIN_LETTERS_POSITION_6 = 575;
    public static final int LATIN_LETTERS_POSITION_7 = 625;
    public static final int LATIN_LETTERS_POSITION_8 = 775;
    public static final int LATIN_LETTERS_POSITION_9 = 900;
    public static final int LATIN_LETTERS_POSITION_10 =1025;

    public static final int LATIN_LETTERS_POSITION_Y = 0;
    public static final int LATIN_LETTERS_END_POSITION_Y = 630;
    public static final int LATIN_LETTERS_ADDITION_Y = 25;


    //dispositivo seleccionado
    private static float proportion = 1f;//1

    //resultado clave
    private int keyResultPositionX = (int) (75 * proportion);
    private int keyResultPositionAdditionX = (int) (75 * proportion);
    private int keyResultPositionY = (int) (75 * proportion);
    private float keyResultLettersSize = (25f * proportion);

    //resultado posibilidades
    private int resultLettersPositionX = keyResultPositionX;
    private int resultPositionAdditionX = keyResultPositionAdditionX;
    private int resultLettersPositionY = (int) (150 * proportion);
    private int resultLettersPositionAdditionY = (int) (25 * proportion * 1.1);
    private float resultLettersSize = (15f * proportion);

    //resultado aciertos posibilidades
    private int resultLettersHitPositionX = keyResultPositionX + 25;
    private int resultLettersHitPositionAdditionX = keyResultPositionAdditionX;
    private int resultLettersHitPositionY = resultLettersPositionY - 4;
    private int resultLettersHitPositionAdditionY = resultLettersPositionAdditionY;
    private float resultLettersHitSize = (int) (25f * proportion);

    public int getKeyResultPositionX() {
        return keyResultPositionX;
    }

    public int getKeyResultPositionAdditionX() {
        return keyResultPositionAdditionX;
    }

    public int getKeyResultPositionY() {
        return keyResultPositionY;
    }

    public float getKeyResultLettersSize() {
        return keyResultLettersSize;
    }

    public int getResultLettersPositionX() {
        return resultLettersPositionX;
    }

    public int getResultPositionAdditionX() {
        return resultPositionAdditionX;
    }

    public int getResultLettersPositionY() {
        return resultLettersPositionY;
    }

    public int getResultLettersPositionAdditionY() {
        return resultLettersPositionAdditionY;
    }

    public float getResultLettersSize() {
        return resultLettersSize;
    }

    public int getResultLettersHitPositionX() {
        return resultLettersHitPositionX;
    }

    public int getResultLettersHitPositionAdditionX() {
        return resultLettersHitPositionAdditionX;
    }

    public int getResultLettersHitPositionY() {
        return resultLettersHitPositionY;
    }

    public int getResultLettersHitPositionAdditionY() {
        return resultLettersHitPositionAdditionY;
    }

    public float getResultLettersHitSize() {
        return resultLettersHitSize;
    }

    private Dimens() {
    }

    private static boolean firstAccess = true;
    private static Dimens dimens;

    public static Dimens getDimens(Context context) {
        if (firstAccess) {
            firstAccess = false;

            dimens = new Dimens();
            dimens.getProportions(context);
            //proportion = getProportions(context);
            //recalculateValues();
            Log.v("Proporciones", "la proporcion es: "+proportion);
            Toast.makeText(context, "las proporciones son= " + proportion, Toast.LENGTH_SHORT).show();
        }
        return dimens;
    }

    public float getProportion() {
        return proportion;
    }

    /**Devuelve la proporción de la pantalla con respecto x la de referencia (Jiayu G4)
     * Se utiliza para redimensionar de forma proporcional x otros dispositivos,
     * multiplica la dimensión actual por el valor obtenido por esta función y
     * el resultado debería mostrarse en una posición proporcional correcta respecto al
     * dispositivo de referencia
     *
     * @param context contexto desde el que se llama
     * @return proporción
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void getProportions(Context context) {

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        float width;// = size.x;
        float height;// = size.y;

        if( context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            width = size.x;
            height = size.y;
        }
        else{
            width = size.y;
            height = size.x;
        }

        width = width / 720;
        height = height / 1080;

        proportion = Math.min(width, height);
        recalculateValues();
        //return Math.min(width, height);
    }

    private void recalculateValues() {

        /*//resultado clave
        keyResultPositionX = (int) (75 * proportion);
        keyResultPositionAdditionX = (int) (75 * proportion);
        keyResultPositionY = (int) (75 * proportion);
        keyResultLettersSize = (25f * proportion);

        //resultado posibilidades
        resultLettersPositionY = (int) (150 * proportion);
        resultLettersPositionAdditionY = (int) (25 * proportion * 1.1);
        resultLettersSize = (15f * proportion);

        //resultado aciertos posibilidades
        resultLettersHitSize = (int) (25f * proportion);*/


         keyResultPositionX = (int) (75 * proportion);
         keyResultPositionAdditionX = (int) (75 * proportion);
         keyResultPositionY = (int) (75 * proportion);
         keyResultLettersSize = (25f * proportion);

        //resultado posibilidades
         resultLettersPositionX = keyResultPositionX;
         resultPositionAdditionX = keyResultPositionAdditionX;
         resultLettersPositionY = (int) (150 * proportion);
         resultLettersPositionAdditionY = (int) (25 * proportion * 1.1);
         resultLettersSize = (15f * proportion);

        //resultado aciertos posibilidades
         resultLettersHitPositionX = keyResultPositionX + 25;
         resultLettersHitPositionAdditionX = keyResultPositionAdditionX;
         resultLettersHitPositionY = resultLettersPositionY - 4;
         resultLettersHitPositionAdditionY = resultLettersPositionAdditionY;
         resultLettersHitSize = (int) (25f * proportion);
    }

}
