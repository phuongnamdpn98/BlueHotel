package Model;

public class Room {
	private String maPhong;
	private String tenPhong;
	private int trangThai;
	private String hinh;
	private int maLP;
	
	public Room() {}
	public Room(String maPhong, String tenPhong, int trangThai, String hinh, int maLP) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.hinh = hinh;
		this.maLP = maLP;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
	}
	public int getMaLP() {
		return maLP;
	}
	public void setMaLP(int maLP) {
		this.maLP = maLP;
	}
	
	

	
}
