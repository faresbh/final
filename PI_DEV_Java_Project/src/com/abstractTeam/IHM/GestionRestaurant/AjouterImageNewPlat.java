package com.abstractTeam.IHM.GestionRestaurant;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.abstractTeam.Model.Photo;
 
public class AjouterImageNewPlat extends javax.swing.JFrame {
 
    private static final int IMG_WIDTH = 120;
    private static final int IMG_HEIGHT = 120;
    JLabel label;
    ImageIcon photo;
    WritableRaster raster;
    DataBufferByte data;
 File  image;
 public static int sommeImages=0;
 public static List<Photo> photos=new ArrayList<Photo>();
 
    public AjouterImageNewPlat() {
        initComponents();
        jPanel2.setSize(120, 120);
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
 
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
 
        jLabel1.setText("jLabel1");
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image resize and upload demo");
 
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
 
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());
 
        btnSave.setText("Save Image");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(107)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(jButton1)
        			.addGap(80))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(47, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(311, Short.MAX_VALUE)
        			.addComponent(btnSave))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(19)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton1))
        			.addGap(24)
        			.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
        			.addComponent(btnSave))
        );
        getContentPane().setLayout(layout);
 setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
    }// </editor-fold>                        
 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	sommeImages++;
        JFileChooser chooser;
        FileNameExtensionFilter filter;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(image);
        chooser.setMultiSelectionEnabled(true);
        filter = new FileNameExtensionFilter("jpeg, gif and png files", "jpg", "gif", "png");
        chooser.addChoosableFileFilter(filter);
 
        int i = chooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            image = chooser.getSelectedFile();
            jLabel2.setText(image.getAbsolutePath());
            try {
                BufferedImage originalImage = ImageIO.read(image);
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
 
                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                photo = new ImageIcon(toImage(resizeImageJpg));
 
                //converting buffered image to byte array
                raster = resizeImageJpg.getRaster();
                data = (DataBufferByte) raster.getDataBuffer();
 
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
 
 
            jPanel2.removeAll();
            label = new JLabel("", photo, JLabel.CENTER);
            jPanel2.add(label);
 
            repaint();
            chooser.setCurrentDirectory(image);
        }
 
    }                                        
    public Image toImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) { 
    	Photo photo=new Photo();
    	photo.setUrl(image.getAbsolutePath());
    	PanelPlat.photosNewPlat.add(photo); 
    	PanelPlat.listImageModel.addElement(photo.getUrl());
    	}
      
                                         
 
    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
 
        return resizedImage;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
 
            public void run() {
                new AjouterImageNewPlat().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
}