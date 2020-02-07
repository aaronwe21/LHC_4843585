package infrastructure;

import infrastructure.energy.USP;
import infrastructure.lhc.IRing;
import infrastructure.lhc.Ring;

public class LargeHadronCollider {
    private Building building;
    private IRing ring;
    private USP[] usps;

    public LargeHadronCollider() {
        this.usps = new USP[2];
        this.ring = new Ring();
    }

    public IRing getRing() {
        return ring;
    }

    public USP[] getUsps() {
        return usps;
    }
}
