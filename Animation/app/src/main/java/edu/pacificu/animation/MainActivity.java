package edu.pacificu.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
{
    private Display mDisplay;
    private AnimationView mAnimationView;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);

        WindowManager window = getWindowManager ();
        mDisplay = window.getDefaultDisplay ();

        mAnimationView = new AnimationView (this, mDisplay);
        mAnimationView.setBackgroundColor (Color.BLACK);
        setContentView (mAnimationView);
    }
}
