package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class SenderSystemsViewDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SenderSystemsViewDTO.class);
        SenderSystemsViewDTO senderSystemsViewDTO1 = new SenderSystemsViewDTO();
        senderSystemsViewDTO1.setId(1L);
        SenderSystemsViewDTO senderSystemsViewDTO2 = new SenderSystemsViewDTO();
        assertThat(senderSystemsViewDTO1).isNotEqualTo(senderSystemsViewDTO2);
        senderSystemsViewDTO2.setId(senderSystemsViewDTO1.getId());
        assertThat(senderSystemsViewDTO1).isEqualTo(senderSystemsViewDTO2);
        senderSystemsViewDTO2.setId(2L);
        assertThat(senderSystemsViewDTO1).isNotEqualTo(senderSystemsViewDTO2);
        senderSystemsViewDTO1.setId(null);
        assertThat(senderSystemsViewDTO1).isNotEqualTo(senderSystemsViewDTO2);
    }
}
