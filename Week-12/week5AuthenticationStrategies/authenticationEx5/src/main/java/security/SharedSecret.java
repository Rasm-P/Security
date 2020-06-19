/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.SecureRandom;

/**
 *
 * @author rasmu
 */
/* This generates a secure random per execution of the server
 * A server restart, will generate a new key, making all existing tokens invalid
 * For production (and if a load-balancer is used) come up with a persistent key strategy */
public class SharedSecret {
    private static byte[] secret;
    public static byte[] getSharedKey() {
//        System.out.println("******************* IMPORTANT ******************'");
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println("**** REMOVE FIXED SECRET BEFORE PRODUCTION ******");
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        //REMOVE BEFORE PRODUCTION
//        if(true){
//            return "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA".getBytes();
//        }
        
        if (secret == null) {
            secret = new byte[32];
            new SecureRandom().nextBytes(secret);
        }
        return secret;
    }
}
