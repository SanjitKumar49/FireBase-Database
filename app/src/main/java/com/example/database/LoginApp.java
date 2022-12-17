package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginApp extends AppCompatActivity {
    private EditText userid,idnt;
    private Button button2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        userid=findViewById(R.id.userid);
        idnt=findViewById(R.id.idnt);
        button2=findViewById(R.id.button2);
        auth=FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user=userid.getText().toString();
                String pass=idnt.getText().toString();
                if(TextUtils.isEmpty(user) ||TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginApp.this, "Enter the data", Toast.LENGTH_SHORT).show();
                }
                else{
                    methodforlogin(user,pass);
                }

            }
        });

    }





    private void methodforlogin(String userid,String idnt){
        auth.signInWithEmailAndPassword(userid,idnt).addOnCompleteListener(LoginApp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent =new Intent(LoginApp.this,Welcome.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginApp.this, "fail", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}