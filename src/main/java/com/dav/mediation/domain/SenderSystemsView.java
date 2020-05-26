package com.dav.mediation.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Class representing SenderSystemsView
 */
@Entity
@Table(name = "sender_systems_view")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SenderSystemsView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "sender_is", length = 50, nullable = false)
    private String senderIs;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderIs() {
        return senderIs;
    }

    public SenderSystemsView senderIs(String senderIs) {
        this.senderIs = senderIs;
        return this;
    }

    public void setSenderIs(String senderIs) {
        this.senderIs = senderIs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SenderSystemsView)) {
            return false;
        }
        return id != null && id.equals(((SenderSystemsView) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SenderSystemsView{" +
            "id=" + getId() +
            ", senderIs='" + getSenderIs() + "'" +
            "}";
    }
}
