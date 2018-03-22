package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class SpielVorbereitung extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spiel_vorbereitung);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.btn_spiel_erstellen){
            Intent i = new Intent(SpielVorbereitung.this, SpielDurchfuehrung.class);
            startActivity(i);
        }
    }
}
