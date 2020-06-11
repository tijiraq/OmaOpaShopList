package com.example.omaopashoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ListActivity extends Activity {

    String[] items = { "alle anzeigen", "Omas Liste", "Opas Liste", "Datei erzeugen","Datei anzeigen" };  // anzuzeigende Listenelemente
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        setTitle("Daten Bank");
        SQLiteDatabase db = null;
        try {
            ListAdapter demoListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
            // Durch setListAdapter() werden der Adapter und damit die Listenelemente mit der ListActivity verknüpft.
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(demoListAdapter);
            listView.setOnItemClickListener(new ListViewListener());



        } catch (Exception ex) {
        }
        //finally {
//            db.close();
//        }
  }
    private class ListViewListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View datenElement, int position, long id) {

            FileOutputStream fos = null; //OpenFile
            PrintWriter pwr = null;//FileWriter
            String[] columns = {"ItemNr", "Person", "Ware", "Menge", "Wichtigkeit"};
            SQLiteDatabase db = null;
            TextView sqlView = findViewById(R.id.ViewSQL);
            FileInputStream fis = null;
            BufferedReader bin = null;
            StringBuilder text =new StringBuilder();
            CharSequence ausgewaehlterText = ((TextView)datenElement).getText();
        if (ausgewaehlterText.equals("alle anzeigen")) {
            try {
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank
                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", null, null, null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "ItemNr | \tPerson | \tWare | \tMenge | \t Wichtigkeit |\n";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result = result + itemStr + " |\t" + personStr + " |\t" + wareStr + " |\t" + mengStr + " |\t" + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }

                    // Ausgabe des Ergebnisses

                    sqlView.setText(result);

                }

            } catch (Exception ex) {
            }
        }
        else if ("Omas Liste".equals(ausgewaehlterText)) {
            try {
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank

                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", columns, "Person = 'Oma'", null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "ItemNr | \tPerson | \tWare | \tMenge | \t Wichtigkeit |\n";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result = result + itemStr + " |\t" + personStr + " |\t" + wareStr + " |\t" + mengStr + " |\t" + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }

                    // Ausgabe des Ergebnisses

                    sqlView.setText(result);

                }

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();

            }

        }
        else if ("Opas Liste".equals(ausgewaehlterText)) {
            try {
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank

                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", columns, "Person = 'Opa'", null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "ItemNr | \tPerson | \tWare | \tMenge | \t Wichtigkeit |\n";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result = result + itemStr + " |\t" + personStr + " |\t" + wareStr + " |\t" + mengStr + " |\t" + wichtigStr + "\n";
                        } while (ergCursor.moveToNext());
                    }
                    // Ausgabe des Ergebnisses

                    sqlView.setText(result);
                }

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();

            }

        }
        else if ("Datei erzeugen".equals(ausgewaehlterText)) {
            try {
                fos = getApplicationContext().openFileOutput("Einkaufsliste.txt", 0);
                pwr = new PrintWriter(fos);
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank
                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", null, null, null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result += itemStr + ", " + personStr + ", " + wareStr + ", " + mengStr + ", " + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }
                    pwr.println(result);
                    pwr.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Datei wured erstellt", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Fehler beim Öffnen: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Schreiben: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Schliessen: " + e.getClass(), Toast.LENGTH_LONG).show();
            }
        }
        else if ("Datei anzeigen".equals(ausgewaehlterText)) {

            try {
                fis = getApplicationContext().openFileInput("Einkaufsliste.txt");
                bin = new BufferedReader(new InputStreamReader(fis));
                String line="";
                while ((line=bin.readLine())!=null){
                    text.append(line);
                    text.append("\n");
                }
                Toast.makeText(getApplicationContext(), "die Datei wurde gelesen", Toast.LENGTH_LONG).show();
                sqlView.setText(text.toString());
                bin.close();
                fis.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Fehler beim Öffnen: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Lesen: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Schließen: " + e.getClass(), Toast.LENGTH_LONG).show();

            }
        }



    }

    //Listner für alle in der Tebelle anzeigen Button
    class ShowAllListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String[] columns = {"ItemNr", "Person", "Ware", "Menge", "Wichtihkeit"};
            SQLiteDatabase db = null;
            TextView sqlView = findViewById(R.id.ViewSQL);
            try {
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank

                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", null, null, null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "ItemNr | \tPerson | \tWare | \tMenge | \t Wichtigkeit |\n";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result = result + itemStr + " |\t" + personStr + " |\t" + wareStr + " |\t" + mengStr + " |\t" + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }

                    // Ausgabe des Ergebnisses

                    sqlView.setText(result);

                }

            } catch (Exception ex) {
            }
        }
    }

    //Listner für nur Omas Eintraege anzeigen
    class ShowOmaListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String[] columns = {"ItemNr", "Person", "Ware", "Menge", "Wichtigkeit"};
            SQLiteDatabase db = null;
            TextView sqlView = findViewById(R.id.ViewSQL);
            try {
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank

                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", columns, "Person = 'Oma'", null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "ItemNr | \tPerson | \tWare | \tMenge | \t Wichtigkeit |\n";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result = result + itemStr + " |\t" + personStr + " |\t" + wareStr + " |\t" + mengStr + " |\t" + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }

                    // Ausgabe des Ergebnisses

                    sqlView.setText(result);

                }

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();

            }
        }
    }

    //Listner fuer nur Opas Eintraege anzeigen
    class ShowOpaListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String[] columns = {"ItemNr", "Person", "Ware", "Menge", "Wichtigkeit"};
            SQLiteDatabase db = null;
            TextView sqlView = findViewById(R.id.ViewSQL);
            try {
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank

                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", columns, "Person = 'Opa'", null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "ItemNr | \tPerson | \tWare | \tMenge | \t Wichtigkeit |\n";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result = result + itemStr + " |\t" + personStr + " |\t" + wareStr + " |\t" + mengStr + " |\t" + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }

                    // Ausgabe des Ergebnisses

                    sqlView.setText(result);

                }

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();

            }
        }
    }

    //Listner fuer ein Datei erstellen
    class CreateFileListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db = null;
            TextView sqlView = findViewById(R.id.ViewSQL);
            FileOutputStream fos = null; //OpenFile
            PrintWriter pwr = null;//FileWriter

            try {
                fos = getApplicationContext().openFileOutput("Einkaufsliste.txt", 0);
                pwr = new PrintWriter(fos);
                db = openOrCreateDatabase("EinkauflisteDB", MODE_PRIVATE, null);  // Öffnen der Datenbank
                Cursor ergCursor;  // Cursor zum Durchlaufen des Ergebnisses
                ergCursor = db.query(true, "Einkaufliste", null, null, null, null, null, null, null);
                if (ergCursor != null) {
                    // Durchlaufen des Ergebnis-Cursors und Zusammenstellen der Ausgabe
                    int itemIndex = ergCursor.getColumnIndexOrThrow("ItemNr");
                    int personIndex = ergCursor.getColumnIndexOrThrow("Person");
                    int wareIndex = ergCursor.getColumnIndexOrThrow("Ware");
                    int mengIndex = ergCursor.getColumnIndexOrThrow("Menge");
                    int wichtigIndex = ergCursor.getColumnIndexOrThrow("Wichtigkeit");
                    String result = "";
                    if (ergCursor.moveToFirst()) {
                        do {
                            String itemStr = ergCursor.getString(itemIndex);
                            String personStr = ergCursor.getString(personIndex);
                            String wareStr = ergCursor.getString(wareIndex);
                            String mengStr = ergCursor.getString(mengIndex);
                            String wichtigStr = ergCursor.getString(wichtigIndex);
                            result += itemStr + ", " + personStr + ", " + wareStr + ", " + mengStr + ", " + wichtigStr + "\n";

                        } while (ergCursor.moveToNext());
                    }
                    pwr.println(result);
                    pwr.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Datei wured erstellt", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Fehler beim Öffnen: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Schreiben: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Schliessen: " + e.getClass(), Toast.LENGTH_LONG).show();
            }
        }

    }

    //Listner fuer Datei anzeigen
    class ShowFileListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView sqlView = findViewById(R.id.ViewSQL);
            FileInputStream fis = null;
            BufferedReader bin = null;
            StringBuilder text =new StringBuilder();
            try {
                fis = getApplicationContext().openFileInput("Einkaufsliste.txt");
                bin = new BufferedReader(new InputStreamReader(fis));
                String line="";
                while ((line=bin.readLine())!=null){
                    text.append(line);
                     text.append("\n");
                }
                Toast.makeText(getApplicationContext(), "die Datei wurde gelesen", Toast.LENGTH_LONG).show();
                sqlView.setText(text.toString());
                bin.close();
                fis.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Fehler beim Öffnen: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Lesen: " + e.getClass(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Fehler beim Schließen: " + e.getClass(), Toast.LENGTH_LONG).show();

            }
        }
    }

}
}



