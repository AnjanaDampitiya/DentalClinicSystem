/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dental_clinic",
                "root",
              System.getenv("DENTAL_DB_PASSWORD")
        );
    }

    // DAILY APPOINTMENTS REPORT
    public List<String[]> getDailyAppointments(String date)
            throws Exception {

        List<String[]> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
                "SELECT a.appointment_id, "
                + "p.patient_name, "
                + "d.dentist_name, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status "
                + "FROM appointments a "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "WHERE DATE(a.appointment_date) = ? "
                + "ORDER BY a.appointment_time";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, date);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            String[] row = {
                String.valueOf(
                    rs.getInt("appointment_id")
                ),
                rs.getString("patient_name"),
                rs.getString("dentist_name"),
                String.valueOf(
                    rs.getDate("appointment_date")
                ),
                String.valueOf(
                    rs.getTime("appointment_time")
                ),
                rs.getString("status")
            };

            list.add(row);
        }

        rs.close();
        pst.close();
        con.close();

        return list;
    }
  // MONTHLY REVENUE / BILLS REPORT
public List<String[]> getMonthlyRevenue(String month)
        throws Exception {

    List<String[]> list = new ArrayList<>();

    Connection con = getConnection();

    String sql =
            "SELECT bill_id, appointment_id, bill_date, amount, payment_status "
            + "FROM bills "
            + "WHERE DATE_FORMAT(bill_date, '%Y-%m') = ? "
            + "ORDER BY bill_date";

    PreparedStatement pst =
            con.prepareStatement(sql);

    pst.setString(1, month);

    ResultSet rs = pst.executeQuery();

    while (rs.next()) {

        String[] row = {
            String.valueOf(rs.getInt("bill_id")),
            String.valueOf(rs.getInt("appointment_id")),
            String.valueOf(rs.getDate("bill_date")),
            String.valueOf(rs.getBigDecimal("amount")),
            rs.getString("payment_status")
        };

        list.add(row);
    }

    rs.close();
    pst.close();
    con.close();

    return list;
}
// PATIENT TREATMENT REPORT
public List<String[]> getPatientTreatments() throws Exception {

    List<String[]> list = new ArrayList<>();

    Connection con = getConnection();

    String sql =
            "SELECT p.patient_id, "
            + "p.patient_name, "
            + "t.treatment_name, "
            + "t.description, "
            + "t.fee "
            + "FROM appointments a "
            + "JOIN patients p "
            + "ON a.patient_id = p.patient_id "
            + "JOIN treatments t "
            + "ON a.treatment_id = t.treatment_id "
            + "ORDER BY p.patient_name";

    PreparedStatement pst =
            con.prepareStatement(sql);

    ResultSet rs = pst.executeQuery();

    while (rs.next()) {

        String[] row = {
            String.valueOf(rs.getInt("patient_id")),
            rs.getString("patient_name"),
            rs.getString("treatment_name"),
            rs.getString("description"),
            String.valueOf(rs.getDouble("fee"))
        };

        list.add(row);
    }

    rs.close();
    pst.close();
    con.close();

    return list;
}
}
