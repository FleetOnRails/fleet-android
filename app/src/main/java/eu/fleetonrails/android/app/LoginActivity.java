package eu.fleetonrails.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import eu.fleetonrails.android.app.utils.network.LoginUtil;
import eu.fleetonrails.android.app.utils.network.MeUtil;

/**
 * Created by alan Kehoe
 * on 08/03/2014.
 */
public class LoginActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }


    public void sendLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        EditText uiUsername = (EditText) findViewById(R.id.username);
        EditText uiPassword = (EditText) findViewById(R.id.password);

        String username = null;
        String password = null;

        if (!(uiUsername.getText() == null) && !(uiPassword.getText() == null)) {
            username = uiUsername.getText().toString();
            password = uiPassword.getText().toString();
        }

        LoginUtil.login(LoginActivity.this, username, password);
        MeUtil.index(LoginActivity.this);

        startActivity(intent);
    }
}
