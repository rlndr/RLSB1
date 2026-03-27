package tech.lander.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rory on 2/28/17.
 */
public class QgroupTest {

    @Test
    public void Qgroup() {
        Qgroup qg = new Qgroup();
        qg.setDesc("This is a Test.");
        qg.setMinMagnatude("5");
        assertEquals("This is a Test.", qg.getDesc(), "Description was not set");
        assertEquals("5", qg.getMinMagnatude(), "Min Magnatude was not set");
    }
}