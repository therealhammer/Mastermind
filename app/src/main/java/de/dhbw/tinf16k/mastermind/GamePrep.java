package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class GamePrep extends Activity {

    private EditText nfAnzahlRunden;
    private RadioGroup rgAnzahlFarben, rgAnzahlStellen;
    private CheckBox cbLeereStellen, cbFarbenMehrfach, cbComGame;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_prep);

        rgAnzahlFarben = findViewById(R.id.rgAnzahlFarben);
        rgAnzahlStellen = findViewById(R.id.rgAnzahlStellen);
        cbLeereStellen = findViewById(R.id.cbLeereStellen);
        cbFarbenMehrfach = findViewById(R.id.cbFarbenMehrfach);
        SeekBar sbRunden = findViewById(R.id.sbRunden);
        nfAnzahlRunden = findViewById(R.id.nfAnzahlRunden);
        cbComGame = findViewById(R.id.cbComGame);


        rgAnzahlStellen.getCheckedRadioButtonId();
        rgAnzahlFarben.requestFocus();

        nfAnzahlRunden.setText("10");
        sbRunden.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                nfAnzahlRunden.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View v) {
        if (v.getId() == R.id.btn_create_game) {
            RadioButton rbTmp = findViewById(rgAnzahlFarben.getCheckedRadioButtonId());
            int iAnzahlFarben = Integer.parseInt(rbTmp.getText().toString());

            rbTmp = findViewById(rgAnzahlStellen.getCheckedRadioButtonId());
            int iAnzahlStellen = Integer.parseInt(rbTmp.getText().toString());

            boolean bLeereStellen = cbLeereStellen.isChecked();
            boolean bFarbenMehrfach = cbFarbenMehrfach.isChecked();

            int iAnzahlRunden = Integer.parseInt(nfAnzahlRunden.getText().toString());

            boolean bComGame = cbComGame.isChecked();
            Intent i = new Intent(GamePrep.this, Game.class);
            i.putExtra("Anzahl-Farben", iAnzahlFarben);
            i.putExtra("Anzahl-Stellen", iAnzahlStellen);
            i.putExtra("leere-Stellen", bLeereStellen);
            i.putExtra("Farben-mehrfach", bFarbenMehrfach);
            i.putExtra("Anzahl-Runden", iAnzahlRunden);
            i.putExtra("Mensch-gegen-Maschine", bComGame);

            startActivity(i);
        } else if (v.getId() == R.id.rbFarben5) {
        }
    }
}
