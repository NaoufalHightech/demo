package ben.fr.demomigrationhexagonal.repository;

import ben.fr.demomigrationhexagonal.domain.Assurer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AssurerRepository {

    private final JdbcTemplate jdbcTemplate;

    public AssurerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAssurers(List<Assurer> assures)  {
        String sql = "INSERT INTO assurers (nom, prenom, date_naissance, adresse) VALUES ( ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Assurer assurer = assures.get(i);
                ps.setString(1, assurer.getNom());
                ps.setString(2, assurer.getPrenom());
                ps.setDate(3, java.sql.Date.valueOf(assurer.getDateNaissance()));
                ps.setString(4, assurer.getAdresse());
            }

            @Override
            public int getBatchSize() {
                return assures.size();
            }
        });
    }


    public List<Assurer> getAllAssurers() {
        String sql = "SELECT nom, prenom, date_naissance, adresse FROM assurers"; // Remplacez 'assureurs' par votre nom de table
        //return jdbcTemplate.query(sql, new AssurerRowMapper());
        // Peux-être remplacer par des lambda
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new Assurer(
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("date_naissance"),
                                rs.getString("adresse")
                        )
        );
    }
}

