package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.NhanVienDAO;
import Model.NhanVien;
import Utils.DialogHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class themNV implements Initializable {
	
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtHoTen;
	@FXML
	private TextField txtMaNV;
	@FXML
	private TextField txtPass;
	@FXML
	private TextField txtSDT;
	@FXML
    private ComboBox<String> cboVaiTro;
	@FXML
    private RadioButton rdoNam;
    @FXML
    private RadioButton rdoNu;
	@FXML
	private VBox quanLyNVContainer;
	
	ObservableList<String> listCbo = FXCollections.observableArrayList("Nhân viên", "Quản lý");
	ToggleGroup gioiTinhGroup = new ToggleGroup();
	NhanVienDAO dao = new NhanVienDAO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cboVaiTro.setItems(listCbo);
		cboVaiTro.setValue(listCbo.get(0));
		rdoNam.setToggleGroup(gioiTinhGroup);
		rdoNu.setToggleGroup(gioiTinhGroup);
		rdoNam.setSelected(true);
	}
	
	@FXML
	void addNV(ActionEvent event) {
		insert();
	}
	
	public void switchScene(String filename) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
			Parent newContent = loader.load();
			VBox vBox = (VBox) newContent;
			// Binding width and height qlphBox follow to container
			vBox.prefWidthProperty().bind(quanLyNVContainer.widthProperty());
			vBox.prefHeightProperty().bind(quanLyNVContainer.heightProperty());
			quanLyNVContainer.getChildren().setAll(vBox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void switchQLNV(MouseEvent event) {
		switchScene("QuanLyNhanVien.fxml");
	}

	NhanVien getForm() {
		NhanVien nv = new NhanVien();
		nv.setMaNV(txtMaNV.getText());
		nv.setPass(txtPass.getText());
		nv.setHoTen(txtHoTen.getText());
		if(rdoNam.isSelected()) {
			nv.setGioiTinh(1);
		} else {
			nv.setGioiTinh(0);
		}
		
		nv.setEmail(txtEmail.getText());
		nv.setSoDT(txtSDT.getText());
		String vaiTro = cboVaiTro.getValue();
		System.out.println(vaiTro);
		if (vaiTro.equalsIgnoreCase("Nhân viên")) {
			nv.setVaiTro(true);
		} else {
			nv.setVaiTro(false);
		}

		return nv;
	}
	
	void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtPass.setText(nv.getPass());
        txtHoTen.setText(nv.getHoTen());
        txtEmail.setText(nv.getEmail());
        txtSDT.setText(nv.getSoDT());
        if(nv.getVaiTro()) {
        	cboVaiTro.setValue("Nhân viên");
        } else {
        	cboVaiTro.setValue("Quản lý");
        }
		
    }
	
	void clearForm() {
        NhanVien nv = new NhanVien();
        this.setForm(nv);
        
    }

	void insert() {
		NhanVien nv = this.getForm();
		
		try {
			dao.insert(nv); // thêm mới
			this.clearForm();
			DialogHelper.informAlert("Thêm mới thành công!", "Thêm mới thành công!");
			
		} catch (Exception e) {
			DialogHelper.informAlert("Thêm mới thất bại!","Thêm mới thất bại!");
		}

	}
	
}
