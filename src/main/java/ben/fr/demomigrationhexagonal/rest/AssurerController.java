package ben.fr.demomigrationhexagonal.rest;

import ben.fr.demomigrationhexagonal.csv.CsvAssurer;
import ben.fr.demomigrationhexagonal.rest.dto.AssurerDto;
import ben.fr.demomigrationhexagonal.service.AssurerFileParser;

import ben.fr.demomigrationhexagonal.service.AssurerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/assurers")
//@RequiredArgsConstructor
public class AssurerController {

    private static final Logger logger = LoggerFactory.getLogger(AssurerController.class);

    private final AssurerFileParser assurerFileParser;
    private final AssurerService assurerService;

    public AssurerController(AssurerFileParser assurerFileParser, AssurerService assurerService) {
        this.assurerFileParser = assurerFileParser;
        this.assurerService = assurerService;
        logger.info("AssurerController initialized with assurerFileParser and assurerService");
    }

    @PostMapping("/import")
    @ResponseStatus(NO_CONTENT)
    public void importAssures(@RequestParam("file") MultipartFile fileToImport) throws IOException {
        List<CsvAssurer> csvEvents = assurerFileParser.parse(fileToImport);

        assurerService.importAssurers(csvEvents);
    }

    @GetMapping("/upcoming")
    public List<AssurerDto> getUpcomingEvents() {
        return assurerService.getUpcomingAssurers();
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportAssures() {
        InputStreamResource fileToExport = new InputStreamResource(new ByteArrayInputStream(assurerService.exportAssurers()));

        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment;filename=assurers.csv")
                .contentType(MediaType.parseMediaType("text/csv;charset=UTF-8"))
                .body(fileToExport);
    }

}
