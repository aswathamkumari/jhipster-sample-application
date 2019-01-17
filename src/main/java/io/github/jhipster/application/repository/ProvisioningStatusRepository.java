package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ProvisioningStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProvisioningStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvisioningStatusRepository extends JpaRepository<ProvisioningStatus, Long> {

}
