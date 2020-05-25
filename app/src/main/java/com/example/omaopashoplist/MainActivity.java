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
    ArrayList<String> items = new ArrayList<>(); //Listspeicher
    int omaszaehler = 0, opasZaehler = 0; //Listzaehler
    ShopItem itemList=new ShopItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_Label);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items); //Listspeicher mit dem Adapter binden
        RadioGroup group = findViewById(R.id.radioGroup); //Radiogroup
        group.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangeListener());
        CheckBox wichtig = findViewById(R.id.checkbox);//checkbox
        wichtig.setOnCheckedChangeListener(new MyCheckboxOnCheckedChangeListener());
        Button ok = findViewById(R.id.button1);//ok Button
        ok.setOnClickListener(new MyButtonListener());
        Button clear = findViewById(R.id.button2);//clear Button
        clear.setOnClickListener(new MyButtonListenerClear());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Dropdownlists Layout
        Spinner spinner = findViewById(R.id.spinner); //spinner
        spinner.setAdapter(spinnerAdapter); //spinner mit Adapter binden
    }
    //Radiochecklistener
    class MyRadioGroupOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int buttonId = group.getCheckedRadioButtonId();
            RadioButton button = (RadioButton) findViewById(buttonId);
            if(checkedId!=-1){ //falls eine von den beiden ausgewaehlt
            String person = (String) button.getText().toString();
            itemList.setPerson(person);
            }
        }
    }
    //Checkboxlistener
    class MyCheckboxOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton button, boolean isChecked) {
            if (isChecked)
                itemList.setWichtig(true);
            else
                itemList.setWichtig(false);
        }
    }
    //OK-Button Listener
    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            String aus = ""; //hilfsvar.
            String omaSt="Omas List: ", opaSt="Opas List: "; //zwei hilfsvar. um auf dem Textview einzugeben
            RadioGroup group = findViewById(R.id.radioGroup);//binde Radiogroup
            EditText ware = findViewById(R.id.text1);//binde den Edittext von "Ware"
            EditText wv = findViewById(R.id.text2);//binde den Edittext von "Wie Viel"
            if((group.getCheckedRadioButtonId())==-1||ware.getText().toString().equals("")||wv.getText().toString().equals("")) //falls die Felder leer sind
                Toast.makeText(getApplicationContext(), "empty fields!", Toast.LENGTH_LONG).show();
            else{
                itemList.setWare(ware.getText().toString());
                itemList.setMenge(Integer.parseInt(wv.getText().toString()));
               // for (int i = 0; i < 4; i++) //
                 //   aus += input[i]; //alles von hilfsarray in den hilfsvar. speichern

                if (itemList.getPerson().contains("Oma")) { //falls in dem Item das "Oma" enthaelt, dann zaehlt der Omazahler hoch
                    omaszaehler++;
                }
                else if (itemList.getPerson().contains("Opa")) {//falls in dem Item das "Opa" enthaelt, dann zaehlt der Opazahler hoch
                    opasZaehler++;
                }
                Toast.makeText(getApplicationContext(), "added to the List", Toast.LENGTH_LONG).show();
                TextView oma = findViewById(R.id.text3); //setze Textview fuer Oma
                oma.setText(omaSt.concat(Integer.toString(omaszaehler)));
                TextView opa = findViewById(R.id.text4);//setze TextView fuer Opa
                opa.setText(opaSt.concat(Integer.toString(opasZaehler)));
                items.add(itemList.toString()); //fuege in den Listspeicher
            }
        }
    }
    //clear-Button Listiner
    class MyButtonListenerClear implements View.OnClickListener {
        public void onClick(View v) {
            EditText ware = findViewById(R.id.text1);
            ware.setText(""); //setze Edittext1 leer
            EditText wv = findViewById(R.id.text2);
            wv.setText(""); //setze Edittext2 leer
            RadioGroup group = findViewById(R.id.radioGroup);
            group.clearCheck(); //uncheck Radiobutten
            CheckBox wichtig = findViewById(R.id.checkbox);
            wichtig.setChecked(false); //uncheck checkbutton
            Toast.makeText(getApplicationContext(), "clear", Toast.LENGTH_LONG).show();

        }
    }


}