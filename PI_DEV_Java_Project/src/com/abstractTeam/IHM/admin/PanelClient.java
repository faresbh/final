package com.abstractTeam.IHM.admin;




import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.abstractTeam.Controller.ClientDAO;
import com.abstractTeam.IHM.ApplicationFrame;
import com.abstractTeam.Model.Client;

@SuppressWarnings("serial")
public class PanelClient extends JPanel {


	JLabel lblNewLabel_1 = new JLabel("");
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelClient(final int type) {
		
		
		
		
		setBackground(Color.WHITE);
		
		setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2,
				(Color) new Color(192, 192, 192)),
				"Gestion Client  - Resto-Tunisie -", TitledBorder.LEADING,
				TitledBorder.TOP, new Font(" Arial ", Font.BOLD, 15),
				Color.DARK_GRAY));
		setBounds(337, 76, 1013, 611);
		setLayout(null);
		
	
			
						
			table= new JTable(new TableModelClient(type));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(56, 119, 917, 361);
			scrollPane.setViewportView(table);
			add(scrollPane);
			
			JButton btnAccepter = new JButton("Accepter");
			
			btnAccepter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int id= (Integer) table.getValueAt(table.getSelectedRow(),0);
					ClientDAO dao= new ClientDAO();
				Client client=	dao.FindClientByID(id);
				client.setStatut("valide");
				dao.UpdateClient(client);
				//rafraichir
				dao.EnvoyerMail(client);
				ApplicationFrame.content.remove(ApplicationFrame.panelContenu);
				ApplicationFrame.panelContenu= new PanelClient(1);
				ApplicationFrame.content.add(ApplicationFrame.panelContenu);
				ApplicationFrame.content.validate();
				ApplicationFrame.content.repaint();
				//rafraichir
				}
			});
			btnAccepter.setBounds(274, 509, 127, 23);
			add(btnAccepter);
			if (type == 2){
				btnAccepter.setVisible(false);
				
			}
			JButton btnRefuser = new JButton("Refuser");
			btnRefuser.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					ClientDAO dao = new ClientDAO();
					int id= (Integer) table.getValueAt(table.getSelectedRow(),0);
						dao.supprimerClient(id);
					//rafraichir
						if(type==1){
						ApplicationFrame.content.remove(ApplicationFrame.panelContenu);
						ApplicationFrame.panelContenu= new PanelClient(1);
						ApplicationFrame.content.add(ApplicationFrame.panelContenu);
						ApplicationFrame.content.validate();
						ApplicationFrame.content.repaint();
						
						}else{
							ApplicationFrame.content.remove(ApplicationFrame.panelContenu);
							ApplicationFrame.panelContenu= new PanelClient(2);
							ApplicationFrame.content.add(ApplicationFrame.panelContenu);
							ApplicationFrame.content.validate();
							ApplicationFrame.content.repaint();
							
						}
				}
			});
//			btnRefuser.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//				
//					
//						 int[] selection = table.getSelectedRows();
//						 remove(selection);
//						 Client c=null;
//						if (c.getIdClient() == idClient){
//							
//					String req = "delete Client From Client where idClient == id ";
//						
//						}}
//			});
			btnRefuser.setBounds(423, 509, 114, 23);
			add(btnRefuser);
			if (type == 2){
				btnAccepter.setVisible(false);
				btnRefuser.setText("Supprimer");
				
			}
		
	}
}
