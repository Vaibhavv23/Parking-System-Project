import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Park extends JFrame{
	JPanel park;
	TitledBorder parkborder;
	JLabel usernamelbl,carnolbl,usernametf,carnotf,doplbl,doptf,parktable;
	JButton back;
	JTable ptable;
    JScrollPane ptablesp;
	DBHandler objdb=new DBHandler();
	String pack;
  public Park(String username,String carno) {
	  setLayout(null);
		Font f = new Font("Calibri", Font.PLAIN, 18);
		// will create panel of name release and put border around it
		LineBorder border=new LineBorder(Color.GREEN, 4, true);
		parkborder=new TitledBorder(border, "PARK", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		park = new JPanel();
		park.setLayout(null);
		park.setBounds(20, 20, 550, 420);
		park.setBorder(parkborder);
		add(park);
		
		// userinfo
		usernamelbl = new JLabel("User Name");
		usernamelbl.setBounds(100, 50, 100, 30);
		usernamelbl.setFont(f);
		park.add(usernamelbl);
		
		carnolbl=new JLabel("Car No");
		carnolbl.setBounds(100, 100, 100, 30);
		carnolbl.setFont(f);
		park.add(carnolbl);
		
		doplbl = new JLabel("Date Of Parking");
		doplbl.setBounds(100, 150, 150, 30);
		doplbl.setFont(f);
		park.add(doplbl);
     
		usernametf = new JLabel(username);
		usernametf.setBounds(270, 50, 100, 30);
		usernametf.setFont(f);
		park.add(usernametf);
		
		carnotf=new JLabel(carno);
		carnotf.setBounds(270, 100, 180, 30);
		carnotf.setFont(f);
		park.add(carnotf);
		
		
		doptf = new JLabel();
		doptf.setBounds(270, 150, 100, 30);
		Date dt=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yy");
		 String strdate =formatter.format(dt);
		doptf.setText(strdate);
        park.add(doptf);
        
        pack=objdb.getPackFromCarno(carno);
        objdb.insertintblPayment(carno, pack,strdate);
        objdb.UpdateCarStatus("parked", carno);
        parktable = new JLabel("Park Table");
		parktable.setBounds(50, 190, 120, 40);
		parktable.setFont(f);
		park.add(parktable);
		
		DBHandlerTable objTable=new DBHandlerTable();
        ptable=objTable.getParkedTable(carno);
        ptablesp=new JScrollPane(ptable);
  	    ptablesp.setBounds(80, 240, 400, 60);
  	    park.add(ptablesp);

		ImageIcon backicn=new ImageIcon("Images/backbutton.jpg");
		back=new JButton(backicn);
		back.setFont(f);
		back.setBounds(50, 320, 102, 40);
		park.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		   UserHomePage uhp=new UserHomePage(username, carno);
		   dispose();
			}});
       
		// footer
		setVisible(true);
		setTitle("Park Page");
		setBounds(50, 50, 750, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
