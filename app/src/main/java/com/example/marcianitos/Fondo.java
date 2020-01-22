package com.example.marcianitos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Fondo {

    Bitmap fondo ,fondo2;
    int y;
    int x1,x2;
    int velo;
int anchoPantalla;
    boolean derecha=true;

    public Fondo(Bitmap fondo, int y ,int velo, int anchoPantalla) { // Constructores
        this.fondo = fondo;
        this.fondo2 = espejo(fondo,true);
        this.y = y;
        this.x1 = 0;
        this.x2=fondo.getWidth();
        this.velo=velo;
        this.anchoPantalla=anchoPantalla;
    }
    public void move(){
        if(derecha){
            x1+=velo;
            x2+=velo;
            if(x1>anchoPantalla)x1=x2-fondo.getWidth();
            if(x2>anchoPantalla)x2=x1-fondo.getWidth();

        }
    }
    public void dibuja(Canvas c){
        c.drawBitmap(fondo,x1,y,null);
        c.drawBitmap(fondo2,x2,y,null);
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }
    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(),
                imagen.getHeight(), matrix, false);
    }
}
