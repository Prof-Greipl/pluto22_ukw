package de.hawlandshut.pluto22_ukw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "xxSignInActivity";

    // Schritt 3: Jedes UI Element bekommt eine Variable
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    Button mButtonSignIn;
    Button mButtonForgotPassword;
    Button mButtonCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Schritt 3b: Initialisieren der UI Variablen
        mEditTextEmail = findViewById( R.id.signInEmail);
        mEditTextPassword= findViewById( R.id.signInPassword);
        mButtonSignIn = findViewById( R.id.signInButtonSignIn);
        mButtonForgotPassword = findViewById( R.id.signInButtonForgotPassword);
        mButtonCreateAccount = findViewById( R.id.signInButtonCreateAccount);

        // Schritt 3c (im Script 3.3): Implement Listeners
        mButtonSignIn.setOnClickListener( this );
        mButtonCreateAccount.setOnClickListener( this );
        mButtonForgotPassword.setOnClickListener( this );
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null ){
            // Wie können nur von der CreateAccount-Activity kommen.
           finish();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.signInButtonCreateAccount:
                doGotoCreateAccount();
                return;

            case R.id.signInButtonForgotPassword:
                doForgotPassword();
                return;

            case R.id.signInButtonSignIn:
                doSignIn();
                return;
        }
    }

    // Business Logic
    private void doSignIn() {
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();

        if (password.length()<2){
            Toast.makeText(getApplicationContext(), "Password too short.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "You are signed in.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sign in failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in user creation : " + task.getException().getMessage());
                        }
                    }
                });
    }

    private void doForgotPassword() {

        String email = mEditTextEmail.getText().toString();
        FirebaseAuth.getInstance().sendPasswordResetEmail( email )
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Password reset mail sent.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sending password reset mail  failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in sending password reset mail: " + task.getException().getMessage());
                        }
                    }
                });
    }

    private void doGotoCreateAccount() {
        Intent intent = new Intent(getApplication(), CreateAccountActivity.class);
        startActivity(intent);
    }
}