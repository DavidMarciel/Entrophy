package com.example.david.ent2.Letters;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by david on 15/03/2015.
 */
public class TypeFaces {

    private static boolean initializated = false;

    public static final int DEFAULT            = 0;
    public static final int COMICATE           = 1;
    public static final int JANVIER            = 2;
    public static final int LE_FUTUR_ATENDRA   = 3;
    public static final int O4B_20             = 4;
    public static final int O4B_03             = 5;
    public static final int JANVIER_LIGHT      = 6;
    public static final int CALIGSTROY         = 7;
    public static final int GLORIA_HALLELUJAH  = 8;
    public static final int LES_JOURS_HEUREUX  = 9;
    public static final int PATAQUES           = 10;
    public static final int PATAQUES_BRUSH     = 11;
    public static final int QUAND_TU_DORS      = 12;
    public static final int ARMALITE           = 13;
    public static final int OLIVER             = 14;
    public static final int ATRACTWOMEN        = 15;
    public static final int BLKCHCRY           = 16;
    public static final int NEUROPOLITICAL     = 17;
    public static final int OCTIN_STENCIL_RG   = 18;
    public static final int RM_TYPERIGHTER     = 19;
    public static final int AEROLITE_BOLD      = 20;

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

    public static Typeface getTipeFace(Context context, int letterTypeAsInt) {

        if(!initializated) initTypefaces(context);

        Typeface typeface;

        switch (letterTypeAsInt){
            case DEFAULT:               typeface = byDefault;             break;
            case COMICATE:              typeface = comicate;              break;
            case JANVIER:               typeface = janvier;               break;
            case LE_FUTUR_ATENDRA:      typeface = letraLeFuturAtendra;   break;
            case O4B_20:                typeface = o4b_20;                break;
            case O4B_03:                typeface = o4b_03;                break;
            case JANVIER_LIGHT:         typeface = janvierLight;          break;
            case CALIGSTROY:            typeface = caligstroy;            break;
            case GLORIA_HALLELUJAH:     typeface = gloriaHallelujah;      break;
            case LES_JOURS_HEUREUX:     typeface = lesJousHereux;         break;
            case PATAQUES:              typeface = pataques;              break;
            case PATAQUES_BRUSH:        typeface = pataquesBrush;         break;
            case QUAND_TU_DORS:         typeface = quandTuDors;           break;
            case ARMALITE:              typeface = armalite;              break;
            case OLIVER:                typeface = oliver;                break;
            case ATRACTWOMEN:           typeface = atractWomen;           break;
            case BLKCHCRY:              typeface = blkChCry;              break;
            case NEUROPOLITICAL:        typeface = neuroPolitical;        break;
            case OCTIN_STENCIL_RG:      typeface = octinStencilRg;        break;
            case RM_TYPERIGHTER:        typeface = rmTypeRighter;         break;
            case AEROLITE_BOLD:         typeface = aeroliteBold;          break;

            default:                    typeface = null;                  break;
        }
        return typeface;
    }

}
