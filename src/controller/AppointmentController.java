/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.AppointmentDAO;
import model.Appointment;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Admin
 */

public class AppointmentController {
    private AppointmentDAO appointmentDAO;

public AppointmentController() {
    appointmentDAO = new AppointmentDAO();
}

public boolean saveAppointment(Appointment appointment) throws Exception {
    return appointmentDAO.save(appointment);
}

public boolean updateAppointment(Appointment appointment) throws Exception {
    return appointmentDAO.update(appointment);
}

public boolean deleteAppointment(int appointmentId) throws Exception {
    return appointmentDAO.delete(appointmentId);
}  
public java.util.List<Appointment> getAllAppointments() throws Exception {
    AppointmentDAO dao = new AppointmentDAO();
    return dao.getAllAppointments();
}
public Appointment searchByAppointmentId(int appointmentId) throws Exception {
    AppointmentDAO dao = new AppointmentDAO();
    return dao.searchByAppointmentId(appointmentId);
}

public List<Integer> getAppointmentIds() throws SQLException {
    return appointmentDAO.getAppointmentIds();
}

public List<model.AppointmentDetails> getAllAppointmentDetails()
        throws Exception {

    return appointmentDAO.getAllAppointmentDetails();
}

public model.AppointmentDetails getAppointmentDetails(
        int appointmentId) throws Exception {

    return appointmentDAO.getAppointmentDetails(appointmentId);
}
}
