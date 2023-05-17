package com.example.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class AnimView extends View {

    private float mRadius;
    private final Paint mPaint =  new Paint();
    private static final int COLOR_ADJUSTED = 5;
    private float mX;
    private float mY;
    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;
    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this, "radius",getWidth(),0);

        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        ObjectAnimator growAgainAnimator = ObjectAnimator.ofFloat(this,"radius",0,getWidth());

        growAgainAnimator.setDuration(ANIMATION_DURATION);
        growAgainAnimator.setStartDelay(ANIMATION_DELAY);
        growAgainAnimator.setRepeatCount(1);
        growAgainAnimator.setRepeatMode(ValueAnimator.REVERSE);

        growAgainAnimator.setInterpolator(new AccelerateInterpolator());
//        animatorSet.play(growAgainAnimator).before(shrinkAnimator);
        animatorSet.play(growAgainAnimator).after(shrinkAnimator);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
            mX = event.getX();
            mY = event.getY();

            if(animatorSet != null && animatorSet.isRunning()){
                animatorSet.cancel();
            }
            animatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX,mY,mRadius,mPaint);
    }

    public void setRadius(float radius){
        mRadius = radius;
        mPaint.setColor(Color.YELLOW + (int) radius/COLOR_ADJUSTED);
        invalidate();
    }
    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
