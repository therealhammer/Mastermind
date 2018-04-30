package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class Licenses extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licenses);
        TextView tv = (TextView)findViewById(R.id.version);
        tv.setText("Version: " + BuildConfig.VERSION_NAME);
    }
}
