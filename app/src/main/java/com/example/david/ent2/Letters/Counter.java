package com.example.david.ent2.Letters;

/**
 * Created by david on 01/09/2014.
 */
public class Counter {

    private Character[] letters;
    private int counter;
    private int numberOfLetters;

    public Counter(int numberOfLetters){
        letters = new Character[numberOfLetters];
        this.numberOfLetters = numberOfLetters;
        counter = 0;
    }

    public boolean add(Character c){
        if(  c == null
          || counter >= numberOfLetters){
            return false;
        }
        else{
            letters[counter] = c;
            counter++;
            return true;
        }
    }

    public Character get(int e){
        if(e <= size())
            return letters[e];
        else return null;
    }

    public int size() {
        return Math.min(counter, numberOfLetters);
    }

    public boolean full() {
        if (counter >= numberOfLetters) return true;
        else return false;
    }

}
