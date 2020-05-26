package com.dav.mediation.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Class representing ReceiverSyetemsView
 */
@Entity
@Table(name = "receiver_syetems_view")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReceiverSyetemsView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "receiver_is", length = 50, nullable = false)
    private String receiverIs;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverIs() {
        return receiverIs;
    }

    public ReceiverSyetemsView receiverIs(String receiverIs) {
        this.receiverIs = receiverIs;
        return this;
    }

    public void setReceiverIs(String receiverIs) {
        this.receiverIs = receiverIs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReceiverSyetemsView)) {
            return false;
        }
        return id != null && id.equals(((ReceiverSyetemsView) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReceiverSyetemsView{" +
            "id=" + getId() +
            ", receiverIs='" + getReceiverIs() + "'" +
            "}";
    }
}
