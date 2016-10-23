package me.chkfung.amz_musicplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by chkfu_000 on 10/1/2016.
 */

public class AmzWaveView extends View {
    public void setPath(Path path) {
        this.path = path;
        invalidate();
    }

    private Path path = new Path();

    private byte[] mBytes = new byte[]{};
    private float A = 50f;

    public float getA() {
        return A;
    }

    public void setA(float a) {
        A = a;
    }

    public float getB() {
        return B;
    }

    public void setB(float b) {
        B = b;
    }

    public float getC() {
        return C;
    }

    public void setC(float c) {
        C = c;
        invalidate();
    }

    private float B = 0f;
    private float C = 0.04f;

    private float randomAmplitude = 50f;
    private float mAmplitude = 50f;

    public float getWaveLength() {
        return mWaveLength;
    }

    public void setWaveLength(float mWaveLength) {
        this.mWaveLength = mWaveLength;
        invalidate();
    }

    private float mWaveLength = 0.02f;

    public float getAmplitude() {
        return mAmplitude;
    }

    public void setAmplitude(float mAmplitude) {
        this.mAmplitude = mAmplitude;
        invalidate();
    }

    int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.GRAY};

    /**
     * +------------------------+
     * |<--wave length->        |______
     * |   /\          |   /\   |  |
     * |  /  \         |  /  \  | amplitude
     * | /    \        | /    \ |  |
     * |/      \       |/      \|__|____
     * |        \      /        |  |
     * |         \    /         |  |
     * |          \  /          |  |
     * |           \/           | water level
     * |                        |  |
     * |                        |  |
     * +------------------------+__|____
     */
    public AmzWaveView(Context context) {
        super(context);
    }

    public AmzWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AmzWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint Pen1 = new Paint();
    Path bezierPath1 = new Path();
    private void init(){
        Pen1.setColor(Color.BLACK);
        Pen1.setStyle(Paint.Style.STROKE);
        Pen1.setStrokeWidth(1f);
        Pen1.setAntiAlias(true);
    }
    public void setupMusicPlayer(byte[] bytess){
        mBytes = bytess;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //Paint From Bottom to top
        Paint mLayer1 = new Paint();
        mLayer1.setColor(ContextCompat.getColor(getContext(), R.color.PinkDepth1));
        Paint mLayer2 = BasicPainter(R.color.PinkDepth2);
        Paint mLayer3 = BasicPainter(R.color.PinkDepth3);
        Paint mLayer4 = BasicPainter(R.color.PinkDepth4);
        Paint mLayer5 = BasicPainter(R.color.PinkDepth5);

        Paint[] mPaint = {mLayer1, mLayer2, mLayer3, mLayer4, mLayer5};
        /*  Drawing from bottom to top to cover the wave empty area
        * +----------------+
        * | Level 4
        * | |   Color Depth 5
        * | |   Wave
        * | Level 3
        * | |   Color Depth 4
        * | |   Wave
        * | Level 2
        * | |   Color Depth 3
        * | |   Wave
        * | Level 1
        * | |   Color Depth 2
        * | |   Wave
        * | Rect Color Depth 1
        * +----------------+
        * */
        canvas.drawRect(0, getMeasuredHeight() / 5 * 4 - 100, getMeasuredWidth(), getMeasuredHeight(), mPaint[0]);

        for (int level = 1; level < 5; level++) {
            double previousValueY = 0;
            for (int BeginX = 0; BeginX < getMeasuredWidth(); BeginX += 2) {
                int BeginY = getMeasuredHeight() / 5 * (4 - level);
                double currentValueY = Math.sin(BeginX * mWaveLength) + 2 * Math.sin(BeginX / mAmplitude);
//                float waveHeight = (float) (getMeasuredHeight() / 5 + currentValueY * randomAmplitude);
//                if (previousValueY - currentValueY < 0 && previousValueY < 0 && previousValueY > -0.01)
//                    randomAmplitude = (float) Math.random() * mAmplitude;
//                previousValueY = currentValueY;
//                System.out.println(Math.random());

//                float waveHeight = (float) (getMeasuredHeight() / 5 + currentValueY* mAmplitude);
                //A*sin(a*x) + B*sin(b*x) + C*sin(c*x)
//                float waveHeight =  (float) (mAmplitude*Math.sin(BeginX*0.01 )
//                        +mAmplitude/2*Math.cos(BeginX*0.02 )
//                        +mAmplitude/3*Math.sin(BeginX*0.03 )
//                        +getMeasuredHeight() / 5) ;

                float waveHeight = (float) (
                        A * Math.sin(BeginX * 0.01f)
                                + B * Math.sin(BeginX * 0.02f)
                                + C * Math.sin(BeginX * 0.03f)
//                        +B*Math.sin(BeginX*0.04f )

                                + getMeasuredHeight() / 5);
//                        *(1+level/5);
                canvas.drawLine(BeginX, BeginY - 100, BeginX, BeginY + waveHeight, mPaint[level]);
            }
        }
        canvas.drawPath(path,Pen1);

//        canvas.drawLine(0,0,0,500,bezierLayer1);
//        Path bezierPath1 = new Path();
//        bezierPath1.moveTo(0,100);
//        bezierPath1.quadTo(getMeasuredWidth()/4,0,getMeasuredWidth()/2,200);
//        bezierPath1.quadTo(getMeasuredWidth()/4*3,50,getMeasuredWidth(),0);
//        canvas.drawPath(bezierPath1,bezierLayer1);

//        if(mBytes.length>0){
//            bezierPath1 = new Path();
//            bezierPath1.moveTo(0,800);
//            System.out.println("Start Here");
//            for (int i = 0; i <mBytes.length/4 ; i+=1) {
//                int preY = mBytes[i]>0? mBytes[i]: mBytes[i]+256;
//                int aftY = mBytes[i+1]>0? mBytes[i+1]: mBytes[i+1]+256;
//                bezierPath1.quadTo(i*5,preY + 128,i*5+5,aftY+128);
////                canvas.drawLine(i,Math.abs(mBytes[i]),i+1,Math.abs(mBytes[i+1]),Pen1);
////                System.out.println(i +" ; "+mBytes[i]);
//            }
//
//            System.out.println("End Here");
//            canvas.drawPath(bezierPath1,Pen1);
//        }
//        System.out.println("Draw ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
}
