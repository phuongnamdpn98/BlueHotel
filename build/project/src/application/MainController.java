package application;

import java.io.IOException;

import Model.NhanVien;
import Utils.Auth;
import Utils.DialogHelper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private GridPane navbar; // Container các top nav-item
	@FXML 
	private SVGPath navSVGPath; // SVG nằm trong nav-item
    @FXML
    private Text navText; // Text trong nav-item
    @FXML
    private VBox container; // Container render nội dung của từng nav-item
    
    NhanVien user = Auth.user;
    @FXML
    public void initialize() {
    	
    	if(user.getVaiTro()) {
    		setEventMainUser();
    	} else {
    		setEventMainAdmin();
    	}
    	switchScene("QuanLyPhong.fxml");
    }

    // Method to switch between different scenes
    public void switchScene(String filename)  {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
            Parent newContent = loader.load();
            VBox vbox = (VBox) newContent;
            // Binding width and height vbox follow to container            
            vbox.prefWidthProperty().bind(container.widthProperty());
            vbox.prefHeightProperty().bind(container.heightProperty());
            container.getChildren().setAll(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void setEventMainUser() {
    	// Set event click cho các top nav-item
    	int i = 1;
    	for(Node navBtn : navbar.getChildren()) {
    		final int index = i;
    		navBtn.setOnMouseClicked(event -> {
    			switch (index) {
					case 1: {
						switchScene("QuanLyPhong.fxml");
						switchNav1(navBtn);
						break;
					}
					case 2: {
						DialogHelper.informAlert("Vui lòng đăng nhập với quyền quản lý", null);
						//switchScene("QuanLyPhong.fxml");
						break;
					}
					case 3: {
						switchScene("QuanLyKhachHang.fxml");
						switchNav1(navBtn);
						break;
					}
					case 4: {
						switchScene("QuanLyThanhToan.fxml");
						switchNav1(navBtn);
						break;
					}
					case 5: {
						DialogHelper.informAlert("Vui lòng đăng nhập với quyền quản lý", null);
						//switchScene("QuanLyPhong.fxml");
						break;
					}
					//case 6: bỏ qua 
					case 7: {
						switchScene("CaNhan.fxml");
						switchNav1(navBtn);
						break;
					}
					case 8: {
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						FXMLLoader loadera = new FXMLLoader();
						loadera.setLocation(getClass().getResource("LoginBH.fxml"));
						Parent mainFrame;
						try {
							mainFrame = loadera.load();
							Scene scene = new Scene(mainFrame);
							stage.setScene(scene);
							stage.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("click logout");
						break;
					}
					case 9: {
						Platform.exit();
					}
    			};
    			    			   				
    		});
    		i++;
    	}
	}
    
    private void switchNav1(Node navBtn) {
    	// Find and clear active class in all nav item
    	for(Node watchBtn : navbar.getChildren()) {
			HBox box = (HBox) watchBtn;
			if (box.getStyleClass().contains("active")){
				box.getStyleClass().remove("active");
				Group group = (Group) box.getChildren().get(0);
				SVGPath svg = (SVGPath)group.getChildren().get(0);
				svg.setFill(Paint.valueOf("#ffffff"));
				Text txt = (Text) box.getChildren().get(1);
				txt.setFill(Paint.valueOf("#ffffff"));
				break;
			}
		}
		// Set active class for clicked nav item
		navBtn.getStyleClass().add("active");
		HBox box = (HBox) navBtn;
		Group group = (Group) box.getChildren().get(0);
		SVGPath svg = (SVGPath)group.getChildren().get(0);
		svg.setFill(Paint.valueOf("#000000"));
		Text txt = (Text) box.getChildren().get(1);
		txt.setFill(Paint.valueOf("#000000"));
	
	
    }
    
    public void setEventMainAdmin() {
    	// Set event click cho các top nav-item
    	int i = 1;
    	for(Node navBtn : navbar.getChildren()) {
    		final int index = i;
    		navBtn.setOnMouseClicked(event -> {
    			switch (index) {
					case 1: {
						switchScene("QuanLyPhong.fxml");
						break;
					}
					case 2: {
						switchScene("QuanLyNhanVien.fxml");
						break;
					}
					case 3: {
						switchScene("QuanLyKhachHang.fxml");
						break;
					}
					case 4: {
						switchScene("QuanLyThanhToan.fxml");
						break;
					}
					case 5: {
						switchScene("ThongKeBaoCao.fxml");
						break;
					}
					//case 6: bỏ qua 
					case 7: {
						switchScene("CaNhan.fxml");
						break;
					}
					case 8: {
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						FXMLLoader loadera = new FXMLLoader();
						loadera.setLocation(getClass().getResource("LoginBH.fxml"));
						Parent mainFrame;
						try {
							mainFrame = loadera.load();
							Scene scene = new Scene(mainFrame);
							stage.setScene(scene);
							stage.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("click logout");
						break;
					}
					case 9: {
						Platform.exit();
					}
    			};
    			// Find and clear active class in all nav item
    			for(Node watchBtn : navbar.getChildren()) {
    				HBox box = (HBox) watchBtn;
    				if (box.getStyleClass().contains("active")){
    					box.getStyleClass().remove("active");
    					Group group = (Group) box.getChildren().get(0);
    					SVGPath svg = (SVGPath)group.getChildren().get(0);
    					svg.setFill(Paint.valueOf("#ffffff"));
    					Text txt = (Text) box.getChildren().get(1);
    					txt.setFill(Paint.valueOf("#ffffff"));
    					break;
    				}
    			}
    			// Set active class for clicked nav item
    			navBtn.getStyleClass().add("active");
    			HBox box = (HBox) navBtn;
				Group group = (Group) box.getChildren().get(0);
				SVGPath svg = (SVGPath)group.getChildren().get(0);
				svg.setFill(Paint.valueOf("#000000"));
				Text txt = (Text) box.getChildren().get(1);
				txt.setFill(Paint.valueOf("#000000"));
    		});
    		i++;
    	}
	}
    
}
