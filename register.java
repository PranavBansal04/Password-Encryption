package network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class register {

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

// Main Method
public static void main(String[] args) throws FileNotFoundException, IOException {
  
   FileWriter object = new FileWriter("database.txt", true);
   File store = new File("database.txt");
   Scanner sc1 = new Scanner(store);
   ArrayList <String> user_ar = new ArrayList<String>();
   ArrayList <String> pass_ar = new ArrayList<String>();
   while(sc1.hasNext()) {
       String user1 = sc1.next().trim();
       String hash1 = sc1.next();
       user_ar.add(user1);
       pass_ar.add(hash1);

   }


Scanner sc = new Scanner(System.in);
System.out.println("Enter new user, Yes or No?");
String response = sc.next();
while (response.equals("Yes")) {


System.out.print("Enter a username: ");
String user = sc.next();
while(user_ar.indexOf(user)!=-1) {
   System.out.println("User name already exists!Enter a different user name :");
   user = sc.next();
}
user_ar.add(user);////////
System.out.print("Enter a pwdword: ");

String pwd = sc.next();
String newHash = hash(pwd);

FileWriter object1 = new FileWriter("database.txt", true);
PrintWriter writer = new PrintWriter(object1);
writer.write("\r\n");
writer.write(user);
writer.write("\t");
writer.write(newHash);
//writer.write("\t");
//writer.write(pwd);
writer.close();

System.out.println("Enter another user, Yes or No?");
response = sc.next();
}

  
System.out.println(" Credentials saved. You are now registered");
}
}