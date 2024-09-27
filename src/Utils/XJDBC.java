package Utils;

import java.sql.*;

public class XJDBC {
	private static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Blue_Hotel;user=sa;password=songlong;encrypt=true;trustServerCertificate=true";

	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Failed to load SQL Server JDBC driver", ex);
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connectionUrl);
	}

	public static PreparedStatement getStmt(Connection conn, String sql, Object... args) throws SQLException {
		PreparedStatement pstmt;
		if (sql.trim().startsWith("{")) {
			pstmt = conn.prepareCall(sql);
		} else {
			pstmt = conn.prepareStatement(sql);
		}
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		return pstmt;
	}

	public static void update(String sql, Object... args) {
		try (Connection conn = getConnection(); PreparedStatement stmt = getStmt(conn, sql, args)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet query(String sql, Object... args) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = getStmt(conn, sql, args);
		return stmt.executeQuery();
	}

	public static Object value(String sql, Object... args) {
		try (Connection conn = getConnection();
				PreparedStatement stmt = getStmt(conn, sql, args);
				ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getObject(1); // Chỉ số cột bắt đầu từ 1, không phải 0
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
