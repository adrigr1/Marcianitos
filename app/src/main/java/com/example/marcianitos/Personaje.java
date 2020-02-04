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

    int anchoPantalla, altoPantalla;
    //Bitmap[] frames, framesd, framesi;
    int x, y, y2;
    int frameActual = 0;
    int cont = 0;
    Bitmap frame;


    int vidas;

    int tickFrame = 100;
    long tiempoFrame;

    int velocidad = 0;
    int speedShoot = 30;

    int tickMover = 50;
    long tiempoMover;

    Rect cuadrado;
    Paint pincelRect;
    Context contet;


    public Personaje(Bitmap frame, int x, int y, int vidas, int anchoPantalla, int altoPantalla) {
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

    public Personaje(Bitmap frame, int x, int y2, int anchoPantalla, int altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.frame = frame;
        this.x = x;
        this.y2 = y2;
    }

    public void dibuja(Canvas c) {
        c.drawBitmap(frame, x, y, null);
    }

    public void dibuja2(Canvas c) {
        c.drawBitmap(frame, x, y2, null);
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = this.contet.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    public void shoot() {
        if (this.x >= anchoPantalla) {
            this.x=anchoPantalla/20*3;
        }else{
            this.x += speedShoot;
        }
//        this.y2+=y;
        tiempoMover = System.currentTimeMillis();
    }


    public void moveDown(boolean movement, int naveheight) {
        if (movement) {
            velocidad = 10;
        } else {
            velocidad = 0;
        }
        if (this.y >= altoPantalla - naveheight) {
            this.y = altoPantalla - naveheight;
        } else {
            this.y += velocidad;
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

    public void moveUP(boolean movement) {
        if (movement) {
            velocidad = -10;
        } else {
            velocidad = 0;
        }
        if (this.y <= 0) {
            this.y = 0;
        } else {
            this.y += velocidad;
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
        // mover();
        cambiaFrame();
    }

    public void cambiaFrame() {
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
