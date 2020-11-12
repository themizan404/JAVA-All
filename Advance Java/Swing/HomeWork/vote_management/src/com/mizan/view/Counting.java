package com.mizan.view;

import com.mizan.connection.DBConnection;
import com.mizan.pojo.Candidate;
import com.mizan.pojo.ImageModel;
import com.mizan.pojo.PartyCount;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohdm
 */
public class Counting extends javax.swing.JFrame {

    /**
     * Creates new form Addvoter
     */
    public Counting() {
        initComponents();
        showCandidate();
        showCounting();

    }
static Connection connect = DBConnection.getConnection();
    public ArrayList<Candidate> candidatelist() {
        ArrayList<Candidate> candidatelist = new ArrayList<>();
        try {
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote_management", "root", "12345");
            String sql = "select * from addcandidate";
            Statement ps = connect.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            Candidate candidate;
            while (rs.next()) {
                candidate = new Candidate(rs.getString("candidateid"), rs.getString("partyname"), rs.getString("partyleader"), rs.getString("sex"), rs.getString("age"), rs.getString("partyHQ"), rs.getString("partysign"), rs.getBytes("candidate_image"));
                candidatelist.add(candidate);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Counting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidatelist;
    }

    public void showCandidate() {
        ArrayList<Candidate> list = candidatelist();
        DefaultTableModel model = (DefaultTableModel) jCount.getModel();
        String[] columnName = {"CandidateID", "Name", "Party Leader", "Sex", "Age", "Party HQ", "Party Sign", "Image"};

        Object[][] row = new Object[list.size()][10];;
        for (int i = 0; i < list.size(); i++) {
            row[i][0] = list.get(i).getCandidateid();
            row[i][1] = list.get(i).getName();
            row[i][2] = list.get(i).getParty_leader();
            row[i][3] = list.get(i).getSex();
            row[i][4] = list.get(i).getAge();
            row[i][5] = list.get(i).getPartyHQ();
            row[i][6] = list.get(i).getPartysign();
            if (list.get(i).getCandidateImage() != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getCandidateImage()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                row[i][7] = image;
            } else {
                row[i][7] = null;
            }
            model.addRow(row);
        }
        ImageModel modelimage = new ImageModel(row, columnName);
        jCount.setModel(modelimage);
        jCount.setRowHeight(120);
        jCount.getColumnModel().getColumn(7).setPreferredWidth(150);
    }

    public ArrayList<PartyCount> partyCountlist() {
        ArrayList<PartyCount> partyCountlist;
        partyCountlist = new ArrayList<>();
        try {

//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote_management", "root", "12345");
            String sql = "select partyname,count from result";
            Statement ps = connect.createStatement();

            ResultSet rs = ps.executeQuery(sql);
            PartyCount counting;
            while (rs.next()) {
                counting = new PartyCount(rs.getInt("count"), rs.getString("partyname"));
                partyCountlist.add(counting);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Addvoter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partyCountlist;
    }

    public void showCounting() {
        ArrayList<PartyCount> list = partyCountlist();
        DefaultTableModel mode2 = (DefaultTableModel) jCountTable.getModel();

        Object[] row1 = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            row1[0] = list.get(i).getCount();
            row1[1] = list.get(i).getPartyname();

            mode2.addRow(row1);
        }
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
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCount = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jCountTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Voter");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 32));

        jCount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jCount);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1290, 400));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Counting");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 1130, -1));

        jCountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Count", "Party Name"
            }
        ));
        jScrollPane2.setViewportView(jCountTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 840, 150));

        jButton2.setIcon(new javax.swing.ImageIcon("G:\\IT Files\\Pratices Files\\All Swing\\vote_management\\src\\com\\mizan\\pic\\back.png")); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1314, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1314, 619));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Counting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Counting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTable jCount;
    private javax.swing.JTable jCountTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}