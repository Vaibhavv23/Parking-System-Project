import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminHomePage extends JFrame {
	JTable userinfotable;
	JScrollPane jsp;
	JLabel usernamelbl,carnolbl,phonenolbl,carstatuslbl,blackliststatuslbl,finelbl,usernametf,carnotf,
	phonenotf,carstatustf,blackliststatustf,tagline1,tagline2;
	JTextField finetf;
	JButton blacklist,removeblacklist,delete,imposefine,logo;
  DBHandlerTable objtable=new DBHandlerTable();
  DBHandler objdb=new DBHandler();
  public AdminHomePage() {
	  // header
	  Font f1=new Font("Open-Sans",Font.CENTER_BASELINE,22);
	    // creation of userinfo panel in which info of user is printed.
		ImageIcon icnlogo=new ImageIcon("Images/digiparklogo.png");
		logo=new JButton(icnlogo);
		logo.setBounds(10, 20, 300, 115);
		add(logo);
		
		tagline1=new JLabel("WELCOME TO DIGIPARK WHERE WE MAKE PARKING DIGITALISE");
		tagline1.setFont(f1);
		tagline1.setBounds(400, 60, 800, 30);
		add(tagline1);
        
		


	  Font f=new Font("Arial",Font.PLAIN,18);
		setLayout(null);
		setVisible(true);
	  
		// labels
		usernamelbl=new JLabel("User Name");
		usernamelbl.setFont(f);
		usernamelbl.setBounds(830, 170, 120, 30);
		add(usernamelbl);

		carnolbl=new JLabel("Car No.");
		carnolbl.setFont(f);
		carnolbl.setBounds(830, 220, 120, 30);
		add(carnolbl);
		
		phonenolbl=new JLabel("Phone no.");
		phonenolbl.setFont(f);
		phonenolbl.setBounds(830, 270, 120, 30);
		add(phonenolbl);
		
		carstatuslbl=new JLabel("Car Status");
		carstatuslbl.setFont(f);
		carstatuslbl.setBounds(830, 320, 120, 30);
		add(carstatuslbl);
		
		blackliststatuslbl=new JLabel("Blacklist Status");
		blackliststatuslbl.setFont(f);
		blackliststatuslbl.setBounds(830, 370, 160, 30);
		add(blackliststatuslbl);

		finelbl=new JLabel("Fine Imposed");
		finelbl.setFont(f);
		finelbl.setBounds(830, 420, 120, 30);
		add(finelbl);
       
       // buttons
		ImageIcon icnblock=new ImageIcon("Images/blockbutton.jpg");
       blacklist=new JButton(icnblock);
       blacklist.setFont(f);
       blacklist.setBounds(800, 480, 104, 40);
       add(blacklist);
       
       ImageIcon icnunblock=new ImageIcon("Images/unblock.jpg");
       removeblacklist=new JButton(icnunblock);
       removeblacklist.setFont(f);
       removeblacklist.setBounds(800, 540, 136, 40);
       add(removeblacklist);
       
       ImageIcon icndelete=new ImageIcon("Images/deleteuser.jpg");
       delete=new JButton(icndelete);
       delete.setFont(f);
       delete.setBounds(980, 480, 169, 40);
       add(delete);
       
       ImageIcon icnfine=new ImageIcon("Images/imposefine.jpg");
       imposefine=new JButton(icnfine);
       imposefine.setFont(f);
       imposefine.setBounds(980, 540, 167, 40);
       add(imposefine);
       
       ImageIcon loginasicon=new ImageIcon("Images/loginasbutton.jpg");
	  JButton  logout=new JButton(loginasicon);
	    logout.setFont(f1);
	    logout.setBounds(1200, 630, 133, 40);
	    add(logout);
       logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				  dispose();
			}});
      
       
		// user tables 
		userinfotable=objtable.getUserInfoTable();
	     jsp=new JScrollPane(userinfotable);
	     jsp.setBounds(60, 150, 650,530);
	     getContentPane().add(jsp);
        userinfotable.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
			
			}
			public void mouseExited(MouseEvent arg0) {
				
			}
		
			public void mouseEntered(MouseEvent arg0) {
			
			}
			
			public void mouseClicked(MouseEvent arg0) {
				int row=userinfotable.rowAtPoint(arg0.getPoint());
				 String username= (String) userinfotable.getValueAt(row, 0);
				 String carno=(String) userinfotable.getValueAt(row, 1);
				 String phoneno=(String) userinfotable.getValueAt(row, 2);
				 String carstatus=(String) userinfotable.getValueAt(row, 3);
				 String blackliststatus=(String) userinfotable.getValueAt(row, 4);
				 int finech=(int) userinfotable.getValueAt(row, 5);
				 
	             usernametf=new JLabel(username);
	             usernametf.setFont(f);
	             usernametf.setBounds(1050,170,120,30);
	             add(usernametf);
		         		
	             carnotf=new JLabel(carno);
	             carnotf.setFont(f);
	             carnotf.setBounds(1050,220,170,30);
	             add(carnotf);
	             
	             phonenotf=new JLabel(phoneno);
	             phonenotf.setFont(f);
	             phonenotf.setBounds(1050,270,120,30);
	             add(phonenotf);
	             
	             carstatustf=new JLabel(carstatus);
	             carstatustf.setFont(f);
	             carstatustf.setBounds(1050,320,120,30);
	             add(carstatustf);
	             
	             blackliststatustf=new JLabel(blackliststatus);
	             blackliststatustf.setFont(f);
	             blackliststatustf.setBounds(1050, 370, 120, 30);
	             add(blackliststatustf);

                  finetf=new JTextField(String.valueOf(finech));
                  finetf.setFont(f);
 	              finetf.setBounds(1050, 420, 120, 30);
 	             add(finetf);
 	             
 	             blacklist.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						objdb.updateblackliststatusfromusername("yes", username);
						dispose();
						AdminHomePage ahp=new AdminHomePage();
						JOptionPane.showMessageDialog(null, "selected user added to blacklist successfully");
					}
				});
 	             removeblacklist.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						objdb.updateblackliststatusfromusername("no", username);
						dispose();
						AdminHomePage ahp=new AdminHomePage();
						JOptionPane.showMessageDialog(null, "selected user removed from blacklist successfully");
					
					}
				});
 	             imposefine.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						objdb.updateFine(Integer.parseInt(finetf.getText()), carno);
						dispose();
						AdminHomePage ahp=new AdminHomePage();
						JOptionPane.showMessageDialog(null, "fine imposed on selected user successfully");
					}
				});
 	             delete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						objdb.deletetblpaymentffromcarno(carno);
						objdb.tbllapffornewuser("avail", carno);
						objdb.deletecarinfobyusername(username);
						objdb.deletetbluserbyusername(username);
						dispose();
						AdminHomePage ahp=new AdminHomePage();
						JOptionPane.showMessageDialog(null, "selected user deleted successfully.");
					}
				});
			}
		});
		
       this.repaint();
	  // footer
	  setSize(Toolkit.getDefaultToolkit().getScreenSize());
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}
