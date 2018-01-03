package com.example.david.ent2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.Counter;

import java.util.ArrayList;

/**
 * Created by david on 15/03/2015.
 */
public class DataStorage {

    public static Counter getPassword(Context context) {

        SharedPreferences sh = context.getSharedPreferences("Entrophy clave", Context.MODE_PRIVATE);

        int numberOfLetters = sh.getInt("NumeroLetras", 0);
        Counter cont = new Counter(numberOfLetters);
        Character c;


        for (int i = 0; i < numberOfLetters; i++) {

            String[] s = sh.getString("letra " + i, "").split(" ");
            char letter = s[0].charAt(0);
            int color = Integer.parseInt(s[1]);
            int letterType;
            try {
                letterType = Integer.parseInt(s[2]);
            }catch (Exception e){
                letterType = 0;
            }

            c = new Character(letter, color, letterType, context);       //hay que usar este en vez del de abajo
            cont.add(c);
        }

        return cont;
    }


    public static Character[][] getPossibilities(Context context) {

        SharedPreferences sh = context.getSharedPreferences("Entrophy", context.MODE_PRIVATE);

        int numberOfLetters = sh.getInt("NumeroLetras", 0);
        if(numberOfLetters == 0) Log.v("Desarrollo", "El numero de letters es 0, falla en \"Result getPossibilities\"");
        Character[][] possibilities = new Character[numberOfLetters][];

        for (int i = 0; i < numberOfLetters; i++) {

            int numberOfSubletters = sh.getInt("subLetra " + i, 0);
            possibilities[i] = new Character[numberOfSubletters];
            Character c;

            for (int j = 0; j < numberOfSubletters; j++) {

                String s1 = sh.getString("letraSub " + i + " " + j, "Z -16777216");
                String[] s = s1.split(" ");
                char letter = s[0].charAt(0);
                int color = Integer.parseInt(s[1]);
                int letterType = Integer.parseInt(s[2]);

                Log.v("tipoLetra", "el tipo de letra es: "+letterType);                          //borrar

                if (letter == 'Z') {
                    Log.v("La letra es Z:", "indices " + i + " " + j + " " + s1);
                }
                c = new Character(letter, color, letterType, context);
                possibilities[i][j] = c;
            }
        }
        return possibilities;
    }


    public static long getElapsedTime(Context c) {
        SharedPreferences sh = c.getSharedPreferences("Entrophy", c.MODE_PRIVATE);
        long elapsedTime = sh.getLong("tiempoTardado", 0);

        return elapsedTime;
    }

    public static void savePossibilities(ArrayList<Character>[] choosenCharacters, Context applicationContext) {

        Character c;

        for (int i = 0; i < choosenCharacters.length; i++) {

            int j;
            for (j = 0; j < choosenCharacters[i].size(); j++) {

                c = choosenCharacters[i].get(j);

                String letters = c.getValue() + " " + c.getColor() + " " + c.getLetterType();
                add1Possibility(letters, i, j, applicationContext);
            }
            savePossibilitiesForTap(i, choosenCharacters[i].size(),applicationContext);
        }
    }

    public static void saveElapsedTime(long l, Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putLong("tiempoTardado", l);

        ed.commit();

    }

    private static void savePossibilitiesForTap(int tapNumber, int number, Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putInt("subLetra " + tapNumber, number);

        ed.commit();
    }

    private static void add1Possibility(String letter, int tapNumber, int number, Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putString("letraSub " + tapNumber + " " + number, letter);

        ed.commit();

    }

    public static void resetSharedPref(int numberOfLettersInPassword, Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.clear();
        ed.commit();

        ed.putInt("NumeroLetras", numberOfLettersInPassword);

        ed.commit();
    }



    public static void storePassword(Counter counter, Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy clave", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();


        ed.putInt("NumeroLetras", counter.size());
        String[] letters = new String[counter.size()];
        Character c;


        for(int i = 0; i< counter.size(); i++){
            c = counter.get(i);
            letters[i] = c.getValue()+" "+c.getColor()+" "+c.getLetterType();

            ed.putString("letra " +i, letters[i]);
        }
        ed.commit();
    }


    public static void signal(Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy senala", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putBoolean("senal", true);
        ed.commit();
        Log.v("Desarrollo", "señalizado "+ isSinalizable(applicationContext));
    }

    public static void notSignal(Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy senala", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putBoolean("senal", false);
        ed.commit();
        Log.v("Desarrollo", "desSeñalizado "+ isSinalizable(applicationContext));
    }

    public static boolean isSinalizable(Context context){

        SharedPreferences sh = context.getSharedPreferences("Entrophy senala", context.MODE_PRIVATE);
        boolean isSingal = sh.getBoolean("senal", true);
        Log.v("Desarrollo", "isSeñala vale "+ isSingal);
        return isSingal;
    }

    public static void signalMovePassword(Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy senala", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putBoolean("moverContraseña", true);
        ed.commit();
        Log.v("Desarrollo", "señalizado isMoverContraseña"+ isSinalizable(applicationContext));
    }

    public static void unSignalMovePassword(Context applicationContext) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy senala", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putBoolean("moverContraseña", false);
        ed.commit();
        Log.v("Desarrollo", "desSeñalizado isMoverContraseña"+ isSinalizable(applicationContext));
    }

    public static boolean isPasswordMoving(Context context) {

        SharedPreferences sh = context.getSharedPreferences("Entrophy senala", context.MODE_PRIVATE);
        boolean isSignal = sh.getBoolean("moverContraseña", true);
        Log.v("Desarrollo", "isMoverContraseña vale "+ isSignal);
        return isSignal;

    }

    public static void setSpeed(Context applicationContext, int velocidad) {

        SharedPreferences sh = applicationContext.getSharedPreferences("Entrophy senala", applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putInt("speed", velocidad);
        ed.commit();
        Log.v("Desarrollo", "fijada speed xSpeed: "+velocidad);
    }

    public static int getSpeed(Context context) {

        SharedPreferences sh = context.getSharedPreferences("Entrophy senala", context.MODE_PRIVATE);
        int speed = sh.getInt("speed", 5);                              //cambiar la por defecto xSpeed 5
        Log.v("Desarrollo", "la speed es: "+ speed);
        return speed;
    }

    public static void setAlphabetsType(String alphabetsType, Context context) {
        SharedPreferences sh = context.getSharedPreferences("Entrophy senala", context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putString("alphabetsType", alphabetsType);
        ed.commit();
        Log.v("Desarrollo", "fijada alphabetsType xSpeed: "+alphabetsType);
    }

    public static String getAlphabetsType(Context context) {

        SharedPreferences sh = context.getSharedPreferences("Entrophy senala", context.MODE_PRIVATE);
        String alphabetsType = sh.getString("alphabetsType", "Dynamic");
        Log.v("Desarrollo", "la alphabetsType es: "+ alphabetsType);
        return alphabetsType;
    }
}
