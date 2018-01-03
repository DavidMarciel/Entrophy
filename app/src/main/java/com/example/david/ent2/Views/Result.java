package com.example.david.ent2.Views;

import android.annotation.TargetApi;
import android.app.Activity;
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
    private Character[][] itemsPerTap;
    private Dimens dimens;
    private long time;
    private int hits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        dimens = Dimens.getMedidas();
        hits = 0;
        //extrae informacion
        password = DataStorage.getPassword(getApplicationContext());
        itemsPerTap = DataStorage.getPossibilities(getApplicationContext());
        time = DataStorage.getElapsedTime(getApplicationContext());

        updateTextViews(password, itemsPerTap, time);
        boolean hitted = isCorrect();
        showTime(hitted, time);
    }

    private RelativeLayout relativeLayout;
    private TextView[] tvKey;         //password
    private TextView[][] tvItemsPerTap;    //pulsados
    private TextView[] tvNumber;        //super índice
    private TextView[] tvHits;       //acertados
    private TextView tvTime;       //acertados
    private TextView tvContraseñaAcertada; //password acertada

    private void updateTextViews(Counter counter, Character[][] itemsPerTap, long time) {

        relativeLayout = (RelativeLayout) findViewById(R.id.resRelativeLayout);

        //password
        if (password != null) {
            tvKey = new TextView[counter.size()];
            for (int i = 0; i < counter.size(); i++) {
                showPassword(counter.get(i), i);
            }
        }

        //pulsadas
        if (itemsPerTap != null) {
            tvItemsPerTap = new TextView[itemsPerTap.length][];
            tvHits = new TextView[itemsPerTap.length];

            for (int i = 0; i < itemsPerTap.length; i++) {

                if (itemsPerTap[i] != null) {
                    tvItemsPerTap[i] = new TextView[itemsPerTap[i].length];

                    for (int j = 0; j < itemsPerTap[i].length; j++) {

                        if (itemsPerTap[i][j] != null) {
                            if (itemsPerTap[i][j].equals(password.get(i))) {
                                showHit(itemsPerTap[i][j], i, j);
                                signalHit(i);
                                hits++;
                            }
                            showTap(itemsPerTap[i][j], i, j);
                        }
                    }
                }
            }
        }

        if (itemsPerTap != null) {
            for (int position = 0; position < itemsPerTap.length; position++) {
                tvNumber = new TextView[itemsPerTap.length];

                if (itemsPerTap[position] != null && itemsPerTap[position].length > 0) {
                    showNumber(itemsPerTap[position].length, position);
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
        tvTime = new TextView(getApplicationContext());
        tvTime.setX(dimens.KEY_RESULT_POSITION_X);
        tvTime.setY(dimens.KEY_RESULT_POSITION_Y - 60);

        if(correct) {
            tvTime.setText("Contraseña correcta (" + (((float) time) / 1000) + " s)");
            tvTime.setTextSize(dimens.KEY_RESULT_LETTERS_SIZE * 0.75f);
            tvTime.setTextColor(Color.rgb(43, 173, 69)/*verde*/);
        }
        else{
            tvTime.setText("Contraseña incorrecta (" + (((float) time) / 1000) + " s)");
            tvTime.setTextSize(dimens.KEY_RESULT_LETTERS_SIZE * 0.75f);
            tvTime.setTextColor(Color.RED);
        }


        relativeLayout.addView(tvTime);
    }

    private void showPassword(Character c, int x) {
        tvKey[x] = new TextView(getApplicationContext());
        tvKey[x].setX(dimens.KEY_RESULT_POSITION_X + x * dimens.KEY_RESULT_POSITION_ADDITION_X);
        tvKey[x].setY(dimens.KEY_RESULT_POSITION_Y);
        tvKey[x].setText(c.getValue() + "");
        tvKey[x].setTextSize(dimens.KEY_RESULT_LETTERS_SIZE);
        tvKey[x].setTextColor(c.getColor());
        tvKey[x].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvKey[x]);
    }

    private void showTap(Character c, int x, int y) {
        tvItemsPerTap[x][y] = new TextView(getApplicationContext());

        tvItemsPerTap[x][y].setX(dimens.RESULT_LETTERS_POSITION_X + x * dimens.RESULT_LETTERS_POSITION_ADDITION_X);
        tvItemsPerTap[x][y].setY(dimens.RESULT_LETTERS_POSITION_Y + y * dimens.RESULT_LETTERS_POSITION_ADDITION_Y);
        tvItemsPerTap[x][y].setText(c.getValue() + "");
        tvItemsPerTap[x][y].setTextSize(dimens.RESULT_LETTERS_SIZE);
        tvItemsPerTap[x][y].setTextColor(c.getColor());
        tvItemsPerTap[x][y].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvItemsPerTap[x][y]);
    }

    private void showHit(Character c, int x, int y) {
        tvItemsPerTap[x][y] = new TextView(getApplicationContext());

        tvItemsPerTap[x][y].setX(dimens.RESULT_LETTERS_HIT_POSITION_X + dimens.RESULT_LETTERS_HIT_POSITION_ADDITION_X * x);
        tvItemsPerTap[x][y].setY(dimens.RESULT_LETTERS_HIT_POSITION_Y + dimens.RESULT_LETTERS_HIT_POSITION_ADDITION_Y * y);
        tvItemsPerTap[x][y].setText(c.getValue() + "");
        tvItemsPerTap[x][y].setTextSize(dimens.RESULT_LETTERS_HIT_SIZE);
        tvItemsPerTap[x][y].setTextColor(c.getColor());
        tvItemsPerTap[x][y].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(tvItemsPerTap[x][y]);
    }

    private void showNumber(int cuantos, int x) {
        tvNumber[x] = new TextView(getApplicationContext());
        tvNumber[x].setX(dimens.KEY_RESULT_POSITION_X
                + dimens.KEY_RESULT_LETTERS_SIZE
                + x * dimens.KEY_RESULT_POSITION_ADDITION_X);
        tvNumber[x].setY(dimens.KEY_RESULT_POSITION_Y);
        tvNumber[x].setText("  " + cuantos + "");
        tvNumber[x].setTextSize(dimens.KEY_RESULT_LETTERS_SIZE / 2);
        tvNumber[x].setTextColor(Color.BLACK);
        relativeLayout.addView(tvNumber[x]);
    }

    private void signalHit(int x) {
        tvHits[x] = new TextView(getApplicationContext());
        tvHits[x].setX(dimens.KEY_RESULT_POSITION_X
                + dimens.KEY_RESULT_LETTERS_SIZE
                + x * dimens.KEY_RESULT_POSITION_ADDITION_X);
        tvHits[x].setY(dimens.KEY_RESULT_POSITION_Y
                + dimens.KEY_RESULT_LETTERS_SIZE);
        tvHits[x].setText("  A");
        tvHits[x].setTextSize(dimens.KEY_RESULT_LETTERS_SIZE / 2);
        tvHits[x].setTextColor(Color.RED);
        relativeLayout.addView(tvHits[x]);
    }

}
