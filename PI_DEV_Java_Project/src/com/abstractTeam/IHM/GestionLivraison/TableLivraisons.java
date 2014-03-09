package com.abstractTeam.IHM.GestionLivraison;



import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import com.abstractTeam.Controller.LivraisonsDao;
import com.abstractTeam.IHM.ApplicationFrame;
import com.abstractTeam.Model.Livraison;

@SuppressWarnings("serial")
public class TableLivraisons extends JPanel {
	public static FrameLivraison  frame ;
	JLabel lblNewLabel_1 = new JLabel("");

	public static JPanel panel;
public static	Livraison livraison= new Livraison();
	Date date ;
	
	/**
	 * Create the panel.
	 */
	
	 private Image backgroundImage;
	static JTable table;

	
	@SuppressWarnings("deprecation")
	public TableLivraisons(String fileName) {
		setForeground(Color.WHITE);
		 
		 
		    try {
				backgroundImage = ImageIO.read(new File(fileName));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  

		
		  
		
		setBackground(Color.WHITE);

		setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(192, 192, 192)), "Gestion Reservations  - Resto-Tunisie -", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		setBounds(337, 76, 1013, 611);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 332, 649, 236);
		add(scrollPane);
		
		table = new JTable(new modelLivraison());
		
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.DARK_GRAY);
		table.setShowVerticalLines(false);
		table.setFillsViewportHeight(true);
		table.getColumn("id").setMinWidth(0);
		table.getColumn("id").setMaxWidth(0);
		table.setToolTipText("Liste des r\u00E9servations");
		// table.setIntercellSpacing(new Dimension(22, 1));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		table.getColumn("").setCellRenderer(new ButtonRenderer());
		table.getColumn("")
				.setCellEditor(new ButtonEditor(new JCheckBox()));
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Liste des Livraisons");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 43));
		lblNewLabel.setBounds(352, 60, 612, 95);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Retour");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setIcon(new ImageIcon("img/icone_retour.png"));
		lblNewLabel_2.setBounds(901, 48, 91, 56);
		add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ApplicationFrame.content.remove(ApplicationFrame.panelContenu);
				ApplicationFrame.panelContenu= new PanelLivraison(new String("img/liv.jpg"));

				ApplicationFrame.content.add(ApplicationFrame.panelContenu);
				ApplicationFrame.content.validate();
				ApplicationFrame.content.repaint();
			}
		});
	

	}
	
	  public void paintComponent(Graphics g) {
		    super.paintComponent(g);

		    // Draw the background image.
		    g.drawImage(backgroundImage, 0, 0, this);
		  }
}
class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
		//setText("<html><style color='black'>Valider</html>");
	//	setForeground(Color.BLACK);
	
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
 class  ButtonEditor extends DefaultCellEditor {
	protected JButton button;

	private String label;

	

	private boolean isPushed;

	private LivraisonsDao gestion_liv= new LivraisonsDao();

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
	//	button.setText("Valider");
	//	button.setForeground(Color.BLACK);
		button.setBackground(Color.BLACK);
		button.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				System.out.println("Frame Réservation opened");
				
				int row = TableLivraisons.table.getSelectedRow();
				int id = (Integer) TableLivraisons.table.getValueAt(row,0);
				TableLivraisons.livraison = gestion_liv.findLivraisonById(id);
				
				TableLivraisons.frame= new FrameLivraison(TableLivraisons.livraison);
				TableLivraisons.frame.setVisible(true);
				
				TableLivraisons.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	@SuppressWarnings("deprecation")
	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			if (button.getText().matches("")) {
				
			}  
		isPushed = false;}
		return new String(label);
		}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
