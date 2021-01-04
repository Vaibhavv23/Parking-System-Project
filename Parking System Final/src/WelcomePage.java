import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomePage extends JFrame {
	JLabel title,loginlbl,lblbg;
	JButton admin,user,adminimage,userimage;
	public WelcomePage() {
		Font f=new Font("Arial",Font.BOLD,30);
		Font f1=new Font("Arial",Font.BOLD,24);
		setLayout(null);
		setVisible(true);
	ImageIcon img=new ImageIcon("Images/bgparking1.jpg");
	lblbg=new JLabel(img);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
	   title=new JLabel("WELCOME TO THE DIGIPARK");
	   title.setFont(f);
	   title.setForeground(Color.WHITE);
	    title.setBounds(400, 280, 700, 60);
	   lblbg.add(title);
	   
	    loginlbl=new JLabel("LOGIN AS");
	    loginlbl.setFont(f1);
	    loginlbl.setForeground(Color.WHITE);
	    loginlbl.setBounds(600, 360, 200, 60);
	    lblbg.add(loginlbl);
	     
	    ImageIcon icnadmin=new  ImageIcon("Images/adminbtn.jpg");	
	    admin=new JButton(icnadmin);
	    admin.setFont(f1);
	    admin.setBounds(360,550 , 125, 45);
	    lblbg.add(admin);
	    admin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				AdminLoginPage admlgn=new AdminLoginPage();
			}
		});
	    ImageIcon icnuser=new  ImageIcon("Images/button_user.jpg");
	    		
	    user=new JButton(icnuser);
	    user.setFont(f1);
	    user.setBounds(800, 550, 107, 45);
	    lblbg.add(user);
	    user.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserLoginPage usrlgn=new UserLoginPage(); 
			}
		});
	   setSize(1360, 760);
	   
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
}
