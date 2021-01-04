import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Payment extends JFrame {
	JPanel carinfo, payment;
	TitledBorder carinfoborder, paymentborder;
	JLabel carnolbl, dop, dor, tor, selectPack, carnotf, doptf, tortf, dortf, fixedlbl, expirylbl, finelbl;
	JLabel ttlbilllbl, fixedtf, expirytf, finetf, ttlbilltf, message, message2;
	JButton calpayment, pay, bill, exit;
	JRadioButton Gold, Silver, Bronze;
	DBHandler objdb = new DBHandler();

	public Payment(String username, String carno) {
		LinkedList<Packinfo> lst = new LinkedList<Packinfo>();
		Packinfo pi1 = new Packinfo("Gold", 80, 5);
		Packinfo pi2 = new Packinfo("Silver", 50, 3);
		Packinfo pi3 = new Packinfo("Bronze", 20, 1);
		lst.add(pi1);
		lst.add(pi2);
		lst.add(pi3);

		setLayout(null);
		Font f = new Font("Calibri", Font.PLAIN, 20);
		// border of carinfo panel and its detail
		LineBorder border = new LineBorder(Color.red, 4, true);
		carinfoborder = new TitledBorder(border, "CARINFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.BLUE);
		carinfo = new JPanel();
		carinfo.setLayout(null);
		carinfo.setBounds(30, 40, 520, 300);
		carinfo.setBorder(carinfoborder);
		add(carinfo);

		// content inside the carinfo panel
		carnolbl = new JLabel("Car No");
		carnolbl.setBounds(30, 20, 100, 30);
		carnolbl.setFont(f);
		carinfo.add(carnolbl);

		dop = new JLabel("DOP");
		dop.setBounds(30, 80, 100, 30);
		dop.setFont(f);
		carinfo.add(dop);

		dor = new JLabel("DOR");
		dor.setBounds(30, 140, 100, 30);
		dor.setFont(f);
		carinfo.add(dor);

		tor = new JLabel("TOR");
		tor.setBounds(30, 200, 100, 30);
		tor.setFont(f);
		carinfo.add(tor);

		selectPack = new JLabel("Pack Selected");
		selectPack.setBounds(30, 260, 150, 30);
		selectPack.setFont(f);
		carinfo.add(selectPack);

		// labels
		carnotf = new JLabel(carno);
		carnotf.setBounds(200, 20, 150, 30);
		carnotf.setFont(f);
		carinfo.add(carnotf);

		doptf = new JLabel();
		doptf.setBounds(200, 80, 100, 30);
		doptf.setFont(f);
		doptf.setText(objdb.getDOPbyCarNo(carno));
		carinfo.add(doptf);

		dortf = new JLabel();
		dortf.setBounds(200, 140, 100, 30);
		dortf.setFont(f);
		carinfo.add(dortf);
		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		String strDate = formatter.format(dt);
		dortf.setText(strDate);
		carinfo.add(dortf);

		tortf = new JLabel();
		tortf.setBounds(200, 200, 100, 30);
		tortf.setFont(f);
		Calendar cal = Calendar.getInstance();
		String time = cal.get(Calendar.HOUR_OF_DAY) + " : " + cal.get(Calendar.MINUTE);
		;
		tortf.setText(time);
		carinfo.add(tortf);

		paymentborder = new TitledBorder(border, "PAYMENT", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), Color.BLUE);
		payment = new JPanel();
		payment.setLayout(null);
		payment.setBounds(580, 40, 400, 300);
		payment.setBorder(paymentborder);
		add(payment);

		fixedlbl = new JLabel("Fixed Charge");
		fixedlbl.setBounds(20, 20, 130, 30);
		fixedlbl.setFont(f);
		payment.add(fixedlbl);

		expirylbl = new JLabel("Expiry Charge");
		expirylbl.setBounds(20, 80, 150, 30);
		expirylbl.setFont(f);
		payment.add(expirylbl);

		finelbl = new JLabel("Fine Charge");
		finelbl.setBounds(20, 140, 150, 30);
		finelbl.setFont(f);
		payment.add(finelbl);

		ttlbilllbl = new JLabel("Total Charge");
		ttlbilllbl.setBounds(20, 200, 150, 30);
		ttlbilllbl.setFont(f);
		payment.add(ttlbilllbl);

		Gold = new JRadioButton("Gold");
		Gold.setFont(f);
		Gold.setBounds(200, 260, 80, 30);
		Gold.setEnabled(false);
		carinfo.add(Gold);

		Silver = new JRadioButton("Silver");
		Silver.setFont(f);
		Silver.setBounds(300, 260, 80, 30);
		Silver.setEnabled(false);
		carinfo.add(Silver);

		Bronze = new JRadioButton("Bronze");
		Bronze.setFont(f);
		Bronze.setBounds(400, 260, 100, 30);
		Bronze.setEnabled(false);
		carinfo.add(Bronze);
		String packname = objdb.getPackFromCarno(carno);
		if (packname.equals("Gold")) {
			Gold.setSelected(true);
		} else if (packname.equals("Silver")) {
			Silver.setSelected(true);
		} else {
			Bronze.setSelected(true);
		}
		
		
		ImageIcon calpaymenticn=new ImageIcon("Images/calculatebill.jpg");
		calpayment = new JButton(calpaymenticn);
		calpayment.setFont(f);
		calpayment.setBounds(200, 380, 200, 40);
		add(calpayment);

		calpayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon payicn=new ImageIcon("Images/pay.jpg");
				pay = new JButton(payicn);
				pay.setBounds(640, 380, 80, 40);
				add(pay);

				int price = 0;
				int validity = 0;

				for (Packinfo packinfo : lst) {
					if (packinfo.getPack().equals(packname)) {
						price = packinfo.getPrice();
						validity = packinfo.getDays();
					}
				}
				fixedtf = new JLabel();
				fixedtf.setFont(f);
				fixedtf.setBounds(220, 20, 150, 30);
				fixedtf.setText(String.valueOf(price));
				payment.add(fixedtf);

				expirytf = new JLabel();
				expirytf.setFont(f);
				expirytf.setBounds(220, 80, 150, 30);
				payment.add(expirytf);

				finetf = new JLabel();
				finetf.setFont(f);
				finetf.setBounds(220, 140, 150, 30);
				payment.add(finetf);

				ttlbilltf = new JLabel();
				ttlbilltf.setFont(f);
				ttlbilltf.setBounds(220, 200, 150, 30);
				payment.add(ttlbilltf);

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
				Date dop = null;
				try {
					dop = formatter.parse(doptf.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long diff = dt.getTime() - dop.getTime();
				long standingdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				System.out.println(standingdays);
				int extracharge = 0;
				if (standingdays <= validity) {
					expirytf.setText(String.valueOf(extracharge));
				} else {
					int fullday = (int) (standingdays - (validity + 1));
					System.out.println(fullday);
					extracharge = extracharge + fullday * 24 * 4;
					System.out.println(extracharge);
					if (cal.get(Calendar.MINUTE) > 45) {
						extracharge = extracharge + 4;
					}
					extracharge = extracharge + cal.get(Calendar.HOUR_OF_DAY) * 4;
					System.out.println(extracharge);
					expirytf.setText(String.valueOf(extracharge));
				}
				int adminfine = objdb.getFinebyCarno(carno);
				finetf.setText(String.valueOf(adminfine));

				int totalbill = price + extracharge + adminfine;
				ttlbilltf.setText(String.valueOf(totalbill));

				objdb.UpdateinTblPayment(carno, dortf.getText(), tortf.getText(), price, extracharge);
				pay.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						message = new JLabel("Thank you for using the digipark application.");
						message.setFont(f);
						message.setBounds(280, 460, 400, 40);
						add(message);
						message = new JLabel("Come Again");
						message.setFont(f);
						message.setBounds(380, 520, 400, 40);
						add(message);
						ImageIcon exiticn=new ImageIcon("Images/exit.jpg");
						exit = new JButton(exiticn);
						exit.setBounds(400, 600, 89, 40);
						add(exit);
						exit.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								objdb.deletetblpaymentffromcarno(carno);
								objdb.tbllapffornewuser("avail", carno);
								objdb.UpdateCarStatus("released", carno);
								System.exit(0);

							}
						});
					}
				});
			}
		});

		this.repaint();
		// footer

		setVisible(true);
		setTitle("Payment Page");
		setBounds(50, 0, 1020, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
