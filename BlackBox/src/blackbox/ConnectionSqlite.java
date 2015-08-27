/**
 * @(#)ConnectionSqlite.java
 * 
 * @author Xiao Xiao
 * @version 1.0
*/

package blackbox;

import java.sql.*;
import javax.swing.*;

public class ConnectionSqlite {

	Connection conn = null;

	/**
	 * Connection to sqlite database
	 * 
	 * @return Connection
	 */
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(
					"jdbc:sqlite:src/userDetails.sqlite");
			System.out.println("Connection to database is successful");
			return conn;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
