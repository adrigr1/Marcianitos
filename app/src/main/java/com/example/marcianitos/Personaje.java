package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

public class Personaje {

    private int anchoPantalla, altoPantalla;
    //Bitmap[] frames, framesd, framesi;
    private int x, y;
    private int frameActual = 0;
    private int cont = 0;
    private Bitmap frame;


    private int vidas;

    private int tickFrame = 100;
    private long tiempoFrame;

    private  int velocidad = 0;
    private int tickMover = 50;
    private long tiempoMover;

    private Rect cuadrado;
    private Paint pincelRect;
    private Context contet;


    private Personaje(Bitmap frame, int x, int y, int vidas, int anchoPantalla, int altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.vidas = vidas;
        this.tiempoFrame = System.currentTimeMillis();
        this.tiempoMover = System.currentTimeMillis();

    }

    public void actualizaRectangulo() {
        //cuadrado.set(x, y, x + frames[frameActual].getWidth(), y + frames[frameActual].getHeight());
    }

    private Personaje(Bitmap frame, int x, int y, int anchoPantalla, int altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.frame = frame;
        this.x = x;
        this.y = y;
    }

    private void dibuja(Canvas c) {
        //if (nav) {
            c.drawBitmap(frame, x, y, null);
        //}else{

        //}
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = this.contet.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    private void mover3() {
        if (System.currentTimeMillis() - tiempoMover > tickMover) {
            velocidad = 30;
            this.x += velocidad;
            tiempoMover = System.currentTimeMillis();
//            actualizaRectangulo();
        }
    }


    private void mover() {
        if (System.currentTimeMillis() - tiempoMover > tickMover) {
            velocidad=7;
            this.y += velocidad;
            tiempoMover = System.currentTimeMillis();
//            actualizaRectangulo();
        }
//        if (System.currentTimeMillis() - tiempoMover > tickMover) {
//            this.x += velocidad;
//            if(this.x+this.frames[frameActual].getWidth()>anchoPantalla){
//                this.x=anchoPantalla-this.frames[frameActual].getWidth();
//                velocidad*=-1*1.1f;
//                frames=framesi;
//            }
//            if(this.x<0) {
//                this.x=0;
//                velocidad*=-1*1.1f;
//                frames=framesd;
//            }
//            tiempoMover = System.currentTimeMillis();
//            if(velocidad>0 && velocidad>200) velocidad=200;
//            if(velocidad>0 && velocidad<-200) velocidad=-200;
//
//            actualizaRectangulo();
//        }
    }

    public void mover1(int tipo) {
//        if (tipo == 1) {
//            //mover();
//        } else mover2();
    }

    private void mover2() {
        if (System.currentTimeMillis() - tiempoMover > tickMover) {
            velocidad = -7;
            this.y += velocidad;
            tiempoMover = System.currentTimeMillis();
            //actualizaRectangulo();
        }

//        if (System.currentTimeMillis() - tiempoMover > tickMover) {
//            this.x += velocidad;
//            this.y += velocidad;
//            if(this.x+this.frames[frameActual].getWidth()>anchoPantalla){
//                this.x=anchoPantalla-this.frames[frameActual].getWidth();
//                velocidad*=-1*1.1f;
//                frames=framesi;
//            }
//            if(this.y>altoPantalla){
//                this.y=0;
//            }
//            if(this.y<0){
//                this.y=altoPantalla;
//            }
//            if(this.x<0) {
//                this.x=0;
//                velocidad*=-1*1.1f;
//                frames=framesd;
//            }
//            tiempoMover = System.currentTimeMillis();
//            if(velocidad>0 && velocidad>200) velocidad=200;
//            if(velocidad>0 && velocidad<-200) velocidad=-200;
//
//            actualizaRectangulo();
//        }
    }

    public void todo(Canvas c) {
        //dibuja(c);
        mover();
        cambiaFrame();
    }

    private void cambiaFrame() {
        if (System.currentTimeMillis() - tiempoFrame > tickFrame) {
            cont++;
            //frameActual = cont % frames.length;
            tiempoFrame = System.currentTimeMillis();
        }
    }

    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(),
                imagen.getHeight(), matrix, false);
    }


    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

//    public Bitmap[] getFrames() {
//        return frames;
//    }
//
//    public void setFrames(Bitmap[] frames) {
//        this.frames = frames;
//    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTickFrame() {
        return tickFrame;
    }

    public void setTickFrame(int tickFrame) {
        this.tickFrame = tickFrame;
    }

    public long getTiempoFrame() {
        return tiempoFrame;
    }

    public void setTiempoFrame(long tiempoFrame) {
        this.tiempoFrame = tiempoFrame;
    }

    public int getVelocidad() {
        return velocidad;
    }

//    public void setVelocidad(int velocidad) {
//        this.velocidad = (int) (velocidad * 1.4f);
//        if (velocidad > 0) frames = framesd;
//        else frames = framesi;
//    }

    public int getFrameActual() {
        return frameActual;
    }

    public void setFrameActual(int frameActual) {
        this.frameActual = frameActual;
    }

    public int getTickMover() {
        return tickMover;
    }

    public void setTickMover(int tickMover) {
        this.tickMover = tickMover;
    }

    public Paint getPincelRect() {
        return pincelRect;
    }

    public void setPincelRect(Paint pincelRect) {
        this.pincelRect = pincelRect;
    }

    public long getTiempoMover() {
        return tiempoMover;
    }

    public void setTiempoMover(long tiempoMover) {
        this.tiempoMover = tiempoMover;
    }

    public Rect getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(Rect cuadrado) {
        this.cuadrado = cuadrado;
    }
}
