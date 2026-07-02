package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(TestListener.class)
public class AIDemoFailureTest extends BaseTest {

    @Test
    public void aiFailureDemo() {

        throw new RuntimeException("Artificial failure for AI analysis.");

    }

}