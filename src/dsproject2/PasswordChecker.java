/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproject2;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author meltem koc
 */
public class PasswordChecker {
    private File dictionaryFile = new File("src/dsproject2/See Words.txt");
	private File mostlyUsedFile = new File("src/dsproject2/Mostly Used Passwords.txt");
	
	public String inputPassword() {
		Scanner scan = new Scanner(System.in);
		System.out.println("WELCOME TO PASSWORD CHECKER SYSTEM");
		System.out.println("Your password must be at least 5 characters long.");
		System.out.println("Your password must not be a word in the english dictionary or one of mostly used password.");
		System.out.println("Your password must not be a word in the dictionary followed/prefixed by a digit 0-9");
		System.out.println("(e.g., hello5, 5hello)");
		System.out.print("\nEnter a password: ");
		String password = scan.next();
		scan.close();
		return password;
	}
	
	public boolean passwordCheck(String password) throws IOException {
		BufferedReader brDictionary = new BufferedReader(new FileReader(dictionaryFile));
		BufferedReader brMostlyUsed = new BufferedReader(new FileReader(mostlyUsedFile));
		String dictionaryLine = "", mostlyUsedLine = "";
		password = password.toLowerCase();
		if(password.length() < 5) {
			brDictionary.close();
			brMostlyUsed.close();
			return false;
		}
		while((mostlyUsedLine = brMostlyUsed.readLine()) != null) {
			if(password.equals(mostlyUsedLine)) {
				brDictionary.close();
				brMostlyUsed.close();
				return false;
			}
		}
		while((dictionaryLine = brDictionary.readLine()) != null) {
			if(password.equals(dictionaryLine)) {
				brDictionary.close();
				brMostlyUsed.close();
				return false;
			}
			else if(password.substring(1, password.length()).equals(dictionaryLine) || 
					(password.substring(0, password.length() - 1).equals(dictionaryLine))) {
				if(password.charAt(0) == '0' || password.charAt(0) == '1' || password.charAt(0) == '2' || 
						password.charAt(0) == '3' || password.charAt(0) == '4' || password.charAt(0) == '5' ||
						password.charAt(0) == '6' || password.charAt(0) == '7' || password.charAt(0) == '8' || 
						password.charAt(0) == '9') {
					brDictionary.close();
					brMostlyUsed.close();
					return false;
				}
				else if(password.charAt(password.length() - 1) == '0' || password.charAt(password.length() - 1) == '1' 
						|| password.charAt(password.length() - 1) == '2' || password.charAt(password.length() - 1) == '3' || 
						password.charAt(password.length() - 1) == '4' || password.charAt(password.length() - 1) == '5' ||
						password.charAt(password.length() - 1) == '6' || password.charAt(password.length() - 1) == '7' || 
						password.charAt(password.length() - 1) == '8' || password.charAt(password.length() - 1) == '9') {
					brDictionary.close();
					brMostlyUsed.close();
					return false;
				}
			}
		}
		brDictionary.close();
		brMostlyUsed.close();
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		PasswordChecker passwordChecker = new PasswordChecker();
		boolean isPasswordGood;
		String password = passwordChecker.inputPassword();
		isPasswordGood = passwordChecker.passwordCheck(password);
		if(isPasswordGood) {
			System.out.println(password + " is a good password.");
		}
		else {
			System.out.println(password + " is not a good password.");
		}
	}
    
}
