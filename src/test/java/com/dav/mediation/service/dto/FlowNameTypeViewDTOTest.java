package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowNameTypeViewDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowNameTypeViewDTO.class);
        FlowNameTypeViewDTO flowNameTypeViewDTO1 = new FlowNameTypeViewDTO();
        flowNameTypeViewDTO1.setId(1L);
        FlowNameTypeViewDTO flowNameTypeViewDTO2 = new FlowNameTypeViewDTO();
        assertThat(flowNameTypeViewDTO1).isNotEqualTo(flowNameTypeViewDTO2);
        flowNameTypeViewDTO2.setId(flowNameTypeViewDTO1.getId());
        assertThat(flowNameTypeViewDTO1).isEqualTo(flowNameTypeViewDTO2);
        flowNameTypeViewDTO2.setId(2L);
        assertThat(flowNameTypeViewDTO1).isNotEqualTo(flowNameTypeViewDTO2);
        flowNameTypeViewDTO1.setId(null);
        assertThat(flowNameTypeViewDTO1).isNotEqualTo(flowNameTypeViewDTO2);
    }
}
