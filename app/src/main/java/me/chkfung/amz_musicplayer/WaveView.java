package me.chkfung.amz_musicplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chkfu_000 on 10/6/2016.
 */

public class WaveView extends View {
    public byte[] getMBytes() {
        return MBytes;
    }

    public void setMBytes(byte[] MBytes) {
        this.MBytes = MBytes;
        invalidate();
    }

    private byte[] MBytes ;


    Paint mLayer2 = BasicPainter(R.color.PinkDepth2);
    Paint mLayer3 = BasicPainter(R.color.PinkDepth3);
    Paint mLayer4 = BasicPainter(R.color.PinkDepth4);
    Paint mLayer5 = BasicPainter(R.color.PinkDepth5);

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    private void init() {
//        Paint mLayer1 = new Paint();
//        mLayer1.setColor(ContextCompat.getColor(getContext(), R.color.PinkDepth1));
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint Pen2 = new Paint();
        Pen2.setColor(Color.BLACK);
        Pen2.setTextSize(50f);
        Paint mLayer1 = new Paint();
        mLayer1.setColor(ContextCompat.getColor(getContext(), R.color.PinkDepth1));
        Paint[] mPaint = {mLayer1, mLayer2, mLayer3, mLayer4, mLayer5};

        canvas.drawRect(0, getMeasuredHeight() / 5 * 4 - 100, getMeasuredWidth(), getMeasuredHeight(), mPaint[0]);
        if (MBytes != null) {
            for (int level = 1; level < 5; level++) {
                for (int BeginX = 0; BeginX < MBytes.length; BeginX ++) {
                    int BeginY = getMeasuredHeight() / 5 * (4 - level);
                    float waveHeight = (float) (
                             MBytes[BeginX]>0?MBytes[BeginX]:MBytes[BeginX]
                            //mbytes[BeginX]
                            +getMeasuredHeight() / 5)+ getMeasuredHeight() / 8;
//                        *(1+level/5);
                    canvas.drawLine(BeginX, BeginY - 100, BeginX, BeginY + waveHeight, mPaint[level]);
                }
            }

            Paint Pen1 = new Paint();
            Pen1.setColor(Color.BLACK);
            Pen1.setStyle(Paint.Style.STROKE);
            Pen1.setStrokeWidth(1f);
            Pen1.setAntiAlias(true);
            Path bezierPath1 = new Path();
            bezierPath1.moveTo(0,800);
            System.out.println("Start Here");
            for (int i = 0; i <MBytes.length-10; i+=1) {
                int preY = MBytes[i]>0? MBytes[i]: MBytes[i]+256;
                int aftY = MBytes[i+1]>0? MBytes[i+1]: MBytes[i+1]+256;
                bezierPath1.quadTo(i,preY + 128,i+1,aftY+128);
//                canvas.drawLine(i,Math.abs(mBytes[i]),i+1,Math.abs(mBytes[i+1]),Pen1);
//                System.out.println(i +" ; "+mBytes[i]);
            }

            System.out.println("End Here");
            canvas.drawPath(bezierPath1,Pen1);
        }
    }

    private Paint BasicPainter(@ColorRes int mColorRes) {
        //Convert to Color Int for Paint after retrieve from Resource
        int mColor = ContextCompat.getColor(getContext(), mColorRes);
        Paint mBasicPaint = new Paint();
        mBasicPaint.setStyle(Paint.Style.STROKE);
        mBasicPaint.setStrokeWidth(2);
        mBasicPaint.setColor(mColor);
        mBasicPaint.setAntiAlias(true);
        return mBasicPaint;
    }
}
