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

public class UserLoginPage extends JFrame {
	JLabel arrlbl[] = new JLabel[2];
	JTextField usrtf;
	JPasswordField usrpwd;
	JPanel pane;
	TitledBorder loginborder;
	JButton lgnbtn, signbtn;
	DBHandler objdb = new DBHandler();

	public UserLoginPage() {
		setLayout(null);
		LineBorder border = new LineBorder(Color.ORANGE, 3, true);
		loginborder = new TitledBorder(border, "USER LOGIN", TitledBorder.CENTER,
	    TitledBorder.DEFAULT_POSITION,new Font("Arial", Font.BOLD, 14), Color.BLUE);

		Font f = new Font("Calibri", Font.PLAIN, 18);
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBounds(20, 20, 550, 400);
		pane.setBorder(loginborder);
		add(pane);
		// creation of title
		JLabel title = new JLabel("WELCOME TO USER LOGIN");
		title.setForeground(Color.red);
		Font ftitle = new Font("Arial", Font.PLAIN, 25);
		title.setFont(ftitle);

		title.setBounds(100, 50, 400, 50);
		pane.add(title);

		// creation of users label
		String strlbl[] = { "Username", "Password" };
		int ypos[] = { 150, 210 };
		for (int i = 0; i < 2; i++) {
			arrlbl[i] = new JLabel(strlbl[i]);
			arrlbl[i].setFont(f);
			arrlbl[i].setBounds(160, ypos[i], 100, 40);
			pane.add(arrlbl[i]);
		}

		// creation of user textfield
		usrtf = new JTextField();
		usrtf.setBounds(300, 150, 150, 40);
		pane.add(usrtf);

		// creation of user password field
		usrpwd = new JPasswordField();
		usrpwd.setBounds(300, 210, 150, 40);
		pane.add(usrpwd);
           
		// creation of search icon
//		ImageIcon searchicon=new ImageIcon("Images/searchicon.png");
//		JButton searchbtn=new JButton(searchicon);
//		searchbtn.setBounds(480, 150, 40, 40);
//		pane.add(searchbtn);
		
		// creation of Signup button
		ImageIcon icnsignup = new ImageIcon("Images/button_sign-up.jpg");
		signbtn = new JButton(icnsignup);
		signbtn.setBounds(20, 320, 135, 40);
		signbtn.setFont(f);
		pane.add(signbtn);

		// creation of user login btn
		ImageIcon icnlogin = new ImageIcon("Images/loginbutton.jpg");
		lgnbtn = new JButton(icnlogin);
		lgnbtn.setFont(f);
		lgnbtn.setBounds(400, 320, 114, 40);
		pane.add(lgnbtn);

		// signup button actionListener
		signbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				NewUser nu = new NewUser();
			}
		});

		// login button actionListener
		lgnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usrtf.getText();
				String userpassword = usrpwd.getText();
				if (username.isEmpty() || userpassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "please fill out the textfields before login");
				} else if (objdb.getBlacklistStatus(username)) {
					JOptionPane.showMessageDialog(null, "You are blacklist.Contact admin for further info.");
				} else if (objdb.IsvalidUser(username, usrpwd.getText())) {
					JOptionPane.showMessageDialog(null, "Wrong username or password");
					usrtf.setText("");
					usrpwd.setText("");
				} else {
					String carno = objdb.getCarnobyusername(username);
					if (objdb.verifyneworexistinguser(carno)) {
						dispose();
						UserHomePage uhp = new UserHomePage(username, carno);
					} else {
						if (objdb.getCarStatusFromCarno(carno).equals("booked")) {
							dispose();
							UserHomePage uhp = new UserHomePage(username, carno);
						} else {
							dispose();
							Update up = new Update(username);
						}
					}

				}
			}
		});

//		searchbtn.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//			String pwd =objdb.getuserPasswordbyUserName(usrtf.getText());
//			usrpwd.setText(pwd);
//			}
//		});
		setVisible(true);
		setTitle("User Login Page");
		setBounds(250, 0, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
