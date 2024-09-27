package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Controller.KhachHangDAO;
import Model.KhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class QLKHController implements Initializable {

	@FXML
	private Button btnSearch;
	@FXML
	private VBox quanLyKHContainer;
	@FXML
	private TableView<KhachHang> table;
	@FXML
	private TableColumn<KhachHang, Integer> idColumn;
	@FXML
	private TableColumn<KhachHang, String> nameColumn;
	@FXML
	private TableColumn<KhachHang, String> emailColumn;
	@FXML
	private TableColumn<KhachHang, String> phoneColumn;
	@FXML
	private TableColumn<Map<String, KhachHang>, Button> updateColumn;
	@FXML
	private TableColumn<Map<String, KhachHang>, Button> deleteColumn;
	@FXML
	private Button themKH;
	@FXML
	private TextField txtSearchKhachHang;
	@FXML
	private ObservableList<KhachHang> khachHangList;
	
	KhachHangDAO dao = new KhachHangDAO();
	ArrayList<KhachHang> kh = new ArrayList<KhachHang>();
	static KhachHang khData;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		kh = dao.selectAll();
		fillTable(kh);
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

	@FXML
	void switchADDKhachHang(MouseEvent event) {
		switchScene("ThemKhachHang.fxml");
	}

	@FXML
	void actionSearch(ActionEvent event) {
		String searchKH = txtSearchKhachHang.getText();

		if (searchKH != null) {
			kh = dao.selectByKeyword(searchKH);
			fillTable(kh);
		} else {
			kh = dao.selectAll();
			fillTable(kh);
		}
	}
	
	@FXML
    void exportFileExcel(ActionEvent event) {
		exportExcel();
    }

	public void fillTable(ArrayList<KhachHang> kh) {

		khachHangList = FXCollections.observableArrayList(kh);
		idColumn.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("maKH"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("hoTen"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("soDT"));
		
		updateColumn.setStyle("-fx-alignment: CENTER;");
		updateColumn.setCellFactory(param -> {
            TableCell<Map<String, KhachHang>, Button> cell = new TableCell<>() {
                private final Button button = new Button("Cập nhật");
                {
                	button.getStyleClass().add("btn-solid");
                	button.setPrefSize(60, 30);
                    button.setOnAction(event -> {
                    	KhachHang khachHang = (KhachHang) getTableRow().getItem();
                        if (khachHang != null) {
                        	// Fill data to tab form
                        	khData = khachHang;
                        	
                        	switchScene("CapNhatKhachHang.fxml");
                        	
                        	System.out.println();
                        }
                    });
                }
                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };
            return cell;
        });
		
		table.setItems(khachHangList);
	}
	
	private void exportExcel() {
		try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("nhân viên");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH KHÁCH HÀNG");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ và Tên");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Email");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            
            List<KhachHang> listItem = dao.selectAll();

            for (int i = 0; i < listItem.size(); i++) {
            	KhachHang khachHangExcel = listItem.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(khachHangExcel.getMaKH());
                row.createCell(1).setCellValue(khachHangExcel.getHoTen());
                row.createCell(2).setCellValue(khachHangExcel.getEmail());
                row.createCell(3).setCellValue(khachHangExcel.getSoDT() );
                                
            }

            FileOutputStream out = new FileOutputStream(new File("D:/khachHangBlueHotel.xlsx"));
            switchScene("QuanLyKhachHang.fxml");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
