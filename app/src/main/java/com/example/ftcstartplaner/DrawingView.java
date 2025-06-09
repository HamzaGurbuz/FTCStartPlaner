package com.example.ftcstartplaner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    private final Bitmap backgroundBitmap;
    private Canvas drawingCanvas;
    private Bitmap drawingBitmap;

    private int currentColor = Color.BLACK;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);

        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.field);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        drawingBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawingCanvas = new Canvas(drawingBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Arka planı ölçeklendir ve çiz
        if (backgroundBitmap != null) {
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(backgroundBitmap, getWidth(), getHeight(), false);
            canvas.drawBitmap(scaledBitmap, 0, 0, null);
        }

        // Önce daha önce çizilenleri çiz
        canvas.drawBitmap(drawingBitmap, 0, 0, null);

        // Sonra aktif path'i çiz
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        // ScrollView veya üst view’lerin dokunmayı kesmesini engelle
        getParent().requestDisallowInterceptTouchEvent(true);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                // Bitince aktif path'i kalıcı bitmap'e çiz ve temizle
                drawingCanvas.drawPath(path, paint);
                path.reset();

                // ScrollView yeniden yakalayabilir
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        invalidate();
        return true;
    }

    public void setPaintColor(int color) {
        currentColor = color;
        paint.setColor(color);
        paint.setStrokeWidth(8f);
        invalidate();
    }


    public void clear() {
        path.reset();
        drawingBitmap.eraseColor(Color.TRANSPARENT);
        invalidate();
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Arka plan
        if (backgroundBitmap != null) {
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(backgroundBitmap, getWidth(), getHeight(), false);
            canvas.drawBitmap(scaledBitmap, 0, 0, null);
        }

        // Çizilenler
        canvas.drawBitmap(drawingBitmap, 0, 0, null);

        // Aktif path
        canvas.drawPath(path, paint);

        return bitmap;
    }

}
