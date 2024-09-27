package Model;

import java.util.Date;

public class KhachHangDangThue {
	private int maPTP;
	private int maKH;
	private String hoTen;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String email;
	private String soDT;
	private String cccd;
	private int maLK;
	private Date ngayDen;
	private String maPhong;
	private String tenPhong;
	private int maLP;
	private int gia;

	public KhachHangDangThue(int maPTP, int maKH, String hoTen, Date ngaySinh, boolean gioiTinh, String email,
			String soDT, String cccd, int maLK, Date ngayDen, String maPhong, String tenPhong,
			int maLP, int gia) {
		super();
		this.maPTP = maPTP;
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDT = soDT;
		this.cccd = cccd;
		this.maLK = maLK;
		this.ngayDen = ngayDen;
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.maLP = maLP;
		this.gia = gia;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getMaLP() {
		return maLP;
	}

	public void setMaLP(int maLP) {
		this.maLP = maLP;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public int getMaPTP() {
		return maPTP;
	}

	public void setMaPTP(int maPTP) {
		this.maPTP = maPTP;
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

	public int getMaLK() {
		return maLK;
	}

	public void setMaLK(int maLK) {
		this.maLK = maLK;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

}
