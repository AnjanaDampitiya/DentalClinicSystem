/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import controller.BillController;
import model.Bill;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BillTest {

    // =====================================================
    // DATABASE CONNECTION
    // =====================================================

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dental_clinic",
                "root",
                System.getenv("DENTAL_DB_PASSWORD")
        );
    }


    // =====================================================
    // 1. TEST SAVE BILL
    // =====================================================

    @Test
    public void testSaveBill() throws Exception {

        Connection connection = getConnection();

        try {

            BillController controller =
                    new BillController(connection);

            // Get existing bills/appointments
            List<Integer> appointmentIds =
                    controller.getAppointmentIds();

            assertTrue(
                    "At least one appointment is required",
                    appointmentIds.size() > 0
            );

            int appointmentId =
                    appointmentIds.get(0);

            Bill bill = new Bill(
                    0,
                    appointmentId,
                    1500.00,
                    "2030-03-01",
                    "Paid"
            );

            boolean result =
                    controller.saveBill(bill);

            assertTrue(
                    "Bill should be saved successfully",
                    result
            );

            assertTrue(
                    "Bill ID should be generated",
                    bill.getBillId() > 0
            );

            // Cleanup
            controller.deleteBill(
                    bill.getBillId()
            );

        } finally {

            connection.close();
        }
    }


    // =====================================================
    // 2. TEST GET ALL BILLS
    // =====================================================

    @Test
    public void testGetAllBills() throws Exception {

        Connection connection = getConnection();

        try {

            BillController controller =
                    new BillController(connection);

            List<Bill> bills =
                    controller.getAllBills();

            assertNotNull(
                    "Bill list should not be null",
                    bills
            );

        } finally {

            connection.close();
        }
    }


    // =====================================================
    // 3. TEST UPDATE BILL
    // =====================================================

    @Test
    public void testUpdateBill() throws Exception {

        Connection connection = getConnection();

        try {

            BillController controller =
                    new BillController(connection);

            List<Integer> appointmentIds =
                    controller.getAppointmentIds();

            assertTrue(
                    "At least one appointment is required",
                    appointmentIds.size() > 0
            );

            int appointmentId =
                    appointmentIds.get(0);

            // Create temporary bill
            Bill bill = new Bill(
                    0,
                    appointmentId,
                    2000.00,
                    "2030-03-02",
                    "Pending"
            );

            boolean saved =
                    controller.saveBill(bill);

            assertTrue(
                    "Test bill should be saved",
                    saved
            );

            int billId =
                    bill.getBillId();

            assertTrue(
                    "Bill ID should be generated",
                    billId > 0
            );

            // Update bill
            Bill updatedBill = new Bill(
                    billId,
                    appointmentId,
                    2500.00,
                    "2030-03-03",
                    "Paid"
            );

            boolean updated =
                    controller.updateBill(updatedBill);

            assertTrue(
                    "Bill should be updated successfully",
                    updated
            );

            // Cleanup
            controller.deleteBill(billId);

        } finally {

            connection.close();
        }
    }


    // =====================================================
    // 4. TEST DELETE BILL
    // =====================================================

    @Test
    public void testDeleteBill() throws Exception {

        Connection connection = getConnection();

        try {

            BillController controller =
                    new BillController(connection);

            List<Integer> appointmentIds =
                    controller.getAppointmentIds();

            assertTrue(
                    "At least one appointment is required",
                    appointmentIds.size() > 0
            );

            int appointmentId =
                    appointmentIds.get(0);

            // Create temporary bill
            Bill bill = new Bill(
                    0,
                    appointmentId,
                    3000.00,
                    "2030-03-04",
                    "Pending"
            );

            boolean saved =
                    controller.saveBill(bill);

            assertTrue(
                    "Test bill should be saved",
                    saved
            );

            int billId =
                    bill.getBillId();

            assertTrue(
                    "Bill ID should be generated",
                    billId > 0
            );

            // Delete
            boolean deleted =
                    controller.deleteBill(billId);

            assertTrue(
                    "Bill should be deleted successfully",
                    deleted
            );

        } finally {

            connection.close();
        }
    }


    // =====================================================
    // 5. TEST CALCULATE TOTAL BILL
    // =====================================================

    @Test
    public void testCalculateTotalBill()
            throws Exception {

        Connection connection = getConnection();

        try {

            BillController controller =
                    new BillController(connection);

            List<Integer> appointmentIds =
                    controller.getAppointmentIds();

            assertTrue(
                    "At least one appointment is required",
                    appointmentIds.size() > 0
            );

            int appointmentId =
                    appointmentIds.get(0);

            double treatmentFee =
                    controller.getTreatmentFee(
                            appointmentId
                    );

            double total =
                    controller.calculateTotalBill(
                            appointmentId
                    );

            double expected =
                    treatmentFee + 1000.00;

            assertEquals(
                    "Total bill should equal treatment fee + consultation fee",
                    expected,
                    total,
                    0.01
            );

        } finally {

            connection.close();
        }
    }
}
