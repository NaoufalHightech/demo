package ben.fr.demomigrationhexagonal.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetAdresseAssurerResponse")
public class GetAdresseAssurerResponse {
    private String adresse;

    // Getters et Setters
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
