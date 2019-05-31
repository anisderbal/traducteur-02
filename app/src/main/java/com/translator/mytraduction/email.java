package com.translator.mytraduction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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


public class email extends AppCompatActivity {
    Toolbar toolbar;

   private EditText editTextemail;
   private EditText  editTextpassword;
   private EditText editTextshowpassword;
   private Button btnconnexion,inscr;
   private FirebaseAuth firebaseAuth;
   private CheckBox showpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        /**Toolbar toolbar email **/
        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Connexion");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /**  Init EditText**/
        editTextemail = (EditText) findViewById(R.id.editTexteamil);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        editTextshowpassword=(EditText)findViewById(R.id.editTextpassword);

        /** Init Button connexion,  checkBox  showpassword**/
        btnconnexion = (Button) findViewById(R.id.btnconnexion1);
        showpassword =(CheckBox)findViewById(R.id.checkBox2) ;

        /** Afficher Mot de passe**/
         showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editTextshowpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    editTextshowpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }

            }
        });

         /**OnClick Bouton  nouvel_utilisateur.......... **/
         /** Inti inscr**/
        inscr = (Button) findViewById(R.id.btnInscri1);

        inscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(email.this, nouvel_utilisateur.class);
                startActivity(intent);

            }
        });

         /** OnClick Bouton connexion**/
         /** signInWithEmailAndPassword**/
        btnconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = editTextemail.getText().toString().trim();
                final String password = editTextpassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(email.this, "Login ou Mot de passe Incorrects.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(email.this, "login ou mot de passe Incorrects", Toast.LENGTH_LONG).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(email.this, "Mot de passe faible", Toast.LENGTH_LONG).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(email.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(email.this, historique.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(email.this, "  Refuse connexion", Toast.LENGTH_LONG).show();

                                }


                            }
                        });


            }
        });


    }



}