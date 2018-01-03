package com.example.david.ent2.Letters;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by david on 15/03/2015.
 */
public class TypeFaces {

    private static boolean initializated = false;

    static final int DEFAULT = 0;
    static final int COMICATE           = 1;
    static final int JANVIER            = 2;
    static final int LE_FUTUR_ATENDRA   = 3;
    static final int O4B_20             = 4;
    static final int O4B_03             = 5;
    static final int JANVIER_LIGHT      = 6;
    static final int CALIGSTROY         = 7;
    static final int GLORIA_HALLELUJAH  = 8;
    static final int LES_JOURS_HEUREUX  = 9;
    static final int PATAQUES           = 10;
    static final int PATAQUES_BRUSH     = 11;
    static final int QUAND_TU_DORS      = 12;
    static final int ARMALITE           = 13;
    static final int OLIVER             = 14;
    static final int ATRACTWOMEN        = 15;
    static final int BLKCHCRY           = 16;
    static final int NEUROPOLITICAL     = 17;
    static final int OCTIN_STENCIL_RG   = 18;
    static final int RM_TYPERIGHTER     = 19;
    static final int AEROLITE_BOLD      = 20;

    private static Typeface byDefault;
    private static Typeface comicate;
    private static Typeface janvier;
    private static Typeface letraLeFuturAtendra;
    private static Typeface o4b_20;
    private static Typeface o4b_03;
    private static Typeface janvierLight;
    private static Typeface caligstroy;
    private static Typeface gloriaHallelujah;
    private static Typeface lesJousHereux;
    private static Typeface pataques;
    private static Typeface pataquesBrush;
    private static Typeface quandTuDors;
    private static Typeface armalite;
    private static Typeface oliver;
    private static Typeface atractWomen;
    private static Typeface blkChCry;
    private static Typeface neuroPolitical;
    private static Typeface octinStencilRg;
    private static Typeface rmTypeRighter;
    private static Typeface aeroliteBold;



    private static void initTypefaces(Context c) {

        initializated = true;

        byDefault = null;
        letraLeFuturAtendra = Typeface.createFromAsset(c.getAssets(),"fonts/LeFuturAttendra.otf");
        comicate = Typeface.createFromAsset(c.getAssets(),"fonts/comicate.ttf");
        janvier = Typeface.createFromAsset(c.getAssets(),"fonts/24Janvier.otf");

        o4b_20 = Typeface.createFromAsset(c.getAssets(),"fonts/04B_20__.ttf");
        o4b_03 = Typeface.createFromAsset(c.getAssets(),"fonts/04B_03__.ttf");
        janvierLight = Typeface.createFromAsset(c.getAssets(),"fonts/24Janvier-Light.otf");
        caligstroy = Typeface.createFromAsset(c.getAssets(),"fonts/Caligstroy.ttf");
        gloriaHallelujah = Typeface.createFromAsset(c.getAssets(),"fonts/gloriahallelujah.ttf");
        lesJousHereux = Typeface.createFromAsset(c.getAssets(),"fonts/LesJoursHeureux.otf");
        pataques = Typeface.createFromAsset(c.getAssets(),"fonts/Pataques.ttf");
        pataquesBrush = Typeface.createFromAsset(c.getAssets(),"fonts/PataquesBrush.ttf");
        quandTuDors = Typeface.createFromAsset(c.getAssets(),"fonts/Quand_tu_dors_.otf");
        armalite = Typeface.createFromAsset(c.getAssets(),"fonts/armalite_rifle.ttf");
        oliver = Typeface.createFromAsset(c.getAssets(),"fonts/oliver__.ttf");
        atractWomen = Typeface.createFromAsset(c.getAssets(),"fonts/ATTRACTMOREWOMEN.ttf");
        blkChCry = Typeface.createFromAsset(c.getAssets(),"fonts/BLKCHCRY.TTF");
        neuroPolitical = Typeface.createFromAsset(c.getAssets(),"fonts/neuropolitical rg.ttf");
        octinStencilRg = Typeface.createFromAsset(c.getAssets(),"fonts/octin stencil rg.ttf");
        rmTypeRighter = Typeface.createFromAsset(c.getAssets(),"fonts/rm_typerighter_old.ttf");
        aeroliteBold = Typeface.createFromAsset(c.getAssets(),"fonts/more_fonts/Aerolite Bold.otf");

    }

    public static Typeface getTipeFace(Context c, int letterTypeAsInt) {

        if(!initializated) initTypefaces(c);

        Typeface tf = null;

        switch (letterTypeAsInt){
            case DEFAULT:               tf = byDefault;             break;
            case COMICATE:              tf = comicate;              break;
            case JANVIER:               tf = janvier;               break;
            case LE_FUTUR_ATENDRA:      tf = letraLeFuturAtendra;   break;
            case O4B_20:                tf = o4b_20;                break;
            case O4B_03:                tf = o4b_03;                break;
            case JANVIER_LIGHT:         tf = janvierLight;          break;
            case CALIGSTROY:            tf = caligstroy;            break;
            case GLORIA_HALLELUJAH:     tf = gloriaHallelujah;      break;
            case LES_JOURS_HEUREUX:     tf = lesJousHereux;         break;
            case PATAQUES:              tf = pataques;              break;
            case PATAQUES_BRUSH:        tf = pataquesBrush;         break;
            case QUAND_TU_DORS:         tf = quandTuDors;           break;
            case ARMALITE:              tf = armalite;              break;
            case OLIVER:                tf = oliver;                break;
            case ATRACTWOMEN:           tf = atractWomen;           break;
            case BLKCHCRY:              tf = blkChCry;              break;
            case NEUROPOLITICAL:        tf = neuroPolitical;        break;
            case OCTIN_STENCIL_RG:      tf = octinStencilRg;        break;
            case RM_TYPERIGHTER:        tf = rmTypeRighter;         break;
            case AEROLITE_BOLD:         tf = aeroliteBold;           break;

            default:                    tf = null;                  break;
        }
        return tf;
    }

}
