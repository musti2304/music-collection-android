package com.mustafayousef.musiccollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;


public class SpotifyLoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1337;
    private static final String CLIENT_ID = "a6926c08031143dfb0e47fa848734d36";
    private static final String REDIRECT_URI = "musiccollection://init";
    private static final String[] scopes = {"user-read-private", "user-follow-read"};


    private AuthorizationRequest.Builder builder;
    private AuthorizationRequest request;
    private AuthorizationResponse response;

    private static final String TAG = "SpotifyLoginActivity";
    private String accessToken;

    private Button btnSpotifyLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        btnSpotifyLogin = findViewById(R.id.login_with_spotify);
        btnSpotifyLogin.setOnClickListener(v -> {

            this.builder = new AuthorizationRequest
                    .Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
            builder.setScopes(scopes);

            this.request = builder.build();
            AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            this.response = AuthorizationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                case TOKEN:
                    Log.d(TAG, "access token: " + response.getAccessToken());
                    Log.d(TAG, "response: " + response.);

                    redirectToMainContentView();
                    break;
                case ERROR:
                    Log.e(TAG, "Error retrieving access token, " + response.getError());
                    break;
                default:

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Aaand we will finish off here.

    }

    private void redirectToMainContentView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
