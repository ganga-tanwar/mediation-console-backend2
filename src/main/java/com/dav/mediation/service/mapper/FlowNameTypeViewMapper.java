package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.FlowNameTypeViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowNameTypeView} and its DTO {@link FlowNameTypeViewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FlowNameTypeViewMapper extends EntityMapper<FlowNameTypeViewDTO, FlowNameTypeView> {



    default FlowNameTypeView fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowNameTypeView flowNameTypeView = new FlowNameTypeView();
        flowNameTypeView.setId(id);
        return flowNameTypeView;
    }
}
