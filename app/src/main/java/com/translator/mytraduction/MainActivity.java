package com.translator.mytraduction;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;
import java.util.Locale;

public class MainActivity extends  AppCompatActivity {

    private Button btn, btn2;
    private EditText editTextemail1;
    private EditText editTextpas;
    private Button connexion3333;
    private FirebaseAuth firebaseAuth;
    LoginButton loginButton;
    private CallbackManager callbackManager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       btn = findViewById(R.id.btn1);
       btn2 = findViewById(R.id.btnanis);
     btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  startActivities(new Intent(MainActivity.this,Mainconnexion.class));
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  startActivities(new Intent(MainActivity.this,Mainconnexion.class));
                Intent intent = new Intent(MainActivity.this, camera.class);

                startActivity(intent);
            }
        });
        //  editTextemail1 = (EditText) findViewById(R.id.editText1);
        //  editTextpas = (EditText) findViewById(R.id.editText2);
        //  firebaseAuth = FirebaseAuth.getInstance();
        //   loginButton=(LoginButton)findViewById(R.id.btn33) ;
        //connexion3333 = (Button) findViewById(R.id.button4);



     /*connexion3333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = editTextemail1.getText().toString().trim();
                final String password = editTextpas.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(MainActivity.this, "login ou mot de passe incorrects.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(MainActivity.this, "login ou mot de passe incorrects", Toast.LENGTH_LONG).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "mot de passe faible", Toast.LENGTH_LONG).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(MainActivity.this, historique.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(MainActivity.this, "hhhhhhhhh", Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });


            }
        });*/


    }


    }




