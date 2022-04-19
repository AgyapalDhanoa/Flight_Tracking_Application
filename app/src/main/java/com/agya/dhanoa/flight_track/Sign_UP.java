package com.agya.dhanoa.flight_track;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_UP extends AppCompatActivity {

    private EditText user, pass,gmail,lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = findViewById(R.id.sign_Up_User);
        pass = findViewById(R.id.sign_up_pass);
        gmail = findViewById(R.id.et_gmail);
        lastname = findViewById(R.id.et_Last_Name);
    }


    private boolean Validation(){
        if(user.length() ==0){
            user.setError("Empty Username not permitted");
            return false;
        }
        else if (user.length()<4){
            user.setError("Longer username is required");
            return false;
        }

        if(pass.length() ==0){
            pass.setError("Password is required");
            return false;
        }
        else if( pass.length()<6){
            pass.setError("Password should be atleast 6 digits");
            return false;
        }
         if (lastname.length()<=2){
            lastname.setError("Check Last Name again");
        }

        return true;

    }

    public void BackToLogin(View view) {
        Intent intent = new Intent(this, LoginAndSignUp.class);
        startActivity(intent);
    }



    public void AddingUser(View view) {

        String sharedPrefFileName = "com.example.flight_track";
        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFileName, MODE_PRIVATE);
        if(Validation()) {
            SharedPreferences.Editor preferenceEditor = mPreferences.edit();
            preferenceEditor.putString(user.getText().toString(), pass.getText().toString());
            preferenceEditor.putString(gmail.getText().toString(),lastname.getText().toString());
            preferenceEditor.apply();
            Intent intent = new Intent(this, LoginAndSignUp.class);
            startActivity(intent);

        }
    }
}