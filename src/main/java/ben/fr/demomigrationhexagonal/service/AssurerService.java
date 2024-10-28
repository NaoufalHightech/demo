package ben.fr.demomigrationhexagonal.service;

import ben.fr.demomigrationhexagonal.AssurerExportException;
import ben.fr.demomigrationhexagonal.csv.CsvAssurer;
import ben.fr.demomigrationhexagonal.domain.Assurer;
import ben.fr.demomigrationhexagonal.mapper.AssurerMapper;
import ben.fr.demomigrationhexagonal.repository.AssurerRepository;
import ben.fr.demomigrationhexagonal.rest.dto.AssurerDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static ben.fr.demomigrationhexagonal.csv.CsvProperties.DELIMITER;
import static ben.fr.demomigrationhexagonal.csv.CsvProperties.HEADERS;
import static org.apache.commons.lang3.CharUtils.LF;

@Slf4j
@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssurerService {

    private final AssurerMapper assurerMapper; // Mapper pour transformer CsvAssurer en Assurer
    private final AssurerRepository assurerRepository;
    public AssurerService(AssurerMapper assurerMapper, AssurerRepository assurerRepository){
        this.assurerMapper = assurerMapper;
        this.assurerRepository = assurerRepository;
    }
    @Transactional
    public void importAssurers(List<CsvAssurer> csvAssures) {
        List<Assurer> assures = assurerMapper.mapCsvAssurersToAssurers(csvAssures);
        saveAssurers(assures);
    }
    
    private void saveAssurers(List<Assurer> assures) {
        // Implémentez la logique pour sauvegarder les assurés (DB, API, etc.)
        assurerRepository.saveAssurers(assures);
    }

    public List<AssurerDto> getUpcomingAssurers() {
        List<Assurer> upcomingAssurers = getUpcomingAssurersFromDB(); // Récupérer les assurés à venir
        return assurerMapper.mapToDtoList(upcomingAssurers); // Mapper en DTO
    }

    private List<Assurer> getUpcomingAssurersFromDB() {
        // Implémentez la logique pour récupérer les assurés à venir (DB, API, etc.)
        return new ArrayList<>(); // Remplacez par une vraie implémentation
    }

    public byte[] exportAssurers() {
        CSVFormat csvFormat = CSVFormat.newFormat(DELIMITER).builder()
                .setHeader(HEADERS)
                .setRecordSeparator(LF)
                .build();

        List<Assurer> assures = getAllAssurers(); // Récupérer tous les assures

        List<String[]> csvColumns = assures.stream()
                .map(this::getCsvColumns)
                .toList();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(byteArrayOutputStream), csvFormat)) {

            csvPrinter.printRecords(csvColumns);
            csvPrinter.flush();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            //logger.error("Une erreur est survenue lors de l'export", e);
            throw new AssurerExportException(e);
        }
    }

    private List<Assurer> getAllAssurers() {
        // Implémentez la logique pour récupérer tous les assurés (DB, API, etc.)
        return assurerRepository.getAllAssurers(); // Remplacez par une vraie implémentation
    }

    private String[] getCsvColumns(Assurer assurer) {
        return new String[]{
                assurer.getNom(),
                assurer.getPrenom(),
                assurer.getAdresse(),
                assurer.getDateNaissance()
        };
    }
}