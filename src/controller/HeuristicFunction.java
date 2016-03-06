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
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class HeuristicFunction
{
    /**
     * Calculates euclidian distance between two points.
     * Also can be used as a heuristic measure
     *
     * @return distance between two points
     */
    public static double euclidianDistance(int x1, int y1, int x2, int y2)
    {
        double xDiffSqr = Math.pow((x1 - x2), 2);
        double yDiffSqr = Math.pow((y1 - y2), 2);
        return Math.sqrt(xDiffSqr + yDiffSqr);
    }

    /**
     * Our first heuristic distance function - uses euclidian distance
     *
     * @return h1(n)
     */
    public static double h1(int x1, int y1, int x2, int y2)
    {
        return euclidianDistance(x1, y1, x2, y2);
    }

    /**
     * Our second heuristic distance function - uses Manhattan distance
     *
     * @return h2(n)
     */
    public static double h2(int x1, int y1, int x2, int y2)
    {
        double xDiff = Math.abs(x1 - x2);
        double yDiff = Math.abs(y1 - y2);
        return xDiff + yDiff;
    }
}
