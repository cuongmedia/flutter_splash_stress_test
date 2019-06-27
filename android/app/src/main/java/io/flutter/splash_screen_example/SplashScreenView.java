package io.flutter.splash_screen_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SplashScreenView extends FrameLayout {
  private static final String TAG = "SplashScreenView";

  private View whiteUnderlay;
  private ImageView imageView;

  public SplashScreenView(Context context) {
    super(context);

    whiteUnderlay = new View(getContext());
    whiteUnderlay.setBackgroundColor(Color.WHITE);
    addView(whiteUnderlay);

    imageView = new ImageView(getContext());
    imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash_screen, getContext().getTheme()));
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(imageView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  public void transitionOut(Runnable onTransitionComplete) {
    animateWhiteUnderlay();
    animateLogoOverlay(onTransitionComplete);
  }

  private void animateWhiteUnderlay() {
    AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
    fadeOut.setStartOffset(0);
    fadeOut.setDuration(500);

    whiteUnderlay.startAnimation(fadeOut);
  }

  @SuppressLint("NewApi")
  private void animateLogoOverlay(Runnable onTransitionComplete) {
    Animation scaleUp = new ScaleAnimation(
        1f, 8f,
        1f, 8f,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f
    );
    scaleUp.setFillAfter(true);
    scaleUp.setDuration(500);
    scaleUp.setInterpolator(new AccelerateInterpolator());

    AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
    fadeOut.setStartOffset(400);
    fadeOut.setDuration(100);

    AnimationSet animationSet = new AnimationSet(false);
    animationSet.addAnimation(scaleUp);
    animationSet.addAnimation(fadeOut);
    animationSet.setFillAfter(true);
    animationSet.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
        animationSet.setAnimationListener(null);
        onTransitionComplete.run();
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });

    imageView.startAnimation(animationSet);
  }
}
