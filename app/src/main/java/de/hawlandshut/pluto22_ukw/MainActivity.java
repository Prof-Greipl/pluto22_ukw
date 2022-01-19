package de.hawlandshut.pluto22_ukw;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import de.hawlandshut.pluto22_ukw.model.Post;
import de.hawlandshut.pluto22_ukw.test.PostTestData;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "xxMainActivity";


    // ChildEventListener
    ChildEventListener mCEL;
    Query mQuery;
    boolean mListenerIsRunning = false;

    ArrayList<Post> mPostList; // Enthält posts, die vom vom server kommen
    ArrayAdapter<Post> mAdapter;

    // UI Variable deklarieren
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseDatabase.getInstance().setPersistenceEnabled( true );

        mPostList = new ArrayList<Post>();
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

        // Init mCEL
        mCEL = getChildEventListener();
        mQuery = FirebaseDatabase.getInstance().getReference("/posts").limitToLast(3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null ){
            resetApp();
            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
        } else {
            if (!mListenerIsRunning){
                mPostList.clear();
                mAdapter.notifyDataSetChanged();
                mQuery.addChildEventListener( mCEL );
                mListenerIsRunning = true;
            }
        }
    }

    private void resetApp() {
        if (mListenerIsRunning){
            mQuery.removeEventListener( mCEL );
            mListenerIsRunning = false;
        }
        mPostList.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


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

    private ChildEventListener getChildEventListener(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG,"CEL : onChildAdded : " + snapshot.getKey());
                Post p = Post.fromSnapshot( snapshot );
                mPostList.add( p );
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG,"CEL : onChildChanged : " + snapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"CEL : onChildRemoved : " + snapshot.getKey());
                String key = snapshot.getKey();
                for (int i = 0; i < mPostList.size(); i++){
                    if (key.equals( mPostList.get(i).firebaseKey)){
                        mPostList.remove(i);
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG,"CEL : onChildMoved : " + snapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG,"CEL : listener was cancelled.");
                mListenerIsRunning = false;
            }
        };
    }
}