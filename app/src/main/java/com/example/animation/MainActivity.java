package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ObjectAnimator mAnimatorRotate1;
    ObjectAnimator mAnimatorMove;
    ObjectAnimator mAnimatorFade;
    ObjectAnimator mAnimatorFadeIn;
    ObjectAnimator mAnimatorMoveDown;
    ObjectAnimator mAnimatorMoveRotate2;
    ObjectAnimator mAnimatorX;
    ObjectAnimator mAnimatorY;
    ObjectAnimator mAnimatorFadeZomb;
    ObjectAnimator mAnimatorFadePea;

    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgView = findViewById(R.id.img);
        Button btn = findViewById(R.id.btn_start);
        ImageView projectile = findViewById(R.id.projectile) ;
        ImageView zombie = findViewById(R.id.zombie) ;
        mAnimatorRotate1 = ObjectAnimator.ofFloat(imgView,"rotation",360);
        mAnimatorMove = ObjectAnimator.ofFloat(projectile,"x",460);
        mAnimatorMoveDown = ObjectAnimator.ofFloat(imgView,"y",1450);
        mAnimatorMoveRotate2 = ObjectAnimator.ofFloat(zombie,"rotation",90);
        mAnimatorFade = ObjectAnimator.ofFloat(imgView,"alpha",0);
        mAnimatorFadeZomb = ObjectAnimator.ofFloat(zombie,"alpha",0);
        mAnimatorFadePea = ObjectAnimator.ofFloat(projectile,"alpha",0);
        mAnimatorFadeIn = ObjectAnimator.ofFloat(imgView,"alpha",1);
        mAnimatorX = ObjectAnimator.ofFloat(imgView,"x",450);
        mAnimatorY = ObjectAnimator.ofFloat(imgView,"y",450);
        mAnimatorFadeIn = ObjectAnimator.ofFloat(imgView,"alpha",1);

        mAnimatorFadeIn.setStartDelay(2000);

        mAnimatorFadePea.setStartDelay(250);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimatorRotate1.setDuration(500);

//                animatorSet.play(mAnimatorRotate1).with(mAnimatorMove).before(mAnimatorFadeIn).before(mAnimatorFade).before(mAnimatorMoveRotate2).before(mAnimatorMoveDown);
                animatorSet.play(mAnimatorRotate1).with(mAnimatorMove).before(mAnimatorMoveRotate2).before(mAnimatorFadeZomb).with(mAnimatorFadePea);
                animatorSet.start();

            }
        });
    }
}