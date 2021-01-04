import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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

public class Update extends JFrame {
	JPanel ui, packinfo, goldinfo, silverinfo, bronzeinfo;
	TitledBorder uinfoborder, packborder, goldborder, silverborder, bronzeborder;
	JLabel fullnmlbl, phonenolbl, passwordlbl, carnolbl, SelectPack, stmt, validity1, validity2, validity3, charge1,
			charge2, charge3;
	JTextField fullnmtf, phonenotf, carnotf;
	JPasswordField passwordtf;
	JRadioButton gold, silver, bronze;
	ButtonGroup packs;
	JButton back, clear, update;
	DBHandler objdb = new DBHandler();
	Validation valid = new Validation();

	public Update(String username) {
		setLayout(null);
		Font f = new Font("Calibri", Font.PLAIN, 18);

		// creation of border along with the panel.
		LineBorder border = new LineBorder(Color.orange, 4, true);
		uinfoborder = new TitledBorder(border, "UPDATE INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.BLUE);
		ui = new JPanel();
		ui.setLayout(null);
		ui.setBounds(20, 20, 830, 650);
		ui.setBorder(uinfoborder);
		add(ui);

		// labels
		fullnmlbl = new JLabel("Full Name");
		fullnmlbl.setFont(f);
		fullnmlbl.setBounds(200, 70, 120, 40);
		ui.add(fullnmlbl);

		phonenolbl = new JLabel("Phone no.");
		phonenolbl.setFont(f);
		phonenolbl.setBounds(200, 130, 120, 40);
		ui.add(phonenolbl);

		passwordlbl = new JLabel("Password");
		passwordlbl.setFont(f);
		passwordlbl.setBounds(200, 190, 120, 40);
		ui.add(passwordlbl);

		carnolbl = new JLabel("Car No.");
		carnolbl.setFont(f);
		carnolbl.setBounds(200, 250, 120, 40);
		ui.add(carnolbl);

		fullnmtf = new JTextField();
		fullnmtf.setBounds(400, 70, 120, 40);
		fullnmtf.setText(objdb.getFullNamebyUserName(username));
		ui.add(fullnmtf);

		phonenotf = new JTextField();
		phonenotf.setBounds(400, 130, 120, 40);
		phonenotf.setText(objdb.getPhonenobyUserName(username));
		ui.add(phonenotf);

		passwordtf = new JPasswordField();
		passwordtf.setBounds(400, 190, 120, 40);
		passwordtf.setText(objdb.getuserPasswordbyUserName(username));
		ui.add(passwordtf);

		carnotf = new JTextField();
		carnotf.setBounds(400, 250, 120, 40);
		carnotf.setText(objdb.getCarnobyusername(username));
		ui.add(carnotf);
        
		update = new JButton("Update");
		update.setBounds(620, 580, 100, 40);
		add(update);
		
		LineBorder borderp=new LineBorder(Color.green, 3, true);
		packborder=new TitledBorder(borderp, "PACK INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		packinfo = new JPanel();
		packinfo.setBorder(packborder);
		packinfo.setLayout(null);
		packinfo.setBounds(30, 310, 750, 150);
		ui.add(packinfo);
        
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

		charge1 = new JLabel("Cost : 100 Rs");
		charge1.setFont(font);
		charge1.setBounds(20, 60, 200, 30);
		goldinfo.add(charge1);

		LineBorder bordersilver=new LineBorder(Color.DARK_GRAY, 3, true);
		silverborder=new TitledBorder(bordersilver, "SILVER USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		silverborder = new TitledBorder("SILVER PACK");
		silverinfo = new JPanel();
		silverinfo.setBorder(silverborder);
		silverinfo.setLayout(null);
		silverinfo.setBounds(260, 30, 220, 100);
		packinfo.add(silverinfo);

		validity2 = new JLabel("Validity : 3 days");
		validity2.setFont(font);
		validity2.setBounds(20, 20, 200, 30);
		silverinfo.add(validity2);

		charge2 = new JLabel("Cost : 60 Rs");
		charge2.setFont(font);
		charge2.setBounds(20, 60, 200, 30);
		silverinfo.add(charge2);

		LineBorder borderbronze=new LineBorder(Color.magenta, 3, true);
		bronzeborder=new TitledBorder(borderbronze, "BRONZE USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLUE);
		bronzeborder = new TitledBorder("BRONZE PACK");
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
		SelectPack.setBounds(50, 550, 120, 30);
		ui.add(SelectPack);

		gold = new JRadioButton("Gold");
		gold.setBounds(200, 550, 120, 30);
		gold.setFont(f);
		ui.add(gold);

		silver = new JRadioButton("Silver");
		silver.setBounds(340, 550, 120, 30);
		silver.setFont(f);
		ui.add(silver);

		bronze = new JRadioButton("Bronze");
		bronze.setBounds(480, 550, 120, 30);
		bronze.setFont(f);
		bronze.setSelected(true);
		ui.add(bronze);

		packs = new ButtonGroup();
		packs.add(bronze);
		packs.add(gold);
		packs.add(silver);

		String packselected = objdb.getPackFromUserName(username);
		if (packselected.equals("Gold")) {
			gold.setSelected(true);
		} else if (packselected.equals("Silver")) {
			silver.setSelected(true);
		} else {
			bronze.setSelected(true);
		}

		
		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (phonenotf.getText().isEmpty() || fullnmtf.getText().isEmpty() || passwordtf.getText().isEmpty()
						|| carnotf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill out all the textfields");
				} else if (objdb.uniqueCarnoforupdation(carnotf.getText(), username)) {
					JOptionPane.showMessageDialog(null, "Please enter unique car no.");
				} else if (!valid.FullNameValidator(fullnmtf.getText())) {
					System.out.println("Incorrect full name");
				} else if (!valid.PhonenoValidator(phonenotf.getText())) {
				} else if (!valid.PasswordValidator(passwordtf.getText())) {

				} else if (!valid.CarNoValidator(carnotf.getText())) {

				} else {
                   objdb.updateinfobyusername(fullnmtf.getText(), phonenotf.getText(), passwordtf.getText(), username);
                   String pack;
   				if (gold.isSelected()) {
   					pack = "Gold";
   				} else if (silver.isSelected()) {
   					pack = "Silver";
   				} else {
   					pack = "Bronze";
   				}
   				objdb.updateCarinfobyusername(carnotf.getText(), pack,"booked", username);
                   UserHomePage uhp=new UserHomePage(username, carnotf.getText());
				}
			}
		});

		////update.repaint();
		
		this.repaint();
		// footer
		setVisible(true);
		setTitle("Update Info Page");
		setBounds(50, 0, 870, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
