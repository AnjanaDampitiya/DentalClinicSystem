/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import model.Patient;

public class PatientDAO {

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dental_clinic",
                "root",
                System.getenv("DENTAL_DB_PASSWORD")
        );
    }

    // SAVE
    public boolean save(Patient patient) throws Exception {

    Connection con = getConnection();

    // Check duplicate patient
    String checkSql =
            "SELECT patient_id FROM patients "
          + "WHERE patient_name = ? AND contact_number = ?";

    PreparedStatement checkPst =
            con.prepareStatement(checkSql);

    checkPst.setString(1, patient.getPatientName());
    checkPst.setString(2, patient.getContactNumber());

    java.sql.ResultSet rs = checkPst.executeQuery();

    if (rs.next()) {

        rs.close();
        checkPst.close();
        con.close();

        return false;
    }

    rs.close();
    checkPst.close();

    String sql =
            "INSERT INTO patients "
          + "(patient_name, address, contact_number) "
          + "VALUES (?, ?, ?)";

    PreparedStatement pst =
            con.prepareStatement(sql);

    pst.setString(1, patient.getPatientName());
    pst.setString(2, patient.getAddress());
    pst.setString(3, patient.getContactNumber());

    int rows = pst.executeUpdate();

    pst.close();
    con.close();

    return rows > 0;
}

    // UPDATE
    public boolean update(Patient patient) throws Exception {

        Connection con = getConnection();

        String sql = "UPDATE patients SET "
                + "patient_name=?, address=?, contact_number=? "
                + "WHERE patient_id=?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, patient.getPatientName());
        pst.setString(2, patient.getAddress());
        pst.setString(3, patient.getContactNumber());
        pst.setInt(4, patient.getPatientId());

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }

    // DELETE
    public boolean delete(int patientId) throws Exception {

        Connection con = getConnection();

        String sql = "DELETE FROM patients WHERE patient_id=?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, patientId);

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }
}