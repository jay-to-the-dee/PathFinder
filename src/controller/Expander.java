/*
 * Copyright (C) 2016 jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller;

import java.util.*;
import model.Grid;

/**
 * This class expands a node, by exploring the surrounding nodes and finding
 * the cost involved.
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Expander
{
    Grid grid;
    private final int xNode;
    private final int yNode;
    private final double pathCostHN;

    public Expander(Grid grid, int xNode, int yNode, double pathCostHN)
    {
        this.grid = grid;
        this.xNode = xNode;
        this.yNode = yNode;
        this.pathCostHN = pathCostHN;
    }

    public Set<SingleNodeCostFN> doExpand()
    {
        Set<SingleNodeCostFN> nodeCosts = new LinkedHashSet<>();

        for (int k = 1; k >= -1; k--)
        {
            for (int j = 1; j >= -1; j--)
            {
                if (!(k == 0 && j == 0))
                {
                    SingleNodeCostFN node = doNode(xNode + k, yNode + j);
                    if (node != null)
                    {
                        nodeCosts.add(node);
                    }
                }
            }
        }

        return nodeCosts;
    }

    private SingleNodeCostFN doNode(int sX, int sY)
    {
        // Check if node is within grid bounds
        if (grid.isOutOfBounds(sX, sY))
        {
            return null;
        }

        // Check if node is an obstacle
        if (grid.isAnObstacle(sX, sY))
        {
            return null;
        }

        SingleNodeCostFN node = new SingleNodeCostFN(sX, sY);

        node.setG(pathCostHN + distance(xNode, yNode, sX, sY));
        node.setH(distance(grid.getTarget().getX(), grid.getTarget().getY(), sX, sY));

        return node;
    }

    /**
     * Our heuristic distance function
     * @return h1(n)
     */
    public static double distance(int x1, int y1, int x2, int y2)
    {
        double xDiffSqr = Math.pow((x1 - x2), 2);
        double yDiffSqr = Math.pow((y1 - y2), 2);
        return Math.sqrt(xDiffSqr + yDiffSqr);
    }
}
