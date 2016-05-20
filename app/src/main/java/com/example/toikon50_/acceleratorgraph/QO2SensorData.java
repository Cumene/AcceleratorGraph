package com.example.toikon50_.acceleratorgraph;

public class QO2SensorData {
    private long time;
    private float[] data;

    QO2SensorData(long time, float[] data) {
        this.time = time;
        this.data = data;
    }

    long getTime() {
        return time;
    }

    float[] getData() {
        return data;
    }
}