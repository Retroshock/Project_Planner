package com.example.adrian.git;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    public static final String NUME_KEY = "com.example.adrian.git.nume";
    public static final String STDATE_KEY = "com.example.adrian.git.startDate";
    public static final String ENDDATE_KEY = "com.example.adrian.git.endDate";
    public static final String NOTIF_KEY = "com.example.adrian.git.notificatie";
    public static final String NOTA_KEY = "com.example.adrian.git.nota";
    public static final String LOCATIE_KEY = "com.example.adrian.git.locatie";
    public static final String STATIC_KEY = "com.example.adrian.git.static";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvtAct);
        TextView stDate = (TextView) findViewById(R.id.stDateEvtAct);
        TextView endDate = (TextView) findViewById(R.id.endDateEvtAct);
        TextView nume = (TextView) findViewById(R.id.numeEvtAct);
        TextView notificatie = (TextView) findViewById(R.id.notificatieEvtAct);
        TextView nota = (TextView) findViewById(R.id.notaEvtAct);
        TextView locatie = (TextView) findViewById(R.id.locatieEvtAct);
        TextView static_ = (TextView) findViewById(R.id.staticEvtAct);
        setSupportActionBar(toolbar);
        Bundle b = getIntent().getExtras();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
}
