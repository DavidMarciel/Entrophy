package com.example.david.ent2.Handlers;

import android.content.Context;
import android.widget.RelativeLayout;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Letters.AlphabetsFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by david on 22/07/2014.
 */
public class LettersHandlerFactory {

    private AlphabetsFactory alphabetsFactory;


    public LettersHandlerFactory(Context context, RelativeLayout relativeLayout) {

        alphabetsFactory = new AlphabetsFactory(context, relativeLayout);
    }

    public Handler[] getHandlers() {

        Character[] letters;          //array auxiliar que contiene las letters
        ArrayList<Handler> handlers = new ArrayList<>();

        letters = alphabetsFactory.getLetters(AlphabetsFactory.LATIN_LASER_ALPHABET);
        Collections.reverse(Arrays.asList(letters));
        handlers.add(new HandlerLaser(letters));

        letters = alphabetsFactory.getLetters(AlphabetsFactory.LATIN_GUSANO_ALPHABET);
        Collections.reverse(Arrays.asList(letters));
        handlers.add(new SnakeHandler(letters));

//        letters = alphabetsFactory.getLetters(AlphabetsFactory.LATIN_INFINITE_BLOCK_ALPHABET);
//        losManejadores.add(new InfiniteBlockHandler(letters));


        Handler[] handlers1 = new Handler[handlers.size()];
        handlers.toArray(handlers1);

        return handlers1;
    }

}
