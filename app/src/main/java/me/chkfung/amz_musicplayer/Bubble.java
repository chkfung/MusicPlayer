package me.chkfung.amz_musicplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by chkfu_000 on 10/21/2016.
 */

public class Bubble {

    private final Random random;
    private final Paint paint;
    private final Point position;
    private final int radius;
    private final int width;
    private final int height;
    private static final int HeightRange = 200;
    public static Bubble create(Paint paint, int width, int height) {
        Random random = new Random();
        return new Bubble(random, paint, width, height);
    }

    Bubble(Random random, Paint paint, int width, int height) {
        this.height = height;
        this.width = width;
        this.random = random;
        this.paint = paint;
        this.position = new Point(random.nextInt(width), height + random.nextInt(HeightRange));
        this.radius = random.nextInt(10)+5;
    }

    private void move() {
        position.y -=5;
    }

    public void draw(Canvas canvas) {
        move();
        if(position.y <= height- HeightRange)
            reset();
        canvas.drawCircle(position.x,position.y,radius,paint);
    }
    public void reset(){
        position.y=height + random.nextInt(HeightRange);
        position.x = random.nextInt(width);
    }
}

