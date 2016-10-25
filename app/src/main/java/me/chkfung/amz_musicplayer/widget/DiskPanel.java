package me.chkfung.amz_musicplayer.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import me.chkfung.amz_musicplayer.R;

/**
 * Created by chkfu_000 on 10/24/2016.
 */

public class DiskPanel extends View {
    Paint borderPaint;
    Paint innerPaint;
    Drawable AlbumImage;
    int width = 0;
    int height = 0;
    int radius = 0;
    private final int INNER_PADDING = 40;
    private final int IMAGE_BORDER_PADDING = 100;

    private int angle = 0;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
        invalidate();
    }

    public DiskPanel(Context context) {
        super(context);
        init();
    }

    public DiskPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiskPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width / 2, height / 2, radius, borderPaint);
        canvas.drawArc(INNER_PADDING, INNER_PADDING, width - INNER_PADDING, height - INNER_PADDING, angle + 0, 112, false, borderPaint);
        canvas.drawArc(INNER_PADDING, INNER_PADDING, width - INNER_PADDING, height - INNER_PADDING, angle + 200, 67, false, borderPaint);

        canvas.drawArc(IMAGE_BORDER_PADDING, IMAGE_BORDER_PADDING, width - IMAGE_BORDER_PADDING, height - IMAGE_BORDER_PADDING, angle + 0, 270, false, innerPaint);
        AlbumImage.setBounds(IMAGE_BORDER_PADDING + 10, IMAGE_BORDER_PADDING + 10, width - IMAGE_BORDER_PADDING - 10, height - IMAGE_BORDER_PADDING - 10);
        AlbumImage.draw(canvas);
    }

    private void init() {
        borderPaint = new Paint();
        borderPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        borderPaint.setStrokeWidth(10);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeCap(Paint.Cap.ROUND);

        innerPaint = new Paint();
        innerPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        innerPaint.setStrokeWidth(5);
        innerPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeCap(Paint.Cap.ROUND);
        innerPaint.setAntiAlias(true);

        AlbumImage = ContextCompat.getDrawable(getContext(), R.drawable.album_circle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = (width - 20) / 2;
    }
}
