package Model;

import java.util.Date;

public class HoaDon {
	
	private int maHD;
	private String maNV;
	private int maPhieuThuePhong;
	private Date ngayDi;
	
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDon(int maHD, String maNV, int maPhieuThuePhong, Date ngayDi) {
		super();
		this.maHD = maHD;
		this.maNV = maNV;
		this.maPhieuThuePhong = maPhieuThuePhong;
		this.ngayDi = ngayDi;
		
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public int getMaPhieuThuePhong() {
		return maPhieuThuePhong;
	}
	public void setMaPhieuThuePhong(int maPhieuThuePhong) {
		this.maPhieuThuePhong = maPhieuThuePhong;
	}
	public Date getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}
	
	
}
