package com.example.marcianitos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class Personaje {

    int anchoPantalla = 0, altoPantalla = 0;
    Bitmap[] frames, framesd, framesi;
    int x, y;
    int frameActual = 0;
    int cont = 0;


    int vidas;

    int tickFrame = 100;
    long tiempoFrame;

    int velocidad = 5;
    int tickMover = 50;
    long tiempoMover;

    Rect cuadrado;
    Paint pincelRect;


    public Personaje(Bitmap[] frames, int x, int y, int vidas , int anchoPantalla ,int altoPantalla ) {
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla=altoPantalla;
        this.frames = frames;
        framesd = frames;
        framesi = new Bitmap[frames.length];
        for (int i = 0; i < framesi.length; i++) {
            framesi[i] = espejo(frames[i],true);
        }
        this.x = x;
        this.y = y;
        this.vidas = vidas;
        this.tiempoFrame = System.currentTimeMillis();
        this.tiempoMover = System.currentTimeMillis();
        pincelRect = new Paint();
        pincelRect.setColor(Color.RED);
        pincelRect.setStyle(Paint.Style.STROKE);
        pincelRect.setStrokeWidth(10);
        cuadrado = new Rect(x, y, x + frames[frameActual].getWidth(), y + frames[frameActual].getHeight());
    }

    public void actualizaRectangulo() {
        cuadrado.set(x, y, x + frames[frameActual].getWidth(), y + frames[frameActual].getHeight());
    }

    public void dibuja(Canvas c) {
//        if(frames[fr])
        c.drawBitmap(frames[frameActual], x, y, null);
        c.drawRect(cuadrado, pincelRect);
    }

    public void mover() {
        if (System.currentTimeMillis() - tiempoMover > tickMover) {
            this.x += velocidad;
            if(this.x+this.frames[frameActual].getWidth()>anchoPantalla){
                this.x=anchoPantalla-this.frames[frameActual].getWidth();
                velocidad*=-1*1.1f;
                frames=framesi;
            }
            if(this.x<0) {
                this.x=0;
                velocidad*=-1*1.1f;
                frames=framesd;
            }
            tiempoMover = System.currentTimeMillis();
            if(velocidad>0 && velocidad>200) velocidad=200;
            if(velocidad>0 && velocidad<-200) velocidad=-200;

            actualizaRectangulo();
        }
    }

    public void mover1(int tipo){
        if(tipo==1){
            mover();
        }
        else mover2();
    }

    public void mover2(){
        if (System.currentTimeMillis() - tiempoMover > tickMover) {
            this.x += velocidad;
            this.y += velocidad;
            if(this.x+this.frames[frameActual].getWidth()>anchoPantalla){
                this.x=anchoPantalla-this.frames[frameActual].getWidth();
                velocidad*=-1*1.1f;
                frames=framesi;
            }
            if(this.y>altoPantalla){
                this.y=0;
            }
            if(this.y<0){
                this.y=altoPantalla;
            }
            if(this.x<0) {
                this.x=0;
                velocidad*=-1*1.1f;
                frames=framesd;
            }
            tiempoMover = System.currentTimeMillis();
            if(velocidad>0 && velocidad>200) velocidad=200;
            if(velocidad>0 && velocidad<-200) velocidad=-200;

            actualizaRectangulo();
        }
    }

    public void todo(Canvas c) {
        dibuja(c);
        mover();
        cambiaFrame();
    }

    public void cambiaFrame() {
        if (System.currentTimeMillis() - tiempoFrame > tickFrame) {
            cont++;
            frameActual = cont % frames.length;
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

    public Bitmap[] getFrames() {
        return frames;
    }

    public void setFrames(Bitmap[] frames) {
        this.frames = frames;
    }

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

    public void setVelocidad(int velocidad) {
        this.velocidad = (int)(velocidad*1.4f);
        if(velocidad>0) frames=framesd;
        else  frames=framesi;
    }

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