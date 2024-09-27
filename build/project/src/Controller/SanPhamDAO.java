package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.SanPham;
import Utils.XJDBC;

public class SanPhamDAO{
	
    public ArrayList<SanPham> selectAll() {
        String sql = "SELECT * FROM SanPham";
        return this.selectBySql(sql);
    }

    public SanPham selectById(String maSP) {
        String sql = "SELECT * FROM SanPham WHERE maSP=?";
        List<SanPham> list = this.selectBySql(sql, maSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<SanPham> selectBySql(String sql, Object... args) {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while (rs.next()) {
                	SanPham entity = new SanPham();
                    entity.setMaSP(rs.getString("maSP"));
                    entity.setTenSP(rs.getString("tenSP"));
                    entity.setGia(rs.getInt("gia"));
                    entity.setHinh(rs.getString("hinh"));
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
