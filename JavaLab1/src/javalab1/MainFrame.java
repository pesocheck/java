package javalab1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends javax.swing.JFrame {

    private final ArrayList<RecIntegral> dataList = new ArrayList<>();
    private final String[] clientAddresses = {"127.0.0.1", "127.0.0.1", "127.0.0.1"};
    private final int[] clientPorts = {12345, 12347, 12348};
    private final Map<Integer, Integer> pendingResults = new HashMap<>();
    private final Map<Integer, Double> partialSums = new HashMap<>();
    private final Map<Integer, Integer> taskIdToRow = new HashMap<>();
    
    private DatagramSocket serverSocket;

    public MainFrame() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                System.setOut(new PrintStream(System.out, true, "CP866"));
            } else {
                System.setOut(new PrintStream(System.out, true, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initComponents();
        setTitle("Lab6 - UDP Server (Variant 7) - 3 clients");
        setLocationRelativeTo(null);
        startResultListener();
    }
    
    private void startResultListener() {
        new Thread(() -> {
            try {
                serverSocket = new DatagramSocket(12346);
                System.out.println("Server listening on port 12346...");
                
                while (true) {
                    byte[] buffer = new byte[4096];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    serverSocket.receive(packet);
                    
                    ObjectInputStream ois = new ObjectInputStream(
                        new ByteArrayInputStream(packet.getData())
                    );
                    RecIntegral resultRec = (RecIntegral) ois.readObject();
                    
                    System.out.println("Received result: " + resultRec.getResult() 
                        + " for task ID=" + resultRec.getId());
                    
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        aggregateResult(resultRec);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private void aggregateResult(RecIntegral resultRec) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        Integer rowIndex = taskIdToRow.get(resultRec.getId());
        
        if (rowIndex == null) {
            System.out.println("Unknown task ID: " + resultRec.getId());
            return;
        }
        
        double currentSum = partialSums.getOrDefault(rowIndex, 0.0);
        currentSum += Double.parseDouble(resultRec.getResult());
        partialSums.put(rowIndex, currentSum);
        
        int remaining = pendingResults.getOrDefault(rowIndex, 0) - 1;
        pendingResults.put(rowIndex, remaining);
        
        if (remaining <= 0) {
            String finalResult = String.format("%.6f", currentSum).replace(",", ".");
            model.setValueAt(finalResult, rowIndex, 3);
            System.out.println("Row " + rowIndex + " completed: " + finalResult);
            taskIdToRow.remove(resultRec.getId());
        }
    }

    @SuppressWarnings("unchecked")
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
        setTitle("Laboratory work - f(x) = 1/ln(x)");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        uppr.setColumns(10);

        downpr.setColumns(10);

        st3p.setColumns(10);

        jLabel1.setText("Upper limit");

        jLabel2.setText("Lower limit");

        jLabel3.setText("Step");

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

        jLabel4.setText("Function: 1/ln(x)");

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

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        clac.setText("Calculate");
        clac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clacActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        fillBtn.setText("Fill");
        fillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillActionPerformed(evt);
            }
        });

        saveTxtBtn.setText("Save (text)");
        saveTxtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTxtBtnActionPerformed(evt);
            }
        });

        loadTxtBtn.setText("Load (text)");
        loadTxtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTxtBtnActionPerformed(evt);
            }
        });

        saveBinBtn.setText("Save (Binary)");
        saveBinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBinBtnActionPerformed(evt);
            }
        });

        loadBinBtn.setText("Load (Binary)");
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
                "Lower limit", "Upper limit", "Step", "Result"
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
    }

    private void addActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            double low = Double.parseDouble(downpr.getText());
            double up = Double.parseDouble(uppr.getText());
            double stepVal = Double.parseDouble(st3p.getText());

            RecIntegral rec = new RecIntegral(low, up, stepVal);
            dataList.add(rec);

            DefaultTableModel tModel = (DefaultTableModel) jTable1.getModel();
            tModel.addRow(new Object[]{low, up, stepVal, ""});
            
        }
        catch (InvalidException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage() + "\nInvalid value: " + ex.getErrVal(),
                "Range error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter correct numeric values!", "Input error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            dataList.remove(selectedRow);
            ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete!");
        }
    }

    private void clacActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to calculate!");
            return;
        }
        
        if (!model.getValueAt(selectedRow, 3).toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "This row is already calculated!");
            return;
        }

        double low = Double.parseDouble(model.getValueAt(selectedRow, 0).toString());
        double up = Double.parseDouble(model.getValueAt(selectedRow, 1).toString());
        double step = Double.parseDouble(model.getValueAt(selectedRow, 2).toString());

        int clientCount = clientAddresses.length;
        double fullInterval = up - low;
        double partSize = fullInterval / clientCount;
        
        pendingResults.put(selectedRow, clientCount);
        partialSums.put(selectedRow, 0.0);

        System.out.println("Sending task row " + selectedRow + " to " + clientCount + " clients");

        for (int c = 0; c < clientCount; c++) {
            double partLow = low + c * partSize;
            double partUp = (c == clientCount - 1) ? up : partLow + partSize;
            
            try {
                RecIntegral task = new RecIntegral(partLow, partUp, step);
                task.setId(selectedRow);
                taskIdToRow.put(selectedRow, selectedRow);
                sendTaskToClient(task, clientAddresses[c], clientPorts[c]);
                System.out.println("  Client " + c + ": [" + partLow + ", " + partUp + "], ID=" + selectedRow);
            } catch (InvalidException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void sendTaskToClient(RecIntegral task, String address, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(task);
            byte[] data = baos.toByteArray();
            
            InetAddress inetAddress = InetAddress.getByName(address);
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
            socket.send(packet);
            socket.setSoTimeout(5000);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error sending to client " + address + ":" + port + "\n" + e.getMessage());
        }
    }

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
        pendingResults.clear();
        partialSums.clear();
        taskIdToRow.clear();
    }

    private void fillActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

    private void saveTxtBtnActionPerformed(java.awt.event.ActionEvent evt) {
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
                    "Write error: " + ex.getMessage()
                );
            }
        }
    }

    private void loadTxtBtnActionPerformed(java.awt.event.ActionEvent evt) {
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
                javax.swing.JOptionPane.showMessageDialog(this, "Read error: " + ex.getMessage());
            }
        }
    }

    private void saveBinBtnActionPerformed(java.awt.event.ActionEvent evt) {
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
                    "Serialization error: " + ex.getMessage()
                );
            }
        }
    }

    private void loadBinBtnActionPerformed(java.awt.event.ActionEvent evt) {
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
                javax.swing.JOptionPane.showMessageDialog(this, "Deserialization error: " + ex.getMessage());
            }
        }
    }

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
}