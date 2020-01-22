package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class Escena3 extends Escena {
    Bitmap img;
    public Escena3(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        super(idEscena, anchoPantalla, altoPantalla, contet);
        img= BitmapFactory.decodeResource(contet.getResources(),R.drawable.run_00);
    }

    @Override
    public void dibuja(Canvas c) {
        c.drawColor(Color.RED);
        c.drawText("Escena 3",anchoPantalla/2,ptexto.getTextSize()*2,ptexto);
        c.drawBitmap(img,200,200,null);
        super.dibuja(c);

    }

    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        if(ant.contains(x,y))return 0;
        return super.onTouchEvent(event);
    }
}
