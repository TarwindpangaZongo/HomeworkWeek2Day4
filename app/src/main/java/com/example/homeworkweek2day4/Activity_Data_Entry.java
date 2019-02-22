package com.example.homeworkweek2day4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity_Data_Entry extends AppCompatActivity {

    //Constants
    public static final String KEY_PERSON_RESULT = "person_result";
    public static final int RESULT_CODE = 426;

    //Declare views
    EditText etNameDisplay;
    EditText etAddressDisplay;
    EditText etCityDisplay;
    EditText etStateDisplay;
    EditText etZipDisplay;
    EditText etPhoneDisplay;
    EditText etEmailDisplay;

    //The Intent that started this activity
    Intent sentIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__data__entry);
        sentIntent = getIntent(); //gets intent that started the activity
        bindViews();
    }

    //
    //  Bind Views to xml elements
    //  @return void
    public void bindViews() {
        etNameDisplay = findViewById(R.id.etNameDisplay);
        etAddressDisplay = findViewById(R.id.etAddressDisplay);
        etCityDisplay = findViewById(R.id.etCityDisplay);
        etStateDisplay = findViewById(R.id.etStateDisplay);
        etZipDisplay = findViewById(R.id.etZipDisplay);
        etPhoneDisplay = findViewById(R.id.etPhoneDisplay);
        etEmailDisplay = findViewById(R.id.etNameDisplay);
    }

    //
    // Create a Person Object
    // @return Person The new person object
    //
    public Person generatePersonObjectFromInput() {
        Person returnPerson = new Person();  //the person we will return from method

        //Set-up Person object
        returnPerson.setName(
                etNameDisplay.getText() != null ? etNameDisplay.getText().toString() : "");
        returnPerson.setAddress(
                etAddressDisplay.getText() != null ? etAddressDisplay.getText().toString() : "");
        returnPerson.setCity(
                etCityDisplay.getText() != null ? etCityDisplay.getText().toString() : "");
        returnPerson.setState(
                etStateDisplay.getText() != null ? etStateDisplay.getText().toString() : "");
        returnPerson.setZip(
                etZipDisplay.getText() != null ? etZipDisplay.getText().toString() : "");
        returnPerson.setPhone(
                etPhoneDisplay.getText() != null ? etPhoneDisplay.getText().toString() : "");
        returnPerson.setEmail(
                etEmailDisplay.getText() != null ? etEmailDisplay.getText().toString() : "");

        return returnPerson;
    }

    public void onClick(View view) {
        Person personResult = generatePersonObjectFromInput();
        Bundle bundleOfThePersonResult = new Bundle();
        bundleOfThePersonResult.putParcelable(KEY_PERSON_RESULT, personResult); //put person object in bundle
        sentIntent.putExtras(bundleOfThePersonResult); //attach the result bundle to intent
        setResult(RESULT_CODE, sentIntent); //send back bundle with result to activity which called it for result
        finish(); //Make sure the activity is flagged to be destroyed
    }



}
