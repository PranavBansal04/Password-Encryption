package network;

import java.io.File;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

   static String hash(String str) {

       String hashed = "";

       try {
      
       MessageDigest security = MessageDigest.getInstance("SHA-512");
       security.update(str.getBytes(Charset.forName("UTF8")));

       byte[] bytes = security.digest();
       StringBuffer buffer = new StringBuffer();

       for (int i = 0; i < bytes.length; ++i) {
      
       buffer.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100)
       .substring(1, 3));

       }
       hashed = buffer.toString();
       } catch (NoSuchAlgorithmException e) {

       e.printStackTrace();
       }
       return hashed;
       }

public static void main(String[] args) throws FileNotFoundException {


File store = new File("database.txt");
if (!store.exists()) {

System.out.println(store + " not found, run the program first. ");

System.exit(0);
}

//creating arraylists for usernames and hashed passwords
ArrayList <String> user_ar = new ArrayList<String>();
ArrayList <String> pass_ar = new ArrayList<String>();

Scanner sc = new Scanner(store);

//this loop will add all usernames and corresponding passwords to arraylists

//remember that index of a passwords in pass_ar for a specific username is at the same index as of the username in user_ar

while(sc.hasNextLine()) {
   String user = sc.next().trim();
   String hash = sc.next();//.trim();
   user_ar.add(user);
   pass_ar.add(hash);
   
//   System.out.println(user);
//   System.out.println(hash);
  
}
//System.out.println(user_ar);

sc = new Scanner(System.in);

boolean done = false;
int attempts = 0;

while (!done) {

// Prompt for registered username and pwdword.
System.out.print("Enter registered username: ");

String userName = sc.next();

System.out.print("Enter your password: ");

String pwdword = sc.next();

//the ArrayList.indexOf() method returns -1 if the element is absent in the array, otherwise it returns the index

int index=user_ar.indexOf(userName);
//I changed the if condition here as now we will check the presence of the username in the array

if (index!=-1) {

//in this if condition we will check the equality with the hash password stored in the pass_ar at the same index

//if the password matches then login will be successful
if (hash(pwdword).equals(pass_ar.get(index))) {
done = true;

System.out.println("Login successful");
} else {
System.out.println("Password incorrect");
}
} else {
System.out.println("Login unsuccessful. Please register for an account");
}

attempts++;

if (attempts == 3 && !done) {
  
System.out.println("You entered the max number of" +
"entries. Account locked for security." +
"Contact system administrator");

done = true;

}
}
}
}