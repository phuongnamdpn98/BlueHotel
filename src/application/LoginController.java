package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.NhanVienDAO;
import Model.NhanVien;
import Utils.Auth;
import Utils.DialogHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
	
	private Stage stage;
    private Scene scene;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnForgetPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    
    NhanVienDAO dao = new NhanVienDAO();
    
    @FXML
    void clickExit(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void clickForgetPass(ActionEvent event) {
    	try {
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader1 = new FXMLLoader();
			loader1.setLocation(getClass().getResource("ForgetPass.fxml"));
			Parent forgetFrame = loader1.load();
	        
	        scene = new Scene(forgetFrame);
	        stage.setScene(scene);
	        stage.show();
		} catch (Exception e) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
		}
    }


    @FXML
    void clickLogin(ActionEvent event) {
    	String manv = txtUsername.getText();
        String matKhau = txtPassword.getText();
        NhanVien nhanVien = dao.selectById(manv);
        if(nhanVien == null){
            DialogHelper.warningAlert("Sai tên đăng nhập!","Sai tên đăng nhập!");
        }
        else if(!matKhau.equals(nhanVien.getPass())){
            DialogHelper.warningAlert("Sai mật khẩu!","Sai mật khẩu!");
        }
        else{
            Auth.user = nhanVien;
    		try {
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("Main.fxml"));
    			Parent mainFrame = loader.load();
    	        scene = new Scene(mainFrame);
    	        stage.setScene(scene);
    	        stage.show();
    	        MainController ctrl = loader.getController();
    	        //ctrl.setEventMain();
    	        if(Auth.user.getVaiTro()) {
    	        	ctrl.setEventMainUser();
    	    	} else {
    	    		
    	    		ctrl.setEventMainAdmin();
    	    	}
    			
    		} catch (IOException e) {
    			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
    		}
        }
    }
    

    
}
