package com.example.toikon50_.acceleratorgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class QO2GraphView extends View {
    private Context context;
    ArrayList<QO2SensorData> list = new ArrayList<QO2SensorData>();

    public QO2GraphView(Context context) {
        super(context);
        this.context = context;
        setBackgroundColor(Color.WHITE);
    }

    float rate = 30;
    float timeRate = 0.2f;

    @Override
    public void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(Color.argb(255, 0, 0, 0));
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);//0m/s^2
        canvas.drawLine(0, getHeight() / 2 - 10 * rate, getWidth(), getHeight() / 2 - 10 * rate, paint);//10m/s^2
        canvas.drawLine(0, getHeight() / 2 + 10 * rate, getWidth(), getHeight() / 2 + 10 * rate, paint);//-10m/s^2

        if (!list.isEmpty()) {
            QO2SensorData prev = list.get(0);
            float prevx = getWidth();
            float[] prevy = new float[3];// 0-x軸について 1-y軸について 2-z軸について

            prevy[0] = getHeight() / 2 - prev.getData()[0] * rate;
            prevy[1] = getHeight() / 2 - prev.getData()[1] * rate;
            prevy[2] = getHeight() / 2 - prev.getData()[2] * rate;

            long firstTime = prev.getTime();

            paint.setStyle(Paint.Style.STROKE);

            point1 :for (int i = 1; i < list.size(); i++) {
                QO2SensorData now = list.get(i);
                float x = getWidth() - ((now.getTime() - firstTime) * timeRate);
                float[] y = new float[3];

                y[0] = getHeight() / 2 - now.getData()[0] * rate;
                y[1] = getHeight() / 2 - now.getData()[1] * rate;
                y[2] = getHeight() / 2 - now.getData()[2] * rate;

                final int LINE_WIDTH = 3;

                paint.setColor(Color.argb(255, 255, 0, 0));
                paint.setStrokeWidth(LINE_WIDTH);
                canvas.drawLine(prevx, prevy[0], x, y[0], paint);

                paint.setColor(Color.argb(255, 0, 255, 0));
                paint.setStrokeWidth(LINE_WIDTH);
                canvas.drawLine(prevx, prevy[1], x, y[1], paint);

                paint.setColor(Color.argb(255, 0, 0, 255));
                paint.setStrokeWidth(LINE_WIDTH);
                canvas.drawLine(prevx, prevy[2], x, y[2], paint);

                prevx = x;
                float y0 = y[0];
                float y1 = y[1];
                float y2 = y[2];

                prevy[0] = y0;
                prevy[1] = y1;
                prevy[2] = y2;

                if (x < 0) {
                    list.remove(0);
                }
            }
        }
    }

    public void change(float[] data) {
        long time = System.currentTimeMillis();
        list.add(new QO2SensorData(time, data));
        invalidate();
    }
}
