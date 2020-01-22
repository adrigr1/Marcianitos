package com.example.marcianitos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class Escena2 extends Escena {
    public Escena2(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        super(idEscena, anchoPantalla, altoPantalla, contet);
    }

    @Override
    public void dibuja(Canvas c) {
        c.drawColor(Color.BLUE);
        c.drawText("Escena 2",anchoPantalla/2,ptexto.getTextSize()*2,ptexto);

        super.dibuja(c);

    }

    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
