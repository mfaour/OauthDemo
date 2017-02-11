/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Alienware
 */
public class MyConstants {
     public static final String GOOGLE_CLIENT_ID = "885618097394-l4vecatdujl0a7akpgcnrdjr1pk5bepq.apps.googleusercontent.com";
   // Client Secret
   public static final String GOOGLE_CLIENT_SECRET = "EUmFsS7wQaFAO_K4HtMUdK_6";
   // Redirect URI
   public static final String GOOGLE_REDIRECT_URL = "http://localhost:8084/OAuthDemo/profile.jsp";
   public static final String GOOGLE_OPEN_REDIRECT_URL = "http://localhost:8084/OAuthDemo/openId.jsp";
   private static final Iterable<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
 
   
    private static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
    // public static final String SCOPE = "https://mail.google.com/ https://www.googleapis.com/auth/userinfo.email";

    /**
     * @return the SCOPE
     */
    public static  Collection<String> GOOGLE_SCOPE() {
        return  makeCollection(SCOPE);
    }

}
