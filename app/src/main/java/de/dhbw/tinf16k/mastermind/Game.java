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
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.LinkedList;
import java.util.List;

public class Game extends Activity implements View.OnClickListener {
    private int iAnzahlFarben, iAnzahlStellen, iAnzahlRunden;
    private boolean bLeereStellen, bFarbenMehrfach, bComGame;
    private List<LinearLayout> rowList = new LinkedList<>();
    private int iBtnSize;
    private Drawable drawableDefaultCircle;
    private LinearLayout.LayoutParams lpBtnLayout, lpEvalPegLayout;
    private LinearLayout llGameBoard;

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

        iBtnSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        lpBtnLayout = new LinearLayout.LayoutParams(iBtnSize, iBtnSize);
        lpBtnLayout.setMargins(iBtnSize / 15, iBtnSize / 15, iBtnSize / 15, iBtnSize / 15);
        lpEvalPegLayout = new LinearLayout.LayoutParams(iBtnSize / 2, iBtnSize / 2);
        lpEvalPegLayout.setMargins(iBtnSize / 15, iBtnSize / 15, iBtnSize / 15, iBtnSize / 15);
        drawableDefaultCircle = getDrawable(R.drawable.circle);
        drawableDefaultCircle.setTint(getResources().getColor(R.color.white));

        createRow();
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
            tmpButton.setBackground(drawableDefaultCircle);
            tmpButton.setLayoutParams(lpBtnLayout);
            rowList.get(rowList.size() - 1).addView(tmpButton);
        }

        LinearLayout tmpEval = new LinearLayout(this);
        tmpEval.setOrientation(LinearLayout.VERTICAL);

        LinearLayout tmpEvalRow1 = new LinearLayout(this);
        LinearLayout tmpEvalRow2 = new LinearLayout(this);

        for (int i = 0; i < iAnzahlStellen; i++) {
            ImageView tmpEvalPeg = new ImageView(this);
            tmpEvalPeg.setId(i);
            tmpEvalPeg.setBackground(drawableDefaultCircle);
            tmpEvalPeg.setLayoutParams(lpEvalPegLayout);
            if (i < iAnzahlStellen / 2)
                tmpEvalRow1.addView(tmpEvalPeg);
            else
                tmpEvalRow2.addView(tmpEvalPeg);
        }
        //rowList.get(rowList.size() - 1).addView(tmpEvalRow1);
        tmpEval.addView(tmpEvalRow1);
        tmpEval.addView(tmpEvalRow2);
        rowList.get(rowList.size() - 1).addView(tmpEval);

        llGameBoard.addView(rowList.get(rowList.size() - 1));
    }
}
