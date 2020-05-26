package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class EventStatusMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventStatusMaster.class);
        EventStatusMaster eventStatusMaster1 = new EventStatusMaster();
        eventStatusMaster1.setId(1L);
        EventStatusMaster eventStatusMaster2 = new EventStatusMaster();
        eventStatusMaster2.setId(eventStatusMaster1.getId());
        assertThat(eventStatusMaster1).isEqualTo(eventStatusMaster2);
        eventStatusMaster2.setId(2L);
        assertThat(eventStatusMaster1).isNotEqualTo(eventStatusMaster2);
        eventStatusMaster1.setId(null);
        assertThat(eventStatusMaster1).isNotEqualTo(eventStatusMaster2);
    }
}
