package application;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.Date;
import Controller.KhachHangDAO;
import Model.KhachHang;
import Utils.DialogHelper;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class capNhatKH implements Initializable {
		
	@FXML
    private ComboBox<String> cboKieuKH;

    @FXML
    private VBox quanLyKHContainer;

    @FXML
    private RadioButton rdoNam;

    @FXML
    private RadioButton rdoNu;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHoTenKh;
    @FXML
    private TextField txtMaKH;

    @FXML
    private TextField txtSDT;
    @FXML
    private DatePicker ngaySinh_CNKH;
   
    //Giới tính 0 là true là Nam và 1 là false là nữ
    ObservableList<String> listCbo = FXCollections.observableArrayList("Khách cá nhân", "Khách VIP", "Khách đoàn");
	ToggleGroup gioiTinhGroup = new ToggleGroup();
	KhachHang getKH = QLKHController.khData;
	KhachHangDAO dao = new KhachHangDAO();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cboKieuKH.setItems(listCbo);
		rdoNam.setToggleGroup(gioiTinhGroup);
		rdoNu.setToggleGroup(gioiTinhGroup);
		//System.out.println(getKH.getHoTen());
		setForm(getKH);
	}

    @FXML
    void switchQLKH(MouseEvent event) {
    	switchScene("QuanLyKhachHang.fxml");
    }
    
    @FXML
    void updateKhachHang(ActionEvent event) {
    	update();
    }
    
    public void switchScene(String filename) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
			Parent newContent = loader.load();
			VBox vBox = (VBox) newContent;
			// Binding width and height qlphBox follow to container
			vBox.prefWidthProperty().bind(quanLyKHContainer.widthProperty());
			vBox.prefHeightProperty().bind(quanLyKHContainer.heightProperty());
			quanLyKHContainer.getChildren().setAll(vBox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	   
    private KhachHang getForm() {
    	KhachHang kh = new KhachHang();
    	kh.setMaKH(Integer.valueOf( txtMaKH.getText()));
		kh.setHoTen(txtHoTenKh.getText());
		LocalDate ngaySinh = ngaySinh_CNKH.getValue();
		kh.setNgaySinh(Date.valueOf(ngaySinh));
		
		if (rdoNam.isSelected()) {
			kh.setGioiTinh(true);
		} else {
			kh.setGioiTinh(false);
		}
		kh.setEmail(txtEmail.getText());
		kh.setSoDT(txtSDT.getText());
		kh.setCccd(txtCCCD.getText());
		String cboloaiKH = cboKieuKH.getValue();
		int loaiKH;
		if (cboloaiKH.equals("Khách cá nhân")) {
			loaiKH = 1;
		} else if (cboloaiKH.equals("Khách VIP")) {
			loaiKH = 2;
		} else {
			loaiKH = 3;
		}
		kh.setMaLoaiKH(loaiKH);
		return kh;
	}

    private void setForm(KhachHang kh) {
    	txtMaKH.setText(String.valueOf(kh.getMaKH()));
		txtHoTenKh.setText(kh.getHoTen());
		txtCCCD.setText(kh.getCccd());
		txtEmail.setText(kh.getEmail());
		LocalDate ngaySinh = kh.getNgaySinh().toLocalDate();
		ngaySinh_CNKH.setValue(ngaySinh);
		
		txtSDT.setText(kh.getSoDT());
		switch (kh.getMaLoaiKH()) {
			case 1:
				cboKieuKH.setValue("Khách cá nhân");
				break;
			case 2:
				cboKieuKH.setValue("Khách VIP");
				break;
			case 3:
				cboKieuKH.setValue("Khách đoàn");
				break;
			default:
				cboKieuKH.setValue("Khách cá nhân");
				break;
			}
		if (kh.getGioiTinh()) {
			rdoNu.setSelected(true);
	    } else {
	        rdoNam.setSelected(true);
	    }
	}

    private void clearForm() {
		txtMaKH.setText("");
		txtHoTenKh.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		ngaySinh_CNKH.setValue(null);
		txtSDT.setText("");

	}
	
	private void update() {
		KhachHang kh = this.getForm();

		try {
			System.out.println("ma khach hang: "+kh.getMaKH());
			dao.update(kh); // cập nhật
			this.clearForm();
			DialogHelper.informAlert("Thêm mới thành công!", "Thêm mới thành công!");

		} catch (Exception e) {
			DialogHelper.informAlert("Thêm mới thất bại!", "Thêm mới thất bại!");
		}
	}

}
