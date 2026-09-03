/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Appointment;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.AppointmentDetails;

/**
 *
 * @author Admin
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class AppointmentDAO {

    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dental_clinic",
            "root",
            System.getenv("DENTAL_DB_PASSWORD")
        );
    }
   public boolean save(Appointment appointment) throws Exception {

    Connection con = getConnection();

    String sql = "INSERT INTO appointments "
        + "(patient_id, dentist_id, treatment_id, "
        + "appointment_date, appointment_time, status) "
        + "VALUES (?, ?, ?, ?, ?, ?)";

    java.sql.PreparedStatement pst = con.prepareStatement(sql);

   pst.setInt(1, appointment.getPatientId());
pst.setInt(2, appointment.getDentistId());
pst.setInt(3, appointment.getTreatmentId());
pst.setDate(4, appointment.getAppointmentDate());
pst.setTime(5, appointment.getAppointmentTime());
pst.setString(6, appointment.getStatus());

    int rows = pst.executeUpdate();

    pst.close();
    con.close();

    return rows > 0;
} 
   public boolean update(Appointment appointment) throws Exception {

    Connection con = getConnection();

    String sql = "UPDATE appointments SET "
            + "patient_id = ?, dentist_id = ?, treatment_id = ?, "
            + "appointment_date = ?, appointment_time = ?, status = ? "
            + "WHERE appointment_id = ?";

    java.sql.PreparedStatement pst = con.prepareStatement(sql);

  pst.setInt(1, appointment.getPatientId());
pst.setInt(2, appointment.getDentistId());
pst.setInt(3, appointment.getTreatmentId());
pst.setDate(4, appointment.getAppointmentDate());
pst.setTime(5, appointment.getAppointmentTime());
pst.setString(6, appointment.getStatus());
pst.setInt(7, appointment.getAppointmentId());

int rows = pst.executeUpdate();

pst.close();
con.close();

return rows > 0;
}
   
   public boolean delete(int appointmentId) throws Exception {

    Connection con = getConnection();

    String sql = "DELETE FROM appointments WHERE appointment_id = ?";

    java.sql.PreparedStatement pst = con.prepareStatement(sql);

    pst.setInt(1, appointmentId);

    int rows = pst.executeUpdate();

    pst.close();
    con.close();

    return rows > 0;
}
  public java.util.List<Appointment> getAllAppointments() throws Exception {

    java.util.List<Appointment> list = new java.util.ArrayList<>();

    Connection con = getConnection();

    String sql = "SELECT * FROM appointments";

    java.sql.PreparedStatement pst = con.prepareStatement(sql);

    java.sql.ResultSet rs = pst.executeQuery();

    while (rs.next()) {

        Appointment appointment = new Appointment();

        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDentistId(rs.getInt("dentist_id"));
        appointment.setTreatmentId(rs.getInt("treatment_id"));
        appointment.setAppointmentDate(rs.getDate("appointment_date"));
        appointment.setAppointmentTime(rs.getTime("appointment_time"));
        appointment.setStatus(rs.getString("status"));

        list.add(appointment);
    }

    rs.close();
    pst.close();
    con.close();

    return list;
} 
  public Appointment searchByAppointmentId(int appointmentId) throws Exception {

    Connection con = getConnection();

    String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

    java.sql.PreparedStatement pst = con.prepareStatement(sql);

    pst.setInt(1, appointmentId);

    java.sql.ResultSet rs = pst.executeQuery();

    Appointment appointment = null;

    if (rs.next()) {

        appointment = new Appointment();

        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDentistId(rs.getInt("dentist_id"));
        appointment.setTreatmentId(rs.getInt("treatment_id"));
        appointment.setAppointmentDate(rs.getDate("appointment_date"));
        appointment.setAppointmentTime(rs.getTime("appointment_time"));
        appointment.setStatus(rs.getString("status"));
    }

    rs.close();
    pst.close();
    con.close();

    return appointment;
}
 public List<Integer> getAppointmentIds() throws SQLException {

    List<Integer> ids = new ArrayList<>();

    String sql = "SELECT appointment_id FROM appointments ORDER BY appointment_id";

    try {
        Connection con = getConnection();

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ids.add(rs.getInt("appointment_id"));
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return ids;
}
public AppointmentDetails getAppointmentDetails(int appointmentId) throws Exception {

    Connection con = getConnection();

    String sql =
        "SELECT " +
        "a.appointment_id, " +
        "p.patient_name, " +
        "p.address, " +
        "p.contact_number, " +
        "d.dentist_name, " +
        "t.treatment_name, " +
        "a.appointment_date, " +
        "a.appointment_time, " +
        "a.status " +
        "FROM appointments a " +
        "JOIN patients p ON a.patient_id = p.patient_id " +
        "JOIN dentists d ON a.dentist_id = d.dentist_id " +
        "JOIN treatments t ON a.treatment_id = t.treatment_id " +
        "WHERE a.appointment_id = ?";

    PreparedStatement pst = con.prepareStatement(sql);

    pst.setInt(1, appointmentId);

    ResultSet rs = pst.executeQuery();

    AppointmentDetails details = null;

    if (rs.next()) {

        details = new AppointmentDetails();

        details.setAppointmentId(
                rs.getInt("appointment_id"));

        details.setPatientName(
                rs.getString("patient_name"));

        details.setPatientAddress(
                rs.getString("address"));

        details.setPatientContact(
                rs.getString("contact_number"));

        details.setDentistName(
                rs.getString("dentist_name"));

        details.setTreatmentName(
                rs.getString("treatment_name"));

       details.setAppointmentDate(
        rs.getDate("appointment_date").toString()
);

details.setAppointmentTime(
        rs.getTime("appointment_time").toString()
);

details.setStatus(
        rs.getString("status")
);
    }

    rs.close();
    pst.close();
    con.close();

    return details;
}
public List<model.AppointmentDetails> getAllAppointmentDetails()
        throws Exception {

    List<model.AppointmentDetails> list =
            new ArrayList<>();

    Connection con = getConnection();

    String sql =
        "SELECT "
        + "a.appointment_id, "
        + "p.patient_name, "
        + "p.address, "
        + "p.contact_number, "
        + "d.dentist_name, "
        + "t.treatment_name, "
        + "a.appointment_date, "
        + "a.appointment_time, "
        + "a.status "
        + "FROM appointments a "
        + "JOIN patients p "
        + "ON a.patient_id = p.patient_id "
        + "JOIN dentists d "
        + "ON a.dentist_id = d.dentist_id "
        + "JOIN treatments t "
        + "ON a.treatment_id = t.treatment_id "
        + "ORDER BY a.appointment_id";

    PreparedStatement pst =
            con.prepareStatement(sql);

    ResultSet rs =
            pst.executeQuery();

    while (rs.next()) {

        model.AppointmentDetails details =
                new model.AppointmentDetails();

        details.setAppointmentId(
                rs.getInt("appointment_id"));

        details.setPatientName(
                rs.getString("patient_name"));

        details.setPatientAddress(
                rs.getString("address"));

        details.setPatientContact(
                rs.getString("contact_number"));

        details.setDentistName(
                rs.getString("dentist_name"));

        details.setTreatmentName(
                rs.getString("treatment_name"));

        details.setAppointmentDate(
                rs.getDate("appointment_date").toString());

        details.setAppointmentTime(
                rs.getTime("appointment_time").toString());

        details.setStatus(
                rs.getString("status"));

        list.add(details);
    }

    rs.close();
    pst.close();
    con.close();

    return list;
}
}