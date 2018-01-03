package com.example.david.ent2.Views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
public class Response extends Activity {

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
        boolean hitted = correct();
        showTime(hitted, time);
    }

    private RelativeLayout relativeLayout;
    private TextView tvTime;       //acertados
    private TextView tvContraseñaAcertada; //password acertada

    private void updateTextViews(Counter counter, Character[][] itemsPerTap, long time) {

        relativeLayout = (RelativeLayout) findViewById(R.id.resRelativeLayout);

        //pulsadas
        if (itemsPerTap != null) {

            for (int i = 0; i < itemsPerTap.length; i++) {
                if (itemsPerTap[i] != null) {

                    for (int j = 0; j < itemsPerTap[i].length; j++) {
                        if (itemsPerTap[i][j] != null) {
                            if (itemsPerTap[i][j].equals(password.get(i))) {
                                hits++;
                            }
                        }
                    }

                }
            }

        }
    }

    private boolean correct() {

        boolean correct = false;

        if(hits == password.size()){
            correct = true;
        }
        else{
            correct = false;
        }

        return correct;
    }

    private void showTime(boolean acertado, long tiempo) {
        tvTime = new TextView(getApplicationContext());
        tvTime.setX(dimens.KEY_RESULT_POSITION_X);
        tvTime.setY(dimens.KEY_RESULT_POSITION_Y - 60);

        if(acertado) {
            tvTime.setText("\n\nAcceso\n\nconcedido.\n\n(" + (((float) tiempo) / 1000) + " s)");
            tvTime.setTextSize(dimens.KEY_RESULT_LETTERS_SIZE * 0.75f);
            tvTime.setTextColor(Color.rgb(43, 173, 69)/*verde*/);
        }
        else{
            tvTime.setText("\n\nAcceso\n\ndenegado.\n\n(" + (((float) tiempo) / 1000) + " s)");
            tvTime.setTextSize(dimens.KEY_RESULT_LETTERS_SIZE * 0.75f);
            tvTime.setTextColor(Color.RED);
        }


        Typeface letterType = TypeFaces.getTipeFace(getApplicationContext(), 4);
        tvTime.setTypeface(letterType);
        tvTime.setGravity(View.TEXT_ALIGNMENT_CENTER);

        relativeLayout.addView(tvTime);
    }

}
