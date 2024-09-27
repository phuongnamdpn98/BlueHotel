package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Controller.KhachHangDAO;
import Model.KhachHang;
import Utils.DialogHelper;
import Utils.XDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class themKH implements Initializable {

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
	private TextField txtSDT;
	@FXML
    private DatePicker ngsySinh_AddCustomer;

	ObservableList<String> listCbo = FXCollections.observableArrayList("Khách cá nhân", "Khách VIP", "Khách đoàn");
	ToggleGroup gioiTinhGroup = new ToggleGroup();
	KhachHangDAO dao = new KhachHangDAO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cboKieuKH.setItems(listCbo);
		rdoNam.setToggleGroup(gioiTinhGroup);
		rdoNu.setToggleGroup(gioiTinhGroup);
	}
	
	@FXML
    void switchQLNV(MouseEvent event) {

    }
	@FXML
    void cboLoaiKH(ActionEvent event) {

    }

	@FXML
	void addKhachHangMoi(ActionEvent event) {
		insert();
	}

	@FXML
	void switchQLKH(ActionEvent event) {
		switchScene("QuanLyKhachHang.fxml");
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
	

	KhachHang getForm() {
		KhachHang kh = new KhachHang();
		kh.setHoTen(txtHoTenKh.getText());
		LocalDate ngaySinh = ngsySinh_AddCustomer.getValue();
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

	void clearForm() {
		
		txtHoTenKh.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		ngsySinh_AddCustomer.setValue(null);
		txtSDT.setText("");

	}

	void insert() {
		KhachHang kh = this.getForm();

		try {
			dao.insert(kh); // thêm mới

			this.clearForm();
			DialogHelper.informAlert("Thêm mới thành công!","");

		} catch (Exception e) {
			DialogHelper.informAlert("Thêm mới thất bại!", "");

		}

	}
	
}
