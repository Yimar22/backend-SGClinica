package co.edu.icesi.emt.core.application.controller.personalinformation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.emt.auth.util.validators.UserAdminValidator;
import co.edu.icesi.emt.common.exception.model.UserIsNotAdminException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;
import co.edu.icesi.emt.core.application.dto.personalinformation.PersonalInformationModificationDTO;
import co.edu.icesi.emt.core.application.dto.personalinformation.PersonalInformationPreviewDTO;
import co.edu.icesi.emt.core.application.dto.personalinformation.PersonalInformationRetrievalDTO;
import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformation;
import co.edu.icesi.emt.core.domain.service.personalinformation.PersonalInformationService;

@RestController
@RequestMapping("/personalinformation")
public class PersonalInformationController {

    private final PersonalInformationService personalInformationService;

    private final UserAdminValidator userAdminValidator;

    @Autowired
    public PersonalInformationController(PersonalInformationService personalInformationService,
            UserAdminValidator userAdminValidator) {
        this.personalInformationService = personalInformationService;
        this.userAdminValidator = userAdminValidator;
    }

    @GetMapping
    public ResponseEntity<List<PersonalInformationPreviewDTO>> findAll(final HttpServletRequest httpRequest)
            throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(httpRequest);
        return ResponseEntity.ok(PersonalInformationPreviewDTO.from(personalInformationService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInformationRetrievalDTO> findById(@PathVariable("id") String id,
            final HttpServletRequest httpRequest)
            throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(httpRequest);
        PersonalInformation personalInformation = personalInformationService.findById(id);

        if (personalInformation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(PersonalInformationRetrievalDTO.from(personalInformation));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PersonalInformationModificationDTO personalInformationCreationDTO,
            final HttpServletRequest httpRequest)
            throws UserIsNotAdminException, UserNotFoundException {

        userAdminValidator.validate(httpRequest);

        personalInformationService.save(PersonalInformationModificationDTO.fromDTO(personalInformationCreationDTO));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id, final HttpServletRequest httpRequest)
            throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(httpRequest);
        personalInformationService.deleteById(id);

        return ResponseEntity.ok("Personal information deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
            @RequestBody PersonalInformationModificationDTO personalInformationModificationDTO, final HttpServletRequest httpRequest)
            throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(httpRequest);

        personalInformationService.update( PersonalInformationModificationDTO.fromDTO(personalInformationModificationDTO));
        return ResponseEntity.ok("Personal information updated");
    }
}
