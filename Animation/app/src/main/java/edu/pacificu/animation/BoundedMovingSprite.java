package edu.pacificu.animation;

import android.content.Context;
import android.view.Display;

import java.util.Random;

public class BoundedMovingSprite extends MovingSprite {

    int MoveX, MoveY;
    int LeftWall, RightWall, TopWall, BottomWall;
    Random mRand = new Random (Double.doubleToLongBits(Math.random()));

    public BoundedMovingSprite(Context context, Display display, int drawable, int topCoord, int leftCoord)
    {
        super(context, display, drawable, topCoord, leftCoord);

        MoveX = (mRand.nextInt () % 25) + 1;
        MoveY = (mRand.nextInt () % 25) + 1;
        LeftWall = 0;
        RightWall = getDisplayWidth() - getSpriteWidth();
        TopWall = 0;
        BottomWall = getDisplayHeight() - getSpriteHeight() - 156;

        if (topCoord > BottomWall)
        {
            mTopCoordinate = BottomWall - getSpriteHeight();
        }

        if (leftCoord > RightWall)
        {
            mLeftCoordinate = RightWall - getSpriteWidth();
        }

    }

    @Override
    public void move ()
    {
        changeDirection();

        mLeftCoordinate += MoveX;
        mTopCoordinate += MoveY;
    }

    private void changeDirection ()
    {
        if (mLeftCoordinate <= LeftWall)
        {
            MoveX *= -1;
        }
        if (mLeftCoordinate >= RightWall)
        {
            MoveX *= -1;
        }
        if (mTopCoordinate <= TopWall)
        {
            MoveY *= -1;
        }
        if (mTopCoordinate >= BottomWall)
        {
            MoveY *= -1;
        }
    }

}
