package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import Model.HoaDon;
import Utils.XJDBC;

public class HoaDonDAO extends BlueHotelDAO<HoaDon, String> {

	public boolean insert(HoaDon model) {
		try {
			String sql = "INSERT INTO HoaDon (maNV, maPTP, ngayDi) VALUES ( ?, ?, ?)";
	        XJDBC.update(sql,
	                model.getMaNV(),
	                model.getMaPhieuThuePhong(),
	                model.getNgayDi());
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
    }

    public void update(HoaDon model) {
        String sql = "UPDATE HoaDon SET maNV =?, maPTP =?, ngayDi =? WHERE maHD=?";
        XJDBC.update(sql,
        		model.getMaNV(),
                model.getMaPhieuThuePhong(),
                model.getNgayDi(),
                model.getMaHD());
    }

    public boolean delete(String MaNV) {
    	try {
    		String sql = "DELETE FROM HoaDon WHERE maHD=?";
            XJDBC.update(sql, MaNV);
            return true;
		} catch (Exception e) {
			return false;
		}
        
    }

    public ArrayList<HoaDon> selectAll() {
        String sql = "SELECT * FROM HoaDon";
        return this.selectBySql(sql);
    }

    public HoaDon selectById(String maHD) {
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<HoaDon> list = this.selectBySql(sql, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<HoaDon> selectBySql(String sql, Object... args) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                	HoaDon entity = new HoaDon();
                    entity.setMaHD(rs.getInt("maHD"));
                    entity.setMaNV(rs.getString("maNV"));
                    entity.setMaPhieuThuePhong(rs.getInt("maPTP"));
                    entity.setNgayDi(rs.getDate("ngayDi"));
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
    
    private ArrayList<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            ArrayList<Object[]> list = new ArrayList<>();
            ResultSet rs = XJDBC.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Object[]> hoaDon_KhachHang() {
        String sql = "{CALL sp_hoaDon_KhachHang}";
        String[] cols = {"maHD", "hoTen", "email", "sdt"};
        return this.getListOfArray(sql, cols);
    }
	
	public void InsertHoaDonAndChiTietHoaDon(String maNV, int maPTP, Date ngayDi, String maSPList, String soLuong) throws SQLException {
        String sql = "{CALL InsertHoaDonAndChiTietHoaDon (?, ?, ?, ?, ?)}";
		XJDBC.update(sql, maNV, maPTP, ngayDi,maSPList,soLuong);
    }
	
	
    
}
