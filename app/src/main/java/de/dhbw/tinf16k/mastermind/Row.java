package de.dhbw.tinf16k.mastermind;

import android.content.Context;
import android.widget.LinearLayout;

class Row extends LinearLayout{
    private int holeNumber, rightPlaces, rightColors;
    private int balls[] = new int[8]; //Contains the ball variation of the row, a maximum of 8 Balls

    public Row(Context context) {
        super(context);
    }

    public int[] getBalls() {
        return balls;
    }

    public void setBalls(int[] balls) {
        this.balls = balls;
    }

    public void setBall(int ball, int color) {
        this.balls[ball] = color;
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
