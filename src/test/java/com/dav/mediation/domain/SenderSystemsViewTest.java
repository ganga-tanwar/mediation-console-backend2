package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class SenderSystemsViewTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SenderSystemsView.class);
        SenderSystemsView senderSystemsView1 = new SenderSystemsView();
        senderSystemsView1.setId(1L);
        SenderSystemsView senderSystemsView2 = new SenderSystemsView();
        senderSystemsView2.setId(senderSystemsView1.getId());
        assertThat(senderSystemsView1).isEqualTo(senderSystemsView2);
        senderSystemsView2.setId(2L);
        assertThat(senderSystemsView1).isNotEqualTo(senderSystemsView2);
        senderSystemsView1.setId(null);
        assertThat(senderSystemsView1).isNotEqualTo(senderSystemsView2);
    }
}
