package com.example.david.ent2.Dimens;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**Contiene las medidas de los diferentes modelos de telefono utilizados
 * Created by david on 03/09/2014.
 */
public class Dimens {

    //dispositivo seleccionado
    static public float PROPORTION = 1;

    //dispositivos posibles
    static final public int G4_Desarrollo = 3;

    //caracteristicas de la pantalla
    public static float Y_MAX_SCREEN_SIZE = 1080;//1400;
    public static float X_MAX_SCREEN_SIZE = 630;//1050; //900;
    public static float Y_MIN_SCREEN_SIZE = 0;// 100;
    public static float X_MIN_SCREEN_SIZE = 0;//50;
    public static float SCREEN_X = X_MAX_SCREEN_SIZE - X_MIN_SCREEN_SIZE;
    public static float SCREEN_Y = Y_MAX_SCREEN_SIZE - Y_MIN_SCREEN_SIZE;

    //caracteristicas del desvio
    public static int VERTICAL_Y_DEVIATION = 60;
    public static int VERTICAL_X_DEVIATION = 7;
    public static int HORIZONTAL_Y_DEVIATION = 70;
    public static int HORIZONTAL_X_DEVIATION = 12;
    public static double MAXIMUM_RANGE_DISTANCE = 77.0;
    public static double MAXIMUM_LOW_RANGE_DISTANCE = 10.0;


    //caracteristicas de disposicion de letters
    public static int CHINEESE_LETTERS_POSITION_X = 0;
    public static int CHINEESE_LETTERS_ADDITION_X = 48;
    public static int CHINEESE_LETTERS_POSITION_Y = 0;
    public static int CHINEESE_LETTERS_ADDITION_Y = 40;

    public static int DEVANAGARI_POSITION_X = 17;
    public static int DEVANAGARI_POSITION_ADDITION_X = 57;
    public static int DEVANAGARI_POSITION_Y = 150;
    public static int DEVANAGARI_POSITION_ADDITION_Y = 77;

    public static int ARABIAN_POSITION_X = 440;
    public static int ARABIAN_POSITION_ADDITION_X = 57;
    public static int ARABIAN_POSITION_Y = 75;
    public static int ARABIAN_POSITION_ADDITION_Y = 67;

    public static int HEBREAN_POSITION_X = 250;
    public static int HEBREAN_POSITION_ADDITION_X = 85;
    public static int HEBREAN_POSITION_Y = 650;
    public static int HEBREAN_POSITION_ADDITION_Y = 100;

    public static int CIRILICAl_POSITION_X = 34;
    public static int CIRILICAL_POSITION_ADDITION_X = 57;
    public static int CIRILICAl_POSITION_Y = 400;
    public static int CIRILICAL_POSITION_ADDITION_Y = 70;

    public static int LATIN_LETTERS_POSITION_1 = 50;
    public static int LATIN_LETTERS_POSITION_2 = 125;
    public static int LATIN_LETTERS_POSITION_3 = 225;
    public static int LATIN_LETTERS_POSITION_4 = 325;
    public static int LATIN_LETTERS_POSITION_5 = 475;
    public static int LATIN_LETTERS_POSITION_6 = 575;
    public static int LATIN_LETTERS_POSITION_7 = 625;
    public static int LATIN_LETTERS_POSITION_8 = 775;
    public static int LATIN_LETTERS_POSITION_9 = 900;
    public static int LATIN_LETTERS_POSITION_10 =1025;

    public static int LATIN_LETTERS_POSITION_Y = 0;
    public static int LATIN_LETTERS_END_POSITION_Y = 630;
    public static int LATIN_LETTERS_ADDITION_Y = 25;


    //resultado clave
    public static int KEY_RESULT_POSITION_X = (int) (75 * PROPORTION);
    public static int KEY_RESULT_POSITION_ADDITION_X = (int) (75 * PROPORTION);
    public static int KEY_RESULT_POSITION_Y = (int) (75 * PROPORTION);
    public static float KEY_RESULT_LETTERS_SIZE = (25f * PROPORTION);

    //resultado posibilidades
    public static int RESULT_LETTERS_POSITION_X = KEY_RESULT_POSITION_X;
    public static int RESULT_LETTERS_POSITION_ADDITION_X = KEY_RESULT_POSITION_ADDITION_X;
    public static int RESULT_LETTERS_POSITION_Y = (int) (150 * PROPORTION);
    public static int RESULT_LETTERS_POSITION_ADDITION_Y = (int) (25 * PROPORTION * 1.1);
    public static float RESULT_LETTERS_SIZE = (15f * PROPORTION);

    //resultado aciertos posibilidades
    public static int RESULT_LETTERS_HIT_POSITION_X = KEY_RESULT_POSITION_X + 25;
    public static int RESULT_LETTERS_HIT_POSITION_ADDITION_X = KEY_RESULT_POSITION_ADDITION_X;
    public static int RESULT_LETTERS_HIT_POSITION_Y = RESULT_LETTERS_POSITION_Y - 4;
    public static int RESULT_LETTERS_HIT_POSITION_ADDITION_Y = RESULT_LETTERS_POSITION_ADDITION_Y;
    public static float RESULT_LETTERS_HIT_SIZE = (int) (25f * PROPORTION);


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
    private static float getProportions(Context context) {

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

        return Math.min(width, height);
    }

    static boolean firstUse = true;

    public static void initMedidas(Context context) {
        if (firstUse) {
            PROPORTION = getProportions(context);
            firstUse = false;
            Toast.makeText(context, "las proporciones son= "+ PROPORTION,Toast.LENGTH_SHORT).show();
        }
    }

    private Dimens() {
    }

    private static Dimens singleton;

    public static Dimens getMedidas() {

        if(singleton == null){
            singleton = new Dimens();
        }

        return singleton;
    }
}
