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
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mdm
 */
public class Transaksi extends javax.swing.JFrame {
    private Connection conn;
    private Statement stat;
    private ResultSet res;
    private String t;
    private DefaultTableModel dtm;
    private int harga;
    private long transaction_id;
    private int Total;
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation (
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2
        );
        koneksi();
        initDataTable();
        setItemsBarang();
        JamRealTime();
        JumlahDataTable();
        HitungTotal();
    }
    
    private void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pointofsales", "root", "");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setItemsBarang() {
        ListBarang.removeAllItems();
        try {
            String query = "SELECT id, nama FROM barang";
            ResultSet rs;
            rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                ListBarang.addItem(rs.getString("nama"));
            }
            rs.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terdapat Kesalahan: " + e);
            return;
        }
    }
    
    private void HitungSubTotal(int jumlah) {
        int subtotal;
        subtotal = ((int) jumlah * harga);
        SubTotalBarang.setText(Integer.toString(subtotal));
    }
    
    private void HitungTotal() {
        try {
            res = conn.createStatement().executeQuery("SELECT SUM(subtotal) as Total FROM transaksi");
            while(res.next()) {
                Total = Integer.parseInt(res.getString("Total"));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terdapat Kesalahan: " + e);
            return;
        }
        TotalBelanja.setText(Integer.toString(Total));
        Diskon.setText("0");
    }
    
    private void GetSetHarga(String item) {
        try {
            res = conn.createStatement().executeQuery("SELECT harga FROM barang "
                    + "WHERE nama = '"+item+"'");
            while(res.next()) {
                harga = Integer.parseInt(res.getString("harga"));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terdapat Kesalahan: " + e);
            return;
        }
    }
    
    private void initDataTable(){
        DefaultTableModel t = new DefaultTableModel();
        
        t.addColumn("No");
        t.addColumn("Barang");
        t.addColumn("Qty");
        t.addColumn("Subtotal");
        
        TableTransaksi.setModel(t);
        try {
            res = conn.createStatement().executeQuery("SELECT "
                    + " t.id, t.subtotal, t.qty as jumlah, d.barang "
                    + " FROM transaksi t "
                    + " LEFT JOIN detail_transaksi d ON d.id_transaksi = t.id"
                    + " LEFT JOIN barang b ON d.id_barang = b.id ");
            while(res.next()){
                t.addRow(new Object[]{
                    res.getString("id"),
                    res.getString("barang"),
                    res.getString("jumlah"),
                    res.getString("subtotal")
                });
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void JumlahDataTable() {
        DefaultTableModel t = new DefaultTableModel();
        LabelJumlahData.setText("Jumlah Data: " + TableTransaksi.getRowCount());
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
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ListBarang = new javax.swing.JComboBox();
        LabelJam = new javax.swing.JLabel();
        Jumlah = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        SubTotalBarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTransaksi = new javax.swing.JTable();
        LabelJumlahData = new javax.swing.JLabel();
        ButtonRefresh = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        TambahTransaksi = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        JumlahUang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Kembalian = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TotalBelanja = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Diskon = new javax.swing.JTextField();
        ButtonCheckout = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
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

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Dashboard / Transaksi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Formulir: Transaksi");

        jLabel2.setText("Barang");

        ListBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ListBarangItemStateChanged(evt);
            }
        });

        LabelJam.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        LabelJam.setText("Jam");

        Jumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JumlahFocusLost(evt);
            }
        });
        Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahActionPerformed(evt);
            }
        });
        Jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JumlahKeyTyped(evt);
            }
        });

        jLabel6.setText("Subtotal");

        SubTotalBarang.setEnabled(false);

        TableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableTransaksi);

        LabelJumlahData.setText("Jumlah Data:");

        ButtonRefresh.setText("Refresh");
        ButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRefreshActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Versi: 1.0");

        TambahTransaksi.setText("Tambah");
        TambahTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahTransaksiActionPerformed(evt);
            }
        });

        jLabel5.setText("Jumlah Uang");

        JumlahUang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JumlahUangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JumlahUangKeyReleased(evt);
            }
        });

        jLabel7.setText("Kembalian");

        Kembalian.setEnabled(false);

        jLabel8.setText("Total Belanja");

        TotalBelanja.setEnabled(false);
        TotalBelanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalBelanjaActionPerformed(evt);
            }
        });

        jLabel9.setText("Diskon");

        Diskon.setEnabled(false);

        ButtonCheckout.setText("Checkout");
        ButtonCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCheckoutActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Formulir: Checkout");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LabelJumlahData)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonRefresh)))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6))
                            .addGap(35, 35, 35)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(ListBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(SubTotalBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(206, 206, 206)
                            .addComponent(TambahTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel9))
                                            .addGap(19, 19, 19)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(JumlahUang)
                                                .addComponent(Diskon)
                                                .addComponent(Kembalian)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(TotalBelanja)))
                                    .addGap(2, 2, 2))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ButtonCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(74, 74, 74)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelJam)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17)
                        .addComponent(LabelJam)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ListBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(SubTotalBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TambahTransaksi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalBelanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Diskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(JumlahUang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(ButtonCheckout)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonRefresh)
                            .addComponent(LabelJumlahData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addContainerGap())))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        new Dashboard().setVisible(true); dispose();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MenuTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuTransaksiMouseClicked
        // TODO add your handling code here:
        new Transaksi().setVisible(true); dispose();
    }//GEN-LAST:event_MenuTransaksiMouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new Bantuan().setVisible(true); dispose();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar dari aplikasi ?", "Confirmation" , JOptionPane.YES_NO_OPTION);
        if (confirm == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void ButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRefreshActionPerformed
        initDataTable();
        JumlahDataTable();
    }//GEN-LAST:event_ButtonRefreshActionPerformed

    private void TotalBelanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalBelanjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalBelanjaActionPerformed

    private void JumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JumlahKeyPressed

    private void JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahActionPerformed

    private void JumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JumlahKeyReleased

    private void JumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JumlahKeyTyped

    private void JumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JumlahFocusLost
        // TODO add your handling code here:
        try{
            String jumlah = Jumlah.getText();
            HitungSubTotal(Integer.parseInt(jumlah));
        } catch(NumberFormatException ex){ // handle your exception
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex);
        }
    }//GEN-LAST:event_JumlahFocusLost

    private void ListBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ListBarangItemStateChanged
        // TODO add your handling code here:
        String item = (String) ListBarang.getSelectedItem();
        GetSetHarga(item);
    }//GEN-LAST:event_ListBarangItemStateChanged

    private void TambahTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahTransaksiActionPerformed
        // TODO add your handling code here:
        
        String Barang, BarangID, Qty, Subtotal;
        Barang = (String) ListBarang.getSelectedItem();
        
        Qty = Jumlah.getText();
        Subtotal = SubTotalBarang.getText();
        
        try {
            PreparedStatement statement;
            statement = conn.prepareStatement("INSERT INTO transaksi (qty, subtotal) VALUES (?,?)", new String[] {"id"});
            statement.setString(1, Qty);
            statement.setString(2, Subtotal);
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if (res.next()) {
                transaction_id = res.getLong(1);
            }
            conn.createStatement().executeUpdate("INSERT INTO detail_transaksi (id_transaksi, barang) VALUES ("
                + "'" + transaction_id + "',"
                + "'" + Barang + "')");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah!");
            initDataTable();
            JumlahDataTable();
        } catch (Exception e){
            initDataTable();
            JumlahDataTable();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_TambahTransaksiActionPerformed

    private void ButtonCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCheckoutActionPerformed
        // TODO add your handling code here:
        String Jumlah_Uang, Kembalian_Uang;
        Jumlah_Uang = JumlahUang.getText();
        Kembalian_Uang  = Kembalian.getText();
        try {
            conn.createStatement().executeUpdate("INSERT INTO pembayaran (id_transaksi, jumlah_uang, kembalian) VALUES ("
                + "'" + transaction_id + "',"
                + "'" + Jumlah_Uang + "',"
                + "'" + Kembalian_Uang +"')");
            JOptionPane.showMessageDialog(null, "Pembayaran berhasil disimpan!");
        }  catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_ButtonCheckoutActionPerformed

    private void JumlahUangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahUangKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JumlahUangKeyPressed

    private void JumlahUangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JumlahUangKeyReleased
        // TODO add your handling code here:
        Kembalian.setText("");
        if (JumlahUang.getText().trim().isEmpty()) {
            Kembalian.setText("");
            return;
        }
        int Uang = Integer.parseInt(JumlahUang.getText());
        int Kembali = Uang - Total;
        Kembalian.setText(Integer.toString(Kembali));
    }//GEN-LAST:event_JumlahUangKeyReleased
    
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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCheckout;
    private javax.swing.JButton ButtonRefresh;
    private javax.swing.JTextField Diskon;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JTextField JumlahUang;
    private javax.swing.JTextField Kembalian;
    private javax.swing.JLabel LabelJam;
    private javax.swing.JLabel LabelJumlahData;
    private javax.swing.JComboBox ListBarang;
    private javax.swing.JMenu MenuTransaksi;
    private javax.swing.JTextField SubTotalBarang;
    private javax.swing.JTable TableTransaksi;
    private javax.swing.JButton TambahTransaksi;
    private javax.swing.JTextField TotalBelanja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
