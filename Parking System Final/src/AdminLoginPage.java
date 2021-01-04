import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AdminLoginPage extends JFrame {
	JLabel arrlbl[]=new JLabel[2];
	JTextField usrtf;
	JPasswordField usrpwd;
	JButton lgnbtn;
	JPanel pane;
	TitledBorder adminborder;
   DBHandler objdh=new DBHandler();
	public AdminLoginPage() {
		setLayout(null);
		LineBorder border = new LineBorder(Color.ORANGE, 3, true);
		adminborder = new TitledBorder(border, "USER LOGIN", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.BLUE);
		 Font f=new Font("Calibri",Font.PLAIN,18);
		 pane=new JPanel();
		 pane.setLayout(null);
		 pane.setBounds(20,20,550,400);
		 pane.setBorder(adminborder);
		 add(pane);
		 //  creation of title 
		 JLabel title=new JLabel("WELCOME TO ADMIN LOGIN");
		 Font ftitle=new Font("Arial",Font.PLAIN,25);
		 title.setFont(ftitle);
		 title.setForeground(Color.red);
		 title.setBounds(100, 50, 400, 50);
		 pane.add(title);
		 
		 // creation of admin label
		 String strlbl[]= {"Adminname","Password"};
		 int ypos[]= {150,210};
		 for (int i=0;i<2;i++) {
			 arrlbl[i]=new JLabel(strlbl[i]);
			 arrlbl[i].setFont(f);
			 arrlbl[i].setBounds(160, ypos[i], 100, 40);
			 pane.add(arrlbl[i]);
		 }
		 
		 // creation of adminname textfield
		   usrtf=new JTextField();
		   usrtf.setBounds(300,150,150,40);
		   pane.add(usrtf);

		 // creation of  adminpassword field
		   usrpwd=new JPasswordField();
		   usrpwd.setBounds(300,210,150,40);
		   pane.add(usrpwd);

		   // creation of admin login button
		   ImageIcon icnlogin = new ImageIcon("Images/loginbutton.jpg");
			lgnbtn = new JButton(icnlogin);
		   lgnbtn.setFont(f);
		   lgnbtn.setBounds(230, 310, 114, 40);
		   pane.add(lgnbtn);
		   
		   // login button actionListener
		   lgnbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(usrtf.getText().isEmpty()||usrpwd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "please fill out the textfields before login");
				}
				else if(objdh.validAdmin(usrtf.getText(),usrpwd.getText())) {
					JOptionPane.showMessageDialog(null, "Wrong username or password");
					usrtf.setText("");
					usrpwd.setText("");
				}
				else {
					AdminHomePage ahp=new AdminHomePage();
					dispose();
				}
				
			}
		});
		   
		   setVisible(true);
		   setTitle("Admin Login Page");
		   setBounds(250,0,600,500);
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
