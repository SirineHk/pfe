package com.example.bbbbbbbb.Services;
import com.example.bbbbbbbb.entities.Role;
import com.example.bbbbbbbb.entities.Utilisateur;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public String aff() {
        return "hello";
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sirine12");
    }

    private boolean checkAuth(String username, String password) throws ClassNotFoundException, SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.ADMIN")) {

            while (rs.next()) {
                System.out.println(rs.getString(3) + " " + rs.getString(4));
                if (rs.getString(3).equals(username) && rs.getString(4).equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }


    private long checkRole(String username) throws ClassNotFoundException, SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.ADMIN WHERE LOGIN = '" + username + "'")) {

            while (rs.next()) {
                System.out.println(rs.getString(8));
                return Integer.parseInt(rs.getString(8));
            }
        }
        return 0;
    }


    private boolean testActivation(String username) throws ClassNotFoundException, SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.ADMIN  WHERE LOGIN = '" + username + "'")) {

            while (rs.next()) {
                if (rs.getString(5).equals("D")) {
                    return true;
                }
            }
        }
        return false;
    }

    Boolean testAdress(String s) throws IOException, SQLException {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from PFE.ADMIN")) {
            while (rs.next()) {
                if ((rs.getString(3).equals(s))) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    private String titre(int r) throws SQLException {
        String nom = "";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.ROLE WHERE ID = '" + r + "'")) {
            while (rs.next()) {
                nom = rs.getString(2);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nom;
    }


    ////////////////////////////////Authentification///////////////////////////
    public String authentification() throws ClassNotFoundException, SQLException {

        String username = "sirine";
        String password = "sirine";
        if (username.isEmpty()) {
            return "Enter username";
        } else if (password.isEmpty()) {
            return "Enter your password";
        } else if (checkAuth(username, password)) {
            if (testActivation(username)) {
                return "Votre Compte est désactiver";
            } else {
                if (checkRole(username) == 1) {
                    return "Je suis un admin";
                } else if (checkRole(username) == 2) {
                    return "Je suis un superviseur";
                } else if (checkRole(username) == 3) {
                    return "Je suis un commercial";
                }
            }
        }
        return "Email ou mot de passe invalide";
    }


    ///////////////////////Création Compte////////////////////////////////////////
    public String creerCompte() throws IOException, SQLException {

        String a = "AAAA";
        String b = "123456";
        String c = "123456";
        long role = 1;
        if (a.isEmpty()) {
            return "taper l'email";
        } else if (b.isEmpty()) {
            return "taper le mot de passe";
        } else if (c.isEmpty()) {
            return "Confirmer le mot de passe";
        } else if (!(b.equals(c))) {
            return "Vérifier le mot de passe";
        } else if (testAdress(a)) {
            return "Email existe deja choisir un autre";
        } else {
            char stat = 'A';
            try (Connection con = getConnection();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("INSERT INTO PFE.ADMIN(LOGIN, PASSWORD,STATUS,ROLE) VALUES ('" + a + "','" + b + "','" + stat + "','" + role + "')")) {
                return "création avec succés";
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }


    //////////////////////Afficher liste user////////////////////////
    public List<Utilisateur> listUsers() throws SQLException, ClassNotFoundException {
        List<Utilisateur> users = new ArrayList<>();
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.ADMIN")) {

            while (rs.next()) {
                Long id = Long.valueOf(rs.getString(1));
                int r = Integer.parseInt(rs.getString(8));
                String nom=titre(r);
                users.add(new Utilisateur(id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7), new Role(r, nom)));
            }
        }
        return users;
    }




        public String actiDesa() throws ClassNotFoundException, SQLException {
        String username = "admin";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM PFE.ADMIN WHERE LOGIN = '" + username + "'")) {
            while (rs.next()) {
                if (rs.getString(5).equals("A")) {
                    char newStatus = 'D';
                    int rowsUpdated = st.executeUpdate("UPDATE PFE.ADMIN SET status = '" + newStatus + "' WHERE LOGIN ='" + username + "'");
                    if (rowsUpdated > 0) {
                        return "DESACTIVATION AVEC SUCCES";
                    } else {
                        return "Echec de la desactivation";
                    }
                } else {
                    char newStatus = 'A';
                    int rowsUpdated = st.executeUpdate("UPDATE PFE.ADMIN SET status = '" + newStatus + "' WHERE LOGIN ='" + username + "'");
                    if (rowsUpdated > 0) {
                        return "ACTIVATION AVEC SUCCES";
                    } else {
                        return "Echec de l'activation";
                    }
                }
            }
        }
        return "modification avec succès";
    }

    public String update() throws ClassNotFoundException, SQLException {
        String newN = "admine";
        String newE = "admine";
        String newP = "admine";
        String newPre = "adm";
        String newNum = "12345678";
        if (newNum.length() != 8) {
            return "num tel invalide";
        }
        long num = 1;
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            int rowsUpdated = st.executeUpdate("UPDATE PFE.ADMIN SET NOM = '" + newN + "', LOGIN = '" + newE + "', PASSWORD = '" + newP + "', PRENOM = '" + newPre + "', NUMTEL = '" + newNum + "' WHERE ID ='" + num + "'");
            return rowsUpdated > 0 ? "Modification réussie" : "Aucune modification effectuée";
        }
    }
}
