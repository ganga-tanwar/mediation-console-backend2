package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class ReceiverSyetemsViewTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceiverSyetemsView.class);
        ReceiverSyetemsView receiverSyetemsView1 = new ReceiverSyetemsView();
        receiverSyetemsView1.setId(1L);
        ReceiverSyetemsView receiverSyetemsView2 = new ReceiverSyetemsView();
        receiverSyetemsView2.setId(receiverSyetemsView1.getId());
        assertThat(receiverSyetemsView1).isEqualTo(receiverSyetemsView2);
        receiverSyetemsView2.setId(2L);
        assertThat(receiverSyetemsView1).isNotEqualTo(receiverSyetemsView2);
        receiverSyetemsView1.setId(null);
        assertThat(receiverSyetemsView1).isNotEqualTo(receiverSyetemsView2);
    }
}
