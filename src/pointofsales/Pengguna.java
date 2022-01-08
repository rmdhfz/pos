/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointofsales;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mdm
 */
public class Pengguna extends javax.swing.JFrame {

    /**
     * Creates new form Pengguna
     */
     // Variabel Global
    private Connection conn;
    private Statement stat;
    private ResultSet res;
    private String t;
    private DefaultTableModel dtm;
    private String TabelPengguna = "pengguna";
    
    public Pengguna() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation (
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2
        );
        JamRealTime();
        koneksi();
        initDataTable();
        JumlahDataTable();
    }
    
    private void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pointofsales", "root", "");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private int waktumulai = 0;
    private void JamRealTime(){
        new Thread(){
            @Override
            public void run(){
                while(waktumulai == 0){
                    Calendar kalender = new GregorianCalendar();
                    int jam     = kalender.get(Calendar.HOUR);
                    int menit   = kalender.get(Calendar.MINUTE);
                    int detik   = kalender.get(Calendar.SECOND);
                    int AMPM    = kalender.get(Calendar.AM_PM);
                    String SiangMalam;
                    if (AMPM == 1){
                        SiangMalam = "PM";
                    }else{
                        SiangMalam = "AM";
                    }
                    String JamRealTime = jam + ":" + menit + ":" + detik + " " + SiangMalam;
                    LabelJam.setText("Jam: " + JamRealTime);
                }
            }
        }.start();
    }
    
    private void initDataTable(){
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("No");
        t.addColumn("Nama");
        t.addColumn("Email");
        t.addColumn("Username");
        
        PenggunaTabel.setModel(t);
        try {
            res = conn.createStatement().executeQuery("SELECT * FROM "+TabelPengguna+" ");
            while(res.next()){
                t.addRow(new Object[]{
                    res.getString("id"),
                    res.getString("nama"),
                    res.getString("email"),
                    res.getString("username")
                });
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void JumlahDataTable() {
        DefaultTableModel t = new DefaultTableModel();
        LabelJumlahData.setText("Jumlah Data: " + PenggunaTabel.getRowCount());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NamaPengguna = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EmailPengguna = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LabelJam = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ButtonSimpanPengguna = new javax.swing.JButton();
        ButtonBersihkanPengguna = new javax.swing.JButton();
        ButtonKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PenggunaTabel = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        UsernamePengguna = new javax.swing.JTextField();
        PasswordPengguna = new javax.swing.JPasswordField();
        LabelJumlahData = new javax.swing.JLabel();
        ButtonRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Formulir: Data Pengguna");

        jLabel4.setText("Nama");

        jLabel5.setText("Email");

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Dashboard / Master Pengguna");

        jLabel2.setText("Versi: 1.0");

        LabelJam.setText("Jam");

        jLabel6.setText("Password");

        ButtonSimpanPengguna.setText("Kirim");
        ButtonSimpanPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSimpanPenggunaActionPerformed(evt);
            }
        });

        ButtonBersihkanPengguna.setText("Bersih");
        ButtonBersihkanPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBersihkanPenggunaActionPerformed(evt);
            }
        });

        ButtonKembali.setBackground(new java.awt.Color(255, 255, 255));
        ButtonKembali.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ButtonKembali.setText("Kembali");
        ButtonKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonKembaliActionPerformed(evt);
            }
        });

        PenggunaTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(PenggunaTabel);

        jLabel7.setText("Username");

        LabelJumlahData.setText("Jumlah Data:");

        ButtonRefresh.setText("Refresh");
        ButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGap(39, 39, 39))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UsernamePengguna)
                                    .addComponent(EmailPengguna)
                                    .addComponent(NamaPengguna)
                                    .addComponent(PasswordPengguna)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 69, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ButtonBersihkanPengguna)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ButtonSimpanPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LabelJumlahData)
                                        .addGap(389, 389, 389))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonRefresh))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonKembali)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelJam)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ButtonKembali))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(EmailPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(UsernamePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PasswordPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonSimpanPengguna)
                            .addComponent(ButtonBersihkanPengguna)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelJumlahData)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonRefresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelJam)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonKembaliActionPerformed
        new Dashboard().setVisible(true); dispose();
    }//GEN-LAST:event_ButtonKembaliActionPerformed

    private void ButtonBersihkanPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBersihkanPenggunaActionPerformed
       NamaPengguna.setText("");
       EmailPengguna.setText("");
       PasswordPengguna.setText("");
    }//GEN-LAST:event_ButtonBersihkanPenggunaActionPerformed

    private void ButtonSimpanPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSimpanPenggunaActionPerformed
        String Nama, Username, Email, Pwd;
        
        Nama        = NamaPengguna.getText();
        Username    = UsernamePengguna.getText();
        Email       = EmailPengguna.getText();
        Pwd         = PasswordPengguna.getText();
        
        try {
            conn.createStatement().executeUpdate("INSERT INTO pengguna (nama, email, username, password) VALUES ("
                + "'" + Nama + "',"
                + "'" + Username + "',"
                + "'" + Email + "',"
                + "'" + Pwd +"')");
            JOptionPane.showMessageDialog(null, "Berhasil menyimpan data!");
            initDataTable();
        } catch (Exception e){
            // tampilkan pesan kesalahan try catch
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_ButtonSimpanPenggunaActionPerformed

    private void ButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRefreshActionPerformed
        initDataTable();
    }//GEN-LAST:event_ButtonRefreshActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengguna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBersihkanPengguna;
    private javax.swing.JButton ButtonKembali;
    private javax.swing.JButton ButtonRefresh;
    private javax.swing.JButton ButtonSimpanPengguna;
    private javax.swing.JTextField EmailPengguna;
    private javax.swing.JLabel LabelJam;
    private javax.swing.JLabel LabelJumlahData;
    private javax.swing.JTextField NamaPengguna;
    private javax.swing.JPasswordField PasswordPengguna;
    private javax.swing.JTable PenggunaTabel;
    private javax.swing.JTextField UsernamePengguna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
