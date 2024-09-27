package Model;

import java.sql.Date;

public class PhieuDatPhong {
	
	private int maPhieu; // ms sql tự sinh
	private String maNV;
	private int maKH;
	private Date ngayDat;
	private Date ngayDenDK;
	private Date ngayDiDK;
	private int soNguoi;
	private int soLuongPhong;
	public PhieuDatPhong() {
		super();
	}
	public PhieuDatPhong(String maNV, int maKH, Date ngayDat, Date ngayDenDK, Date ngayDiDK, int soNguoi,
			int soLuongPhong) {
		super();
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayDat = ngayDat;
		this.ngayDenDK = ngayDenDK;
		this.ngayDiDK = ngayDiDK;
		this.soNguoi = soNguoi;
		this.soLuongPhong = soLuongPhong;
	}
	public int getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public Date getNgayDenDK() {
		return ngayDenDK;
	}
	public void setNgayDenDK(Date ngayDenDK) {
		this.ngayDenDK = ngayDenDK;
	}
	public Date getNgayDiDK() {
		return ngayDiDK;
	}
	public void setNgayDiDK(Date ngayDiDK) {
		this.ngayDiDK = ngayDiDK;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	public int getSoLuongPhong() {
		return soLuongPhong;
	}
	public void setSoLuongPhong(int soLuongPhong) {
		this.soLuongPhong = soLuongPhong;
	}
	
	
	
}
