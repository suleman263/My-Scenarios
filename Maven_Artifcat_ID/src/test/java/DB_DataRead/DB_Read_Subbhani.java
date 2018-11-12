package DB_DataRead;
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import jxl.write.WriteException;
	import jxl.write.biff.RowsExceededException;


	public class DB_Read_Subbhani {

	               public static void exportToExcelFromDb(String url, String userName, String password, String query,
	                                             String targetFilename) {
	                              connectDb(url, userName, password, query, targetFilename);

	               }

	               public static void connectDb(String url, String userName, String password, String query, String targetFilename) {
	                              try {

	                                             Class.forName("oracle.jdbc.driver.OracleDriver");

	                                             Connection con = DriverManager.getConnection(url, userName, password);
	                                             Statement stmt = con.createStatement();
	                                             ResultSet rs = stmt.executeQuery(query);
	                                             toXLS(rs, targetFilename);

	                                             con.close();

	                              } catch (Exception e) {
	                                             System.out.println(e);
	                              }

	               }

	               public static void toXLS(ResultSet rs, String targetFileName)
	                                             throws SQLException, IOException, RowsExceededException, WriteException {
	                              try {
	                                             ResultSetMetaData metadata = rs.getMetaData();
	                                             int count = metadata.getColumnCount();
	                                             XSSFWorkbook workbook = new XSSFWorkbook();
	                                             XSSFSheet spreadsheet = workbook.createSheet("Query Results");

	                                             XSSFRow row = spreadsheet.createRow(0);
	                                             XSSFCell cell;

	                                             for (int i = 0; i < metadata.getColumnCount(); i++) {
	                                                            cell = row.createCell(i);
	                                                            cell.setCellValue(metadata.getColumnName(i + 1));

	                                             }
	                                             int j = 1;
	                                             while (rs.next()) {
	                                                            row = spreadsheet.createRow(j);
	                                                            for (int i = 0; i < count; i++) {
	                                                                           cell = row.createCell(i);
	                                                                           cell.setCellValue(rs.getString(i + 1));

	                                                            }
	                                                            j++;
	                                             }

	                                             FileOutputStream out = new FileOutputStream(new File("C:\\IDF_Results\\" + targetFileName + ".xlsx"));
	                                             workbook.write(out);
	                                             out.close();
	                                             System.out.println("exceldatabase.xlsx written successfully");

	                              } catch (Exception e) {
	                                             e.printStackTrace();
	                              }

	               }

	               public static void main(String[] args) {
	                              String query = "select * from capiq.snlregflowperioddata where REGFLOWPERIODID='C2082AF8-C5EB-4AC9-AA89-000433736E81'";
	                            exportToExcelFromDb("jdbc:oracle:thin:@nj09mhf0603-scan:1521/spreftst.world", "finmaster_etl_user", "finmaster_etl_user",
	                                                            query, "query_results");
	               }

	}
	

	
