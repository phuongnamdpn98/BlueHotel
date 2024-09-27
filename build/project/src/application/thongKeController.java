package application;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Controller.ThongKeDAO;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

public class thongKeController implements Initializable {

	@FXML
	private PieChart pieChartLoaiKh;

	@FXML
	private PieChart pieChartLoaiPhong;

	@FXML
	private LineChart<String, Number> lineChartDoanhThu;

	@FXML
	private ComboBox<String> cboNam;
	
	ThongKeDAO dao = new ThongKeDAO();
	ObservableList<String> listCbo = FXCollections.observableArrayList("2024", "2025", "2026");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cboNam.setItems(listCbo);
		loaiPieChartLoaiKh();
		loaiPieChartLoaiPhong();
	}
	
	@FXML
	void chooseYear(ActionEvent event) {
		String selectedYear = cboNam.getValue();

		if (selectedYear == null) {
			System.out.println("Vui lòng chọn năm trước khi hiển thị biểu đồ.");

		} else {
			int cboYear = Integer.parseInt(selectedYear);	
			List<Object[]> dataFromProcedure = dao.thongKeNam(cboYear);
			// Set up the x and y axes
	//	    CategoryAxis xAxis = new CategoryAxis();
//		    xAxis.setLabel("X Axis");
//		    NumberAxis yAxis = new NumberAxis();
//		    yAxis.setLabel("Y Axis");
//		    // Create the LineChart with the axes
//		    lineChartDoanhThu = new LineChart<>(xAxis, yAxis);
//		    lineChartDoanhThu.setTitle("XY Chart Demo");
		    
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Tháng");
			for (Object[] row : dataFromProcedure) {
				String thang = "Tháng " + String.valueOf(row[0]);
				long doanhThu = Long.parseLong(String.valueOf(row[1]));
				System.out.println(thang + " | " + doanhThu);
				series.getData().add(new XYChart.Data<>(thang, doanhThu));
			}
			
			lineChartDoanhThu.getData().clear();
			lineChartDoanhThu.getData().add(series);
		}

	}

	void loaiPieChartLoaiKh() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		List<Object[]> dataFromFunction = dao.tinhPhanTramLoaiKhachHang();
		for (Object[] row : dataFromFunction) {
			String loaiLK = (String) row[0];
			BigDecimal myBigDecimal = (BigDecimal) row[1];
			double phanTram = myBigDecimal.doubleValue(); // Chuyển đổi sang kiểu double
			pieChartData.add(new PieChart.Data(loaiLK, phanTram));
			// pieChartLoaiKh.getData().addAll(pieChartData);
		}
		pieChartLoaiKh.setData(pieChartData);
	}

	void loaiPieChartLoaiPhong() {
		ObservableList<PieChart.Data> pieChartDataPhong = FXCollections.observableArrayList();
		List<Object[]> dataFromProcedure = dao.tinhPhanTramLoaiPhong();

		for (Object[] row : dataFromProcedure) {
			String loaiPhong = (String) row[0];
			BigDecimal myBigDecimal = (BigDecimal) row[1];
			double phanTram = myBigDecimal.doubleValue(); // Chuyển đổi sang kiểu double
			pieChartDataPhong.add(new PieChart.Data(loaiPhong, phanTram));
			// pieChartLoaiPhong.getData().addAll(pieChartDataPhong);
		}
		pieChartLoaiPhong.setData(pieChartDataPhong);
	}

}
