package com.example.alvihn.myapplicationtpisic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

/**
 * Created by Alvihn on 27/03/2018.
 */

public class ComposeDateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composedate);
    }

    public void validateNewDate (View v) {
        Spinner spinnerChampJour = (Spinner) findViewById(R.id.spinnerChampJour);
        Spinner spinnerChampMois = (Spinner) findViewById(R.id.spinnerChampMois);
        Spinner spinnerChampAnnee = (Spinner) findViewById(R.id.spinnerChampAnnee);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("valeur_jour",spinnerChampJour.getSelectedItem().toString());
        returnIntent.putExtra("valeur_mois",spinnerChampMois.getSelectedItem().toString());
        returnIntent.putExtra("valeur_annee",spinnerChampAnnee.getSelectedItem().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
