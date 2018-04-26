package de.dhbw.tinf16k.mastermind;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.LinearLayout;

class Row extends LinearLayout{
    private int holeNumber, rightPlaces, rightColors;
    private int balls[] = {0,0,0,0,0,0,0,0}; //Contains the ball variation of the row, a maximum of 8 Balls
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

        for (int i = 0; i < holeNumber; i++) {
            Button tmpButton = new Button(context);
            tmpButton.setId(i+3257612);
            tmpButton.setOnClickListener(ocListenr);
            tmpButton.setBackground(drawableCircle[0]);
            tmpButton.setLayoutParams(lpBtnLayout);

            this.addView(tmpButton);
        }
    }

    public int[] getBalls() {
        return balls;
    }

    public void setBalls(int[] balls) {
        this.balls = balls;
    }

    public void setBall(int ball, int color) {
        this.balls[ball] = color;
        Button tmpButton = findViewById(ball+3257612);
        tmpButton.setBackground(drawableCircle[color]);
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
