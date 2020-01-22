package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;

public class Escena1 extends Escena {
    Bitmap fondo1;
    Bitmap nave,gun,gun1;
    Fondo f;
    Personaje gun10;
    Rect up, down,gunBtn;
    Paint butControl;
    int posy,posxGun;
    int velNave=0,velGun=0;
    boolean disparar=false;

    public Escena1(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        super(idEscena, anchoPantalla, altoPantalla, contet);
        fondo1 = getBitmapFromAssets("espacio.png");
        fondo1 = Bitmap.createScaledBitmap(fondo1, anchoPantalla*3/2, altoPantalla, false);
        f = new Fondo(fondo1,0, 10, anchoPantalla);

        up=new Rect(0,0,anchoPantalla/10,altoPantalla/5*2);
        down=new Rect(0,altoPantalla/5*3,anchoPantalla/10,altoPantalla);
        gunBtn=new Rect(anchoPantalla/7*6,altoPantalla/2,anchoPantalla/11*10,altoPantalla/11*6);

        posxGun=anchoPantalla/6;

        butControl=new Paint();
        butControl.setColor(Color.GREEN);

        nave = getBitmapFromAssets("1.png");
        gun = getBitmapFromAssets("boton.png");
        gun1 = getBitmapFromAssets("boton.png");
        //gun1=new Personaje(gun,anchoPantalla/7*6,altoPantalla/2,1,anchoPantalla,altoPantalla);
        //nave = Bitmap.createScaledBitmap(nave, anchoPantalla, altoPantalla, false);
    }

    @Override
    public void dibuja(Canvas c) {

        f.dibuja(c);
        c.drawText("Escena 1",anchoPantalla/2,ptexto.getTextSize()*2,ptexto);
        c.drawRect(up,butControl);
        c.drawRect(down,butControl);


        posy+=velNave;
        posxGun+=velGun;
        c.drawBitmap(nave,anchoPantalla/6,posy,null);

        c.drawRect(gunBtn,butControl);
        c.drawBitmap(gun,anchoPantalla/7*6,altoPantalla/2,null);
        if(disparar){
            c.drawBitmap(gun1,posxGun,posy,null);
        }else {

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

        if (down.contains((int) event.getX(), (int) event.getY())) {
            velNave = 5;
        } else if (up.contains((int) event.getX(), (int) event.getY())) {
            velNave = -5;
        } else {
            velNave = 0;
        }
        if(gunBtn.contains((int) event.getX(), (int) event.getY())){
            disparar=true;
            velGun=10;
        } else {
            disparar=false;
        }


//                int accion = event.getActionMasked();
//        int indice = event.getActionIndex();
//        int id = event.getPointerId(indice);
//        float x = event.getX(indice);
//        float y = event.getY(indice);
//        switch (accion) {
//            case MotionEvent.ACTION_DOWN:
////                Log.i("pulso", "bajo 1");
//            case MotionEvent.ACTION_POINTER_DOWN:
//                dedos.put(id, new Point((int) x, (int) y));
//
//                per.setVelocidad(per.getVelocidad() * -1);
//
//                if (pantallaInicio) pantallaInicio = false;
//                Log.i("pulso", "bajo otro");
//                return true;
//            case MotionEvent.ACTION_UP:
////                Log.i("pulso", "LEVANTO 1");
//            case MotionEvent.ACTION_POINTER_UP:
//                dedos.remove(id);
//                Log.i("pulso", "LEVANTO otro");
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                for (int i = 0; i < event.getPointerCount(); i++) {
//                    dedos.put(event.getPointerId(i),
//                            new Point((int) event.getX(i), (int) event.getY(i)));
//                }
//
////                Log.i("pulso","MUEVO "+x+" "+y);
//                return true;
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
