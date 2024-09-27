package Model;

public class HoaDonKhachHang {
	
	private int maHD;
	private String hoTen;
	private String email;
	private String sdt;
	public HoaDonKhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDonKhachHang(int maHD, String hoTen, String email, String sdt) {
		super();
		this.maHD = maHD;
		this.hoTen = hoTen;
		this.email = email;
		this.sdt = sdt;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
}	
