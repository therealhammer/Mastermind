package de.dhbw.tinf16k.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

class Pop extends Activity implements View.OnClickListener{
    private int colorNumber, holeNumber;
    private boolean emptyHoleAllowed, doubleColorAllowed, againstComputer;
    int btnSize = 30;
    Row r;
    private Button ballSelection[];
    private Drawable drawableCircle[] = new Drawable[9];
    private LinearLayout.LayoutParams lpSelectionBtnLayout;
    private LinearLayout llPegSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        llPegSelection = findViewById(R.id.llPegSelection2);
        Bundle extras = getIntent().getExtras();
        r = new Row (this, holeNumber, this, btnSize);
        if (extras != null) {
            colorNumber = extras.getInt("Color-Number");
            holeNumber = extras.getInt("Hole-Number");
            emptyHoleAllowed = extras.getBoolean("empty-Hole-allowed");
            doubleColorAllowed = extras.getBoolean("double-Color-allowed");
        }
        createBallSelection();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8),(int)( height * 0.5));

        //createRow();
    }
    public void Close(View view)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("Colors", r.getBallsInt());
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onClick(View v) {
        int clickedBtnId = v.getId();

            for (View ballSelectBtn : ballSelection)
                if (v == ballSelectBtn)
                    selectBall(ballSelectBtn.getId(), holeNumber);


            for (View ballRowButton : r.getBalls())
                if (v == ballRowButton)
                    r.unSetBall(ballRowButton.getId());

    }
    private void createBallSelection() {
        ballSelection = new Button[colorNumber];
        for (int i = 0; i < colorNumber; i++) {
            ballSelection[i] = new Button(this);
            ballSelection[i].setId(i + 1);
            //ballSelection[i].setOnClickListener(this);
            ballSelection[i].setBackground(drawableCircle[i + 1]);
            ballSelection[i].setLayoutParams(lpSelectionBtnLayout);
            llPegSelection.addView(ballSelection[i]);
        }
    }
    private void selectBall(int colorNumberOfBall, int numberOfHoles) {
        boolean setBalls[] = r.getSetBalls();
        int i = 0;
        while (setBalls[i]) {
            i++;
            if (i >= numberOfHoles)
                return;
        }

        r.setBall(i, colorNumberOfBall);
    }
    /*private void createRow() {
        rowList.add(new Row(this, holeNumber, this, btnSize));
        Row r = new Row(this, holeNumber, this, btnSize);

        LinearLayout tmpEval = new LinearLayout(this);
        tmpEval.setOrientation(LinearLayout.VERTICAL);

        LinearLayout tmpEvalRow1 = new LinearLayout(this);
        LinearLayout tmpEvalRow2 = new LinearLayout(this);

        for (int i = 0; i < holeNumber; i++) {
            ImageView tmpEvalPin = new ImageView(this);
            tmpEvalPin.setId(i + 943284);
            tmpEvalPin.setBackground(drawableCircle[0]);
            tmpEvalPin.setLayoutParams(lpEvalPinLayout);
            if (i < holeNumber / 2)
                tmpEvalRow1.addView(tmpEvalPin);
            else
                tmpEvalRow2.addView(tmpEvalPin);
        }
        tmpEval.addView(tmpEvalRow1);
        tmpEval.addView(tmpEvalRow2);
        rowList.get(rowList.size() - 1).addView(tmpEval);

        llGameBoard.addView(rowList.get(rowList.size() - 1));
    }*/
}
