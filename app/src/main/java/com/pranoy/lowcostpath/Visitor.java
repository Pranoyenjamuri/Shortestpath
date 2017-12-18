package com.pranoy.lowcostpath;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Pranoy on 12/16/2017.
 */

public class Visitor {
    private int row;
    private GridCreator grid;
    private StateCollector pathCollector;

    public Visitor(int startRow, GridCreator grid, StateCollector collector) {
        if (grid == null) {
            throw new IllegalArgumentException("A visitor requires a grid");
        } else if (collector == null) {
            throw new IllegalArgumentException("A visitor requires a collector");
        } else if (startRow > 0 && startRow <= grid.getRowCount()) {
            this.row = startRow;
            this.grid = grid;
            this.pathCollector = collector;
        } else {
            throw new IllegalArgumentException("Cannot visit a row outside of grid boundaries");
        }
    }

    public PathState getBestPathForRow() {
        PathState initialPath = new PathState(this.grid.getColumnCount());
        this.visitPathsForRow(this.row, initialPath);
        return this.pathCollector.getBestPath();
    }

    private void visitPathsForRow(int row, PathState path) {
        if (this.canVisitRowOnPath(row, path)) {
            this.visitRowOnPath(row, path);
        }

        List<Integer> adjacentRows = this.grid.getRowsAdjacentTo(row);
        boolean currentPathAdded = false;
        Iterator var5 = adjacentRows.iterator();

        while (var5.hasNext()) {
            int adjacentRow = ((Integer) var5.next()).intValue();
            if (this.canVisitRowOnPath(adjacentRow, path)) {
                PathState pathCopy = new PathState(path);
                this.visitPathsForRow(adjacentRow, pathCopy);
            } else if (!currentPathAdded) {
                this.pathCollector.addPath(path);
                currentPathAdded = true;
            }
        }

    }

    private boolean canVisitRowOnPath(int row, PathState path) {
        return !path.isComplete() && !this.nextVisitOnPathWouldExceedMaximumCost(row, path);
    }

    private void visitRowOnPath(int row, PathState path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, this.grid.getValueForRowAndColumn(row, columnToVisit));
    }

    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, PathState path) {
        int nextColumn = path.getPathLength() + 1;
        return path.getTotalCost() + this.grid.getValueForRowAndColumn(row, nextColumn) > PathState.MAXIMUM_COST;
    }
}
