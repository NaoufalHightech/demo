package ben.fr.demomigrationhexagonal.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetAdresseAssurerRequest")
public class GetAdresseAssurerRequest {
    private String nom;
    private String prenom;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
