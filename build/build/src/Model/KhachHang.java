package Model;

import java.sql.Date;

public class KhachHang {
	
	private int maKH;
	private String hoTen;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String email;
	private String soDT;
	private String cccd;
	private int maLoaiKH;
	public KhachHang() {
		super();
	}
	public KhachHang(String hoTen, Date ngaySinh, boolean gioiTinh, String email, String soDT, String cccd,
			int maLoaiKH) {
		super();
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDT = soDT;
		this.cccd = cccd;
		this.maLoaiKH = maLoaiKH;
	}
	public int getMaKH() {
		return maKH;
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
	public int getMaLoaiKH() {
		return maLoaiKH;
	}
	public void setMaLoaiKH(int maLoaiKH) {
		this.maLoaiKH = maLoaiKH;
	}
	
	
}
