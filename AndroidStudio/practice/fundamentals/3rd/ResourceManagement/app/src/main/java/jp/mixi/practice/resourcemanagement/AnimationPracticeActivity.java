package jp.mixi.practice.resourcemanagement;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by takafumi.nanao on 2015/02/05.
 */
public class AnimationPracticeActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_practice);

        View imageView = findViewById(R.id.iv_anim_sample);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_sample);
        imageView.startAnimation(animation);
    }
}
