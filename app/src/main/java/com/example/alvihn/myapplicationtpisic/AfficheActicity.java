package com.example.alvihn.myapplicationtpisic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alvihn on 27/03/2018.
 */

public class AfficheActicity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        Intent intent = getIntent();
        TextView champNom = (TextView) findViewById(R.id.textViewNomAffiche);
        champNom.setText(intent.getStringExtra("nom"));
        TextView champPrenom = (TextView) findViewById(R.id.textViewPrenomAffiche);
        champPrenom.setText(intent.getStringExtra("prenom"));
        TextView champVille = (TextView) findViewById(R.id.textViewVilleAffiche);
        champVille.setText(intent.getStringExtra("ville"));
        TextView champDepartement = (TextView) findViewById(R.id.textViewDepartementAffiche);
        champDepartement.setText(intent.getStringExtra("departement"));
        TextView champDate = (TextView) findViewById(R.id.textViewDateAffiche);
        champDate.setText(intent.getStringExtra("date"));

        ArrayList<String> valeurs_numero = intent.getStringArrayListExtra("valeurs_numero");
        afficheValeursNumero(valeurs_numero);
    }

    public void finAffichage(View v) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void afficheValeursNumero(ArrayList<String> valeurs_numero) {
        LinearLayout linearLayout = findViewById(R.id.dynamicContent2);
        for(String valeur_numero : valeurs_numero) {
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            LinearLayout llayout = new LinearLayout(this);
            llayout.setOrientation(LinearLayout.HORIZONTAL);
            llayout.setLayoutParams(lparams);

            TextView nameChampNumero = new TextView(this);
            nameChampNumero.setText(R.string.champNumero);

            TextView champNumero = new TextView(this);
            //champNumero.setEms(15);
            champNumero.setText(valeur_numero);

            llayout.addView(nameChampNumero);
            llayout.addView(champNumero);
            linearLayout.addView(llayout);
        }
    }
}
