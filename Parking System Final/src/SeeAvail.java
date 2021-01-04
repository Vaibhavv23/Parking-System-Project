import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SeeAvail extends JFrame{
	JLabel usernamelbl,carnolbl,usernametf,carnotf,avail, reserved,availtf, reservedtf;
	JButton back,availimg,reservedimg;
	DBHandler objdb=new DBHandler();
  public SeeAvail(String username,String carno) {
	  setLayout(null);
		Font f = new Font("Arial", Font.PLAIN, 18);
		usernamelbl = new JLabel("User Name");
		usernamelbl.setBounds(130, 50, 100, 30);
		usernamelbl.setFont(f);
		add(usernamelbl);
		
		carnolbl=new JLabel("Car No");
		carnolbl.setBounds(130, 100, 100, 30);
		carnolbl.setFont(f);
		add(carnolbl);
		
		usernametf = new JLabel(username);
		usernametf.setBounds(270, 50, 100, 30);
		usernametf.setFont(f);
		add(usernametf);
		
		carnotf=new JLabel(carno);
		carnotf.setBounds(270, 100, 180, 30);
		carnotf.setFont(f);
		add(carnotf);
		
		
		avail = new JLabel("Available Slots");
		avail.setFont(f);
		avail.setBounds(150, 150, 150, 40);
		add(avail);

		reserved = new JLabel("Reserved Slots");
		reserved.setFont(f);
		reserved.setBounds(330, 150, 150, 40);
		add(reserved);
		
		ImageIcon availicn=new ImageIcon("Images/availcaricon.png");
		availimg =new JButton(availicn);
		availimg.setBounds(150, 220, 135, 135);
		add(availimg);

		ImageIcon reservedicn=new ImageIcon("Images/reservedcaricon.png");
		reservedimg =new JButton(reservedicn);
		reservedimg.setBounds(330, 220, 135, 135);
		add(reservedimg);

		int availcount = objdb.getAvailableCount();
		int reservecount = 36 - availcount;

		availtf = new JLabel();
		availtf.setFont(f);
		availtf.setBounds(200, 360, 40, 40);
		availtf.setText(String.valueOf(availcount));
		add(availtf);

		reservedtf = new JLabel();
		reservedtf.setFont(f);
		reservedtf.setBounds(380, 360, 40, 40);
		reservedtf.setText(String.valueOf(reservecount));
		add(reservedtf);
		
		ImageIcon backicn=new ImageIcon("Images/backbutton.jpg");
		back=new JButton(backicn);
		back.setFont(f);
		back.setBounds(20, 420, 102, 40);
		add(back);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		// footer
		setVisible(true);
		setBounds(250, 0, 620, 520);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
