/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BillDAO;
import java.sql.Connection;
import java.sql.SQLException;
import model.Bill;
import java.util.List;
import dao.AppointmentDAO;

public class BillController {

    private BillDAO billDAO;

    public BillController(Connection connection) {
        billDAO = new BillDAO(connection);
    }

    public boolean saveBill(Bill bill) throws SQLException {
        return billDAO.saveBill(bill);
    }

    public boolean updateBill(Bill bill) throws SQLException {
        return billDAO.updateBill(bill);
    }

    public boolean deleteBill(int billId) throws SQLException {
        return billDAO.deleteBill(billId);
    }
    public List<Bill> getAllBills() throws SQLException {
    return billDAO.getAllBills();
}
    public List<Integer> getAppointmentIds() throws SQLException {
    AppointmentDAO appointmentDAO = new AppointmentDAO();
    return appointmentDAO.getAppointmentIds();
}
   // CALCULATE TOTAL BILL
public double calculateTotalBill(int appointmentId) throws SQLException {

    double treatmentCost =
            billDAO.getTreatmentFeeByAppointmentId(appointmentId);

    double consultationFee = 1000.00;

    return treatmentCost + consultationFee;
}
public double getTreatmentFee(int appointmentId) throws SQLException {

    return billDAO.getTreatmentFeeByAppointmentId(appointmentId);
}
}