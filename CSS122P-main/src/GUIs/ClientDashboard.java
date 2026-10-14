/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;
import MyLib.Buyer;
import MyLib.Request;
import MyLib.Property;
import MyLib.Data;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author vinzb
 */
public class ClientDashboard extends javax.swing.JFrame {
    
    private Buyer currentBuyer;
    private Request activeRequest; 

    // 1. Update constructor to accept the logged-in buyer
    public ClientDashboard(Buyer buyer) {
        this.currentBuyer = buyer;
        initComponents();
        loadProperties();
        loadActiveRequest(); 
        scaleImage();
        loadChatHistory();
    }

    // 2. Add the method to find and display their single request
    private void loadActiveRequest() {
        activeRequest = null; 
        
        for (Request req : Data.getInstance().clientRequests) {
            if (req.getBuyerUsername().equals(currentBuyer.getUsername())) {
                activeRequest = req;
                break; 
            }
        }

        if (activeRequest != null) {
            lblProperty.setText(activeRequest.getPropertyId());
            lblPayment.setText(activeRequest.getPreferredPayment());
            lblIncome.setText(String.format("PHP %,.2f", activeRequest.getMonthlyIncome()));
            lblStatus.setText(activeRequest.getStatus());
            
            if (activeRequest.getStatus().equals("Acknowledged")) {
                btnReserve.setEnabled(true);
            } else {
                btnReserve.setEnabled(false);
            }
        } else {
            lblProperty.setText("None");
            lblPayment.setText("N/A");
            lblIncome.setText("N/A");
            lblStatus.setText("No active requests");
            btnReserve.setEnabled(false);
        }
    }
    private void scaleImage() {
        try {
            javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(getClass().getResource("/Resources/LOT-GRADING.png"));
            java.awt.Image img = originalIcon.getImage();
            java.awt.Image scaledImg = img.getScaledInstance(450, 270, java.awt.Image.SCALE_SMOOTH);
            jLabel6.setIcon(new javax.swing.ImageIcon(scaledImg)); // Make sure jLabel1 matches your pasted image label name
        } catch (Exception e) {
            System.out.println("Image not found.");
        }
    }

    private void loadProperties() {
        DefaultTableModel model = (DefaultTableModel) tblProperties.getModel();
        model.setRowCount(0); 

        java.util.List<MyLib.Property> propertyList = new java.util.ArrayList<>(MyLib.Data.getInstance().properties.values());

        String selectedBlockStr = (String) cmbBlock.getSelectedItem(); 
        if (selectedBlockStr != null && selectedBlockStr.startsWith("Block ")) {
            try {
                int targetBlock = Integer.parseInt(selectedBlockStr.replace("Block ", ""));
                propertyList.removeIf(p -> p.getBlock() != targetBlock);
            } catch (NumberFormatException e) {}
        }

        String sortBy = (String) cmbSortBy.getSelectedItem();
        String order = (String) cmbOrder.getSelectedItem();
        
        if (sortBy != null && order != null) {
            java.util.Comparator<MyLib.Property> comparator = sortBy.equals("Lot Size") 
                ? java.util.Comparator.comparingDouble(MyLib.Property::getLotSize) 
                : java.util.Comparator.comparingDouble(MyLib.Property::getPrice);

            if (order.equals("Descending")) {
                comparator = comparator.reversed();
            }
            propertyList.sort(comparator);
        }

        for (MyLib.Property p : propertyList) {
            if (p.isListed() && p.getStatus().equals("Available")) {
                model.addRow(new Object[]{
                    p.getId(), p.getLotSize() + " sqm", String.format("PHP %,.2f", p.getPrice()), p.getStatus()
                });
            }
        }
    }
    private void loadChatHistory() {
        txtChatHistory.setText(""); // Clear current text
        String targetAgent = "admin"; // Temporarily hardcoded to match the AgentDashboard setup

        for (MyLib.Message msg : MyLib.Data.getInstance().allMessages) {
            boolean isFromClientToAgent = msg.getSenderUsername().equals(currentBuyer.getUsername()) && msg.getReceiverUsername().equals(targetAgent);
            boolean isFromAgentToClient = msg.getSenderUsername().equals(targetAgent) && msg.getReceiverUsername().equals(currentBuyer.getUsername());

            if (isFromClientToAgent || isFromAgentToClient) {
                txtChatHistory.append("[" + msg.getSenderUsername() + "]: " + msg.getContent() + "\n");
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblProperty = new javax.swing.JLabel();
        lblPayment = new javax.swing.JLabel();
        lblIncome = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnReserve = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblPropertyTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProperties = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cmbBlock = new javax.swing.JComboBox<>();
        cmbSortBy = new javax.swing.JComboBox<>();
        cmbOrder = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtChatHistory = new javax.swing.JTextArea();
        btnSendMessage = new javax.swing.JButton();
        txtChatMessage = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("My Active Requests");

        jLabel2.setText("Property:");

        jLabel3.setText("Payment Method:");

        jLabel4.setText("Income Submitted:");

        jLabel5.setText("Status:");

        lblProperty.setText("jLabel6");

        lblPayment.setText("jLabel7");

        lblIncome.setText("jLabel8");

        lblStatus.setText("jLabel9");

        btnReserve.setText("Finalize Reservation");
        btnReserve.addActionListener(this::btnReserveActionPerformed);

        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReserve))
                .addContainerGap(788, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblProperty, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(112, 112, 112)
                            .addComponent(jLabel1)))
                    .addContainerGap(724, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblStatus))
                .addGap(18, 18, 18)
                .addComponent(btnReserve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblProperty))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblPayment))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblIncome))
                    .addContainerGap(340, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("My Requests", jPanel1);

        lblPropertyTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblPropertyTitle.setText("AVAILABLE PROPERTIES");

        tblProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Property ID", "Lot Size", "TCP (Price)", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProperties.setColumnSelectionAllowed(true);
        tblProperties.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProperties);
        tblProperties.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\vinzb\\OneDrive\\Documents\\GitHub\\LEASEWOOD\\CSS122P-main\\src\\Resources\\LOT-GRADING.png")); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cmbBlock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Blocks", "Block 1", "Block 2", "Block 3", "Block 4", "Block 5" }));
        cmbBlock.addActionListener(this::cmbBlockActionPerformed);

        cmbSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TCP (Price)", "Lot Size" }));
        cmbSortBy.addActionListener(this::cmbSortByActionPerformed);

        cmbOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cmbOrder.addActionListener(this::cmbOrderActionPerformed);

        jButton1.setText("View Property");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(lblPropertyTitle)
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(cmbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbSortBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPropertyTitle)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Browse Properties", jPanel2);

        txtChatHistory.setColumns(20);
        txtChatHistory.setRows(5);
        txtChatHistory.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane6.setViewportView(txtChatHistory);

        btnSendMessage.setText("SEND MESSAGE");
        btnSendMessage.addActionListener(this::btnSendMessageActionPerformed);

        txtChatMessage.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtChatMessage.setText("Send a message.....");
        txtChatMessage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChatMessage.addActionListener(this::txtChatMessageActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSendMessage)))
                .addContainerGap(344, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendMessage))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chat", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReserveActionPerformed
       
        if (activeRequest == null) return;

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "The agent has approved your computation for " + activeRequest.getPropertyId() + ".\nDo you want to finalize this reservation?", 
            "Confirm Reservation", 
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // 1. Fetch the property
            Property targetProperty = Data.getInstance().properties.get(activeRequest.getPropertyId());
            
            // 2. Trigger the State Pattern
            targetProperty.getState().reserve(targetProperty, currentBuyer.getUsername());
            
            // 3. Update the request status
            activeRequest.setStatus("Reserved");
            
            // 4. Save data (Uncomment when your Facade is ready)
            // MyLib.LeaseWoodFacade.saveSystemData(); 

            javax.swing.JOptionPane.showMessageDialog(this, "Reservation Successful! Property " + activeRequest.getPropertyId() + " is now yours.");
            loadActiveRequest(); // Refresh the UI
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnReserveActionPerformed

    private void cmbBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBlockActionPerformed
        loadProperties();// TODO add your handling code here:
    }//GEN-LAST:event_cmbBlockActionPerformed

    private void cmbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortByActionPerformed
        loadProperties();// TODO add your handling code here:
    }//GEN-LAST:event_cmbSortByActionPerformed

    private void cmbOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrderActionPerformed
        loadProperties();// TODO add your handling code here:
    }//GEN-LAST:event_cmbOrderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = tblProperties.getSelectedRow();

        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a property from the table first.", "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String propertyId = (String) tblProperties.getValueAt(selectedRow, 0);
        MyLib.Property selectedProperty = MyLib.Data.getInstance().properties.get(propertyId);

        // Pass BOTH the property and the buyer session to the next window
        new PropertyDetails(selectedProperty, currentBuyer).setVisible(true);
        this.dispose();//TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        String content = txtChatMessage.getText().trim();
        String targetAgent = "admin"; // Temporarily hardcoded

        if (content.isEmpty()) return;

        // Create the message with the buyer as the sender
        MyLib.Message newMsg = new MyLib.Message(currentBuyer.getUsername(), targetAgent, content);
        MyLib.Data.getInstance().allMessages.add(newMsg);

        txtChatMessage.setText(""); // Clear the input field
        loadChatHistory(); // Refresh the window to show the new message

        MyApp.LeaseWood.systemFacade.saveSystemData(); // Uncomment when saving is active
    }//GEN-LAST:event_btnSendMessageActionPerformed

    private void txtChatMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChatMessageActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here
        new GUIs.Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReserve;
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JComboBox<String> cmbBlock;
    private javax.swing.JComboBox<String> cmbOrder;
    private javax.swing.JComboBox<String> cmbSortBy;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblIncome;
    private javax.swing.JLabel lblPayment;
    private javax.swing.JLabel lblProperty;
    private javax.swing.JLabel lblPropertyTitle;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblProperties;
    private javax.swing.JTextArea txtChatHistory;
    private javax.swing.JTextField txtChatMessage;
    // End of variables declaration//GEN-END:variables
}
