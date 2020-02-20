package dk.cphbusiness.security.encryption;

import java.util.Base64;

public class Exercise1 {

  public static final String STR1
      = // Base64 encoded
      "VGhlIGlkZW50aXR5IEkgc3RvbGUgd2FzIGZhbHNl";

  public static final String STR2
      = // ROT encoded
      "Tvjri kyflxyk Tcvfgrkir yru r dfjk tyridzex efjv";

  public static void main( String[] args ) {
    Base64.Decoder decoder = Base64.getDecoder();
    String dec = new String( decoder.decode( STR1 ) );
    System.out.println("Decoded string is: "+dec);

    int k = 9; // Exercise - which K yields the right result
    String res = rot(STR2, k);
    System.out.println("Decrypted string is: "+res);
    }

  private static String rot(String in, int offset) {
    final int N = 'Z' - 'A' + 1; // also known as 26...
    StringBuilder builder = new StringBuilder();
    for (char c : in.toCharArray()) {
      if ('A' <= c && c <= 'Z') 
          builder.append((char)(((c - 'A') + offset + N) % N + 'A'));
      else if ('a' <= c && c <= 'z')
          builder.append((char)(((c - 'a') + offset + N) % N + 'a'));
      else builder.append(c);
      }
    return builder.toString();
    }

  }
