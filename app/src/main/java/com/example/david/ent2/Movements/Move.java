package com.example.david.ent2.Movements;

import com.example.david.ent2.Letters.Character;
import com.example.david.ent2.Dimens.Dimens;

/**A Move encapsulates the way a character can move
 * Created by david on 11/06/2014.
 */
public abstract class Move {

    //propiedades de pantalla
    static float Y_MAX_SCREEN_SIZE = Dimens.Y_MAX_SCREEN_SIZE;//1400;
    static float X_MAX_SCREEN_SIZE = Dimens.X_MAX_SCREEN_SIZE;//1050; //900;
    static float Y_MIN_SCREEN_SIZE = Dimens.Y_MIN_SCREEN_SIZE;// 100;
    static float X_MIN_SCREEN_SIZE = Dimens.X_MIN_SCREEN_SIZE;//50;
    static float SCREEN_WIDTH = X_MAX_SCREEN_SIZE - X_MIN_SCREEN_SIZE;
    static float SCREEN_HEIGHT = Y_MAX_SCREEN_SIZE - Y_MIN_SCREEN_SIZE;

    //propiedades de movimiento
    public static final int STATIC_MOVE = 0;
    public static final int HORIZONTAL_BLOCK_BOUNCE_MOVE = 1;
    public static final int VERTICAL_BLOCK_BOUNCE_MOVE = 2;
    public static final int LINE_MOVE = 3;
    public static final int MATRIX_MOVE = 4;
    //public static final int EXTRANGE_MOVE_1 = 5;
    public static final int VERTICAL_BOUNCE_MOVE = 6;
    public static final int LINE_BOUNCE_MOVE = 7;
    public static final int INFINITE_BLOCK_MOVE = 8;
    public static final int LATERAL_WIND_MOVE = 9;
    public static final int LASER_MOVE = 10;
    public static final int SNAKE_MOVE = 11;

    protected Character cr;
    protected float x, y;


    /**Constructor estático, devuelve una nueva instancia del tipo de movimiento requerido
     *
     * @param movementType tipo de movimiento requerido (usar las constantes de la clase Move)
     * @return Move pedido
     */
    public static Move getMovement(int movementType) {

        switch (movementType){
            case STATIC_MOVE: return new StaticMove();
            case HORIZONTAL_BLOCK_BOUNCE_MOVE: return new HorizontalBlockBounceMove();
            case VERTICAL_BLOCK_BOUNCE_MOVE: return new VerticalBlockBounceMove();
            case LINE_MOVE: return new LineMove();
            case MATRIX_MOVE: return new MatrixMove();
            //case EXTRANGE_MOVE_1: return new MovimientoRaro1();
            case VERTICAL_BOUNCE_MOVE: return new VerticalBounceMove();
            case LINE_BOUNCE_MOVE: return new LineBounceMove();
            case INFINITE_BLOCK_MOVE: return new InfiniteBlockMove();
            case LATERAL_WIND_MOVE: return new LateralWindMove();
            case LASER_MOVE: return new LaserMove();
            case SNAKE_MOVE: return new SnakeMove();
            default: return new StaticMove();
        }

    }


    public abstract int getMovementType();

    protected abstract void concreteInitialization();

    public abstract void move();

    public void setCaracter(Character character) {
        cr = character;
        initPosition();
    }

    private void initPosition() {
        x = cr.getX();
        y = cr.getY();

        concreteInitialization();
    }

    public void update(){
        cr.setX(x);
        cr.setY(y);
    }

    public String getInfo(){
        return "\nTamaño de pantalla x,y : "+
                X_MAX_SCREEN_SIZE +","+ Y_MAX_SCREEN_SIZE;
    }
}
