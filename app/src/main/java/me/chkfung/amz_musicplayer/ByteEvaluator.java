package me.chkfung.amz_musicplayer;

import android.animation.TypeEvaluator;

/**
 * Created by chkfu_000 on 10/6/2016.
 */

public class ByteEvaluator implements TypeEvaluator<byte[]> {
    @Override
    public byte[] evaluate(float fraction, byte[] startValue, byte[] endValue) {
        for (byte item: endValue) {

        }
        return endValue;
    }
}
