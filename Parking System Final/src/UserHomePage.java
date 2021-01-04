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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class UserHomePage extends JFrame {
	JPanel titlelogo, userinfo, location, operations;
	TitledBorder infoborder, locborder, opborder;
	JLabel usernamelbl, carnolbl, usernametf, carnotf, lapnolbl, tracknolbl, lapnotf, tracknotf, tagline1, tagline2;
	JButton logout, park, release, seeavail, seemap, logo;
	DBHandler objdb = new DBHandler();
	int lapno, trackno;

	public UserHomePage(String username, String carno) {
		setLayout(null);
		Font f = new Font("Open-Sans", Font.CENTER_BASELINE, 22);
		// creation of userinfo panel in which info of user is printed.
		ImageIcon icnlogo = new ImageIcon("Images/digiparklogo.png");
		logo = new JButton(icnlogo);
		logo.setBounds(30, 40, 300, 115);
		add(logo);

		tagline1 = new JLabel("WELCOME TO DIGIPARK");
		tagline1.setFont(f);
		tagline1.setBounds(450, 60, 300, 30);
		add(tagline1);

		tagline2 = new JLabel("WHERE WE MAKE PARKING DIGITALISE");
		tagline2.setFont(f);
		tagline2.setBounds(360, 110, 440, 30);
		add(tagline2);
		LineBorder borderinfo = new LineBorder(Color.DARK_GRAY, 3, true);
		infoborder = new TitledBorder(borderinfo, " USER INFO ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.blue);
		userinfo = new JPanel();
		userinfo.setLayout(null);
		userinfo.setBounds(30, 200, 300, 140);
		userinfo.setBorder(infoborder);
		add(userinfo);

		usernamelbl = new JLabel("User Name");
		Font f1 = new Font("Calibri", Font.PLAIN, 18);
		usernamelbl.setBounds(20, 20, 110, 40);
		usernamelbl.setFont(f1);
		userinfo.add(usernamelbl);

		carnolbl = new JLabel("Car No.");
		carnolbl.setBounds(20, 80, 110, 40);
		carnolbl.setFont(f1);
		userinfo.add(carnolbl);

		usernametf = new JLabel();
		usernametf.setText(username);

		usernametf.setBounds(160, 20, 120, 40);
		usernametf.setFont(f1);
		userinfo.add(usernametf);

		carnotf = new JLabel();
		carnotf.setBounds(160, 80, 120, 40);
		carnotf.setFont(f1);
		carnotf.setText(carno);
		userinfo.add(carnotf);

		// creation of loc panel along with locborder
		LineBorder border = new LineBorder(Color.GREEN, 3, true);
		locborder = new TitledBorder(border, " LOCATION ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.BLUE);
		location = new JPanel();
		location.setLayout(null);
		location.setBounds(30, 400, 300, 140);
		location.setBorder(locborder);
		add(location);

		// content inside loc panel
		lapnolbl = new JLabel("Lap no.");
		lapnolbl.setBounds(20, 20, 110, 40);
		lapnolbl.setFont(f1);
		location.add(lapnolbl);

		tracknolbl = new JLabel("Track no.");
		tracknolbl.setBounds(20, 80, 110, 40);
		tracknolbl.setFont(f1);
		location.add(tracknolbl);

		lapnotf = new JLabel("lap");
		lapnotf.setBounds(160, 20, 120, 40);
		lapnotf.setFont(f1);
		location.add(lapnotf);

		tracknotf = new JLabel();
		tracknotf.setBounds(160, 80, 120, 40);
		tracknotf.setFont(f1);
		location.add(tracknotf);

		if (objdb.verifyneworexistinguser(carno)) {
			lapno = objdb.getLapOfLoginUser(carno);
			trackno = objdb.getTrackOfLoginUser(carno);
		} else {
			lapno = objdb.getLapnoOfNewUser();
			trackno = objdb.getTracknoOfNewUser();
			objdb.UpdateLocStatusofNewUser(carno, lapno, trackno);
		}

		lapnotf.setText(String.valueOf(lapno));
		tracknotf.setText(String.valueOf(trackno));

		// buttons within operation
		LineBorder borderop = new LineBorder(Color.red, 3, true);
		opborder = new TitledBorder(borderop, "OPERATIONS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.BLUE);
		operations = new JPanel();
		operations.setLayout(null);
		operations.setBounds(430, 200, 270, 340);
		operations.setBorder(opborder);
		add(operations);
		ImageIcon icnpark = new ImageIcon("Images/park.jpg");
		park = new JButton(icnpark);
		park.setBounds(100, 50, 94, 40);
		park.setFont(f1);
		operations.add(park);

		park.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Park park = new Park(username, carno);
				dispose();
			}
		});
		ImageIcon icnseeavail = new ImageIcon("Images/seeavail.jpg");
		seeavail = new JButton(icnseeavail);
		seeavail.setBounds(70, 120, 140, 40);
		seeavail.setFont(f1);
		operations.add(seeavail);

		seeavail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeeAvail seeavail = new SeeAvail(username, carno);
			}
		});

		ImageIcon icnseemap = new ImageIcon("Images/seemap.jpg");
		seemap = new JButton(icnseemap);
		seemap.setBounds(80, 190, 129, 40);
		seemap.setFont(f1);
		operations.add(seemap);

		seemap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeeMap map = new SeeMap(lapno, trackno);
			}
		});
		ImageIcon icnrelease = new ImageIcon("Images/release.jpg");
		release = new JButton(icnrelease);
		release.setBounds(80, 260, 124, 40);
		operations.add(release);
		release.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = JOptionPane.showConfirmDialog(null, "Are you sure you want to release?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (value == JOptionPane.YES_OPTION) {
					Release rls = new Release(username, carno);
					dispose();
				} else {

				}
			}
		});
		String carstatus;
		carstatus = objdb.getCarStatusFromCarno(carno);
		if (carstatus.equals("booked")) {
			seemap.setEnabled(false);
			release.setEnabled(false);
		} else {
			park.setEnabled(false);
		}

		ImageIcon loginasicon = new ImageIcon("Images/loginasbutton.jpg");
		logout = new JButton(loginasicon);
		logout.setFont(f1);
		logout.setBounds(340, 600, 133, 40);
		add(logout);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		// footer
		setVisible(true);
		setTitle("User Home Page");
		setBounds(250, 0, 820, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
