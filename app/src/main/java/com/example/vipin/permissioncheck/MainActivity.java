package com.example.vipin.permissioncheck;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //Button RequestPermission
    private Button buttonRequestPermission;

    //Permission code that will be checked in the method onRequestPermissionResult
    private int STORAGE_PERMISSION_CODE = 23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing button
        buttonRequestPermission = (Button) findViewById(R.id.buttonRequestPermission);

        //Adding a click listener
        buttonRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //First checked if the app is already having the permission
                if (isReadStorageAllowed()) {
                    //If permission is already having then showing the toast
                    Toast.makeText(MainActivity.this, "You already have the permission", Toast.LENGTH_SHORT).show();
                    //Existing the method with return
                    return;
                }
                //If the app has not the permission then asking for the permission
                requestStoragePermission();
            }
        });
    }

    //We are calling this method to check the permission status

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return  true;

        //If permission is not granted returning false;
        return false;
    }


    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            //If the user has denied the permission previously your code will to this block
            //Here you can explain why need this permission
            //Explain here why need this permission

        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        }
        //This method will be called when the user will top on allow or deny


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE)
        {
            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the Storage", Toast.LENGTH_SHORT).show();
            }
            else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
