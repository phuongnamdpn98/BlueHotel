package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.NhanVien;
import Utils.XJDBC;

public class NhanVienDAO extends BlueHotelDAO<NhanVien, String>{
	
//	create table NhanVien (
//			maNV varchar(50) not null, 
//			pass varchar(50) not null,
//			hoTen nvarchar(50) not null,
//			gioiTinh bit not null,
//			email varchar(50) not null,
//			sdt varchar(16) not null,
//			vaiTro bit not null
//			primary key(maNV)
//		)
	
	public boolean insert(NhanVien model) {
		try {
			String sql = "INSERT INTO NhanVien (maNV, pass, hoTen, gioiTinh, email, sdt, vaiTro) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        XJDBC.update(sql,
	                model.getMaNV(),
	                model.getPass(),
	                model.getHoTen(),
	                model.getGioiTinh(),
	                model.getEmail(),
	                model.getSoDT(),
	                model.getVaiTro());
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET pass =?, hoTen =?, gioiTinh =?, email =?, sdt =?, vaiTro =? WHERE maNV =?";
        XJDBC.update(sql,
                model.getPass(),
                model.getHoTen(),
                model.getGioiTinh(),
                model.getEmail(),
                model.getSoDT(),
                model.getVaiTro(),
                model.getMaNV());
    }

    public boolean delete(String MaNV) {
    	try {
    		String sql = "DELETE FROM NhanVien WHERE maNV=?";
            XJDBC.update(sql, MaNV);
            return true;
		} catch (Exception e) {
			return false;
		}
        
    }

    public ArrayList<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }

    public NhanVien selectById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        List<NhanVien> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    
    
    public ArrayList<NhanVien> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NhanVien WHERE HoTen LIKE ?";
        return selectBySql(sql, "%" + keyword + "%");
    }

    public ArrayList<NhanVien> selectBySql(String sql, Object... args) {
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString("maNV"));
                    entity.setPass(rs.getString("pass"));
                    entity.setHoTen(rs.getString("hoTen"));
                    entity.setGioiTinh(rs.getInt("gioiTinh"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setSoDT(rs.getString("sdt"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
