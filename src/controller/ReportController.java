/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReportDAO;
import java.util.List;

public class ReportController {

    private ReportDAO reportDAO;

    public ReportController() {
        reportDAO = new ReportDAO();
    }

    // DAILY APPOINTMENTS REPORT
    public List<String[]> getDailyAppointments(String date)
            throws Exception {

        return reportDAO.getDailyAppointments(date);
    }
    // MONTHLY REVENUE / BILLS REPORT
public List<String[]> getMonthlyRevenue(String month)
        throws Exception {

    return reportDAO.getMonthlyRevenue(month);
}
// PATIENT TREATMENT REPORT
public List<String[]> getPatientTreatments()
        throws Exception {

    return reportDAO.getPatientTreatments();
}
}