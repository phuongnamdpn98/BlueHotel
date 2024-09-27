package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.PhieuThuePhong;
import Utils.XJDBC;

public class PhieuThuePhongDAO extends BlueHotelDAO<PhieuThuePhong, String> {
	
//	maPTP int identity(1,1) not null,
//	maNV varchar(50) not null,
//	maKH int not null,
//	maPhong varchar(10) not null,
//	ngayDen date not null,
	public boolean insert(PhieuThuePhong model) {
		try {
			String sql = "INSERT INTO PhieuThuePhong (maNV, maKH, maPhong, ngayDen, trangThai) VALUES ( ?, ?, ?, ?, ?)";
	        XJDBC.update(sql,
	                model.getMaNV(),
	                model.getMaKH(),
	                model.getMaPhong(),
	                model.getNgayDen(),
	        		model.getTrangThai());
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    public void update(PhieuThuePhong model) {
        String sql = "UPDATE PhieuThuePhong SET maNV =?, maKH =?, maPhong =?, ngayDen =?, trangThai=? WHERE maPTP=?";
        XJDBC.update(sql,
        		model.getMaNV(),
                model.getMaKH(),
                model.getMaPhong(),
                model.getNgayDen(),
                model.getTrangThai(),
                model.getMaPhieuThuePhong());
    }
    
    public void updateTrangThai(int maPTP, int trangThai) {
        String sql = "UPDATE PhieuThuePhong SET  trangThai=? WHERE maPTP=?";
        XJDBC.update(sql,
        		trangThai,
        		maPTP);
    }

    public boolean delete(String maPTP) {
    	try {
    		String sql = "DELETE FROM PhieuThuePhong WHERE maPTP=?";
            XJDBC.update(sql, maPTP);
            return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    public ArrayList<PhieuThuePhong> selectAll() {
        String sql = "SELECT * FROM PhieuThuePhong";
        return this.selectBySql(sql);
    }

    public PhieuThuePhong selectById(String maPTP) {
        String sql = "SELECT * FROM PhieuThuePhong WHERE maPTP=?";
        List<PhieuThuePhong> list = this.selectBySql(sql, maPTP);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<PhieuThuePhong> selectBySql(String sql, Object... args) {
        ArrayList<PhieuThuePhong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                	PhieuThuePhong entity = new PhieuThuePhong();
                    entity.setMaPhieuThuePhong(rs.getInt("maPTP"));
                    entity.setMaNV(rs.getString("maNV"));
                    entity.setMaKH(rs.getInt("maKH"));
                    entity.setMaPhong(rs.getString("maPhong"));
                    entity.setNgayDen(rs.getDate("ngayDen"));
                    entity.setTrangThai(rs.getInt("trangThai"));
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
