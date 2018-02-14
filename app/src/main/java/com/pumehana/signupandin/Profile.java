package com.pumehana.signupandin;

//Created by USagi

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class Profile extends AppCompatActivity {

    private Button usMeasure;
    private Button standardMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usMeasure = findViewById(R.id.usMeasure);
        standardMeasure = findViewById(R.id.standardMeasure);




    }
}
