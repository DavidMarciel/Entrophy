package com.example.david.ent2.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.Counter;

import java.util.ArrayList;

/**It is responsable to store/retrieve data from Shared Preferences
 * Created by david on 15/03/2015.
 */
public class DataStorage {

    private static final String SHAREDPREFERENCES_NAME = "Entrophy senala";
    private static final String SHAREDPREFERENCES_KEY_NAME = "Entrophy clave";
    private static final String LOG_DEVELOPMENT= "Desarrollo";
    private static final String NUMBER_OF_LETTERS = "NumeroLetras";
    private static final String SIGNAL = "senal";
    private static final String ELAPSED_TIME = "tiempoTardado";
    private static final String ENTROPHY = "Entrophy";
    private static final String ALPHABETS_TYPE = "alphabetsType";
    private static final String DEFAULT_ALPHABETS_TYPE = "Dynamic";
    private static final String SPEED = "speed";
    private static final String IS_PASSWORD_MOVING = "moverContraseña";
    private static final String LETTER = "letra ";
    private static final String SUB_LETTER = "letraSub ";
    private static final String NUMBER_OF_SUB_LETTERS = "subLetra ";

    public static Counter getPassword(Context context) {

        SharedPreferences sh = context.getSharedPreferences(SHAREDPREFERENCES_KEY_NAME, Context.MODE_PRIVATE);

        int numberOfLetters = sh.getInt(NUMBER_OF_LETTERS, 0);
        Counter counter = new Counter(numberOfLetters);


        for (int i = 0; i < numberOfLetters; i++) {

            String characterInfo = sh.getString(LETTER + i, "");
            Character character = getCharacter(context, characterInfo);
            counter.add(character);
        }

        return counter;
    }

    @NonNull
    private static Character getCharacter(Context context, String characterInfo) {

        String[] letterInfoInSharedPrefferences = characterInfo.split(" ");
        char letter = letterInfoInSharedPrefferences[0].charAt(0);
        int color = Integer.parseInt(letterInfoInSharedPrefferences[1]);
        int letterType = getLetterType(letterInfoInSharedPrefferences);

        return new Character(letter, color, letterType, context);
    }

    private static int getLetterType(String[] s) {
        int letterType;
        try {
            letterType = Integer.parseInt(s[2]);
        }catch (Exception e){
            letterType = 0;
        }
        return letterType;
    }


    public static Character[][] getPossibilities(Context context) {

        SharedPreferences sh = context.getSharedPreferences(ENTROPHY, Context.MODE_PRIVATE);

        int numberOfLetters = sh.getInt(NUMBER_OF_LETTERS, 0);
        if(numberOfLetters == 0) {
            Log.v(LOG_DEVELOPMENT, "El numero de letters es 0, falla en \"Result getPossibilities\"");
        }
        Character[][] possibilities = new Character[numberOfLetters][];

        for (int i = 0; i < numberOfLetters; i++) {

            int numberOfSubletters = sh.getInt(NUMBER_OF_SUB_LETTERS + i, 0);
            possibilities[i] = new Character[numberOfSubletters];

            for (int j = 0; j < numberOfSubletters; j++) {

                String characterInfo = sh.getString(SUB_LETTER + i + " " + j, "Z -16777216");
                Character character = getCharacter(context, characterInfo);
                possibilities[i][j] = character;
            }
        }
        return possibilities;
    }



    public static long getElapsedTime(Context context) {
        SharedPreferences sh = context.getSharedPreferences(ENTROPHY, Context.MODE_PRIVATE);
        long elapsedTime = sh.getLong(ELAPSED_TIME, 0);

        return elapsedTime;
    }

    public static void savePossibilities(ArrayList<ArrayList<Character>> choosenCharacters, Context applicationContext) {

        for (int i = 0; i < choosenCharacters.size(); i++) {

            for (int j = 0; j < choosenCharacters.get(i).size(); j++) {

                Character c = choosenCharacters.get(i).get(j);

                String letters = c.getValue() + " " + c.getColor() + " " + c.getLetterType();
                add1Possibility(letters, i, j, applicationContext);
            }
            savePossibilitiesForTap(i, choosenCharacters.get(i).size(),applicationContext);
        }
    }

    public static void saveElapsedTime(long l, Context applicationContext) {

        SharedPreferences.Editor ed = getPasswordEditor(applicationContext);

        ed.putLong(ELAPSED_TIME, l);
        ed.commit();

    }

    private static void savePossibilitiesForTap(int tapNumber, int number, Context applicationContext) {

        SharedPreferences.Editor ed = getPasswordEditor(applicationContext);

        ed.putInt(NUMBER_OF_SUB_LETTERS + tapNumber, number);
        ed.commit();
    }

    private static void add1Possibility(String letter, int tapNumber, int number, Context applicationContext) {

        SharedPreferences.Editor ed = getPasswordEditor(applicationContext);

        ed.putString(SUB_LETTER + tapNumber + " " + number, letter);
        ed.commit();

    }

    private static SharedPreferences.Editor getPasswordEditor(Context context) {
        SharedPreferences sh = context.getSharedPreferences(ENTROPHY, Context.MODE_PRIVATE);
        return sh.edit();
    }

    public static void resetStoredTapValues(int numberOfLettersInPassword, Context applicationContext) {

        SharedPreferences.Editor ed = getPasswordEditor(applicationContext);
        ed.clear();
        ed.commit();

        ed.putInt(NUMBER_OF_LETTERS, numberOfLettersInPassword);
        ed.commit();
    }

    public static void storePassword(Counter counter, Context context) {

        SharedPreferences.Editor ed = getSharedPreferencesKeyEditor(context, SHAREDPREFERENCES_KEY_NAME);

        ed.putInt(NUMBER_OF_LETTERS, counter.size());
        //String[] letters = new String[counter.size()];
        //Character c;


        for(int i = 0; i< counter.size(); i++){

            Character c = counter.get(i);

            String characterInfo = c.getValue()+" "+c.getColor()+" "+c.getLetterType();
            ed.putString(LETTER +i, characterInfo);
        }
        ed.commit();
    }

    public static void signal(Context applicationContext) {

        SharedPreferences.Editor ed = DataStorage.getSharedPreferencesKeyEditor(applicationContext, SHAREDPREFERENCES_NAME);
        ed.putBoolean(SIGNAL, true);
        ed.commit();
        Log.v(LOG_DEVELOPMENT, "señalizado "+ isSignalizable(applicationContext));
    }

    public static void notSignal(Context applicationContext) {

        SharedPreferences.Editor ed = DataStorage.getSharedPreferencesKeyEditor(applicationContext, SHAREDPREFERENCES_NAME);
        ed.putBoolean(SIGNAL, false);
        ed.commit();
        Log.v(LOG_DEVELOPMENT, "desSeñalizado "+ isSignalizable(applicationContext));
    }

    public static boolean isSignalizable(Context context){

        SharedPreferences sh = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean isSingal = sh.getBoolean(SIGNAL, true);
        Log.v(LOG_DEVELOPMENT, "isSeñala vale "+ isSingal);
        return isSingal;
    }

    public static void signalMovePassword(Context applicationContext) {

        SharedPreferences.Editor ed = DataStorage.getSharedPreferencesKeyEditor(applicationContext, SHAREDPREFERENCES_NAME);
        ed.putBoolean(IS_PASSWORD_MOVING, true);
        ed.commit();
        Log.v(LOG_DEVELOPMENT, "señalizado isMoverContraseña"+ isSignalizable(applicationContext));
    }

    public static void unSignalMovePassword(Context applicationContext) {

        SharedPreferences.Editor ed = DataStorage.getSharedPreferencesKeyEditor(applicationContext, SHAREDPREFERENCES_NAME);
        ed.putBoolean(IS_PASSWORD_MOVING, false);
        ed.commit();
        Log.v(LOG_DEVELOPMENT, "desSeñalizado isMoverContraseña"+ isSignalizable(applicationContext));
    }

    public static boolean isPasswordMoving(Context context) {

        SharedPreferences sh = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean isSignal = sh.getBoolean(IS_PASSWORD_MOVING, true);
        Log.v(LOG_DEVELOPMENT, "isMoverContraseña vale "+ isSignal);
        return isSignal;

    }

    public static void setSpeed(Context applicationContext, int velocidad) {

        SharedPreferences.Editor ed = DataStorage.getSharedPreferencesKeyEditor(applicationContext, SHAREDPREFERENCES_NAME);
        ed.putInt(SPEED, velocidad);
        ed.commit();
        Log.v(LOG_DEVELOPMENT, "fijada speed xSpeed: "+velocidad);
    }

    public static int getSpeed(Context context) {

        SharedPreferences sh = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        int speed = sh.getInt(SPEED, 5);                              //cambiar la por defecto xSpeed 5
        Log.v(LOG_DEVELOPMENT, "la speed es: "+ speed);
        return speed;
    }

    public static void setAlphabetsType(String alphabetsType, Context context) {

        SharedPreferences.Editor ed = DataStorage.getSharedPreferencesKeyEditor(context, SHAREDPREFERENCES_NAME);
        ed.putString(ALPHABETS_TYPE, alphabetsType);
        ed.commit();
        Log.v(LOG_DEVELOPMENT, "fijada alphabetsType xSpeed: "+alphabetsType);
    }

    public static String getAlphabetsType(Context context) {

        SharedPreferences sh = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        String alphabetsType = sh.getString(ALPHABETS_TYPE, DEFAULT_ALPHABETS_TYPE);
        Log.v(LOG_DEVELOPMENT, "la alphabetsType es: "+ alphabetsType);
        return alphabetsType;
    }

    private static SharedPreferences.Editor getSharedPreferencesKeyEditor(Context context, String sharedpreferencesKeyName) {
        SharedPreferences sh = context.getSharedPreferences(sharedpreferencesKeyName, Context.MODE_PRIVATE);
        return sh.edit();
    }

}
