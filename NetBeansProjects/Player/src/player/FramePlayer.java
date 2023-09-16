package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author tomea
 */
public class FramePlayer extends javax.swing.JFrame {

    javazoom.jl.player.Player Player;
    javazoom.jl.player.advanced.AdvancedPlayer pl;
    Flow flow = new Flow();
    ArrayList SongList = new ArrayList();
    File Cancion;
    boolean play = true;
    String currentSong;
    boolean isPaused;
    private int pausedPosition=0;
  

    public FramePlayer() {
        initComponents();
        setLocationRelativeTo(null);
        
        
    }



    public void PlaySong() {
        int IndiceSeleccionado = SelectSong();
        if (play) {
            try {
                //Guarda en cancion el indice del archivo (la cancion)
                Cancion = (File) this.SongList.get(IndiceSeleccionado);
                currentSong = playlist.getModel().getElementAt(IndiceSeleccionado);
                FileInputStream song = new FileInputStream(Cancion);
                BufferedInputStream songg = new BufferedInputStream(song);
                Player = new javazoom.jl.player.Player(songg);
                pl=new javazoom.jl.player.advanced.AdvancedPlayer(songg);
                if (isPaused) {
                    pl.play(pausedPosition, Integer.MAX_VALUE);
                }
                play = false;
                jLabel2.setText(currentSong);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una canción");
            }
            new Thread() {
                @Override
                public void run() {
                    try {
                        Player.play();
                    } catch (Exception e) {
                    }
                }
            }
                    .start();
        } else {
            Player.close();
            play = true;
            PlaySong();
        }
    }
    
    //FUNCION SELECT
    public int SelectSong() {
        int IndiceSeleccionado = playlist.getSelectedIndex();
        
        return IndiceSeleccionado;
    }
    
    //ACTUALIZA LA PLAYLIST
    public void Update() {
        SongList = flow.getArregloCanciones();
        DefaultListModel listModel = new DefaultListModel();

        for (int indice = 0; indice < SongList.size(); indice++) {
            int indice2 = indice + 1;
            listModel.add(indice, indice2 + "- " + ((File) SongList.get(indice)).getName());
        }
        playlist.setModel(listModel);
    }
    
    //FUNCION ADD - agrega a la playlist
    public void Add() {
        flow.addSong(this);
        Update();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playlist = new javax.swing.JList<>();
        Add = new javax.swing.JButton();
        Play = new javax.swing.JButton();
        Pause = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Select = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Stop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        playlist.setBackground(new java.awt.Color(204, 204, 204));
        playlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playlistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(playlist);

        Add.setBackground(new java.awt.Color(255, 255, 255));
        Add.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        Add.setForeground(new java.awt.Color(0, 102, 153));
        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Play.setBackground(new java.awt.Color(255, 255, 255));
        Play.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        Play.setForeground(new java.awt.Color(0, 102, 153));
        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });

        Pause.setBackground(new java.awt.Color(255, 255, 255));
        Pause.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        Pause.setForeground(new java.awt.Color(0, 102, 153));
        Pause.setText("Pause");
        Pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PauseActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("MP3 PLAYER - FLOW");

        Select.setBackground(new java.awt.Color(255, 255, 255));
        Select.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        Select.setForeground(new java.awt.Color(0, 102, 153));
        Select.setText("Select");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Current Song: ");

        Stop.setBackground(new java.awt.Color(255, 255, 255));
        Stop.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        Stop.setForeground(new java.awt.Color(0, 102, 153));
        Stop.setText("Stop");
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Play)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Select)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Pause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Stop))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Play)
                    .addComponent(Pause)
                    .addComponent(Select)
                    .addComponent(Add)
                    .addComponent(Stop))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //BTN CON FUNCIONES
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        Add();
    }//GEN-LAST:event_AddActionPerformed

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        PlaySong();
    }//GEN-LAST:event_PlayActionPerformed

    private void PauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PauseActionPerformed
        
       
    }//GEN-LAST:event_PauseActionPerformed

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        SelectSong();
    }//GEN-LAST:event_SelectActionPerformed

    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        Player.close();
    }//GEN-LAST:event_StopActionPerformed

    private void playlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_playlistMouseClicked

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
            java.util.logging.Logger.getLogger(FramePlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePlayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePlayer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Pause;
    private javax.swing.JButton Play;
    private javax.swing.JButton Select;
    private javax.swing.JButton Stop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> playlist;
    // End of variables declaration//GEN-END:variables
}
