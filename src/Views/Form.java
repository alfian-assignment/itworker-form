/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Exceptions.Minimun10DigitNoPegawai;
import Utils.Database;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Alfian Andi Nugraha
 */
public class Form extends javax.swing.JFrame {

    /**
     * Creates new form Form
     */
    public Form() {
        initComponents();
        this.handleSelectPosisi();
        this.refreshData();
    }
    
    public void refreshData() {
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        DefaultTableModel tableModel = (DefaultTableModel)this.jTable1.getModel();
        
        try {
            String query = "SELECT * FROM workers";
            Statement statement = Database.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                Object[] resultRow = {
                    result.getInt("id"),
                    result.getString("no_pegawai"),
                    result.getString("nama"),
                    result.getString("posisi"),
                    String.format("%.0f", result.getDouble("gaji")),
                };
                tableModel.addRow(resultRow);
            }
        } catch (Exception err) {
            this.openPopUp("Tidak dapat memuat data silahkan cek database\nError : " + err.getMessage());
        }
    }
    
    public void insertData() throws Minimun10DigitNoPegawai, Exception {
        if(this.inputNoPegawai.getText().length() != 10) {
            throw new Minimun10DigitNoPegawai();
        }
        
        String nama = this.inputNama.getText();
        String no_pegawai = this.inputNoPegawai.getText();
        String posisi = this.selectPosisi.getSelectedItem().toString();
        double gaji = this.getGaji(posisi);
        
        String query = String.format(
            "INSERT INTO workers(nama, no_pegawai, posisi, gaji) VALUES ('%s', '%s', '%s', %f)", 
            nama, no_pegawai, posisi, gaji
        );
        
        try {
            Statement statement = Database.getConnection().createStatement();
            statement.executeUpdate(query);
        } catch(Exception err) {
            throw err;
        }
        
        this.refreshData();
        this.openPopUp("Data berhasil ditambahkan");
        this.resetForm();
    }
    
    public void deleteData() throws Minimun10DigitNoPegawai, Exception {
        if(this.selectedIdPegawai < 0) {
            this.openPopUp("Silahkan pilih id yang ingin dihapus");
            return;
        }
        
        String query = String.format(
            "DELETE FROM workers WHERE id = %d", this.selectedIdPegawai
        );
        
        try {
            Statement statement = Database.getConnection().createStatement();
            statement.executeUpdate(query);
        } catch(Exception err) {
            throw err;
        }
        
        this.refreshData();
        this.openPopUp("Id : " + this.selectedIdPegawai + " berhasil dihapus");
        this.resetSelectedIdPegawai();
        this.resetForm();
    }
    
    public void updateData() throws Minimun10DigitNoPegawai, Exception {
        if(this.inputNoPegawai.getText().length() != 10) {
            throw new Minimun10DigitNoPegawai();
        }
        
        if(this.selectedIdPegawai < 0) {
            this.openPopUp("Silahkan pilih id yang ingin diperbaharui");
            return;
        }
        
        String nama = this.inputNama.getText();
        String no_pegawai = this.inputNoPegawai.getText();
        String posisi = this.selectPosisi.getSelectedItem().toString();
        double gaji = this.getGaji(posisi);
        
        String query = String.format(
            "UPDATE workers SET nama = '%s', no_pegawai = '%s', posisi = '%s', gaji = %f WHERE id = %d", 
            nama, no_pegawai, posisi, gaji, this.selectedIdPegawai
        );
        
        try {
            Statement statement = Database.getConnection().createStatement();
            statement.executeUpdate(query);
        } catch(Exception err) {
            throw err;
        }
        
        this.refreshData();
        this.openPopUp("Data berhasil diperbaharui");
        this.resetSelectedIdPegawai();
        this.resetForm();
    }
    
    public double getGaji(String posisi) {
        switch(posisi){
            case "Senior Programmer":
                return 15000000;
            case "Bussiness Analyst":
                return 21000000;
            case "Data Center Officer":
                return 18000000;
            case "Junior Programmer":
                return 8000000;
            default:
                return -1;
        }
    }
    
    public void resetSelectedIdPegawai() {
        this.selectedIdPegawai = -1;
        this.toggleUpdateAndDeleteButton();
    }
    
    public void resetForm() {
        this.inputNama.setText("");
        this.inputNoPegawai.setText("");
        this.selectPosisi.setSelectedItem("Senior Programmer");
        this.handleSelectPosisi();
    }
    
    public void setTextFieldGaji(double gaji) {
        String nominal = String.format("%.0f", gaji);
        this.fieldGaji.setText(String.valueOf(nominal));
    }
    
    public void handleSelectPosisi() {
        String posisi = this.selectPosisi.getSelectedItem().toString();
        double gaji = this.getGaji(posisi);
        this.setTextFieldGaji(gaji);
    }
    
    public void openPopUp(String message) {
        this.popup.showMessageDialog(null, message);
    }
    
    public void toggleUpdateAndDeleteButton() {
        this.updateButton.setEnabled(!this.updateButton.isEnabled());
        this.deleteButton.setEnabled(!this.deleteButton.isEnabled());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputNoPegawai = new javax.swing.JTextField();
        selectPosisi = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        fieldGaji = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        insertButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("IT Worker Form");

        jLabel2.setText("Nama");

        jLabel3.setText("No. Pegawai");

        selectPosisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Senior Programmer", "Bussiness Analyst", "Data Center Officer", "Junior Programmer" }));
        selectPosisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPosisiActionPerformed(evt);
            }
        });

        jLabel4.setText("Posisi");

        fieldGaji.setEnabled(false);

        jLabel5.setText("Gaji (IDR)");

        insertButton.setText("Insert");
        insertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insertButtonMouseClicked(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.setEnabled(false);
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "No. Pegawai", "Nama", "Posisi", "Gaji"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(insertButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateButton))
                            .addComponent(inputNoPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(inputNama)
                            .addComponent(selectPosisi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldGaji, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(deleteButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exitButton)))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(exitButton))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputNoPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectPosisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertButton)
                    .addComponent(updateButton))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(deleteButton)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertButtonMouseClicked
        try {
            this.insertData();
        } catch (Exception err) {
            System.out.println(err.getMessage());
            this.openPopUp(err.getMessage());
        }
    }//GEN-LAST:event_insertButtonMouseClicked

    private void selectPosisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPosisiActionPerformed
        this.handleSelectPosisi();
    }//GEN-LAST:event_selectPosisiActionPerformed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = this.jTable1.getSelectedRow();
        int selectedId = Integer.parseInt(this.jTable1.getValueAt(selectedRow, 0).toString());
        this.selectedIdPegawai = selectedId;
        
        this.inputNoPegawai.setText(this.jTable1.getValueAt(selectedRow, 1).toString());
        this.inputNama.setText(this.jTable1.getValueAt(selectedRow, 2).toString());
        this.selectPosisi.setSelectedItem(this.jTable1.getValueAt(selectedRow, 3).toString());
        this.handleSelectPosisi();
        this.updateButton.setEnabled(true);
        this.deleteButton.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        if(!this.deleteButton.isEnabled()) return;
        try {
            this.deleteData();
        } catch (Exception err) {
            this.openPopUp(err.getMessage());
        }
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        if(!this.updateButton.isEnabled()) return;
        try {
            this.updateData();
        } catch (Exception err) {
            this.openPopUp(err.getMessage());
        }
    }//GEN-LAST:event_updateButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }
    public JOptionPane popup;
    private int selectedIdPegawai = -1;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField fieldGaji;
    private javax.swing.JTextField inputNama;
    private javax.swing.JTextField inputNoPegawai;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> selectPosisi;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
