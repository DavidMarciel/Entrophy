package com.example.david.ent2.Letters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.david.ent2.Dimens.Dimens;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by david on 22/07/2014.
 */
public class AlphabetsFactory {             //Factory Method Patron

    public static final int LATIN_ALPHABET = 0;
    public static final int RUSSIAN_ALPHABET = 1;
    public static final int CHINEESE_ALPHABET = 2;
    public static final int ARABIAN_ALPHABET = 3;
    public static final int DEBANAGARI_ALPHABET = 4;
    public static final int HEBREO_ALPHABET = 5;
    public static final int LATIN_LASER_ALPHABET = 6;
    public static final int LATIN_GUSANO_ALPHABET = 7;
    public static final int NUMBERS = 8;
    public static final int LATIN_LATERAL_WIND_ALPHABET = 9;
    public static final int LATIN_INFINITE_BLOCK_ALPHABET = 10;

    private Context context;
    private RelativeLayout relativeLayout;

    public AlphabetsFactory(Context context, RelativeLayout relativeLayout) {
        this.context = context;
        this.relativeLayout = relativeLayout;
    }

    public Character[] getLetters() {
        ArrayList<Character> lettersList = Instantiator.instantiateLetters( context, relativeLayout);
        return getListAsArray(lettersList);
    }

    public Character[] getLatinLetters() {
        Log.v("AlphabetsFactory", "call to getLatinLetters");
        ArrayList<Character> lettersList = Instantiator.instantiateLatinLetters( context, relativeLayout);
        return getListAsArray(lettersList);
    }

    @NonNull
    private Character[] getListAsArray(ArrayList<Character> lettersList) {
        return lettersList.toArray(new Character[lettersList.size()]);
    }

    public Character[] getStaticLetters() {

        Log.v("AlphabetsFactory", "call to getStaticLetters");
        ArrayList<Character> lettersList = Instantiator.instantiateLatinLetters( context, relativeLayout);
        return getListAsArray(lettersList);
    }


    public Character[] getWorldLetters() {
        ArrayList<Character> lettersList = Instantiator.instantiateWorldLetters(context, relativeLayout);
        return getListAsArray(lettersList);
    }


    public Character[] getMixedLetters() {
        ArrayList<Character> lettersList = Instantiator.instantiateMixedLetters(context, relativeLayout);
        return getListAsArray(lettersList);
    }


    public Character[] getLetters(int type) {

        ArrayList<Character> lettersList;

        switch (type){
            case LATIN_ALPHABET:                lettersList = Instantiator.instantiateLatinLetters(context, relativeLayout);                                          break;
            case RUSSIAN_ALPHABET:              lettersList = Creator.createCirilicalLetters(context, relativeLayout, Color.rgb(77,178,250));    break;
            case CHINEESE_ALPHABET:             lettersList = Creator.createChineeseLetters(context, relativeLayout, Color.LTGRAY);                               break;
            case ARABIAN_ALPHABET:              lettersList = Creator.createArabianLetters(context, relativeLayout, Color.rgb(219,219,4));       break;
            case DEBANAGARI_ALPHABET:           lettersList = Creator.createDevanagariLetters(context, relativeLayout, Color.rgb(250,181,62));   break;
            case HEBREO_ALPHABET:               lettersList = Creator.createHebreanLetters(context, relativeLayout, Color.RED);                                    break;
            case NUMBERS:                       return Instantiator.instantiateNumbers(context, relativeLayout, Color.rgb(43, 173, 69));
            case LATIN_LASER_ALPHABET:          return Instantiator.instantiateLaserLetters(context, relativeLayout);
            case LATIN_GUSANO_ALPHABET:         return Instantiator.instantiateSnakeLetters(context, relativeLayout);
            case LATIN_INFINITE_BLOCK_ALPHABET: lettersList = Creator.createInfinityBlockLatinLetters(context, relativeLayout, Color.rgb(255,159,54)); break;
            case LATIN_LATERAL_WIND_ALPHABET:   return Instantiator.instantiateLateralWindLetters(context, relativeLayout, Color.BLUE);

            default:                            lettersList = null;                break;
        }
        return getListAsArray(lettersList);
    }

}

