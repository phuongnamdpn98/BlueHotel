package Model;

public class LoaiKhachHang {
	private int maLoaiKH;
	private String tenLoaiKH;
	public LoaiKhachHang() {
		super();
	}
	public LoaiKhachHang(int maLoaiKH, String tenLoaiKH) {
		super();
		this.maLoaiKH = maLoaiKH;
		this.tenLoaiKH = tenLoaiKH;
	}
	public int getMaLoaiKH() {
		return maLoaiKH;
	}
	public void setMaLoaiKH(int maLoaiKH) {
		this.maLoaiKH = maLoaiKH;
	}
	public String getTenLoaiKH() {
		return tenLoaiKH;
	}
	public void setTenLoaiKH(String tenLoaiKH) {
		this.tenLoaiKH = tenLoaiKH;
	}
	
	
}
