package de.dhbw.tinf16k.mastermind;

import android.content.Context;
import android.widget.LinearLayout;

class Row extends LinearLayout{
    private int balls[] = new int[8]; //Contains the ball variation of the row, a maximum of 8 Balls
    private int whitePins, blackPins;

    public Row(Context context) {
        super(context);
    }

    public int[] getBalls() {
        return balls;
    }

    public void setBalls(int[] balls) {
        this.balls = balls;
    }

    public int getWhitePins() {
        return whitePins;
    }

    public void setWhitePins(int whitePins) {
        this.whitePins = whitePins;
    }

    public int getBlackPins() {
        return blackPins;
    }

    public void setBlackPins(int blackPins) {
        this.blackPins = blackPins;
    }
}
