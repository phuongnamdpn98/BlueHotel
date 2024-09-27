package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utils.XJDBC;

public class ThongKeDAO {
	
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
	
	public List<Object[]> thongKeNam(int year) {
        String sql = "{CALL sp_ThongKeNam (?)}";
        String[] cols = {"Thang", "DoanhThu"};
        return this.getListOfArray(sql, cols, year);
    }

    public List<Object[]> tinhPhanTramLoaiKhachHang() {
        String sql = "{CALL sp_TinhPhanTramLoaiKhachHang}";
        String[] cols = {"LoaiLK", "PhanTram"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> tinhPhanTramLoaiPhong() {
        String sql = "{CALL sp_TinhPhanTramLoaiPhong}";
        String[] cols = {"LoaiPhong", "PhanTram"};
        return this.getListOfArray(sql, cols);
    }
}
