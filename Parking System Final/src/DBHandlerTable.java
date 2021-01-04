import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.pool.OracleDataSource;

public class DBHandlerTable {
	public Connection getDBCon() {
		Connection con=null;
		try {
			OracleDataSource ods=new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con=ods.getConnection("suyash","icsd");
			System.out.println("Connection established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public JTable getParkedTable(String carno) {
		String[] columnNames = { "CarID", "LapNo.", "TrackNo.", "CarStatus" };
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select carid,carstatus,lapno,trackno from carinfo,tbllapf where carinfo.carno=tbllapf.carno and carinfo.carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			JTable jtabell = new JTable(model);
			while (rset.next()) {
				Vector row = new Vector();

				int CarId = rset.getInt("carid");
				int lapno = rset.getInt("lapno");
				int trackno = rset.getInt("trackno");
				String CarStatus = rset.getString("carstatus");

				row.add(CarId);
				row.add(lapno);
				row.add(trackno);
				row.add(CarStatus);
				model.addRow(row);
			}
			return jtabell;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
  public JTable getdisplayTable() {
	  String[] columnNames = { "Username","Carno","CarStatus","LapNo","TrackNo","BlacklistStatus" };
		Connection con = getDBCon();
		try {
			PreparedStatement stmt = con.prepareStatement("select username,carinfo.carno, from carinfo,tbllapf where carinfo.carno=tbllapf.carno and carinfo.carno=?");
			ResultSet rset = stmt.executeQuery();
			DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			JTable jtabell = new JTable(model);
			while (rset.next()) {
				Vector row = new Vector();

				int CarId = rset.getInt("carid");
				int lapno = rset.getInt("lapno");
				int trackno = rset.getInt("trackno");
				String CarStatus = rset.getString("carstatus");

				row.add(CarId);
				row.add(lapno);
				row.add(trackno);
				row.add(CarStatus);
				model.addRow(row);
			}
			return jtabell;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  
  }
  public JTable getUserInfoTable() {
	  String[] columnNames = { "Username","Carno","Phone no.","Car Status","BlacklistStatus", "Fine Imposed" };
		Connection con = getDBCon();
	  try {
		PreparedStatement stmt=con.prepareStatement("select tbluserf.username,carinfo.carno,phoneno,carstatus,blackliststatus,Finech from tbluserf,carinfo,tblpaymentf where tbluserf.username=carinfo.username and carinfo.carno=tblpaymentf.carno");
	    ResultSet rset=stmt.executeQuery();
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		JTable jtabell = new JTable(model);
		while (rset.next()) {
			Vector row = new Vector();
            String username=rset.getString("username");
			String carno=rset.getString("carno");
			String phoneno=rset.getString("phoneno");
			String carstatus=rset.getString("carstatus");
			String blackliststatus=rset.getString("blackliststatus");
			int finech=rset.getInt("finech");
			
			row.add(username);
			row.add(carno);
			row.add(phoneno);
			row.add(carstatus);
			row.add(blackliststatus);
			row.add(finech);
			model.addRow(row);
		}
		return jtabell;

	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }
}
