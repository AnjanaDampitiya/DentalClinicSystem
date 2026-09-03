/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DentistDAO;
import model.Dentist;
import java.util.List;

public class DentistController {

    private DentistDAO dentistDAO;

    public DentistController() {
        dentistDAO = new DentistDAO();
    }

    // SAVE
    public boolean saveDentist(Dentist dentist) throws Exception {
        return dentistDAO.save(dentist);
    }

    // UPDATE
    public boolean updateDentist(Dentist dentist) throws Exception {
        return dentistDAO.update(dentist);
    }

    // DELETE
    public boolean deleteDentist(int dentistId) throws Exception {
        return dentistDAO.delete(dentistId);
    }

    // GET ALL
    public List<Dentist> getAllDentists() throws Exception {
        return dentistDAO.getAllDentists();
    }
}