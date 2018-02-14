package com.pumehana.signupandin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Policy extends AppCompatActivity implements View.OnClickListener {


    private Button measurement_agree;
    private Button measurement_decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy); measurement_agree = findViewById(R.id.measurement_agree);
        measurement_decline = findViewById(R.id.measurement_decline);

        measurement_agree.setOnClickListener(this);
        measurement_decline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == measurement_agree){
            Intent profile = new Intent(this, Profile.class);
            startActivity(profile);
        }
        else{
            Toast.makeText(this, "Sorry we need your permission to get your personal information.", Toast.LENGTH_SHORT).show();
            Intent main = new Intent(this, Main.class);
            startActivity(main);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}