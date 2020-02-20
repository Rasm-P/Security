package dk.cphbusiness.security.encryption;

import java.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* 
A number of corners have been cut here. Do not build on this one
Use the one named Exercise 3 for real use.
 */
public class Exercise2 {

    private static final String ALGORITHM = "AES";

    private static final String CIFER
            = "NmxhSedpfrayg4OsgKNWSjVp68E0xa76H5bOa+LEgt3fvVWPM/QHX48ySecVpyEPO/xVRaa2URbzEglWugmPpji8Q6ClwoMYHmX6qtimZ7I=";

    public static void main(String[] args) {

        //• Exercise 2: My code for force breaking the password sceme between 1000 & 9999
        for (int i = 1000; i <= 9999; i++) {
            int k = i; // which k yields the right result
            String key = "passwordabcd" + k; // has to be 128 bit/16 bytes

            String dec = decrypt(CIFER, key);
            if (dec != null && dec.contains(". ")) {
                System.out.println("Decrypted: " + dec);
                System.out.println("key: " + key);
            }
        }

    }

    public static String encrypt(final String valueEnc, final String secKey) {
        String encryptedVal = null;
        try {
            final Key key = generateKeyFromString(secKey);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            final byte[] encValue = c.doFinal(valueEnc.getBytes());
            encryptedVal = new String(Base64.getEncoder().encode(encValue));
        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
        }
        return encryptedVal;
    }

    public static String decrypt(final String encryptedValue, final String secretKey) {
        String decryptedValue = null;
        try {
            final Key key = generateKeyFromString(secretKey);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            final byte[] decorVal = Base64.getDecoder().decode(encryptedValue);
            // System.out.println(decorVal.length);
            final byte[] decValue = c.doFinal(decorVal);
            decryptedValue = new String(decValue);
        } catch (Exception ex) {
            //System.out.println("The Exception is=" + ex);
            // ex.printStackTrace();
        }
        return decryptedValue;
    }

    private static Key generateKeyFromString(final String secKey) throws Exception {
        final byte[] keyVal = secKey.getBytes();
        final Key key = new SecretKeySpec(keyVal, ALGORITHM);
        return key;
    }
}
