/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

/**
 *
 * @author vinzb
 */
public class AgentDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgentDashboard.class.getName());
    
    private void loadRequests() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblRequests.getModel();
        model.setRowCount(0); // Clear existing rows

        for (MyLib.Request req : MyLib.Data.getInstance().clientRequests) {
            model.addRow(new Object[]{
                req.getBuyerUsername(),
                req.getPropertyId(),
                String.format("PHP %,.2f", req.getMonthlyIncome()),
                req.getPreferredPayment(),
                req.getStatus()
            });
        }
    }
    private void loadSchedule() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblSchedule.getModel();
        model.setRowCount(0);
        cmbScheduleClient.removeAllItems();

        for (MyLib.Request req : MyLib.Data.getInstance().clientRequests) {
            // Only show clients who aren't cancelled or rejected
            if (!req.getStatus().equals("Cancelled")) {
                model.addRow(new Object[]{
                    req.getBuyerUsername(),
                    req.getPropertyId(),
                    req.getMeetingSchedule(),
                    req.getStatus()
                });
                
                // Add the buyer to the dropdown so the agent can select them
                cmbScheduleClient.addItem(req.getBuyerUsername());
            }
        }
    }
    private void loadChatClients() {
        javax.swing.DefaultListModel<String> listModel = new javax.swing.DefaultListModel<>();
        for (MyLib.Request req : MyLib.Data.getInstance().clientRequests) {
            if (!req.getStatus().equals("Cancelled")) {
                listModel.addElement(req.getBuyerUsername());
            }
        }
        lstChatClients.setModel(listModel);
    }
    private void loadChatHistory() {
        String selectedClient = lstChatClients.getSelectedValue();
        if (selectedClient == null) return;

        txtChatHistory.setText(""); // Clear current text
        String currentAgent = "admin"; // Temporarily hardcoded until session management

        for (MyLib.Message msg : MyLib.Data.getInstance().allMessages) {
            boolean isFromAgentToClient = msg.getSenderUsername().equals(currentAgent) && msg.getReceiverUsername().equals(selectedClient);
            boolean isFromClientToAgent = msg.getSenderUsername().equals(selectedClient) && msg.getReceiverUsername().equals(currentAgent);

            if (isFromAgentToClient || isFromClientToAgent) {
                txtChatHistory.append("[" + msg.getSenderUsername() + "]: " + msg.getContent() + "\n");
            }
        }
    }
    private void loadWalletBalance() {
        // Fetch the current agent. (Assumes currentAgentUsername is tracked in the dashboard)
        String currentAgentUsername = "admin"; // Temporarily hardcoded for testing
        MyLib.Agent agent = MyLib.Data.getInstance().agents.get(currentAgentUsername);
        
        if (agent != null) {
            lblWalletBalance.setText(String.format("Available Balance: PHP %,.2f", agent.getCommissionBalance()));
        }
    }
    private void loadSettledTransactions() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblSettledTransactions.getModel();
        model.setRowCount(0);

        for (MyLib.Request req : MyLib.Data.getInstance().clientRequests) {
            // A transaction is considered settled if the property is successfully reserved or sold
            if (req.getStatus().equals("Reserved") || req.getStatus().equals("Sold")) {
                
                // Fetch the associated property to retrieve its TCP
                MyLib.Property prop = MyLib.Data.getInstance().properties.get(req.getPropertyId());
                String priceStr = (prop != null) ? String.format("PHP %,.2f", prop.getPrice()) : "Unknown";

                model.addRow(new Object[]{
                    req.getPropertyId(),
                    req.getBuyerUsername(),
                    priceStr,
                    req.getStatus()
                });
            }
        }
    }
    /**
     * Creates new form AgentDashboard
     */
    public AgentDashboard() {
        initComponents();
        loadRequests();
        loadSchedule();             // Populates the Schedule table and the Combo Box
        loadSettledTransactions();  // Populates the Settled Transactions table
        loadChatClients();          // Populates the Chat list with buyers
        loadWalletBalance();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRequests = new javax.swing.JTable();
        btnAcknowledge = new javax.swing.JButton();
        btLogout = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSettledTransactions = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        lblWalletBalance = new javax.swing.JLabel();
        cmbBank = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtTransferAmount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtAmortPrice = new javax.swing.JTextField();
        txtAmortIncome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbAmortOption = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnGenerateAmort = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAmortization = new javax.swing.JTable();
        lblAmortSummary = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstChatClients = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtChatHistory = new javax.swing.JTextArea();
        btnSendMessage = new javax.swing.JButton();
        txtChatMessage = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();
        cmbScheduleClient = new javax.swing.JComboBox<>();
        txtScheduleDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSetSchedule = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Buyer", "Property ID", "Monthly Income", "Payment Method", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRequests);

        jScrollPane1.setViewportView(jScrollPane2);

        btnAcknowledge.setText("Generate Computation");
        btnAcknowledge.addActionListener(this::btnAcknowledgeActionPerformed);

        btLogout.setText("LOGOUT");
        btLogout.addActionListener(this::btLogoutActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAcknowledge, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 220, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAcknowledge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(btLogout)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Buyer Requests", jPanel1);

        tblSettledTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Property ID", "Buyer Username", "Total Contract Price", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblSettledTransactions);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Settled Transactions", jPanel5);

        lblWalletBalance.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        lblWalletBalance.setText("AVAILABLE BALANCE: PHP0.00");

        cmbBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select transaction", "BDO", "BPI", "UnionBank", "GCash", " " }));

        jLabel5.setText("Select BANK/E-WALLET");

        txtTransferAmount.setText("Enter Amount");

        jLabel6.setText("Transfer Amount:");

        jButton1.setText("Transfer Funds");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWalletBalance)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTransferAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblWalletBalance)
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTransferAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Commission Wallet", jPanel6);

        txtAmortPrice.setText("PHP0.00");

        txtAmortIncome.setText("PHP0.00");

        jLabel1.setText("Property Price (TCP):");

        jLabel2.setText("Client Monthly Income:");

        cmbAmortOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHOOSE LOAN TYPE", "Bank", "In-house", "PAG-IBIG", " " }));

        jLabel3.setText("Financing Type:");

        btnGenerateAmort.setText("GENERATE TABLE");
        btnGenerateAmort.addActionListener(this::btnGenerateAmortActionPerformed);

        tblAmortization.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Term (Years)", "Monthly Payment", "Eligibility"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblAmortization);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmortSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmortIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmortPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAmortOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGenerateAmort)))
                .addContainerGap(301, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAmortPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAmortIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbAmortOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerateAmort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAmortSummary)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Amortization Generator", jPanel2);

        lstChatClients.setBackground(new java.awt.Color(204, 204, 204));
        lstChatClients.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstChatClients.addListSelectionListener(this::lstChatClientsValueChanged);
        jScrollPane5.setViewportView(lstChatClients);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSendMessage)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSendMessage))))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chat", jPanel4);

        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Client", "Property ID", "Date & Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSchedule);

        cmbScheduleClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "choose client", " " }));

        txtScheduleDate.setText("(MM/DD/YYY) - (HH:MM AM/PM)");
        txtScheduleDate.addActionListener(this::txtScheduleDateActionPerformed);

        jLabel4.setText("Date & Time:");

        btnSetSchedule.setText("SET MEETING");
        btnSetSchedule.addActionListener(this::btnSetScheduleActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmbScheduleClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtScheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSetSchedule)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbScheduleClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtScheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnSetSchedule))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Client Schedule", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAcknowledgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcknowledgeActionPerformed
        int selectedRow = tblRequests.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Select a request first.", "No Selection", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Retrieve the specific request and corresponding property
        MyLib.Request selectedReq = MyLib.Data.getInstance().clientRequests.get(selectedRow);
        MyLib.Property targetProperty = MyLib.Data.getInstance().properties.get(selectedReq.getPropertyId());
        double price = targetProperty.getPrice();

        // 2. Route to the correct Strategy based on the request
        MyLib.FinancingStrategy strategy = null;
        switch (selectedReq.getPreferredPayment()) {
            case "Bank Financing":
                strategy = new MyLib.BankFinancing();
                break;
            case "In-House Financing":
                strategy = new MyLib.InHouseFinancing();
                break;
            case "Pag-ibig Housing Loan":
                strategy = new MyLib.PagIbigFinancing();
                break;
            case "Spot Cash":
                javax.swing.JOptionPane.showMessageDialog(this, "Spot Cash chosen. One-time payment: PHP " + String.format("%,.2f", price));
                selectedReq.setStatus("Acknowledged");
                loadRequests();
                return;
        }

        // 3. Calculate and display
        if (strategy != null) {
            int termYears = 10; // Default term for the sample computation
            double monthly = strategy.calculateMonthlyAmortization(price, termYears);
            
            String message = String.format("Sample Computation for %s\nProperty: %s\nTCP: PHP %,.2f\nEst. Monthly (%d yrs): PHP %,.2f", 
                selectedReq.getBuyerUsername(), targetProperty.getId(), price, termYears, monthly);
            
            javax.swing.JOptionPane.showMessageDialog(this, message, "Computation Generated", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // 4. Update status and refresh table
            selectedReq.setStatus("Acknowledged");
            loadRequests();
            
            // Uncomment the line below once your save method is active
            // MyLib.LeaseWoodFacade.saveSystemData(); 
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnAcknowledgeActionPerformed

    private void btnGenerateAmortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateAmortActionPerformed
        try {
            String priceStr = txtAmortPrice.getText().trim();
            String incomeStr = txtAmortIncome.getText().trim();
            String option = (String) cmbAmortOption.getSelectedItem();
            
            if (priceStr.isEmpty() || incomeStr.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please enter both price and income.", "Input Error", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            double price = Double.parseDouble(priceStr);
            double income = Double.parseDouble(incomeStr);
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblAmortization.getModel();

            MyApp.LeaseWood.generateAmortization(option, price, income, model, lblAmortSummary);

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter valid numeric values without commas.", "Format Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnGenerateAmortActionPerformed

    private void btnSetScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetScheduleActionPerformed
    String selectedClient = (String) cmbScheduleClient.getSelectedItem();
        String dateTime = txtScheduleDate.getText().trim();

        if (selectedClient == null || dateTime.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a client and enter a date/time.");
            return;
        }

        // Find the client's request and update the schedule
        for (MyLib.Request req : MyLib.Data.getInstance().clientRequests) {
            if (req.getBuyerUsername().equals(selectedClient)) {
                req.setMeetingSchedule(dateTime);
                break;
            }
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Meeting scheduled with " + selectedClient + " for " + dateTime);
        txtScheduleDate.setText("");
        
        // Refresh the table to show the new date
        loadSchedule();
// TODO add your handling code here:
    }//GEN-LAST:event_btnSetScheduleActionPerformed

    private void lstChatClientsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstChatClientsValueChanged
        if (!evt.getValueIsAdjusting()) { // Prevents double-firing when the user clicks
            loadChatHistory();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_lstChatClientsValueChanged

    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        String selectedClient = lstChatClients.getSelectedValue();
        String content = txtChatMessage.getText().trim();
        String currentAgent = "admin"; // Temporarily hardcoded

        if (selectedClient == null || content.isEmpty()) return;

        MyLib.Message newMsg = new MyLib.Message(currentAgent, selectedClient, content);
        MyLib.Data.getInstance().allMessages.add(newMsg);

        txtChatMessage.setText(""); // Clear input
        loadChatHistory(); // Refresh the window to show the new message
        
        // MyLib.LeaseWoodFacade.saveSystemData(); // Uncomment when saving is active
// TODO add your handling code here:
    }//GEN-LAST:event_btnSendMessageActionPerformed

    private void txtChatMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChatMessageActionPerformed

    private void txtScheduleDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScheduleDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtScheduleDateActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btnAcknowledge;
    private javax.swing.JButton btnGenerateAmort;
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JButton btnSetSchedule;
    private javax.swing.JComboBox<String> cmbAmortOption;
    private javax.swing.JComboBox<String> cmbBank;
    private javax.swing.JComboBox<String> cmbScheduleClient;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAmortSummary;
    private javax.swing.JLabel lblWalletBalance;
    private javax.swing.JList<String> lstChatClients;
    private javax.swing.JTable tblAmortization;
    private javax.swing.JTable tblRequests;
    private javax.swing.JTable tblSchedule;
    private javax.swing.JTable tblSettledTransactions;
    private javax.swing.JTextField txtAmortIncome;
    private javax.swing.JTextField txtAmortPrice;
    private javax.swing.JTextArea txtChatHistory;
    private javax.swing.JTextField txtChatMessage;
    private javax.swing.JTextField txtScheduleDate;
    private javax.swing.JTextField txtTransferAmount;
    // End of variables declaration//GEN-END:variables
}
