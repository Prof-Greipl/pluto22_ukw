package de.hawlandshut.pluto22_ukw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "xxCreateAccountActivity";

    // 3.1. Declare UI Variables
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    EditText mEditTextPassword1;
    Button mButtonCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // 3.2. Initialize UI-Variables
        mEditTextEmail = findViewById( R.id.createAccountEditTextEmail );
        mEditTextPassword = findViewById( R.id.createAccountEditTextPassword );
        mEditTextPassword1 = findViewById( R.id.createAccountEditTextPassword1 );
        mButtonCreateAccount = findViewById( R.id.createAccountButtonCreateAccount );

        // TODO: Remove later, only for testing
        mEditTextEmail.setText("dietergreipl@gmail.com");
        mEditTextPassword.setText("123456");
        mEditTextPassword1.setText("123456");


        // 3.3 Implement Listeners
        mButtonCreateAccount.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.createAccountButtonCreateAccount:
                doCreateAccount();
                return;

        }
    }

    private void doCreateAccount() {
        Toast.makeText(getApplication(), "pressed createAccount", Toast.LENGTH_LONG).show();
    }
}