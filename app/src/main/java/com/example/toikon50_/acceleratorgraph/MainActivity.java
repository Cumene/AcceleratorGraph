package com.example.toikon50_.acceleratorgraph;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class MainActivity extends Activity {
    private SensorManager sensorManager;
    TextView[] texts;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        QO2GraphView view = new QO2GraphView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        texts = new TextView[5];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = new TextView(this);
            layout.addView(texts[i]);
        }
        texts[0].setText("加速度");
        texts[4].setText("");

        QO2GraphListener listener = new QO2GraphListener(this, view, texts);

        layout.addView(view);

        List<Sensor> sensors = sensorManager
                .getSensorList(Sensor.TYPE_ACCELEROMETER);
        for (Sensor sensor : sensors) {
            sensorManager.registerListener(listener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


        setContentView(layout);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.toikon50_.acceleratorgraph/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("( *°ω°⊂彡☆))Д´) ﾊﾟｰﾝ");
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("(*´ω｀*)");
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.toikon50_.acceleratorgraph/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
            System.out.println("（○v○）");
            texts[4].setText("タッチイベント待ち");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("(*>△<)");
                texts[4].setText("押してる");
                /*
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // onPause();
                */
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("(o・∇・o)");
                texts[4].setText("離した");
                //onResume();
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("ζ'ヮ')ζ");
                texts[4].setText("動いてる");
                break;

        }

        return false;
    }
}

