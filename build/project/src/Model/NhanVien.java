package Model;

public class NhanVien {

	private String maNV;
	private String pass;
	private String hoTen;
	private int gioiTinh;
	private String email;
	private String soDT;
	private boolean vaiTro;
	public NhanVien() {
		super();
	}
	public NhanVien(String maNV, String pass, String hoTen, int gioiTinh, String email, String soDT, boolean vaiTro) {
		super();
		this.maNV = maNV;
		this.pass = pass;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDT = soDT;
		this.vaiTro = vaiTro;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(int gioiTinh) {
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
	public boolean getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}
	
	
}
