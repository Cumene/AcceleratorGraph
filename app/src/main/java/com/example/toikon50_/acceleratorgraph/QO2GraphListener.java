package com.example.toikon50_.acceleratorgraph;

        import android.app.Activity;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.widget.TextView;

public class QO2GraphListener implements SensorEventListener {
    private Activity activity;
    private QO2GraphView view;
    private TextView[] texts;


    public QO2GraphListener(Activity activity, QO2GraphView view, TextView[] texts) {
        this.activity = activity;
        this.view = view;
        this.texts = texts;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                float[] array = new float[3];
                array[0] = event.values[0];
                array[1] = event.values[1];
                array[2] = event.values[2];

                texts[1].setText("x = " + array[0]);
                texts[2].setText("y = " + array[1]);
                texts[3].setText("z = " + array[2]);
                view.change(array);
                break;
        }
    }
}