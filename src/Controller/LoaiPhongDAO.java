package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.LoaiPhong;
import Utils.XJDBC;

public class LoaiPhongDAO {
	public ArrayList<LoaiPhong> selectAll() {
		String sql = "SELECT * FROM LoaiPhong;";
		return this.selectBySql(sql);
	}

	public ArrayList<LoaiPhong> selectBySql(String sql, Object... args) {
		ArrayList<LoaiPhong> list = new ArrayList<>();
		try (Connection conn = XJDBC.getConnection();
				PreparedStatement stmt = XJDBC.getStmt(conn, sql, args);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				LoaiPhong entity = new LoaiPhong();
				entity.setMaLoaiPhong(rs.getInt("maLP"));
				entity.setTenLoaiPhong(rs.getString("tenLP"));
				entity.setGia(rs.getInt("gia"));
				list.add(entity);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}
}

