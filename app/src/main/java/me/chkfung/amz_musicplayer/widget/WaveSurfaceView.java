package me.chkfung.amz_musicplayer.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import me.chkfung.amz_musicplayer.R;

/**
 * Created by chkfu_000 on 10/24/2016.
 */

public class WaveSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final Handler mHandler = new Handler();
    private static int BUBBLESIZE = 30;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private boolean mIsDrawing;
    private
    Thread thread = new Thread(this);

    Bubble[] BubbleArray1;
    Bubble[] BubbleArray2;
    Bubble[] BubbleArray3;
    Bubble[] BubbleArray4;


    Wave wave1;
    Wave wave2;
    Wave wave3;
    Wave wave4;

    public WaveSurfaceView(Context context) {
        super(context);
        init();
    }

    public WaveSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        thread.start();
        System.out.println("SURFACE CREATED");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        System.out.println("SURFACE CHANGED");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
        System.out.println("SURFACE DESTROYED");
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            try {
                canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    Paint mSolid1 = SolidPainter(R.color.PinkDepth5);
                    Paint mSolid5 = SolidPainter(R.color.PinkDepth1);
                    canvas.drawRect(0, 0, getWidth(), getHeight() / 5, mSolid1);
                    wave1.draw(canvas);
                    wave2.draw(canvas);
                    wave3.draw(canvas);
                    wave4.draw(canvas);
                    canvas.drawRect(0, 4 * getHeight() / 5, getWidth(), getHeight(), mSolid5);
                    for (int i = 0; i < BUBBLESIZE; i++) {
                        BubbleArray1[i].draw(canvas);
                        BubbleArray2[i].draw(canvas);
                        BubbleArray3[i].draw(canvas);
                        BubbleArray4[i].draw(canvas);
                    }
                }
            } catch (IllegalArgumentException e) {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
                System.out.println("Failure :" + e.getMessage());
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);

            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int heightPortion = h / 5;
        wave1.init(w, heightPortion);
        wave2.init(w, heightPortion);
        wave3.init(w, heightPortion);
        wave4.init(w, heightPortion);
        for (int i = 0; i < BUBBLESIZE; i++) {
            BubbleArray1[i].init(w, heightPortion);
            BubbleArray2[i].init(w, heightPortion);
            BubbleArray3[i].init(w, heightPortion);
            BubbleArray4[i].init(w, heightPortion);
        }
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        Paint mLayer1 = new Paint();
        mLayer1.setColor(ContextCompat.getColor(getContext(), R.color.PinkDepth5));

        Paint mStroke2 = StrokePainter(R.color.PinkDepth4);
        Paint mStroke3 = StrokePainter(R.color.PinkDepth3);
        Paint mStroke4 = StrokePainter(R.color.PinkDepth2);
        Paint mStroke5 = StrokePainter(R.color.PinkDepth1);

        Paint mSolid2 = SolidPainter(R.color.PinkDepth4);
        Paint mSolid3 = SolidPainter(R.color.PinkDepth3);
        Paint mSolid4 = SolidPainter(R.color.PinkDepth2);
        Paint mSolid5 = SolidPainter(R.color.PinkDepth1);

//        CirclePaint = new Paint[]{mCircle2, mCircle3, mCircle4, mCircle5};
//
        wave1 = Wave.create(mStroke2, mSolid2, 1);
        wave2 = Wave.create(mStroke3, mSolid3, 2);
        wave3 = Wave.create(mStroke4, mSolid4, 3);
        wave4 = Wave.create(mStroke5, mSolid5, 4);

        BubbleArray1 = new Bubble[BUBBLESIZE];
        BubbleArray2 = new Bubble[BUBBLESIZE];
        BubbleArray3 = new Bubble[BUBBLESIZE];
        BubbleArray4 = new Bubble[BUBBLESIZE];

        for (int i = 0; i < BUBBLESIZE; i++) {
            BubbleArray1[i] = Bubble.create(mSolid2, 1);
            BubbleArray2[i] = Bubble.create(mSolid3, 2);
            BubbleArray3[i] = Bubble.create(mSolid4, 3);
            BubbleArray4[i] = Bubble.create(mSolid5, 4);
        }
    }

    public void StopAnim() {
//
//        mIsDrawing = false;
//        wave1.stopAnim();
//        wave2.stopAnim();
//        wave3.stopAnim();
//        wave4.stopAnim();
//        for (int i = 0; i < BUBBLESIZE; i++) {
//            BubbleArray1[i].reset();
//            BubbleArray2[i].reset();
//            BubbleArray3[i].reset();
//            BubbleArray4[i].reset();
//        }
    }

    public void ResumeAnim() {
//        mIsDrawing = true;
//        thread.start();
    }
//    private void draw() {
//        try {
//            canvas = surfaceHolder.lockCanvas();
//            Paint mSolid1 = SolidPainter(R.color.PinkDepth5);
//            Paint mSolid5 = SolidPainter(R.color.PinkDepth1);
//            canvas.drawRect(0, 0, getWidth(), getHeight() / 5, mSolid1);
////            wave1.draw(canvas);
////            wave2.draw(canvas);
////            wave3.draw(canvas);
////            wave4.draw(canvas);
////            canvas.drawRect(0, 4 * getHeight() / 5, getWidth(), getHeight(),mSolid5);
//            for (int i = 0; i < BUBBLESIZE; i++) {
//                BubbleArray1[i].draw(canvas);
//                BubbleArray2[i].draw(canvas);
//                BubbleArray3[i].draw(canvas);
//                BubbleArray4[i].draw(canvas);
//            }
//        } catch (Exception e) {
//            System.out.println("Failure :" + e.getMessage());
//        } finally {
//            if (canvas != null)
//                surfaceHolder.unlockCanvasAndPost(canvas);
////            mHandler.postDelayed(this,2000);
//
//        }
//    }

    private Paint StrokePainter(@ColorRes int mColorRes) {
        //Convert to Color Int for Paint after retrieve from Resource
        int mColor = ContextCompat.getColor(getContext(), mColorRes);
        Paint mBasicPaint = new Paint();
        mBasicPaint.setStyle(Paint.Style.STROKE);
        mBasicPaint.setStrokeWidth(2);
        mBasicPaint.setColor(mColor);
        return mBasicPaint;
    }

    private Paint SolidPainter(@ColorRes int mColorRes) {
        //Convert to Color Int for Paint after retrieve from Resource
        int mColor = ContextCompat.getColor(getContext(), mColorRes);
        Paint mCirclePainter = new Paint();
        mCirclePainter.setAntiAlias(true);
        mCirclePainter.setStyle(Paint.Style.FILL);
        mCirclePainter.setColor(mColor);
        return mCirclePainter;
    }
}
