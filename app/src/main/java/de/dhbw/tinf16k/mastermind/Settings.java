package de.dhbw.tinf16k.mastermind;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;


import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        read();
    }
    private void read()
    {
        String data = "";

        try {
            //new FileOutputStream("testfile", true).close();
            FileOutputStream fOut = openFileOutput("testfile", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write("Hier is ja data drinne!");
            osw.flush();
            osw.close();
            InputStream stream = getApplicationContext().openFileInput("testfile");
            InputStreamReader sr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(sr);
            data = br.readLine();
            Log.d("Mastermind", "Data: " + data);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void save()
    {

    }
    public void chooseColor(View view)
    {
        int farbe = 0xffffff;
        switch(view.getId()) {
            case R.id.b1:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c1); break;}
            case R.id.b2:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c2); break;}
            case R.id.b3:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c3); break;}
            case R.id.b4:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c4); break;}
            case R.id.b5:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c5); break;}
            case R.id.b6:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c6); break;}
            case R.id.b7:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c7); break;}
            case R.id.b8:{farbe = ContextCompat.getColor(getApplicationContext(), R.color.c8); break;}
        }
        ColorPickerDialogBuilder
            .with(this)
            .setTitle("Choose color")
            .initialColor(farbe)
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(15)
            .setOnColorSelectedListener(new OnColorSelectedListener() {
                @Override
                public void onColorSelected(int selectedColor) {
                    //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                }
            })
            .setPositiveButton("ok", new ColorPickerClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                    //changeBackgroundColor(selectedColor);

                }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .build()
            .show();
    }
}
