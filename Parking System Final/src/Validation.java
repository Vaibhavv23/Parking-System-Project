import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Validation {
	 Pattern pattern;
	 Matcher matcher;

	public  boolean UsernameValidator(String username) {
		// username constraints -  input only char upper or lowercase between 6 to 20 char
		String patternvalidation = "^[a-zA-Z0-9@!#$%^&*]{3,20}$";
		boolean b=true;
		pattern = Pattern.compile(patternvalidation);
		matcher=pattern.matcher(username);
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "Username must be of length between 3 to 20.");
			b=false;
		}
		return b;
	}
	public boolean PasswordValidator(String password) {
		// password constraints - input password between 8 to 40 characters
//		Contain at least one digit.
//		Contain at least one lower case character.
//		Contain at least one upper case character.
//		Contain at least on special character from [ @ # $ % ! . ].
		String patternvalidation="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!^&]).{8,40})";
		boolean b=true;
		pattern=Pattern.compile(patternvalidation);
		matcher=pattern.matcher(password);
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "Password must contain atleast one digit,one lowercase letter,one uppercase letter and one special "
					+ "character . Length should be between 8 to 40 characters.");
			b=false;
		}
		return b;
	}
	public  boolean FullNameValidator(String fullname) {
		// 2 words - Aman Jain 
		// limits 8 to 40
		// space must be between 2 words
		// 1st letter of each word is capital
		String patternvalidation="(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";
		boolean b=true;
		pattern=Pattern.compile(patternvalidation);
		matcher=pattern.matcher(fullname);
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null,"Fullname must contain 2 CamelCase words seperated by space.");
			b=false;
		}
		return b;
		}
public boolean PhonenoValidator(String phoneno) {
	// then contain 7 or 8 or 9.
	// then contains 9 digits
	String patternvalidation="^[789][0-9]{9}$";
	boolean b=true;
	pattern=Pattern.compile(patternvalidation);
	matcher=pattern.matcher(phoneno);
	if(!matcher.matches()) {
		JOptionPane.showMessageDialog(null, "Please enter a valid mobile number");
		b=false;
	}
	return b;
}

public boolean CarNoValidator(String carno) {
	String patternvalidation="^[A-Z]{2} [0-9]{2} [A-Z]{2} [0-9]{4}$";
	boolean b=true;
	pattern=Pattern.compile(patternvalidation);
	matcher=pattern.matcher(carno);
	if(!matcher.matches()) {
		JOptionPane.showMessageDialog(null, "Please enter a valid car number");
		b=false;
	}
	return b;
}
}
