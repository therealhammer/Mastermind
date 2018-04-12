package de.dhbw.tinf16k.mastermind;

import android.app.Activity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.View;


import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;


public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }
    private void save()
    {

    }
    public void chooseColor(View view)
    {
        ColorPickerDialogBuilder
            .with(this)
            .setTitle("Choose color")
            .initialColor(0x0000aa)
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
