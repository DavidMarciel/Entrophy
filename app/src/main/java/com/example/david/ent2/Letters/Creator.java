package com.example.david.ent2.Letters;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Movements.Move;

import java.util.ArrayList;

/**Delegate to create the Alphabets
 * Created by david on 04/01/2018.
 */

public class Creator {

    static ArrayList<Character> createHebreanLetters(Context context, RelativeLayout relativeLayout, int rgb) {

        ArrayList<Character> letters = new ArrayList<>();
        Character cr;

        final int FIRST_LETTER = 1488;
        final int NUMBER_OF_LETTERS = 27;// 0xFF;
        final int LETTERS_PER_ROW = 4;// 11; //en realidad son 12
        int x = -1;
        int y = -1;

        for (int i = 0; i < NUMBER_OF_LETTERS; i++) {

            char value = (char) (FIRST_LETTER + i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if (x == 0) y++;

            cr = new Character(value, relativeLayout, context, rgb, 0f,
                    Dimens.HEBREAN_POSITION_X + Dimens.HEBREAN_POSITION_ADDITION_X * x,
                    Dimens.HEBREAN_POSITION_Y + Dimens.HEBREAN_POSITION_ADDITION_Y * y,
                    Move.INFINITE_BLOCK_MOVE, 0);
            letters.add(cr);
        }
        return letters;
    }

    static ArrayList<Character> createInfinityBlockLatinLetters(Context context, RelativeLayout relativeLayout, int rgb) {

        ArrayList<Character> letters = new ArrayList<>();
        Character cr;

        final int FIRST_LETTER = 'A';
        final int NUMBER_OF_LETTERS = 26;// 0xFF;
        final int LETTERS_PER_ROW = 4;// 11; //en realidad son 12
        int x = -1;
        int y = -1;

        for (int i = 0; i < NUMBER_OF_LETTERS; i++) {

            char valor = (char) (FIRST_LETTER + i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if (x == 0) y++;

            cr = new Character(valor, relativeLayout, context, rgb, 0f,
                    Dimens.HEBREAN_POSITION_X + Dimens.HEBREAN_POSITION_ADDITION_X * x,
                    Dimens.HEBREAN_POSITION_Y + Dimens.HEBREAN_POSITION_ADDITION_Y * y,
                    Move.INFINITE_BLOCK_MOVE, 0);
            letters.add(cr);
        }
        return letters;
    }

    static ArrayList<Character> createLatinLetters(Context context, RelativeLayout relativeLayout, int firstLetter, int rgb, int movementType, int xPosition, boolean rigth, int letterType) {

        ArrayList<Character> letters = new ArrayList<>();

        final int numLetters = 26;
        int yDistance;
        int yPosition;

        if (rigth == true) {
            yPosition = Dimens.LATIN_LETTERS_POSITION_Y;
            yDistance = Dimens.LATIN_LETTERS_ADDITION_Y;
        } else {
            //el tamaño es 1050 por eso uso 1025
            // solo se puede ustilizar 950 para que sea compatible con el modo apaisado uso 925
            yPosition = Dimens.LATIN_LETTERS_END_POSITION_Y;
            ;
            yDistance = -Dimens.LATIN_LETTERS_ADDITION_Y;
        }

        TextView tv;
        Character cr;

        for (int i = 0; i < numLetters; i++) {

            char valor = (char) (firstLetter + i);
            cr = new Character(valor, relativeLayout, context, rgb, 0.0f,
                    yPosition + yDistance * i,
                    xPosition,
                    movementType, letterType);

            letters.add(cr);
        }
        return letters;
    }

    private static ArrayList<Character> createLatinLetterMix(Context context, RelativeLayout relativeLayout, int firstLetter, int rgb, int moveType, int xPosition, boolean right, int letterType,
                                                             int firstLetter2, int rgb2, int moveType2, int xPosition2, boolean right2, int letterType2) {

        ArrayList<Character> letters = new ArrayList<>();

        final int numLetters = 26;
        int yDistance;
        int yPosition;


        if (right == true) {
            yPosition = Dimens.LATIN_LETTERS_POSITION_Y;
            yDistance = Dimens.LATIN_LETTERS_ADDITION_Y;
        } else {
            //el tamaño es 1050 por eso uso 1025
            // solo se puede ustilizar 950 para que sea compatible con el modo apaisado uso 925
            yPosition = Dimens.LATIN_LETTERS_END_POSITION_Y;
            ;
            yDistance = -Dimens.LATIN_LETTERS_ADDITION_Y;
        }

        Character cr;

        for (int i = 0; i < numLetters; i += 2) {

            char valor = (char) (firstLetter + i);
            cr = new Character(valor, relativeLayout, context, rgb, 0.0f,
                    yPosition + yDistance * i,
                    xPosition,
                    moveType, letterType);

            letters.add(cr);

            char valor2 = (char) (firstLetter2 + i + 1);
            cr = new Character(valor2, relativeLayout, context, rgb2, 0.0f,
                    yPosition + yDistance * (i + 1),
                    xPosition,
                    moveType2, letterType2);

            letters.add(cr);
        }
        return letters;
    }

    static ArrayList<Character> createNumbers(Context context, RelativeLayout relativeLayout, int firstLetter, int rgb, int movementType, int xPosition, boolean right, int letterType) {

        ArrayList<Character> letters = new ArrayList<>();

        final int numLetters = 10;
        float yDistance;
        int yPosition;


        if (right == true) {
            yPosition = Dimens.LATIN_LETTERS_POSITION_Y;
            yDistance = Dimens.LATIN_LETTERS_ADDITION_Y * 2.5f;
        } else {
            //el tamaño es 1050 por eso uso 1025
            // solo se puede ustilizar 950 para que sea compatible con el modo apaisado uso 925
            yPosition = Dimens.LATIN_LETTERS_END_POSITION_Y + 10;
            yDistance = -Dimens.LATIN_LETTERS_ADDITION_Y * 2.5f;
        }

        Character cr;

        for (int i = 0; i < numLetters; i++) {

            char value = (char) (firstLetter + i);
            cr = new Character(value, relativeLayout, context, rgb, 0f,
                    (float) yPosition + yDistance * i,
                    xPosition,
                    movementType, letterType);

            letters.add(cr);
        }
        return letters;
    }

    static ArrayList<Character> createLetters(Context context, RelativeLayout relativeLayout, int firstLetter, int numLetters, int rgb, int movementType,
                                              int positionX, int positionY, int xDistance, int yDistance, int letterType) {

        ArrayList<Character> letters = new ArrayList<>();
        Character cr;

        for (int i = 0; i < numLetters; i++) {

            char value = (char) (firstLetter + i);
            cr = new Character(value, relativeLayout, context, rgb, 0f, positionX + xDistance * i, positionY + yDistance * i, movementType, letterType);

            letters.add(cr);
        }
        return letters;
    }

    static ArrayList<Character> createChineeseLetters(Context context, RelativeLayout relativeLayout, int rgb) {

        ArrayList<Character> letters = new ArrayList<>();

        //rango caracteres chinos: //rango 4E00-9FFF        son 20991 letters, demasiadas
        final int FIRST_LETTER = 0x4E00;
        final int NUMBER_OF_LETTERS = 0x9FFF - 0x4E00;
        final int LETTERS_PER_ROW = 13; // 15;
        int x = -1;
        int y = -1;

        for (int i = 0; i < NUMBER_OF_LETTERS; i += 55) {

            char value = (char) (FIRST_LETTER + i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if (x == 0) y++;

            letters.add(
                    new Character(value, relativeLayout, context, rgb, 0f,
                            (Dimens.CHINEESE_LETTERS_POSITION_X + Dimens.CHINEESE_LETTERS_ADDITION_X * x)/*(10+ 60 * x)*/,
                            (Dimens.CHINEESE_LETTERS_POSITION_Y + Dimens.CHINEESE_LETTERS_ADDITION_Y * y)/*(0+ 60 * y)*/,
                            Move.STATIC_MOVE, 0)
            );

        }
        return letters;
    }

    static ArrayList<Character> createDevanagariLetters(Context context, RelativeLayout relativeLayout, int rgb) {

        ArrayList<Character> letters = new ArrayList<>();

        //rango caracteres devanagari: 0x904-0x939
        final int FIRST_LETTER = 0x0904;
        final int NUMBER_OF_LETTERS = 0x939 - 0x904;
        final int LETTERS_PER_ROW = 10;//15;
        int x = -1;
        int y = -1;

        for (int i = 0; i < NUMBER_OF_LETTERS; i++) {

            char value = (char) (FIRST_LETTER + i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if (x == 0) y++;

            letters.add(
                    new Character(value, relativeLayout, context, rgb, 0f,
                            (Dimens.DEVANAGARI_POSITION_X + Dimens.DEVANAGARI_POSITION_ADDITION_X * x),
                            (Dimens.DEVANAGARI_POSITION_Y + Dimens.DEVANAGARI_POSITION_ADDITION_Y * y),
                            Move.VERTICAL_BOUNCE_MOVE, 0)
            );
        }
        return letters;
    }

    static ArrayList<Character> createCirilicalLetters(Context context, RelativeLayout relativeLayout, int rgb) {

        ArrayList<Character> letters = new ArrayList<>();

        Character cr;

        final int FIRST_LETTER = 1040;
        final int NUMBER_OF_LETTERS = 64;// 0xFF;
        final int LETTERS_PER_ROW = 10;// 11; //en realidad son 12
        int x = -1;
        int y = -1;

        for (int i = 0; i < NUMBER_OF_LETTERS; i++) {

            char value = (char) (FIRST_LETTER + i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if (x == 0) y++;

            cr = new Character(value, relativeLayout, context, rgb, 0f,
                    Dimens.CIRILICAl_POSITION_X + Dimens.CIRILICAL_POSITION_ADDITION_X * x,
                    Dimens.CIRILICAl_POSITION_Y + Dimens.CIRILICAL_POSITION_ADDITION_Y * y,
                    Move.VERTICAL_BLOCK_BOUNCE_MOVE, 0);
            letters.add(cr);
        }
        return letters;
    }

    static ArrayList<Character> createArabianLetters(Context context, RelativeLayout relativeLayout, int rgb) {

        ArrayList<Character> letters = new ArrayList<>();

        //rangos xSpeed crear: 0x622-0x623, 0x628-0x63A, 0x641-0x64A

        int color = rgb;
        int LETTERS_PER_ROW = 3 -1; //en realidad son 4
        int x = 0;
        int y = 0;
        int i = 0;

        Character cr;

        //rangos xSpeed crear: 0x622-0x623
        int FIRST_LETTER = 0x0622;
        int NUMBER_OF_LETTERS = 0x0623- FIRST_LETTER;

        for(; i<= NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);

            cr = getCaracterArabe(context, relativeLayout, color, x, y, value);
            letters.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;
        }

        //rangos xSpeed crear: 0x628-0x63A
        FIRST_LETTER = 0x0628;
        NUMBER_OF_LETTERS = 0x063B-FIRST_LETTER;

        for(i = 0; i< NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);
            cr = getCaracterArabe(context, relativeLayout, color, x, y, value);
            letters.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

        }

        //rangos xSpeed crear: 0x641-0x64A
        final int FIRST_LETTER2 = 0x0641;
        final int NUMBER_OF_LETTERS2 = 0x0658-FIRST_LETTER;

        for(i = 0; i< 10; i++){     //son solo 10 letters

            char value = (char)(FIRST_LETTER2+i);
            cr = getCaracterArabe(context, relativeLayout, color, x, y, value);
            letters.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

        }

        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;

        //rangos xSpeed crear: 0x641-0x64A
        final int FIRST_LETTER3 = 0x0660;
        final int NUMBER_OF_LETTERS3 = 0x066A-FIRST_LETTER3;

        for(i = 0; i< NUMBER_OF_LETTERS3; i++){     //son solo 10 letters

            char value = (char)(FIRST_LETTER3+i);
            cr =  getCaracterArabe(context, relativeLayout, color, x, y, value);
            letters.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

        }
        return letters;
    }

    private static Character getCaracterArabe(Context context, RelativeLayout relativeLayout, int color, int x, int y, char value) {
        Character cr;
        cr = new Character(value, relativeLayout, context, color, 0f,
                Dimens.ARABIAN_POSITION_X + Dimens.ARABIAN_POSITION_ADDITION_X * x,
                Dimens.ARABIAN_POSITION_Y + Dimens.ARABIAN_POSITION_ADDITION_Y * y,
                Move.HORIZONTAL_BLOCK_BOUNCE_MOVE, 0);
        return cr;
    }
}
