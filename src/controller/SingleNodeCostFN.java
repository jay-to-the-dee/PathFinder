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
 * This class stores and returns the costing for a node exploration
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class SingleNodeCostFN
{
    private final int xPosition;
    private final int yPosition;
    private double G;
    private double H;

    public SingleNodeCostFN(int xPosition, int yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public SingleNodeCostFN(int xPosition, int yPosition, double G, double H)
    {
        this(xPosition, yPosition);
        this.G = G;
        this.H = H;
    }

    public int getXPosition()
    {
        return xPosition;
    }

    public int getYPosition()
    {
        return yPosition;
    }

    public void setG(double G)
    {
        this.G = G;
    }

    public void setH(double H)
    {
        this.H = H;
    }

    public double getG()
    {
        return G;
    }

    public double getH()
    {
        return H;
    }

    /**
     * Get the total cost function for this node by adding g(n)+h(n)
     *
     * @return f(N)
     */
    public double getF()
    {
        return G + H;
    }
}
