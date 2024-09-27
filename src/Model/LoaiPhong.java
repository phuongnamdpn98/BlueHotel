package Model;

public class LoaiPhong {
	
	private int maLoaiPhong;
	private String tenLoaiPhong;
	private int gia;
	public LoaiPhong() {
		super();
	}
	public LoaiPhong(int maLoaiPhong, String tenLoaiPhong, int gia) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.gia = gia;
	}
	public int getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(int maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	
	
	
}
