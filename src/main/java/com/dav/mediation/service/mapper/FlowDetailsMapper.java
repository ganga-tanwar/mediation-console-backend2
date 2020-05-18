package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.FlowDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowDetails} and its DTO {@link FlowDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FlowDetailsMapper extends EntityMapper<FlowDetailsDTO, FlowDetails> {



    default FlowDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowDetails flowDetails = new FlowDetails();
        flowDetails.setId(id);
        return flowDetails;
    }
}
