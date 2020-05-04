package com.example.omaopashoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<String> items = new ArrayList<>();
    String[] input = new String[4];
    int omaszaehler = 0, opasZaehler = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("OmaOpaShopList");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        RadioGroup group = findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangeListener());
        CheckBox wichtig = findViewById(R.id.checkbox);
        wichtig.setOnCheckedChangeListener(new MyCheckboxOnCheckedChangeListener());
        Button ok = findViewById(R.id.button1);
        ok.setOnClickListener(new MyButtonListener());
        Button clear = findViewById(R.id.button2);
        clear.setOnClickListener(new MyButtonListenerClear());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);


    }

    class MyRadioGroupOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int buttonId = group.getCheckedRadioButtonId();
            RadioButton button = (RadioButton) findViewById(buttonId);
            if(checkedId!=-1){
            String ausgabe = (String) button.getText().toString() + ", ";
            input[0] = ausgabe + ", ";
            }
        }
    }

    class MyCheckboxOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        public void onCheckedChanged(CompoundButton button, boolean isChecked) {

            if (isChecked)
                input[3] = ", ganz wichtig!";
            else
                input[3] = "";
        }

    }

    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            String aus = "";
            String omaSt="Omas List: ", opaSt="Opas List: ";
            RadioGroup group = findViewById(R.id.radioGroup);
            EditText ware = findViewById(R.id.text1);
            EditText wv = findViewById(R.id.text2);
            if((group.getCheckedRadioButtonId())==-1||ware.getText().toString().equals("")||wv.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "empty fields!", Toast.LENGTH_LONG).show();
            else{
                input[1] = ware.getText().toString() + ", ";
                input[2] = wv.getText().toString();
                for (int i = 0; i < 4; i++)
                    aus += input[i];

                if (input[0].contains("Oma")) {
                    omaszaehler++;
                }
                else if (input[0].contains("Opa")) {
                    opasZaehler++;
                }
                Toast.makeText(getApplicationContext(), "added to the List", Toast.LENGTH_LONG).show();
                TextView oma = findViewById(R.id.text3);
                oma.setText(omaSt.concat(Integer.toString(omaszaehler)));
                TextView opa = findViewById(R.id.text4);
                opa.setText(opaSt.concat(Integer.toString(opasZaehler)));
                items.add(aus);
            }
        }
    }

    class MyButtonListenerClear implements View.OnClickListener {
        public void onClick(View v) {
            EditText ware = findViewById(R.id.text1);
            ware.setText("");
            EditText wv = findViewById(R.id.text2);
            wv.setText("");
            RadioGroup group = findViewById(R.id.radioGroup);
            group.setOnCheckedChangeListener(null);
            group.clearCheck();
            CheckBox wichtig = findViewById(R.id.checkbox);
            wichtig.setChecked(false);
            Toast.makeText(getApplicationContext(), "clear", Toast.LENGTH_LONG).show();

        }
    }


}