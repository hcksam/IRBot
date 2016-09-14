package hck.irbot;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

import hck.irbot.common.FixData;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        checkPermission();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case FixData.PERMISSIONS_REQUEST_INDEX: {
                checkPermission();
                return;
            }
        }
    }

    private void forward() {
        Intent intent = new Intent(context, webViewActivity.class);
        startActivity(intent);

    }

    private void checkPermission() {
        LinkedList<String> noGranted = havePermissionsNoGranted();
        if (noGranted.size() != 0) {
            ActivityCompat.requestPermissions(this,
                    noGranted.toArray(new String[0]),
                    FixData.PERMISSIONS_REQUEST_INDEX);
        } else {
            forward();
        }
    }

    private LinkedList<String> havePermissionsNoGranted() {
        LinkedList<String> noGranted = new LinkedList<>();

        for (String permission : FixData.Permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    permission);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                noGranted.add(permission);
            }
        }

        return noGranted;
    }
}