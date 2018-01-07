package com.example.david.ent2.Views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.ent2.AsyncLoop;
import com.example.david.ent2.Storage.DataStorage;
import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Letters.AlphabetsFactory;
import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.Counter;
import com.example.david.ent2.Letters.TypeFaces;
import com.example.david.ent2.Handlers.Handler;
import com.example.david.ent2.Handlers.LettersHandlerFactory;
import com.example.david.ent2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by david on 31/08/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PasswordChange extends Activity {

    private static final int NUMBER_OF_PASSWORD_LETTERS = 5;

    private boolean activeBackground;
    private RelativeLayout relativeLayout;
    private Character[] letters;
    private Handler[] handlers;
    private Counter counter;
    private TextView[] selectedLetters;
    private Button newLetterButton;
    private CheckBox moveLettersInPasswordChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showTime(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        activeBackground = false;
        relativeLayout = findViewById(R.id.lLayoutCambiaContrasena);
        selectedLetters = new TextView[NUMBER_OF_PASSWORD_LETTERS];

        showTime(2);
        newLetterButton =  findViewById(R.id.LLatinas);

        moveLettersInPasswordChange = findViewById(R.id.moverEnContraseña);
        updateSignal(getApplicationContext());
        Dimens.getDimens(getApplicationContext());

        letters = new Character[0];

        showTime(3);
        counter = DataStorage.getPassword(getApplicationContext());
        updateTextViews(counter);
        counter = new Counter(NUMBER_OF_PASSWORD_LETTERS);
        showTime(4);

        if(counter.size() == 0){
            Toast.makeText(getApplicationContext(), "Por favor, establezca una contraseña", Toast.LENGTH_LONG).show();
        }

    }

    private AsyncLoop asyncLoop;

    public void tapHandler(View v) {

        String alphabetsType = DataStorage.getAlphabetsType(this);

        AlphabetsFactory alphabetsFactory = new AlphabetsFactory(getApplicationContext(), relativeLayout);

        switch (alphabetsType){
            case "Dynamic": letters = alphabetsFactory.getLatinLetters(); break;
            case "Static": letters = alphabetsFactory.getStaticLetters(); break;
            case "World Alphabets": letters = alphabetsFactory.getWorldLetters(); break;
            case "Mixed": letters = alphabetsFactory.getMixedLetters(); break;
        }

        LettersHandlerFactory im = new LettersHandlerFactory(this, relativeLayout);
        handlers = im.getHandlers();

        stopLoop();
        asyncLoop = new AsyncLoop(letters, handlers, getApplicationContext());

        if(DataStorage.isPasswordMoving(getApplicationContext())) {
            if(asyncLoop != null) {
                asyncLoop.setInitialStop(500);
                asyncLoop.execute();
            }
        }

        updateOrientationPortraitLandscape(getApplication().getResources().getConfiguration());  //inicializa la orientacion

        disablebuttons();
    }



    @Override
    protected void onPause() {
        super.onPause();
        stopLoop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.v("Desarrollo", "tapHandler en fondo");

        stopLoop();

        //para evitar las pulsaciones en el fondo de pantalla que cuelgan la interfaz utilizamos activeBackground
        if(!activeBackground) {
            return false;
        }
        else {

            if (!validTap()) return true;

            Character c = showSelectedLetters(event);

            if (c == null) {
                return true;
            }
            else if (counter.add(c)) {                           //si se puede añadir
                cleanElements();
                updateTextViews(counter);
                enableButtons();
            }
            if (counter.full()) {                          //ya se ha elegido la clave
                cleanElements();
                disablebuttons();
                stopLoop();
                updateTextViews(counter);
                DataStorage.storePassword(counter, getApplicationContext());                                                                           //necesario implementar
            }
            return true;
        }
    }

    private void updateTextViews(Counter counter) {

        for (int i = 0; i < counter.size(); i++) {
            generateSelectedLabels(counter.get(i), i);
        }
    }


    private Character showSelectedLetters(MotionEvent event) {

        ArrayList<Character> selected = new ArrayList<>();
        //String s = "";

        if (letters != null) {

            //recoge las posibles
            selected.addAll( gatherCloseLetters(event));

            //recoge las posibles en handlers
            selected.addAll( gatherCloseLettersInHandlers(event));

            if(selected.size()<1) return null;

            letters = new Character[selected.size()];
            selected.toArray(letters);

            relativeLayout.removeAllViews();

            //si solo hay una seleccionada es la respuesta
            if (selected.size() == 1) {
                return selected.get(0);
            }
            // si hay varias seleccionadas se expanden
            else if (selected.size() > 1) {
                Log.d("numero de letters", letters.length + "");
                recolocateLetters(letters);
            }
        }
        return null;
    }

    private ArrayList<Character> gatherCloseLettersInHandlers(MotionEvent event) {

        ArrayList<Character> closeLetters = new ArrayList<>();

        if(handlers != null) {
            for (int i = 0; i < handlers.length; i++) {

                if (handlers[i] != null) {
                    ArrayList<Character> ac = handlers[i].tap(event);
                    closeLetters.addAll(ac);
                    handlers[i] = null;
                }
            }
        }
        return closeLetters;
    }

    private ArrayList<Character> gatherCloseLetters(MotionEvent event) {

        ArrayList<Character> closeLetters = new ArrayList<>();

        for (Character letter : letters) {

            if (letter.inRange(event)) {
                closeLetters.add(letter);
            }
        }
        return closeLetters;
    }

    private void stopLoop() {
        if(asyncLoop != null){
            asyncLoop.cancel(true);
        }
    }

    private void recolocateLetters(Character[] selected) {

        Log.d("numero de letras2", selected.length + "");

        //double distance = Dimens.MAXIMUM_RANGE_DISTANCE /2-1;
        double xMax = Dimens.X_MAX_SCREEN_SIZE;
        double xMin = Dimens.X_MIN_SCREEN_SIZE + 150f;
        //double yMax = Dimens.Y_MAX_SCREEN_SIZE;
        double yMin = Dimens.Y_MIN_SCREEN_SIZE + 150f;
        double margen = 100;

        float usefulLength = (float) (xMax - xMin);
        int lettersPerRow = (int) (usefulLength/ (margen+6d));
        int x = -1;
        int y = -1;

        for (Character aSelected : selected) {

            if (x < lettersPerRow) x++;
            else x = 0;
            if (x == 0) y++;

            aSelected.setX((float) ((x * margen) % usefulLength) + (float) xMin);
            aSelected.setY((float) (y * margen + yMin));

            Log.d("Letra en posicion: ", aSelected.getValue() + " " + aSelected.getX() + " " + aSelected.getY());
            aSelected.showIn(relativeLayout);
        }
    }

    private void generateSelectedLabels(Character c, int x) {
        selectedLetters[x] = new TextView(getApplicationContext());
        selectedLetters[x].setX(200 + (x * 60));
        selectedLetters[x].setY(650);
        selectedLetters[x].setText(c.getValue() + "");
        selectedLetters[x].setTextSize(25f);
        selectedLetters[x].setTextColor(c.getColor());
        selectedLetters[x].setTypeface(TypeFaces.getTipeFace(getApplicationContext(), c.getLetterType()), Typeface.BOLD);
        relativeLayout.addView(selectedLetters[x]);
    }

    private void hideCounter() {

        for (int i = 0; i < NUMBER_OF_PASSWORD_LETTERS; i++) {
            relativeLayout.removeView(selectedLetters[i]);
        }
    }

    private void disablebuttons() {

        relativeLayout.removeView(newLetterButton);

        hideCounter();
        enableBackgroundTaps(true);
    }

    private void enableBackgroundTaps(boolean ebt) {
        activeBackground = ebt;
    }


    private void enableButtons() {

        relativeLayout.addView(newLetterButton);
        enableBackgroundTaps(false);

        relativeLayout.addView(moveLettersInPasswordChange);
    }

    private void cleanElements() {
        letters = null;
        handlers = null;
        relativeLayout.removeAllViews();
    }

    /**
     * salta al girar la pantalla (entre otros)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateOrientationPortraitLandscape(newConfig);
    }

    private void updateOrientationPortraitLandscape(Configuration configuration) {

        boolean isOrientacionVertical;// = Character.ORIENTACION_VERTICAL;

        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {            //esta de pie
            isOrientacionVertical = true;
        } else {                                                                   //esta tumbado
            isOrientacionVertical = false;
        }

        Character.setIsOrientationVertical(isOrientacionVertical, getApplicationContext());
        updateCharacteres();
        updateHandlers();
    }

    /**
     * Actualiza la pantalla al cambiarla xSpeed apaisado
     */
    private void updateCharacteres() {
        if (letters != null) {
            for (Character letter : letters) {
                letter.update();
            }
        }
    }

    private void updateHandlers() {
        for (Handler handler : handlers) {
            handler.update();
        }
    }

    private void initItems() {
        relativeLayout.setPadding(0, 0, 0, 0);
        updateOrientationPortraitLandscape(getApplication().getResources().getConfiguration());  //inicializa la orientacion
    }

    private void showTime(int llamada) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss.SSSZ");
        String date = df.format(Calendar.getInstance().getTime());
        Log.v("tiempo", "tiempo en setContrasena: " + date + " llamada " + llamada + "\n");
    }


    private long lastTapTime;

    public boolean validTap() {

        //espera entre pulsaciones
        if (lastTapTime + 150 < getTime()) {

            lastTapTime = getTime();
            return true;
        }
        else{
            lastTapTime = getTime();
            return false;
        }
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public void notifyMove(View view){

        if(moveLettersInPasswordChange.isChecked()){
            DataStorage.signalMovePassword(getApplicationContext());

            asyncLoop = new AsyncLoop(letters, handlers, getApplicationContext());
            if(DataStorage.isPasswordMoving(getApplicationContext())) {
                if(asyncLoop != null) {
                    asyncLoop.setInitialStop(500);
                    asyncLoop.execute();
                }
            }
        }else{
            DataStorage.unSignalMovePassword(getApplicationContext());
            if(asyncLoop != null) {
                asyncLoop.cancel(true);
            }
        }



        Log.v("Desarrollo", "metodo tapSignal entra, valor del checkbox = " + moveLettersInPasswordChange.isChecked());
    }

    private void updateSignal(Context applicationContext) {

        if(DataStorage.isPasswordMoving(applicationContext)){
            moveLettersInPasswordChange.setChecked(true);
        }
        else{
            moveLettersInPasswordChange.setChecked(false);
        }

    }
}
