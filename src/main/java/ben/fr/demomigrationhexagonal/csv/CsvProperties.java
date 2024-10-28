package ben.fr.demomigrationhexagonal.csv;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CsvProperties {

    public static final char DELIMITER = ';';

    public static final String[] HEADERS = {"nom", "prenom", "date_naissance", "adresse"};

}
