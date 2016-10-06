package me.chkfung.amz_musicplayer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;Visualizer mVisualizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMusic();
    }

    private void initMusic(){
        final AmzWaveView WaveView = (AmzWaveView)findViewById(R.id.WaveView);

        mediaPlayer = MediaPlayer.create(this,R.raw.overyou);
        mediaPlayer.start();

         mVisualizer = new Visualizer(mediaPlayer.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener(){
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int i) {

                Path bezierPath1 = new Path();
                bezierPath1.moveTo(0,800);
                for (int j = 0; j <bytes.length/4 ; j+=1) {
                    int preY = bytes[j]>0? bytes[j]: bytes[j]+256;
                    int aftY = bytes[j+1]>0? bytes[j+1]: bytes[j+1]+256;
                    bezierPath1.quadTo(j*5,preY + 128,j*5+5,aftY+128);
                }
                ObjectAnimator objectAnimator = ObjectAnimator.ofObject(WaveView,"path",new PathEvaluator(),bezierPath1);
                objectAnimator.start();
//                WaveView.setupMusicPlayer(bytes);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int i) {

            }
        };
        mVisualizer.setDataCaptureListener(captureListener,Visualizer.getMaxCaptureRate()/2,true,true);
        mVisualizer.setEnabled(true);
    }
    private void initAnimation(){

        AmzWaveView WaveView = (AmzWaveView)findViewById(R.id.WaveView);

        ObjectAnimator amplitudeAnimator = ObjectAnimator.ofFloat(WaveView,"Amplitude",50f,100f);
        amplitudeAnimator.setDuration(1000);
        amplitudeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        amplitudeAnimator.start();
        ObjectAnimator waveLengthAnimator = ObjectAnimator.ofFloat(WaveView,"WaveLength",0.02f,0.021f);
        waveLengthAnimator.setDuration(1000);
        waveLengthAnimator.setRepeatCount(ValueAnimator.INFINITE);
        waveLengthAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        waveLengthAnimator.start();
        ObjectAnimator RandomAAnimator = ObjectAnimator.ofFloat(WaveView,"A",10f,0f);
        RandomAAnimator.setDuration(1000);
        RandomAAnimator.setRepeatCount(ValueAnimator.INFINITE);
        RandomAAnimator.setRepeatMode(ValueAnimator.REVERSE);
        RandomAAnimator.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator RandomBAnimator = ObjectAnimator.ofFloat(WaveView,"B",30f,0f);
        RandomBAnimator.setDuration(3000);
        RandomBAnimator.setRepeatCount(ValueAnimator.INFINITE);
        RandomBAnimator.setRepeatMode(ValueAnimator.REVERSE);
        RandomBAnimator.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator RandomCAnimator = ObjectAnimator.ofFloat(WaveView,"C",50f,0f);

        RandomCAnimator.setDuration(2000);
        RandomCAnimator.setRepeatCount(ValueAnimator.INFINITE);
        RandomCAnimator.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(amplitudeAnimator,RandomAAnimator,RandomBAnimator,RandomCAnimator);

        animatorSet.start();
    }
}
