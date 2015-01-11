package com.ikimasu.gin.dejavuprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


/**
 *
 */
public class DejavuProgress extends View {

    public DejavuProgress(Context context) {
        super(context);
        initPaints();
    }

    public DejavuProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public DejavuProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaints();
    }

    Paint p1 = new Paint();
    Paint p2 = new Paint();
    Paint p3 = new Paint();
    Paint p4 = new Paint();

    Rect rect1 = new Rect();
    Rect rect2 = new Rect();
    Rect rect3 = new Rect();
    Rect rect4 = new Rect();

    int blue = Color.parseColor("#33B5E5");
    int green = Color.parseColor("#99CC00");
    int red = Color.parseColor("#FF4444");
    int yellow = Color.parseColor("#FFBB33");
    int transparent = Color.parseColor("#EECCCCCC");
    int borderLength;//边长
    int borderInterval;//边缘间隔

    int toBeWhiteBlock = 1;

    private void initPaints() {
        p1.setAntiAlias(true);
        p2.setAntiAlias(true);
        p3.setAntiAlias(true);
        p4.setAntiAlias(true);
        initColorsForPaint();
    }

    private void initColorsForPaint() {
        setPaintColor(p1, blue);
        setPaintColor(p2, green);
        setPaintColor(p3, red);
        setPaintColor(p4, yellow);
        switch (toBeWhiteBlock) {
            case 1:
                setPaintColor(p1, transparent);
                break;
            case 2:
                setPaintColor(p2, transparent);
                break;
            case 3:
                setPaintColor(p4, transparent);
                break;
            case 4:
                setPaintColor(p3, transparent);
                break;
        }
        invalidate();
    }

    private void setPaintColor(Paint paint, int color) {
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dejavuWidth = MeasureSpec.getSize(widthMeasureSpec);
        borderInterval = dejavuWidth / 12;
        borderLength = (dejavuWidth - borderInterval) / 2;
        rect1.set(0,
                0,
                borderLength,
                borderLength);
        rect2.set(borderLength + borderInterval,
                0,
                borderLength * 2 + borderInterval,
                borderLength);
        rect3.set(0,
                borderLength + borderInterval,
                borderLength,
                borderLength * 2 + borderInterval);
        rect4.set(borderLength + borderInterval,
                borderLength + borderInterval,
                borderLength * 2 + borderInterval,
                borderLength * 2 + borderInterval);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect1, p1);
        canvas.drawRect(rect2, p2);
        canvas.drawRect(rect3, p3);
        canvas.drawRect(rect4, p4);
        postDelayed(invalidatingDelay, 150);
    }

    InvalidatingDelay invalidatingDelay = new InvalidatingDelay();

    private class InvalidatingDelay implements Runnable {
        @Override
        public void run() {
            toBeWhiteBlock++;
            if (toBeWhiteBlock > 4) {
                toBeWhiteBlock = 1;
            }
            ;
            initColorsForPaint();
        }
    }

    public void setBorderInterval(int borderInterval) {
        this.borderInterval = borderInterval;
    }

    public void setBorderLength(int borderLength) {
        this.borderLength = borderLength;
    }

    public int getBorderInterval() {
        return borderInterval;
    }

    public int getBorderLength() {
        return borderLength;
    }

}

