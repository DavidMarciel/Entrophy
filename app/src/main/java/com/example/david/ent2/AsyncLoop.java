package com.example.david.ent2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Handlers.Handler;

/**
 * Created by david on 19/05/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AsyncLoop extends AsyncTask<Object, Object, Object> {

    private Character[] letters;
    private int time;
    private int lastTapTime;
    private int initialStop;
    private Handler[] handler;
    int speed;

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

        Log.v("Desarrollo", "primera tap hecha");
        if(initialStop != 0) {
            waiting(initialStop, "fallo en la waiting inicial");
        }

        //bucle del programa
        while (true) {

            waiting(speed, "fallo en el bucleAsincrono");   //para letters sueltas

            //bucle cancelado
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

            //move las letters
            if (letters != null) {
                for (int i = 0; i < letters.length; i++) {
                    letters[i].move();
                }
            }

            //move los manejadores
            if (handler != null) {
                for (int i = 0; i < handler.length; i++) {
                    if (handler[i] != null)
                        handler[i].move();
                }
            }

//            Log.d("move", "move");

            //update el time
            time++;
        }
    }

    private void waiting(int tiempoMilis, String mensaje) {
        try {
            Thread.sleep(tiempoMilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.v("Fallo", mensaje);
        }
    }

    public int getTime() {
        return time;
    }


    public boolean validTap() {

        //waiting entre pulsaciones
        if (lastTapTime + 3 < getTime()) {

            Log.v("time", "time: " +
                    lastTapTime + " " +
                            getTime() + " " +
                            (lastTapTime - getTime()) +
                            "\n"
            );

            lastTapTime = getTime();
            return true;
        }
        else{
            lastTapTime = getTime();
            return false;
        }
    }

    public void setInitialStop(int initialStop) {
        this.initialStop = initialStop;
    }

}
