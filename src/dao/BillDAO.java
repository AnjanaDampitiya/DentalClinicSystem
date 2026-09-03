/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Bill;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    private Connection connection;

    public BillDAO(Connection connection) {
        this.connection = connection;
    }

    // SAVE
    public boolean saveBill(Bill bill) throws SQLException {

        String sql = "INSERT INTO bills "
                + "(appointment_id, amount, bill_date, payment_status) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement pst = connection.prepareStatement(
    sql,
    java.sql.Statement.RETURN_GENERATED_KEYS
);

        pst.setInt(1, bill.getAppointmentId());
        pst.setDouble(2, bill.getAmount());
        pst.setString(3, bill.getBillDate());
        pst.setString(4, bill.getPaymentStatus());

       int rows = pst.executeUpdate();

if (rows > 0) {
    java.sql.ResultSet rs = pst.getGeneratedKeys();

    if (rs.next()) {
        bill.setBillId(rs.getInt(1));
    }

    return true;
}

return false;
    }

    // UPDATE
    public boolean updateBill(Bill bill) throws SQLException {

        String sql = "UPDATE bills SET "
                + "appointment_id = ?, "
                + "amount = ?, "
                + "bill_date = ?, "
                + "payment_status = ? "
                + "WHERE bill_id = ?";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, bill.getAppointmentId());
        pst.setDouble(2, bill.getAmount());
        pst.setString(3, bill.getBillDate());
        pst.setString(4, bill.getPaymentStatus());
        pst.setInt(5, bill.getBillId());

        return pst.executeUpdate() > 0;
    }

    // DELETE
    public boolean deleteBill(int billId) throws SQLException {

        String sql = "DELETE FROM bills WHERE bill_id = ?";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, billId);

        return pst.executeUpdate() > 0;
    }
    public List<Bill> getAllBills() throws SQLException {

    List<Bill> bills = new ArrayList<>();

    String sql = "SELECT * FROM bills ORDER BY bill_id DESC";

    PreparedStatement pst = connection.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();

    while (rs.next()) {

        Bill bill = new Bill(
            rs.getInt("bill_id"),
            rs.getInt("appointment_id"),
            rs.getDouble("amount"),
            rs.getString("bill_date"),
            rs.getString("payment_status")
        );

        bills.add(bill);
    }

    return bills;
}
    // GET TREATMENT FEE BY APPOINTMENT
public double getTreatmentFeeByAppointmentId(int appointmentId) throws SQLException {

    String sql = "SELECT t.fee "
               + "FROM appointments a "
               + "INNER JOIN treatments t "
               + "ON a.treatment_id = t.treatment_id "
               + "WHERE a.appointment_id = ?";

    PreparedStatement pst = connection.prepareStatement(sql);

    pst.setInt(1, appointmentId);

    ResultSet rs = pst.executeQuery();

    double fee = 0;

    if (rs.next()) {
        fee = rs.getDouble("fee");
    }

    rs.close();
    pst.close();

    return fee;
}
}