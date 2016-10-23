package me.chkfung.amz_musicplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chkfu_000 on 10/18/2016.
 */

public class mWaveView extends View {
    
    private static final Handler mHandler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
               invalidate();
        }
    };
    private static final int BubbleSize = 30;
    Random rand = new Random();

    private byte[] animatedY;

    public byte[] getAnimatedY() {
        return animatedY;
    }

    public void setAnimatedY(byte[] animatedY) {
        this.animatedY = animatedY;
        invalidate();
//        postInvalidateDelayed(2000);
    }

    List<Float> amp = new ArrayList<Float>();
    Paint[] mPaint;
    Paint[] CirclePaint;
    Bubble[] BubbleArray1;
    Bubble[] BubbleArray2;
    Bubble[] BubbleArray3;
    Bubble[] BubbleArray4;

    Wave wave1;
    Wave wave2;
    Wave wave3;
    Wave wave4;
    public mWaveView(Context context) {
        super(context);
        init();
    }

    public mWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public mWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int mStartY = getHeight() / mPaint.length;

//        for (int i = 0; i < mPaint.length; i++) {
//            for (int j = 0; j < getWidth(); j++) {
//                byte amplitude = 0;
//                if (animatedY != null)
//                    amplitude = animatedY[j];
////                float amplitude = -(float) (Math.sin(0.01 * j) + Math.sin(0.02 * j)) * 50 - 140;
//                canvas.drawLine(j, mStartY * i + amplitude - 100, j + 1, mStartY * (i + 1), mPaint[i]);
//            }
//
//        }
        wave1.draw(canvas);
        wave2.draw(canvas);
        wave3.draw(canvas);
        wave4.draw(canvas);
        for (int i = 0; i < BubbleArray1.length; i++) {
            BubbleArray1[i].draw(canvas);
        }
        for (int i = 0; i < BubbleArray2.length; i++) {
            BubbleArray2[i].draw(canvas);
        }
        for (int i = 0; i < BubbleArray3.length; i++) {
            BubbleArray3[i].draw(canvas);
        }
        for (int i = 0; i < BubbleArray4.length; i++) {
            BubbleArray4[i].draw(canvas);
        }
        mHandler.postDelayed(runnable,15);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        Paint mLayer1 = new Paint();
        mLayer1.setColor(ContextCompat.getColor(getContext(), R.color.PinkDepth5));
        Paint mLayer2 = BasicPainter(R.color.PinkDepth4);
        Paint mLayer3 = BasicPainter(R.color.PinkDepth3);
        Paint mLayer4 = BasicPainter(R.color.PinkDepth2);
        Paint mLayer5 = BasicPainter(R.color.PinkDepth1);
        mPaint = new Paint[]{mLayer1, mLayer2, mLayer3, mLayer4, mLayer5};

        Paint mCircle2 = CirclePainter(R.color.PinkDepth4);
        Paint mCircle3 = CirclePainter(R.color.PinkDepth3);
        Paint mCircle4 = CirclePainter(R.color.PinkDepth2);
        Paint mCircle5 = CirclePainter(R.color.PinkDepth1);

        CirclePaint = new Paint[]{mCircle2, mCircle3, mCircle4, mCircle5};

        wave1 = Wave.create(mLayer2,mCircle2,1024,300);
        wave2 = Wave.create(mLayer3,mCircle3,1024,600);
        wave3 = Wave.create(mLayer4,mCircle4,1024,900);
        wave4 = Wave.create(mLayer5,mCircle5,1024,1200);

        BubbleArray1 = new Bubble[BubbleSize];
        for (int i = 0; i < BubbleArray1.length; i++) {
            BubbleArray1[i] = Bubble.create(mCircle2, 1024, 300);
        }
        BubbleArray2 = new Bubble[BubbleSize];
        for (int i = 0; i < BubbleArray1.length; i++) {
            BubbleArray2[i] = Bubble.create(mCircle3, 1024, 600);
        }
        BubbleArray3 = new Bubble[BubbleSize];
        for (int i = 0; i < BubbleArray1.length; i++) {
            BubbleArray3[i] = Bubble.create(mCircle4, 1024, 900);
        }
        BubbleArray4 = new Bubble[BubbleSize];
        for (int i = 0; i < BubbleArray1.length; i++) {
            BubbleArray4[i] = Bubble.create(mCircle5, 1024, 1200);
        }
    }

    private Paint BasicPainter(@ColorRes int mColorRes) {
        //Convert to Color Int for Paint after retrieve from Resource
        int mColor = ContextCompat.getColor(getContext(), mColorRes);
        Paint mBasicPaint = new Paint();
        mBasicPaint.setStyle(Paint.Style.STROKE);
        mBasicPaint.setStrokeWidth(2);
        mBasicPaint.setColor(mColor);
        return mBasicPaint;
    }

    private Paint CirclePainter(@ColorRes int mColorRes) {
        //Convert to Color Int for Paint after retrieve from Resource
        int mColor = ContextCompat.getColor(getContext(), mColorRes);
        Paint mCirclePainter = new Paint();
        mCirclePainter.setAntiAlias(true);
        mCirclePainter.setStyle(Paint.Style.FILL);
        mCirclePainter.setColor(mColor);
        return mCirclePainter;
    }
}
