package com.example.david.ent2.Letters;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Movements.Move;

import java.util.ArrayList;
import java.util.Arrays;

/**Delegated to instanciate the Alphabets
 * Created by david on 04/01/2018.
 */

public class Instantiator {


    //fuentes buenas: ARMALITE, OLIVER, NEUROPOLITICAL, BLKCHCRY

    /*Amarillo Color.rgb(252,147,17)*/
    /*Amarillo Chllón Color.rgb(252,247,17)*/
    /*Amarillo oscuro Color.rgb(228, 123, 12)*/
    /*Naranja Color.rgb(252,147,17)*/
    /*Morado Color.rgb(177, 12, 228)*/
    /*Gris claro  Color.rgb(140,140,140)*/
    /*Gris oscuro Color.rgb(70,70,70)*/

    static Character[] instantiateNumbers(Context context, RelativeLayout relativeLayout, int color) {

        ArrayList<Character> letters = Creator.createNumbers(context, relativeLayout, '0', color, Move.STATIC_MOVE, Dimens.LATIN_LETTERS_POSITION_2, true, 0);
        return lettersAsArray(letters);
    }

    static Character[] instantiateLaserLetters(Context context, RelativeLayout relativeLayout){

        ArrayList<Character> letters = Creator.createLetters(context, relativeLayout, 'a', 26, Color.rgb(70,70,70), 0, 500, 50, 0, 40, TypeFaces.O4B_03);
        return lettersAsArray(letters);
    }

    static Character[] instantiateSnakeLetters(Context context, RelativeLayout relativeLayout){
        ArrayList<Character> letters = Creator.createLetters(context, relativeLayout, 'A', 26, Color.rgb(70,70,70), 0, 200, 50, 0, 40, TypeFaces.DEFAULT);
        return lettersAsArray(letters);
    }

    static Character[] instantiateLateralWindLetters(Context context, RelativeLayout relativeLayout, int c){
        ArrayList<Character> letters = Creator.createLetters(context, relativeLayout, 'A', 26, c, Move.LATERAL_WIND_MOVE, 50, 10, 0, 42, TypeFaces.DEFAULT);
        return lettersAsArray(letters);
    }

    static Character[] instantiateLateralWindLettersLowerCase(Context context, RelativeLayout relativeLayout, int c){
        ArrayList<Character> letters = Creator.createLetters(context, relativeLayout, 'a', 26, c, Move.LATERAL_WIND_MOVE, 350, 1080, 0, -42, TypeFaces.O4B_03);
        return lettersAsArray(letters);
    }

    static ArrayList<Character> instantiateLatinLetters(Context context, RelativeLayout relativeLayout) {

        ArrayList<Character> letters = new ArrayList<>();

        //matrix
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(43, 173, 69), Move.MATRIX_MOVE,
                Dimens.LATIN_LETTERS_POSITION_1, false, 0));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(43, 173, 69), Move.MATRIX_MOVE,
                Dimens.LATIN_LETTERS_POSITION_6, true, 0));

        //viento lateral
        letters.addAll( Arrays.asList( instantiateLateralWindLetters(context,
                relativeLayout, Color.BLUE)));
        letters.addAll( Arrays.asList( instantiateLateralWindLettersLowerCase(context
                , relativeLayout, Color.BLUE)));

        //letters colores sin mezclar
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_2, true, 0));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_3, true, TypeFaces.AEROLITE_BOLD));

        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(252,147,17), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_4, true, 0));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_10, true, TypeFaces.BLKCHCRY));


        //letters colores sin mezclar
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_8, true, 0));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_7, true, TypeFaces.GLORIA_HALLELUJAH));

        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_5, true, 0));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(252,147,17), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_9, true, TypeFaces.NEUROPOLITICAL));

        return letters;
    }

    public static ArrayList<Character> instantiateWorldLetters(Context context, RelativeLayout relativeLayout) {

        ArrayList<Character> letters = new ArrayList<>();

        letters.addAll( Creator.createCirilicalLetters(context, relativeLayout,
                Color.rgb(77,178,250)));
        letters.addAll( Creator.createDevanagariLetters(context, relativeLayout,
                Color.rgb(250,181,62)));
        letters.addAll( Creator.createHebreanLetters(context, relativeLayout, Color.RED));
        letters.addAll( Arrays.asList( Instantiator.instantiateLaserLetters(context, relativeLayout)));
        letters.addAll( Arrays.asList( Instantiator.instantiateSnakeLetters(context, relativeLayout)));
        letters.addAll( Creator.createChineeseLetters(context, relativeLayout, Color.WHITE));

        return letters;
    }

    public static ArrayList<Character> instantiateMixedLetters(Context context, RelativeLayout relativeLayout) {

        ArrayList<Character> letters = new ArrayList<>();

        //matrix
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(43, 173, 69), Move.MATRIX_MOVE,
                Dimens.LATIN_LETTERS_POSITION_1, false, 0));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(43, 173, 69), Move.MATRIX_MOVE,
                Dimens.LATIN_LETTERS_POSITION_6, true, 0));

        //viento lateral
        letters.addAll( Arrays.asList( Instantiator.instantiateLateralWindLetters(context, relativeLayout, Color.BLUE)));
        letters.addAll( Arrays.asList( Instantiator.instantiateLateralWindLettersLowerCase(context, relativeLayout, Color.BLUE)));


        letters.addAll( Creator.createCirilicalLetters(context, relativeLayout, Color.rgb(77,178,250)));
        letters.addAll( Creator.createDevanagariLetters(context, relativeLayout, Color.rgb(250,181,62)));
        letters.addAll( Creator.createHebreanLetters(context, relativeLayout, Color.BLACK));

        letters.addAll( Arrays.asList( Instantiator.instantiateLaserLetters(context, relativeLayout)));
        letters.addAll( Arrays.asList( Instantiator.instantiateSnakeLetters(context, relativeLayout)));


        //letters colores sin mezclar
       letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_3, true, TypeFaces.AEROLITE_BOLD));
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'a',
                Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_10, true, TypeFaces.BLKCHCRY));


        //letters colores sin mezclar
        letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_8, true, 0));
         letters.addAll( Creator.createLatinLetters(context, relativeLayout, 'A',
                Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE,
                Dimens.LATIN_LETTERS_POSITION_5, true, 0));

        return letters;
    }

    /**Encargado de llamar xSpeed la generacion de cada tipo de letra
     *
     */
    public static ArrayList<Character> instantiateLetters(Context context, RelativeLayout relativeLayout) {

        ArrayList<Character> letters = new ArrayList<>();

//        createChineeseLetters(Color.rgb(230,230,230) /*Color.LTGRAY*/);                                  //chinas
//        createCirilicalLetters( Color.rgb(77,178,250));                                                //cirilicas
//        createArabianLetters( Color.rgb(219,219,4));                                                    //arabes
//        createDevanagariLetters( Color.rgb(250,181,62));                                               //devanagari
//        createHebreanLetters(Color.rgb(255,178,102)/*Color.rgb(62, 62, 62)*/);                         //hebreo

        letters.addAll( Instantiator.instantiateLatinLetters(context, relativeLayout));                                                                   //latinas

//        instantiateNumbers(Color.rgb(255, 6, 67));


        return letters;
    }

    private static Character[] lettersAsArray(ArrayList<Character> letterList) {
        Character[] aux = new Character[letterList.size()];
        letterList.toArray(aux);
        return aux;
    }
}
