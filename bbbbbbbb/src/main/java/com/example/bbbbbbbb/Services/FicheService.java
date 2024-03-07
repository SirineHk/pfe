package com.example.bbbbbbbb.Services;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FicheService {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sirine12");
    }

    public String update() throws ClassNotFoundException, SQLException, ParseException {
        String newN = "nom";
        String newDStr = "2000-02-15"; // Nouvelle date au format "yyyy-MM-dd"
        String newAdr= "adresse";
        String newEma = "email";
        String newDesc = "description";
        String newNum = "12345687";

        if (newNum.length() != 8) {
            return "num tel invalide";
        }

        long num = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newD = sdf.parse(newDStr);
        java.sql.Date newSqlDate = new java.sql.Date(newD.getTime());

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE PFE.FICHE SET NOM = ?, DN = ?, ADRESSE = ?, EMAIL = ?, TEL = ?, DESCRIPTION = ? WHERE ID = ?")) {

            st.setString(1, newN);
            st.setDate(2, newSqlDate);
            st.setString(3, newAdr);
            st.setString(4, newEma);
            st.setString(5, newNum);
            st.setString(6, newDesc);
            st.setLong(7, num);

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0 ? "Modification réussie" : "Aucune modification effectuée";
        }
    }
}
