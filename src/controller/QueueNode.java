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

/**
 * This is a node to go on the Queue object in the Queue class
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class QueueNode
{
    private boolean explored;
    private int parentX;
    private int parentY;
    private final SingleNodeCostFN nodeCost;

    public QueueNode(int parentX, int parentY, SingleNodeCostFN nodeCost)
    {
        explored = false;
        this.parentX = parentX;
        this.parentY = parentY;
        this.nodeCost = nodeCost;
    }

    public void update(int parentX, int parentY, double G, double H)
    {
        this.parentX = parentX;
        this.parentY = parentY;
        nodeCost.setG(G);
        nodeCost.setH(H);
    }

    public void markExplored()
    {
        explored = true;
    }

    public boolean isExplored()
    {
        return explored;
    }

    public int getParentX()
    {
        return parentX;
    }

    public int getParentY()
    {
        return parentY;
    }

    public SingleNodeCostFN getNodeCost()
    {
        return nodeCost;
    }

    @Override
    public String toString()
    {
        return "QueueNode\t"
                + "explored=" + explored
                + ", X=" + nodeCost.getXPosition()
                + ", Y=" + nodeCost.getYPosition()
                + ", parentX=" + parentX
                + ", parentY=" + parentY
                + ", g(n)=" + nodeCost.getG()
                + ", h(n)=" + nodeCost.getH()
                + ", f(n)=" + nodeCost.getF()
                + ' ';
    }

}
