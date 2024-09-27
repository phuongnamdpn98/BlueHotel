package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import Controller.HoaDonDAO;
import Controller.KhachHangDAO;
import Controller.LoaiKhachHangDAO;
import Controller.LoaiPhongDAO;
import Controller.PhieuDatPhongDAO;
import Controller.PhieuThuePhongDAO;
import Controller.PhongDAO;
import Controller.SanPhamDAO;
import Model.Room;
import Model.SanPham;
import Model.KhachHangDatTruoc;
import Model.KhachHangDangThue;
import Model.KhachHang;
import Model.LoaiKhachHang;
import Model.LoaiPhong;
import Model.PhieuDatPhong;
import Model.PhieuThuePhong;
import Utils.DialogHelper;
import Utils.XDate;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class QLPController {

	@FXML
	private VBox containerModule;
	// Component show list phòng
	@FXML
	private GridPane gridPaneFloor_LP;
	@FXML
	private ScrollPane scrollPane_LP;
	@FXML
	private AnchorPane anchorPane_LP;

	// Component quản lý đặt phòng
	@FXML
	private TabPane tabPane_QLDP;
	//
	@FXML
	private TextField hoTenKH_QLDP;
	@FXML
	private TextField email_QLDP;
	@FXML
	private TextField sdt_QLDP;
	@FXML
	private TextField cccd_QLDP;
	@FXML
	private DatePicker ngaySinhKH_QLDP;
	@FXML
	private RadioButton radioNam_QLDP;
	@FXML
	private RadioButton radioNu_QLDP;
	@FXML
	private ComboBox<String> loaiKhach_QLDP;
	@FXML
	private TextField soNguoi_QLDP;
	@FXML
	private TextField soPhong_QLDP;
	@FXML
	private DatePicker ngayDat_QLDP;
	@FXML
	private DatePicker ngayDenDK_QLDP;
	@FXML
	private DatePicker ngayDiDK_QLDP;
	@FXML
	private Button btnClearForm_QLDP;
	@FXML
	private Button btnThemKH_QLDP;
	@FXML
	private Button btnXoaPhieuDat_QLDP;
	@FXML
	private Button btnThemPhieuDat_QLDP;
	@FXML
	private Pane background_QLDP;
	//
	@FXML
	private TextField searchKhachHang_QLDP;
	@FXML
	private TableView<Map<String, Object>> tableKhachHang_QLDP;
	//
	@FXML
	private TextField searchKhachDatTruoc_QLDP;
	@FXML
	private TableView<Map<String, Object>> tableKhachDatTruoc_QLDP;

	// Componet quản lý thuê phòng
	@FXML
	private TabPane tabPane_QLTP;
	@FXML
	private Pane background_QLTP;
	@FXML
	private TextField hoTenNV_QLTP;
	@FXML
	private TextField hoTenKH_QLTP;
	@FXML
	private TextField email_QLTP;
	@FXML
	private TextField sdt_QLTP;
	@FXML
	private TextField cccd_QLTP;
	@FXML
	private RadioButton radioNam_QLTP;
	@FXML
	private RadioButton radioNu_QLTP;
	@FXML
	private ComboBox<String> loaiKhach_QLTP;
	@FXML
	private TextField soPhong_QLTP;
	@FXML
	private TextField loaiPhong_QLTP;
	@FXML
	private DatePicker ngayDen_QLTP;
	@FXML
	private Button btnClearForm_QLTP;
	@FXML
	private Button btnThemKhachHang_QLTP;
	@FXML
	private Button btnThuePhong_QLTP;
	@FXML
	private DatePicker ngaySinhKH_QLTP;

	@FXML
	private TextField searchKhachHang_QLTP;
	@FXML
	private TableView<Map<String, Object>> tableKhachHang_QLTP;

	@FXML
	private TextField searchKhachDatTruoc_QLTP;
	@FXML
	private TableView<Map<String, Object>> tableKhachDatTruoc_QLTP;

	// component list khach dang thue
	@FXML
	private TextField searchKhachDangThue;
	@FXML
	private TableView<Map<String, Object>> tableKhachDangThue;

	// component CardItem
	@FXML
	private GridPane containerProducts;
	@FXML
	private TextField amount_CardItem;
	@FXML
	private Button btnMinus_CardItem;
	@FXML
	private Button btnPlus_CardItem;
	@FXML
	private ImageView img_CardItem;
	@FXML
	private Label name_CardItem;
	@FXML
	private Label price_CardItem;
	@FXML
	private Button btnThanhToanProducts;

	// component bill
	@FXML
	private Button btnQuayLai_Bill;
	@FXML
	private Text txtMaPhong_Bill;
	@FXML
	private Text txtLoaiPhong_Bill;
	@FXML
	private Text txtTenPhong_Bill;
	@FXML
	private Text txtTenKH_Bill;
	@FXML
	private Text txtNgaySinhKH_Bill;
	@FXML
	private Text txtSdtKH_Bill;
	@FXML
	private Text txtNgayDen_Bill;
	@FXML
	private Text txtNgayDi_Bill;
	@FXML
	private TableView<Map<String, Object>> tableProducts_Bill;
	@FXML
	private Text txtTongTienSP_Bill;
	@FXML
	private Text txtTienPhong_Bill;
	@FXML
	private Text txtThanhTienTop_Bill;
	@FXML
	private Text txtThanhTienBottom_Bill;
	@FXML
	private Button btnThanhToan_Bill;

	private PhieuThuePhongDAO ptpDao = new PhieuThuePhongDAO();
	private PhieuDatPhongDAO pdpDao = new PhieuDatPhongDAO();
	private KhachHangDAO khDao = new KhachHangDAO();
	private LoaiKhachHangDAO lkhDao = new LoaiKhachHangDAO();
	private LoaiPhongDAO lpDao = new LoaiPhongDAO();
	private PhongDAO pDao = new PhongDAO();
	private SanPhamDAO spDao = new SanPhamDAO();

	private boolean preventInitLoop = false;
	private int currentFloor = 1; // Holding current floor when switch between components
	private int idMaPhieuSelected = -1;
	private int idCustomerSelected = -1;
	private boolean isRoomSelectedEmpty = false;

	private ArrayList<Room> listRoom = new ArrayList<>();
	private ArrayList<LoaiKhachHang> loaiKhach = new ArrayList<>();
	private ArrayList<LoaiPhong> loaiPhong = new ArrayList<>();
	private ArrayList<KhachHang> listKhachHang = new ArrayList<>();
	private ObservableList<KhachHangDatTruoc> listKhachDatTruoc = FXCollections.observableArrayList();
	private ObservableList<KhachHangDangThue> listKhachDangThue = FXCollections.observableArrayList();
	private ArrayList<SanPham> listSanPham = new ArrayList<>();

	public void initialize() {
		if (!preventInitLoop) {
			firstSetUp();
		}
	}

	// Phương thức để thực hiện truy vấn cơ sở dữ liệu
	private void fetchRoom() {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				loaiPhong = lpDao.selectAll();
				listRoom = pDao.selectAll();
				return null;
			}
		};
		task.setOnSucceeded(event -> {
			fetchKhachHang();
			showRoom(getRoomsByFloor(currentFloor));
		});
		task.setOnFailed(event -> {
			Throwable exception = task.getException();
			exception.printStackTrace();
		});
		// Chạy tác vụ trên một luồng nền
		new Thread(task).start();
	}

	private void fetchKhachHang() {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				listKhachHang = khDao.selectAll();
				return null;
			}
		};
		task.setOnSucceeded(event -> {
			fetchKhachDatTruoc();
			fetchKhachDangThue();
		});
		task.setOnFailed(event -> {
			Throwable exception = task.getException();
			exception.printStackTrace();
		});
		// Chạy tác vụ trên một luồng nền
		new Thread(task).start();

	}

	private void fetchKhachDatTruoc() {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				listKhachDatTruoc.clear();
				ArrayList<PhieuDatPhong> listPDP = pdpDao.selectAll();
				for (PhieuDatPhong pdp : listPDP) {
					// find a KhachHang from listKhachHang equals to pdp. yep!!!
					KhachHang kh = listKhachHang.stream().filter(k -> k.getMaKH() == pdp.getMaKH()).findFirst()
							.orElse(null);
					if (kh != null) {
						KhachHangDatTruoc khdt = new KhachHangDatTruoc(pdp.getMaPhieu(), kh.getMaKH(), kh.getHoTen(),
								kh.getNgaySinh(), kh.getGioiTinh(), kh.getEmail(), kh.getSoDT(), kh.getCccd(),
								kh.getMaLoaiKH(), pdp.getNgayDat(), pdp.getNgayDenDK(), pdp.getNgayDiDK(),
								pdp.getSoLuongPhong(), pdp.getSoNguoi());
						listKhachDatTruoc.add(khdt);
					}
				}
				return null;
			}
		};
		task.setOnSucceeded(event -> {
			try {
				fillToTableBooked(tableKhachDatTruoc_QLDP, listKhachDatTruoc);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				fillToTableBooked(tableKhachDatTruoc_QLTP, listKhachDatTruoc);
			} catch (Exception e) {
				// TODO: handle exception
			}
		});
		task.setOnFailed(event -> {
			Throwable exception = task.getException();
			exception.printStackTrace();
		});
		new Thread(task).start();
	}

	private void fetchKhachDangThue() {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				listKhachDangThue.clear();
				ArrayList<PhieuThuePhong> listPTP = ptpDao.selectAll();
				int maLP = -1;
				for (PhieuThuePhong ptp : listPTP) {
					String tenPhong = "";
					boolean isFound = false;
					for (Room r : listRoom) {
						if (isFound) {
							break;
						}
						if (r.getMaPhong().equals(ptp.getMaPhong())) {
							for (LoaiPhong lp : loaiPhong) {
								if (lp.getMaLoaiPhong() == r.getMaLP()) {
									maLP = r.getMaLP();
									tenPhong = r.getTenPhong();
									isFound = true;
									break;
								}
							}
						}
					}
					
					if (ptp.getTrangThai() == 1) {
						KhachHang kh = listKhachHang.stream().filter(k -> k.getMaKH() == ptp.getMaKH()).findFirst()
								.orElse(null);
						if (kh != null) {
							int gia = -1;
							for (LoaiPhong lp : loaiPhong) {
								if (lp.getMaLoaiPhong() == maLP) {
									gia = lp.getGia();
								}
							}
							KhachHangDangThue khdt = new KhachHangDangThue(ptp.getMaPhieuThuePhong(), kh.getMaKH(),
									kh.getHoTen(), kh.getNgaySinh(), kh.getGioiTinh(), kh.getEmail(), kh.getSoDT(),
									kh.getCccd(), kh.getMaLoaiKH(), ptp.getNgayDen(), ptp.getMaPhong(), tenPhong, maLP,
									gia);
							listKhachDangThue.add(khdt);
						}
					}
				}
				return null;
			}
		};
		task.setOnSucceeded(event -> {
			fillToTableUsingService(tableKhachDangThue, listKhachDangThue);
		});
		task.setOnFailed(event -> {
			Throwable exception = task.getException();
			exception.printStackTrace();
		});
		new Thread(task).start();
	}

	private void fetchSanPham() {
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				listSanPham = spDao.selectAll();
				return null;
			}
		};
		new Thread(task).start();
	}

	

	public void firstSetUp() {
		loadChild("/components/QLP_ListPhong.fxml");
		setupEventBtnFloors();
		fetchRoom();
		new Thread(new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				loaiKhach = lkhDao.selectAll();
				return null;
			}
		}).start();
	}

	public void loadChild(String filename) {
		containerModule.getChildren().clear();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
			preventInitLoop = true;
			loader.setController(this);
			Parent content = loader.load();
			VBox vbox = (VBox) content;
			vbox.prefWidthProperty().bind(containerModule.widthProperty());
			vbox.prefHeightProperty().bind(containerModule.heightProperty());
			containerModule.getChildren().setAll(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void switchToPhong() {
		loadChild("/components/QLP_ListPhong.fxml");
		setupEventBtnFloors();
		fetchRoom();
	}

	private void resetGlobalVariable() {
		idCustomerSelected = -1;
		idMaPhieuSelected = -1;
	}

	@FXML
	private void switchToQuanLyDatPhong() {
		loadChild("/components/QLP_QuanLyDatPhong.fxml");
		// form
		loaiKhach.forEach(lk -> loaiKhach_QLDP.getItems().add(lk.getTenLoaiKH()));
		loaiKhach_QLDP.setValue(loaiKhach.get(0).getTenLoaiKH());
		ngayDat_QLDP.setValue(LocalDate.now());
		background_QLDP.getStyleClass().add("background-img");
		btnClearForm_QLDP.setOnMouseClicked(event -> {
			clearFormPhieuDatPhong();
		});

		btnThemKH_QLDP.setOnMouseClicked(event -> {
			int idLK = -1;
			for (LoaiKhachHang lk : loaiKhach) {
				if (lk.getTenLoaiKH().equals(loaiKhach_QLDP.getValue())) {
					idLK = lk.getMaLoaiKH();
				}
			}
			LocalDate ngaySinh = ngaySinhKH_QLDP.getValue();
			KhachHang kh = new KhachHang(hoTenKH_QLDP.getText(), Date.valueOf(ngaySinh),
					radioNam_QLDP.isSelected() ? true : false, email_QLDP.getText(), sdt_QLDP.getText(),
					cccd_QLDP.getText(), idLK);
			if (khDao.insert(kh)) {
				DialogHelper.informAlert("Thêm khách hàng thành công", null);
				clearFormPhieuDatPhong();
				fetchKhachHang();
			} else {
				DialogHelper.errorAlert("Thêm khách hàng thất bại", null);
			}
		});
		btnXoaPhieuDat_QLDP.setOnMouseClicked(event -> {
			if (pdpDao.delete(String.valueOf(idMaPhieuSelected))) {
				DialogHelper.informAlert("Xóa phiếu đặt thành công", null);
				clearFormPhieuDatPhong();
				fetchKhachHang();
			} else {
				DialogHelper.informAlert("Xóa phiếu đặt thất bại", null);
			}
		});
		btnThemPhieuDat_QLDP.setOnMouseClicked(event -> {
			PhieuDatPhongDAO pdpDao = new PhieuDatPhongDAO();
			PhieuDatPhong pdp = new PhieuDatPhong("hoaitrang", idCustomerSelected, Date.valueOf(LocalDate.now()),
					Date.valueOf(ngayDenDK_QLDP.getValue()), Date.valueOf(ngayDiDK_QLDP.getValue()),
					Integer.parseInt(soNguoi_QLDP.getText()), Integer.parseInt(soPhong_QLDP.getText()));
			if (pdpDao.insert(pdp)) {
				DialogHelper.informAlert("Thêm phiếu đặt thành công", null);
				fetchKhachHang();
			} else {
				DialogHelper.errorAlert("Thêm phiếu đặt thất bại", null);
			}
		});

		// Setup table khách hàng
		configTableCustomer(tableKhachHang_QLDP);
		TableColumn<Map<String, Object>, Button> colBtnCustomer = new TableColumn<>();
		colBtnCustomer.setMaxWidth(90);
		colBtnCustomer.setStyle("-fx-alignment: CENTER;");
		colBtnCustomer.setCellFactory(param -> {
			TableCell<Map<String, Object>, Button> cell = new TableCell<>() {
				private final Button button = new Button("Chọn");
				{
					button.getStyleClass().add("btn-solid");
					button.setPrefSize(60, 30);
					button.setOnAction(event -> {
						Map<String, Object> rowData = getTableRow().getItem();
						if (rowData != null) {
							hoTenKH_QLDP.setText((String) rowData.get("hoTen"));
							email_QLDP.setText((String) rowData.get("email"));
							sdt_QLDP.setText((String) rowData.get("sdt"));
							cccd_QLDP.setText((String) rowData.get("cccd"));
							Date ngaySinh = (Date) rowData.get("ngaySinh");
							ngaySinhKH_QLDP.setValue(ngaySinh.toLocalDate());
							radioNam_QLDP.setSelected((boolean) rowData.get("gioiTinh"));
							radioNu_QLDP.setSelected(!(boolean) rowData.get("gioiTinh"));
							for (LoaiKhachHang lk : loaiKhach) {
								if (lk.getMaLoaiKH() == (int) rowData.get("maLK")) {
									loaiKhach_QLDP.setValue(lk.getTenLoaiKH());
									break;
								}
							}
							ngayDenDK_QLDP.setValue(null);
							ngayDiDK_QLDP.setValue(null);
							idCustomerSelected = (int) rowData.get("maKH");
							btnXoaPhieuDat_QLDP.setDisable(true);
							tabPane_QLDP.getSelectionModel().select(0);
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
		tableKhachHang_QLDP.getColumns().add(colBtnCustomer);
		tableKhachHang_QLDP.getColumns().forEach(column -> {
			column.setReorderable(false);
		});
		fillToTableCustomer(tableKhachHang_QLDP, listKhachHang);
		PauseTransition pauseSearchKH = new PauseTransition(Duration.seconds(0.5));
		pauseSearchKH.setOnFinished(event -> {
			String searchText = searchKhachHang_QLDP.getText();
			if (searchText.equals("")) {
				fillToTableCustomer(tableKhachHang_QLDP, listKhachHang);
			} else {
				fillToTableCustomer(tableKhachHang_QLDP, timKhachHang(searchText));
			}
			System.out.println("Tìm kiếm với từ khóa: " + searchText);
		});
		// Delete PauseTransition when typing
		searchKhachHang_QLDP.setOnKeyReleased(event -> {
			pauseSearchKH.stop();
			pauseSearchKH.playFromStart();
		});

		// Setup table khách đặt trước
		configTableBooked(tableKhachDatTruoc_QLDP);
		TableColumn<Map<String, Object>, Button> colBtnBooked = new TableColumn<>();
		colBtnBooked.setMaxWidth(90);
		colBtnBooked.setStyle("-fx-alignment: CENTER;");
		colBtnBooked.setCellFactory(param -> {
			TableCell<Map<String, Object>, Button> cell = new TableCell<>() {
				private final Button button = new Button("Chọn");
				{
					button.getStyleClass().add("btn-solid");
					button.setPrefSize(60, 30);
					button.setOnAction(event -> {
						Map<String, Object> rowData = getTableRow().getItem();
						if (rowData != null) {
							hoTenKH_QLDP.setText((String) rowData.get("hoTen"));
							email_QLDP.setText((String) rowData.get("email"));
							sdt_QLDP.setText((String) rowData.get("sdt"));
							cccd_QLDP.setText((String) rowData.get("cccd"));
							Date ngaySinh = (Date) rowData.get("ngaySinh");
							ngaySinhKH_QLDP.setValue(ngaySinh.toLocalDate());
							radioNam_QLDP.setSelected((boolean) rowData.get("gioiTinh"));
							radioNu_QLDP.setSelected(!(boolean) rowData.get("gioiTinh"));
							for (LoaiKhachHang lk : loaiKhach) {
								if (lk.getMaLoaiKH() == (int) rowData.get("maLK")) {
									loaiKhach_QLDP.setValue(lk.getTenLoaiKH());
									break;
								}
							}
							Date ngayDat = (Date) rowData.get("ngayDat");
							ngayDat_QLDP.setValue(ngayDat.toLocalDate());
							Date ngayDenDK = (Date) rowData.get("ngayDenDK");
							ngayDenDK_QLDP.setValue(ngayDenDK.toLocalDate());
							Date ngayDiDK = (Date) rowData.get("ngayDiDK");
							ngayDiDK_QLDP.setValue(ngayDiDK.toLocalDate());
							idCustomerSelected = (int) rowData.get("maKH");
							idMaPhieuSelected = (int) rowData.get("maPhieu");
							btnThemKH_QLDP.setDisable(true);
							btnXoaPhieuDat_QLDP.setDisable(false);
							btnThemPhieuDat_QLDP.setDisable(true);
							tabPane_QLDP.getSelectionModel().select(0);
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
		tableKhachDatTruoc_QLDP.getColumns().add(colBtnBooked);
		tableKhachDatTruoc_QLDP.getColumns().forEach(column -> {
			column.setReorderable(false);
		});
		fillToTableBooked(tableKhachDatTruoc_QLDP, listKhachDatTruoc);
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(event -> {
			String searchText = searchKhachDatTruoc_QLDP.getText();
			if (searchText.equals("")) {
				fillToTableBooked(tableKhachDatTruoc_QLDP, listKhachDatTruoc);
			} else {
				ObservableList<KhachHangDatTruoc> khdtFound = timKhachHangDatTruoc(searchText);
				fillToTableBooked(tableKhachDatTruoc_QLDP, khdtFound);
			}
			System.out.println("Tìm kiếm với từ khóa: " + searchText);
		});
		// Delete PauseTransition when typing
		searchKhachDatTruoc_QLDP.setOnKeyReleased(event -> {
			pause.stop();
			pause.playFromStart();
		});

	}

	private void clearFormPhieuDatPhong() {
		resetGlobalVariable();
		hoTenKH_QLDP.setText("");
		sdt_QLDP.setText("");
		email_QLDP.setText("");
		cccd_QLDP.setText("");
		ngaySinhKH_QLDP.setValue(null);
		radioNam_QLDP.setSelected(true);
		radioNu_QLDP.setSelected(false);
		loaiKhach_QLDP.setValue(loaiKhach.get(0).getTenLoaiKH());
		soNguoi_QLDP.setText("1");
		soPhong_QLDP.setText("1");
		ngayDat_QLDP.setValue(LocalDate.now());
		ngayDenDK_QLDP.setValue(null);
		ngayDiDK_QLDP.setValue(null);
		btnThemKH_QLDP.setDisable(false);
		btnThemPhieuDat_QLDP.setDisable(false);
	}

	private void swtichToQuanLyThuePhong(Room r) {
		loadChild("/components/QLP_QuanLyThuePhong.fxml");
		hoTenNV_QLTP.setText("Tran Minh Phuc"); // nhớ set Auth cho họ tên nhân viên sau khi login !!!
		loaiKhach.forEach(lk -> loaiKhach_QLTP.getItems().add(lk.getTenLoaiKH()));
		loaiKhach_QLTP.setValue(loaiKhach.get(0).getTenLoaiKH());
		soPhong_QLTP.setText(r.getMaPhong());
		for (LoaiPhong lp : loaiPhong) {
			if (lp.getMaLoaiPhong() == r.getMaLP()) {
				loaiPhong_QLTP.setText(lp.getTenLoaiPhong());
				break;
			}
		}
		ngayDen_QLTP.setValue(LocalDate.now());

		btnClearForm_QLTP.setOnMouseClicked(event -> {
			clearFormQLTP();
		});

		btnThemKhachHang_QLTP.setOnMouseClicked(event -> {
			int idLK = -1;
			for (LoaiKhachHang lk : loaiKhach) {
				if (lk.getTenLoaiKH().equals(loaiKhach_QLTP.getValue())) {
					idLK = lk.getMaLoaiKH();
				}
			}
			LocalDate ngaySinh = ngaySinhKH_QLTP.getValue();
			KhachHang kh = new KhachHang(hoTenKH_QLTP.getText(), Date.valueOf(ngaySinh),
					radioNam_QLTP.isSelected() ? true : false, email_QLTP.getText(), sdt_QLTP.getText(),
					cccd_QLTP.getText(), idLK);
			if (khDao.insert(kh)) {
				DialogHelper.informAlert("Thêm khách hàng thành công", null);
				clearFormQLTP();
				fetchKhachHang();
			} else {
				DialogHelper.errorAlert("Thêm khách hàng thất bại", null);
			}
		});

		btnThuePhong_QLTP.setOnMouseClicked(event -> { // nhớ set Auth mã nhân viên
			PhieuThuePhong ptp = new PhieuThuePhong("hoaitrang", idCustomerSelected, soPhong_QLTP.getText(),
					Date.valueOf(LocalDate.now()), 1);
			if (isRoomSelectedEmpty == false) { // nếu phòng được chọn không còn trống
				DialogHelper.informAlert("Thuê phòng thất bại. Phòng " + soPhong_QLTP.getText() + " đã có người thuê!",
						null);
				return;
			}
			if (ptpDao.insert(ptp)) {
				if (pdpDao.delete(String.valueOf(idMaPhieuSelected))) {
					pDao.update(r.getMaPhong(), 1);
					fetchKhachHang();
					resetGlobalVariable();
					isRoomSelectedEmpty = false; // set về false -> phòng được chọn đã được thuê
					DialogHelper.informAlert("Thuê phòng " + soPhong_QLTP.getText() + " thành công!", null);
				} else {
					DialogHelper.errorAlert("Xóa phiếu thuê " + idMaPhieuSelected + " thất bại!", null);
				}
			} else {
				DialogHelper.errorAlert("Thuê phòng " + soPhong_QLTP.getText() + " thất bại!", null);
			}
		});

		// Setup table khách hàng
		configTableCustomer(tableKhachHang_QLTP);
		TableColumn<Map<String, Object>, Button> colBtnCustomer = new TableColumn<>();
		colBtnCustomer.setMaxWidth(90);
		colBtnCustomer.setStyle("-fx-alignment: CENTER;");
		colBtnCustomer.setCellFactory(param -> {
			TableCell<Map<String, Object>, Button> cell = new TableCell<>() {
				private final Button button = new Button("Chọn");
				{
					button.getStyleClass().add("btn-solid");
					button.setPrefSize(60, 30);
					button.setOnAction(event -> {
						Map<String, Object> rowData = getTableRow().getItem();
						if (rowData != null) {
							hoTenKH_QLTP.setText((String) rowData.get("hoTen"));
							email_QLTP.setText((String) rowData.get("email"));
							sdt_QLTP.setText((String) rowData.get("sdt"));
							cccd_QLTP.setText((String) rowData.get("cccd"));
							radioNam_QLTP.setSelected((boolean) rowData.get("gioiTinh"));
							radioNu_QLTP.setSelected(!(boolean) rowData.get("gioiTinh"));
							for (LoaiKhachHang lk : loaiKhach) {
								if (lk.getMaLoaiKH() == (int) rowData.get("maLK")) {
									loaiKhach_QLTP.setValue(lk.getTenLoaiKH());
									break;
								}
							}
							idCustomerSelected = (int) rowData.get("maKH");
							btnThemKhachHang_QLTP.setDisable(true);
							tabPane_QLTP.getSelectionModel().select(0);
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
		tableKhachHang_QLTP.getColumns().add(colBtnCustomer);
		tableKhachHang_QLTP.getColumns().forEach(column -> {
			column.setReorderable(false);
		});
		fillToTableCustomer(tableKhachHang_QLTP, listKhachHang);
		PauseTransition pauseSearchKH = new PauseTransition(Duration.seconds(0.5));
		pauseSearchKH.setOnFinished(event -> {
			String searchText = searchKhachHang_QLTP.getText();
			if (searchText.equals("")) {
				fillToTableCustomer(tableKhachHang_QLTP, listKhachHang);
			} else {
				ArrayList<KhachHang> khdtFound = timKhachHang(searchText);
				fillToTableCustomer(tableKhachHang_QLTP, khdtFound);
			}
			System.out.println("Tìm kiếm với từ khóa: " + searchText);
		});
		// Delete PauseTransition when typing
		searchKhachHang_QLTP.setOnKeyReleased(event -> {
			pauseSearchKH.stop();
			pauseSearchKH.playFromStart();
		});

		// setup table khách đặt trước
		configTableBooked(tableKhachDatTruoc_QLTP);
		// Configure specific column and add event
		TableColumn<Map<String, Object>, Button> colButton = new TableColumn<>();
		colButton.setMaxWidth(90);
		colButton.setStyle("-fx-alignment: CENTER;");
		colButton.setCellFactory(param -> {
			TableCell<Map<String, Object>, Button> cell = new TableCell<>() {

				private final Button button = new Button("Chọn");
				{
					button.getStyleClass().add("btn-solid");
					button.setPrefSize(60, 30);
					button.setOnAction(event -> {
						Map<String, Object> rowData = getTableRow().getItem();
						if (rowData != null) {
							hoTenKH_QLTP.setText((String) rowData.get("hoTen"));
							email_QLTP.setText((String) rowData.get("email"));
							sdt_QLTP.setText((String) rowData.get("sdt"));
							cccd_QLTP.setText((String) rowData.get("cccd"));
							radioNam_QLTP.setSelected((boolean) rowData.get("gioiTinh"));
							radioNu_QLTP.setSelected(!(boolean) rowData.get("gioiTinh"));
							for (LoaiKhachHang lk : loaiKhach) {
								if (lk.getMaLoaiKH() == (int) rowData.get("maLK")) {
									loaiKhach_QLTP.setValue(lk.getTenLoaiKH());
									break;
								}
							}
							idMaPhieuSelected = (int) rowData.get("maPhieu");
							idCustomerSelected = (int) rowData.get("maKH");
							btnThemKhachHang_QLTP.setDisable(true);
							tabPane_QLTP.getSelectionModel().select(0);
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
		tableKhachDatTruoc_QLTP.getColumns().add(colButton);
		tableKhachDatTruoc_QLTP.getColumns().forEach(column -> {
			column.setReorderable(false);
		});
		fillToTableBooked(tableKhachDatTruoc_QLTP, listKhachDatTruoc);

		PauseTransition pause2 = new PauseTransition(Duration.seconds(0.5));
		pause2.setOnFinished(event -> {
			// Action after PauseTransition wait done
			String searchText = searchKhachDatTruoc_QLTP.getText();
			if (searchText.equals("")) {
				fillToTableBooked(tableKhachDatTruoc_QLTP, listKhachDatTruoc);
			} else {
				ObservableList<KhachHangDatTruoc> customerFound = timKhachHangDatTruoc(searchText);
				fillToTableBooked(tableKhachDatTruoc_QLTP, customerFound);
			}
			System.out.println("Tìm kiếm với từ khóa: " + searchText);
		});
		// Delete PauseTransition when typing
		searchKhachDatTruoc_QLTP.setOnKeyReleased(event -> {
			pause2.stop();
			pause2.playFromStart();
		});
		background_QLTP.getStyleClass().add("background-img");
	}

	private void clearFormQLTP() {
		resetGlobalVariable();
		hoTenKH_QLTP.setText("");
		email_QLTP.setText("");
		sdt_QLTP.setText("");
		cccd_QLTP.setText("");
		radioNam_QLTP.setSelected(true);
		radioNu_QLTP.setSelected(false);
		loaiKhach_QLTP.setValue(loaiKhach.get(0).getTenLoaiKH());
		btnThemKhachHang_QLTP.setDisable(false);
	}

	@FXML
	private void switchToListKhachDangThue() {
		loadChild("/components/QLP_ListKhachDangThue.fxml");
		// setup table khách đang thuê
		configTableUsingService(tableKhachDangThue);
		// Configure specific column and add event
		TableColumn<Map<String, Object>, Button> colButtonUsing = new TableColumn<>();
		colButtonUsing.setMaxWidth(120);
		colButtonUsing.setStyle("-fx-alignment: CENTER;");
		colButtonUsing.setCellFactory(param -> {
			TableCell<Map<String, Object>, Button> cell = new TableCell<>() {
				private final Button button = new Button("Trả phòng");
				{
					button.getStyleClass().add("btn-solid");
					button.setPrefSize(80, 30);
					button.setOnAction(event -> {
						Map<String, Object> rowData = getTableRow().getItem();
						if (rowData != null) {
							switchToChooseProducts((String) rowData.get("maPhong"));
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
		tableKhachDangThue.getColumns().add(colButtonUsing);
		tableKhachDangThue.getColumns().forEach(column -> {
			column.setReorderable(false);
		});
		fillToTableUsingService(tableKhachDangThue, listKhachDangThue);
		PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
		pause1.setOnFinished(event -> {
			String searchText = searchKhachDangThue.getText();
			if (searchText.equals("")) {
				fillToTableUsingService(tableKhachDangThue, listKhachDangThue);
			} else {
				ObservableList<KhachHangDangThue> khdtFound = timKhachHangDangThue(searchText);
				fillToTableUsingService(tableKhachDangThue, khdtFound);
			}
			System.out.println("Tìm kiếm với từ khóa: " + searchText);
		});
		// Delete PauseTransition when typing
		searchKhachDangThue.setOnKeyReleased(event -> {
			pause1.stop();
			pause1.playFromStart();
		});
	}

	private void switchToChooseProducts(String maPhong) {
		fetchSanPham();
		loadChild("/components/QLP_Products.fxml");
		String c = "";
		int i = 0;
		for (Node item : containerProducts.getChildren()) {
			final String s = c;
			final int index = i;
			VBox vbox = (VBox) item;
			((ImageView) vbox.lookup("#img_CardItem" + s))
					.setImage(new Image("/imgs/" + listSanPham.get(index).getHinh()));
			((Label) vbox.lookup("#name_CardItem" + s)).setText(listSanPham.get(index).getTenSP());
			String price = formatCurrencyVND(listSanPham.get(index).getGia());
			((Label) vbox.lookup("#price_CardItem" + s)).setText(price);
			TextField txt = (TextField) vbox.lookup("#amount_CardItem" + s);
			((Button) vbox.lookup("#btnMinus_CardItem" + s)).setOnMouseClicked(event -> {
				int result = Integer.parseInt(txt.getText()) - 1;
				if (result >= 0) {
					txt.setText(String.valueOf(result));
				}
			});
			((Button) vbox.lookup("#btnPlus_CardItem" + s)).setOnMouseClicked(event -> {
				int result = Integer.parseInt(txt.getText()) + 1;
				txt.setText(String.valueOf(result));
			});
			c = c + "1";
			i++;
		}

		ArrayList<String> pickedProducts = new ArrayList<>();
		ArrayList<String> amountMapped = new ArrayList<>();
		ArrayList<SanPham> picked = new ArrayList<>();
		btnThanhToanProducts.setOnMouseClicked(event -> {
			pickedProducts.clear();
			amountMapped.clear();
			String a = "";
			int index = 0;
			for (Node item : containerProducts.getChildren()) {
				VBox vbox = (VBox) item;
				TextField txt = (TextField) vbox.lookup("#amount_CardItem" + a);
				int quantity = Integer.parseInt(txt.getText());
				if (quantity > 0) {
					pickedProducts.add(listSanPham.get(index).getMaSP());
					amountMapped.add(String.valueOf(quantity));
					listSanPham.get(index).setSoLuong(quantity);
					picked.add(listSanPham.get(index));
				}
				a = a + "1";
				index++;
			}

			KhachHangDangThue kh = listKhachDangThue.stream().filter(k -> k.getMaPhong().equals(maPhong)).findFirst()
					.orElse(null);
			if (kh != null) {
				System.out.println("Mã phiếu thuê phòng: " + kh.getMaPTP());
				System.out.println("Mã khách hàng cần thanh toán: " + kh.getMaKH());
				System.out.println("Mã phòng cần trả: " + kh.getMaPhong());

				switchToBill(kh, maPhong, pickedProducts, amountMapped, picked, kh.getMaPTP());
			} else {
				DialogHelper.errorAlert("Đã xảy ra sự cố. Hãy thử lại", null);
			}

		});
	}

	private void switchToBill(KhachHangDangThue kh, String maPhong, ArrayList<String> pickedProducts,
			ArrayList<String> amountMapped, ArrayList<SanPham> picked, int maPTP) {
		String strPickedProducts = String.join(",", pickedProducts);
		String strAmountMapped = String.join(",", amountMapped);
		Date sqlDate = Date.valueOf(LocalDate.now());
		Date otherSqlDate = Date.valueOf(XDate.toString(kh.getNgayDen(), "yyyy-MM-dd"));
		// Chuyển đổi java.sql.Date sang java.time.LocalDate
		LocalDate currentDate = sqlDate.toLocalDate();
		LocalDate otherDate = otherSqlDate.toLocalDate();
		// Tính sự chênh lệch giữa các ngày
		long daysDifference = ChronoUnit.DAYS.between(otherDate, currentDate);

		loadChild("/components/QLP_Bill.fxml");
		btnQuayLai_Bill.setOnMouseClicked(event -> {
			switchToChooseProducts(maPhong);
		});
		txtMaPhong_Bill.setText(maPhong);
		txtTenPhong_Bill.setText(kh.getTenPhong());
		txtLoaiPhong_Bill
				.setText((loaiPhong.stream().filter(lp -> lp.getMaLoaiPhong() == kh.getMaLP()).findFirst().orElse(null))
						.getTenLoaiPhong());
		txtTenKH_Bill.setText(kh.getHoTen());
		txtNgaySinhKH_Bill.setText(XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
		txtSdtKH_Bill.setText(kh.getSoDT());
		txtNgayDen_Bill.setText(XDate.toString(kh.getNgayDen(), "dd-MM-yyyy"));
		txtNgayDi_Bill.setText(XDate.toString(Date.valueOf(LocalDate.now()), "dd-MM-yyyy"));

		double tongTienSP = (double) picked.stream().map(p -> p.getTotal()).reduce(0, Integer::sum);
		double tienPhong = (int) daysDifference * kh.getGia();
		double total = tienPhong + tienPhong;
		txtTongTienSP_Bill.setText(formatCurrencyVND(tongTienSP));
		txtTienPhong_Bill.setText(formatCurrencyVND(tienPhong));
		txtThanhTienTop_Bill.setText(formatCurrencyVND(total));
		txtThanhTienBottom_Bill.setText(formatCurrencyVND(total));

		configTableListProducts(tableProducts_Bill);
		fillToTableListProducts(tableProducts_Bill, picked);
		System.out.println("maPTP: " + maPTP);
		System.out.println("sql Date: " + sqlDate);
		System.out.println("strPickedProducts: " + strPickedProducts);
		System.out.println("strAmountMapped: " + strAmountMapped);

		btnThanhToan_Bill.setOnMouseClicked(e -> {
			HoaDonDAO daohd = new HoaDonDAO();
			try {
				pDao.update(maPhong, 0);
				ptpDao.updateTrangThai(maPTP, 0);
				fetchRoom();
				daohd.InsertHoaDonAndChiTietHoaDon("admin", maPTP, sqlDate, strPickedProducts, strAmountMapped);
				DialogHelper.informAlert("Thanh toán thành công", "");
				loadChild("/components/QLP_ListPhong.fxml");
				fetchRoom();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public ArrayList<Room> getRoomsByFloor(int floor) {
		currentFloor = floor;
		ArrayList<Room> room = new ArrayList<>();
		for (Room r : listRoom) {
			int dbFloor = Character.getNumericValue(r.getMaPhong().charAt(1));
			if (dbFloor == floor) {
				room.add(r);
			}
		}
		return room;
	}

	public void setupEventBtnFloors() {
		int i = 1;
		for (Node btn : gridPaneFloor_LP.getChildren()) {
			final int index = i;
			if (currentFloor == index) {
				btn.getStyleClass().remove("btn-empty");
				btn.getStyleClass().add("btn-fill");
			}
			btn.setOnMouseClicked(event -> {
				for (Node btnsm : gridPaneFloor_LP.getChildren()) {
					if (btnsm.getStyleClass().contains("btn-fill")) {
						btnsm.getStyleClass().remove("btn-fill");
						btnsm.getStyleClass().add("btn-empty");
					}
				}
				btn.getStyleClass().remove("btn-empty");
				btn.getStyleClass().add("btn-fill");
				showRoom(getRoomsByFloor(index));
			});
			i++;
		}
	}

	int gapH = 20;
	int gapV = 20;

	public void showRoom(ArrayList<Room> lr) {
		anchorPane_LP.getChildren().clear();
		// Configure grid pane
		GridPane gridPane = new GridPane();
		gridPane.setHgap(gapH);
		gridPane.setVgap(gapV);
		// Binding
		anchorPane_LP.minWidthProperty().bind(scrollPane_LP.widthProperty().subtract(gapH));
		gridPane.minWidthProperty().bind(anchorPane_LP.widthProperty());
		// Set up number row, column for grid
		int numCols = 4; // Number column
		int numRows = (int) (Math.ceil(lr.size() / (double) numCols)); // Number row
		int max = Math.max(numCols, numRows);
		for (int i = 0; i < max; i++) {
			if (i < numCols) {
				ColumnConstraints colConst = new ColumnConstraints();
				colConst.setPercentWidth(100.0 / numCols);
				gridPane.getColumnConstraints().add(colConst);
			}
			if (i < numRows) {
				RowConstraints rowConst = new RowConstraints();
				rowConst.setPercentHeight(100.0 / numRows);
				rowConst.setPrefHeight(140); // Set height for each row
				gridPane.getRowConstraints().add(rowConst);
			}
		}
		// Render each room into each cell in grid pane
		int posRow = 0;
		int posCol = 0;
		for (Room r : lr) {
			Pane pane = new Pane();
			pane.getStyleClass().add("room");
			pane.getStyleClass().add(r.getTrangThai() == 0 ? "room--empty" : "room--busy");

			String txt = "ID phòng: " + String.valueOf(r.getMaPhong());
			String typeRoom = "";
			for (LoaiPhong lp : loaiPhong) {
				if (lp.getMaLoaiPhong() == r.getMaLP()) {
					typeRoom = "Loại phòng: " + lp.getTenLoaiPhong();
					break;
				}
			}

			VBox vbox1 = new VBox();
			vbox1.prefWidthProperty().bind(pane.widthProperty());
			vbox1.prefHeightProperty().bind(pane.heightProperty());
			vbox1.setAlignment(Pos.CENTER);
			vbox1.setSpacing(2);
			Text label1 = new Text(txt);
			label1.getStyleClass().add("txt-room");
			label1.setStyle("-fx-font-weight: bold");
			Text labelTypeRoom = new Text(typeRoom);
			labelTypeRoom.getStyleClass().add("txt-room");
			vbox1.getChildren().addAll(label1, labelTypeRoom);

			VBox vbox2 = new VBox();
			vbox2.prefWidthProperty().bind(pane.widthProperty());
			vbox2.prefHeightProperty().bind(pane.heightProperty());
			vbox2.setAlignment(Pos.CENTER);
			vbox2.setSpacing(5);
			vbox2.getStyleClass().add("vbox2");
			Text label2 = new Text(txt);
			label2.setFill(Color.WHITE);
			label2.setStyle("-fx-font-weight: bold");
			Text labelTypeRoom2 = new Text(typeRoom);
			labelTypeRoom2.setTranslateY(-3);
			labelTypeRoom2.setFill(Color.WHITE);
			Button btnAction;
			if (r.getTrangThai() == 0) {
				btnAction = new Button("Thuê phòng");
				btnAction.setOnMouseClicked(event -> {
					isRoomSelectedEmpty = r.getTrangThai() == 0 ? true : false;
					swtichToQuanLyThuePhong(r);
				});
			} else {
				btnAction = new Button("Trả phòng");
				btnAction.setOnMouseClicked(event -> {
					switchToChooseProducts(r.getMaPhong());
				});
			}
			btnAction.getStyleClass().add("btn-solid");
			btnAction.setPrefSize(100, 32);
			vbox2.getChildren().addAll(label2, labelTypeRoom2, btnAction);

			pane.getChildren().addAll(vbox1, vbox2);
			if (posCol >= numCols) {
				posCol = 0;
				posRow += 1;
			}
			gridPane.add(pane, posCol, posRow);
			GridPane.setHalignment(pane, HPos.CENTER);
			posCol += 1;
		}
		anchorPane_LP.getChildren().add(gridPane);
	}

	public ArrayList<KhachHang> timKhachHang(String keyword) {
		ArrayList<KhachHang> listFound = new ArrayList<>();
		String lowerKeyWord = keyword.toLowerCase();
		for (KhachHang kh : listKhachHang) {
			if (kh.getHoTen().toLowerCase().contains(lowerKeyWord) || kh.getSoDT().toLowerCase().contains(lowerKeyWord)
					|| kh.getEmail().toLowerCase().contains(lowerKeyWord)) {
				listFound.add(kh);
			}
		}
		return listFound;
	}

	public ObservableList<KhachHangDatTruoc> timKhachHangDatTruoc(String keyword) {
		ObservableList<KhachHangDatTruoc> listFound = FXCollections.observableArrayList();
		String lowerKeyWord = keyword.toLowerCase();
		for (KhachHangDatTruoc kh : listKhachDatTruoc) {
			if (kh.getHoTen().toLowerCase().contains(lowerKeyWord) || kh.getSoDT().toLowerCase().contains(lowerKeyWord)
					|| kh.getEmail().toLowerCase().contains(lowerKeyWord)) {
				listFound.add(kh);
			}
		}
		return listFound;
	}

	public ObservableList<KhachHangDangThue> timKhachHangDangThue(String keyword) {
		ObservableList<KhachHangDangThue> listFound = FXCollections.observableArrayList();
		String lowerKeyWord = keyword.toLowerCase();
		for (KhachHangDangThue kh : listKhachDangThue) {
			if (kh.getHoTen().toLowerCase().contains(lowerKeyWord) || kh.getSoDT().toLowerCase().contains(lowerKeyWord)
					|| kh.getEmail().toLowerCase().contains(lowerKeyWord)) {
				listFound.add(kh);
			}
		}
		return listFound;
	}

	public TableView<Map<String, Object>> configTableUsingService(TableView<Map<String, Object>> table) {
		table.getColumns().clear();
		// Tạo các cột cho TableView
		TableColumn<Map<String, Object>, String> column1 = new TableColumn<>("Họ tên khách hàng");
		column1.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("hoTen")));
		TableColumn<Map<String, Object>, String> column2 = new TableColumn<>("Giới tính");
		column2.setCellValueFactory(cellData -> {
			Boolean gioiTinh = (Boolean) cellData.getValue().get("gioiTinh");
			return new SimpleStringProperty(gioiTinh != null && gioiTinh ? "nam" : "nữ");
		});
		TableColumn<Map<String, Object>, String> column3 = new TableColumn<>("Email");
		column3.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("email")));
		TableColumn<Map<String, Object>, String> column4 = new TableColumn<>("Số điện thoại");
		column4.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("sdt")));
		TableColumn<Map<String, Object>, String> column5 = new TableColumn<>("CCCD");
		column5.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("cccd")));
		TableColumn<Map<String, Object>, String> column6 = new TableColumn<>("Ngày đến");
		column6.setCellValueFactory(cellData -> {
			Date ngayDen = (Date) cellData.getValue().get("ngayDen");
			return new SimpleStringProperty(XDate.toString(ngayDen, "yyyy-MM-dd"));
		});
		TableColumn<Map<String, Object>, String> column7 = new TableColumn<>("Số phòng");
		column7.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("maPhong")));
		TableColumn<Map<String, Object>, String> column8 = new TableColumn<>("Loại khách");
		column8.setCellValueFactory(cellData -> {
			int maLK = (int) cellData.getValue().get("maLK");
			String tenLK = null;
			for (LoaiKhachHang lk : loaiKhach) {
				if (maLK == lk.getMaLoaiKH()) {
					tenLK = lk.getTenLoaiKH();
					break;
				}
			}
			return new SimpleStringProperty(tenLK);
		});
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.getColumns().add(column3);
		table.getColumns().add(column4);
		table.getColumns().add(column5);
		table.getColumns().add(column6);
		table.getColumns().add(column7);
		table.getColumns().add(column8);
		table.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER_LEFT;"));
		// Đặt số cột tối đa và buộc các cột tự giàn trải
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		return table;
	}

	public void fillToTableUsingService(TableView<Map<String, Object>> table,
			ObservableList<KhachHangDangThue> dataFill) {
		try {
			table.getItems().clear();
			ObservableList<Map<String, Object>> ol = FXCollections.observableArrayList();
			for (KhachHangDangThue customer : dataFill) {
				Map<String, Object> row = new HashMap<>();
				row.put("maKH", customer.getMaKH()); // lưu trữ, không in lên table
				row.put("hoTen", customer.getHoTen());
				row.put("gioiTinh", customer.getGioiTinh());
				row.put("email", customer.getEmail());
				row.put("sdt", customer.getSoDT());
				row.put("cccd", customer.getCccd());
				row.put("ngayDen", customer.getNgayDen());
				row.put("maPhong", customer.getMaPhong());
				row.put("maLK", customer.getMaLK());
				ol.add(row);
			}
			table.setItems(ol);
		} catch (Exception e) {
			System.out.println("table using service isn't loaded - its ok bro, don't mind");
		}

	}

	public void configTableBooked(TableView<Map<String, Object>> table) {
		table.getColumns().clear();
		// Tạo các cột cho TableView
		TableColumn<Map<String, Object>, String> column1 = new TableColumn<>("Họ tên khách hàng");
		column1.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("hoTen")));
		TableColumn<Map<String, Object>, String> column2 = new TableColumn<>("Giới tính");
		column2.setCellValueFactory(cellData -> {
			Boolean gioiTinh = (Boolean) cellData.getValue().get("gioiTinh");
			return new SimpleStringProperty(gioiTinh != null && gioiTinh ? "nam" : "nữ");
		});
		TableColumn<Map<String, Object>, String> column3 = new TableColumn<>("Email");
		column3.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("email")));
		TableColumn<Map<String, Object>, String> column4 = new TableColumn<>("Số điện thoại");
		column4.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("sdt")));
		TableColumn<Map<String, Object>, String> column5 = new TableColumn<>("CCCD");
		column5.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("cccd")));
		TableColumn<Map<String, Object>, String> column6 = new TableColumn<>("Ngày đến dự kiến");
		column6.setCellValueFactory(cellData -> {
			Date ngayDenDK = (Date) cellData.getValue().get("ngayDenDK");
			return new SimpleStringProperty(XDate.toString(ngayDenDK, "yyyy-MM-dd"));
		});
		TableColumn<Map<String, Object>, String> column7 = new TableColumn<>("Ngày đi dự kiến");
		column7.setCellValueFactory(cellData -> {
			Date ngayDiDK = (Date) cellData.getValue().get("ngayDiDK");
			return new SimpleStringProperty(XDate.toString(ngayDiDK, "yyyy-MM-dd"));
		});
		TableColumn<Map<String, Object>, String> column8 = new TableColumn<>("Loại khách");
		column8.setCellValueFactory(cellData -> {
			int maLK = (int) cellData.getValue().get("maLK");
			String tenLK = null;
			for (LoaiKhachHang lk : loaiKhach) {
				if (maLK == lk.getMaLoaiKH()) {
					tenLK = lk.getTenLoaiKH();
					break;
				}
			}
			return new SimpleStringProperty(tenLK);
		});
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.getColumns().add(column3);
		table.getColumns().add(column4);
		table.getColumns().add(column5);
		table.getColumns().add(column6);
		table.getColumns().add(column7);
		table.getColumns().add(column8);
		table.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER_LEFT;"));
		// Đặt số cột tối đa và buộc các cột tự giàn trải
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
	}

	public void fillToTableBooked(TableView<Map<String, Object>> table, ObservableList<KhachHangDatTruoc> dataFill) {
		try {
			table.getItems().clear();
			ObservableList<Map<String, Object>> ol = FXCollections.observableArrayList();
			for (KhachHangDatTruoc kh : dataFill) {
				Map<String, Object> row = new HashMap<>();
				row.put("maPhieu", kh.getMaPhieu()); // lưu trữ, không in lên table
				row.put("maKH", kh.getMaKH()); // lưu trữ, không in lên table
				row.put("ngaySinh", kh.getNgaySinh()); // lưu trữ, không in lên table
				row.put("hoTen", kh.getHoTen());
				row.put("gioiTinh", kh.getGioiTinh());
				row.put("email", kh.getEmail());
				row.put("sdt", kh.getSoDT());
				row.put("cccd", kh.getCccd());
				row.put("ngayDat", kh.getNgayDat());
				row.put("ngayDenDK", kh.getNgayDenDK());
				row.put("ngayDiDK", kh.getNgayDiDK());
				row.put("maLK", kh.getMaLK());
				ol.add(row);
			}
			table.setItems(ol);

		} catch (Exception e) {
			System.out.println("table booked isn't loaded - its ok bro, don't mind");
		}
	}

	public void configTableCustomer(TableView<Map<String, Object>> table) {
		table.getColumns().clear();
		// Tạo các cột cho TableView
		TableColumn<Map<String, Object>, String> column1 = new TableColumn<>("Họ tên khách hàng");
		column1.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("hoTen")));
		TableColumn<Map<String, Object>, String> column2 = new TableColumn<>("Giới tính");
		column2.setCellValueFactory(cellData -> {
			Boolean gioiTinh = (Boolean) cellData.getValue().get("gioiTinh");
			return new SimpleStringProperty(gioiTinh != null && gioiTinh ? "nam" : "nữ");
		});
		TableColumn<Map<String, Object>, String> column3 = new TableColumn<>("Email");
		column3.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("email")));
		TableColumn<Map<String, Object>, String> column4 = new TableColumn<>("Số điện thoại");
		column4.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("sdt")));
		TableColumn<Map<String, Object>, String> column5 = new TableColumn<>("CCCD");
		column5.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("cccd")));
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.getColumns().add(column3);
		table.getColumns().add(column4);
		table.getColumns().add(column5);
		table.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER_LEFT;"));
		// Đặt số cột tối đa và buộc các cột tự giàn trải
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
	}

	public void fillToTableCustomer(TableView<Map<String, Object>> table, ArrayList<KhachHang> dataFill) {
		table.getItems().clear();
		ObservableList<Map<String, Object>> ol = FXCollections.observableArrayList();
		for (KhachHang kh : dataFill) {
			Map<String, Object> row = new HashMap<>();
			row.put("maKH", kh.getMaKH()); // lưu trữ, không in lên table
			row.put("ngaySinh", kh.getNgaySinh()); // lưu trữ, không in lên table
			row.put("hoTen", kh.getHoTen());
			row.put("gioiTinh", kh.getGioiTinh());
			row.put("email", kh.getEmail());
			row.put("sdt", kh.getSoDT());
			row.put("cccd", kh.getCccd());
			row.put("maLK", kh.getMaLoaiKH());
			ol.add(row);
		}
		table.setItems(ol);
	}

	public void configTableListProducts(TableView<Map<String, Object>> table) {
		table.getColumns().clear();
		TableColumn<Map<String, Object>, ImageView> column1 = new TableColumn<>("Hình");
		column1.setMaxWidth(60);
		column1.setStyle("-fx-alignment: CENTER;");
		column1.setCellValueFactory(cellData -> {
			// The cell value factory should return an ObservableValue
			// We will create a SimpleObjectProperty containing an ImageView
			ImageView img = new ImageView();

			img.setFitWidth(50); // Set your desired width
			img.setFitHeight(50); // Set your desired height
			img.setPreserveRatio(true); // Preserve the aspect ratio
			String urlImg = "/imgs/" + cellData.getValue().get("hinh");
			img.setImage(new Image(urlImg));

			return new SimpleObjectProperty<>(img);
		});
		// Setting the cell factory to customize the cell rendering
		column1.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(ImageView item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setGraphic(null);
				} else {
					setGraphic(item);
				}
			}
		});

		TableColumn<Map<String, Object>, String> column2 = new TableColumn<>("Tên sản phẩm");
		column2.setStyle("-fx-alignment: CENTER;");
		column2.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("tenSP")));
		TableColumn<Map<String, Object>, String> column3 = new TableColumn<>("Đơn giá");
		column3.setCellValueFactory(cellData -> {
			double donGia = Double.parseDouble((String) cellData.getValue().get("gia"));
			return new SimpleStringProperty(formatCurrencyVND(donGia));
		});
		column3.setMaxWidth(100);
		column3.setStyle("-fx-alignment: CENTER_RIGHT;");
		TableColumn<Map<String, Object>, String> column4 = new TableColumn<>("Số lượng");
		column4.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("soLuong")));
		column4.setMaxWidth(100);
		column4.setStyle("-fx-alignment: CENTER_RIGHT;");
		TableColumn<Map<String, Object>, String> column5 = new TableColumn<>("Tổng tiền");
		column5.setCellValueFactory(cellData -> {
			double tongTien = Double.parseDouble((String) cellData.getValue().get("tongTien"));
			return new SimpleStringProperty(formatCurrencyVND(tongTien));
		});
		column5.setMaxWidth(100);
		column5.setStyle("-fx-alignment: CENTER_RIGHT;");
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.getColumns().add(column3);
		table.getColumns().add(column4);
		table.getColumns().add(column5);
	}

	public void fillToTableListProducts(TableView<Map<String, Object>> table, ArrayList<SanPham> dataFill) {
		table.getItems().clear();
		ObservableList<Map<String, Object>> ol = FXCollections.observableArrayList();
		for (SanPham sp : dataFill) {
			Map<String, Object> row = new HashMap<>();
			row.put("hinh", sp.getHinh()); // lưu trữ, không in lên table
			row.put("tenSP", sp.getTenSP()); // lưu trữ, không in lên table
			row.put("gia", String.valueOf(sp.getGia()));
			row.put("soLuong", String.valueOf(sp.getSoLuong()));
			row.put("tongTien", String.valueOf(sp.getTotal()));
			ol.add(row);
		}
		table.setItems(ol);
	}

	public static String formatCurrencyVND(double amount) {
		// Create a NumberFormat instance for Vietnamese currency
		Locale vietnam = Locale.of("vi", "VN");
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(vietnam);
		return currencyFormat.format(amount);
	}

}
