package com.pumehana.signupandin;

//Created by Usagi

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SignUpFragment";
    private TextView userEmail;
    private TextView userPasswordOne;
    private TextView userPasswordTwo;
    private Button submit;
    private Button signUp_clearBtn;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        userEmail = (EditText) v.findViewById(R.id.email);
        userPasswordOne = v.findViewById(R.id.passwordOne);
        userPasswordTwo = v.findViewById(R.id.passwordTwo);
        submit =  v.findViewById(R.id.submit);
        signUp_clearBtn = v.findViewById(R.id.signUp_clearBtn);
        submit.setOnClickListener(this);
        signUp_clearBtn.setOnClickListener(this);
        return v;


    }


    @Override
    public void onClick(View view) {
        if(view == submit){
            createUserAccount(userEmail.getText().toString(), userPasswordOne.getText().toString(), userPasswordTwo.getText().toString());
        }
        if(view == signUp_clearBtn){
            userEmail.setText("");
            userPasswordOne.setText("");
            userPasswordTwo.setText("");
        }
    }

    private void createUserAccount(String email, String passwordOne, String passwordTwo){

        if(email.isEmpty()){
            Toast.makeText(SignUpFragment.this.getActivity(),"Please Enter email", Toast.LENGTH_SHORT).show();
        }
        if(passwordOne.isEmpty()||passwordTwo.isEmpty()){
            Toast.makeText(SignUpFragment.this.getActivity(), "Please enter passowrd", Toast.LENGTH_SHORT).show();
        }
        if(!passwordOne.equals(passwordTwo)){
            Toast.makeText(SignUpFragment.this.getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        if(!email.isEmpty() && passwordOne.equals(passwordTwo)){
         mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, passwordOne).addOnCompleteListener(SignUpFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Log.d(TAG,"CreateAccount:Successful");
                     Toast.makeText(SignUpFragment.this.getActivity(), "Successfully create your account", Toast.LENGTH_SHORT).show();
                     Intent policy = new Intent(getActivity(), Policy.class);
                     startActivity(policy);
                 }
                 else{
                     Log.w(TAG,"CreateAccount:Failure");
                     Toast.makeText(SignUpFragment.this.getActivity(),"Cannot create your account", Toast.LENGTH_SHORT).show();
                 }
             }
         });
        }
    }
}
