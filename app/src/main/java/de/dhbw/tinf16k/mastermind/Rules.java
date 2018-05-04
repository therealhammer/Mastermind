package de.dhbw.tinf16k.mastermind;

/*
    Erstellt von Spencer
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebView;

import java.io.File;

public class Rules extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
        /*WebView browser = (WebView) findViewById(R.id.webV);
        File webFile = new File(Environment.getDataDirectory() + "/test.html");
        browser.loadUrl("file:///" + webFile.getAbsolutePath());*/
    }
}
