package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Controller.NhanVienDAO;
import Model.NhanVien;
import javafx.beans.property.SimpleStringProperty;
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


public class NVController implements Initializable {

	@FXML
	private Button btnSearch;
	@FXML
	private Button btnSwitchThemNV;
	@FXML
	private TableView<NhanVien> table;
	@FXML
	private VBox quanLyNVContainer;
	@FXML
    private Button btnExportExcel;

	@FXML
	private TableColumn<Object, String> idColumn;
	@FXML
	private TableColumn<Object, String> nameColumn;
	@FXML
	private TableColumn<Object, String> emailColumn;
	@FXML
	private TableColumn<Object, String> phoneColumn;
	@FXML
	private TableColumn<Object, String> roleColumn;
	@FXML
    private TableColumn<Map<String, NhanVien>, Button> deleteColumn;
	@FXML
	private TextField txtSearchNhanVien;
	@FXML
	private ObservableList<NhanVien> NhanVienList;
	
	NhanVienDAO dao = new NhanVienDAO();
	ArrayList<NhanVien> nv = new ArrayList<NhanVien>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nv = dao.selectAll();	
		fillTable(nv);
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
	void SwitchAddNV(MouseEvent event) {
		switchScene("ThemNhanVien.fxml");
	}

	@FXML
	void actionSearch(ActionEvent event) {
		String searchNhanVien = txtSearchNhanVien.getText();
		
		if (searchNhanVien != null) {
			nv = dao.selectByKeyword(searchNhanVien);
			fillTable(nv);
		} else {
			nv = dao.selectAll();
			fillTable(nv);
		}
	}
	
	@FXML
    void exportFileExcel(ActionEvent event) {
		exportExcel();
    }
	
	public void fillTable(ArrayList<NhanVien> nv) {
		NhanVienList = FXCollections.observableArrayList(nv);
		idColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("maNV"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("hoTen"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("soDT"));

		roleColumn.setCellValueFactory(cellData -> {
			NhanVien nhanVien = (NhanVien) cellData.getValue();
			String roleName = (nhanVien.getVaiTro() == true) ? "Nhân viên" : "Quản lý";
			return new SimpleStringProperty(roleName);
		});	
		
		deleteColumn.setStyle("-fx-alignment: CENTER;");
		deleteColumn.setCellFactory(param -> {
            TableCell<Map<String, NhanVien>, Button> cell = new TableCell<>() {
                private final Button button = new Button("Xóa");
                {
                	button.getStyleClass().add("btn-solid");
                	button.setPrefSize(60, 30);
                    button.setOnAction(event -> {
                    	NhanVien nhanVien = (NhanVien) getTableRow().getItem();
                        if (nhanVien != null) {
                        	// Fill data to tab form
                        	String tenNhanVien = nhanVien.getHoTen();
                        	String id = nhanVien.getMaNV();
                        	dao.delete(id);
                        	NhanVienList.remove(getIndex());
                        	table.refresh();
                        	System.out.println(tenNhanVien);
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
		
		table.setItems(NhanVienList);
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
            cell.setCellValue("DANH SÁCH NHÂN VIÊN");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã Nhân Viên");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ và Tên");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Email");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Vai trò");       

            List<NhanVien> listItem = dao.selectAll();

            for (int i = 0; i < listItem.size(); i++) {
            	NhanVien nhanvien = listItem.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(nhanvien.getMaNV());
                row.createCell(1).setCellValue(nhanvien.getHoTen());
                row.createCell(2).setCellValue(nhanvien.getEmail());
                row.createCell(3).setCellValue(nhanvien.getSoDT() );
                row.createCell(4).setCellValue(nhanvien.getVaiTro());
                
            }

            FileOutputStream out = new FileOutputStream(new File("D:/nhanVienBlueHotel.xlsx"));
            switchScene("QuanLyNhanVien.fxml");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
