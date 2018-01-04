package com.example.david.ent2.Handlers;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.david.ent2.Letters.Character;

/**
 * Created by david on 11/06/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class InfiniteBlockHandler extends Handler {

    public InfiniteBlockHandler(Character[] letras) {
        super(letras);
        concreteInitialization();
    }

    @Override
    public int getType() {
        return INFINITE_BLOCK_HANDLER;
    }

    @Override
    protected void concreteInitialization() {
        initSpeeds(MAXIMUM_SPEED);
        initPoints();
    }

    @Override
    public void move() {
        for (Character letter : letters) {
            letter.move();
        }
    }

}