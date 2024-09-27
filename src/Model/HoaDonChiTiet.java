package Model;

public class HoaDonChiTiet {
	
	private int maHDCT;
	private String maSP;
	private int maHD;
	private int soLuongSP;
	public HoaDonChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDonChiTiet(int maHDCT, String maSP, int maHD, int soLuongSP) {
		super();
		this.maHDCT = maHDCT;
		this.maSP = maSP;
		this.maHD = maHD;
		this.soLuongSP = soLuongSP;
	}
	public int getMaHDCT() {
		return maHDCT;
	}
	public void setMaHDCT(int maHDCT) {
		this.maHDCT = maHDCT;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	
	
}
