package me.chkfung.amz_musicplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import me.chkfung.amz_musicplayer.widget.Bubble;
import me.chkfung.amz_musicplayer.widget.Wave;

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
    private static boolean AnimStatus = true;
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

        canvas.drawRect(0, 0, getWidth(), getHeight() / 5, mPaint[0]);
        wave1.draw(canvas);
        wave2.draw(canvas);
        wave3.draw(canvas);
        wave4.draw(canvas);
        canvas.drawRect(0, 4 * getHeight() / 5, getWidth(), getHeight(), CirclePaint[3]);
        for (int i = 0; i < BubbleSize; i++) {
            BubbleArray1[i].draw(canvas);
            BubbleArray2[i].draw(canvas);
            BubbleArray3[i].draw(canvas);
            BubbleArray4[i].draw(canvas);
        }
        if (AnimStatus)
            mHandler.postDelayed(runnable, 15);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void StopAnim() {
        AnimStatus = false;
        if (!AnimStatus ) {
            wave1.stopAnim();
            wave2.stopAnim();
            wave3.stopAnim();
            wave4.stopAnim();
            for (int i = 0; i < BubbleSize; i++) {
                BubbleArray1[i].reset();
                BubbleArray2[i].reset();
                BubbleArray3[i].reset();
                BubbleArray4[i].reset();
            }
        }
    }
    public void ResumeAnim(){
        AnimStatus = true;
        invalidate();
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

        wave1 = Wave.create(mLayer2, mCircle2, 1);
        wave2 = Wave.create(mLayer3, mCircle3, 2);
        wave3 = Wave.create(mLayer4, mCircle4, 3);
        wave4 = Wave.create(mLayer5, mCircle5, 4);

        BubbleArray1 = new Bubble[BubbleSize];
        BubbleArray2 = new Bubble[BubbleSize];
        BubbleArray3 = new Bubble[BubbleSize];
        BubbleArray4 = new Bubble[BubbleSize];

        for (int i = 0; i < BubbleSize; i++) {
            BubbleArray1[i] = Bubble.create(mCircle2, 1);
            BubbleArray2[i] = Bubble.create(mCircle3, 2);
            BubbleArray3[i] = Bubble.create(mCircle4, 3);
            BubbleArray4[i] = Bubble.create(mCircle5, 4);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int heightPortion = h / 5;
        wave1.init(w, heightPortion);
        wave2.init(w, heightPortion);
        wave3.init(w, heightPortion);
        wave4.init(w, heightPortion);
        for (int i = 0; i < BubbleSize; i++) {
            BubbleArray1[i].init(w, heightPortion);
            BubbleArray2[i].init(w, heightPortion);
            BubbleArray3[i].init(w, heightPortion);
            BubbleArray4[i].init(w, heightPortion);
        }
    }
}
