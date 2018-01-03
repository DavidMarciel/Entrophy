package com.example.david.ent2.Letters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.ent2.Dimens.Dimens;
import com.example.david.ent2.Movements.Move;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by david on 22/07/2014.
 */
public class AlphabetsFactory {             //Factory Method Patron

    static public final int LATIN_ALPHABET = 0;
    static public final int RUSSIAN_ALPHABET = 1;
    static public final int CHINEESE_ALPHABET = 2;
    static public final int ARABIAN_ALPHABET = 3;
    static public final int DEBANAGARI_ALPHABET = 4;
    static public final int HEBREO_ALPHABET = 5;
    static public final int LATIN_LASER_ALPHABET = 6;
    static public final int LATIN_GUSANO_ALPHABET = 7;
    static public final int NUMBERS = 8;
    static public final int LATIN_LATERAL_WIND_ALPHABET = 9;
    static public final int LATIN_INFINITE_BLOCK_ALPHABET = 10;

    private Context context;
    private RelativeLayout relativeLayout;
    private Dimens dimens;

    private Character[] letters;
    private ArrayList<Character> charactersList;


    public AlphabetsFactory(Context context, RelativeLayout relativeLayout) {
        this.context = context;
        this.relativeLayout = relativeLayout;

        Dimens.initMedidas(context);
        dimens = Dimens.getMedidas();
    }

    public Character[] getLetters() {
        charactersList = new ArrayList<Character>();
        letters = initLetters();
        return letters;
    }

    public Character[] getLatinLetters() {

        charactersList = new ArrayList<Character>();

        instanciateLatinLetters();

        letters = lettersAsArray(charactersList);

        return letters;
    }

    public Character[] getStaticLetters() {
        charactersList = new ArrayList<Character>();

        instanciateLatinLetters();

        letters = lettersAsArray(charactersList);

        return letters;
    }

    public Character[] getWorldLetters() {

        charactersList = new ArrayList<Character>();

        createCirilicalLetters( Color.rgb(77,178,250));
        createDevanagariLetters( Color.rgb(250,181,62));
        createHebreanLetters( Color.RED);
        instanciateLaserLetters();
        instanciateSnakeLetters();
        createChineeseLetters( Color.WHITE);


        letters = lettersAsArray(charactersList);

        return letters;
    }

    public Character[] getMixedLetters() {

        charactersList = new ArrayList<Character>();

        //matrix
        createLatinLetters( 'a', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_1, false, 0);
        createLatinLetters( 'A', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, 0);

        //viento lateral
        instanciateLateralWindLetters(Color.BLUE);
        instanciateLateralWindLettersLowerCase(Color.BLUE);


        createCirilicalLetters( Color.rgb(77,178,250));
        createDevanagariLetters( Color.rgb(250,181,62));
        createHebreanLetters( Color.BLACK);

        instanciateLaserLetters();
        instanciateSnakeLetters();


        //letters colores sin mezclar
        //createLatinLetters( 'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_2, true, 0);
        createLatinLetters( 'a', Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_3, true, TypeFaces.AEROLITE_BOLD);

        //createLatinLetters( 'A', Color.rgb(252,147,17), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_4, true, 0);
        createLatinLetters( 'a', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_10, true, TypeFaces.BLKCHCRY);


        //letters colores sin mezclar
        createLatinLetters( 'A', Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_8, true, 0);
        //createLatinLetters( 'xSpeed', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_7, true, TypeFaces.GLORIA_HALLELUJAH);

        createLatinLetters( 'A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true, 0);
        //createLatinLetters( 'xSpeed', Color.rgb(252,147,17), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_9, true, TypeFaces.NEUROPOLITICAL);



        letters = lettersAsArray(charactersList);

        return letters;
    }

    public Character[] getLetters(int type) {

        charactersList = new ArrayList<Character>();

        switch (type){
            case LATIN_ALPHABET:        instanciateLatinLetters();                                          break;
            case RUSSIAN_ALPHABET:      createCirilicalLetters( Color.rgb(77,178,250));    break;
            case CHINEESE_ALPHABET:     createChineeseLetters( Color.LTGRAY);                               break;
            case ARABIAN_ALPHABET:      createArabianLetters( Color.rgb(219,219,4));       break;
            case DEBANAGARI_ALPHABET:   createDevanagariLetters( Color.rgb(250,181,62));   break;
            case HEBREO_ALPHABET:       createHebreanLetters( Color.RED);                                    break;
            case NUMBERS:               instanciateNumbers(Color.rgb(43, 173, 69));        break;
            case LATIN_LASER_ALPHABET:  instanciateLaserLetters();                                          break;
            case LATIN_GUSANO_ALPHABET: instanciateSnakeLetters();                                          break;
            case LATIN_INFINITE_BLOCK_ALPHABET: createInfinityBlockLatinLetters(Color.rgb(255,159,54)); break;
            case LATIN_LATERAL_WIND_ALPHABET:   instanciateLateralWindLetters(Color.BLUE);                  break;

            default:
                                                                break;
        }
        return lettersAsArray(charactersList);
    }

    /**Encargado de llamar xSpeed la generacion de cada tipo de letra
     *
     */
    private Character[] initLetters() {

//        createChineeseLetters(Color.rgb(230,230,230) /*Color.LTGRAY*/);                                  //chinas
//        createCirilicalLetters( Color.rgb(77,178,250));                                                //cirilicas
//        createArabianLetters( Color.rgb(219,219,4));                                                    //arabes
//        createDevanagariLetters( Color.rgb(250,181,62));                                               //devanagari
//        createHebreanLetters(Color.rgb(255,178,102)/*Color.rgb(62, 62, 62)*/);                         //hebreo

        instanciateLatinLetters();                                                                   //latinas

//        instanciateNumbers(Color.rgb(255, 6, 67));


        return lettersAsArray(charactersList);
    }

    private void instanciateNumbers(int color) {

        createNumbers( '0', color, Move.STATIC_MOVE, dimens.LATIN_LETTERS_POSITION_2, true, 0);

    }

    private void instanciateLatinLetters() {

        //matrix
        createLatinLetters( 'a', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_1, false, 0);
        createLatinLetters( 'A', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, 0);

        //viento lateral
        instanciateLateralWindLetters(Color.BLUE);
        instanciateLateralWindLettersLowerCase(Color.BLUE);

        //letters colores sin mezclar
        createLatinLetters( 'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_2, true, 0);
        createLatinLetters( 'a', Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_3, true, TypeFaces.AEROLITE_BOLD);

        createLatinLetters( 'A', Color.rgb(252,147,17), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_4, true, 0);
        createLatinLetters( 'a', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_10, true, TypeFaces.BLKCHCRY);


        //letters colores sin mezclar
        createLatinLetters( 'A', Color.rgb(177, 12, 228), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_8, true, 0);
        createLatinLetters( 'a', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_7, true, TypeFaces.GLORIA_HALLELUJAH);

        createLatinLetters( 'A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true, 0);
        createLatinLetters( 'a', Color.rgb(252,147,17), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_9, true, TypeFaces.NEUROPOLITICAL);

        //fuentes buenas: ARMALITE, OLIVER, NEUROPOLITICAL, BLKCHCRY
   }
                            /*Amarillo Color.rgb(252,147,17)*/
                            /*Amarillo Chllón Color.rgb(252,247,17)*/
                            /*Amarillo oscuro Color.rgb(228, 123, 12)*/
                            /*Naranja Color.rgb(252,147,17)*/
                            /*Morado Color.rgb(177, 12, 228)*/
                            /*Gris claro  Color.rgb(140,140,140)*/
                            /*Gris oscuro Color.rgb(70,70,70)*/

    private void instanciateLaserLetters(){
        createLetters( 'a', 26, Color.rgb(70,70,70), 0, 500, 50, 0, 40, TypeFaces.O4B_03);
    }

    private void instanciateSnakeLetters(){
        createLetters( 'A', 26, Color.rgb(70,70,70), 0, 200, 50, 0, 40, TypeFaces.DEFAULT);
    }

    private void instanciateLateralWindLetters(int c){
        createLetters( 'A', 26, c, Move.LATERAL_WIND_MOVE, 50, 10, 0, 42, TypeFaces.DEFAULT);
    }

    private void instanciateLateralWindLettersLowerCase(int c){
        createLetters( 'a', 26, c, Move.LATERAL_WIND_MOVE, 350, 1080, 0, -42, TypeFaces.O4B_03);
    }

    private void createHebreanLetters(int rgb) {

        Character cr;

        final int FIRST_LETTER = 1488;
        final int NUMBER_OF_LETTERS = 27;// 0xFF;
        final int LETTERS_PER_ROW = 4;// 11; //en realidad son 12
        int x = -1;
        int y = -1;

        for(int i = 0; i< NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

            cr = new Character(value, relativeLayout, context, rgb, 0f,
                    dimens.HEBREAN_POSITION_X + dimens.HEBREAN_POSITION_ADDITION_X * x,
                    dimens.HEBREAN_POSITION_Y + dimens.HEBREAN_POSITION_ADDITION_Y * y,
                    Move.INFINITE_BLOCK_MOVE, 0);
            charactersList.add(cr);
        }

    }

    private void createInfinityBlockLatinLetters(int rgb) {

        Character cr;

        final int FIRST_LETTER = 'A';
        final int NUMBER_OF_LETTERS = 26;// 0xFF;
        final int LETTERS_PER_ROW = 4;// 11; //en realidad son 12
        int x = -1;
        int y = -1;

        for(int i = 0; i< NUMBER_OF_LETTERS; i++){

            char valor = (char)(FIRST_LETTER+i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

            cr = new Character(valor, relativeLayout, context, rgb, 0f,
                    dimens.HEBREAN_POSITION_X + dimens.HEBREAN_POSITION_ADDITION_X * x,
                    dimens.HEBREAN_POSITION_Y + dimens.HEBREAN_POSITION_ADDITION_Y * y,
                    Move.INFINITE_BLOCK_MOVE, 0);
            charactersList.add(cr);
        }
    }

    private void createLatinLetters(int firstLetter, int rgb, int movementType, int xPosition, boolean rigth, int letterType) {

        final int numLetters = 26;
        int yDistance;
        int yPosition;


        if(rigth == true){
            yPosition = dimens.LATIN_LETTERS_POSITION_Y;
            yDistance = dimens.LATIN_LETTERS_ADDITION_Y;
        }
        else{
            //el tamaño es 1050 por eso uso 1025
            // solo se puede ustilizar 950 para que sea compatible con el modo apaisado uso 925
            yPosition = dimens.LATIN_LETTERS_END_POSITION_Y;;
            yDistance = -dimens.LATIN_LETTERS_ADDITION_Y;
        }

        TextView tv;
        Character cr;

        for(int i = 0; i< numLetters; i++){

            char valor = (char)(firstLetter+i);
            cr = new Character(valor, relativeLayout, context, rgb, 0.0f,
                    yPosition + yDistance * i,
                    xPosition,
                    movementType, letterType);

            charactersList.add(cr);
        }
    }

    private void creaLetrasLatinasMixtas(int firstLetter, int rgb, int moveType, int xPosition, boolean right, int letterType,
                                         int firstLetter2, int rgb2, int moveType2, int xPosition2, boolean right2, int letterType2) {

        final int numLetters = 26;
        int yDistance;
        int yPosition;


        if(right == true){
            yPosition = dimens.LATIN_LETTERS_POSITION_Y;
            yDistance = dimens.LATIN_LETTERS_ADDITION_Y;
        }
        else{
            //el tamaño es 1050 por eso uso 1025
            // solo se puede ustilizar 950 para que sea compatible con el modo apaisado uso 925
            yPosition = dimens.LATIN_LETTERS_END_POSITION_Y;;
            yDistance = -dimens.LATIN_LETTERS_ADDITION_Y;
        }

        Character cr;

        for(int i = 0; i< numLetters; i+=2){

            char valor = (char)(firstLetter+i);
            cr = new Character(valor, relativeLayout, context, rgb, 0.0f,
                    yPosition + yDistance * i,
                    xPosition,
                    moveType, letterType);

            charactersList.add(cr);

            char valor2 = (char)(firstLetter2+i+1);
            cr = new Character(valor2, relativeLayout, context, rgb2, 0.0f,
                    yPosition + yDistance * (i+1),
                    xPosition,
                    moveType2, letterType2);

            charactersList.add(cr);
        }

    }

    private void createNumbers(int firstLetter, int rgb, int movementType, int xPosition, boolean right, int letterType) {

            final int numLetters = 10;
            float yDistance;
            int yPosition;


            if(right == true){
                yPosition = dimens.LATIN_LETTERS_POSITION_Y;
                yDistance = dimens.LATIN_LETTERS_ADDITION_Y *2.5f;
            }
            else{
                //el tamaño es 1050 por eso uso 1025
                // solo se puede ustilizar 950 para que sea compatible con el modo apaisado uso 925
                yPosition = dimens.LATIN_LETTERS_END_POSITION_Y + 10;
                yDistance = -dimens.LATIN_LETTERS_ADDITION_Y *2.5f;
            }

            Character cr;

            for(int i = 0; i< numLetters; i++){

                char value = (char)(firstLetter+i);
                cr = new Character(value, relativeLayout, context, rgb, 0f,
                        (float) yPosition + yDistance * i,
                        xPosition,
                        movementType, letterType);

                charactersList.add(cr);
            }
        }

    private void createLetters(int firstLetter, int numLetters, int rgb, int movementType,
                               int positionX, int positionY, int xDistance, int yDistance, int letterType) {

        Character cr;

        for(int i = 0; i< numLetters; i++){

            char value = (char)(firstLetter+i);
            cr = new Character(value, relativeLayout, context, rgb, 0f, positionX + xDistance * i, positionY + yDistance * i, movementType, letterType);

            charactersList.add(cr);
        }
    }


    private void createChineeseLetters(int rgb) {

        //rango caracteres chinos: //rango 4E00-9FFF        son 20991 letters, demasiadas
        final int FIRST_LETTER = 0x4E00;
        final int NUMBER_OF_LETTERS = 0x9FFF-0x4E00;
        final int LETTERS_PER_ROW = 13; // 15;
        int x = -1;
        int y = -1;

        for(int i = 0; i< NUMBER_OF_LETTERS; i += 55){

            char value = (char)(FIRST_LETTER+i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

            charactersList.add(
                    new Character(value, relativeLayout, context, rgb, 0f,
                            (dimens.CHINEESE_LETTERS_POSITION_X + dimens.CHINEESE_LETTERS_ADDITION_X * x)/*(10+ 60 * x)*/,
                            (dimens.CHINEESE_LETTERS_POSITION_Y + dimens.CHINEESE_LETTERS_ADDITION_Y * y)/*(0+ 60 * y)*/,
                            Move.STATIC_MOVE, 0)
            );

        }

    }


    private void createDevanagariLetters(int rgb) {

        //rango caracteres devanagari: 0x904-0x939
        final int FIRST_LETTER = 0x0904;
        final int NUMBER_OF_LETTERS = 0x939-0x904;
        final int LETTERS_PER_ROW = 10;//15;
        int x = -1;
        int y = -1;

        for(int i = 0; i< NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

            charactersList.add(
                    new Character(value, relativeLayout, context, rgb, 0f,
                            (dimens.DEVANAGARI_POSITION_X + dimens.DEVANAGARI_POSITION_ADDITION_X * x),
                            (dimens.DEVANAGARI_POSITION_Y + dimens.DEVANAGARI_POSITION_ADDITION_Y * y),
                            Move.VERTICAL_BOUNCE_MOVE, 0)
            );
        }

    }


    private void createArabianLetters(int rgb) {

        //rangos xSpeed crear: 0x622-0x623, 0x628-0x63A, 0x641-0x64A

        int color = rgb;
        int LETTERS_PER_ROW = 3 -1; //en realidad son 4
        int x = 0;
        int y = 0;
        int i = 0;

        Character cr;

        //rangos xSpeed crear: 0x622-0x623
        int FIRST_LETTER = 0x0622;
        int NUMBER_OF_LETTERS = 0x0623- FIRST_LETTER;

        for(; i<= NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);

            cr = getCaracterArabe(color, x, y, value);
            charactersList.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;
        }

        //rangos xSpeed crear: 0x628-0x63A
        FIRST_LETTER = 0x0628;
        NUMBER_OF_LETTERS = 0x063B-FIRST_LETTER;

        for(i = 0; i< NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);
            cr = getCaracterArabe(color, x, y, value);
            charactersList.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

        }

        //rangos xSpeed crear: 0x641-0x64A
        final int FIRST_LETTER2 = 0x0641;
        final int NUMBER_OF_LETTERS2 = 0x0658-FIRST_LETTER;

        for(i = 0; i< 10; i++){     //son solo 10 letters

            char value = (char)(FIRST_LETTER2+i);
            cr = getCaracterArabe(color, x, y, value);
            charactersList.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

        }

        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;
        if (x < LETTERS_PER_ROW) x++;
        else x = 0;
        if(x == 0) y++;

        //rangos xSpeed crear: 0x641-0x64A
        final int FIRST_LETTER3 = 0x0660;
        final int NUMBER_OF_LETTERS3 = 0x066A-FIRST_LETTER3;

        for(i = 0; i< NUMBER_OF_LETTERS3; i++){     //son solo 10 letters

            char value = (char)(FIRST_LETTER3+i);
            cr = getCaracterArabe(color, x, y, value);
            charactersList.add(cr);

            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

        }

    }

    private Character getCaracterArabe(int color, int x, int y, char value) {
        Character cr;
        cr = new Character(value, relativeLayout, context, color, 0f,
                dimens.ARABIAN_POSITION_X + dimens.ARABIAN_POSITION_ADDITION_X * x,
                dimens.ARABIAN_POSITION_Y + dimens.ARABIAN_POSITION_ADDITION_Y * y,
                Move.HORIZONTAL_BLOCK_BOUNCE_MOVE, 0);
        return cr;
    }

    private void createCirilicalLetters(int rgb) {

        Character cr;

        final int FIRST_LETTER = 1040;
        final int NUMBER_OF_LETTERS = 64;// 0xFF;
        final int LETTERS_PER_ROW = 10;// 11; //en realidad son 12
        int x = -1;
        int y = -1;

        for(int i = 0; i< NUMBER_OF_LETTERS; i++){

            char value = (char)(FIRST_LETTER+i);
            if (x < LETTERS_PER_ROW) x++;
            else x = 0;
            if(x == 0) y++;

            cr = new Character(value, relativeLayout, context, rgb, 0f,
                    dimens.CIRILICAl_POSITION_X + dimens.CIRILICAL_POSITION_ADDITION_X * x,
                    dimens.CIRILICAl_POSITION_Y + dimens.CIRILICAL_POSITION_ADDITION_Y * y,
                    Move.VERTICAL_BLOCK_BOUNCE_MOVE, 0);
            charactersList.add(cr);
        }
    }

    private Character[] lettersAsArray(ArrayList<Character> letterList) {
        Character[] aux = new Character[letterList.size()];
        letterList.toArray(aux);
        return aux;
    }

    //codigo de depuracion con una letra
    private void initLetter(ArrayList<Character> letters, RelativeLayout relativeLayout) {
        final int LETRAS_1 = 1;

        TextView tv;
        Character cr;

        for(int i = 0; i< LETRAS_1; i++){

            char value = (char)('A'+i);
            cr = new Character(value, relativeLayout, context, Color.BLUE, 0f, i,100,
                    Move.LINE_MOVE, 0);

            Toast.makeText(context, cr.printInfo(), Toast.LENGTH_SHORT).show();
            letters.add(cr);
            Toast.makeText(context, cr.printInfo(), Toast.LENGTH_SHORT).show();
        }
    }

    private void logTime(int call) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss.SSSZ");
        String date = df.format(Calendar.getInstance().getTime());
        Log.v("tiempo", "tiempo en instanciador: " + date.toString() + " llamada "+ call +"\n");
    }

}

//version 1.11
//
//        creaLetrasLatinas( 'A', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_2, true, 0);
//        creaLetrasLatinas('xSpeed', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_1, false, 0);                      //matrix minusculas verde oscuro
//
//        creaLetrasLatinas( 'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_3, true, 0);        //mayusculas negras arriba
//        creaLetrasLatinas( 'xSpeed', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true, 0);       //minusculas negras medio-bajo
//
//        creaLetrasLatinas( 'A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_4, true, 0);     //mayusculas rojas medio
//        creaLetrasLatinas( 'xSpeed', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, 0);      //minusculas rojas medio


//version 2.1
//
//        creaLetrasLatinas( 'A', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_2, true, 0);
//        creaLetrasLatinas('xSpeed', Color.rgb(5, 252, 55), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_1, false, 0);                      //matrix minusculas verde oscuro
//
//        creaLetrasLatinas( 'A', Color.rgb(200,200,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_8, true, TypeFaces.O4B_20);
//        creaLetrasLatinas( 'A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, TypeFaces.GLORIA_HALLELUJAH);     //mayusculas rojas medio
//        creaLetrasLatinas( 'xSpeed', Color.rgb(0,128,255), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_7, false, TypeFaces.O4B_03);      //minusculas verde azulado medio-arriba
//        creaLetrasLatinas( 'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true, TypeFaces.JANVIER_LIGHT);        //mayusculas negras arriba



//colores mezclados
//        creaLetrasLatinasMixtas( 'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_3, true, 0,
//                'xSpeed', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, TypeFaces.O4B_03);        //mayusculas negras arriba
//
//        creaLetrasLatinasMixtas( 'xSpeed', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, TypeFaces.O4B_03,
//                'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_3, true, 0);        //mayusculas negras arriba
//
//        creaLetrasLatinasMixtas('A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_4, true, 0,
//                'xSpeed', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true, TypeFaces.GLORIA_HALLELUJAH );        //mayusculas negras arriba
//
//        creaLetrasLatinasMixtas('xSpeed', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true, TypeFaces.GLORIA_HALLELUJAH,
//                'A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_4, true, 0);        //mayusculas negras arriba



//        //colores iniciales
//        creaLetrasLatinas( 'xSpeed', Color.rgb(225,225,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_12, false, TypeFaces.O4B_20);       //minusculas negras medio-bajo
//        creaLetrasLatinas( 'xSpeed', Color.rgb(204, 0, 204), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_10, false, TypeFaces.O4B_03);      //minusculas rosas medio-bajo
////        creaLetrasLatinas('xSpeed', Color.rgb(43, 173, 69), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_1, false);                      //matrix minusculas verde oscuro
////        creaLetrasLatinas( 'A', Color.rgb(5, 252, 55), Move.MATRIX_MOVE, dimens.LATIN_LETTERS_POSITION_2, true);                      //matrix mayusculas verde claro
//        creaLetrasLatinas( 'A', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_3, true, TypeFaces.JANVIER);        //mayusculas negras arriba
////        creaLetrasLatinas( 'A', Color.rgb(204, 0, 204), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_4, true);        //mayusculas rosas arriba
//        creaLetrasLatinas( 'A', Color.rgb(0,204,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true,TypeFaces.COMICATE);       //mayusculas verde azulado medio-arriba
////        creaLetrasLatinas( 'A', Color.rgb(200,200,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_5, true);       //mayusculas verde azulado medio-arriba
//        creaLetrasLatinas( 'xSpeed', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_6, true, TypeFaces.DEFAULT);      //minusculas rojas medio
////        creaLetrasLatinas( 'A', Color.rgb(255,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_7, false);     //mayusculas rojas medio
//        creaLetrasLatinas( 'xSpeed', Color.rgb(0,128,255), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_8, false, TypeFaces.LE_FUTUR_ATENDRA);      //minusculas verde azulado medio-arriba
////        creaLetrasLatinas( 'xSpeed', Color.rgb(0,0,0), Move.LINE_BOUNCE_MOVE, dimens.LATIN_LETTERS_POSITION_9, false);       //minusculas negras medio-bajo
