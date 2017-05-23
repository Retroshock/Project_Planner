package com.example.adrian.git;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PickSleepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_sleep);

        Button btn = (Button) findViewById(R.id.SubmitSleepTime);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    public void submit (){
        EditText getUp = (EditText) findViewById(R.id.getUpTextField);
        EditText sleep = (EditText) findViewById(R.id.sleepAtTextField);
        SQLiteDatabase db = DatabaseObject.getDbConnection();
        String query = "INSERT INTO sleep VALUES (' " + getUp.getText().toString() + "' , '" + sleep.getText().toString()  + " ')";
        db.execSQL(query);



        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
        finish();

    }

}
