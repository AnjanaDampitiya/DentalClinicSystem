/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TreatmentDAO;
import java.util.List;
import model.Treatment;

public class TreatmentController {

    private TreatmentDAO treatmentDAO;

    public TreatmentController() {
        treatmentDAO = new TreatmentDAO();
    }

    // SAVE
    public boolean saveTreatment(Treatment treatment) throws Exception {
        return treatmentDAO.save(treatment);
    }

    // UPDATE
    public boolean updateTreatment(Treatment treatment) throws Exception {
        return treatmentDAO.update(treatment);
    }

    // DELETE
   public boolean deleteTreatment(int treatmentId) throws Exception {
    return treatmentDAO.delete(treatmentId);
}

    // GET ALL
    public List<Treatment> getAllTreatments() throws Exception {
        return treatmentDAO.getAllTreatments();
    }
}
