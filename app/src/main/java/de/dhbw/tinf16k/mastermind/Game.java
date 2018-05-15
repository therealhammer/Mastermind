package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game extends Activity implements View.OnClickListener {
    private int colorNumber, holeNumber, rowNumber;
    private boolean emptyHoleAllowed, doubleColorAllowed, againstComputer;
    private int masterCode[];
    private float score;
    private List<Row> rowList;
    private int btnSize;
    private Drawable drawableCircle[] = new Drawable[9];
    private LinearLayout.LayoutParams lpSelectionBtnLayout, lpEvalPinLayout;
    private LinearLayout llGameBoard, llPegSelection;
    private Button ballSelection[];

    public Game() {
        rowList = new LinkedList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            colorNumber = extras.getInt("Color-Number");
            holeNumber = extras.getInt("Hole-Number");
            emptyHoleAllowed = extras.getBoolean("empty-Hole-allowed");
            doubleColorAllowed = extras.getBoolean("double-Color-allowed");
            rowNumber = extras.getInt("Row-Number");
            againstComputer = extras.getBoolean("against-Computer");
        }
        generateCodeBySecondPlayer();

        llGameBoard = findViewById(R.id.llBoard);
        llPegSelection = findViewById(R.id.llPegSelection);

        btnSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 26, getResources().getDisplayMetrics());
        lpEvalPinLayout = new LinearLayout.LayoutParams(btnSize / 2, btnSize / 2);
        lpEvalPinLayout.setMargins(btnSize / 15, btnSize / 15, btnSize / 15, btnSize / 15);
        lpSelectionBtnLayout = new LinearLayout.LayoutParams(btnSize * 15 / 10, btnSize * 15 / 10);
        lpSelectionBtnLayout.setMargins(btnSize / 15, btnSize / 15, btnSize / 15, btnSize / 15);

        for (int i = 0; i < 9; i++) {
            drawableCircle[i] = getDrawable(R.drawable.circle);
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

        masterCode = generateRandomColorCode(colorNumber, holeNumber, emptyHoleAllowed, doubleColorAllowed);
        if (againstComputer == false) generateCodeBySecondPlayer();
        createRow();
        createBallSelection();
    }


    @Override
    public void onClick(View view) {
        int clickedBtnId = view.getId();
        if (clickedBtnId == R.id.btnCommitTurn) {
            validateColorCode();
        } else {
            for (View ballSelectBtn : ballSelection)
                if (view == ballSelectBtn)
                    selectBall(ballSelectBtn.getId(), holeNumber);


            for (View ballRowButton : rowList.get(rowList.size() - 1).getBalls())
                if (view == ballRowButton)
                    rowList.get(rowList.size() - 1).unSetBall(ballRowButton.getId());
        }
    }

    private void createRow() {
        rowList.add(new Row(this, holeNumber, this, btnSize));

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
    }

    private void createBallSelection() {
        ballSelection = new Button[colorNumber];
        for (int i = 0; i < colorNumber; i++) {
            ballSelection[i] = new Button(this);
            ballSelection[i].setId(i + 1);
            ballSelection[i].setOnClickListener(this);
            ballSelection[i].setBackground(drawableCircle[i + 1]);
            ballSelection[i].setLayoutParams(lpSelectionBtnLayout);
            llPegSelection.addView(ballSelection[i]);
        }
    }

    private int[] generateRandomColorCode(int colorNumber, int holeNumber,
                                          boolean emptyHoleAllowed,
                                          boolean doubleColorAllowed) {
        Random r = new Random();
        int randVal, colorVal;
        int masterRow[] = new int[holeNumber];
        colorNumber = emptyHoleAllowed ? colorNumber + 1 : colorNumber;
        if (doubleColorAllowed) {
            for (int i = 0; i < holeNumber; i++) {
                randVal = r.nextInt(colorNumber);
                colorVal = emptyHoleAllowed ? randVal : randVal + 1;
                masterRow[i] = colorVal;
            }
        } else {
            int availableColors[] = new int[colorNumber];
            for (int i = 0; i < colorNumber; i++) {
                availableColors[i] = emptyHoleAllowed ? i : i + 1;
            }
            for (int i = 0, j = colorNumber; i < holeNumber; i++, j--) {
                randVal = r.nextInt(j);
                colorVal = emptyHoleAllowed ? availableColors[randVal] : availableColors[randVal];

                masterRow[i] = colorVal;
                availableColors[randVal] = availableColors[j - 1];
            }
        }
        return masterRow;
    }

    private boolean validateColorCode() {
        createRow();
        return true;
    }

    private boolean compareColorCode(Row masterCode, Row tryCode) {
        if (masterCode.getBallsInt() == tryCode.getBallsInt()) return true;
        else return false;
    }
    private void generateCodeBySecondPlayer() {
        startActivityForResult(new Intent(Game.this, Pop.class), 42);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==42 && resultCode==RESULT_OK)
        {
            masterCode = data.getIntArrayExtra("Colors");
        }
    }

    private float computeScore() {

        return 0;
    }

    private void commitScore() {

    }

    private boolean saveGameDataToFile() {
        return true;
    }

    private void pauseGame() {

    }

    private void stopGame() {

    }

    public void loadGameDataFromFile() {

    }

    private void selectBall(int colorNumberOfBall, int numberOfHoles) {
        boolean setBalls[] = rowList.get(rowList.size() - 1).getSetBalls();
        int i = 0;
        while (setBalls[i]) {
            i++;
            if (i >= numberOfHoles)
                return;
        }

        rowList.get(rowList.size() - 1).setBall(i, colorNumberOfBall);
    }
}
