package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.NhanVienDAO;
import Model.NhanVien;
import Utils.Auth;
import Utils.DialogHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DoiMatKhauController implements Initializable {
	@FXML
	private Button btnXacNhan;

	@FXML
	private VBox doiMatKauContainer;

	@FXML
	private TextField txtMatKhauCu;

	@FXML
	private TextField txtMatKhauMoi;

	@FXML
	private TextField txtXacNhanMatKhauMoi;

	NhanVien user = Auth.user;
	NhanVienDAO nvDao = new NhanVienDAO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//System.out.println(user.getEmail());

	}

	@FXML
	void btnXacNhanDoi(ActionEvent event) {
		if(isValidate()) {
			//System.out.println("correct");
			user.setPass(txtMatKhauMoi.getText());
			nvDao.update(user);
			DialogHelper.informAlert("Đổi mật khẩu thành công", null);
			clearForm();
		} else {
			//System.out.println("un correct");
			
		}
	}

	@FXML
	void btnTroVe(MouseEvent event) {
		switchScene("CaNhan.fxml");
	}

	@FXML
	void switchQLNV(MouseEvent event) {

	}

	private boolean isValidate() {
		if (txtMatKhauCu.getText().equals("") || !txtMatKhauCu.getText().equals(user.getPass())) {
			DialogHelper.errorAlert("Bạn chưa nhập mật khẩu hoặc mật khẩu không đúng", null);
			return false;
		} 
		
		if(txtMatKhauMoi.getText().equals("")) {
			DialogHelper.errorAlert("Bạn chưa nhập mật khẩu mới", null);
			return false;
		} 
		
		if(txtXacNhanMatKhauMoi.getText().equals("") || !txtXacNhanMatKhauMoi.getText().equals(txtMatKhauMoi.getText())) {
			DialogHelper.errorAlert("Bạn chưa nhập mật khẩu mới hoặc mật khẩu mới không đúng", null);
			return false;
		}
		
		return true;
	}
	
	private void clearForm() {
		txtMatKhauCu.setText("");
		txtMatKhauMoi.setText("");
		txtXacNhanMatKhauMoi.setText("");
	}

	public void switchScene(String filename) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
			Parent newContent = loader.load();
			VBox vBox = (VBox) newContent;
			// Binding width and height qlphBox follow to container
			vBox.prefWidthProperty().bind(doiMatKauContainer.widthProperty());
			vBox.prefHeightProperty().bind(doiMatKauContainer.heightProperty());
			doiMatKauContainer.getChildren().setAll(vBox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
