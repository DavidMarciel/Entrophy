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

import com.example.david.ent2.DataStorage;
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

        boolean correct = false;

        if(hits == password.size()){
            correct = true;
        }
        else{
            correct = false;
        }
        return correct;
    }

    private void showTime(boolean correct, long time) {
        TextView tvTime = new TextView(getApplicationContext());
        tvTime.setX(Dimens.KEY_RESULT_POSITION_X);
        tvTime.setY(Dimens.KEY_RESULT_POSITION_Y - 60);

        Resources resources = getResources();

        if(correct) {
            String labelAndTime = resources.getString( R.string.rightPassword, (((float) time) / 1000));
            tvTime.setText( labelAndTime);
            tvTime.setTextSize(Dimens.KEY_RESULT_LETTERS_SIZE * 0.75f);
            tvTime.setTextColor(Color.rgb(43, 173, 69)/*verde*/);
        }
        else{
            String labelAndTime = resources.getString( R.string.wrongPassword, (((float) time) / 1000));
            tvTime.setText( labelAndTime);
            tvTime.setTextSize(Dimens.KEY_RESULT_LETTERS_SIZE * 0.75f);
            tvTime.setTextColor(Color.RED);
        }

        relativeLayout.addView(tvTime);
    }

    private void printPasswordCharacter(Character c, int x) {
        tvPassword[x] = new TextView(getApplicationContext());
        tvPassword[x].setX(Dimens.KEY_RESULT_POSITION_X + x * Dimens.KEY_RESULT_POSITION_ADDITION_X);
        tvPassword[x].setY(Dimens.KEY_RESULT_POSITION_Y);
        tvPassword[x].setText( String.valueOf(c.getValue()));
        tvPassword[x].setTextSize(Dimens.KEY_RESULT_LETTERS_SIZE);
        tvPassword[x].setTextColor(c.getColor());
        tvPassword[x].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvPassword[x]);
    }

    private void showTap(Character c, int x, int y) {
        tvItemsPerTap[x][y] = new TextView(getApplicationContext());

        tvItemsPerTap[x][y].setX(Dimens.RESULT_LETTERS_POSITION_X + x * Dimens.RESULT_LETTERS_POSITION_ADDITION_X);
        tvItemsPerTap[x][y].setY(Dimens.RESULT_LETTERS_POSITION_Y + y * Dimens.RESULT_LETTERS_POSITION_ADDITION_Y);
        tvItemsPerTap[x][y].setText( String.valueOf(c.getValue()));
        tvItemsPerTap[x][y].setTextSize(Dimens.RESULT_LETTERS_SIZE);
        tvItemsPerTap[x][y].setTextColor(c.getColor());
        tvItemsPerTap[x][y].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvItemsPerTap[x][y]);
    }

    private void showHit(Character c, int x, int y) {
        tvItemsPerTap[x][y] = new TextView(getApplicationContext());

        tvItemsPerTap[x][y].setX(Dimens.RESULT_LETTERS_HIT_POSITION_X + Dimens.RESULT_LETTERS_HIT_POSITION_ADDITION_X * x);
        tvItemsPerTap[x][y].setY(Dimens.RESULT_LETTERS_HIT_POSITION_Y + Dimens.RESULT_LETTERS_HIT_POSITION_ADDITION_Y * y);
        tvItemsPerTap[x][y].setText( String.valueOf(c.getValue()));
        tvItemsPerTap[x][y].setTextSize(Dimens.RESULT_LETTERS_HIT_SIZE);
        tvItemsPerTap[x][y].setTextColor(c.getColor());
        tvItemsPerTap[x][y].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvItemsPerTap[x][y]);
    }

    private void showNumber(int cuantos, int x) {
        numberOfSelectedItems[x] = new TextView(getApplicationContext());
        numberOfSelectedItems[x].setX(Dimens.KEY_RESULT_POSITION_X
                + Dimens.KEY_RESULT_LETTERS_SIZE
                + x * Dimens.KEY_RESULT_POSITION_ADDITION_X);
        numberOfSelectedItems[x].setY(Dimens.KEY_RESULT_POSITION_Y);
        numberOfSelectedItems[x].setText("  " + cuantos);
        numberOfSelectedItems[x].setTextSize(Dimens.KEY_RESULT_LETTERS_SIZE / 2);
        numberOfSelectedItems[x].setTextColor(Color.BLACK);
        relativeLayout.addView(numberOfSelectedItems[x]);
    }

    private void signalHit(int x) {
        tvHits[x] = new TextView(getApplicationContext());
        tvHits[x].setX(Dimens.KEY_RESULT_POSITION_X
                + Dimens.KEY_RESULT_LETTERS_SIZE
                + x * Dimens.KEY_RESULT_POSITION_ADDITION_X);
        tvHits[x].setY(Dimens.KEY_RESULT_POSITION_Y
                + Dimens.KEY_RESULT_LETTERS_SIZE);
        tvHits[x].setText("  A");
        tvHits[x].setTextSize(Dimens.KEY_RESULT_LETTERS_SIZE / 2);
        tvHits[x].setTextColor(Color.RED);
        relativeLayout.addView(tvHits[x]);
    }

}
