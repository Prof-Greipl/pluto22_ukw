package de.hawlandshut.pluto22_ukw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        // Define Static Textlines
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null){
            Log.e(TAG, "Fatal: Null user in ManageAccount");
        } else {
            mTextViewMail.setText("E-Mail : " + mUser.getEmail());
            mTextViewId.setText("Your id : " + mUser.getUid() );
            mTextViewAccountVerified.setText("Account verified : " + mUser.isEmailVerified());
        }

        // TODO: Only for testing - remove later
        mEditTextPassword.setText("123456");

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

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null) {
            Toast.makeText(getApplicationContext(), "Cannot delete: No user signed in.", Toast.LENGTH_LONG).show();
            return;
        }

        String email = mUser.getEmail();
        String password = mEditTextPassword.getText().toString();
        if (password.length()<2){
            Toast.makeText(getApplicationContext(), "Password to short", Toast.LENGTH_LONG).show();
            return;
        }
        // TODO: Check password - if empty - HOMEWORK


        // Reauthenticate with Credential
        AuthCredential credential;
        credential = EmailAuthProvider.getCredential(email, password);
        mUser.reauthenticate(credential)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User reauthenticated.", Toast.LENGTH_LONG).show();
                            finalDeletion();
                        } else {
                            Toast.makeText(getApplicationContext(), "Reauthentication failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in deleting account (no-reauth) : " + task.getException().getMessage());
                        }
                    }
                });
    }

    private void finalDeletion() {
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        mUser.delete()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account deleted.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Deletion failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in deleting account : " + task.getException().getMessage());
                        }
                    }
                });
    }


    private void doSendActivationMail() {

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null) {
            Toast.makeText(getApplicationContext(), "Meaningless: no user, no mail.", Toast.LENGTH_LONG).show();
            return;
        }

        mUser.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Verification mail sent.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sending verification mail failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in sending verification mail : " + task.getException().getMessage());
                        }
                    }
                });
    }

    private void doSignOut() {
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser == null) {
            Toast.makeText(getApplicationContext(), "Meaningless: no user signed in.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(), "You are signed out.", Toast.LENGTH_LONG).show();
        finish();
    }
}
