package de.dhbw.tinf16k.mastermind;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.LinearLayout;

class Row extends LinearLayout {
    private int rightPlaces, rightColors;
    private boolean setBalls[];
    private Button balls[]; //Contains the ball variation of the row, a maximum of 8 Balls
    private Drawable drawableCircle[] = new Drawable[9];

    private LinearLayout.LayoutParams lpBtnLayout;

    public Row(Context context){
        super(context);
    }

    public Row(Context context, int holeNumber, OnClickListener ocListenr, int btnSize) {
        super(context);

        lpBtnLayout = new LayoutParams(btnSize, btnSize);
        lpBtnLayout.setMargins(btnSize / 15, btnSize / 15, btnSize / 15, btnSize / 15);

        for (int i = 0; i < 9; i++) {
            drawableCircle[i] = context.getDrawable(R.drawable.circle);
        }
        drawableCircle[0].setTint(getResources().getColor(R.color.c0));
        drawableCircle[1].setTint(getResources().getColor(R.color.c1));
        drawableCircle[2].setTint(getResources().getColor(R.color.c2));
        drawableCircle[3].setTint(getResources().getColor(R.color.c3));
        drawableCircle[4].setTint(getResources().getColor(R.color.c4));
        drawableCircle[5].setTint(getResources().getColor(R.color.c5));
        drawableCircle[6].setTint(getResources().getColor(R.color.c6));
        drawableCircle[7].setTint(getResources().getColor(R.color.c7));
        drawableCircle[8].setTint(getResources().getColor(R.color.c8));

        balls = new Button[holeNumber];
        setBalls = new boolean[holeNumber];

        for (int i = 0; i < holeNumber; i++) {
            balls[i] = new Button(context);
            balls[i].setId(i);
            balls[i].setOnClickListener(ocListenr);
            balls[i].setBackground(drawableCircle[0]);
            balls[i].setLayoutParams(lpBtnLayout);

            this.addView(balls[i]);
        }
    }

    public Button[] getBalls() {
        return balls;
    }

    public int[] getBallsInt()
    {
        int[] ints = new int[8];
        for(int i = 0; i < 8; i++)
        {
            if(setBalls[i] == true) {
                if (balls[i].getBackground() == drawableCircle[0]) ints[i] = 0;
                if (balls[i].getBackground() == drawableCircle[1]) ints[i] = 1;
                if (balls[i].getBackground() == drawableCircle[2]) ints[i] = 2;
                if (balls[i].getBackground() == drawableCircle[3]) ints[i] = 3;
                if (balls[i].getBackground() == drawableCircle[4]) ints[i] = 4;
                if (balls[i].getBackground() == drawableCircle[5]) ints[i] = 5;
                if (balls[i].getBackground() == drawableCircle[6]) ints[i] = 6;
                if (balls[i].getBackground() == drawableCircle[7]) ints[i] = 7;
                if (balls[i].getBackground() == drawableCircle[8]) ints[i] = 8;
            }
            else ints[i] = 0;
        }
        return ints;
    }

    public Button getBall(int ball) {
        return balls[ball];
    }

    public boolean[] getSetBalls() {
        return setBalls;
    }

    public void setBalls(Button[] balls) {
        this.balls = balls;
    }

    public void setBall(int ball, int color) {
        this.balls[ball].setBackground(drawableCircle[color]);
        setBalls[ball] = true;
    }

    public void unSetBall(int ball) {
        this.balls[ball].setBackground(drawableCircle[0]);
        setBalls[ball] = false;
    }

    public int getRightPlaces() {
        return rightPlaces;
    }

    public void setRightPlaces(int rightPlaces) {
        this.rightPlaces = rightPlaces;
    }

    public int getRightColors() {
        return rightColors;
    }

    public void setRightColors(int rightColors) {
        this.rightColors = rightColors;
    }
}
