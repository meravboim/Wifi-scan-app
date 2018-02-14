package Files;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.xml.bind.v2.runtime.reflect.Accessor.GetterSetterReflection;

import GUI.Connect;
import object.Cordinate;
import object.Database;
import object.Scan;
import object.WifiData;

import java.sql.Statement;

public class ReadFromSQL {
/**
 * this class read data from sql and inset them into the database
 */
	private  String _ip = "5.29.193.52";
	private  String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	private  String _user = "oop1";
	private   String _password = "Lambda1();";
	private  Connection _con = null;

	/**
	 * @return the _ip
	 */
	public  String get_ip() {
		return _ip;
	}

	/**
	 * @param _ip the _ip to set
	 */
	public  void set_ip(String _ip) {
		this._ip = _ip;
	}

	/**
	 * @return the _url
	 */
	public  String get_url() {
		return _url;
	}

	/**
	 * @param _url the _url to set
	 */
	public  void set_url(String _url) {
		this._url = _url;
	}

	/**
	 * @return the _user
	 */
	public  String get_user() {
		return _user;
	}

	/**
	 * @param _user the _user to set
	 */
	public  void set_user(String _user) {
		this._user = _user;
	}

	/**
	 * @return the _password
	 */
	public  String get_password() {
		return _password;
	}

	/**
	 * @param _password the _password to set
	 */
	public  void set_password(String _password) {
		this._password = _password;
	}

	/**
	 * @return the _con
	 */
	public  Connection get_con() {
		return _con;
	}

	/**
	 * @param _con the _con to set
	 */
	public  void set_con(Connection _con) {
		this._con = _con;
	}

	public  void main(String[] args) {
		ArrayList<Scan> data = test_ex4_db();
		//insert_table(max_id);
	}
	//	public static int test_101() {
	//		Statement st = null;
	//		ResultSet rs = null;
	//		int max_id = -1;
	//		String ip = "localhost";
	//		// String ip = "192.168.1.18";
	//
	//		try {     
	//			_con = DriverManager.getConnection(_url, _user, _password);
	//			st = _con.createStatement();
	//			rs = st.executeQuery("SELECT VERSION()");
	//			if (rs.next()) {
	//				System.out.println(rs.getString(1));
	//			}
	//
	//			PreparedStatement pst = _con.prepareStatement("SELECT * FROM test101");
	//			rs = pst.executeQuery();
	//
	//			while (rs.next()) {
	//				int id = rs.getInt(1);
	//				System.out.println(id);
	//				if(id>max_id) {max_id=id;}
	//				System.out.print(id);
	//				System.out.print(": ");
	//				System.out.print(rs.getString(2));
	//				System.out.print(" (");
	//				double lat = rs.getDouble(3);
	//				System.out.print(lat);
	//				System.out.print(", ");
	//				double lon = rs.getDouble(4);
	//				System.out.print(lon);
	//				System.out.println(") ");
	//			}
	//		} catch (SQLException ex) {
	//			Logger lgr = Logger.getLogger(MySQL_101.class.getName());
	//			lgr.log(Level.SEVERE, ex.getMessage(), ex);
	//		} finally {
	//			try {
	//				if (rs != null) {rs.close();}
	//				if (st != null) { st.close(); }
	//				if (_con != null) { _con.close();  }
	//			} catch (SQLException ex) {
	//
	//				Logger lgr = Logger.getLogger(MySQL_101.class.getName());
	//				lgr.log(Level.WARNING, ex.getMessage(), ex);
	//			}
	//		}
	//		return max_id;
	//	}
/*
 * this class read the table
 */
	public  ArrayList<Scan> test_ex4_db() {
		Statement st = null;
		ResultSet rs = null;
		int max_id = -1;
		ArrayList<Scan> data = new ArrayList<>();

		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");
			rs = pst.executeQuery();
			while (rs.next()) {
				int size = rs.getInt(7);
				int len = 2*size+7;
				Cordinate core = new Cordinate(Double.parseDouble(rs.getString(4)), Double.parseDouble(rs.getString(5)), Double.parseDouble(rs.getString(6)));
				ArrayList<WifiData> wifi = new ArrayList<>();
				for (int j = 8; j < len; j=j+2) {
					WifiData k = new WifiData("",rs.getString(j),"",rs.getString(j+1));
					wifi.add(k);
				}
				Scan temp = new Scan (rs.getString(2),rs.getString(3),core, wifi);
				data.add(temp);
				//	System.out.println(temp.toString());
			}
			System.out.println(data.size());
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(ReadFromSQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(ReadFromSQL.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		ReadFromSQL k = new ReadFromSQL();
		k.set_con(this._con);
		k.set_ip(this._ip);
		k.set_password(this._password);
		k.set_url(this._url);
		k.set_user(this._user);
		Connect c = new Connect();
		c.checkForChange(k);
		return data;
	}
	//	public static void insert_table(int max_id) {
	//		Statement st = null;
	//		ResultSet rs = null;
	//		//String ip = "localhost";
	//		// String ip = "192.168.1.18";
	//
	//		try {     
	//			_con = DriverManager.getConnection(_url, _user, _password);
	//			st = _con.createStatement();
	//			Date now = null;
	//			for(int i=0;i<5;i++) {
	//				int curr_id = 1+i+max_id;
	//				String str = "INSERT INTO test101 (ID,NAME,pos_lat,pos_lon, time, ap1, ap2, ap3) "
	//						+ "VALUES ("+curr_id+",'test_name"+curr_id+"',"+(32+curr_id)+",35.01,"+now+",'mac1"+curr_id+"', 'mac2', 'mac3')";
	//				PreparedStatement pst = _con.prepareStatement(str);
	//				pst.execute();
	//			}
	//		} catch (SQLException ex) {
	//			Logger lgr = Logger.getLogger(ReadFromSQL.class.getName());
	//			lgr.log(Level.SEVERE, ex.getMessage(), ex);
	//		} finally {
	//			try {
	//				if (rs != null) {rs.close();}
	//				if (st != null) { st.close(); }
	//				if (_con != null) { _con.close();  }
	//			} catch (SQLException ex) {
	//
	//				Logger lgr = Logger.getLogger(ReadFromSQL.class.getName());
	//				lgr.log(Level.WARNING, ex.getMessage(), ex);
	//			}
	//		}
	//	}	
	//	public static void insert_table2(int max_id, ArrayList<Scan> ws) {
	//		Statement st = null;
	//		ResultSet rs = null;
	//
	//		try {     
	//			_con = DriverManager.getConnection(_url, _user, _password);
	//			st = _con.createStatement();
	//
	//			int size = ws.size();
	//			for(int i=0;i<size;i++) {
	//				int curr_id = 1+i+max_id;
	//				Scan c = ws.get(i);
	//				String sql = creat_sql(c, curr_id);
	//				PreparedStatement pst = _con.prepareStatement(sql);
	//				System.out.println(sql);
	//				pst.execute();
	//			}
	//		} catch (SQLException ex) {
	//			Logger lgr = Logger.getLogger(ReadFromSQL.class.getName());
	//			lgr.log(Level.SEVERE, ex.getMessage(), ex);
	//		} finally {
	//			try {
	//				if (rs != null) {rs.close();}
	//				if (st != null) { st.close(); }
	//				if (_con != null) { _con.close();  }
	//			} catch (SQLException ex) {
	//
	//				Logger lgr = Logger.getLogger(ReadFromSQL.class.getName());
	//				lgr.log(Level.WARNING, ex.getMessage(), ex);
	//			}
	//		}
	//	}
	//	private static String creat_sql(Scan w, int id) {
	//		String ans = "INSERT INTO ex4_db (ID,time, device,lat,lon,alt, number_of_ap";
	//		String str1 = "", str2="";
	//		Cordinate pos = w.getCore();
	//		int n = w.getWifiNetWork();
	//		String in = " VALUES ("+id+",'"+w.getTime()+"','"+w.getId()+"',"+pos.getLat()+","+pos.getLon()+","+pos.getAlt()+","+n; 
	//		for(int i=0;i<n;i++) {
	//			str1+=",mac"+i+",rssi"+i;
	//			WifiData a = w.getWifi().get(i);
	//			str2+=",'"+a.getMAC()+"',"+a.getSignal();
	//		}
	//		ans +=str1+")"+in+str2+")";    	
	//		return ans;
	//	}
}
