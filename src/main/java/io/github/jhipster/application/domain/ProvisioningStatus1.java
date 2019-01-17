package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ProvisioningStatus1.
 */
@Entity
@Table(name = "provisioning_status_1")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProvisioningStatus1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pvsid")
    private String pvsid;

    @Column(name = "serialnumber")
    private String serialnumber;

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

    public ProvisioningStatus1 pvsid(String pvsid) {
        this.pvsid = pvsid;
        return this;
    }

    public void setPvsid(String pvsid) {
        this.pvsid = pvsid;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public ProvisioningStatus1 serialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
        return this;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
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
        ProvisioningStatus1 provisioningStatus1 = (ProvisioningStatus1) o;
        if (provisioningStatus1.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provisioningStatus1.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProvisioningStatus1{" +
            "id=" + getId() +
            ", pvsid='" + getPvsid() + "'" +
            ", serialnumber='" + getSerialnumber() + "'" +
            "}";
    }
}
