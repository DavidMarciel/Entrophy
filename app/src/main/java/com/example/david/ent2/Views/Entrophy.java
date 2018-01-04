package com.example.david.ent2.Views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.ent2.DataStorage;
import com.example.david.ent2.AsyncLoop;
import com.example.david.ent2.Letters.AlphabetsFactory;
import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.Counter;
import com.example.david.ent2.Handlers.Handler;
import com.example.david.ent2.Handlers.LettersHandlerFactory;
import com.example.david.ent2.R;

import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Entrophy extends Activity {

    private RelativeLayout relativeLayout;
    private static AsyncLoop asyncLoop;

    private TextView starTapViewer;                      //textView que muestra las pulsaciones hechas
    private Character[] letters = new Character[0];            //array que contiene las letters
    private Handler[] handlers;                        //array que contiene los handlers
    private Counter password;

    private int tapNumber;
    private boolean firstTap = true;
    private ArrayList<Character>[] hasBeenSelected;

    private long startTime, endTime;                   //para calcular el tiempo tardado


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getIntent().setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        RelativeLayout relativeLayout =  findViewById(R.id.rLayout);
        relativeLayout.setBackgroundColor(Color.LTGRAY);

        tapNumber = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initItems();
        initAsyncLoop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMoving();
        clearItems();
    }

    private void stopMoving() {
        asyncLoop.cancel(false);
    }

    /**
     * salta al girar la pantalla (entre otros)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        updatePortraitLandscapeOrientation(newConfig);
    }

    /**
     * salta al pulsar la pantala
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tapScreen(event);
        return true;
    }


    private void tapScreen(MotionEvent event) {

        //la primera tapScreen inicia el bucle
        if (firstTap) {
            firstTap = false;

            //reinicia pulsadas
            DataStorage.resetSharedPref(password.size(), getApplicationContext());

            //si no hay contraseña se pide
            if (isPasswordEmpty()) {
                askToSetPassword();
            }
            //si hay contraseña continúa inicida el movimiento y las letras guardadas
            else {
                hasBeenSelected = new ArrayList[password.size()];
            }

            //inicia movimiento
            startTime = System.currentTimeMillis();
            asyncLoop.execute();
        }

        //el resto de pulsaciones son clicks
        //solo si no es el mismo click repetido
        else if (asyncLoop.isValidTap()) {

            updateTapCounter(tapNumber + 1);

            hasBeenSelected[tapNumber] = new ArrayList<Character>();

            Log.v("Desarrollo", "Señalar= " + DataStorage.isSignalizable(getApplicationContext()));

            if (DataStorage.isSignalizable(getApplicationContext())) {

                addAndSignalSelectedLetters(event);

            } else {
                findSelectedLetters(event);
            }

            //almacena pulsados y lanza "Result.class"
            tapNumber++;
            if (tapNumber > password.size() - 1) {

                storeInformation();

                //lanza la pantalla Result
//                Intent i = new Intent(getApplicationContext(), Result.class);
//                startActivity(i);

                //Lanza Response
                Intent i = new Intent(getApplicationContext(), Response.class);
                startActivity(i);
            }

        }
    }

    private void storeInformation() {
        endTime = System.currentTimeMillis();
        Log.v("Fin", "Fin de BucleAsincrono " + " tapNumber " + (tapNumber + 1) + " tamaño contraseña " + (password.size() - 1));
        end();
        DataStorage.savePossibilities(hasBeenSelected, getApplicationContext());
        DataStorage.saveElapsedTime(endTime - startTime, getApplicationContext());
    }

    private void askToSetPassword() {
        Intent iEnt = new Intent(getApplicationContext(), PasswordChange.class);
        iEnt.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(iEnt);
    }

    private boolean isPasswordEmpty() {
        return password.size() == 0;
    }

    private void addAndSignalSelectedLetters(MotionEvent event) {
        //señala letters
        if (letters != null) {
            for (Character letter : letters) {

                if (letter.inRange(event)) {

                    letter.selected();
                    hasBeenSelected[tapNumber].add(letter);
                }
                else {
                    letter.notSelected();
                }
            }
        }
        //señala letters en manejador
        if (handlers != null) {
            for (Handler handler : handlers) {
                handler.tapAndSignal(event, tapNumber, hasBeenSelected);
            }
        }
    }

    private void findSelectedLetters(MotionEvent event) {
        //añade letters pulsadas
        for (Character letter : letters) {

            if (letter.inRange(event)) {
                hasBeenSelected[tapNumber].add(letter);
            }
        }
        //añade letters pulsadas en manejador
        if (handlers != null) {
            for (Handler handler : handlers) {
                handler.tap(event, tapNumber, hasBeenSelected);
            }
        }
    }

    private void updateTapCounter(int tapIndex) {
        String taps = "";
        for (int i = 0; i < tapIndex; i++) {
            taps += " *";
        }
        starTapViewer.setText(taps);
    }

    private void end() {
        stopMoving();
    }

    private void initItems() {

        relativeLayout = (RelativeLayout) findViewById(R.id.rLayout);
        relativeLayout.setPadding(0, 0, 0, 0); //marcar el borde del area que se esta usando

        String alphabetsType = DataStorage.getAlphabetsType(this);

        //usando un instanciador de letters
        AlphabetsFactory alphabetsFactory = new AlphabetsFactory(getApplicationContext(), relativeLayout);

        switch (alphabetsType){
            case "Dynamic": letters = alphabetsFactory.getLatinLetters(); break;
            case "Static": letters = alphabetsFactory.getStaticLetters(); break;
            case "World Alphabets": letters = alphabetsFactory.getWorldLetters(); break;
            case "Mixed": letters = alphabetsFactory.getMixedLetters(); break;
        }


        //usando los handlers
        LettersHandlerFactory lettersHandlerFactory = new LettersHandlerFactory(getApplicationContext(), relativeLayout);
        handlers = lettersHandlerFactory.getHandlers();

        updatePortraitLandscapeOrientation(getApplication().getResources().getConfiguration());  //inicializa la orientacion

        password = DataStorage.getPassword(getApplicationContext());

        generateSelectedLabels();

    }

    private void generateSelectedLabels() {

        starTapViewer = new TextView(getApplicationContext());
        starTapViewer.setTextColor(Color.GRAY);
        starTapViewer.setX(10);
        starTapViewer.setY(20);
//        starTapViewer.setText("A!1");
        starTapViewer.setTextSize(40);
        relativeLayout.addView(starTapViewer);

    }

    /**
     * Actualiza la pantalla al cambiarla xSpeed apaisado
     */
    private void updateCharacteres() {

        for (int i = 0; i < letters.length; i++) {
            letters[i].update();
        }
    }

    private void updateHandlers() {
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].update();
        }
    }

    private void clearItems() {
        letters = new Character[0];
        relativeLayout.removeAllViews();
    }

    /**
     * Inicia el movimiento de las letters
     */
    private void initAsyncLoop() {
        asyncLoop = null;
        asyncLoop = new AsyncLoop(this.letters, this.handlers, getApplicationContext());
    }


    private void updatePortraitLandscapeOrientation(Configuration newConfig) {
        boolean isVerticalyOriented;// = Character.ORIENTACION_VERTICAL;

        if (newConfig.orientation == newConfig.ORIENTATION_PORTRAIT) {            //esta de pie
            isVerticalyOriented = true;
        } else {                                                                   //esta tumbado
            isVerticalyOriented = false;
        }

        Character.setIsOrientationVertical(isVerticalyOriented, getApplicationContext());
        updateCharacteres();
        updateHandlers();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void screenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(this, "Dimensiones de la pantalla: x=" + width + " y= " + height, Toast.LENGTH_LONG).show();
    }

    private void undo() {

        if (tapNumber == 0) {
            finish();
        } else tapNumber--;

        updateTapCounter(tapNumber);
    }

//    /**Muestra las letters seleccionadas en el textview de arriba
//     * Solo para desarrollo
//     */
//    private String muestraLetrasSeleccionadas(MotionEvent event) {
//
//        if (asyncLoop.isValidTap()) {                           //solo si no es el mismo click repetido
////            pulsadas = "";
//
//            for (int i = 0; i < letters.length; i++) {
//
//                if (letters[i].inRange(event)) {
//                    letters[i].selected();
////                    pulsadas += " " + letters[i].getValue();
//                } else {
//                    letters[i].notSelected();
//                }
//            }
////            pulsadas = estadistica( event, pulsadas, asyncLoop.getElapsedTime());         //metodo que mide la viabilidad del proyecto
//        }
//        return null;
//    }

}