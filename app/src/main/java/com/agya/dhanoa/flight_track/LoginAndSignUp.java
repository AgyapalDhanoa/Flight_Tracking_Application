package com.agya.dhanoa.flight_track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAndSignUp extends AppCompatActivity {

    private EditText user, pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_up);
        user = findViewById(R.id.et_username_Login);
        pass = findViewById(R.id.et_pass_SignUp);
    }




    private boolean Validation(){
        if(user.length() ==0){
            user.setError("Empty Username not permitted");
            return false;
        }
        else if (user.length()<=4){
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
        return true;
    }

    public void NewUserSignup(View view) {
        Intent intent = new Intent(this, Sign_UP.class);
        startActivity(intent);


    }

    public void UserLogin(View view) {
        if(Validation()){
            String sharedPrefFileName = "com.example.flight_track";
            SharedPreferences mPreferences = getSharedPreferences(sharedPrefFileName, MODE_PRIVATE);
            String Password= mPreferences.getString(user.getText().toString(),"");
            if(Password.equals(pass.getText().toString())){
                Intent intent = new Intent(LoginAndSignUp.this,MainActivity.class);

                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

