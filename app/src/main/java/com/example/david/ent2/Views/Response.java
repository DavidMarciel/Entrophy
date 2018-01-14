package com.example.david.ent2.Views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
public class Response extends Activity {

    private RelativeLayout relativeLayout;
    private Counter password;
    private int hits;
    private Dimens dimens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        //dimens = Dimens.getMedidas();
        hits = 0;
        //extrae informacion
        password = DataStorage.getPassword(getApplicationContext());
        Character[][] itemsPerTap = DataStorage.getPossibilities(getApplicationContext());
        long time = DataStorage.getElapsedTime(getApplicationContext());
        dimens = Dimens.getDimens(getApplicationContext());

        updateTextViews(itemsPerTap);
        boolean hitted = correct();
        showTime(hitted, time);
    }

    private void updateTextViews(Character[][] itemsPerTap) {

        relativeLayout = findViewById(R.id.resRelativeLayout);

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

        return hits == password.size();
    }

    private void showTime(boolean acertado, long time) {
        TextView timeLabel = new TextView(getApplicationContext());
        timeLabel.setX(dimens.getKeyResultPositionX());
        timeLabel.setY(dimens.getKeyResultPositionY() - 60);

        Resources resources = getResources();

        if(acertado) {
            String labelAndTime = resources.getString( R.string.GrantAccess, (((float) time) / 1000));
            timeLabel.setText( labelAndTime);
            timeLabel.setTextSize(dimens.getKeyResultLettersSize() /* * 0.75f*/);
            timeLabel.setTextColor(Color.rgb(43, 173, 69)/*verde*/);
        }
        else{
            String labelAndTime = resources.getString( R.string.DenyAccess, (((float) time) / 1000));
            timeLabel.setText( labelAndTime);
            timeLabel.setTextSize(dimens.getKeyResultLettersSize() /* * 0.75f*/);
            timeLabel.setTextColor(Color.RED);
        }


//        Typeface letterType = TypeFaces.getTipeFace(getApplicationContext(), 4);
//        timeLabel.setTypeface(letterType);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            timeLabel.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }

        relativeLayout.addView(timeLabel);
    }

}
