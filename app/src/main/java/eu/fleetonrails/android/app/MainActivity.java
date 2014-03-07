package eu.fleetonrails.android.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import eu.fleetonrails.android.app.R;
import eu.fleetonrails.android.app.api.services.OauthService;
import eu.fleetonrails.android.app.model.network.oauth.OauthCredential;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        OauthCredential oauthCredential = new OauthCredential();
        oauthCredential.setGrant_type("password");
        oauthCredential.setClient_id("0a53466ed458c313ba5ee4d555b3e5a8ab76c36a66adbfcd3af54bd3ed1cec02");
        oauthCredential.setClient_secret("86438505b551e5ee6882449041ba74c4e00cb2ad2107ad0eddfce7838993136e");
        oauthCredential.setEmail("alan");
        oauthCredential.setPassword("fleetonrails");

        OauthService.getOauthFromClientCredentials(oauthCredential);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
