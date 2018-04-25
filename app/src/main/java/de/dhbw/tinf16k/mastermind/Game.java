package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

public class Game extends Activity implements View.OnClickListener {
    private int colorNumber, holeNumber, rowNumber;
    private boolean emptyHoleAllowed, doubleColorAllowed;
    private Row masterCode;
    private float score;
    public int gameModeFlag;
    private List<Row> rowList;
    private int iBtnSize;
    private Drawable drawableCircle[] = new Drawable[9];
    private LinearLayout.LayoutParams lpBtnLayout, lpSelectionBtnLayout, lpEvalPinLayout;
    private LinearLayout llGameBoard, llPegSelection;

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
            gameModeFlag = extras.getBoolean("Game-Mode-Flag") ? 1 : 0;//Total bogus to use an int, but it is stated so in class diagram
        }

        llGameBoard = findViewById(R.id.llBoard);
        llPegSelection = findViewById(R.id.llPegSelection);

        iBtnSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 26, getResources().getDisplayMetrics());
        lpEvalPinLayout = new LinearLayout.LayoutParams(iBtnSize / 2, iBtnSize / 2);
        lpEvalPinLayout.setMargins(iBtnSize / 15, iBtnSize / 15, iBtnSize / 15, iBtnSize / 15);
        lpBtnLayout = new LinearLayout.LayoutParams(iBtnSize, iBtnSize);
        lpBtnLayout.setMargins(iBtnSize / 15, iBtnSize / 15, iBtnSize / 15, iBtnSize / 15);
        lpSelectionBtnLayout = new LinearLayout.LayoutParams(iBtnSize, iBtnSize);
        lpSelectionBtnLayout.setMargins(iBtnSize / 15, iBtnSize / 15, iBtnSize / 15, iBtnSize / 15);

        for (int i=0; i<9; i++) {
            drawableCircle[i] = getDrawable(R.drawable.circle);
        }
        drawableCircle[0].setTint(getResources().getColor(R.color.white));
        drawableCircle[1].setTint(getResources().getColor(R.color.c1));
        drawableCircle[2].setTint(getResources().getColor(R.color.c2));
        drawableCircle[3].setTint(getResources().getColor(R.color.c3));
        drawableCircle[4].setTint(getResources().getColor(R.color.c4));
        drawableCircle[5].setTint(getResources().getColor(R.color.c5));
        drawableCircle[6].setTint(getResources().getColor(R.color.c6));
        drawableCircle[7].setTint(getResources().getColor(R.color.c7));
        drawableCircle[8].setTint(getResources().getColor(R.color.c8));

        createRow();
        createPegSelection();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCommitTurn) {
            createRow();
        }
    }

    private void createRow() {
        rowList.add(new Row(this));
        for (int i = 0; i < holeNumber; i++) {
            Button tmpButton = new Button(this);
            tmpButton.setId(i);
            tmpButton.setOnClickListener(this);
            tmpButton.setBackground(drawableCircle[0]);
            tmpButton.setLayoutParams(lpBtnLayout);
            rowList.get(rowList.size() - 1).addView(tmpButton);
        }

        LinearLayout tmpEval = new LinearLayout(this);
        tmpEval.setOrientation(LinearLayout.VERTICAL);

        LinearLayout tmpEvalRow1 = new LinearLayout(this);
        LinearLayout tmpEvalRow2 = new LinearLayout(this);

        for (int i = 0; i < holeNumber; i++) {
            ImageView tmpEvalPin = new ImageView(this);
            tmpEvalPin.setId(i);
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

    private void createPegSelection() {
        for (int i = 0; i < colorNumber; i++) {
            Button tmpButton = new Button(this);
            tmpButton.setId(i);
            tmpButton.setOnClickListener(this);
            tmpButton.setBackground(drawableCircle[i+1]);
            tmpButton.setLayoutParams(lpBtnLayout);
            llPegSelection.addView(tmpButton);
        }
    }
}
