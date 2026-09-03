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
import model.Treatment;

public class TreatmentDAO {

    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dental_clinic",
                "root",
                System.getenv("DENTAL_DB_PASSWORD")
        );
    }

    // SAVE
    public boolean save(Treatment treatment) throws Exception {

        Connection con = getConnection();

        String checkSql =
                "SELECT treatment_id FROM treatments "
                + "WHERE treatment_name = ?";

        PreparedStatement checkPst =
                con.prepareStatement(checkSql);

        checkPst.setString(1, treatment.getTreatmentName());

        ResultSet rs = checkPst.executeQuery();

        if (rs.next()) {

            rs.close();
            checkPst.close();
            con.close();

            return false;
        }

        rs.close();
        checkPst.close();

        String sql =
                "INSERT INTO treatments "
                + "(treatment_name, description, fee) "
                + "VALUES (?, ?, ?)";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, treatment.getTreatmentName());
        pst.setString(2, treatment.getDescription());
        pst.setDouble(3, treatment.getFee());

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }

    // UPDATE
    public boolean update(Treatment treatment) throws Exception {

        Connection con = getConnection();

        String sql =
                "UPDATE treatments SET "
                + "treatment_name=?, description=?, fee=? "
                + "WHERE treatment_id=?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, treatment.getTreatmentName());
        pst.setString(2, treatment.getDescription());
        pst.setDouble(3, treatment.getFee());
        pst.setInt(4, treatment.getTreatmentId());

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }

   // DELETE
public boolean delete(int treatmentId) throws Exception {

    Connection con = getConnection();

    try {

        String sql =
                "DELETE FROM treatments WHERE treatment_id=?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, treatmentId);

        int rows = pst.executeUpdate();

        pst.close();

        return rows > 0;

    } catch (java.sql.SQLIntegrityConstraintViolationException e) {

        // Treatment is already used in appointments
        return false;

    } finally {
        con.close();
    }
}

    // GET ALL TREATMENTS
    public List<Treatment> getAllTreatments() throws Exception {

        List<Treatment> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
                "SELECT treatment_id, treatment_name, "
                + "description, fee "
                + "FROM treatments "
                + "ORDER BY treatment_id DESC";

        PreparedStatement pst =
                con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Treatment treatment = new Treatment();

            treatment.setTreatmentId(
                    rs.getInt("treatment_id"));

            treatment.setTreatmentName(
                    rs.getString("treatment_name"));

            treatment.setDescription(
                    rs.getString("description"));

            treatment.setFee(
                    rs.getDouble("fee"));

            list.add(treatment);
        }

        rs.close();
        pst.close();
        con.close();

        return list;
    }
}