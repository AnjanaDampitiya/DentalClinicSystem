/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import controller.AppointmentController;
import model.Appointment;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.Assert.*;

public class AppointmentTest {

    // =====================================================
    // 1. TEST SAVE APPOINTMENT
    // =====================================================

    @Test
    public void testSaveAppointment() throws Exception {

        AppointmentController controller =
                new AppointmentController();

        // Get an existing appointment
        List<Appointment> appointments =
                controller.getAllAppointments();

        assertTrue(
                "At least one appointment is required for testing",
                appointments.size() > 0
        );

        Appointment existing =
                appointments.get(0);

        Appointment newAppointment =
                new Appointment();

        // Use VALID foreign key IDs from database
        newAppointment.setPatientId(
                existing.getPatientId()
        );

        newAppointment.setDentistId(
                existing.getDentistId()
        );

        newAppointment.setTreatmentId(
                existing.getTreatmentId()
        );

        newAppointment.setAppointmentDate(
                Date.valueOf("2030-02-01")
        );

        newAppointment.setAppointmentTime(
                Time.valueOf("10:30:00")
        );

        newAppointment.setStatus(
                "Scheduled"
        );

        boolean result =
                controller.saveAppointment(newAppointment);

        assertTrue(
                "Appointment should be saved successfully",
                result
        );

        // Find and delete the test appointment
        List<Appointment> updatedList =
                controller.getAllAppointments();

        for (Appointment a : updatedList) {

            if (a.getAppointmentDate().equals(
                    Date.valueOf("2030-02-01"))
                    &&
                    a.getAppointmentTime().equals(
                    Time.valueOf("10:30:00"))
                    &&
                    a.getStatus().equals("Scheduled")) {

                controller.deleteAppointment(
                        a.getAppointmentId()
                );

                break;
            }
        }
    }


    // =====================================================
    // 2. TEST GET ALL APPOINTMENTS
    // =====================================================

    @Test
    public void testGetAllAppointments() throws Exception {

        AppointmentController controller =
                new AppointmentController();

        List<Appointment> appointments =
                controller.getAllAppointments();

        assertNotNull(
                "Appointment list should not be null",
                appointments
        );

        assertTrue(
                "Appointment list should contain appointments",
                appointments.size() > 0
        );
    }


    // =====================================================
    // 3. TEST SEARCH APPOINTMENT
    // =====================================================

    @Test
    public void testSearchByAppointmentId()
            throws Exception {

        AppointmentController controller =
                new AppointmentController();

        List<Appointment> appointments =
                controller.getAllAppointments();

        assertTrue(
                "At least one appointment is required",
                appointments.size() > 0
        );

        int appointmentId =
                appointments.get(0).getAppointmentId();

        Appointment appointment =
                controller.searchByAppointmentId(
                        appointmentId
                );

        assertNotNull(
                "Appointment should be found",
                appointment
        );

        assertEquals(
                "Appointment ID should match",
                appointmentId,
                appointment.getAppointmentId()
        );
    }


    // =====================================================
    // 4. TEST UPDATE APPOINTMENT
    // =====================================================

    @Test
    public void testUpdateAppointment()
            throws Exception {

        AppointmentController controller =
                new AppointmentController();

        List<Appointment> appointments =
                controller.getAllAppointments();

        assertTrue(
                "At least one appointment is required",
                appointments.size() > 0
        );

        // Get existing appointment
        Appointment original =
                appointments.get(0);

        int appointmentId =
                original.getAppointmentId();

        // Create updated appointment
        Appointment updated =
                new Appointment();

        updated.setAppointmentId(
                appointmentId
        );

        updated.setPatientId(
                original.getPatientId()
        );

        updated.setDentistId(
                original.getDentistId()
        );

        updated.setTreatmentId(
                original.getTreatmentId()
        );

        updated.setAppointmentDate(
                original.getAppointmentDate()
        );

        updated.setAppointmentTime(
                original.getAppointmentTime()
        );

        updated.setStatus(
                "Completed"
        );

        boolean result =
                controller.updateAppointment(
                        updated
                );

        assertTrue(
                "Appointment should be updated successfully",
                result
        );

        // Restore original status
        Appointment restore =
                new Appointment();

        restore.setAppointmentId(
                appointmentId
        );

        restore.setPatientId(
                original.getPatientId()
        );

        restore.setDentistId(
                original.getDentistId()
        );

        restore.setTreatmentId(
                original.getTreatmentId()
        );

        restore.setAppointmentDate(
                original.getAppointmentDate()
        );

        restore.setAppointmentTime(
                original.getAppointmentTime()
        );

        restore.setStatus(
                original.getStatus()
        );

        controller.updateAppointment(
                restore
        );
    }


    // =====================================================
    // 5. TEST DELETE APPOINTMENT
    // =====================================================

    @Test
    public void testDeleteAppointment()
            throws Exception {

        AppointmentController controller =
                new AppointmentController();

        List<Appointment> appointments =
                controller.getAllAppointments();

        assertTrue(
                "At least one appointment is required",
                appointments.size() > 0
        );

        // Use valid foreign key IDs
        Appointment existing =
                appointments.get(0);

        Appointment testAppointment =
                new Appointment();

        testAppointment.setPatientId(
                existing.getPatientId()
        );

        testAppointment.setDentistId(
                existing.getDentistId()
        );

        testAppointment.setTreatmentId(
                existing.getTreatmentId()
        );

        testAppointment.setAppointmentDate(
                Date.valueOf("2030-02-02")
        );

        testAppointment.setAppointmentTime(
                Time.valueOf("11:30:00")
        );

        testAppointment.setStatus(
                "Scheduled"
        );

        boolean saved =
                controller.saveAppointment(
                        testAppointment
                );

        assertTrue(
                "Test appointment should be saved",
                saved
        );

        // Find newly created appointment
        List<Appointment> afterSave =
                controller.getAllAppointments();

        int testAppointmentId = -1;

        for (Appointment a : afterSave) {

            if (a.getAppointmentDate().equals(
                    Date.valueOf("2030-02-02"))
                    &&
                    a.getAppointmentTime().equals(
                    Time.valueOf("11:30:00"))
                    &&
                    a.getStatus().equals("Scheduled")) {

                testAppointmentId =
                        a.getAppointmentId();

                break;
            }
        }

        assertTrue(
                "Test appointment ID should be found",
                testAppointmentId > 0
        );

        // Delete
        boolean deleted =
                controller.deleteAppointment(
                        testAppointmentId
                );

        assertTrue(
                "Appointment should be deleted successfully",
                deleted
        );

        // Verify deletion
        Appointment result =
                controller.searchByAppointmentId(
                        testAppointmentId
                );

        assertNull(
                "Deleted appointment should not be found",
                result
        );
    }
}