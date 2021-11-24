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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import de.hawlandshut.pluto22_ukw.model.Post;
import de.hawlandshut.pluto22_ukw.test.PostTestData;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "xxMainActivity";

    // TODO: Only for for testing
    private static final String TEST_MAIL = "dietergreipl@gmail.com";
    private static final String TEST_PASSWORD ="123456";


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
        ){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                // Hole der "leere" View vom Adapter
                View view = super.getView(position, convertView, parent);

                TextView text1, text2;
                text1 = view.findViewById( android.R.id.text1 );
                text2 = view.findViewById( android.R.id.text2 );

                Post post = getItem(position);

                text1.setText(post.author);
                text2.setText(post.title + "\n" + post.body);

                return view;
            }
        };


        // UI Variable initialisieren
        mListView = findViewById( R.id.mainListViewMessages );

        // ListView mit Adapter verbinden
        mListView.setAdapter( mAdapter );


        //TODO: Hier noch eine Testausgabe einfügen.
        Log.d(TAG,"in onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: nur zum Testen...
       // Intent intent = new Intent(getApplication(), ManageAccountActivity.class);
       // startActivity(intent);

        // Test der Authentisierung
        // TODO: Remove later - testing only

        Log.d(TAG,"in onStart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch( item.getItemId() ){
            case R.id.mainMenuCreateTestuser:
                doCreateUser();
                return true;

            case R.id.mainMenuDeleteTestuser:
                doDeleteUser();
                return true;
            
            case R.id.mainMenuTestAuthStatus:
                doTestAuthStatus();
                return true;
                
            case R.id.mainMenuSignInTestuser:
                doSignIn();
                return true;
                
            case R.id.mainMenuSignOutTestuser:
                doSignOut();
                return true;
                
            case R.id.mainMenuSendActivationMail:
                doSendActivationMail();
                return true;
            
            case R.id.mainMenuSendResetPasswordMail:
                doSendResetPasswordMail();
                return true;
        }
        return true;
    }

    private void doSendResetPasswordMail() {
    }

    private void doSendActivationMail() {
    }

    private void doSignOut() {
    }

    private void doSignIn() {
    }

    private void doTestAuthStatus() {
        FirebaseUser mUser;
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        if (mUser == null){
            Toast.makeText(getApplicationContext(), "Kein User angemeldet.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "User: " + mUser.getEmail(), Toast.LENGTH_LONG).show();
        }
    }

    private void doDeleteUser() {
    }

    private void doCreateUser() {
    }

    // TODO: Remove later
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"in onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"in onDestroy");
    }
}