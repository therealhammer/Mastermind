package de.dhbw.tinf16k.mastermind;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.BufferedReader;
import java.io.FileOutputStream;
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
        writeFile("<mastermind>\n");
        readFile();
    }
    private void writeFile(String data)
    {
        try {

            FileOutputStream fOut = openFileOutput("testfile", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write("Hier sind deine Daten drin!\n asdofoasdklj\n asdkfj\nasd");
            osw.flush();
            osw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readFile()
    {
        String data = "";
        String xml = "";
        try {

            InputStream stream = getApplicationContext().openFileInput("testfile");
            InputStreamReader sr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(sr);
            while((data = br.readLine()) != null)
            {
                Log.d("Mastermind", "Data: " + data);
                xml += data;
            }
            br.close();
            return xml;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
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
            .setPositiveButton(R.string.ok, new ColorPickerClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                    //changeBackgroundColor(selectedColor);

                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .build()
            .show();
    }
}
