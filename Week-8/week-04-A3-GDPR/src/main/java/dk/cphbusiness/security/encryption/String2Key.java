package dk.cphbusiness.security.encryption;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/**
 The purpose of String2Key is to...

 @author kasper
 */
public class String2Key {

    public static Key string2Key( String password ) {
        byte[] key = string2KeyArray( password );

        SecretKeySpec secretKeySpec = new SecretKeySpec( key, "AES" );
        return secretKeySpec;
    }

    public static byte[] string2KeyArray( String password ) {
        try {
            byte[] key = password.getBytes( "UTF-8" );
            MessageDigest sha = MessageDigest.getInstance( "SHA-1" );
            key = sha.digest( key );
            return Arrays.copyOf( key, 16 ); // use only first 128 bit
        } catch ( UnsupportedEncodingException | NoSuchAlgorithmException ex ) {
            return null;
        }
    }
}
