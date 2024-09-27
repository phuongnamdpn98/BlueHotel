package Model;

import java.sql.Date;

public class KhachHangDatTruoc {
	private int maPhieu;
	private int maKH;
	private String hoTen;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String email;
	private String soDT;
	private String cccd;
	private int maLK;
	private Date ngayDat;
	private Date ngayDenDK;
	private Date ngayDiDK;
	private int soLuongPhong;
	private int soNguoi;
	
	public KhachHangDatTruoc(int maPhieu, int maKH, String hoTen, Date ngaySinh, boolean gioiTinh, String email, String soDT,
			String cccd, int maLK, Date ngayDat, Date ngayDenDK, Date ngayDiDK, int soLuongPhong, int soNguoi) {
		super();
		this.maPhieu = maPhieu;
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDT = soDT;
		this.cccd = cccd;
		this.maLK = maLK;
		this.ngayDat = ngayDat;
		this.ngayDenDK = ngayDenDK;
		this.ngayDiDK = ngayDiDK;
		this.soLuongPhong = soLuongPhong;
		this.soNguoi = soNguoi;
	}
	public int getMaKH() {
		return maKH;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public int getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	public int getMaLK() {
		return maLK;
	}
	public void setMaLK(int maLoaiKH) {
		this.maLK = maLoaiKH;
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
	public int getSoLuongPhong() {
		return soLuongPhong;
	}
	public void setSoLuongPhong(int soLuongPhong) {
		this.soLuongPhong = soLuongPhong;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	
	
	
}
