package com.pranoy.lowcostpath;


/**
 * Created by Pranoy on 02/01/2018.
 */

public class StateCollector {
    private PathState bestPath;
    private StateComparator comparator = new StateComparator();

    public StateCollector() {
    }

    public PathState getBestPath() {
        return this.bestPath;
    }

    public void addPath(PathState newPath) {
        if(this.comparator.compare(newPath, this.bestPath) < 0) {
            this.bestPath = newPath;
        }

    }
}
