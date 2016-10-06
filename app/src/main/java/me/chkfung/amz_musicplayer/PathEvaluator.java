package me.chkfung.amz_musicplayer;

import android.animation.TypeEvaluator;
import android.graphics.Path;

/**
 * Created by chkfu_000 on 10/6/2016.
 */

public class PathEvaluator implements TypeEvaluator<Path> {
    @Override
    public Path evaluate(float fraction, Path startValue, Path endValue) {
        return endValue;
    }
}
