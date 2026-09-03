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
import model.Dentist;

public class DentistDAO {

    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dental_clinic",
                "root",
                System.getenv("DENTAL_DB_PASSWORD")
        );
    }

    // SAVE
    public boolean save(Dentist dentist) throws Exception {

        Connection con = getConnection();

        String checkSql =
                "SELECT dentist_id FROM dentists "
                + "WHERE dentist_name = ? AND contact_number = ?";

        PreparedStatement checkPst =
                con.prepareStatement(checkSql);

        checkPst.setString(1, dentist.getDentistName());
        checkPst.setString(2, dentist.getContactNumber());

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
                "INSERT INTO dentists "
                + "(dentist_name, specialization, contact_number) "
                + "VALUES (?, ?, ?)";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, dentist.getDentistName());
        pst.setString(2, dentist.getSpecialization());
        pst.setString(3, dentist.getContactNumber());

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }

    // UPDATE
    public boolean update(Dentist dentist) throws Exception {

        Connection con = getConnection();

        String sql =
                "UPDATE dentists SET "
                + "dentist_name=?, specialization=?, contact_number=? "
                + "WHERE dentist_id=?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, dentist.getDentistName());
        pst.setString(2, dentist.getSpecialization());
        pst.setString(3, dentist.getContactNumber());
        pst.setInt(4, dentist.getDentistId());

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }

    // DELETE
    public boolean delete(int dentistId) throws Exception {

        Connection con = getConnection();

        String sql =
                "DELETE FROM dentists WHERE dentist_id=?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, dentistId);

        int rows = pst.executeUpdate();

        pst.close();
        con.close();

        return rows > 0;
    }

    // GET ALL DENTISTS
    public List<Dentist> getAllDentists() throws Exception {

        List<Dentist> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
                "SELECT dentist_id, dentist_name, "
                + "specialization, contact_number "
                + "FROM dentists "
                + "ORDER BY dentist_id DESC";

        PreparedStatement pst =
                con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Dentist dentist = new Dentist();

            dentist.setDentistId(
                    rs.getInt("dentist_id"));

            dentist.setDentistName(
                    rs.getString("dentist_name"));

            dentist.setSpecialization(
                    rs.getString("specialization"));

            dentist.setContactNumber(
                    rs.getString("contact_number"));

            list.add(dentist);
        }

        rs.close();
        pst.close();
        con.close();

        return list;
    }
}