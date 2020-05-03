package com.example.omaopashoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<String> items = new ArrayList<>();
    public String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("OmaOpaShopList");
        RadioGroup group = findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangeListener());
        EditText ware = findViewById(R.id.text1);
        input += ware.getText().toString() + ", ";
        EditText wv = findViewById(R.id.text2);
        input += ware.getText().toString() + ", ";
        CheckBox wichtig = findViewById(R.id.checkbox);
        wichtig.setOnCheckedChangeListener(new MyCheckboxOnCheckedChangeListener());
        Button ok =findViewById(R.id.button1);
        Button clear =findViewById(R.id.button2);



    }

    class MyRadioGroupOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int buttonId = group.getCheckedRadioButtonId();
            RadioButton button = (RadioButton) findViewById(buttonId);
            String ausgabe = (String) button.getText() + ", ";
            input += ausgabe;

        }
    }

    class MyCheckboxOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        public void onCheckedChanged(CompoundButton button, boolean isChecked) {

            if (isChecked)
                input += "ganz wichtig!";
        }

    }
    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            items.add(input);


        }
    }

}