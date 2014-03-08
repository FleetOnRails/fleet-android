package eu.fleetonrails.android.app.models;

/**
 * Created by alan on 08/03/2014.
 */
public class Oauth {
    public String access_token;
    public String token_type;
    public int expires_in;
    public String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}

//public class OauthCredential {
//    public String grant_type;
//    public String client_id;
//    public String client_secret;
//    public String username;
//    public String password;
//
//    public OauthCredential(String username, String password) {
//        this.grant_type = "password";
//        this.client_id = super.client_id;
//        this.client_secret = super.client_secret;
//        this.username = username;
//        this.password = password;
//    }
//}

