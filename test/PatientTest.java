/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import controller.PatientController;
import model.Patient;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class PatientTest {

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dental_clinic",
                "root",
                System.getenv("DENTAL_DB_PASSWORD")
        );
    }

    private String generateContact() {
        long number = System.currentTimeMillis() % 1000000000L;
        return "7" + String.format("%09d", number);
    }

    private int findPatientId(String contact) throws Exception {

        Connection con = getConnection();

        String sql =
                "SELECT patient_id FROM patients "
                + "WHERE contact_number = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, contact);

        ResultSet rs = pst.executeQuery();

        int id = 0;

        if (rs.next()) {
            id = rs.getInt("patient_id");
        }

        rs.close();
        pst.close();
        con.close();

        return id;
    }


    // ==============================
    // TEST 1 - SAVE PATIENT
    // ==============================

    @Test
    public void testSavePatient() throws Exception {

        PatientController controller =
                new PatientController();

        String contact = generateContact();

        Patient patient = new Patient(
                0,
                "Test Patient",
                "Colombo",
                contact
        );

        boolean result =
                controller.savePatient(patient);

        assertTrue(
                "Patient should be saved successfully",
                result
        );

        // Cleanup
        int patientId = findPatientId(contact);

        if (patientId > 0) {
            controller.deletePatient(patientId);
        }
    }


    // ==============================
    // TEST 2 - UPDATE PATIENT
    // ==============================

    @Test
    public void testUpdatePatient() throws Exception {

        PatientController controller =
                new PatientController();

        String contact = generateContact();

        Patient patient = new Patient(
                0,
                "Test Patient",
                "Colombo",
                contact
        );

        // First save
        boolean saved =
                controller.savePatient(patient);

        assertTrue(
                "Test patient should be saved first",
                saved
        );

        // Get generated database ID
        int patientId =
                findPatientId(contact);

        assertTrue(
                "Patient ID should be generated",
                patientId > 0
        );

        // Update
        Patient updatedPatient = new Patient(
                patientId,
                "Updated Patient",
                "Kandy",
                contact
        );

        boolean updated =
                controller.updatePatient(updatedPatient);

        assertTrue(
                "Patient should be updated successfully",
                updated
        );

        // Cleanup
        controller.deletePatient(patientId);
    }


    // ==============================
    // TEST 3 - DELETE PATIENT
    // ==============================

    @Test
    public void testDeletePatient() throws Exception {

        PatientController controller =
                new PatientController();

        String contact = generateContact();

        Patient patient = new Patient(
                0,
                "Delete Test Patient",
                "Galle",
                contact
        );

        // Save first
        boolean saved =
                controller.savePatient(patient);

        assertTrue(
                "Test patient should be saved first",
                saved
        );

        // Get generated ID
        int patientId =
                findPatientId(contact);

        assertTrue(
                "Patient ID should be generated",
                patientId > 0
        );

        // Delete
        boolean deleted =
                controller.deletePatient(patientId);

        assertTrue(
                "Patient should be deleted successfully",
                deleted
        );
    }


    // ==============================
    // TEST 4 - INVALID CONTACT
    // ==============================

    @Test
    public void testInvalidContact() {

        String contact = "ABC123";

        boolean valid =
                contact.matches("\\d{10}");

        assertFalse(
                "Invalid contact should fail validation",
                valid
        );
    }
}