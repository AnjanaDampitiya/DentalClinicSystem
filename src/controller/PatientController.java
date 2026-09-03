/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PatientDAO;
import model.Patient;

public class PatientController {

    private PatientDAO patientDAO;

    public PatientController() {
        patientDAO = new PatientDAO();
    }

    public boolean savePatient(Patient patient) throws Exception {
        return patientDAO.save(patient);
    }

    public boolean updatePatient(Patient patient) throws Exception {
        return patientDAO.update(patient);
    }

    public boolean deletePatient(int patientId) throws Exception {
        return patientDAO.delete(patientId);
    }
}
