package sesion1_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes 
{
	Connection con;
	 public static void main(String[] args)
	 {
		 Reportes rep = new Reportes();
		 rep.conectar();
//		 rep.reporteAPDF();
		 rep.exportarReporte();
	 }
	 private void reporteAPDF() 
	 {
		 JasperPrint jasperPrint;
		 try {
			jasperPrint = JasperFillManager.fillReport("reportes/FirstJasperReport.jasper", null,con);
			JRPdfExporter exp = new JRPdfExporter();
			exp.setExporterInput(new SimpleExporterInput(jasperPrint));
			exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteBanco.pdf"));
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			exp.setConfiguration(conf);
			exp.exportReport();
		 } catch (JRException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
	}
	 
	 private void exportarReporte() 
	 {
		 try {
			 JasperPrint jasperPrintWindow = JasperFillManager.fillReport("reportes/FirstJasperReport.jasper", null,con);
			 JasperViewer jasperView = new JasperViewer(jasperPrintWindow);
			 jasperView.setVisible(true);
		 } catch (JRException e) {
			// TODO: handle exception
		 } finally {
			 try {
				 if(con!=null)con.close();
			 } catch (SQLException e2) {
				// TODO: handle exception
			 }
		 }
	 }
	 
	 private void conectar() 
	 {
		 String server = "jdbc:mysql://localhost/learning";
		 String user = "andres";
		 String password = "qwerty123456";
		
		 try {
			 con = DriverManager.getConnection(server, user, password);
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 if(con!=null)
		 {
			 System.out.println("Conneciotn a BD establecida!!");
		 }
	 }
}
