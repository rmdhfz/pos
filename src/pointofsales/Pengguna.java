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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.compilers.GroovyEvaluator;

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
        jScrollPane1 = new javax.swing.JScrollPane();
        PenggunaTabel = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        UsernamePengguna = new javax.swing.JTextField();
        PasswordPengguna = new javax.swing.JPasswordField();
        LabelJumlahData = new javax.swing.JLabel();
        ButtonRefresh = new javax.swing.JButton();
        TextPencarian = new javax.swing.JTextField();
        ButtonCari = new javax.swing.JButton();
        ButtonUpdate = new javax.swing.JButton();
        ButtonHapus = new javax.swing.JButton();
        ButtonCetak = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        MenuTransaksi = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Formulir: Data Pengguna");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Nama");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Email");

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Dashboard / Master Pengguna");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Versi: 1.0");

        LabelJam.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        LabelJam.setText("Jam");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel6.setText("Password");

        ButtonSimpanPengguna.setText("Submit");
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setText("Username");

        LabelJumlahData.setText("Jumlah Data:");

        ButtonRefresh.setText("Refresh");
        ButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRefreshActionPerformed(evt);
            }
        });

        ButtonCari.setText("Cari");
        ButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCariActionPerformed(evt);
            }
        });

        ButtonUpdate.setText("Update");
        ButtonUpdate.setEnabled(false);
        ButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdateActionPerformed(evt);
            }
        });

        ButtonHapus.setText("Hapus");
        ButtonHapus.setEnabled(false);
        ButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonHapusActionPerformed(evt);
            }
        });

        ButtonCetak.setText("Cetak");
        ButtonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGap(39, 39, 39))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UsernamePengguna)
                                    .addComponent(EmailPengguna)
                                    .addComponent(PasswordPengguna)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(NamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ButtonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ButtonBersihkanPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ButtonSimpanPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LabelJumlahData)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TextPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ButtonCari)))
                                .addGap(4, 4, 4)))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LabelJam)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelJumlahData)
                            .addComponent(TextPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonCari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(42, 42, 42)
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
                            .addComponent(ButtonBersihkanPengguna))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonUpdate)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonRefresh)
                        .addComponent(ButtonHapus)
                        .addComponent(ButtonCetak)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelJam)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jMenu6.setText("Dashboard");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu1.setText("Master Data");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Master Barang");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Master Pengguna");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        MenuTransaksi.setText("Transaksi");
        MenuTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuTransaksiMouseClicked(evt);
            }
        });
        jMenuBar1.add(MenuTransaksi);

        jMenu3.setText("Laporan");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Bantuan");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Keluar");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonBersihkanPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBersihkanPenggunaActionPerformed
       NamaPengguna.setText("");
       EmailPengguna.setText("");
       PasswordPengguna.setText("");
       
       ButtonUpdate.setEnabled(false);
       ButtonHapus.setEnabled(false);
       ButtonSimpanPengguna.setEnabled(true);
    }//GEN-LAST:event_ButtonBersihkanPenggunaActionPerformed

    private void ButtonSimpanPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSimpanPenggunaActionPerformed
        String Nama, Username, Email, Pwd;
        
        Nama        = NamaPengguna.getText();
        Username    = UsernamePengguna.getText();
        Email       = EmailPengguna.getText();
        Pwd         = PasswordPengguna.getText();
        
        if (Nama.trim().isEmpty() && !Nama.matches("[a-zA-Z_]+")) {
            JOptionPane.showMessageDialog(null, "Format Nama yang diizinkan: A-Z");
            return;
        }else if (Email.trim().isEmpty() && !Email.matches("[a-zA-Z0-9@.]")){
            JOptionPane.showMessageDialog(null, "Format Email yang diizinkan: a-zA-Z0-9");
            return;
        }else if (Username.trim().isEmpty() && !Username.matches("[a-z]")) {
            JOptionPane.showMessageDialog(null, "Format Username yang diizinkan: a-z");
            return;
        }
        
        try {
            conn.createStatement().executeUpdate("INSERT INTO pengguna (nama, email, username, password) VALUES ("
                + "'" + Nama + "',"
                + "'" + Username + "',"
                + "'" + Email + "',"
                + "'" + Pwd +"')");
            JOptionPane.showMessageDialog(null, "Berhasil menyimpan data!");
            initDataTable();
            JumlahDataTable();
        } catch (Exception e){
            // tampilkan pesan kesalahan try catch
            initDataTable();
            JumlahDataTable();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
    }//GEN-LAST:event_ButtonSimpanPenggunaActionPerformed

    private void ButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRefreshActionPerformed
        initDataTable();
        JumlahDataTable();
    }//GEN-LAST:event_ButtonRefreshActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
       new Dashboard().setVisible(true); dispose();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar dari aplikasi ?", "Confirmation" , JOptionPane.YES_NO_OPTION);
        if (confirm == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new Bantuan().setVisible(true); dispose();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void ButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCariActionPerformed
        // TODO add your handling code here:
        String q = TextPencarian.getText();
        if (q.matches("[a-z@0-9.A-Z_]+")){
            try {
                res = conn.createStatement().executeQuery("SELECT * FROM pengguna "
                        + "WHERE "
                        + " id = '"+q+"' OR "
                        + " nama = '"+q+"' OR "
                        + " email = '"+q+"' OR"
                        + " username = '"+q+"' ");
                while (res.next()){
                    NamaPengguna.setText(res.getString("nama"));
                    EmailPengguna.setText(res.getString("email"));
                    UsernamePengguna.setText(res.getString("username"));
                    PasswordPengguna.setText(res.getString("password"));
                }
                ButtonUpdate.setEnabled(true);
                ButtonHapus.setEnabled(true);
                ButtonSimpanPengguna.setEnabled(false);
                initDataTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terdapat Kesalahan: " + e);
                return;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Data yang dimasukan tidak valid.");
            return;
        }
    }//GEN-LAST:event_ButtonCariActionPerformed

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed
        // TODO add your handling code here:
        int conf = JOptionPane.showConfirmDialog(null, "Apakah kamu yakin ingin mengupdate data ini ?", "Confirmation" , JOptionPane.YES_NO_OPTION);
        if (conf == 0){
            try {
                String q = TextPencarian.getText();
                PreparedStatement st;
                String sql = "UPDATE pengguna SET "
                        + "nama = ?, "
                        + "email = ?, "
                        + "username = ?, "
                        + "password= ? "
                        + "WHERE "
                        + "nama ='"+q+"' OR "
                        + "email ='"+q+"' OR "
                        + "username = '"+q+"' ";
                st = conn.prepareStatement(sql);
                
                st.setString(1, NamaPengguna.getText());
                st.setString(2, EmailPengguna.getText());
                st.setString(3, UsernamePengguna.getText().toString());
                st.setString(4, PasswordPengguna.getText());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
                initDataTable();
            } catch (SQLException ex) {
                Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void ButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonHapusActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah kamu yakin ingin menghapus data ini ?", "Confirmation" , JOptionPane.YES_NO_OPTION);
        if (confirm == 0){
            PreparedStatement st;
            String q = TextPencarian.getText();
            try {
                String sql = "DELETE FROM pengguna "
                        + "WHERE "
                        + " id = '"+q+"' OR "
                        + " nama = '"+q+"' OR "
                        + " email = '"+q+"' OR"
                        + " username = '"+q+"'";
                st = conn.prepareStatement(sql);
                st.execute();
                JOptionPane.showMessageDialog(null, "Data berhasil menghapus data");
                initDataTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Data gagal menghapus data, karena: " + ex);
            }
        }
    }//GEN-LAST:event_ButtonHapusActionPerformed

    private void MenuTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuTransaksiMouseClicked
        // TODO add your handling code here:
        new Transaksi().setVisible(true); dispose();
    }//GEN-LAST:event_MenuTransaksiMouseClicked

    private void ButtonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCetakActionPerformed
        File namafile = new File("src/Laporan/pengguna.jasper");
        JasperPrint jp = null;
        try {
            jp = JasperFillManager.fillReport(namafile.getPath(), null, conn);
        } catch (JRException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperViewer.viewReport(jp, false);
    }//GEN-LAST:event_ButtonCetakActionPerformed

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
    private javax.swing.JButton ButtonCari;
    private javax.swing.JButton ButtonCetak;
    private javax.swing.JButton ButtonHapus;
    private javax.swing.JButton ButtonRefresh;
    private javax.swing.JButton ButtonSimpanPengguna;
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JTextField EmailPengguna;
    private javax.swing.JLabel LabelJam;
    private javax.swing.JLabel LabelJumlahData;
    private javax.swing.JMenu MenuTransaksi;
    private javax.swing.JTextField NamaPengguna;
    private javax.swing.JPasswordField PasswordPengguna;
    private javax.swing.JTable PenggunaTabel;
    private javax.swing.JTextField TextPencarian;
    private javax.swing.JTextField UsernamePengguna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
