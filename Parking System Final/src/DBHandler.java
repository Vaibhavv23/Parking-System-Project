import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import oracle.jdbc.pool.OracleDataSource;

public class DBHandler {
	public Connection getDBCon() {
		Connection con = null;
		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con = ods.getConnection("suyash", "icsd");
			System.out.println("Connection established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void Insertintotbl(int userid, String username, String fullname, String phoneno, String password) {
		Connection con = getDBCon();
		int carid = 0;
		String blackliststatus = "no";
		try {
			PreparedStatement stmt = con.prepareStatement("insert into tbluserf values(?,?,?,?,?,?,?)");
			stmt.setInt(1, userid);
			stmt.setString(2, username);
			stmt.setString(3, fullname);
			stmt.setString(4, phoneno);
			stmt.setString(5, password);
			stmt.setInt(6, carid);
			stmt.setString(7, blackliststatus);
			stmt.executeUpdate();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			System.exit(0);
		}

	}

	public int getMaxID(String strTblname, String strcolname) {
		int USERID = 1;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select max(" + strcolname + ") as USERID from " + strTblname);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				USERID = rset.getInt("USERID");
			} else {
				USERID = 0;
			}
			USERID++;
			System.out.println("User ID generated is " + USERID);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return USERID;
	}

	public void deletetbluserbyusername(String username) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("delete from tbluserf where username=?");
			stmt.setString(1, username);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFullNamebyUserName(String username) {
		String fullname = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbluserf where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				fullname = rset.getString("fullname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullname;
	}

	public String getPhonenobyUserName(String username) {
		String phoneno = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbluserf where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				phoneno = rset.getString("phoneno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phoneno;
	}

	public String getuserPasswordbyUserName(String username) {
		String userpassword = "";
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbluserf where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				userpassword = rset.getString("userpassword");
			} else {
				JOptionPane.showMessageDialog(null, "no such user exists..");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userpassword;
	}

	public void updateinfobyusername(String fullname, String phoneno, String password, String username) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con
					.prepareStatement("update tbluserf set fullname=?,phoneno=?,userpassword=? where username=?");
			stmt.setString(1, fullname);
			stmt.setString(2, phoneno);
			stmt.setString(3, password);
			stmt.setString(4, username);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean uniqueusername(String username) {
		Connection con = getDBCon();
		String uname;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbluserf");
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				uname = rset.getString("username");
				if (username.equals(uname)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void updateCidInTbluser(int carid, String username) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("update tbluserf set carid=? where username=?");
			stmt.setInt(1, carid);
			stmt.setString(2, username);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertInCarinfo(String username, int carid, String carno, String pack) {
		Connection con = getDBCon();
		String carstatus = "booked";
		try {
			PreparedStatement stmt = con.prepareStatement("insert into carinfo values(?,?,?,?,?)");
			stmt.setString(1, username);
			stmt.setInt(2, carid);
			stmt.setString(3, carno);
			stmt.setString(4, pack);
			stmt.setString(5, carstatus);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCarinfobyusername(String carno, String pack, String carstatus, String username) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con
					.prepareStatement("update carinfo set carno=?,pack=?,carstatus=? where username=?");
			stmt.setString(1, carno);
			stmt.setString(2, pack);
			stmt.setString(3, carstatus);
			stmt.setString(4, username);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean uniqueCarnoforupdation(String carno, String username) {
		Connection con = getDBCon();
		String carn;
		String usern;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo");
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				carn = rset.getString("carno");
				usern = rset.getString("username");
				if (carno.equals(carn)) {
					if (usern.equals(username)) {
						return false;
					} else {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void deletecarinfobyusername(String username) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("delete from carinfo where username=?");
			stmt.setString(1, username);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean uniqueCarno(String carno) {
		Connection con = getDBCon();
		String carn;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo");
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				carn = rset.getString("carno");
				if (carno.equals(carn)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyneworexistinguser(String carno) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				return true;
			}
		} catch (SQLException e) {

		}
		return false;
	}

	public String getCarnobyusername(String username) {
		String carno = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				carno = rset.getString("carno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carno;
	}

	public String getCarStatusFromCarno(String carno) {
		Connection con = getDBCon();
		String carstatus = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				carstatus = rset.getString("carstatus");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carstatus;
	}

	public int getLapOfLoginUser(String carno) {
		int lapno = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				lapno = rset.getInt("lapno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lapno;
	}

	public int getTrackOfLoginUser(String carno) {
		int trackno = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				trackno = rset.getInt("trackno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trackno;
	}

	public int getLapnoOfNewUser() {
		int lapno = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf where locstatus=?");
			stmt.setString(1, "avail");
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				lapno = rset.getInt("lapno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lapno;
	}

	public int getTracknoOfNewUser() {
		int trackno = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf where locstatus=?");
			stmt.setString(1, "avail");
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				trackno = rset.getInt("trackno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trackno;
	}

	public void UpdateLocStatusofNewUser(String carno, int lapno, int trackno) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con
					.prepareStatement("update tbllapf set carno=? , locstatus=? where lapno=? and trackno=?");
			stmt.setString(1, carno);
			stmt.setString(2, "reserved");
			stmt.setInt(3, lapno);
			stmt.setInt(4, trackno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tbllapffornewuser(String locstatus, String carno) {
		String carn = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("update tbllapf set carno=?,locstatus=? where carno=?");
			stmt.setString(1, carn);
			stmt.setString(2, locstatus);
			stmt.setString(3, carno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UpdateCarStatus(String carstatus, String carno) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("update carinfo set carstatus=? where carno=?");
			stmt.setString(1, carstatus);
			stmt.setString(2, carno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPackFromCarno(String carno) {
		String pack = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				pack = rset.getString("pack");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pack;
	}

	public String getPackFromUserName(String username) {
		String pack = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				pack = rset.getString("pack");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pack;
	}

	public void insertintblPayment(String carno, String pack, String dop) {
		String dor = null;
		String tor = null;
		int fixedch = 0;
		int extrach = 0;
		int finech = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("insert into tblpaymentf values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, carno);
			stmt.setString(2, dop);
			stmt.setString(3, dor);
			stmt.setString(4, tor);
			stmt.setString(5, pack);
			stmt.setInt(6, fixedch);
			stmt.setInt(7, extrach);
			stmt.setInt(8, finech);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateFine(int fine, String carno) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("update tblpaymentf set finech=? where carno=?");
			stmt.setInt(1, fine);
			stmt.setString(2, carno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UpdateinTblPayment(String carno, String dor, String tor, int fixedch, int extrach) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con
					.prepareStatement("update tblpaymentf set dor=?,tor=?,fixedch=?,extrach=? where carno=?");
			stmt.setString(1, dor);
			stmt.setString(2, tor);
			stmt.setInt(3, fixedch);
			stmt.setInt(4, extrach);
			stmt.setString(5, carno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedList<Location> selectlapTable() {
		LinkedList<Location> lst = new LinkedList<>();
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf");
			ResultSet rset = stmt.executeQuery();
			String carno, locstatus;
			int lapno, trackno;
			Location obj;
			while (rset.next()) {
				carno = rset.getString("carno");
				lapno = rset.getInt("lapno");
				trackno = rset.getInt("trackno");
				locstatus = rset.getString("locstatus");
				obj = new Location(carno, locstatus, lapno, trackno);
				lst.add(obj);
			}
			rset.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}

	public int getAvailableCount() {
		String locStatus = "avail";
		int count = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbllapf where locstatus=?");
			stmt.setString(1, locStatus);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				count++;
			}
			rset.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public boolean getBlacklistStatus(String username) {
		Connection con = getDBCon();
		String blacklist;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbluserf where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				blacklist = rset.getString("blackliststatus");
				if (blacklist.equals("yes")) {
					return true;
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void updateblackliststatusfromusername(String blackliststatus, String username) {
		Connection con = getDBCon();

		try {
			PreparedStatement stmt = con.prepareStatement("update tbluserf set blackliststatus=? where username=?");
			stmt.setString(1, blackliststatus);
			stmt.setString(2, username);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean IsvalidUser(String username, String password) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbluserf where username=? and userpassword=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			System.exit(0);
		}

		return true;
	}

	public String getDOPbyCarNo(String carno) {
		String dop = null;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tblpaymentf where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				dop = rset.getString("dop");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dop;
	}

	public int getFinebyCarno(String carno) {
		int fine = 0;
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tblpaymentf where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				fine = rset.getInt("finech");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fine;
	}

	public void deletetblpaymentffromcarno(String carno) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("delete from tblpaymentf where carno=?");
			stmt.setString(1, carno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	///// ADMIN SECTION
	public boolean validAdmin(String adminname, String adminpassword) {
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from tbladminf where adminname=? and adminpwd=?");
			stmt.setString(1, adminname);
			stmt.setString(2, adminpassword);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
