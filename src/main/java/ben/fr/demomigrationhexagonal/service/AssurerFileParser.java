package ben.fr.demomigrationhexagonal.service;

import ben.fr.demomigrationhexagonal.csv.CsvAssurer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static ben.fr.demomigrationhexagonal.csv.CsvProperties.HEADERS;
import static ben.fr.demomigrationhexagonal.csv.CsvProperties.DELIMITER;
@Service
public class AssurerFileParser {

    public List<CsvAssurer> parse(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        CSVFormat csvFormat = CSVFormat.newFormat(DELIMITER).builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (CSVParser parser = new CSVParser(inputStreamReader, csvFormat)) {
            return parser.stream()
                    .filter(csvRecord -> csvRecord.size() == HEADERS.length)
                    .map(this::mapToCsvEvent)
                    .toList();
        }
    }

    private CsvAssurer mapToCsvEvent(CSVRecord csvRecord) {
        return CsvAssurer.builder()
                .nom(csvRecord.get(HEADERS[0]))
                .prenom(csvRecord.get(HEADERS[1]))
                .dateNaissance(csvRecord.get(HEADERS[2]))
                .adresse(csvRecord.get(HEADERS[3]))
                .build();
    }

}
