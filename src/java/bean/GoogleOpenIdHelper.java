/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alienware
 */
public class GoogleOpenIdHelper {

    private static final String CLIENT_ID = MyConstants.GOOGLE_CLIENT_ID;
    private static final String CLIENT_SECRET = MyConstants.GOOGLE_CLIENT_SECRET;
    private static final String CALLBACK_URI = MyConstants.GOOGLE_REDIRECT_URL;
    private static final String OPEN_CALLBACK_URI = MyConstants.GOOGLE_OPEN_REDIRECT_URL;

    private String stateToken;

    public GoogleOpenIdHelper() {
        generateStateToken();
    }

    private void generateStateToken() {
        SecureRandom sr1 = new SecureRandom();
        stateToken = "google;" + sr1.nextInt();
    }

    public String BuildURL() {
        String url = "https://accounts.google.com/o/oauth2/v2/auth?"
                + "client_id=" + CLIENT_ID + "&"
                + "response_type=code&"
                + "scope=profile email&access_type=online&prompt=select_account consent&"
                + "redirect_uri=" + CALLBACK_URI + "&"
                + "state=" + stateToken;

        return url;
    }

    public String getToken(final String authCode) throws IOException {

        String url = "https://www.googleapis.com/oauth2/v4/token?"
                + "code=" + authCode + "&"
                + "client_id="+CLIENT_ID+"&"
                + "client_secret="+CLIENT_SECRET+"&"
                + "redirect_uri=" + OPEN_CALLBACK_URI + "&"
                + "grant_type=authorization_code" ;
         
        GoogleTokenResponse tokenResponse
                = new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(),
                        JacksonFactory.getDefaultInstance(),
                        "https://www.googleapis.com/oauth2/v4/token",
                        CLIENT_ID,
                        CLIENT_SECRET,
                        authCode,
                        CALLBACK_URI)
                        .execute();
        String token = tokenResponse.getIdToken();
        
        return token;
        /*GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();  // Use this value as a key to identify a user.
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        return userId +"- "+ email+"-"+name+"-"+pictureUrl+"-"+locale+"-"+familyName+"-"+givenName ;*/
    }
}
