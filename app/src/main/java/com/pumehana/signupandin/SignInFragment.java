package com.pumehana.signupandin;

//Created by Usagi

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.transition.CircularPropagation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class SignInFragment extends Fragment implements View.OnClickListener {

    private final static String TAG = "SignIn";
    private EditText email;
    private EditText password;
    private Button submit;
    private Button forgot_btn;
    private Button signIn_clearBtn;
    private FirebaseAuth mAuth ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        submit = view.findViewById(R.id.submit);
        forgot_btn = view.findViewById(R.id.forgot_btn);
        signIn_clearBtn = view.findViewById(R.id.signIn_clearBtn);
        mAuth = FirebaseAuth.getInstance();
        submit.setOnClickListener(this);
        forgot_btn.setOnClickListener(this);
        signIn_clearBtn.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view == submit){
            signIn();
        }
        if(view == forgot_btn){
            forgotPWD();
        }
        if(view == signIn_clearBtn){
            email.setText("");
            password.setText("");
        }
    }
    //Signing in
    public void signIn() {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(SignInFragment.this.getActivity(), "Please enter both of them", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(SignInFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        //Sign in successfully
                        Log.d(TAG, "signInWithEmail: success");
                        Toast.makeText(SignInFragment.this.getActivity(), "Successfully log in", Toast.LENGTH_SHORT).show();
                        Intent mainMenu = new Intent(getActivity(), MainMenu.class);
                        startActivity(mainMenu);

                    }else{
                        Log.w(TAG, "siginInWithEmail:failure", task.getException());
                        Toast.makeText(SignInFragment.this.getActivity(), "Authentication is failure", Toast.LENGTH_SHORT).show();

                    }


                }
            });

            //return true;

        }
    }

    //Send Verification email to a user who fogot a password
    private void forgotPWD(){
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(SignInFragment.this.getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignInFragment.this.getActivity(), "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();



                }else{
                    Log.e(TAG, "sendEmailVerification", task.getException());
                    Toast.makeText(SignInFragment.this.getActivity(),
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
