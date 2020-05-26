package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class EventStatusMasterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventStatusMasterDTO.class);
        EventStatusMasterDTO eventStatusMasterDTO1 = new EventStatusMasterDTO();
        eventStatusMasterDTO1.setId(1L);
        EventStatusMasterDTO eventStatusMasterDTO2 = new EventStatusMasterDTO();
        assertThat(eventStatusMasterDTO1).isNotEqualTo(eventStatusMasterDTO2);
        eventStatusMasterDTO2.setId(eventStatusMasterDTO1.getId());
        assertThat(eventStatusMasterDTO1).isEqualTo(eventStatusMasterDTO2);
        eventStatusMasterDTO2.setId(2L);
        assertThat(eventStatusMasterDTO1).isNotEqualTo(eventStatusMasterDTO2);
        eventStatusMasterDTO1.setId(null);
        assertThat(eventStatusMasterDTO1).isNotEqualTo(eventStatusMasterDTO2);
    }
}
