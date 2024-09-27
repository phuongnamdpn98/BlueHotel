package Model;

import java.sql.Date;

public class PhieuThuePhong {

	private int maPhieuThuePhong;
	private String maNV;
	private int maKH;
	private String maPhong;
	private Date ngayDen;
	private int trangThai;
	public PhieuThuePhong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhieuThuePhong( String maNV, int maKH, String maPhong, Date ngayDen, int trangThai) {
		super();
		this.maNV = maNV;
		this.maKH = maKH;
		this.maPhong = maPhong;
		this.ngayDen = ngayDen;
		this.trangThai = trangThai;
	}
	public int getMaPhieuThuePhong() {
		return maPhieuThuePhong;
	}
	public void setMaPhieuThuePhong(int maPhieuThuePhong) {
		this.maPhieuThuePhong = maPhieuThuePhong;
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
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public Date getNgayDen() {
		return ngayDen;
	}
	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	

	
	
}
