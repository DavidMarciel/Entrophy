package com.example.david.ent2.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.ent2.DataStorage;
import com.example.david.ent2.R;


/**
 * Created by david on 31/08/2014.
 */
public class Launcher extends AppCompatActivity {

    private Intent iEnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);

        initSignalCheckbox(getApplicationContext());
        initAlphabetsSpinner(getApplicationContext());
        initSeekBar();

        iEnt = new Intent(getApplicationContext(), Entrophy.class);
        iEnt.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(iEnt);
    }


    private void initAlphabetsSpinner(Context applicationContext) {
        Spinner alphabetsType = (Spinner) findViewById(R.id.alphabetsSpinner);

        initializeAlphabetsSpinnerScreenValue(applicationContext, alphabetsType);

        alphabetsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                String alphabetsType = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(adapterView.getContext(),"Alphabets selected : " + alphabetsType, Toast.LENGTH_SHORT).show();

                DataStorage.setAlphabetsType(alphabetsType, getApplicationContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initializeAlphabetsSpinnerScreenValue(Context applicationContext, Spinner alphabetsType) {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.alphabetTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String choosedAlphabet = DataStorage.getAlphabetsType(applicationContext);
        int spinnerPosition = adapter.getPosition(choosedAlphabet);
        alphabetsType.setSelection(spinnerPosition);
    }

    private void initSeekBar() {
        SeekBar sb = (SeekBar) findViewById(R.id.seekBar);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int speed = seekBar.getProgress();

                updateSpeedLabel(speed);

                Log.v("Velocidad", "Velocidad: "+speed);
                DataStorage.setSpeed(getApplicationContext(), speed);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb.setProgress(DataStorage.getSpeed(getApplicationContext()));
    }

    private void updateSpeedLabel(int speed) {
        TextView tv = (TextView) findViewById(R.id.etiquetaVelocidad);
        tv.setText(""+speed);
    }

    private void initSignalCheckbox(Context applicationContext) {
        CheckBox checkboxSignal = (CheckBox) findViewById(R.id.señal);
        if(DataStorage.isSinalizable(applicationContext)){
            checkboxSignal.setChecked(true);
        }
        else{
            checkboxSignal.setChecked(false);
        }

    }

    public void tapHandler(View v){

        if(R.id.Entrophy == v.getId()){
            iEnt.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(iEnt);
        }
        if(R.id.SetPassword == v.getId()){
            Intent i = new Intent(getApplicationContext(), PasswordChange.class);
            startActivity(i);
        }
        if(R.id.Result == v.getId()){
            Intent i = new Intent(getApplicationContext(), Result.class);
            startActivity(i);
        }

    }

    public void tapSignal(View v){

        CheckBox signal = (CheckBox) v;

        if(signal.isChecked()){
            DataStorage.signal(getApplicationContext());
        }else{
            DataStorage.notSignal(getApplicationContext());
        }

        Log.v("Desarrollo", "metodo tapSignal entra, valor del checkbox = " +signal.isChecked());

    }


}
