package javalab1;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class MainFrame extends javax.swing.JFrame {

    private final ArrayList<RecIntegral> dataList = new ArrayList<>();

    public MainFrame() {
        initComponents();
        setTitle("Лабораторная работа");
        setLocationRelativeTo(null);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        uppr = new javax.swing.JTextField();
        downpr = new javax.swing.JTextField();
        st3p = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        clac = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        fillBtn = new javax.swing.JButton();
        saveTxtBtn = new javax.swing.JButton();
        loadTxtBtn = new javax.swing.JButton();
        saveBinBtn = new javax.swing.JButton();
        loadBinBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Лабораторная работа - f(x) = 1/ln(x)");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        uppr.setColumns(10);

        downpr.setColumns(10);

        st3p.setColumns(10);

        jLabel1.setText("Верх. пр.");

        jLabel2.setText("Ниж. пр.");

        jLabel3.setText("Шаг");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uppr, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downpr, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(st3p, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uppr, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downpr, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(st3p, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        jLabel4.setText("Функция: 1/ln(x)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        add.setText("Добавить");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setText("Удалить");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        clac.setText("Вычислить");
        clac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clacActionPerformed(evt);
            }
        });

        clearBtn.setText("Очистить");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        fillBtn.setText("Заполнить");
        fillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillActionPerformed(evt);
            }
        });

        saveTxtBtn.setText("Сохранить(текст)");
        saveTxtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTxtBtnActionPerformed(evt);
            }
        });

        loadTxtBtn.setText("Загрузить(текст)");
        loadTxtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTxtBtnActionPerformed(evt);
            }
        });

        saveBinBtn.setText("Сохранить(Двоичный)");
        saveBinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBinBtnActionPerformed(evt);
            }
        });

        loadBinBtn.setText("Загрузить(Двоичный)");
        loadBinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBinBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fillBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clac, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveTxtBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadTxtBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveBinBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadBinBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add)
                            .addComponent(saveTxtBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadTxtBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clac)
                            .addComponent(saveBinBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearBtn)
                            .addComponent(loadBinBtn)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Нижний предел", "Верхний предел", "Шаг", "Результат"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        try {
            double low = Double.parseDouble(downpr.getText());
            double up = Double.parseDouble(uppr.getText());
            double stepVal = Double.parseDouble(st3p.getText());

            RecIntegral rec = new RecIntegral(low, up, stepVal);
            dataList.add(rec);

            DefaultTableModel    tModel = (DefaultTableModel) jTable1.getModel();
            tModel.addRow(new Object[]{low, up, stepVal, ""});
            
        }
      catch (InvalidException ex) {
    JOptionPane.showMessageDialog(
        this,
        ex.getMessage() + "\nНекорректное значение: " + ex.getErrVal(),
        "Ошибка диапазона",
        JOptionPane.ERROR_MESSAGE
    );
}
        
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректные числовые значения!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
          
        }
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            dataList.remove(selectedRow);
            ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Выберите строку для удаления!");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void clacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clacActionPerformed
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int rowCount = model.getRowCount();

    for (int i = 0; i < rowCount; i++) {
        if (!model.getValueAt(i, 3).toString().isEmpty()) continue;

        double low = Double.parseDouble(model.getValueAt(i, 0).toString());
        double up = Double.parseDouble(model.getValueAt(i, 1).toString());
        double step = Double.parseDouble(model.getValueAt(i, 2).toString());
        
        int rowIndex = i;
        
        calculateRowParallel(low, up, step, rowIndex, model);
    }
    }//GEN-LAST:event_clacActionPerformed
    private void calculateRowParallel(double lower, double upper, double step, int rowIndex, DefaultTableModel model) {
    long startTime = System.nanoTime();
    int threadCount = 7;
    Thread[] threads = new Thread[threadCount];
    double[] partialResults = new double[threadCount];
    
    double fullInterval = upper - lower;
    double delta = fullInterval / threadCount;

    for (int i = 0; i < threadCount; i++) {
        double start = lower + i * delta;
        double end = (i == threadCount - 1) ? upper : start + delta;
        
        threads[i] = new Thread(new CalculationThread(start, end, step, i, partialResults));
        threads[i].start();
    }

    new Thread(() -> {
        try {
            for (Thread t : threads) {
                t.join();
            }
            
            double totalSum = 0;
            for (double res : partialResults) {
                totalSum += res;
            }

            final String finalResult = String.format("%.6f", totalSum).replace(",", ".");
            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000;
            javax.swing.SwingUtilities.invokeLater(() -> {
                model.setValueAt(finalResult, rowIndex, 3);
                System.out.println("potokov: " + threadCount + " | vremya: " + durationMs + " ms");
            });
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }).start();
}
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
    }//GEN-LAST:event_clearActionPerformed

    private void fillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillActionPerformed
        DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
        tModel.setRowCount(0);
        for (RecIntegral rec : dataList) {
            tModel.addRow(new Object[]{
                rec.getLowerBound(),
                rec.getUpperBound(),
                rec.getStep(),
                rec.getResult()
            });
        }
    }//GEN-LAST:event_fillActionPerformed

    private void saveTxtBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTxtBtnActionPerformed
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setCurrentDirectory(new java.io.File("C:\\Users\\alex\\Desktop\\for university\\3 kyrs\\6 семетр\\java\\тесты"));
        if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fc.getSelectedFile();

            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new java.io.File(file.getAbsolutePath() + ".txt");
            }

            try (java.io.FileWriter fw = new java.io.FileWriter(file)) {
                for (RecIntegral rec : dataList) {
                    fw.write(rec.toString() + "\n");
                }
            } catch (java.io.IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(
                    this, 
                    "Ошибка записи: " + ex.getMessage()
                );
            }
        }

    }//GEN-LAST:event_saveTxtBtnActionPerformed

    private void loadTxtBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTxtBtnActionPerformed
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
         fc.setCurrentDirectory(new java.io.File("C:\\Users\\alex\\Desktop\\for university\\3 kyrs\\6 семетр\\java\\тесты"));
        if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fc.getSelectedFile()))) {
                dataList.clear(); 
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split(" ");
                    if (p.length >= 3) {
                        RecIntegral rec = new RecIntegral(
                            Double.parseDouble(p[0]), 
                            Double.parseDouble(p[1]), 
                            Double.parseDouble(p[2])
                        );
                        if (p.length == 4) rec.setResult(p[3]);
                        dataList.add(rec);
                    }
                }
                fillActionPerformed(evt); 
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ошибка чтения: " + ex.getMessage());
            }
        }
    
        // TODO add your handling code here:
    }//GEN-LAST:event_loadTxtBtnActionPerformed

    private void saveBinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBinBtnActionPerformed
    JFileChooser fc = new JFileChooser();
    fc.setCurrentDirectory(new java.io.File("C:\\Users\\alex\\Desktop\\for university\\3 kyrs\\6 семетр\\java\\тесты"));
    if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        java.io.File file = fc.getSelectedFile();

        if (!file.getName().toLowerCase().endsWith(".bin")) {
            file = new java.io.File(file.getAbsolutePath() + ".bin");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(dataList);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this, 
                "Ошибка сериализации: " + ex.getMessage()
            );
        }
    }

    }//GEN-LAST:event_saveBinBtnActionPerformed

    private void loadBinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBinBtnActionPerformed
javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
fc.setCurrentDirectory(new java.io.File("C:\\Users\\alex\\Desktop\\for university\\3 kyrs\\6 семетр\\java\\тесты"));
        if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(fc.getSelectedFile()))) {
                Object readData = ois.readObject();
                if (readData instanceof java.util.ArrayList) {
                    dataList.clear();
                    dataList.addAll((java.util.ArrayList<RecIntegral>) readData);
                    fillActionPerformed(evt);
                }
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ошибка десериализации: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_loadBinBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton clac;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton delete;
    private javax.swing.JTextField downpr;
    private javax.swing.JButton fillBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton loadBinBtn;
    private javax.swing.JButton loadTxtBtn;
    private javax.swing.JButton saveBinBtn;
    private javax.swing.JButton saveTxtBtn;
    private javax.swing.JTextField st3p;
    private javax.swing.JTextField uppr;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
    private void listenForResults(DatagramSocket socket, DefaultTableModel model) {
    new Thread(() -> {
        try {
            socket.setSoTimeout(10000); 
            while (true) {
                byte[] buffer = new byte[4096];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); 
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
                RecIntegral resultRec = (RecIntegral) ois.readObject();

    javax.swing.SwingUtilities.invokeLater(() -> {
        for (int i = 0; i < model.getRowCount(); i++) {
            double low = Double.parseDouble(model.getValueAt(i, 0).toString());
            double up = Double.parseDouble(model.getValueAt(i, 1).toString());
            double step = Double.parseDouble(model.getValueAt(i, 2).toString());

            if (Double.compare(low, resultRec.getLowerBound()) == 0 &&
                Double.compare(up, resultRec.getUpperBound()) == 0 &&
                Double.compare(step, resultRec.getStep()) == 0) {

                if (model.getValueAt(i, 3).toString().isEmpty()) {
                    model.setValueAt(resultRec.getResult(), i, 3);
                    break; 
                        }
                }
                    }
                });
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Время ожидания ответов истекло.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
}
}


