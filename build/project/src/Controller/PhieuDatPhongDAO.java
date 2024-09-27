package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.PhieuDatPhong;
import Utils.XJDBC;

public class PhieuDatPhongDAO extends BlueHotelDAO<PhieuDatPhong, String> {

	@Override
	public boolean insert(PhieuDatPhong model) {
		try {
			String sql = "INSERT INTO PhieuDatPhong (maNV, maKH, ngayDat, ngayDenDK, ngayDiDK, soNguoi, soLuongPhong) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	        XJDBC.update(sql,
	                model.getMaNV(),
	                model.getMaKH(),
	                model.getNgayDat(),
	                model.getNgayDenDK(),
	                model.getNgayDiDK(),
	                model.getSoNguoi(),
	                model.getSoLuongPhong());
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(PhieuDatPhong model) {
		String sql = "UPDATE PhieuDatPhong SET maNV =?, maKH =?, ngayDat =?, ngayDenDK =?, ngayDiDK =?, soNguoi =?, soLuongPhong =? WHERE maPhieu =?";
        XJDBC.update(sql,
        		model.getMaNV(),
                model.getMaKH(),
                model.getNgayDat(),
                model.getNgayDenDK(),
                model.getNgayDiDK(),
                model.getSoNguoi(),
                model.getSoLuongPhong(),
                model.getMaPhieu());
		
	}

	@Override
	public boolean delete(String maPhieu) {
		try {
			String sql = "DELETE FROM PhieuDatPhong WHERE maPhieu =?";
	        XJDBC.update(sql, maPhieu);
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public ArrayList<PhieuDatPhong> selectAll() {
		String sql = "SELECT * FROM PhieuDatPhong";
        return this.selectBySql(sql);
	}

	@Override
	public PhieuDatPhong selectById(String maPhieu) {
		String sql = "SELECT * FROM PhieuDatPhong WHERE maPhieu =?";
        List<PhieuDatPhong> list = this.selectBySql(sql, maPhieu);
        return list.size() > 0 ? list.get(0) : null;
		
	}

	@Override
	public ArrayList<PhieuDatPhong> selectBySql(String sql, Object... args) {
		ArrayList<PhieuDatPhong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                	PhieuDatPhong entity = new PhieuDatPhong();
                    entity.setMaPhieu(rs.getInt("maPhieu"));
                    entity.setMaNV(rs.getString("maNV"));
                    entity.setMaKH(rs.getInt("maKH"));
                    entity.setNgayDat(rs.getDate("ngayDat"));
                    entity.setNgayDenDK(rs.getDate("ngayDenDK"));
                    entity.setNgayDiDK(rs.getDate("ngayDiDK"));
                    entity.setSoNguoi(rs.getInt("soNguoi"));
                    entity.setSoLuongPhong(rs.getInt("soLuongPhong"));
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
