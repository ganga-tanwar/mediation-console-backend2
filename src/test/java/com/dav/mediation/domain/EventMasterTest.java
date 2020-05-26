package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class EventMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventMaster.class);
        EventMaster eventMaster1 = new EventMaster();
        eventMaster1.setId(1L);
        EventMaster eventMaster2 = new EventMaster();
        eventMaster2.setId(eventMaster1.getId());
        assertThat(eventMaster1).isEqualTo(eventMaster2);
        eventMaster2.setId(2L);
        assertThat(eventMaster1).isNotEqualTo(eventMaster2);
        eventMaster1.setId(null);
        assertThat(eventMaster1).isNotEqualTo(eventMaster2);
    }
}
