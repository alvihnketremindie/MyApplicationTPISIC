package com.example.alvihn.myapplicationtpisic;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;

import static android.text.InputType.TYPE_CLASS_PHONE;

public class MainActivity extends AppCompatActivity {

    EditText editTextNom;
    EditText editTextPrenom;
    EditText editTextVille;
    EditText editTextDate;
    TextView textViewDateValue;
    Spinner spinnerVille;
    LinearLayout numeroLayout;
    ArrayList<String> valeurs_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new MyEditTextDatePicker(this, R.id.editTextDate));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String dateDeNaissance=data.getStringExtra("valeur_jour")+" / "+data.getStringExtra("valeur_mois")+" / "+data.getStringExtra("valeur_annee");
                /*textViewDateValue = findViewById(R.id.textViewDateValue);
                textViewDateValue.setText(dateDeNaissance);*/
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        valeurs_numero = getValeursNumero();
        outState.putStringArrayList("valeurs_numero", valeurs_numero);
        // Saving the bundle
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numeroLayout = findViewById(R.id.dynamicContent);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("valeurs_numero")) {
                valeurs_numero = savedInstanceState.getStringArrayList("valeurs_numero");
                for(String valeur_numero : valeurs_numero) {
                    ajoutChampNumero(numeroLayout, valeur_numero);
                }
            }
        }
    }

    /**
    * Action de validation du formulaire de l'IHM d'information.
    * Les donnees relatives au nom, prenoms, date de naissance, ville et departement sont transmise à l'activite
    * AfficheActicity
    * */
    public void validateAction (View v) {
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextVille = findViewById(R.id.editTextVille);
        editTextDate = findViewById(R.id.editTextDate);
        //textViewDateValue = findViewById(R.id.textViewDateValue);
        spinnerVille = findViewById(R.id.spinnerVille);

        valeurs_numero = getValeursNumero();
        
        String textToShow = "Nom : "+editTextNom.getText().toString() + "\r\n"
                + "Prenom : "+editTextPrenom.getText().toString() + "\r\n"
                + "Ville : "+editTextVille.getText().toString() + "\r\n"
                + "Departement : "+spinnerVille.getSelectedItem().toString() + "\r\n"
                + "Date : "+editTextDate.getText().toString() + "\r\n"
                + "Numero : "+valeurs_numero.toString() + "\r\n";
        Toast.makeText(getApplicationContext(), textToShow, Toast.LENGTH_SHORT).show();
        
        Intent intent = new Intent(getApplicationContext(), AfficheActicity.class);
        intent.putExtra("nom",editTextNom.getText().toString());
        intent.putExtra("prenom",editTextPrenom.getText().toString());
        intent.putExtra("ville",editTextVille.getText().toString());
        intent.putExtra("departement",spinnerVille.getSelectedItem().toString());
        intent.putExtra("date",editTextDate.getText().toString());
        intent.putStringArrayListExtra("valeurs_numero", valeurs_numero);

        startActivity(intent);
    }

    public ArrayList<String> getValeursNumero(){
        numeroLayout = findViewById(R.id.dynamicContent);
        int nb_numero = numeroLayout.getChildCount();
        ArrayList<String> valeurs_numero = new ArrayList<String>();
        if(nb_numero > 0) {
            for(int i=0;i<nb_numero; i++) {
                LinearLayout llayout = (LinearLayout) numeroLayout.getChildAt(i);
                if(llayout != null) {
                    EditText champNumero = (EditText) llayout.getChildAt(1);
                    valeurs_numero.add(champNumero.getText().toString());
                }

            }
        }
        return valeurs_numero;
    }

    public void ajoutNumero (View v) {
        numeroLayout = findViewById(R.id.dynamicContent);
        ajoutChampNumero (numeroLayout, "");
    }

    public void ajoutChampNumero (LinearLayout linearLayout, String valeurNumero) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout llayout = new LinearLayout(this);
        llayout.setOrientation(LinearLayout.HORIZONTAL);
        llayout.setLayoutParams(lparams);

        EditText champNumero = new EditText(this);
        champNumero.setEms(15);
        champNumero.setInputType(TYPE_CLASS_PHONE);
        champNumero.setText(valeurNumero);

        TextView nameChampNumero = new TextView(this);
        nameChampNumero.setText(R.string.champNumero);

        llayout.addView(nameChampNumero);
        llayout.addView(champNumero);
        linearLayout.addView(llayout);

    }
    public void choixDate(View v) {
        Intent intent = new Intent(getApplicationContext(), ComposeDateActivity.class);
        startActivityForResult(intent, 1);
    }

    public void lancerWiki(MenuItem item) {
        editTextVille = findViewById(R.id.editTextVille);
        String nomVille = editTextVille.getText().toString();
        String url = "https://fr.wikipedia.org/?search="+ URLEncoder.encode(nomVille).trim();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void sendInfos(MenuItem item) {
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextVille = findViewById(R.id.editTextVille);
        editTextDate = findViewById(R.id.editTextDate);
        valeurs_numero = getValeursNumero();
        String informations  = "Informations!\r\n"+
                "Nom : "+editTextNom.getText().toString()+"\r\n"+
                "Prenom : "+editTextPrenom.getText().toString()+"\r\n"+
                "Ville : "+editTextVille.getText().toString()+"\r\n"+
                "Date : "+editTextDate.getText().toString()+"\r\n";
        for(String valeur_numero : valeurs_numero) {
            informations += "Numero : "+valeur_numero+"\r\n";
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(android.content.Intent.EXTRA_TEXT, informations);
        startActivity(intent);
    }

    public boolean resetAction (MenuItem item) {
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextVille = findViewById(R.id.editTextVille);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNom.getText().clear();
        editTextPrenom.getText().clear();
        editTextVille.getText().clear();
        editTextDate.getText().clear();

        numeroLayout = findViewById(R.id.dynamicContent);
        int nb_numero = numeroLayout.getChildCount();
        if(nb_numero > 0) {
            for(int i=0;i<nb_numero; i++) {
                LinearLayout llayout = (LinearLayout) numeroLayout.getChildAt(i);
                if(llayout != null) {
                    EditText champNumero = (EditText) llayout.getChildAt(1);
                    champNumero.getText().clear();
                }

            }
        }

        return true;
    }
}
