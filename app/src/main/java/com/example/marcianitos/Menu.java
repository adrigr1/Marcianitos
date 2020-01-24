package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;

public class Menu extends Escena {
    Bitmap fondo1, ajustes;
    Fondo f1;
    public Menu(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        super(idEscena, anchoPantalla, altoPantalla, contet);
        fondo1 = getBitmapFromAssets("intro.jpg");
        fondo1 = Bitmap.createScaledBitmap(fondo1, anchoPantalla, altoPantalla, false);
        f1 = new Fondo(fondo1, 0, 20, anchoPantalla);
    }

    @Override
    public void dibuja(Canvas c) {
        try {
            c.drawBitmap(fondo1, 0, 0, null);
            super.dibuja(c);


            c.drawText("Menu", anchoPantalla / 2, ptexto.getTextSize() * 2, ptexto);
            c.drawText("Jugar", anchoPantalla / 2, altoPantalla / 3, ptexto);
            c.drawText("Records", anchoPantalla / 4, altoPantalla / 3 * 2, ptexto);
            c.drawText("Créditos", anchoPantalla / 4 * 2, altoPantalla / 3 * 2, ptexto);
            c.drawText("Ayuda", anchoPantalla / 4 * 3, altoPantalla / 3 * 2, ptexto);
            ajustes = getBitmapFromAssets("descarga.png");
            ajustes = Bitmap.createScaledBitmap(ajustes, anchoPantalla/10, altoPantalla/5, false);
            c.drawBitmap(ajustes,0,0,null);
        } catch (Exception e) {
        }
    }


    @Override
    public void actualizaFisica() {
        f1.move();
        super.actualizaFisica();
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = this.contet.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }
}
