package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;


public class Laser {

    int anchoPantalla, altoPantalla;

    int x, y;

    Bitmap frame;
    Context context;

    public Laser(Bitmap frame, int x, int y, int anchoPantalla, int altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.frame = frame;
        this.x = x;
        this.y = y;
//
//        frame = getBitmapFromAssets("boton.png");
//        frame = Bitmap.createScaledBitmap(frame, frame.getWidth() * 3/5, frame.getHeight() * 3/5, false);

        frame = BitmapFactory.decodeResource(context.getResources(), R.drawable.boton);
        frame = Bitmap.createScaledBitmap(frame, frame.getWidth() * 3/5, frame.getHeight() * 3/5, false);

    }


    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = this.context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
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

    public Bitmap getFrame() {
        return frame;
    }

    public void setFrame(Bitmap frame) {
        this.frame = frame;
    }
}
