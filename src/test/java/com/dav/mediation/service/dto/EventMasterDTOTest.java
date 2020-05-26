package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class EventMasterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventMasterDTO.class);
        EventMasterDTO eventMasterDTO1 = new EventMasterDTO();
        eventMasterDTO1.setId(1L);
        EventMasterDTO eventMasterDTO2 = new EventMasterDTO();
        assertThat(eventMasterDTO1).isNotEqualTo(eventMasterDTO2);
        eventMasterDTO2.setId(eventMasterDTO1.getId());
        assertThat(eventMasterDTO1).isEqualTo(eventMasterDTO2);
        eventMasterDTO2.setId(2L);
        assertThat(eventMasterDTO1).isNotEqualTo(eventMasterDTO2);
        eventMasterDTO1.setId(null);
        assertThat(eventMasterDTO1).isNotEqualTo(eventMasterDTO2);
    }
}
