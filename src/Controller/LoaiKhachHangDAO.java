package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.LoaiKhachHang;
import Utils.XJDBC;

public class LoaiKhachHangDAO {
	public ArrayList<LoaiKhachHang> selectAll() {
		String sql = "SELECT * FROM PhanLoaiKH;";
		return this.selectBySql(sql);
	}

	public ArrayList<LoaiKhachHang> selectBySql(String sql, Object... args) {
		ArrayList<LoaiKhachHang> list = new ArrayList<>();
		try (Connection conn = XJDBC.getConnection();
				PreparedStatement stmt = XJDBC.getStmt(conn, sql, args);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				LoaiKhachHang entity = new LoaiKhachHang();
				entity.setMaLoaiKH(rs.getInt("maLK"));
				entity.setTenLoaiKH(rs.getString("tenLK"));
				list.add(entity);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}
}
