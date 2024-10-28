package ben.fr.demomigrationhexagonal.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // Assurez-vous d'avoir un constructeur sans arguments
public class AssurerDto {
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresse;
}
