package com.example.homeworkweek2day4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    //Constants
    public static final int REQUEST_CODE_FOR_MAIN = 427;
    public static final String KEY_SHARED_PREF = "shared_pref";
    public static final String KEY_LAST_ENTERED_NAME = "last_name";

    //Declare views
    TextView tvNameDisplay;
    TextView tvAddressDisplay;
    TextView tvCityDisplay;
    TextView tvStateDisplay;
    TextView tvZipDisplay;
    TextView tvPhoneDisplay;
    TextView tvEmailDisplay;
    //Shared Preferences Object
    SharedPreferences sharedPreferences;
    //person Database Declaration
    PersonDatabaseHelper personDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);
        //instantiate the person Database
        personDatabaseHelper = new PersonDatabaseHelper(this);
        bindViews();
    }

    //
    //  Bind Views to xml elements
    //  @return void
    private void bindViews() {
        tvNameDisplay = findViewById(R.id.tvNameDisplay);
        tvAddressDisplay = findViewById(R.id.tvAddressDisplay);
        tvCityDisplay = findViewById(R.id.tvCityDisplay);
        tvStateDisplay = findViewById(R.id.tvStateDisplay);
        tvZipDisplay = findViewById(R.id.tvZipDisplay);
        tvPhoneDisplay = findViewById(R.id.tvPhoneDisplay);
        tvEmailDisplay = findViewById(R.id.tvNameDisplay);
    }

    //
    // Populate TextViews
    // @params Person person info to populate
    // @return void
    //
    public void populateTextViews(@NonNull Person personInfoToPopulate) {
        tvNameDisplay.setText(personInfoToPopulate.getName());
        tvAddressDisplay.setText(personInfoToPopulate.getAddress());
        tvCityDisplay.setText(personInfoToPopulate.getCity());
        tvStateDisplay.setText(personInfoToPopulate.getState());
        tvZipDisplay.setText(personInfoToPopulate.getZip());
        tvPhoneDisplay.setText(personInfoToPopulate.getPhone());
        tvEmailDisplay.setText(personInfoToPopulate.getEmail());
    }

    //
    //  Create a explicit intent to start data entry
    //      activity for result, and start the activity for result
    //  @return void
    //
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnStartForResult:
                Intent explicitIntent = new Intent(this, Activity_Data_Entry.class);
                startActivityForResult(explicitIntent, REQUEST_CODE_FOR_MAIN);
                break;
        }
    }

    public void saveAndLogPersonInSharedPref(@NonNull Person person) {
        //Get old values saved in pref
        String name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");

        //Log the old values
        Log.d(
                "TAG",
                "saveAndLogPersonInSharedPref: IN SHARED PREF: name = " + name);

        //Save new values to shared pref
        saveMakeModelToSharedPref(person);

        //get new values
        name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");

        //log the new values
        Log.d(
                "TAG",
                "saveAndLogPersonInSharedPref: IN SHARED PREF: make = " + name);
    }

    //
    // Save to Shared Pref.
    // @param Person person object which we will save info to shared pref. from
    // @return void
    //
    public void saveMakeModelToSharedPref(@NonNull Person person) {
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(KEY_LAST_ENTERED_NAME, person.getName());
        sharedPrefEditor.commit();
    }

    //
    // Save Person to database and print list of all people currently in DB to log
    //
    public void savePersonToDBandSeeLog(@NonNull Person person){
        //Save person to database
        personDatabaseHelper.insertPersonIntoDatabase(person);
        //get all current people in database and log them
        ArrayList<Person> personList = personDatabaseHelper.getAllPeopleFromDatabase();
        for(Person currentPerson : personList) {
            Log.d("TAG", currentPerson.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // null check the intent sent back with result
        if(data != null) {
            //Get bundle from Intent
            Bundle resultBundle = data.getExtras();
            if(resultBundle != null){
                //Get Person from bundle
                Person resultPerson = resultBundle.getParcelable(Activity_Data_Entry.KEY_PERSON_RESULT);
                if(resultPerson != null) {
                    saveAndLogPersonInSharedPref(resultPerson);//save and log make model in shared pref
                    savePersonToDBandSeeLog(resultPerson);//save person to db and print list of all People in db
                    populateTextViews(resultPerson); //populate the views
                }
            }
        }
    }
}
