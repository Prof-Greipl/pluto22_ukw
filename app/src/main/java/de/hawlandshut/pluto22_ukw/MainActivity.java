package de.hawlandshut.pluto22_ukw;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "xxMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Hier noch eine Testausgabe einfügen.
        Log.d(TAG,"in onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: nur zum Testen...
        Intent intent = new Intent(getApplication(), WebViewActivity.class);
        startActivity(intent);

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
            case R.id.mainMenuItem1:
                Toast.makeText(getApplicationContext(), "You pressed Item 1.", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuItem2:
                Toast.makeText(getApplicationContext(), "You pressed Item 2.", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mainMenuItem3:
                Toast.makeText(getApplicationContext(), "You pressed Item 3.", Toast.LENGTH_LONG).show();
                return true;
        }
        return true;
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