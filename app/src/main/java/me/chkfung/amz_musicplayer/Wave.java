package me.chkfung.amz_musicplayer;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by chkfu_000 on 10/23/2016.
 */

public class Wave {

    private final Paint paint;
    private final Paint paintRect;
    private final Random rand = new Random();
    private final int width;
    private final int height;
    private int Counter = 0;
    byte[] generatedAnimate = new byte[2048];
    byte[] progress = new byte[2048];
    boolean positive = true;

    public static Wave create(Paint paint, Paint paintRect, int width, int height) {
        return new Wave(paint, paintRect, width, height);
    }

    Wave(Paint paint, Paint paintRect, int width, int height) {
        this.paintRect = paintRect;
        this.width = width;
        this.height = height;
        this.paint = paint;
        generateRandomSine();
    }

    private void generateRandomSine() {
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
        Counter = 0;
    }

    public void draw(Canvas canvas) {

        if (Counter > 100 || generatedAnimate == null )
            generateRandomSine();

        for (int i = 0; i < width; i++) {
            int y = Counter;
            if(Counter>50)
                y = 100 - Counter;
            progress[i] = (byte) (generatedAnimate[i] * y*2  / 100 );
            canvas.drawLine(i, height + progress[i] - 100, i + 1, height, paint);
        }
        canvas.drawRect(0, height, width, height + 300, paintRect);
        Counter++;
    }
}
