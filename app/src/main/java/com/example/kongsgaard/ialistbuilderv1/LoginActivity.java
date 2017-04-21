package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements GestureDetector.OnGestureListener  {
    private GestureDetector gestureDetector;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gestureDetector = new GestureDetector(this, this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        email = (EditText)findViewById(R.id.Emailtext);
        password = (EditText) findViewById(R.id.PasswordText);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        String prefEmail = settings.getString("Username",null);
        email.setText(prefEmail);
        SharedPreferences.Editor editor = getSharedPreferences("Username",MODE_PRIVATE).edit();
    }
    public void rememberPrefence(View view){
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        CheckBox box = (CheckBox) findViewById(R.id.rememberBox);
        if (box.isChecked()== true){
            editor.putString("Username", email.getText().toString());
        }
        else {
            editor.remove("Username");
        }
        editor.apply();
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseException){
                            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                })
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                                  Toast.makeText(LoginActivity.this, "Authentication failed.",
                                     Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
        public void signIn(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if (task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "user "+ email+" logged in",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
        }
    public void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean leftSwipe = e1.getX() > e2.getX();
       // Log.d(TAG, "onFling left: " + leftSwipe);
        if (leftSwipe) {
            //Intent intent = new Intent(this, MainActivity.class);
            //startActivity(intent);
            finish();
        }
        return true; // done
    }

    public void signIn2(View view) {
        try {
            String Email = email.getText().toString().trim();
            String Password = password.getText().toString().trim();
            signIn(Email, Password);
        }
        catch (Exception e) {
            Log.e("Army",e.getMessage(),e);
            Toast.makeText(LoginActivity.this,"Please Fill Out all fields", Toast.LENGTH_LONG).show();
        }

    }

    public void NewUser(View view) {
        try {
            String Email = email.getText().toString().trim();
            String Password = password.getText().toString().trim();
            createAccount(Email, Password);
        }
        catch (Exception e) {
            Log.e("Army",e.getMessage(),e);
            Toast.makeText(LoginActivity.this,"Please Fill Out all fields", Toast.LENGTH_LONG).show();

        }

    }
}
