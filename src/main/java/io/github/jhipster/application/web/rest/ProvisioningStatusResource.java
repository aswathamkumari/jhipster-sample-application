package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ProvisioningStatus;
import io.github.jhipster.application.repository.ProvisioningStatusRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProvisioningStatus.
 */
@RestController
@RequestMapping("/api")
public class ProvisioningStatusResource {

    private final Logger log = LoggerFactory.getLogger(ProvisioningStatusResource.class);

    private static final String ENTITY_NAME = "provisioningStatus";

    private final ProvisioningStatusRepository provisioningStatusRepository;

    public ProvisioningStatusResource(ProvisioningStatusRepository provisioningStatusRepository) {
        this.provisioningStatusRepository = provisioningStatusRepository;
    }

    /**
     * POST  /provisioning-statuses : Create a new provisioningStatus.
     *
     * @param provisioningStatus the provisioningStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new provisioningStatus, or with status 400 (Bad Request) if the provisioningStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/provisioning-statuses")
    @Timed
    public ResponseEntity<ProvisioningStatus> createProvisioningStatus(@RequestBody ProvisioningStatus provisioningStatus) throws URISyntaxException {
        log.debug("REST request to save ProvisioningStatus : {}", provisioningStatus);
        if (provisioningStatus.getId() != null) {
            throw new BadRequestAlertException("A new provisioningStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvisioningStatus result = provisioningStatusRepository.save(provisioningStatus);
        return ResponseEntity.created(new URI("/api/provisioning-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provisioning-statuses : Updates an existing provisioningStatus.
     *
     * @param provisioningStatus the provisioningStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated provisioningStatus,
     * or with status 400 (Bad Request) if the provisioningStatus is not valid,
     * or with status 500 (Internal Server Error) if the provisioningStatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/provisioning-statuses")
    @Timed
    public ResponseEntity<ProvisioningStatus> updateProvisioningStatus(@RequestBody ProvisioningStatus provisioningStatus) throws URISyntaxException {
        log.debug("REST request to update ProvisioningStatus : {}", provisioningStatus);
        if (provisioningStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProvisioningStatus result = provisioningStatusRepository.save(provisioningStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, provisioningStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provisioning-statuses : get all the provisioningStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of provisioningStatuses in body
     */
    @GetMapping("/provisioning-statuses")
    @Timed
    public List<ProvisioningStatus> getAllProvisioningStatuses() {
        log.debug("REST request to get all ProvisioningStatuses");
        return provisioningStatusRepository.findAll();
    }

    /**
     * GET  /provisioning-statuses/:id : get the "id" provisioningStatus.
     *
     * @param id the id of the provisioningStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the provisioningStatus, or with status 404 (Not Found)
     */
    @GetMapping("/provisioning-statuses/{id}")
    @Timed
    public ResponseEntity<ProvisioningStatus> getProvisioningStatus(@PathVariable Long id) {
        log.debug("REST request to get ProvisioningStatus : {}", id);
        Optional<ProvisioningStatus> provisioningStatus = provisioningStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(provisioningStatus);
    }

    /**
     * DELETE  /provisioning-statuses/:id : delete the "id" provisioningStatus.
     *
     * @param id the id of the provisioningStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/provisioning-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteProvisioningStatus(@PathVariable Long id) {
        log.debug("REST request to delete ProvisioningStatus : {}", id);

        provisioningStatusRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
