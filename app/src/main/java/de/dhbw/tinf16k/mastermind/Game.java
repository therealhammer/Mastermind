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
    private int iAnzahlFarben, iAnzahlStellen, iAnzahlRunden;
    private boolean bLeereStellen, bFarbenMehrfach, bComGame;
    private List<LinearLayout> rowList = new LinkedList<>();
    private int iBtnSize;
    private Drawable drawableCircle[] = new Drawable[9];
    private LinearLayout.LayoutParams lpBtnLayout, lpEvalPinLayout, lpSelectionBtnLayout;
    private LinearLayout llGameBoard, llPegSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            iAnzahlFarben = extras.getInt("Anzahl-Farben");
            iAnzahlStellen = extras.getInt("Anzahl-Stellen");
            bLeereStellen = extras.getBoolean("leere-Stellen");
            bFarbenMehrfach = extras.getBoolean("Farben-mehrfach");
            iAnzahlRunden = extras.getInt("Anzahl-Runden");
            bComGame = extras.getBoolean("Mensch-gegen-Maschine");
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
        rowList.add(new LinearLayout(this));
        for (int i = 0; i < iAnzahlStellen; i++) {
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

        for (int i = 0; i < iAnzahlStellen; i++) {
            ImageView tmpEvalPin = new ImageView(this);
            tmpEvalPin.setId(i);
            tmpEvalPin.setBackground(drawableCircle[0]);
            tmpEvalPin.setLayoutParams(lpEvalPinLayout);
            if (i < iAnzahlStellen / 2)
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
        for (int i = 0; i < iAnzahlFarben; i++) {
            Button tmpButton = new Button(this);
            tmpButton.setId(i);
            tmpButton.setOnClickListener(this);
            tmpButton.setBackground(drawableCircle[i+1]);
            tmpButton.setLayoutParams(lpBtnLayout);
            llPegSelection.addView(tmpButton);
        }
    }
}
