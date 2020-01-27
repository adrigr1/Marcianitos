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
    Personaje per,per1;
    Rect up, down, gunBtn;
    Paint butControl;
    int posy, posxGun;
    int velNave = 0, velGun = 0;
    boolean disparar = false;
    Canvas a;

    public Escena1(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        super(idEscena, anchoPantalla, altoPantalla, contet);
        fondo1 = getBitmapFromAssets("espacio.png");
        fondo1 = Bitmap.createScaledBitmap(fondo1, anchoPantalla * 3 / 2, altoPantalla, false);
        f = new Fondo(fondo1, 0, 5, anchoPantalla);

        up = new Rect(0, 0, anchoPantalla / 10, altoPantalla / 5 * 2);
        down = new Rect(0, altoPantalla / 5 * 3, anchoPantalla / 10, altoPantalla);
        gunBtn = new Rect(anchoPantalla / 7 * 6, altoPantalla / 2, anchoPantalla / 11 * 10, altoPantalla / 11 * 6);

        posxGun = anchoPantalla / 6;
        posy = altoPantalla / 2;

        butControl = new Paint();
        butControl.setColor(Color.GREEN);

        nave = getBitmapFromAssets("1.png");
        per = new Personaje(nave, anchoPantalla / 20*3, altoPantalla/2, 4, anchoPantalla, altoPantalla);

        gun = getBitmapFromAssets("boton.png");
        gun1 = getBitmapFromAssets("boton.png");
        per1 = new Personaje(gun1, anchoPantalla / 20*3, altoPantalla/2, anchoPantalla, altoPantalla);

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



        //c.drawBitmap(nave, anchoPantalla / 6, posy, null);

        c.drawRect(gunBtn, butControl);
        c.drawBitmap(gun, anchoPantalla / 7 * 6, altoPantalla / 2, null);

        if (disparar) {
            if (posxGun == anchoPantalla) {
                posxGun = anchoPantalla / 6;
            }
            per1.dibuja(c);
//            c.drawBitmap(gun1,anchoPantalla / 20*3, altoPantalla/2, null);
        }
        super.dibuja(c);
    }


    @Override
    public void actualizaFisica() {
        super.actualizaFisica();
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
                    per1.mover3();
                }

                Log.i("pulso", "bajo");
                //break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (down.contains((int) event.getX(), (int) event.getY())) {
                    per.mover();
                }

                if (up.contains((int) event.getX(), (int) event.getY())) {
                    if (posy <= 0) {
                        velNave = 0;
                    } else {
                        per.mover2();
                    }
                }
//                posy += velNave;
                Log.i("pulso", "bajo otro");
                break;
            case MotionEvent.ACTION_UP:
                if (gunBtn.contains((int) event.getX(), (int) event.getY())) {
                    //disparar = false;
                }
                Log.i("pulso", "subo");
                ;
            case MotionEvent.ACTION_POINTER_UP:
                if (up.contains((int) event.getX(), (int) event.getY()) || down.contains((int) event.getX(), (int) event.getY())) {
                    velNave = 0;
                }

                Log.i("pulso", "subo otro");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("pulso", "MUEVO");
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
