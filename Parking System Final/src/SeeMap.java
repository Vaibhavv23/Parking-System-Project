import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SeeMap extends JFrame {
  LinkedList<Location> lst=new LinkedList<>();
  DBHandler objdb=new DBHandler();
  JButton btn[][]=new JButton[7][7];
	JPanel map,scale;
	JLabel laplbl[]=new JLabel[7];
	JLabel tracklbl[]=new JLabel[7];
	TitledBorder mapborder,scaleborder;
	JButton avail,reserved,user,back;
	JLabel availlbl,reservedlbl,userlbl;
  
	public SeeMap(int lapno,int trackno) {
		lst=objdb.selectlapTable();
		setLayout(null);
		Font f=new Font("Calibri",Font.PLAIN,18);
		// creation of panel along with the border
		LineBorder bordermap=new LineBorder(Color.ORANGE,3,true);
	    mapborder = new TitledBorder(bordermap, " PARKING LOT MAP ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.blue);
		map=new JPanel();
		map.setLayout(null);
		map.setBounds(20, 20, 600, 550);
		map.setBorder(mapborder);
		add(map);
		
		// creations of buttons
		ImageIcon backicn=new ImageIcon("Images/backbutton.jpg");
		back=new JButton(backicn);
		back.setBounds(720, 520, 102, 40);
		add(back);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
		   dispose();
			}
		});
		
		int ypos=100;

		for(int i=1;i<=6;i++)
		{
			String c=String.valueOf(i);
			int xpos=100;
			for(int j=1;j<=6;j++) {
				String d="." +String.valueOf(j);
				String btnname=c+d;
				btn[i][j]=new JButton(btnname);
				 btn[i][j].setBounds(xpos, ypos, 70, 50);
				 xpos=xpos+80;
				 map.add(btn[i][j]);
			}
					ypos=ypos+70;
			}
		
		  for (Location loc : lst) {
			 
			if(loc.getLocstatus().equals("avail")) {
				btn[loc.getLapno()][loc.getTrackno()].setBackground(Color.GREEN);
			}
			else  {
				btn[loc.getLapno()][loc.getTrackno()].setBackground(Color.red);
			}
			
			if(loc.getLapno()==lapno&&loc.getTrackno()==trackno){
				btn[loc.getLapno()][loc.getTrackno()].setBackground(Color.yellow);
		}
		  }
			
			// creation of laplabels
			String lapname[] ={"Lap 1","Lap 2","Lap 3","Lap 4","Lap 5","Lap 6"};
			int yposlbl=120;
			for(int k=0;k<6;k++) {
				   laplbl[k]=new JLabel(lapname[k]);
				   laplbl[k].setBounds(30, yposlbl, 60, 30);
				   yposlbl=yposlbl+70;
				   laplbl[k].setFont(f);
				   map.add(laplbl[k]);
			}
			
			
			
			// creation of track label
		   String trackname[]= {"Track 1","Track 2","Track 3","Track 4","Track 5","Track 6"};
		   int xposlbl=110;
		   for(int k=0;k<6;k++) {
			   tracklbl[k]=new JLabel(trackname[k]);
			   tracklbl[k].setBounds(xposlbl,50 , 60, 30);
				 xposlbl=xposlbl+80;
			   tracklbl[k].setFont(f);
			   map.add(tracklbl[k]);
		}
		   
		   // creation of scale panel
		   LineBorder borderscale=new LineBorder(Color.CYAN,3,true);
		    scaleborder = new TitledBorder(borderscale, " USER INFO ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
					new Font("Arial", Font.BOLD, 14), Color.blue);
			scale=new JPanel();
			scale.setLayout(null);
			scale.setBounds(640, 150, 250, 300);
			scale.setBorder(scaleborder);
			add(scale);
			
			// content inside scale panel
			 avail=new JButton();
			 avail.setBounds(20, 210, 50, 50);
			 avail.setBackground(Color.green);
			 scale.add(avail); 
			
			 reserved=new JButton();
			 reserved.setBounds(20, 50, 50, 50);
			 reserved.setBackground(Color.red);
			 scale.add(reserved);
			 
			 user=new JButton();
			 user.setBounds(20, 130, 50, 50);
			 user.setBackground(Color.yellow);
			 scale.add(user);
			 
			 reservedlbl=new JLabel("RESERVED");
			 reservedlbl.setBounds(100,50 , 80, 50);
			 scale.add(reservedlbl);
			 
			 userlbl=new JLabel("USER");
			 userlbl.setBounds(100,130 , 50, 50);
			 scale.add(userlbl);
			 
			 availlbl=new JLabel("AVAIL");
			 availlbl.setBounds(100,210,50, 50);
			 scale.add(availlbl);
		
			// frame properties
			setVisible(true);
			setTitle("Map Page");
		    setBounds(50,50,950,650);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}

