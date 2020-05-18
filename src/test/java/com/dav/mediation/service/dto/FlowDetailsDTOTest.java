package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowDetailsDTO.class);
        FlowDetailsDTO flowDetailsDTO1 = new FlowDetailsDTO();
        flowDetailsDTO1.setId(1L);
        FlowDetailsDTO flowDetailsDTO2 = new FlowDetailsDTO();
        assertThat(flowDetailsDTO1).isNotEqualTo(flowDetailsDTO2);
        flowDetailsDTO2.setId(flowDetailsDTO1.getId());
        assertThat(flowDetailsDTO1).isEqualTo(flowDetailsDTO2);
        flowDetailsDTO2.setId(2L);
        assertThat(flowDetailsDTO1).isNotEqualTo(flowDetailsDTO2);
        flowDetailsDTO1.setId(null);
        assertThat(flowDetailsDTO1).isNotEqualTo(flowDetailsDTO2);
    }
}
