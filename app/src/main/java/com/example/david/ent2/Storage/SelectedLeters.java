package com.example.david.ent2.Storage;

import android.content.Context;
import android.util.Log;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.Counter;

import java.util.ArrayList;

/**This class support with the use of letters and taps in views
 * Created by david on 07/01/2018.
 */

public class SelectedLeters {

    private long startTime;
    private ArrayList<ArrayList<Character>> selectedLettersPerTap;
    private int passwordLenght = -1;

    public SelectedLeters(Context context) {
        selectedLettersPerTap = new ArrayList<>();
        passwordLenght = getPasswordLength(context);
    }

    public void restartStorageTaps(Context context){
            DataStorage.resetStoredTapValues(passwordLenght, context);
    }

    public void addTapLetters(int tapNumber, ArrayList<Character> letters){
        selectedLettersPerTap.add(tapNumber -1, letters);
    }

    private int getPasswordLength(Context context) {

        Counter thePassword = DataStorage.getPassword(context);

        passwordLenght = thePassword.size();

        Log.v("password.size()", "" + passwordLenght);

        return passwordLenght;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void storeInformation(Context context) {

        long endTime = System.currentTimeMillis();
        Log.v("Fin", "Fin de BucleAsincrono " + /*" tapNumber " + (tapNumber + 1) + */
                " tamaño contraseña " + (passwordLenght - 1));
        DataStorage.savePossibilities(selectedLettersPerTap, context);
        DataStorage.saveElapsedTime(endTime - startTime, context);
    }

    public int getPasswordLength() {
        return passwordLenght;
    }
}
