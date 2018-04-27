package de.dhbw.tinf16k.mastermind;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;



public class Settings extends Activity {

    public static final String prefname = "settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        SeekBar sb = (SeekBar)findViewById(R.id.sbRunden);
        final EditText et = (EditText)findViewById(R.id.nfAnzahlRunden);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser)
            {
                et.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar sb)
            {

            }
            @Override
            public void onStopTrackingTouch(SeekBar sb)
            {

            }
        });
        read();
    }
    private void read()
    {
        SharedPreferences prefs = getSharedPreferences("de.dhbw.tinf16k.mastermind", Context.MODE_PRIVATE);
        RadioGroup colorRadio = (RadioGroup) findViewById(R.id.rgAnzahlFarben);
        switch (prefs.getInt("colorNumber", 5))
        {
            case 5: colorRadio.check(R.id.rbFarben5);break;
            case 6: colorRadio.check(R.id.rbFarben6);break;
            case 8: colorRadio.check(R.id.rbFarben8);break;
        }
        RadioGroup holeRadio = (RadioGroup) findViewById(R.id.rgAnzahlStellen);
        switch (prefs.getInt("holeNumber", 3))
        {
            case 3: colorRadio.check(R.id.rbStellen3);break;
            case 4: colorRadio.check(R.id.rbStellen4);break;
            case 5: colorRadio.check(R.id.rbStellen5);break;
            case 6: colorRadio.check(R.id.rbStellen6);break;
            case 8: colorRadio.check(R.id.rbStellen8);break;
        }
        CheckBox emptyHoleCB = (CheckBox)findViewById(R.id.cbLeereStellen);
        if(prefs.getBoolean("emptyHoleAllowed", false)==true) emptyHoleCB.setChecked(true);
        else emptyHoleCB.setChecked(false);
        CheckBox doubleColorCB = (CheckBox)findViewById(R.id.cbFarbenMehrfach);
        if(prefs.getBoolean("doubleColorAllowed", false)==true) doubleColorCB.setChecked(true);
        else doubleColorCB.setChecked(false);
        EditText rowNumber = (EditText)findViewById(R.id.nfAnzahlRunden);
        rowNumber.setText(String.valueOf(prefs.getInt("rowNumber", 20)));
        CheckBox gameModeCB = (CheckBox)findViewById(R.id.cbComGame);
        if(prefs.getBoolean("gameModeFlagHuman", false)==true) gameModeCB.setChecked(true);
        else gameModeCB.setChecked(false);
        Button b1 = (Button)findViewById(R.id.b1);
        b1.setBackgroundColor(prefs.getInt("c1", getResources().getColor(R.color.c1)));
        Button b2 = (Button)findViewById(R.id.b2);
        b2.setBackgroundColor(prefs.getInt("c2", getResources().getColor(R.color.c2)));
        Button b3 = (Button)findViewById(R.id.b3);
        b3.setBackgroundColor(prefs.getInt("c3", getResources().getColor(R.color.c3)));
        Button b4 = (Button)findViewById(R.id.b4);
        b4.setBackgroundColor(prefs.getInt("c4", getResources().getColor(R.color.c4)));
        Button b5 = (Button)findViewById(R.id.b5);
        b5.setBackgroundColor(prefs.getInt("c5", getResources().getColor(R.color.c5)));
        Button b6 = (Button)findViewById(R.id.b6);
        b6.setBackgroundColor(prefs.getInt("c6", getResources().getColor(R.color.c6)));
        Button b7 = (Button)findViewById(R.id.b7);
        b7.setBackgroundColor(prefs.getInt("c7", getResources().getColor(R.color.c7)));
        Button b8 = (Button)findViewById(R.id.b8);
        b8.setBackgroundColor(prefs.getInt("c8", getResources().getColor(R.color.c8)));
    }
    public void onClick(View view)
    {
        Log.d("Mastermind", "clicked");
        writeFile();
    }


    private void writeFile()
    {
        SharedPreferences prefs = getSharedPreferences("de.dhbw.tinf16k.mastermind", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        RadioGroup colorRadio = (RadioGroup)findViewById(R.id.rgAnzahlFarben);
        switch (colorRadio.getCheckedRadioButtonId())
        {
            case R.id.rbFarben5: edit.putInt("colorNumber", 5);break;
            case R.id.rbFarben6: edit.putInt("colorNumber", 6);break;
            case R.id.rbFarben8: edit.putInt("colorNumber", 8);break;
        }
        RadioGroup holeRadio = (RadioGroup)findViewById(R.id.rgAnzahlStellen);
        switch (colorRadio.getCheckedRadioButtonId())
        {
            case R.id.rbStellen3: edit.putInt("holeNumber", 3);break;
            case R.id.rbStellen4: edit.putInt("holeNumber", 4);break;
            case R.id.rbStellen5: edit.putInt("holeNumber", 5);break;
            case R.id.rbStellen6: edit.putInt("holeNumber", 6);break;
            case R.id.rbStellen8: edit.putInt("holeNumber", 8);break;
        }
        CheckBox emptyHoleCB = (CheckBox)findViewById(R.id.cbLeereStellen);
        if(emptyHoleCB.isChecked() == true) edit.putBoolean("emptyHoleAllowed", true);
        else edit.putBoolean("emptyHoleAllowed", false);
        CheckBox doubleColorCB = (CheckBox)findViewById(R.id.cbFarbenMehrfach);
        if(doubleColorCB.isChecked() == true) edit.putBoolean("doubleColorAllowed", true);
        else edit.putBoolean("doubleColorAllowed", false);
        EditText rowNumber = (EditText)findViewById(R.id.nfAnzahlRunden);
        int RN = Integer.valueOf(rowNumber.getText().toString());
        edit.putInt("rowNumber", RN);
        CheckBox gameModeCB = (CheckBox)findViewById(R.id.cbComGame);
        if(gameModeCB.isChecked() == true) edit.putBoolean("gameModeFlagHuman", true);
        else edit.putBoolean("gameModeFlagHuman", false);

        edit.commit();

    }
    public void chooseColor(final View view)
    {
        SharedPreferences prefs = getSharedPreferences("de.dhbw.tinf16k.mastermind", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = prefs.edit();
        int farbe = 0xffffff;
        switch(view.getId()) {
            case R.id.b1:{farbe = prefs.getInt("c1", R.color.c1); break;}
            case R.id.b2:{farbe = prefs.getInt("c2", R.color.c2); break;}
            case R.id.b3:{farbe = prefs.getInt("c3", R.color.c3); break;}
            case R.id.b4:{farbe = prefs.getInt("c4", R.color.c4); break;}
            case R.id.b5:{farbe = prefs.getInt("c5", R.color.c5); break;}
            case R.id.b6:{farbe = prefs.getInt("c6", R.color.c6); break;}
            case R.id.b7:{farbe = prefs.getInt("c7", R.color.c7); break;}
            case R.id.b8:{farbe = prefs.getInt("c8", R.color.c8); break;}
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
                    Log.d("Mastermind", "C selected");
                }
            })
            .setPositiveButton(R.string.ok, new ColorPickerClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                    switch(view.getId())
                    {
                        case R.id.b1: edit.putInt("c1", selectedColor);break;
                        case R.id.b2: edit.putInt("c2", selectedColor);break;
                        case R.id.b3: edit.putInt("c3", selectedColor);break;
                        case R.id.b4: edit.putInt("c4", selectedColor);break;
                        case R.id.b5: edit.putInt("c5", selectedColor);break;
                        case R.id.b6: edit.putInt("c6", selectedColor);break;
                        case R.id.b7: edit.putInt("c7", selectedColor);break;
                        case R.id.b8: edit.putInt("c8", selectedColor);break;
                    }
                    edit.commit();
                    read();
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
