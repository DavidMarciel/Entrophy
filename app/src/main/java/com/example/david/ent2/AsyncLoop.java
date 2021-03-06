package com.example.david.ent2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Handlers.Handler;
import com.example.david.ent2.Storage.DataStorage;

/**This is the main loop, it moves the letters and handlers on the screen
 * Created by david on 19/05/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AsyncLoop extends AsyncTask<Object, Object, Object> {

    private static final String DEVELOPMENT = "Desarrollo";
    private Character[] letters;
    private Handler[] handler;
    private int time;
    private int initialStop;
    private int lastTapTime;
    private int speed;

    public AsyncLoop(Character[] letters, Handler[] handler, Context context) {
        this.letters = letters;
        this.handler = handler;
        time = 0;
        initialStop = 0;
        lastTapTime = 0;

        speed = DataStorage.getSpeed(context);
        if(speed != 0) {
//            speed = 57 * 5 / speed;
            speed = 32 * 5 / speed;
        }
    }

    @Override
    protected Object doInBackground(Object... params) {

        Log.v(DEVELOPMENT, "primera tap hecha");
        if(initialStop != 0) {
            waiting(initialStop, "fallo en la waiting inicial");
        }

        //bucle del programa
        while (true) {

            waiting(speed, "fallo en el bucleAsincrono");   //para letters sueltas

            if (isCancelled()) {
                break;
            }
            else{
                publishProgress();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... params) {
        super.onProgressUpdate();

        if(!isCancelled()) {

            move();

            // update time, this time is not the overall time, it is a tick counter,
            // it adds one each time the loop executes
            time++;
        }
    }

    private void move() {

        //move letters
        if (letters != null) {
            for (Character letter : letters) {
                letter.move();
            }
        }

        //move handlers
        if (handler != null) {
            for (Handler aHandler : handler) {
                if (aHandler != null)
                    aHandler.move();
            }
        }
    }

    private void waiting(int tiempoMilis, String mensaje) {
        try {
            Thread.sleep(tiempoMilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.v(DEVELOPMENT, "Fallo " + mensaje);
        }
    }

    public int getTime() {
        return time;
    }


    public boolean isValidTap() {

        //waiting entre pulsaciones
        if (isADifferentTap()) {

            Log.v(DEVELOPMENT, "time" + lastTapTime + " " + getTime() + " " + (lastTapTime - getTime()) + "\n" );

            lastTapTime = getTime();
            return true;
        }
        else{
            lastTapTime = getTime();
            return false;
        }
    }

    private boolean isADifferentTap() {
        return lastTapTime + 3 < getTime();
    }

    public void setInitialStop(int initialStop) {
        this.initialStop = initialStop;
    }

}
