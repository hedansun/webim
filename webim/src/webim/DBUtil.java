package webim;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {

	public static Connection conn=null;
	
//	static {
//		try {
//			//Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost/webim?user=root&password=root&useUnicode=true&characterEncoding=8859_1";
//			//String url = "jdbc:mysql://222.73.86.148/webim?user=webim&password=123456&useUnicode=true&characterEncoding=8859_1";
//			conn = (Connection) DriverManager.getConnection(url);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			
//		}
//	}
	
	public static Connection getConnection() {
		try {
			//Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/webim?user=root&password=root&useUnicode=true&characterEncoding=utf-8";
			//String url = "jdbc:mysql://222.73.86.148/webim?user=webim&password=123456&useUnicode=true&characterEncoding=utf-8";
			conn = (Connection) DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return conn;
	}
	
	public static void main(String [] args){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/webim?user=root&password=root&useUnicode=true&characterEncoding=utf-8";
			//String url = "jdbc:mysql://222.73.86.148/webim?user=webim&password=123456&useUnicode=true&characterEncoding=utf-8";
			conn = (Connection) DriverManager.getConnection(url);
			System.out.println("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
