package application;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Controller.HoaDonDAO;
import Model.HoaDonKhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuanLyThanhToanController implements Initializable {
	
	@FXML
    private TableColumn<Object, String> emailColumn;
    @FXML
    private TableColumn<Object, String> hoTenColumn;
    @FXML
    private TableColumn<Object, Integer> maHDColumn;
    @FXML
    private TableColumn<Object, String> sdtColumn;
    @FXML
    private TableView<HoaDonKhachHang> table;
    @FXML
    private TextField searchHoaDon;
    @FXML
    private TableColumn<Map<String, Object>, Button> xemColumn;
    @FXML
	private ObservableList<HoaDonKhachHang> hoaDonList;
    HoaDonDAO dao = new HoaDonDAO();
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillTable();
		
	}
		
	public void fillTable() {
		List<Object[]> dataFromProcedure = dao.hoaDon_KhachHang();
		this.hoaDonList = FXCollections.observableArrayList();
		System.out.println(dataFromProcedure);
		for(Object[] row : dataFromProcedure) {
			int maHD = Integer.parseInt( String.valueOf(row[0]) );
	        String hoTen = row[1].toString();
	        String email = row[2].toString();
	        String sdt = row[3].toString();
	        
			
			HoaDonKhachHang hd = new HoaDonKhachHang(maHD, hoTen, email, sdt);
			hoaDonList.add(hd);
			
		}
		maHDColumn.setCellValueFactory(new PropertyValueFactory<>("maHD"));
	    hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
	    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
	    sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdt"));
		table.setItems(hoaDonList);
	}
	
	}
