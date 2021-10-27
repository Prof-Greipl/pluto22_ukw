package de.hawlandshut.pluto22_ukw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

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

        // TODO: Remove later, only for testing
        mEditTextEmail.setText("dgreipl@haw-landshut.de");
        mEditTextPassword.setText("123456");
        Log.d(TAG, "in onCreate");
    }
}