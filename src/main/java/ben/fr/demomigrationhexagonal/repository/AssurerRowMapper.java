package ben.fr.demomigrationhexagonal.repository;


import ben.fr.demomigrationhexagonal.domain.Assurer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssurerRowMapper implements RowMapper<Assurer> {
    @Override
    public Assurer mapRow(ResultSet rs, int rowNum) throws SQLException {
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        // Formatage de la date de naissance
        LocalDate dateNaissance = rs.getDate("date_naissance").toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateNaissance.format(formatter);
        String adresse = rs.getString("adresse");
        return new Assurer(nom, prenom, formattedDate, adresse);
    }
}
