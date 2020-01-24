package com.example.marcianitos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class JuegoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    boolean pantallaInicio = true;
    int anchoPantalla = 0, altoPantalla = 0;
    SurfaceHolder surfaceHolder;
    Context context;
    Hilo hilo;
    Paint p;

    Personaje per, per2;

    Bitmap b1, b2, b3;
    Bitmap fondo1, fondo2;

    HashMap<Integer, Point> dedos = new HashMap<>();

    Bitmap[] naves = new Bitmap[6];
    Fondo f1, f2;

    Escena escenaActual;

    public JuegoSurfaceView(Context context) {
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        hilo = new Hilo();


        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            windowManager.getDefaultDisplay().getRealMetrics(dm); // métricas de la pantalla con tamaño reducido
        } else windowManager.getDefaultDisplay().getMetrics(dm);

        anchoPantalla = dm.widthPixels;
        altoPantalla = dm.heightPixels;


        escenaActual=new Menu(0,anchoPantalla,altoPantalla,context);


        if (dm.widthPixels > dm.heightPixels) // Apaisado


            p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(60);
        Log.i("taman", anchoPantalla + ":" + altoPantalla);

        b1 = BitmapFactory.decodeResource(getResources(), R.drawable.run_00);
        b2 = BitmapFactory.decodeResource(getResources(), R.drawable.run_01);
        b3 = getBitmapFromAssets("run/run_07.png");

        Bitmap[] bitmaps = new Bitmap[10];
        for (int i = 0; i < bitmaps.length; i++) {
            bitmaps[i] = getBitmapFromAssets("run/run_0" + i + ".png");
            bitmaps[i] = escalaAltura(bitmaps[i], altoPantalla / 3);
        }

        per = new Personaje(bitmaps, anchoPantalla / 2, altoPantalla - bitmaps[0].getHeight() - altoPantalla / 8, 4, anchoPantalla, altoPantalla);
        per2 = new Personaje(bitmaps, anchoPantalla / 2, altoPantalla - bitmaps[0].getHeight() - altoPantalla / 8, 4, anchoPantalla, altoPantalla);

        fondo1 = getBitmapFromAssets("intro.jpg");
        fondo1 = Bitmap.createScaledBitmap(fondo1, anchoPantalla, altoPantalla, false);
        fondo2 = getBitmapFromAssets("desierto.jpg");
        fondo2 = Bitmap.createScaledBitmap(fondo2, anchoPantalla, altoPantalla, false);

        for (int i = 0; i < naves.length; i++) {
            naves[i] = getBitmapFromAssets("nave" + (i + 1) + ".png");
        }
        f1 = new Fondo(fondo1, 0, 20, anchoPantalla);


    }


    public void dibujar(Canvas c) {
//        if (pantallaInicio) {
//            c.drawBitmap(fondo1, 0, 0, null);
//        } else {
//            c.drawBitmap(fondo2, 0, 0, null);
//            per.dibuja(c);
//            per2.dibuja(c);
//            c.drawText(anchoPantalla + ":" + altoPantalla, 10, 10 + p.getTextSize(), p);
//        }


//        if (pantallaInicio) {
//            f1.dibuja(c);
//            for (Map.Entry<Integer, Point> e : dedos.entrySet()) {
//                c.drawBitmap(naves[e.getKey()], e.getValue().x - naves[e.getKey()].getWidth() / 2, e.getValue().y - naves[e.getKey()].getHeight() / 2, null);
//            }
//        } else {
//            c.drawBitmap(fondo2, 0, 0, null);
//            per.dibuja(c);
//            per2.dibuja(c);
//            c.drawText(anchoPantalla + ":" + altoPantalla, 10, 10 + p.getTextSize(), p);
//        }


//        c.drawBitmap(b1, 10, 10, null);
//        c.drawBitmap(b2, 500, 10, null);
//        c.drawBitmap(b3, 1000, 10, null);

    }

    public void actualizaFisica() {
//        f1.move();
//        if (!pantallaInicio) {
//            per.mover();
//            per.cambiaFrame();
//            per2.mover2();
//            per2.cambiaFrame();
//        }
    }

    public void cambioEscena(int idNuevaEscena){
        switch (idNuevaEscena){
            case 1:
                escenaActual=new Escena1(idNuevaEscena,anchoPantalla,altoPantalla,context);
                break;
            case 2:
                escenaActual=new Escena2(idNuevaEscena,anchoPantalla,altoPantalla,context);
                break;
            case 3:
                escenaActual=new Escena3(idNuevaEscena,anchoPantalla,altoPantalla,context);
                break;
            case 4:
                escenaActual=new Escena4(idNuevaEscena,anchoPantalla,altoPantalla,context);
                break;
            default:
                escenaActual=new Menu(0,anchoPantalla,altoPantalla,context);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int accion = event.getActionMasked();
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
//        }

        int idPulsacion=escenaActual.onTouchEvent(event);
        if(idPulsacion!=escenaActual.idEscena){
            cambioEscena(idPulsacion);
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hilo.setFuncionado(true);
        if (hilo.getState() == Thread.State.NEW) hilo.start();
        if (hilo.getState() == Thread.State.TERMINATED) {
            hilo = new Hilo();
            hilo.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.altoPantalla = height;
        this.anchoPantalla = width;
        b1 = escalaAltura(b1, altoPantalla / 2);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.hilo.setFuncionado(false);
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Hilo extends Thread {
        public boolean funcionado = false;

        public Hilo() {

        }

        public boolean isFuncionado() {
            return funcionado;
        }

        public void setFuncionado(boolean funcionado) {
            this.funcionado = funcionado;
        }

        @Override
        public void run() {
            while (funcionado) {
                Canvas c = null;
                if (!surfaceHolder.getSurface().isValid()) {
                    continue;
                }
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        c = surfaceHolder.lockHardwareCanvas();
                    } else {
                        c = surfaceHolder.lockCanvas();
                    }
                    synchronized (surfaceHolder) {
//                        actualizaFisica();
//                        dibujar(c);
                        escenaActual.actualizaFisica();
                        escenaActual.dibuja(c);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    public Bitmap escalaAltura(int res, int nuevoAlto) {
        Bitmap bitmapAux = BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) /
                bitmapAux.getHeight(), nuevoAlto, true);
    }

    public Bitmap escalaAltura2(int res, int nuevoAlto) {
        Bitmap bitmapAux = BitmapFactory.decodeResource(context.getResources(), res);
        return escalaAltura(bitmapAux, nuevoAlto);
    }

    public Bitmap escalaAltura(Bitmap bitmapAux, int nuevoAlto) {
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) /
                bitmapAux.getHeight(), nuevoAlto, true);
    }
}
