package de.hawlandshut.pluto22_ukw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        // TODO: Remove later, only for testing
        mEditTextEmail.setText("dietergreipl@gmail.com");
        mEditTextPassword.setText("123456");
        Log.d(TAG, "in onCreate");

        // Schritt 3c (im Script 3.3): Implement Listeners
        mButtonSignIn.setOnClickListener( this );
        mButtonCreateAccount.setOnClickListener( this );
        mButtonForgotPassword.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.signInButtonCreateAccount:
                doCreateAccount();
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
    // TODO: Implement
    private void doSignIn() {
        Toast.makeText(getApplication(), "pressed signIn", Toast.LENGTH_LONG).show();
    }

    private void doForgotPassword() {
        Toast.makeText(getApplication(), "pressed forgotPassword", Toast.LENGTH_LONG).show();
    }

    private void doCreateAccount() {
        Toast.makeText(getApplication(), "pressed createAccount", Toast.LENGTH_LONG).show();
    }
}