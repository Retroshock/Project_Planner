package com.example.adrian.git;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //private TextView nume;
    //private TextView email;
    private SignInButton login;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        login = (SignInButton) findViewById(R.id.loginSignAct);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        //nume = (TextView) findViewById(R.id.numeSignAct);
        //email = (TextView) findViewById(R.id.emailSignAct);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();

        DatabaseObject BD = new DatabaseObject(getApplicationContext());
        IDGen.setIdCurent();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void signIn (){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    /*public void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }*/

    private void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            /*GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String mail = account.getEmail();
            nume.setText(name);
            email.setText(mail);
            Glide.with(this).load(imgURL).into(profilePicture);
            updateUI(true);*/
            Intent intent = new Intent(this, Navigation.class);
            Toast.makeText(getApplicationContext(), "Connected! ", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Could not connect to google", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Navigation.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    public void skip(View v)
    {
        //getApplicationContext().deleteDatabase("events");
        String verifyQuery = "select * from sleep;";

        Cursor cursor = DatabaseObject.getDbConnection().rawQuery(verifyQuery, null);
        if (!cursor.moveToFirst()) {
            Intent intent = new Intent(this, PickSleepActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent (this, Navigation.class);
            startActivity(intent);
            finish();
        }
    }
}
