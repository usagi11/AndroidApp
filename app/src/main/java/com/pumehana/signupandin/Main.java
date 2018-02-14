package com.pumehana.signupandin;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void ChangeFragment(View v){
        Fragment fragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(v.getId() == R.id.signIn){
            fragment = new SignInFragment();
            ft.replace(R.id.fragment_base, fragment);
            ft.commit();

        }
        if(v.getId() == R.id.signUp){
            fragment = new SignUpFragment();
            ft.replace(R.id.fragment_base, fragment);
            ft.commit();

        }

    }
}
