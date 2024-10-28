package ben.fr.demomigrationhexagonal.mapper;

import ben.fr.demomigrationhexagonal.csv.CsvAssurer;
import ben.fr.demomigrationhexagonal.domain.Assurer;
import ben.fr.demomigrationhexagonal.rest.dto.AssurerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AssurerMapper {

    @Mapping(target = "dateNaissance", source = "dateNaissance", qualifiedByName = "stringToDate") // Si vous utilisez LocalDate
    Assurer mapCsvAssurerToAssurer(CsvAssurer csvAssurer);

    List<Assurer> mapCsvAssurersToAssurers(List<CsvAssurer> csvAssurers);

    AssurerDto mapToDto(Assurer assurer);

    List<AssurerDto> mapToDtoList(List<Assurer> assures);

    // Méthode pour mapper la date
    @Named("stringToDate")
    default LocalDate stringToDate(String dateNaissanceString) {
        // Liste des formats supportés
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ISO_LOCAL_DATE // yyyy-MM-dd
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateNaissanceString, formatter);
            } catch (DateTimeParseException e) {
                // Ignore et essayer le format suivant
            }
        }

        // Si aucun format ne correspond, lever une exception
        throw new IllegalArgumentException("Date format not supported: " + dateNaissanceString);
    }
}
