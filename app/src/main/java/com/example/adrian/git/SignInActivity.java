package com.example.adrian.git;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Button logOutButton;
    private ImageView profilePicture;
    private TextView name;
    private TextView email;
    private LinearLayout profileSection;
    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton = (SignInButton) findViewById(R.id.Login);
        logOutButton = (Button) findViewById(R.id.Logout);
        name = (TextView) findViewById(R.id.Nume);
        email = (TextView) findViewById(R.id.Email);
        profilePicture = (ImageView) findViewById(R.id.AccountPicture);
        profileSection = (LinearLayout) findViewById(R.id.Credentials);
        profileSection.setVisibility(View.GONE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {

    }

    private void signIn (){

    }
    private void signOut(){

    }
    private void handleResult(GoogleSignInResult googleSignInResult){

    }
    private void updateUI(boolean isLogin){

    }


}
