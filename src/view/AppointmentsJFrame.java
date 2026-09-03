/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import controller.AppointmentController;
import java.sql.*;
import model.Appointment;

/**
 *
 * @author Admin
 */
public class AppointmentsJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AppointmentsJFrame
     */
 public AppointmentsJFrame() {
    initComponents();

    SpinnerDateModel model = new SpinnerDateModel(
        new java.util.Date(),
        null,
        null,
        java.util.Calendar.MINUTE
    );

    jSpinner1.setModel(model);

    JSpinner.DateEditor editor =
        new JSpinner.DateEditor(jSpinner1, "hh:mm a");

    jSpinner1.setEditor(editor);

    loadPatientIDs();
    loadDentistIDs();
    loadTreatmentIDs();
}
 private void loadTreatmentIDs() {

    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dental_clinic",
            "root",
            System.getenv("DENTAL_DB_PASSWORD")
        );

        String sql = "SELECT treatment_id FROM treatments";

        PreparedStatement pst = con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery();

        jComboBox4.removeAllItems();

        while (rs.next()) {
            jComboBox4.addItem(rs.getString("treatment_id"));
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
 private void loadDentistIDs() {

    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dental_clinic",
            "root",
            System.getenv("DENTAL_DB_PASSWORD")
        );

        String sql = "SELECT dentist_id FROM dentists";

        PreparedStatement pst = con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery();

        jComboBox3.removeAllItems();

        while (rs.next()) {
            jComboBox3.addItem(rs.getString("dentist_id"));
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
private void loadPatientIDs() {

    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dental_clinic",
            "root",
            System.getenv("DENTAL_DB_PASSWORD")
        );

        String sql = "SELECT patient_id FROM patients";

        PreparedStatement pst = con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery();

        jComboBox2.removeAllItems();

        while (rs.next()) {
            jComboBox2.addItem(rs.getString("patient_id"));
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}


/**
 * This method is called from within the constructor...
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APPOINTMENT MANAGEMENT");

        jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("APPOINTMENT MANAGEMENT");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Appointment ID:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Patient ID:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Dentist ID:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Treatment ID: ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Appointment Date:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Appointment Time:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Status:");

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SAVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 102));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 102));
        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText(" BACK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Confirmed", "Completed", "Cancelled" }));

        jButton5.setBackground(new java.awt.Color(0, 0, 102));
        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("View Appointments");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("© 2026 Sunrise Dental Clinic ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(250, 250, 250))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sunrise_dental_clinic_logo_135x110.png"))); // NOI18N
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(39, 39, 39))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton5))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(62, 62, 62)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(138, 138, 138))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(39, 39, 39)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try {

        Appointment appointment = new Appointment();

        // ==============================
        // 1. PATIENT VALIDATION
        // ==============================

        if (jComboBox2.getSelectedItem() == null
                || jComboBox2.getSelectedItem().toString().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Patient!"
            );

            return;
        }

        int patientId = Integer.parseInt(
                jComboBox2.getSelectedItem().toString()
        );

        if (patientId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Patient ID!"
            );

            return;
        }

        appointment.setPatientId(patientId);


        // ==============================
        // 2. DENTIST VALIDATION
        // ==============================

        if (jComboBox3.getSelectedItem() == null
                || jComboBox3.getSelectedItem().toString().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Dentist!"
            );

            return;
        }

        int dentistId = Integer.parseInt(
                jComboBox3.getSelectedItem().toString()
        );

        if (dentistId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Dentist ID!"
            );

            return;
        }

        appointment.setDentistId(dentistId);


        // ==============================
        // 3. TREATMENT VALIDATION
        // ==============================

        if (jComboBox4.getSelectedItem() == null
                || jComboBox4.getSelectedItem().toString().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Treatment!"
            );

            return;
        }

        int treatmentId = Integer.parseInt(
                jComboBox4.getSelectedItem().toString()
        );

        if (treatmentId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Treatment ID!"
            );

            return;
        }

        appointment.setTreatmentId(treatmentId);


        // ==============================
        // 4. DATE VALIDATION
        // ==============================

        java.util.Date date = jDateChooser1.getDate();

        if (date == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment date!"
            );

            return;
        }

        // Prevent past dates
        java.util.Date today = new java.util.Date();

        if (date.before(today)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment date cannot be in the past!"
            );

            return;
        }

        appointment.setAppointmentDate(
                new java.sql.Date(date.getTime())
        );


        // ==============================
        // 5. TIME VALIDATION
        // ==============================

        java.util.Date time =
                (java.util.Date) jSpinner1.getValue();

        if (time == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment time!"
            );

            return;
        }

        appointment.setAppointmentTime(
                new java.sql.Time(time.getTime())
        );


        // ==============================
        // 6. STATUS VALIDATION
        // ==============================

        if (jComboBox1.getSelectedItem() == null
                || jComboBox1.getSelectedItem().toString().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select appointment status!"
            );

            return;
        }

        String status =
                jComboBox1.getSelectedItem().toString();

        appointment.setStatus(status);


        // ==============================
        // 7. SAVE APPOINTMENT
        // ==============================

        AppointmentController controller =
                new AppointmentController();

        boolean saved =
                controller.saveAppointment(appointment);

        if (saved) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment Saved Successfully!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment Save Failed!"
            );
        }


    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Patient, Dentist and Treatment IDs must be valid numbers!"
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage()
        );
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

        // ==============================
        // 1. APPOINTMENT ID VALIDATION
        // ==============================

        String appointmentId = jTextField1.getText().trim();

        if (appointmentId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment from the table first!"
            );

            return;
        }

        int appId = Integer.parseInt(appointmentId);

        if (appId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Appointment ID!"
            );

            return;
        }


        // ==============================
        // 2. PATIENT VALIDATION
        // ==============================

        if (jComboBox2.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Patient!"
            );

            return;
        }

        String patientId =
                jComboBox2.getSelectedItem().toString().trim();

        if (patientId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Patient!"
            );

            return;
        }

        int patient = Integer.parseInt(patientId);

        if (patient <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Patient ID!"
            );

            return;
        }


        // ==============================
        // 3. DENTIST VALIDATION
        // ==============================

        if (jComboBox3.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Dentist!"
            );

            return;
        }

        String dentistId =
                jComboBox3.getSelectedItem().toString().trim();

        if (dentistId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Dentist!"
            );

            return;
        }

        int dentist = Integer.parseInt(dentistId);

        if (dentist <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Dentist ID!"
            );

            return;
        }


        // ==============================
        // 4. TREATMENT VALIDATION
        // ==============================

        if (jComboBox4.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Treatment!"
            );

            return;
        }

        String treatmentId =
                jComboBox4.getSelectedItem().toString().trim();

        if (treatmentId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Treatment!"
            );

            return;
        }

        int treatment = Integer.parseInt(treatmentId);

        if (treatment <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Treatment ID!"
            );

            return;
        }


        // ==============================
        // 5. DATE VALIDATION
        // ==============================

        java.util.Date selectedDate =
                jDateChooser1.getDate();

        if (selectedDate == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment date!"
            );

            return;
        }

        // Prevent past dates
        java.util.Date today =
                new java.util.Date();

        if (selectedDate.before(today)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment date cannot be in the past!"
            );

            return;
        }


        // ==============================
        // 6. TIME VALIDATION
        // ==============================

        java.util.Date selectedTime =
                (java.util.Date) jSpinner1.getValue();

        if (selectedTime == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment time!"
            );

            return;
        }


        // ==============================
        // 7. STATUS VALIDATION
        // ==============================

        if (jComboBox1.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select appointment status!"
            );

            return;
        }

        String status =
                jComboBox1.getSelectedItem().toString().trim();

        if (status.isEmpty()
                || status.equalsIgnoreCase("Select Status")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select appointment status!"
            );

            return;
        }


        // ==============================
        // 8. DATABASE UPDATE
        // ==============================

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dental_clinic",
            "root",
            System.getenv("DENTAL_DB_PASSWORD")
        );

        String sql = "UPDATE appointments SET "
                + "patient_id = ?, "
                + "dentist_id = ?, "
                + "treatment_id = ?, "
                + "appointment_date = ?, "
                + "appointment_time = ?, "
                + "status = ? "
                + "WHERE appointment_id = ?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, patient);
        pst.setInt(2, dentist);
        pst.setInt(3, treatment);

        java.sql.Date sqlDate =
                new java.sql.Date(
                        selectedDate.getTime()
                );

        pst.setDate(4, sqlDate);

        java.sql.Time sqlTime =
                new java.sql.Time(
                        selectedTime.getTime()
                );

        pst.setTime(5, sqlTime);

        pst.setString(6, status);

        pst.setInt(7, appId);


        int affectedRows =
                pst.executeUpdate();


        // ==============================
        // 9. RESULT
        // ==============================

        if (affectedRows > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment Updated Successfully!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment ID not found!"
            );
        }

        pst.close();
        con.close();


    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Appointment, Patient, Dentist and Treatment IDs "
                + "must be valid numbers!"
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Database Error: " + e.getMessage()
        );
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         try {

        // ==============================
        // 1. APPOINTMENT ID VALIDATION
        // ==============================

        String appointmentId =
                jTextField1.getText().trim();

        if (appointmentId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment first!"
            );

            return;
        }

        // Convert ID
        int id = Integer.parseInt(appointmentId);

        // Check positive ID
        if (id <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Appointment ID!"
            );

            return;
        }


        // ==============================
        // 2. CONFIRM DELETE
        // ==============================

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this appointment?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }


        // ==============================
        // 3. DELETE APPOINTMENT
        // ==============================

        AppointmentController controller =
                new AppointmentController();

        boolean deleted =
                controller.deleteAppointment(id);


        // ==============================
        // 4. RESULT
        // ==============================

        if (deleted) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment Deleted Successfully!"
            );

            // Clear ID field
            jTextField1.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment ID not found!"
            );
        }


    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Appointment ID must be a valid number!"
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Database Error: " + e.getMessage()
        );
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
new DashboardJFrame().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       ViewAppointmentsJFrame view = new ViewAppointmentsJFrame();
view.setVisible(true);
this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentsJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
