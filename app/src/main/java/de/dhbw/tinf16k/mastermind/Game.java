package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Game extends Activity {
    private int iAnzahlFarben, iAnzahlStellen, iAnzahlRunden;
    private boolean bLeereStellen, bFarbenMehrfach;

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
        }

        TextView testTv = findViewById(R.id.testText);
        String sTestString = "Anz. F.: " + String.valueOf(iAnzahlFarben) +
                "\nAnz. Stellen: " + String.valueOf(iAnzahlStellen) +
                "\nleere Stellen: " + String.valueOf(bLeereStellen) +
                "\nFarben mehrfach: " + String.valueOf(bFarbenMehrfach) +
                "\nAnz. Runden: " + String.valueOf(iAnzahlRunden);
        testTv.setText(sTestString);
    }
}
