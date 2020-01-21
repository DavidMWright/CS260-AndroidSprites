package edu.pacificu.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Random;

/**
 * Defines the View for displaying the animation.
 *
 * @author Computer Science, Pacific University.
 *
 * @version 1.0
 */
public class AnimationView extends ImageView
{
    Random mRand = new Random (42);

    Display mHolderDisplay;
    FixedSprite mFixedSprite;
    MovingSprite mMovingBallGreen;
    MovingSprite mYellowSprite;
    ArrayList<BoundedMovingSprite> mAManyMovingBalls;
    int currentNumBalls = 0;

    /**
     * Constructor that initializes the values associated with the sprite.
     *
     * @param context
     *          reference to application-specific resources
     *
     * @param display
     *          the display
     *
     * @since 1.0
     */
    public AnimationView (Context context, Display display)
    {
        super (context);
        mHolderDisplay = display;
        setFocusable (true); // make sure we get key events
        mFixedSprite = new FixedSprite (context, display,
                R.drawable.ball_blue, 10, 10);
        mMovingBallGreen = new MovingSprite (context, display,
                R.drawable.ball_green, 20, 20);
        mYellowSprite = new MovingSprite (context, display,
                R.drawable.ball_yellow, 30, 30);
        mAManyMovingBalls = new ArrayList<>();
    }

    /**
     * Draw method that is repeatedly called for animation
     *
     * @param canvas
     *          used to host the draw calls
     *
     * @since 1.0
     */

    @Override
    public void onDraw (Canvas canvas)
    {
        //mFixedSprite.doDraw (canvas);
        //mMovingBallGreen.move ();
       // mYellowSprite.move ();
        //mMovingBallGreen.doDraw (canvas);
       // mYellowSprite.doDraw (canvas);
        for (int i = 0; i < currentNumBalls; i++) {
                mAManyMovingBalls.get(i).move();
                mAManyMovingBalls.get(i).doDraw(canvas);

        }
        super.onDraw (canvas);
        invalidate ();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int holderX, holderY;

        Log.d ("Screen", Integer.toString(getHeight()));

        holderX = (int) event.getX();
        holderY = (int) event.getY();

        int color = mRand.nextInt(4);

        if (color == 1) {
            mAManyMovingBalls.add (new BoundedMovingSprite(super.getContext(),
                    mHolderDisplay, R.drawable.ball_green, holderY, holderX));
        }
        else if (color == 2) {
            mAManyMovingBalls.add (new BoundedMovingSprite(super.getContext(),
                    mHolderDisplay, R.drawable.ball_blue, holderY, holderX));
        }
        else {
            mAManyMovingBalls.add (new BoundedMovingSprite(super.getContext(),
                    mHolderDisplay, R.drawable.ball_yellow, holderY, holderX));
        }
        currentNumBalls++;
        return super.onTouchEvent(event);
    }
}
