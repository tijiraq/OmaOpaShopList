package com.example.omaopashoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>(); //Listspeicher
    int omaszaehler = 0, opasZaehler = 0; //Listzaehler
    ShopItem itemList=new ShopItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_Label);
        //Bigen shared Prefrences Variabeln------------
        SharedPreferences sp = getSharedPreferences("MySharedPrefrences", 0);
        String omaOpa = sp.getString("OmaOpa","");
        String ware= sp.getString("Ware","");
        int menge = sp.getInt("Menge",0);
        boolean wichtigSp = sp.getBoolean("Wichtig", false);
        Toast.makeText(getApplicationContext(), omaOpa+", "+ware+", "+ String.valueOf(menge)+", "+String.valueOf(wichtigSp), Toast.LENGTH_LONG).show();
        //End-----------------
        //SQL Begin----
        SQLiteDatabase db =null;
        try{
            db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Einkaufliste(ItemNr INTEGER, Person TEXT, Ware TEXT, Menge INTEGER, Wichtigkeit TEXT, PRIMARY KEY (ItemNr) )");

        }
        catch (Exception ex){ }
//        finally {
//            db.close();
//        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items); //Listspeicher mit dem Adapter binden
        RadioGroup group = findViewById(R.id.radioGroup); //Radiogroup Object
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

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sp = getSharedPreferences("MySharedPrefrences", 0);
        EditText ware = (EditText) findViewById(R.id.text1); // Edittext von Ware einbinden
        EditText menge = (EditText)findViewById(R.id.text2);// Edittext von Menge einbinden
        CheckBox wichtig = findViewById(R.id.checkbox); // Checckbox von Wichtig einbinden
        RadioGroup omaOpa= findViewById(R.id.radioGroup);//Radiogroup  von Oma oder Opaeinbinden
        int buttonId = omaOpa.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) findViewById(buttonId);
        SharedPreferences.Editor editor = sp.edit(); //Shared Preferences Editor

        int mengeSp=0; String omaOpaSp="";
        String wareSp = ware.getText().toString();
        if(!menge.getText().toString().equals(""))
            mengeSp= Integer.parseInt(menge.getText().toString());
        boolean wichtigSp= wichtig.isChecked();
        if(button!=null)
             omaOpaSp = button.getText().toString();
        // in shared Preferces speichern
        editor.putString("OmaOpa", omaOpaSp);
        editor.putString("Ware", wareSp);
        editor.putInt("Menge", mengeSp);
        editor.putBoolean("Wichtig", wichtigSp);
        editor.commit();


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

                SQLiteDatabase db =null;
                try{
                    db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE,null);
                    db.execSQL("INSERT INTO Einkaufliste (Person, Ware, Menge, Wichtigkeit) VALUES ('"+itemList.getPerson()+"','" +itemList.getWare()+"','"+ itemList.getMenge()+"','"+ itemList.wichtigToString()+"');");


                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show(); // exception massage as toast
                }
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
    //MenuItem
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sqlactivity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.showListActivity) {
            startActivity (new Intent(this, ListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}