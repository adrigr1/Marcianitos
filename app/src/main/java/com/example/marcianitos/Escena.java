package com.example.marcianitos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Escena {
    int idEscena, anchoPantalla, altoPantalla;
    Context contet;
    Paint ptexto, pboton,pboton2;
    Rect ant,sig,jugar,records,creditos,ayuda;

    public Escena(int idEscena, int anchoPantalla, int altoPantalla, Context contet) {
        this.idEscena = idEscena;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.contet = contet;

        ptexto=new Paint();
        ptexto.setTextSize(altoPantalla/10);
        ptexto.setColor(Color.WHITE);
        ptexto.setTextAlign(Paint.Align.CENTER);

        int aux=anchoPantalla/10;
        ant=new Rect(0,altoPantalla-aux,aux*2,altoPantalla);
        sig=new Rect(anchoPantalla-aux*2,altoPantalla-aux,anchoPantalla,altoPantalla);
        jugar=new Rect(anchoPantalla/5*2,altoPantalla/4,anchoPantalla/5*3,altoPantalla/5*2);
        records=new Rect(anchoPantalla/8,altoPantalla/7*4,anchoPantalla/8*3,altoPantalla/7*5);
        creditos=new Rect(anchoPantalla/5*2,altoPantalla/7*4,anchoPantalla/5*3,altoPantalla/7*5);
        ayuda=new Rect(anchoPantalla/6*4,altoPantalla/7*4,anchoPantalla/6*5,altoPantalla/7*5);

        pboton=new Paint();
        pboton.setColor(Color.MAGENTA);
        pboton2=new Paint();
        pboton2.setColor(Color.BLUE);
    }

    public void dibuja(Canvas c){

        if(idEscena>0) {
            c.drawRect(ant, pboton);

        }
        if(idEscena<4){
            c.drawRect(sig,pboton);
        }
        if(idEscena==0){
            c.drawRect(jugar,pboton2);
            c.drawRect(records,pboton2);
            c.drawRect(creditos,pboton);
            c.drawRect(ayuda,pboton2);
        }

    }

    public void actualizaFisica(){

    }

    public int onTouchEvent(MotionEvent event){
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(idEscena>0){
            if(ant.contains(x,y))return idEscena-1;
        }
        if(idEscena<4){
            if(sig.contains(x,y))return idEscena+1;
        }
        if(idEscena==0){
            if(jugar.contains(x,y))return idEscena+1;
            if(records.contains(x,y))return idEscena+2;
            if(creditos.contains(x,y))return idEscena+3;
            if(ayuda.contains(x,y))return idEscena+4;

        }

        return idEscena;
    }
}
