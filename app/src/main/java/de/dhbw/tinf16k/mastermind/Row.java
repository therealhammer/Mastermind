package de.dhbw.tinf16k.mastermind;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.LinearLayout;

class Row extends LinearLayout{
    private int rightPlaces, rightColors;
    private boolean setBalls[];
    private Button balls[]; //Contains the ball variation of the row, a maximum of 8 Balls
    private Drawable drawableCircle[] = new Drawable[9];

    private LinearLayout.LayoutParams lpBtnLayout;

    public Row(Context context, int holeNumber, OnClickListener ocListenr, int btnSize) {
        super(context);

        lpBtnLayout = new LayoutParams(btnSize, btnSize);
        lpBtnLayout.setMargins(btnSize / 15, btnSize / 15, btnSize / 15, btnSize / 15);

        for (int i = 0; i < 9; i++) {
            drawableCircle[i] = context.getDrawable(R.drawable.circle);
        }
        drawableCircle[8].setTint(getResources().getColor(R.color.c8));
        drawableCircle[1].setTint(getResources().getColor(R.color.c1));
        drawableCircle[2].setTint(getResources().getColor(R.color.c2));
        drawableCircle[3].setTint(getResources().getColor(R.color.c3));
        drawableCircle[4].setTint(getResources().getColor(R.color.c4));
        drawableCircle[5].setTint(getResources().getColor(R.color.c5));
        drawableCircle[6].setTint(getResources().getColor(R.color.c6));
        drawableCircle[7].setTint(getResources().getColor(R.color.c7));
        drawableCircle[0].setTint(getResources().getColor(R.color.c0));

        balls= new Button[holeNumber];
        setBalls = new boolean[holeNumber];

        for (int i = 0; i < holeNumber; i++) {
            balls[i] = new Button(context);
            balls[i].setOnClickListener(ocListenr);
            balls[i].setBackground(drawableCircle[0]);
            balls[i].setLayoutParams(lpBtnLayout);

            this.addView(balls[i]);
        }
    }

    public Button[] getBalls() {
        return balls;
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
