package com.example.bbbbbbbb.Services;

import com.example.bbbbbbbb.entities.Groupe;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class GroupService {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sirine12");
    }

    public List<Groupe> listGroupes() {
        List<Groupe> groupes = new ArrayList<>();
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.GROUPE" )) {

            while (rs.next()) {
                Long id = Long.valueOf(rs.getString(1));
                ;
                groupes.add(new Groupe(id, rs.getString(2)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return groupes;
    }


    public String newGroup() throws IOException, SQLException {

        String a = "sssss";
        if (a.isEmpty()) {
            return "taper le nom du groupe";
        } else {
            try (Connection con = getConnection();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("INSERT INTO PFE.GROUPE(NOM) VALUES ('" + a + "')")) {
                return "création avec succés";
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }



    public String modif() throws ClassNotFoundException, SQLException {
        String newN = "admine";
        long num = 1;
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            int rowsUpdated = st.executeUpdate("UPDATE PFE.GROUPE SET NOM = '" + newN + "' WHERE ID ='" + num + "'");
            return rowsUpdated > 0 ? "Modification réussie" : "Aucune modification effectuée";
        }
    }

    private void inser(int sup, int grp) {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("INSERT INTO PFE.GRPUSER VALUES ('" + sup + "','"+grp +"')")) {
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void supp(int grp) {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            int rowsAffected = st.executeUpdate("DELETE FROM PFE.GRPUSER WHERE IDGRP='" + grp+"'");
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(int sup) {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            int rowsAffected = st.executeUpdate("DELETE FROM PFE.GRPUSER WHERE IDUSER='" + sup+"'");
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String addSup() throws IOException, SQLException {
        int sup = 21;
        int grp = 2;
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            // Delete existing supervisor with ID 'sup'
            delete(sup);

            // Delete existing supervisor from group 'grp'
            supp(grp);

            // Insert the new supervisor into group 'grp'
            inser(sup, grp);

            return "success";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public String addComm() throws IOException, SQLException {
        List<Integer> l= List.of(23,2);
        int grp = 1;
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            for (int i :l){
                // Delete existing supervisor with ID 'sup'
                delete(i);
                // Insert the new supervisor into group 'grp'
                inser(i, grp);
            }

            return "success";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




}

