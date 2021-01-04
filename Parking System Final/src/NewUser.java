import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NewUser extends JFrame{
	 JPanel pi;
	 TitledBorder pinfoborder;
	 JLabel usrid,usridtf,usrnm,fullnm,gender,phoneno,password,cpassword;
	 JTextField usernmtf,fullnmtf,phonenotf;
	 JRadioButton rbmale;
	 JRadioButton rbfemale;
	 ButtonGroup genders;
	 JPasswordField passwordfield,cpasswordfield;
     JButton back,clear,next;
     Validation valid=new Validation();
     DBHandler dbuser=new DBHandler();
 public NewUser() {
	 setLayout(null);
		Font f=new Font("Calibri",Font.PLAIN,18);

		// creation of border along with the panel.
		LineBorder border=new LineBorder(Color.ORANGE, 3, true);
		pinfoborder=new TitledBorder(border, "PERSONAL INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
	     pi =new JPanel();
		 pi.setLayout(null);
		 pi.setBounds(20,20,650,580);
		 pi.setBorder(pinfoborder);
		 add(pi);
		 
		// creation of jLabels
		 usrid=new JLabel("UserID");
		 usrid.setBounds(50, 30, 100,40 );
		 usrid.setFont(f);
		 pi.add(usrid);
		 usrnm=new JLabel("UserName");
		 usrnm.setBounds(50, 90, 100, 40);
		 usrnm.setFont(f);
		 pi.add(usrnm);
		 
		 fullnm=new JLabel("FullName");
		 fullnm.setBounds(50, 150, 100, 40);
		 fullnm.setFont(f);
		 pi.add(fullnm);
		 
		 gender=new JLabel("Gender");
		 gender.setBounds(50, 210, 100, 40);
		 gender.setFont(f);
		 pi.add(gender);
		 phoneno =new JLabel("Phone Number");
		 phoneno.setBounds(50, 270, 200, 40);
		 phoneno.setFont(f);
		 pi.add(phoneno);
		 password=new JLabel("Password");
		 password.setBounds(50, 330, 100, 40);
		 password.setFont(f);
		 pi.add(password);
		 
		 cpassword=new JLabel("Confirm Password");
		 cpassword.setBounds(50, 390, 200, 40);
		 cpassword.setFont(f);
		 pi.add(cpassword);
		  
		 usridtf=new JLabel();
		 usridtf.setBounds(350, 30, 100, 40);
		 usridtf.setText(String.valueOf(dbuser.getMaxID("tbluserf","userid")));
		 pi.add(usridtf);
		 // textfields
		 
		 usernmtf=new JTextField();
		  usernmtf.setBounds(350, 90, 130, 40);
		  pi.add(usernmtf);
		  
		  fullnmtf=new JTextField();
		  fullnmtf.setBounds(350, 150, 130, 40);
		  pi.add(fullnmtf);
		  
		  phonenotf=new JTextField();
		  phonenotf.setBounds(350, 270, 130, 40);
		  pi.add(phonenotf);
		  
		  // passwordfields
		  passwordfield=new JPasswordField();
		  passwordfield.setBounds(350, 330, 130, 40);
		  pi.add(passwordfield);
		  
		  cpasswordfield=new JPasswordField();
		  cpasswordfield.setBounds(350, 390, 130, 40);
		  pi.add(cpasswordfield);
		  
		  // radio buttons
		  rbmale=new JRadioButton("Male");
		  rbmale.setBounds(350, 210, 80, 40);
		  pi.add(rbmale);
		  
	      rbfemale=new JRadioButton("Female");
		  rbfemale.setBounds(450,210,80,40);
		  rbfemale.setSelected(true);
		  pi.add(rbfemale);
		  
		 genders=new ButtonGroup();
		 genders.add(rbmale);
		 genders.add(rbfemale);

         // buttons
		 ImageIcon icnback=new ImageIcon("Images/backbutton.jpg");
		  back=new JButton(icnback);
		  back.setBounds(30, 470, 102, 40);
		  pi.add(back);
		  ImageIcon icnclear=new ImageIcon("Images/clearbutton.jpg");
		  clear=new JButton(icnclear);
		  clear.setBounds(280, 470, 114, 40);
		  pi.add(clear);
		  ImageIcon icnnext=new ImageIcon("Images/nextbutton.jpg");
		  next=new JButton(icnnext);
		  next.setBounds(480, 470, 101, 40);
		  next.setFont(f);
		  pi.add(next);
        
		  // action listener
		  back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				UserLoginPage login=new UserLoginPage();
					dispose();
				}});
		  clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usernmtf.setText("");
				fullnmtf.setText("");
				phonenotf.setText("");
				passwordfield.setText("");
				cpasswordfield.setText("");		
			}});
		  next.addActionListener(new ActionListener() {
			  
			public void actionPerformed(ActionEvent arg0) {
				if(usernmtf.getText().isEmpty()||fullnmtf.getText().isEmpty()||phonenotf.getText().isEmpty()||passwordfield.getText().isEmpty()||cpasswordfield.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill out all the fields .");
				}
				else if(!valid.UsernameValidator(usernmtf.getText())) {
					System.out.println("Incorrect username");
				}
				else if(dbuser.uniqueusername(usernmtf.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter a unique username");
				}
				else if(!valid.PasswordValidator(passwordfield.getText())){
					System.out.println("Incorrect password");
				}
				else if(!valid.FullNameValidator(fullnmtf.getText())) {
					System.out.println("Incorrect fullname");
				}
				else if(!valid.PhonenoValidator(phonenotf.getText())) {
					System.out.println("Invalid Phone no.");
				}
				else if(!passwordfield.getText().equals(cpasswordfield.getText())){
					JOptionPane.showMessageDialog(null, "Password and Confirm password do not match.");
				}
				else {
				dbuser.Insertintotbl(Integer.parseInt(usridtf.getText()), usernmtf.getText(), fullnmtf.getText(), phonenotf.getText(), passwordfield.getText());
				usridtf.setText(String.valueOf(dbuser.getMaxID("tbluserf", "userid")));
				dispose();
				CarInfo ci=new CarInfo(usernmtf.getText());
				}
			}});
		 // footer
			setVisible(true);
			setTitle("New User Page");
			 setBounds(50,0,750,700);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	

 }
}
