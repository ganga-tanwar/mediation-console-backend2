package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class ReceiverSyetemsViewDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceiverSyetemsViewDTO.class);
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO1 = new ReceiverSyetemsViewDTO();
        receiverSyetemsViewDTO1.setId(1L);
        ReceiverSyetemsViewDTO receiverSyetemsViewDTO2 = new ReceiverSyetemsViewDTO();
        assertThat(receiverSyetemsViewDTO1).isNotEqualTo(receiverSyetemsViewDTO2);
        receiverSyetemsViewDTO2.setId(receiverSyetemsViewDTO1.getId());
        assertThat(receiverSyetemsViewDTO1).isEqualTo(receiverSyetemsViewDTO2);
        receiverSyetemsViewDTO2.setId(2L);
        assertThat(receiverSyetemsViewDTO1).isNotEqualTo(receiverSyetemsViewDTO2);
        receiverSyetemsViewDTO1.setId(null);
        assertThat(receiverSyetemsViewDTO1).isNotEqualTo(receiverSyetemsViewDTO2);
    }
}
