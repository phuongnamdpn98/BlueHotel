package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.HoaDonChiTiet;
import Utils.XJDBC;

public class ChiTietHoaDonDAO extends BlueHotelDAO<HoaDonChiTiet, String> {
//	maCTHD int identity(1,1) not null,
//	maSP varchar(10) not null,
//	maHD int not null,
//	soLuongSP int not null,
	
	public boolean insert(HoaDonChiTiet model) {
        try {
        	String sql = "INSERT INTO ChiTietHoaDon (maSP, maHD, soLuongSP) VALUES ( ?, ?, ?)";
        	XJDBC.update(sql,
        			model.getMaSP(),
        			model.getMaHD(),
        			model.getSoLuongSP());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    public void update(HoaDonChiTiet model) {
        String sql = "UPDATE ChiTietHoaDon SET maSP =?, maHD =?, soLuongSP =? WHERE maCTHD=?";
        XJDBC.update(sql,
        		 model.getMaSP(),
                 model.getMaHD(),
                 model.getSoLuongSP(),
                 model.getMaHDCT());
    }

    public boolean delete(String maCTHD) {
    	try {
    		String sql = "DELETE FROM ChiTietHoaDon WHERE maCTHD=?";
            XJDBC.update(sql, maCTHD);
            return true;
		} catch (Exception e) {
			return false;
		}
        
    }

    public ArrayList<HoaDonChiTiet> selectAll() {
        String sql = "SELECT * FROM ChiTietHoaDon";
        return this.selectBySql(sql);
    }

    public HoaDonChiTiet selectById(String maCTHD) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE maCTHD=?";
        List<HoaDonChiTiet> list = this.selectBySql(sql, maCTHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                	HoaDonChiTiet entity = new HoaDonChiTiet();
                    entity.setMaHDCT(rs.getInt("maCTHD"));
                    entity.setMaSP(rs.getString("maSP"));
                    entity.setMaHD(rs.getInt("maHD"));
                    entity.setSoLuongSP(rs.getInt("soLuongSP"));
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
