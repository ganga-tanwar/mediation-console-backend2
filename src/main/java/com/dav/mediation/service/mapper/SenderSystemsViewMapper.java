package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.SenderSystemsViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SenderSystemsView} and its DTO {@link SenderSystemsViewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SenderSystemsViewMapper extends EntityMapper<SenderSystemsViewDTO, SenderSystemsView> {



    default SenderSystemsView fromId(Long id) {
        if (id == null) {
            return null;
        }
        SenderSystemsView senderSystemsView = new SenderSystemsView();
        senderSystemsView.setId(id);
        return senderSystemsView;
    }
}
