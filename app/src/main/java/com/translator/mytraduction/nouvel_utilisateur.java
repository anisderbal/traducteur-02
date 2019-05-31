package com.translator.mytraduction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class nouvel_utilisateur extends AppCompatActivity {

    Toolbar toolbar3;

    private EditText editTextemail;
    private EditText editTextpassword;
    private EditText editTextconfirmation;
    private Button inscrire;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private CheckBox checkBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvel_utilisateur);
        toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /**Init  EditText**/
        editTextemail = (EditText) findViewById(R.id.editTextinscri_email);
        editTextpassword = (EditText) findViewById(R.id.editTextinscri_password);
        editTextconfirmation = (EditText) findViewById(R.id.editTextinscri_confirmation);

        /** Init Bouton  Inscription**/
        inscrire = (Button) findViewById(R.id.btninscri);

        /**Init progressBar**/
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        /**init checkBox1**/
        checkBox1 =(CheckBox) findViewById(R.id.checkBox);

        /**Init Bouton Inscription**/
        inscrire = (Button) findViewById(R.id.btninscri);

        /**Init firebaseAuth **/
        firebaseAuth = FirebaseAuth.getInstance();

        /**ONCLICK  inscrire**/
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString().trim();
                String password = editTextpassword.getText().toString().trim();
                String confirPassword = editTextconfirmation.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(nouvel_utilisateur.this, "S'il vous plait entrez votre  Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(nouvel_utilisateur.this, "S'il vous plait entrez Mot de Passe", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(confirPassword)) {

                    Toast.makeText(nouvel_utilisateur.this, "S'il vous plait entrez confirme Mot de Passe", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(nouvel_utilisateur.this, "Mot de Passe trés court", Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                if (password.equals(confirPassword)) {
                    progressBar.setVisibility(View.GONE);
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(nouvel_utilisateur.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(nouvel_utilisateur.this, "Inscription correct", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(nouvel_utilisateur.this, email.class);
                                        startActivity(intent);
                                    } else {


                                        Toast.makeText(nouvel_utilisateur.this, "Inscription incorrect", Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }
                            });

                }


            }

        });
        /** ONCLICK  checkBox1**/
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editTextpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editTextconfirmation.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    editTextpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editTextconfirmation.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });


    }
}
