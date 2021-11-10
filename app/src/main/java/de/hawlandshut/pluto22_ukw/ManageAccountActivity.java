package de.hawlandshut.pluto22_ukw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "xxManageAccountActivity";

    // 3.1. Declare UI Variables
    TextView mTextViewMail;
    TextView mTextViewAccountVerified;
    TextView mTextViewId;
    Button mButtonSignOut;
    Button mButtonSendActivationMail;
    EditText mEditTextPassword;
    Button mButtonDeleteAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        // 3.2. Initialize UI-Variables
        mTextViewMail = findViewById(R.id.manageAccountTextViewEmail);
        mTextViewAccountVerified = findViewById(R.id.manageAccountTextViewAccountVerified);
        mTextViewId = findViewById(R.id.manageAccountTextViewId);
        mButtonSignOut = findViewById(R.id.manageAccountButtonSignOut);
        mButtonSendActivationMail = findViewById(R.id.manageAccountButtonSendActivationMail);
        mEditTextPassword = findViewById(R.id.manageAccountPassword);
        mButtonDeleteAccount = findViewById(R.id.manageAccountButtonDeleteAccount);

        // 3.3 Implement Listeners
        mButtonDeleteAccount.setOnClickListener( this );
        mButtonSignOut.setOnClickListener( this );
        mButtonSendActivationMail.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();

        switch (i) {
            case R.id.manageAccountButtonSignOut:
                doSignOut();
                return;

            case R.id.manageAccountButtonSendActivationMail:
                doSendActivationMail();
                return;

            case R.id.manageAccountButtonDeleteAccount:
                doDeleteAccount();
                return;
        }
    }

    private void doDeleteAccount() {
        Toast.makeText(getApplication(), "pressed deleteAccount", Toast.LENGTH_LONG).show();
    }

    private void doSendActivationMail() {
        Toast.makeText(getApplication(), "pressed sendAct.Mail", Toast.LENGTH_LONG).show();
    }

    private void doSignOut() {
        Toast.makeText(getApplication(), "pressed signOut", Toast.LENGTH_LONG).show();
    }
}
