package com.translator.mytraduction;

import android.app.ListActivity;
import android.app.Notification;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.translator.mytraduction.model.Language;
import com.translator.mytraduction.model.TranslateTask;
import com.translator.mytraduction.model.TranslatorBackgroundTask;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    private Button btnsynchron;
    private EditText editTextOut;
    private  EditText editTextIn;
    private TextToSpeech toSpeech;
    private Button btn_speecheText;
    private Spinner spDest;
    private Spinner spIn;
    private String text;
    private String to;
    private  String from;
    private   EditText edtOut;






    Context context=this;
    private static final int REQUEST_CODE = 1234;
    public static EditText tvTranslatedText;
    EditText etUserText;
    Button buTranslate;
    Button buSpeak;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /** inti editText**/
        editTextIn = (EditText) findViewById(R.id.inputtxt);
        edtOut = (EditText)findViewById(R.id.outputtxt);

        /*test*/
        tvTranslatedText = (EditText) findViewById(R.id.outputtxt);
        etUserText = (EditText)findViewById(R.id.inputtxt);

        buTranslate = (Button)findViewById(R.id.btntraduction);


        /** Inti Spinner**/
        final Spinner spinnerIn = (Spinner) findViewById(R.id.spinnerleft);
        final Spinner spinnerOut = (Spinner) findViewById(R.id.spinnerrighte);
        spIn =(Spinner) findViewById(R.id.spinnerleft);
        spDest=(Spinner)findViewById(R.id.spinnerrighte);
        /** Init TextView **/
        final TextView txtInt = (TextView) findViewById(R.id.selectionspinner1);
        final TextView txtOut = (TextView) findViewById(R.id.selectionspinner2);
        /** charge spinner **/
        final ArrayList<String> spinnerArry = new ArrayList<String>();
        spinnerArry.add("Anglais");
        spinnerArry.add("Français");
        spinnerArry.add("Arabe");
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spinnerArry);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /** charege spinnerIn,spinnerOut **/
        spinnerIn.setAdapter(adapter);
        spinnerOut.setAdapter(adapter);
        /**  charge txtInt **/

        spinnerIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtInt.setText("" + spinnerArry.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerIn.setSelection(0);
            }
        });

        /**  charge txtOut **/
        spinnerOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtOut.setText("" + spinnerArry.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerOut.setSelection(0);

            }
        });

        /**OnClick Bouton synchronisation **/
        btnsynchron = (Button) findViewById(R.id.btnsynchronisation);
        btnsynchron.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                int i = spinnerIn.getSelectedItemPosition();
                int j = spinnerOut.getSelectedItemPosition();
                spinnerIn.setSelection(j);
                spinnerOut.setSelection(i);
            }
        });


        /**OnClick Bouton search**/
        Button btnsearch = (Button) findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                share();
            }
        });
        /** Text  to speech**/
        btn_speecheText=(Button)findViewById(R.id.btnspeakerin);
        toSpeech =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                int i = spinnerIn.getSelectedItemPosition();


                if (status!=TextToSpeech.ERROR ){

                    toSpeech.setLanguage(Locale.ENGLISH);



                }
            }
        });
        /** OnClick bouton speakerin**/
        btn_speecheText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeech.speak(editTextIn.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });

      /*  Button btnraduction = (Button) findViewById(R.id.btntraduction);
        btnraduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 translate();
            }
        });*/
        buTranslate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buTranslate(view);
                    }
                }
        );

        /** Toolbar toolbar home**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**inti BottomNavigationView**/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom1);

        /** bottomNavigationView**/
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item1) {

                int id = item1.getItemId();

                if (id == R.id.btn_nav_fav) {
                    Intent intent = new Intent(home.this, favorite.class);
                    startActivity(intent);


                } else if (id == R.id.btn_nav_camera) {


                    Intent intent1 = new Intent(home.this, connexion.class);
                    startActivity(intent1);


                } else if (id == R.id.btn_nav_microphone) {
                    speak();

                }

            }
        });

    }

   /* private void translate() {
        this.text = this.editTextIn.getText().toString();
        this.from = this.spIn.getSelectedItem().toString();
        this.to = this.spDest.getSelectedItem().toString();
        Toast.makeText(home.this, "pour teste.",
                Toast.LENGTH_SHORT).show();
          new TranslateTask(this.text, this.from, this.to, this, this.edtOut).execute(new Void[0]);


    }*/





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    /**Navigation view item**/


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.btn_nav_rechercher) {
            Intent intent = new Intent(home.this, home.class);
            startActivity(intent);


        } else if (id == R.id.btn_nav_connexion) {


            Intent intent1 = new Intent(home.this, connexion.class);
            startActivity(intent1);


        } else if (id == R.id.btn_nav_historique) {
            Intent intent2 = new Intent(home.this, historique.class);
            startActivity(intent2);


        } else if (id == R.id.btn_nav_favorite) {
            Intent intent3 = new Intent(home.this, favorite.class);
            startActivity(intent3);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**OnClick Bouton supprimer **/

    public void supprimer(View view) {

        final EditText clear = (EditText) findViewById(R.id.inputtxt);

        Button btn1 = (Button) findViewById(R.id.btnclear);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear.setText("");

            }


        });


    }

    /**  OnClick Bouton  copy **/
    private EditText editTextcopy;

    public void copy(View view) {

        editTextcopy = (EditText) findViewById(R.id.outputtxt);
        Button btn_copy_text = (Button) findViewById(R.id.btncopie);

        btn_copy_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getstring = editTextcopy.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                clipboard.setText(getstring);


            }
        });


    }

    /** Methode sahre**/

    private void share() {
        editTextOut = (EditText) findViewById(R.id.outputtxt);
        String str = editTextOut.getText().toString();
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", str);
        localIntent.setType("text/plain");
        startActivity(Intent.createChooser(localIntent, "Share via..."));
    }

    /** methode  speak**/
    private void speak() {
        //intenet  to show speech  text dailog

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Dites quelque chose...");
        //start intent
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);

        } catch (Exception e) {

            //get message  of error and  show
            Toast.makeText(this, "Je n'ai pas compris" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK &&null!=data ){
                    ///  get  text  array  from  voice intent
                    ArrayList<String> result=  data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    editTextIn.setText(result.get(0));

                }
                break;

            }


        }
    }







    public void buTranslate(View view) {
        //Default variables for translation
        String textToBeTranslated = "";
        textToBeTranslated= etUserText.getText().toString();
        String languagePair = "fr-ar" ;//English to arabe ("<source_language>-<target_language>")
        //Executing the translation function
        Translate(textToBeTranslated,languagePair);

    }
    void Translate(String textToBeTranslated, String languagePair){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(context);

        String translationResult = "";

        translationResult = String.valueOf(translatorBackgroundTask.execute(textToBeTranslated,languagePair)); // Returns the translated text as a String
        Log.d("Translation Result",translationResult); // Logs the result in Android Monitor

    }


}