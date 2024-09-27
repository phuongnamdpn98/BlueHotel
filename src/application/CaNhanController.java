package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.NhanVien;
import Utils.Auth;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CaNhanController implements Initializable {
	
	@FXML
    private Button btnDoiMatKhau;
	
	@FXML
    private VBox caNhanContainer;

    @FXML
    private ComboBox<String> cboVaiTro;

    @FXML
    private RadioButton rdoNam;

    @FXML
    private RadioButton rdoNu;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHoTen;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUserName;
    
    NhanVien user = Auth.user;
    ObservableList<String> listCbo = FXCollections.observableArrayList("Nhân viên", "Quản lý");
	ToggleGroup gioiTinhGroup = new ToggleGroup();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//System.out.println(user.getEmail());
		setForm();
	}
	
	@FXML
    void switchDoiMatKhau(MouseEvent event) {
		switchScene("DoiMatKhau.fxml");
    }
	
	public void switchScene(String filename) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
			Parent newContent = loader.load();
			VBox vBox = (VBox) newContent;
			// Binding width and height qlphBox follow to container
			vBox.prefWidthProperty().bind(caNhanContainer.widthProperty());
			vBox.prefHeightProperty().bind(caNhanContainer.heightProperty());
			caNhanContainer.getChildren().setAll(vBox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setForm() {
		txtUserName.setText(user.getMaNV());
		txtHoTen.setText(user.getHoTen());
		txtEmail.setText(user.getEmail());
		txtPhone.setText(user.getSoDT());
		if(user.getVaiTro()) {
        	cboVaiTro.setValue("Nhân viên");
        } else {
        	cboVaiTro.setValue("Quản lý");
        }
		if(user.getGioiTinh() == 1) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
	}
	
}
