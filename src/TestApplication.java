import infrastructure.ControlCenter;
import infrastructure.energy.Battery;
import infrastructure.energy.USP;
import infrastructure.lhc.ProtonTrap;
import infrastructure.lhc.ProtonTrapID;
import infrastructure.lhc.Ring;
import infrastructure.lhc.detector.Detector;
import infrastructure.lhc.experiment.Experiment;
import infrastructure.lhc.experiment.IExperiment;
import infrastructure.lhc.experiment.RunExperimentFullEvent;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Matrikelnummer:

public class TestApplication {

    @BeforeEach

    @Test
    @Order(1)
    public void testAssignLHC() {

    }

    @Test
    @Order(2)
    public void testBatteries() {
        USP test = new USP();
        assertEquals(25, test.getBatteries().length);
    }

    @Test
    @Order(3)
    public void testIDCardVisitor() {

    }

    @Test
    @Order(4)
    public void testIDCardEmployee() {

    }

    @Test
    @Order(5)
    public void testLockIDCard() {

    }

    @Test
    @Order(6)
    public void testLockedIDCard() {

    }

    @Test
    @Order(7)
    public void testExpiredIDCard() {

    }

    @Test
    @Order(8)
    public void testControlCenter() {

    }

    @Test
    @Order(9)
    public void testSearchAlgorithm() {

    }

    @Test
    @Order(10)
    public void testRingInitialize() {

    }

    @Test
    @Order(11)
    public void testProtons() {

    }

    @Test
    @Order(12)
    public void testExperiment() {

    }

    @Test
    @Order(13)
    public void testCollideProtons() {
        ProtonTrap protonTrap1 = new ProtonTrap(ProtonTrapID.A);
        ProtonTrap protonTrap2 = new ProtonTrap(ProtonTrapID.B);
        Detector detector = new Detector();
        Ring ring = new Ring();
        ring.setProtonTraps(protonTrap1, protonTrap2);
        ring.setDetector(detector);
        ControlCenter controlCenter = ControlCenter.instance;
        controlCenter.addSubscriber(ring);
        controlCenter.startExperiment();
    }

    @Test
    @Order(14)
    public void testExperimentSize() {
        ProtonTrap protonTrap1 = new ProtonTrap(ProtonTrapID.A);
        ProtonTrap protonTrap2 = new ProtonTrap(ProtonTrapID.B);
        Detector detector = new Detector();
        Ring ring = new Ring();
        ring.setProtonTraps(protonTrap1, protonTrap2);
        ring.setDetector(detector);
        ControlCenter controlCenter = ControlCenter.instance;
        controlCenter.addSubscriber(ring);
        controlCenter.startExperiment();
        List<IExperiment> experimentList = detector.getExperimentList();

        for(IExperiment experiment: experimentList) {
            int counter = 0;
            for (int i = 0; i < ((Experiment)experiment).getBlocks().length; i ++) {
                if (((Experiment)experiment).getBlock(i) != null)
                    counter++;
            }
            //check if every Experiment has 200.000 real blocks
            assertEquals(200000, counter);
            //check if every Block has a String with the length 10
            for (int i = 0; i < 200000; i++) {
                assertEquals(10, experiment.getBlock(i).getStructure().length());
            }
        }
    }

    @Test
    @Order(15)
    public void testDetector() {

    }
}
