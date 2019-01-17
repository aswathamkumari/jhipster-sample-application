package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ProvisioningStatus1;
import io.github.jhipster.application.repository.ProvisioningStatus1Repository;
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
 * REST controller for managing ProvisioningStatus1.
 */
@RestController
@RequestMapping("/api")
public class ProvisioningStatus1Resource {

    private final Logger log = LoggerFactory.getLogger(ProvisioningStatus1Resource.class);

    private static final String ENTITY_NAME = "provisioningStatus1";

    private final ProvisioningStatus1Repository provisioningStatus1Repository;

    public ProvisioningStatus1Resource(ProvisioningStatus1Repository provisioningStatus1Repository) {
        this.provisioningStatus1Repository = provisioningStatus1Repository;
    }

    /**
     * POST  /provisioning-status-1-s : Create a new provisioningStatus1.
     *
     * @param provisioningStatus1 the provisioningStatus1 to create
     * @return the ResponseEntity with status 201 (Created) and with body the new provisioningStatus1, or with status 400 (Bad Request) if the provisioningStatus1 has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/provisioning-status-1-s")
    @Timed
    public ResponseEntity<ProvisioningStatus1> createProvisioningStatus1(@RequestBody ProvisioningStatus1 provisioningStatus1) throws URISyntaxException {
        log.debug("REST request to save ProvisioningStatus1 : {}", provisioningStatus1);
        if (provisioningStatus1.getId() != null) {
            throw new BadRequestAlertException("A new provisioningStatus1 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvisioningStatus1 result = provisioningStatus1Repository.save(provisioningStatus1);
        return ResponseEntity.created(new URI("/api/provisioning-status-1-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provisioning-status-1-s : Updates an existing provisioningStatus1.
     *
     * @param provisioningStatus1 the provisioningStatus1 to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated provisioningStatus1,
     * or with status 400 (Bad Request) if the provisioningStatus1 is not valid,
     * or with status 500 (Internal Server Error) if the provisioningStatus1 couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/provisioning-status-1-s")
    @Timed
    public ResponseEntity<ProvisioningStatus1> updateProvisioningStatus1(@RequestBody ProvisioningStatus1 provisioningStatus1) throws URISyntaxException {
        log.debug("REST request to update ProvisioningStatus1 : {}", provisioningStatus1);
        if (provisioningStatus1.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProvisioningStatus1 result = provisioningStatus1Repository.save(provisioningStatus1);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, provisioningStatus1.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provisioning-status-1-s : get all the provisioningStatus1S.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of provisioningStatus1S in body
     */
    @GetMapping("/provisioning-status-1-s")
    @Timed
    public List<ProvisioningStatus1> getAllProvisioningStatus1S() {
        log.debug("REST request to get all ProvisioningStatus1S");
        return provisioningStatus1Repository.findAll();
    }

    /**
     * GET  /provisioning-status-1-s/:id : get the "id" provisioningStatus1.
     *
     * @param id the id of the provisioningStatus1 to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the provisioningStatus1, or with status 404 (Not Found)
     */
    @GetMapping("/provisioning-status-1-s/{id}")
    @Timed
    public ResponseEntity<ProvisioningStatus1> getProvisioningStatus1(@PathVariable Long id) {
        log.debug("REST request to get ProvisioningStatus1 : {}", id);
        Optional<ProvisioningStatus1> provisioningStatus1 = provisioningStatus1Repository.findById(id);
        return ResponseUtil.wrapOrNotFound(provisioningStatus1);
    }

    /**
     * DELETE  /provisioning-status-1-s/:id : delete the "id" provisioningStatus1.
     *
     * @param id the id of the provisioningStatus1 to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/provisioning-status-1-s/{id}")
    @Timed
    public ResponseEntity<Void> deleteProvisioningStatus1(@PathVariable Long id) {
        log.debug("REST request to delete ProvisioningStatus1 : {}", id);

        provisioningStatus1Repository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
