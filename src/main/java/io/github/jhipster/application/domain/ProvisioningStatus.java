package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ProvisioningStatus.
 */
@Entity
@Table(name = "provisioning_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProvisioningStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pvsid")
    private String pvsid;

    @Column(name = "serialnumber")
    private String serialnumber;

    @Column(name = "username")
    private String username;

    @Column(name = "siteid")
    private Long siteid;

    @Column(name = "environment")
    private String environment;

    @Column(name = "operation")
    private String operation;

    @Column(name = "status")
    private Long status;

    @Column(name = "jhi_level")
    private Long level;

    @Column(name = "jhi_timestamp")
    private Instant timestamp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPvsid() {
        return pvsid;
    }

    public ProvisioningStatus pvsid(String pvsid) {
        this.pvsid = pvsid;
        return this;
    }

    public void setPvsid(String pvsid) {
        this.pvsid = pvsid;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public ProvisioningStatus serialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
        return this;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getUsername() {
        return username;
    }

    public ProvisioningStatus username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSiteid() {
        return siteid;
    }

    public ProvisioningStatus siteid(Long siteid) {
        this.siteid = siteid;
        return this;
    }

    public void setSiteid(Long siteid) {
        this.siteid = siteid;
    }

    public String getEnvironment() {
        return environment;
    }

    public ProvisioningStatus environment(String environment) {
        this.environment = environment;
        return this;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getOperation() {
        return operation;
    }

    public ProvisioningStatus operation(String operation) {
        this.operation = operation;
        return this;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getStatus() {
        return status;
    }

    public ProvisioningStatus status(Long status) {
        this.status = status;
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getLevel() {
        return level;
    }

    public ProvisioningStatus level(Long level) {
        this.level = level;
        return this;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public ProvisioningStatus timestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProvisioningStatus provisioningStatus = (ProvisioningStatus) o;
        if (provisioningStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provisioningStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProvisioningStatus{" +
            "id=" + getId() +
            ", pvsid='" + getPvsid() + "'" +
            ", serialnumber='" + getSerialnumber() + "'" +
            ", username='" + getUsername() + "'" +
            ", siteid=" + getSiteid() +
            ", environment='" + getEnvironment() + "'" +
            ", operation='" + getOperation() + "'" +
            ", status=" + getStatus() +
            ", level=" + getLevel() +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
