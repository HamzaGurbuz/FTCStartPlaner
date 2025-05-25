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

    private Bitmap backgroundBitmap;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);

        // Drawable’dan bitmap olarak arka planı yükle
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.field);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Önce arka plan resmini çiz
        if (backgroundBitmap != null) {
            // Bitmap'i view boyutuna göre ölçekle
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(backgroundBitmap, getWidth(), getHeight(), false);
            canvas.drawBitmap(scaledBitmap, 0, 0, null);
        }

        // Üzerine çizim yolunu çiz
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    public void clear() {
        path.reset();
        invalidate();
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }
}
