package de.hawlandshut.pluto22_ukw;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hawlandshut.pluto22_ukw.model.Post;
import de.hawlandshut.pluto22_ukw.test.PostTestData;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "xxMainActivity";

    // TODO: Only for for testing
    private static final String TEST_MAIL = "dietergreipl@gmail.com";
    private static final String TEST_PASSWORD = "123456";


    ArrayList<Post> mPostList; // Enthält posts, die vom vom server kommen
    ArrayAdapter<Post> mAdapter;

    // UI Variable deklarieren
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Testdaten erzeugen
        // TODO: only for testing - remove later
        PostTestData.createTestData();
        mPostList = (ArrayList<Post>) PostTestData.postTestList;


        mAdapter = new ArrayAdapter<Post>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                mPostList
        ) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                // Hole der "leere" View vom Adapter
                View view = super.getView(position, convertView, parent);

                TextView text1, text2;
                text1 = view.findViewById(android.R.id.text1);
                text2 = view.findViewById(android.R.id.text2);

                Post post = getItem(position);

                text1.setText(post.author);
                text2.setText(post.title + "\n" + post.body);

                return view;
            }
        };


        // UI Variable initialisieren
        mListView = findViewById(R.id.mainListViewMessages);

        // ListView mit Adapter verbinden
        mListView.setAdapter(mAdapter);


        //TODO: Hier noch eine Testausgabe einfügen.
        Log.d(TAG, "in onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference("App/Version");
        mDatabase.setValue("V1.0");

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser == null ){
            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
        }

        Log.d(TAG, "in onStart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: Only for testing
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.mainMenuPost:
                intent = new Intent(getApplication(), PostActivity.class);
                startActivity(intent);
                return true;

            case R.id.mainMenuManageAccount:
                intent = new Intent(getApplication(), ManageAccountActivity.class);
                startActivity(intent);
                return true;

        }
        return true;
    }


    private void doSendResetPasswordMail() {
/*
        String email = TEST_MAIL;

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
*/
    }

    private void doSendActivationMail() {
        /*
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
*/
    }

    private void doSignOut() {
        /*
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser == null) {
            Toast.makeText(getApplicationContext(), "Meaningless: no user signed in.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(), "You are signed out.", Toast.LENGTH_LONG).show();

         */
    }

    private void doSignIn() {
        /*
        String email = TEST_MAIL;
        String password = TEST_PASSWORD;
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "You are signed in.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sign in failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in user creation : " + task.getException().getMessage());
                        }
                    }
                });

         */
    }

    private void doTestAuthStatus() {
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser == null) {
            Toast.makeText(getApplicationContext(), "No user signed in.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "User: " + mUser.getEmail() + "(" + mUser.isEmailVerified()+")", Toast.LENGTH_LONG).show();
        }
    }

    private void doDeleteUser() {
        /*
        String email;
        String password = TEST_PASSWORD;

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null) {
            Toast.makeText(getApplicationContext(), "Cannot delete: No user signed in.", Toast.LENGTH_LONG).show();
            return;
        }

        email = mUser.getEmail();
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

         */
    }





    private void doCreateUser() {
        Log.d(TAG, "called created user");
        String email = TEST_MAIL;
        String password = TEST_PASSWORD;
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User created.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "User creation failed.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Error in user creation : " + task.getException().getMessage());
                        }
                    }
                });
    }

    // TODO: Remove later
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "in onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "in onDestroy");
    }
}