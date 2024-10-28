package ben.fr.demomigrationhexagonal.csv;

import lombok.Builder;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public record CsvAssurer(
        String nom,
        String prenom,
        String dateNaissance, // Format recommandé: yyyy-MM-dd
        String adresse
) {
    public static CsvAssurerBuilder builder() {
        return new CsvAssurerBuilder();
    }

    public static class CsvAssurerBuilder {
        private String nom;
        private String prenom;
        private String dateNaissance;
        private String adresse;

        public CsvAssurerBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public CsvAssurerBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public CsvAssurerBuilder dateNaissance(String dateNaissance) {
            this.dateNaissance = dateNaissance;
            return this;
        }

        public CsvAssurerBuilder adresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public CsvAssurer build() {
            return new CsvAssurer(nom, prenom, dateNaissance, adresse);
        }
    }
}


