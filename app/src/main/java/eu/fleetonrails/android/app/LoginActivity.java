package eu.fleetonrails.android.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import eu.fleetonrails.android.app.models.Oauth;
import eu.fleetonrails.android.app.services.network.OauthService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login("alan", "fleetonrails");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        login(username, password);
        startActivity(intent);
    }

    public void login(String username, String password) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        RestAdapter restAdapter;
        restAdapter = new RestAdapter.Builder()
                .setServer(OauthService.serverPath)
                .build();

        OauthService oauthService = restAdapter.create(OauthService.class);

        oauthService.getBearerFromCredentials("password", prefs.getString("oauth_client_id", ""),
                prefs.getString("oauth_client_secret", ""), username, password, new Callback<Oauth>() {
                    @Override
                    public void success(Oauth oauth, Response response) {
                        SharedPreferences fleetPreferences = getSharedPreferences("FleetPreferences", MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = fleetPreferences.edit();
                        prefEditor.putString("AccessToken", oauth.getAccess_token());
                        prefEditor.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("error", error.toString());
                    }
                }
        );

        SharedPreferences fleetPreferences = getSharedPreferences("FleetPreferences", MODE_PRIVATE);
        Log.d("Bearer ", fleetPreferences.getString("AccessToken", ""));
    }

}
