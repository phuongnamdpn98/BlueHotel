package Model;

public class SanPham {
	
	private String maSP;
	private String tenSP;
	private int gia;
	private String hinh;
	private int soLuong;
	public int getTotal() {
		return gia * soLuong;
	}

	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public SanPham() {
		super();
		
	}
	public SanPham(String maSP, String tenSP, int gia, String hinh) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.gia = gia;
		this.hinh = hinh;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
	}
	
	
}
