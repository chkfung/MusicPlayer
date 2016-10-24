package me.chkfung.amz_musicplayer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by chkfu_000 on 10/21/2016.
 */

public class Bubble {

    private int YPos = 0;
    private final int SPEED = 10;
    private final Random random;
    private final Paint paint;
    private Point position;
    private final int radius;
    private int width;
    private final int sequence;
    private int HeightRange;

    public static Bubble create(Paint paint, int sequence) {
        Random random = new Random();
        return new Bubble(random, paint, sequence);
    }

    Bubble(Random random, Paint paint, int sequence) {
        this.sequence = sequence;
        this.random = random;
        this.paint = paint;
        this.radius = random.nextInt(10) + 5;
    }

    public void init(int width, int YPos) {
        HeightRange = YPos/2;
        this.YPos = YPos ;
        this.width = width;
        this.position = new Point(random.nextInt(width), this.YPos*sequence + random.nextInt(HeightRange));
    }

    private void move() {
        position.y -= random.nextInt(5)+3;
    }

    public void draw(Canvas canvas) {
        move();
        if (position.y <= YPos *(sequence-1))
            reset();
        canvas.drawCircle(position.x, position.y, radius, paint);
    }

    public void reset() {
        position.y = YPos*sequence + random.nextInt(HeightRange);
        position.x = random.nextInt(width);
    }

}

