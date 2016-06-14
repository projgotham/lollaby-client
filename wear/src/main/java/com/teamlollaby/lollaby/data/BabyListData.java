package com.teamlollaby.lollaby.data;

/**
 * Created by MinGu on 2015-11-01.
 */
public class BabyListData implements Cloneable {
    public String babyName;
    public float temperature;
    public float humidity;
    public int pressure;
    public int noise;

    public BabyListData() {
        this.babyName = "연결해주세요!";
        this.temperature = 26.0f;
        this.humidity = 36.0f;
        this.pressure = 450;
        this.noise = 400;
    }

    @Override
    public String toString() {
        return "Temperature=" + temperature + ", Humidity=" + humidity + ", Pressure=" + pressure + ", Noise=" + noise;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
