package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class GamePrep extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_prep);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.btn_create_game){
            Intent i = new Intent(GamePrep.this, Game.class);
            startActivity(i);
        }
    }
}
