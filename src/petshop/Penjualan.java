/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Rafly Renaldy
 */
public class Penjualan extends javax.swing.JFrame {

    /**
     * Creates new form Penjualan
     */
    public Penjualan() {
        initComponents();
        tampil_combo();
        tampil_combo2();
        tampil_combo3();
        GetData();
    }
    private void cleardata(){
         txtKodeTransaksi.setText("");
        txtNamaBarang.setText("");
        cmbKodeBarang.setSelectedItem("Pilih");
         cmbIdPegawai.setSelectedItem("Pilih");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtTotal.setText("");
    }
      public void insert_data(){
              try {
            String sql = "INSERT INTO penjualan(kode_pegawai, kode_barang, jumlah, total) VALUES ('"+cmbIdPegawai.getSelectedItem()+"','"+cmbKodeBarang.getSelectedItem()+"','"+txtJumlah.getText()+"','"+txtTotal.getText()+"')";
            java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "berhasil disimpan");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
        public void delete_data(){
               try { // hapus data
        String sql ="delete from penjualan where kode_penjualan='"+cmbAksi.getSelectedItem()+"'";
        java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Data akan dihapus");
        
    } catch (SQLException | HeadlessException e) {}
    
        }
        public void search_data(){
            if(cmbAksi.getSelectedItem()=="Semua"){
        GetData();
        
        }else{
            
            
        try {
        Connection conn;
        conn = (Connection)koneksi.koneksiDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select penjualan.kode_penjualan,pegawai.nama_pegawai,barang.nama_barang, barang.harga,barang.jenis,penjualan.jumlah,penjualan.total from pegawai inner join (penjualan inner join barang on penjualan.kode_barang = barang.kode_barang ) on penjualan.kode_pegawai = pegawai.kode_pegawai where kode_penjualan='"+cmbAksi.getSelectedItem()+"'");
        tblPenjualan.setModel(DbUtils.resultSetToTableModel(sql));
    }
    catch (SQLException | HeadlessException e) {
    }
            }
        }
        public void update_data(){
            try {
            String sql = "update penjualan SET kode_pegawai='"+cmbIdPegawai.getSelectedItem()+"',kode_barang='"+cmbKodeBarang.getSelectedItem()+"',jumlah='"+txtJumlah.getText()+"',total='"+txtTotal.getText()+"' where kode_penjualan='"+txtKodeTransaksi.getText()+"'";
            java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "berhasil diubah");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    private void GetData(){ // menampilkan data dari database
    try {
        Connection conn;
        conn = (Connection)koneksi.koneksiDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select penjualan.kode_penjualan,pegawai.nama_pegawai,barang.nama_barang, barang.harga,barang.jenis,penjualan.jumlah,penjualan.total from pegawai inner join (penjualan inner join barang on penjualan.kode_barang = barang.kode_barang ) on penjualan.kode_pegawai = pegawai.kode_pegawai order by penjualan.kode_penjualan asc");
        tblPenjualan.setModel(DbUtils.resultSetToTableModel(sql));
    }
    catch (SQLException | HeadlessException e) {
    }
}
public void tampil_combo()
    {
        try {
        java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
        Statement stt = conn.createStatement();
        String sql = "select kode_penjualan from penjualan order by kode_penjualan asc";      // disini saya menampilkan NIM, anda dapat menampilkan
        ResultSet res = stt.executeQuery(sql);                                // yang anda ingin kan
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0] = res.getString(1);
            
            cmbAksi.addItem((String) ob[0]);                                      // fungsi ini bertugas menampung isi dari database
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
public void tampil_combo2()
    {
        try {
        java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
        Statement stt = conn.createStatement();
        String sql = "select kode_pegawai from pegawai order by kode_pegawai asc";      // disini saya menampilkan NIM, anda dapat menampilkan
        ResultSet res = stt.executeQuery(sql);                                // yang anda ingin kan
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0] = res.getString(1);
            
            cmbIdPegawai.addItem((String) ob[0]);                                      // fungsi ini bertugas menampung isi dari database
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
public void tampil_combo3()
    {
        try {
        java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
        Statement stt = conn.createStatement();
        String sql = "select kode_barang from barang order by kode_barang asc";      // disini saya menampilkan NIM, anda dapat menampilkan
        ResultSet res = stt.executeQuery(sql);                                // yang anda ingin kan
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0] = res.getString(1);
            
            cmbKodeBarang.addItem((String) ob[0]);                                      // fungsi ini bertugas menampung isi dari database
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

public void hitung(){
    int jumlah = Integer.parseInt(txtJumlah.getText());

int harga = Integer.parseInt(txtHarga.getText());


int total;
total = (int) (jumlah * (harga + (harga * 0.05)));
txtTotal.setText(String.valueOf(total));

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        btnHitung = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPenjualan = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        txtHarga = new javax.swing.JTextField();
        btnUbah = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbAksi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        cmbIdPegawai = new javax.swing.JComboBox<>();
        cmbKodeBarang = new javax.swing.JComboBox<>();
        txtNamaBarang = new javax.swing.JTextField();
        txtKodeTransaksi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Penjualan");

        jLabel5.setText("Jumlah");

        btnHitung.setText("Hitung");

        jLabel6.setText("Total");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnCari.setText("Cari ");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        tblPenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode Penjualan", "Nama Pegawai", "Nama Barang", "Harga", "Jenis", "Jumlah", "Total"
            }
        ));
        tblPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPenjualan);

        btnHapus.setText("Hapus");
        btnHapus.setToolTipText("");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.setToolTipText("");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        jLabel1.setText("Id Pegawai");

        cmbAksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Semua" }));

        jLabel2.setText("Kode Barang");

        txtJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtJumlahFocusLost(evt);
            }
        });

        jLabel3.setText("Harga");

        txtTotal.setToolTipText("");
        txtTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTotalFocusLost(evt);
            }
        });

        cmbIdPegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));

        cmbKodeBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cmbKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKodeBarangActionPerformed(evt);
            }
        });

        txtNamaBarang.setEnabled(false);

        txtKodeTransaksi.setEnabled(false);

        jLabel4.setText("Kode Transaksi");

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKodeTransaksi)
                            .addComponent(cmbKodeBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(cmbIdPegawai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUbah))
                            .addComponent(btnHitung)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbAksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHapus)
                        .addGap(73, 73, 73)
                        .addComponent(btnCari)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKodeTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbIdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(btnHitung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate)
                            .addComponent(btnUbah)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCari)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        insert_data();
    
    GetData();
        cleardata();
        
       
    }//GEN-LAST:event_btnCreateActionPerformed

    private void txtTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTotalFocusLost
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_txtTotalFocusLost

    private void cmbKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKodeBarangActionPerformed
        // TODO add your handling code here:
        try {
        Connection conn;
        conn = (Connection)koneksi.koneksiDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select nama_barang, harga from barang where kode_barang='"+cmbKodeBarang.getSelectedItem()+"'");
       if(sql.next()){
        String harga = sql.getString("harga");
            txtHarga.setText(String.valueOf(harga));
             String nama_barang = sql.getString("nama_barang");
            txtNamaBarang.setText(String.valueOf(nama_barang));
       }
         }
    catch (SQLException | HeadlessException e) {
    }
    }//GEN-LAST:event_cmbKodeBarangActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
         update_data();
    
    GetData();
    cleardata();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenjualanMouseClicked
        // TODO add your handling code here:
         try {
        int row =tblPenjualan.getSelectedRow();
        String tabel_klik=(tblPenjualan.getModel().getValueAt(row, 0).toString());
        java.sql.Connection conn =(java.sql.Connection)koneksi.koneksiDB();
        java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select * from penjualan where kode_penjualan ='"+tabel_klik+"'");
        if(sql.next()){
            String kode_pembelian = sql.getString("kode_penjualan");
            txtKodeTransaksi.setText(kode_pembelian);
            String kode_pegawai = sql.getString("kode_pegawai");
            cmbIdPegawai.setSelectedItem(kode_pegawai);            
            String kode_barang = sql.getString("kode_barang");
            cmbKodeBarang.setSelectedItem(kode_barang);
            String jumlah = sql.getString("jumlah");
            txtJumlah.setText(jumlah);
            String total = sql.getString("total");
            txtTotal.setText(total);
            
}
    } catch (Exception e) {}
    }//GEN-LAST:event_tblPenjualanMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
      delete_data();
    GetData();
   
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        search_data();
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtJumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJumlahFocusLost
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_txtJumlahFocusLost

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the commanentsd line argum
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
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbAksi;
    private javax.swing.JComboBox<String> cmbIdPegawai;
    private javax.swing.JComboBox<String> cmbKodeBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPenjualan;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKodeTransaksi;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
