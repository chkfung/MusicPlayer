package me.chkfung.amz_musicplayer;

import android.animation.TypeEvaluator;

import java.util.Random;

/**
 * Created by chkfu_000 on 10/19/2016.
 */

public class WaveEvaluator implements TypeEvaluator<byte[]> {

    float prevfraction = 0;
    Random rand = new Random();
    byte[] generatedAnimate = new byte[2048];
    boolean positive = true;

    @Override
    public byte[] evaluate(float fraction, byte[] startValue, byte[] endValue) {
        if (fraction < prevfraction && fraction < 0.2) {
//            if ( fraction > 0.8&&fraction > prevfraction) {
            int A = (rand.nextInt(20) + 20) * (positive ? 1 : -1);
            int B = (rand.nextInt(20) + 20) * (positive ? 1 : -1);
            int C = (rand.nextInt(20) + 20) * (positive ? 1 : -1);
            double a = (rand.nextInt(100) + 200) / 10000.0;
            double b = (rand.nextInt(100) + 200) / 10000.0;
            double c = (rand.nextInt(100) + 200) / 10000.0;
            for (int i = 0; i < 2048; i++) {
                generatedAnimate[i] = (byte) (A * Math.sin(a * i) + B * Math.sin(b * i) + C * Math.sin(c * i));
            }
            if (positive)
                positive = false;
            else
                positive = true;
        }
        byte[] fractionY = new byte[2048];
        if (fraction > 0.5 && fraction > prevfraction && fraction<0.8 &&false)
            for (int i = 0; i < generatedAnimate.length; i++) {
                fractionY[i] = (byte) (generatedAnimate[i] * 0.5);
            }
        else
            for (int i = 0; i < generatedAnimate.length; i++) {
                fractionY[i] = (byte) (generatedAnimate[i] * fraction);
            }

        prevfraction = fraction;

        return fractionY;
    }
}
