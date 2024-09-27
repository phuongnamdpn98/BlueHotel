package Controller;

import java.sql.*;
import java.util.ArrayList;

import Model.Room;
import Utils.XJDBC;

public class PhongDAO {
	public ArrayList<Room> selectAll() {
		String sql = "SELECT * FROM Phong;";
		return this.selectBySql(sql);
	}

	public void update(String maPhong, int status) {
		String sql = "UPDATE Phong SET trangthai=? WHERE maPhong=?";
		XJDBC.update(sql, status ,maPhong);
	}

	public ArrayList<Room> selectBySql(String sql, Object... args) {
		ArrayList<Room> list = new ArrayList<>();
		try (Connection conn = XJDBC.getConnection();
				PreparedStatement stmt = XJDBC.getStmt(conn, sql, args);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Room entity = new Room();
				entity.setMaPhong(rs.getString("maPhong"));
				entity.setTenPhong(rs.getString("tenPhong"));
				entity.setTrangThai(rs.getInt("trangthai"));
				entity.setHinh(rs.getString("hinh"));
				entity.setMaLP(rs.getInt("maLP"));
				list.add(entity);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}
}
