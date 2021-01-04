import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CarInfo extends JFrame {
	JPanel carinfo, packinfo, goldinfo, silverinfo, bronzeinfo;
	TitledBorder carborder, packborder, goldborder, silverborder, bronzeborder;
	JLabel carid,caridtf,carno, brand, SelectPack, stmt, validity1, validity2, validity3, charge1, charge2,
			charge3;
	JTextField  carnotf;
	JButton next, clear, back;
	JComboBox brandjcb;
	JRadioButton gold, silver, bronze;
	ButtonGroup packs;
    DBHandler objdb=new DBHandler();
    Validation valid=new Validation();
   public CarInfo(String username) {
	   setLayout(null);
		Font f = new Font("Calibri", Font.PLAIN, 18);
		LineBorder border=new LineBorder(Color.orange, 3, true);
		carborder=new TitledBorder(border, "CAR INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		carinfo = new JPanel();
		carinfo.setLayout(null);
		carinfo.setBounds(30, 30, 800, 600);
		carinfo.setBorder(carborder);
		add(carinfo);
          
		// labels
	    carid = new JLabel("Car ID");
		carid.setBounds(250, 60, 100, 40);
		carid.setFont(f);
		carinfo.add(carid);

		carno = new JLabel("Car No.");
		carno.setBounds(250, 120, 100, 40);
		carno.setFont(f);
		carinfo.add(carno);

		brand = new JLabel("Car Brand");
		brand.setBounds(250, 180, 100, 40);
		brand.setFont(f);
		carinfo.add(brand);
		
		caridtf=new JLabel();
		caridtf.setFont(f);
		caridtf.setText(String.valueOf(objdb.getMaxID("carinfo", "carid")));
		caridtf.setBounds(420, 60, 100, 40);
		carinfo.add(caridtf);
		
		// carno. textbox and brand combobox
		carnotf = new JTextField();
		carnotf.setBounds(420, 120, 150, 40);
		carinfo.add(carnotf);

		brandjcb = new JComboBox();
		String brands[] = { "Ferrari", "MG-Hector", "Mercedes-Benz", "Audi", "BMW", "Volkswagen", "Ford", "Hyundai",
				"Toyota", "Renault", "Nissan", "Honda" };
		for (String str : brands) {
			brandjcb.addItem(str);
		}
		brandjcb.setBounds(420, 180, 150, 40);
		carinfo.add(brandjcb);


         // packinfo text
		LineBorder borderp=new LineBorder(Color.green, 3, true);
		packborder=new TitledBorder(borderp, "PACK INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		packinfo = new JPanel();
		packinfo.setBorder(packborder);
		packinfo.setLayout(null);
		packinfo.setBounds(30, 250, 750, 150);
		carinfo.add(packinfo);

		LineBorder bordergold=new LineBorder(Color.YELLOW, 3, true);
		goldborder=new TitledBorder(bordergold, "GOLD USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		goldinfo = new JPanel();
		goldinfo.setBorder(goldborder);
		goldinfo.setLayout(null);
		goldinfo.setBounds(30, 30, 220, 100);
		packinfo.add(goldinfo);

		validity1 = new JLabel("Validity : 5 days");
		Font font = new Font("Calibri", Font.BOLD, 18);
		validity1.setFont(font);
		validity1.setBounds(20, 20, 200, 30);
		goldinfo.add(validity1);

		charge1 = new JLabel("Cost : 80 Rs");
		charge1.setFont(font);
		charge1.setBounds(20, 60, 200, 30);
		goldinfo.add(charge1);

		LineBorder bordersilver=new LineBorder(Color.DARK_GRAY, 3, true);
		silverborder=new TitledBorder(bordersilver, "SILVER USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		silverinfo = new JPanel();
		silverinfo.setBorder(silverborder);
		silverinfo.setLayout(null);
		silverinfo.setBounds(260, 30, 220, 100);
		packinfo.add(silverinfo);

		validity2 = new JLabel("Validity : 3 days");
		validity2.setFont(font);
		validity2.setBounds(20, 20, 200, 30);
		silverinfo.add(validity2);
       
		charge2 = new JLabel("Cost : 50 Rs");
		charge2.setFont(font);
		charge2.setBounds(20, 60, 200, 30);
		silverinfo.add(charge2);

		LineBorder borderbronze=new LineBorder(Color.magenta, 3, true);
		bronzeborder=new TitledBorder(borderbronze, "BRONZE USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		bronzeinfo = new JPanel();
		bronzeinfo.setBorder(bronzeborder);
		bronzeinfo.setLayout(null);
		bronzeinfo.setBounds(500, 30, 220, 100);
		packinfo.add(bronzeinfo);

		validity3 = new JLabel("Validity : 1 day");
		validity3.setFont(font);
		validity3.setBounds(20, 20, 200, 30);
		bronzeinfo.add(validity3);

		charge3 = new JLabel("Cost : 20 Rs");
		charge3.setFont(font);
		charge3.setBounds(20, 60, 200, 30);
		bronzeinfo.add(charge3);
		
		// radiobuttons
		SelectPack = new JLabel("Select pack");
		SelectPack.setFont(f);
		SelectPack.setBounds(50, 450, 120, 30);
		carinfo.add(SelectPack);

		gold = new JRadioButton("Gold");
		gold.setBounds(200, 450, 120, 30);
		gold.setFont(f);
		carinfo.add(gold);

		silver = new JRadioButton("Silver");
		silver.setBounds(340, 450, 120, 30);
		silver.setFont(f);
		carinfo.add(silver);

		bronze = new JRadioButton("Bronze");
		bronze.setBounds(480, 450, 120, 30);
		bronze.setFont(f);
		bronze.setSelected(true);
		carinfo.add(bronze);

		packs = new ButtonGroup();
		packs.add(bronze);
		packs.add(gold);
		packs.add(silver);

		// buttons 
		ImageIcon icnclear=new ImageIcon("Images/clearbutton.jpg");
		clear=new JButton(icnclear);
		clear.setFont(f);
		clear.setBounds(40, 520, 114, 40);
		carinfo.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        carnotf.setText("");		
			}
		});
		ImageIcon icnnext=new ImageIcon("Images/nextbutton.jpg");
		next = new JButton(icnnext);
		next.setFont(f);
		next.setBounds(630, 520, 101, 40);
		carinfo.add(next);
         next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(carnotf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill out all the textfields.");
				}else if(objdb.uniqueCarno(carnotf.getText())) {
					JOptionPane.showMessageDialog(null, "please enter a unique carno.");
				}
				else if(!valid.CarNoValidator(carnotf.getText())) {
				
				}
				else {
				objdb.updateCidInTbluser(Integer.parseInt(caridtf.getText()), username);
				String pack;
				if (gold.isSelected()) {
					pack = "Gold";
				} else if (silver.isSelected()) {
					pack = "Silver";
				} else {
					pack = "Bronze";
				}

				objdb.insertInCarinfo(username,Integer.parseInt(caridtf.getText()) , carnotf.getText(),pack);
				dispose();
				UserLoginPage loginpage=new UserLoginPage();
				}
				}});
    
        // footer
		setVisible(true);
		setTitle("Car info Page");
		setBounds(250, 0, 850, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
}
