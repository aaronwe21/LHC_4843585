import infrastructure.ControlCenter;
import infrastructure.LargeHadronCollider;
import infrastructure.energy.Battery;
import infrastructure.energy.USP;
import infrastructure.lhc.*;
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
        LargeHadronCollider lhc = new LargeHadronCollider();
        assertEquals(2, lhc.getUsps().length);
        assertNotNull(lhc.getRing());
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
        ProtonTrap protonTrap1 = new ProtonTrap(ProtonTrapID.A);
        ProtonTrap protonTrap2 = new ProtonTrap(ProtonTrapID.B);
        Detector detector = new Detector();
        Ring ring = new Ring();
        ring.setProtonTraps(protonTrap1, protonTrap2);
        ring.setDetector(detector);
        ControlCenter controlCenter = ControlCenter.instance;
        controlCenter.addSubscriber(ring);

        assertEquals(25, protonTrap1.getProtons().size());
        assertEquals(25, protonTrap2.getProtons().size());

        for (IProton proton: protonTrap1.getProtons()) {
            String structure = ring.structureToString(proton.getStructure());
            assertEquals(1000000, structure.length());
        }

        for (IProton proton: protonTrap2.getProtons()) {
            String structure = ring.structureToString(proton.getStructure());
            assertEquals(1000000, structure.length());
        }
    }

    @Test
    @Order(12)
    public void testExperiment() {
        ProtonTrap protonTrap1 = new ProtonTrap(ProtonTrapID.A);
        ProtonTrap protonTrap2 = new ProtonTrap(ProtonTrapID.B);
        Ring ring = new Ring();
        ring.setProtonTraps(protonTrap1, protonTrap2);
        ring.releaseProton();
        assertEquals(1, ring.getProton1().getID());
        assertEquals(2, ring.getProton2().getID());
    }

    @Test
    @Order(13)
    public void testCollideProtons() {
        ProtonTrap protonTrap1 = new ProtonTrap(ProtonTrapID.A);
        ProtonTrap protonTrap2 = new ProtonTrap(ProtonTrapID.B);
        Ring ring = new Ring();
        ring.setProtonTraps(protonTrap1, protonTrap2);
        int initialEnergy = 50000;
        ring.activate(initialEnergy);
        ring.activateMagneticField();
        ring.releaseProton();
        while(ring.getEnergy() < 300000) {
            ring.increaseEnergy(25000);
        }
        assertEquals(300000, ring.getEnergy());
        ring.collide();
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
