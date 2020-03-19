/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleImplementation;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author rasmu
 */
public class simplePasswordControl {

    public static void main(String[] args) {
        String[] badPass = {"123456", "password", "123456789", "12345678", "12345", "111111", "1234567", "sunshine", "qwerty", "iloveyou", "princess", "admin", "welcome", "666666", "abc123", "football", "123123", "monkey", "654321", "!@#$%^&amp;*", "charlie", "aa123456", "donald", "password1", "qwerty123"};
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String password;

        do {
            System.out.print("Please enter password and then hit enter:");
            password = sc.nextLine();

            if (password.length() < 8) {
                valid = false;
                System.out.println("Password must have at least 8 characters");
                continue;
            }
            for (String s : badPass) {
                if (password.equals(s)) {
                    valid = false;
                    System.out.println("That password is weak!");
                    break;
                }
            }
            if (!Pattern.compile("[A-Z ]").matcher(password).find()) {
                System.out.println("Password must have atleast one uppercase character !!");
                valid = false;
                continue;
            }
            if (!Pattern.compile("[a-z ]").matcher(password).find()) {
                System.out.println("Password must have atleast one lowercase character !!");
                valid = false;
                continue;
            }
            if (!Pattern.compile("[0-9 ]").matcher(password).find()) {
                System.out.println("Password must have atleast one digit character !!");
                valid = false;
                continue;
            }
            if (!Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(password).find()) {
                System.out.println("Password must have atleast one specail character !!");
                valid = false;
                continue;
            }
            valid = true;

        } while (!valid);

        System.out.println(
                "Password Accepted");
    }

}
