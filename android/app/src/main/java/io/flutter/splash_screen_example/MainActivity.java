package io.flutter.splash_screen_example;

//import android.os.Bundle;
//import io.flutter.app.FlutterActivity;
//import io.flutter.plugins.GeneratedPluginRegistrant;
import android.content.Context;
import android.view.View;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.SplashScreen;

public class MainActivity extends FlutterActivity {
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    GeneratedPluginRegistrant.registerWith(this);
//  }

  @Override
  public SplashScreen provideSplashScreen() {
    return new SplashScreen() {
      SplashScreenView splashScreenView;

      @Override
      public View createSplashView(Context context) {
        if (splashScreenView == null) {
          splashScreenView = new SplashScreenView(context);
        }
        return splashScreenView;
      }

      @Override
      public void transitionToFlutter(Runnable onTransitionCompleter) {
        splashScreenView.transitionOut(onTransitionCompleter);
      }
    };
  }
}
