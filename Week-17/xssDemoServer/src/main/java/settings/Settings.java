package settings;


public class Settings {
  private static boolean escape = false; //Set to false to allow XSS
  //private static String pu_name= "pu-mysql";
  private static String pu_name= "pu-derby";
   public static String getPuName(){
     return pu_name;
   }
   public static void setEscapeUntrustedData(boolean val){
     escape = val;
   }
   public static boolean escapeUntrustedData(){
     return escape;
   }
}
