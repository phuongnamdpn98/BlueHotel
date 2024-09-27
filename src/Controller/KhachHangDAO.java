package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.KhachHang;
import Utils.XJDBC;

public class KhachHangDAO extends BlueHotelDAO<KhachHang, String>{
	public boolean insert(KhachHang model) {
        try {			
        	String sql = "INSERT INTO KhachHang (hoTen, ngaySinh, gioiTinh, email, sdt, cccd, maLK) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	XJDBC.update(sql,
        			model.getHoTen(),
        			model.getNgaySinh(),
        			model.getGioiTinh(),
        			model.getEmail(),
        			model.getSoDT(),
        			model.getCccd(),
        			model.getMaLoaiKH());
        	return true;
		} catch (Exception e) {
			return false;
		}
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET hoTen =?, ngaySinh =?, gioiTinh =?, email =?, sdt =?, cccd =?, maLK =? WHERE maKH =?";
        XJDBC.update(sql,
                model.getHoTen(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getEmail(),
                model.getSoDT(),
                model.getCccd(),
                model.getMaLoaiKH(),
                model.getMaKH());
    }

    @Override
	public boolean delete(String maKH) {
    	try {
   		 String sql = "DELETE FROM KhachHang WHERE maKH=?";
   	     XJDBC.update(sql, maKH);
   	     return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

    public ArrayList<KhachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return this.selectBySql(sql);
    }

    public KhachHang selectById(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = this.selectBySql(sql, maKH);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public ArrayList<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE hoTen LIKE ?";
        return selectBySql(sql, "%" + keyword + "%");
    }

    public ArrayList<KhachHang> selectBySql(String sql, Object... args) {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                	KhachHang entity = new KhachHang();
                    entity.setMaKH(rs.getInt("maKH"));
                    entity.setHoTen(rs.getString("hoTen"));
                    entity.setNgaySinh(rs.getDate("ngaySinh"));
                    entity.setGioiTinh(rs.getBoolean("gioiTinh"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setSoDT(rs.getString("sdt"));
                    entity.setCccd(rs.getString("cccd"));
                    entity.setMaLoaiKH(rs.getInt("maLK"));
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
