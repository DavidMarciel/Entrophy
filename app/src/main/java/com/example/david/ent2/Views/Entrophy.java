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

import com.example.david.ent2.Storage.DataStorage;
import com.example.david.ent2.AsyncLoop;
import com.example.david.ent2.Letters.AlphabetsFactory;
import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Handlers.Handler;
import com.example.david.ent2.Handlers.LettersHandlerFactory;
import com.example.david.ent2.R;
import com.example.david.ent2.Storage.SelectedLeters;

import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Entrophy extends Activity {

    private AsyncLoop asyncLoop;

    private TextView starTapViewer;                      //textView que muestra las pulsaciones hechas
    private Character[] letters = new Character[0];            //array que contiene las letters
    private Handler[] handlers;                        //array que contiene los handlers

    private int tapNumber = 0;
    private boolean firstTap = true;
    private SelectedLeters selectedLeters;
    private boolean isSignalizable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getIntent().setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setBackgroundColor();
    }

    private void setBackgroundColor() {
        RelativeLayout relativeLayout =  findViewById(R.id.rLayout);
        relativeLayout.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Entrophy", "onResume Entrophy starts");
        initItems();
        Log.v("Entrophy", "onResume Entrophy after initItems");
        initAsyncLoop(this.letters, this.handlers);
        Log.v("Entrophy", "onResume Entrophy ends");
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

            //si no hay contraseña se pide
            if (isPasswordEmpty()) {
                askToSetPassword();
            }

            //reinicia pulsadas
            selectedLeters.restartStorageTaps(getApplicationContext());
            selectedLeters.setStartTime( System.currentTimeMillis());

            //inicia movimiento
            asyncLoop.execute();
        }

        //el resto de pulsaciones son clicks
        //solo si no es el mismo click repetido
        else if (asyncLoop.isValidTap()) {

            updateTapCounterViewer(tapNumber + 1);

            ArrayList<Character> selectedsThisTap;// = new ArrayList<Character>();

            selectedsThisTap = getSelectedLetters(event);

            //almacena pulsados
            tapNumber++;
            selectedLeters.addTapLetters(tapNumber, selectedsThisTap);

            //lanza "Result.class" con la respuesta
            if (lastTap()) {

                selectedLeters.storeInformation( getApplicationContext());
                end();

                //lanza la pantalla Result
//                Intent i = new Intent(getApplicationContext(), Result.class);
//                startActivity(i);

                //Lanza Response
                Intent i = new Intent(getApplicationContext(), Response.class);
                startActivity(i);
            }
        }
    }

    private ArrayList<Character> getSelectedLetters(MotionEvent event) {
        ArrayList<Character> selectedsThisTap;
        Log.v("Desarrollo", "Señalar= " + DataStorage.isSignalizable(getApplicationContext()));

        if (isSignalizable) {
            selectedsThisTap = findAndSignalSelectedLetters(event);
        } else {
            selectedsThisTap = findSelectedLetters(event);
        }
        return selectedsThisTap;
    }

    private boolean lastTap() {
        return tapNumber > selectedLeters.getPasswordLength() -1; // password.size() - 1;
    }

    private void askToSetPassword() {
        Intent iEnt = new Intent(getApplicationContext(), PasswordChange.class);
        iEnt.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(iEnt);
    }

    private boolean isPasswordEmpty() {
        return selectedLeters.getPasswordLength() == 0;
    }

    private ArrayList<Character> findAndSignalSelectedLetters(MotionEvent event) {

        ArrayList<Character> selectedsThisTap = new ArrayList<>();
        //señala letters
        if (letters != null) {
            for (Character letter : letters) {

                if (letter.inRange(event)) {

                    letter.selected();
                    selectedsThisTap.add(letter);
                }
                else {
                    letter.notSelected();
                }
            }
        }
        //señala letters en manejador
        if (handlers != null) {
            for (Handler handler : handlers) {
                selectedsThisTap.addAll( handler.tapAndSignal(event));
            }
        }
        return selectedsThisTap;
    }

    private ArrayList<Character> findSelectedLetters(MotionEvent event) {

        ArrayList<Character> selectedsThisTap = new ArrayList<>();

        //añade letters pulsadas
        for (Character letter : letters) {

            if (letter.inRange(event)) {
                selectedsThisTap.add(letter);
            }
        }
        //añade letters pulsadas en manejador
        if (handlers != null) {
            for (Handler handler : handlers) {
                selectedsThisTap.addAll( handler.tap(event));
            }
        }
        return selectedsThisTap;
    }

    private void updateTapCounterViewer(int tapIndex) {
        StringBuilder taps = new StringBuilder();
        for (int i = 0; i < tapIndex; i++) {
            taps.append(" *");
        }
        starTapViewer.setText(taps);
    }

    private void end() {
        stopMoving();
    }

    private void initItems() {

        Log.v("Entrophy", "initItems starts");
        RelativeLayout relativeLayout = findViewById(R.id.rLayout);
        relativeLayout.setPadding(0, 0, 0, 0); //marcar el borde del area que se esta usando

        updatePortraitLandscapeOrientation(getApplication().getResources().getConfiguration());  //inicializa la orientacion

        letters = initLetters( relativeLayout);
        handlers = initHandlers( relativeLayout);
        selectedLeters = new SelectedLeters( getApplicationContext());
        isSignalizable = DataStorage.isSignalizable(getApplicationContext());

        generateSelectedLabels( relativeLayout);
    }

    private Handler[] initHandlers(RelativeLayout relativeLayout) {

        //usando los handlers
        LettersHandlerFactory lettersHandlerFactory = new LettersHandlerFactory(getApplicationContext(), relativeLayout);
        Handler[] theHandlers = lettersHandlerFactory.getHandlers();

        Log.v("Entrophy", "initItems after handlers");
        Log.v("handlers.length", ""+theHandlers.length);

        return theHandlers;
    }

    private Character[] initLetters(RelativeLayout relativeLayout) {

        String alphabetsType = DataStorage.getAlphabetsType(this);
        Log.v("Entrophy", "Alphabet Type"+ alphabetsType);

        //usando un instanciador de letters
        AlphabetsFactory alphabetsFactory = new AlphabetsFactory(getApplicationContext(), relativeLayout);

        Character[] theLetters = null;

        switch (alphabetsType){
            case "Dynamic": theLetters = alphabetsFactory.getLatinLetters(); break;
            case "Static": theLetters = alphabetsFactory.getStaticLetters(); break;
            case "World Alphabets": theLetters = alphabetsFactory.getWorldLetters(); break;
            case "Mixed": theLetters = alphabetsFactory.getMixedLetters(); break;
        }
        Log.v("Entrophy", "Alphabet Type created");

        return theLetters;
    }

    private void generateSelectedLabels(RelativeLayout relativeLayout) {

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

        for (Character letter : letters) {
            letter.update();
        }
    }

    private void updateHandlers() {
        for (Handler handler : handlers) {
            handler.update();
        }
    }

    private void clearItems() {
        letters = new Character[0];

        RelativeLayout relativeLayout = findViewById(R.id.rLayout);
        relativeLayout.removeAllViews();
    }

    /**
     * Inicia el movimiento de las letters
     * @param letters array of letters to move
     * @param handlers array of handlers to move
     */
    private void initAsyncLoop(Character[] letters, Handler[] handlers) {
        Log.v("Entrophy", "initAsyncLoop starts");
        asyncLoop = null;
        asyncLoop = new AsyncLoop(letters, handlers, getApplicationContext());
        Log.v("Entrophy", "initAsyncLoop ends");
    }


    private void updatePortraitLandscapeOrientation(Configuration newConfig) {
        boolean isVerticalyOriented;// = Character.ORIENTACION_VERTICAL;

        Log.v("Entrophy", "updatePortraitLandscapeOrientation starts");

        if (newConfig.orientation == newConfig.ORIENTATION_PORTRAIT) {            //esta de pie
            isVerticalyOriented = true;
        } else {                                                                   //esta tumbado
            isVerticalyOriented = false;
        }

        Character.setIsOrientationVertical(isVerticalyOriented, getApplicationContext());

        try{
            updateCharacteres();
        }catch (Exception e){ }
        try{
            updateHandlers();
        }catch (Exception e){ }

        Log.v("Entrophy", "updatePortraitLandscapeOrientation ends");
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