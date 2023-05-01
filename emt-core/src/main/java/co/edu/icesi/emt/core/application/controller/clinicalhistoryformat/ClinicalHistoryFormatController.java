package co.edu.icesi.emt.core.application.controller.clinicalhistoryformat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.emt.core.application.dto.clinicalhistory.format.ClinicalHistoryFormatCreationDTO;
import co.edu.icesi.emt.core.application.dto.clinicalhistory.format.ClinicalHistoryFormatLightDTO;
import co.edu.icesi.emt.core.application.dto.clinicalhistory.format.ClinicalHistoryFormatRetrievalDTO;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatRetrieval;
import co.edu.icesi.emt.core.domain.service.clinicalhistory.format.ClinicalHistoryFormatService;

@RestController
@RequestMapping("/clinicalhistoryformat")
public class ClinicalHistoryFormatController {

    private final ClinicalHistoryFormatService clinicalHistoryFormatService;

    @Autowired
    public ClinicalHistoryFormatController(ClinicalHistoryFormatService clinicalHistoryFormatService) {
        this.clinicalHistoryFormatService = clinicalHistoryFormatService;
    }

    @GetMapping
    public ResponseEntity<List<ClinicalHistoryFormatLightDTO>> findAll() {
        return ResponseEntity.ok(ClinicalHistoryFormatLightDTO.from(clinicalHistoryFormatService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalHistoryFormatRetrievalDTO> createClinicalHistoryFormat(
            @PathVariable("id") String id) {
        ClinicalHistoryFormatRetrieval format = clinicalHistoryFormatService.findById(id);

        if (format == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ClinicalHistoryFormatRetrievalDTO.from(format));
    }

    @PostMapping
    public ResponseEntity<ClinicalHistoryFormatRetrievalDTO> createClinicalHistoryFormat(
            @RequestBody ClinicalHistoryFormatCreationDTO format) {

        this.clinicalHistoryFormatService.save(ClinicalHistoryFormatCreationDTO.fromDTO(format));

        return ResponseEntity.ok().build();
    }
}
