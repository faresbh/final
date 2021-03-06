package GestionReservation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.abstractTeam.Controller.ConnexionDB;
import com.abstractTeam.Model.Reservation;

/**
 * 
 * @salma
 */
public class GenererPDF extends JFrame {
	@SuppressWarnings("unchecked")
	public GenererPDF(Reservation res) {
		Connection connection;
		try {
			// - Connexion � la base
			connection = ConnexionDB.getConnected();
			// - Chargement et compilation du rapport
			
			JasperDesign jasperDesign = JRXmlLoader.load("doc/report4.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			// - Param�tres � envoyer au rapport
			Map parameters = new HashMap();

			parameters.put("idRes", new Integer(res.getIdReservation()));
			parameters.put("nomClient", res.getClient().getNom());
			parameters.put("prenomClient", res.getClient().getPrenom());
			parameters.put("adrClient", res.getClient().getAdresse());
			parameters.put("telClient", res.getClient().getTel());
			parameters.put("Resto", res.getRestaurant().getNom());
			parameters.put("somme", res.getFacture().getSomme());

			// - Execution du rapport
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);
			// - Cr�ation du rapport au format PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"doc/facture/factureRes.pdf");
			JasperViewer.viewReport(jasperPrint, false); // L'affichage du
															// rapport en
															// utilisant
															// JRViewer
			System.out.println("success");

		}

		catch (JRException e) {
			System.out.println("erreur de compilation" + e.getMessage());
		}

	}

}
/**/