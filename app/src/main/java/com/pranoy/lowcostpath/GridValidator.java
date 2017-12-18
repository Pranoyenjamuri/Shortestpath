package com.pranoy.lowcostpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Pranoy on 12/16/2017.
 */

public class GridValidator {
    private GridCreator grid;
    private StateComparator pathComparator;

    public GridValidator(GridCreator grid) {
        if (grid == null) {
            throw new IllegalArgumentException("A visitor requires a grid");
        } else {
            this.grid = grid;
            this.pathComparator = new StateComparator();
        }
    }

    public PathState getBestPathForGrid() {
        List<PathState> allPaths = new ArrayList();

        for (int row = 1; row <= this.grid.getRowCount(); ++row) {
            Visitor visitor = new Visitor(row, this.grid, new StateCollector());
            allPaths.add(visitor.getBestPathForRow());
        }

        Collections.sort(allPaths, this.pathComparator);
        return (PathState) allPaths.get(0);
    }
}
