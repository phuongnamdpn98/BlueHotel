package application;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Controller.NhanVienDAO;
import Model.NhanVien;
import Utils.DialogHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgetController {

	private Stage stage;
	private Scene scene;

	@FXML
	private Button btnBackLogin;

	@FXML
	private Button btnYeuCau;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtUsername;
	
	NhanVienDAO dao = new NhanVienDAO();

	@FXML
	void backLogin(ActionEvent event) {
		try {

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loadera = new FXMLLoader();
			loadera.setLocation(getClass().getResource("LoginBH.fxml"));
			Parent mainFrame = loadera.load();

			scene = new Scene(mainFrame);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@FXML
	void clickYeuCau(ActionEvent event) {
		sendEmail();
	}

	
	public static String generateRandomPassword(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	

	private void sendEmail() {
		String manv = txtUsername.getText();
		String email = txtEmail.getText();
		NhanVien nhanVien = dao.selectById(manv);
		String password = generateRandomPassword(6);
        System.out.println("Mật khẩu ngẫu nhiên: " + password);
		if (nhanVien == null) {
			DialogHelper.informAlert("Sai tên đăng nhập!", "Sai tên đăng nhập!");

		} else if (!email.equals(nhanVien.getEmail())) {
			DialogHelper.informAlert("Sai Email!", "Sai Email!");

		} else {
			try {
				
				nhanVien.setPass(password);
				dao.update(nhanVien);
				
				Properties p = new Properties();
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", 587);
				p.put("mail.smtp.ssl.trust", "*");

				String accountName = "phuongnamdpn98@gmail.com";
				// String accountPassword = new String(txtPassword.getPassword());
				String accountPassword = "ptccuqdvuxzqmpcw";
				Session s = Session.getInstance(p, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(accountName, accountPassword);
					}
				});

				String from = accountName;
				String to = email;
				String subject = "Quên mật khẩu";
				String body = "Mật khẩu của bạn là: " + nhanVien.getPass();

				MimeMessage msg = new MimeMessage(s);
				msg.setFrom(new InternetAddress(from));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				msg.setSubject(subject);
				msg.setText(body);

				Transport.send(msg);
				DialogHelper.informAlert("Vui lòng kiểm tra email!", "Vui lòng kiểm tra email!");
			} catch (Exception e) {

			}
		}
	}
}
