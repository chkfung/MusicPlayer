package me.chkfung.amz_musicplayer;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by chkfu_000 on 10/23/2016.
 */

public class Wave {
    private int YPos;
    private int width;
    private final int INTERVAL = 27;
    private final Paint paint;
    private final Paint paintRect;
    private final Random rand = new Random();
    private final int sequence;
    private int Counter = 0;
    byte[] generatedAnimate = new byte[2048];
    byte[] progress = new byte[2048];
    boolean positive = true;

    public static Wave create(Paint paint, Paint paintRect, int sequence) {
        return new Wave(paint, paintRect, sequence);
    }

    Wave(Paint paint, Paint paintRect, int sequence) {
        this.paintRect = paintRect;
        this.sequence = sequence;
        this.paint = paint;
        generateRandomSine();
    }

    public void init(int width, int YPos) {
        this.YPos = YPos;
        this.width = width;
    }

    private void generateRandomSine() {
        int A = (rand.nextInt(20) + 20) * ( rand.nextBoolean()? 1 : -1);
        int B = (rand.nextInt(20) + 20) * ( rand.nextBoolean() ? 1 : -1);
        int C = (rand.nextInt(20) + 20) * ( rand.nextBoolean() ? 1 : -1);
        double a = (rand.nextInt(150) + 100) / 10000.0;
        double b = (rand.nextInt(150) + 100) / 10000.0;
        double c = (rand.nextInt(150) + 100) / 10000.0;
        for (int i = 0; i < 2048; i++) {
            generatedAnimate[i] = (byte) (
                    A * Math.sin(a * i) +
                            B * Math.sin(b * i) +
                            C * Math.sin(c * i)
            );
        }
        Counter = 0;
    }

    public void draw(Canvas canvas) {

        if (Counter > INTERVAL || generatedAnimate == null)
            generateRandomSine();
        for (int i = 0; i < width; i++) {
            progress[i] = (byte) (generatedAnimate[i] * mInterpolator.VALUES[Counter]);
            canvas.drawLine(i, YPos * sequence + progress[i] - 100, i + 1, YPos * sequence, paint);
        }
        canvas.drawRect(0, YPos * sequence, width, YPos * sequence + YPos, paintRect);
        Counter++;
    }
    public void stopAnim(){
        Counter = 0;
        for (int i = 0; i <width ; i++) {
            generatedAnimate[i] =0;
        }
    }
}
