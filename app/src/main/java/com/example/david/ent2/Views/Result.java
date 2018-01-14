package com.example.david.ent2.Views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.ent2.Storage.DataStorage;
import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.Counter;
import com.example.david.ent2.Letters.TypeFaces;
import com.example.david.ent2.R;


/**
 * Created by david on 02/09/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Result extends Activity {

    private Counter password;
    private int hits;

    private RelativeLayout relativeLayout;
    private TextView[] tvPassword;         //password
    private TextView[][] tvItemsPerTap;    //pulsados
    private TextView[] numberOfSelectedItems;        //super índice
    private TextView[] tvHits;       //acertados
    private Dimens dimens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        relativeLayout = findViewById(R.id.resRelativeLayout);

        hits = 0;
        //extrae informacion
        password = DataStorage.getPassword(getApplicationContext());
        Character[][] itemsPerTap = DataStorage.getPossibilities(getApplicationContext());
        long time = DataStorage.getElapsedTime(getApplicationContext());
        dimens = Dimens.getDimens(getApplicationContext());

        updateTextViews(password, itemsPerTap);
        boolean hitted = isCorrect();
        showTime(hitted, time);
    }


    private void updateTextViews(Counter counter, Character[][] itemsPerTap) {

        printPassword(counter);

        printTaps(itemsPerTap);

        printNumberOfItemsPerTab(itemsPerTap);
    }

    private void printNumberOfItemsPerTab(Character[][] itemsPerTap) {
        if (itemsPerTap != null) {
            for (int position = 0; position < itemsPerTap.length; position++) {
                numberOfSelectedItems = new TextView[itemsPerTap.length];

                if (itemsPerTap[position] != null && itemsPerTap[position].length > 0) {
                    showNumber(itemsPerTap[position].length, position);
                }
            }
        }
    }

    private void printTaps(Character[][] itemsPerTap) {
        if (itemsPerTap != null) {
            tvItemsPerTap = new TextView[itemsPerTap.length][];
            tvHits = new TextView[itemsPerTap.length];

            for (int i = 0; i < itemsPerTap.length; i++) {

                printPossibleItemsForThisTap(itemsPerTap[i], i);
            }
        }
    }

    private void printPassword(Counter counter) {
        if (password != null) {
            tvPassword = new TextView[counter.size()];
            for (int i = 0; i < counter.size(); i++) {
                printPasswordCharacter(counter.get(i), i);
            }
        }
    }

    private void printPossibleItemsForThisTap(Character[] possibleCharacters, int i) {

        if (possibleCharacters != null) {
            tvItemsPerTap[i] = new TextView[possibleCharacters.length];

            for (int j = 0; j < possibleCharacters.length; j++) {

                if (possibleCharacters[j] != null) {

                    //if the character is the correct (if hits)
                    if (possibleCharacters[j].equals(password.get(i))) {

                        showHit(possibleCharacters[j], i, j);
                        signalHit(i);
                        hits++;
                    }
                    showTap(possibleCharacters[j], i, j);
                }
            }
        }
    }

    private boolean isCorrect() {

        return hits == password.size();
    }

    private void showTime(boolean correct, long time) {
        TextView tvTime = new TextView(getApplicationContext());
        tvTime.setX(dimens.getKeyResultPositionX());
        tvTime.setY(dimens.getKeyResultPositionY() - 60);

        Resources resources = getResources();
        String labelAndTime;

        if(correct) {
            labelAndTime = resources.getString( R.string.rightPassword, (((float) time) / 1000));
            tvTime.setText( labelAndTime);
            tvTime.setTextSize(dimens.getKeyResultLettersSize() /* * 0.75f*/);
            tvTime.setTextColor(Color.rgb(43, 173, 69)/*verde*/);
        }
        else{
            labelAndTime = resources.getString( R.string.wrongPassword, (((float) time) / 1000));
            tvTime.setText( labelAndTime);
            tvTime.setTextSize(dimens.getKeyResultLettersSize() /* * 0.75f*/);
            tvTime.setTextColor(Color.RED);
        }

        relativeLayout.addView(tvTime);
    }

    private void printPasswordCharacter(Character c, int x) {
        tvPassword[x] = new TextView(getApplicationContext());
        tvPassword[x].setX(dimens.getKeyResultPositionX() + x * dimens.getKeyResultPositionAdditionX());
        tvPassword[x].setY(dimens.getKeyResultPositionY());
        tvPassword[x].setText( String.valueOf(c.getValue()));
        tvPassword[x].setTextSize(dimens.getKeyResultLettersSize());
        tvPassword[x].setTextColor(c.getColor());
        tvPassword[x].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvPassword[x]);
    }

    private void showTap(Character c, int x, int y) {
        tvItemsPerTap[x][y] = new TextView(getApplicationContext());

        tvItemsPerTap[x][y].setX(dimens.getResultLettersPositionX() + x * dimens.getResultLettersPositionAdditionX());
        tvItemsPerTap[x][y].setY(dimens.getResultLettersPositionY() + y * dimens.getResultLettersPositionAdditionY());
        tvItemsPerTap[x][y].setText( String.valueOf(c.getValue()));
        tvItemsPerTap[x][y].setTextSize(dimens.getResultLettersSize());
        tvItemsPerTap[x][y].setTextColor(c.getColor());
        tvItemsPerTap[x][y].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvItemsPerTap[x][y]);
    }

    private void showHit(Character c, int x, int y) {
        tvItemsPerTap[x][y] = new TextView(getApplicationContext());

        tvItemsPerTap[x][y].setX(dimens.getResultLettersHitPositionX() + dimens.getResultLettersHitPositionAdditionX() * x);
        tvItemsPerTap[x][y].setY(dimens.getResultLettersHitPositionY() + dimens.getResultLettersHitPositionAdditionY() * y);
        tvItemsPerTap[x][y].setText( String.valueOf(c.getValue()));
        tvItemsPerTap[x][y].setTextSize(dimens.getResultLettersHitSize());
        tvItemsPerTap[x][y].setTextColor(c.getColor());
        tvItemsPerTap[x][y].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvItemsPerTap[x][y]);
    }

    private void showNumber(int numberOfElements, int x) {
        numberOfSelectedItems[x] = new TextView(getApplicationContext());
        numberOfSelectedItems[x].setX(dimens.getKeyResultPositionX()
                + dimens.getKeyResultLettersSize()
                + x * dimens.getKeyResultPositionAdditionX());
        numberOfSelectedItems[x].setY(dimens.getKeyResultPositionY());
        numberOfSelectedItems[x].setText("  " + numberOfElements);
        numberOfSelectedItems[x].setTextSize(dimens.getKeyResultLettersSize() / 2);
        numberOfSelectedItems[x].setTextColor(Color.BLACK);
        relativeLayout.addView(numberOfSelectedItems[x]);
    }

    private void signalHit(int x) {
        tvHits[x] = new TextView(getApplicationContext());
        tvHits[x].setX(dimens.getKeyResultPositionX()
                + dimens.getKeyResultLettersSize()
                + x * dimens.getKeyResultPositionAdditionX());
        tvHits[x].setY(dimens.getKeyResultPositionY()
                + dimens.getKeyResultLettersSize());
        tvHits[x].setText("  A");
        tvHits[x].setTextSize(dimens.getKeyResultLettersSize() / 2);
        tvHits[x].setTextColor(Color.RED);
        relativeLayout.addView(tvHits[x]);
    }

}
