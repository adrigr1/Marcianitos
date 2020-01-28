package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;

public class Escena1 extends Escena {
    Bitmap fondo1;
    Bitmap nave, gun, gun1;
    Fondo f;
    Personaje per, bala;
    Rect up, down, gunBtn;
    Paint butControl;
    int posy, posxGun;
    int velNave = 0, velGun = 0;
    boolean disparar = false, mUp = false, mDown = false;
    Canvas a;

    public Escena1(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        super(idEscena, anchoPantalla, altoPantalla, contet);
        fondo1 = getBitmapFromAssets("espacio.png");
        fondo1 = Bitmap.createScaledBitmap(fondo1, anchoPantalla * 3 / 2, altoPantalla, false);
        f = new Fondo(fondo1, 0, 5, anchoPantalla);

        up = new Rect(0, 0, anchoPantalla / 20*2, altoPantalla / 20 * 8);
        down = new Rect(0, altoPantalla / 20 * 12, anchoPantalla / 20*2, altoPantalla);
        gunBtn = new Rect(anchoPantalla / 20*18, altoPantalla / 2, anchoPantalla / 20 * 19, altoPantalla / 20 * 11);

        posxGun = anchoPantalla / 6;
        posy = altoPantalla / 2;

        butControl = new Paint();
        butControl.setColor(Color.GREEN);

        nave = getBitmapFromAssets("1.png");
        per = new Personaje(nave, anchoPantalla / 20 * 3, altoPantalla / 2, 4, anchoPantalla, altoPantalla);

        gun = getBitmapFromAssets("boton.png");
        gun1 = getBitmapFromAssets("boton.png");
        bala = new Personaje(gun1, anchoPantalla / 20 * 3 + nave.getWidth(), altoPantalla / 2, anchoPantalla, altoPantalla);
//
        //gun1=new Personaje(gun,anchoPantalla/7*6,altoPantalla/2,1,anchoPantalla,altoPantalla);
        //nave = Bitmap.createScaledBitmap(nave, anchoPantalla, altoPantalla, false);
    }

    @Override
    public void dibuja(Canvas c) {

        f.dibuja(c);
        c.drawText("Escena 1", anchoPantalla / 2, ptexto.getTextSize() * 2, ptexto);
        c.drawRect(up, butControl);
        c.drawRect(down, butControl);
        per.dibuja(c);

        posxGun += velGun;

        c.drawRect(gunBtn, butControl);
        c.drawBitmap(gun, anchoPantalla / 20*18, altoPantalla / 2, null);

        if (disparar) {
            bala.dibuja(c);
        }
        super.dibuja(c);
    }


    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
        if (disparar) bala.shoot();
        if (mDown) {
            per.moveDown(true);
        } else {
            per.moveDown(false);
        }
        if (mUp) {
            per.moveUP(true);
        } else per.moveUP(false);
        f.move();
    }

    @Override
    public int onTouchEvent(MotionEvent event) {

        int accion = event.getActionMasked();
        int indice = event.getActionIndex();
        int id = event.getPointerId(indice);
        float x = event.getX(indice);
        float y = event.getY(indice);
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (gunBtn.contains((int) event.getX(), (int) event.getY())) {
                    disparar = true;
                }

                Log.i("pulso", "bajo");
                //break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (down.contains((int) event.getX(), (int) event.getY())) {
                    mDown = true;
                }
                if (up.contains((int) event.getX(), (int) event.getY())) {
                    mUp = true;
                }
                Log.i("pulso", "bajo otro");
                break;
            case MotionEvent.ACTION_UP:
                if (gunBtn.contains((int) event.getX(), (int) event.getY())) {
                    disparar = false;
                }
                Log.i("pulso", "subo");
                ;
            case MotionEvent.ACTION_POINTER_UP:
                if (up.contains((int) event.getX(), (int) event.getY())) {
                    mUp = false;
                }

                if (down.contains((int) event.getX(), (int) event.getY())) {
                    mDown = false;
                }




            Log.i("pulso", "subo otro");
            break;
            case MotionEvent.ACTION_MOVE:
                // Log.i("pulso", "MUEVO");
                break;

        }
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
