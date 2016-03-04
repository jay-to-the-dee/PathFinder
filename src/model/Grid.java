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
package model;

import java.util.*;

/**
 * Stores all the required data for the space represented in a grid shape
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Grid
{
    public final int width;
    public final int height;

    /*All the real data is stored in the following three fields
    The grid itself is generated dynamically when requested*/
    GridStartPoint startingPoint;
    GridTarget target;
    Set<GridObstacle> obstacles;

    public Grid(int width, int height)
    {
        this.width = width;
        this.height = height;

        clearObstacles(); //Initialises the HashSet for obstacles field
    }

    public void setStartintPoint(int x, int y)
    {
        if (!isOutOfBounds(x, y))
        {
            this.startingPoint = new GridStartPoint(x, y);
        }
        else
        {
            throw new OutOfBoundsException(x, y);
        }
    }

    public void setTarget(int x, int y)
    {
        if (!isOutOfBounds(x, y))
        {
            this.target = new GridTarget(x, y);
        }
        else
        {
            throw new OutOfBoundsException(x, y);
        }
    }

    public void addObstacle(int x, int y)
    {
        if (!isOutOfBounds(x, y))
        {
            this.obstacles.add(new GridObstacle(x, y));
        }
        else
        {
            throw new OutOfBoundsException(x, y);
        }
    }

    public final void clearObstacles()
    {
        obstacles = new HashSet<>();
    }

    public GridStartPoint getStartingPoint()
    {
        return startingPoint;
    }

    public GridTarget getTarget()
    {
        return target;
    }

    public Set<GridObstacle> getObstacles()
    {
        return obstacles;
    }

    public boolean isAnObstacle(int sX, int sY)
    {
        if (sX == startingPoint.getX() && sY == startingPoint.getY())
        {
            return true;
        }
        for (GridObstacle obstacle : obstacles)
        {
            if (obstacle.getX() == sX && obstacle.getY() == sY)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds(int sX, int sY)
    {
        return (sX <= 0 || sX > width || sY <= 0 || sY > height);
    }

    public GridObject[][] convertToXYGridObjectArray()
    {
        //We want to index from 1,1 not 0,0 so create an oversized array and ignore the 0th row and 0th column
        GridObject[][] objectGrid = new GridObject[width + 1][height + 1];

        if (startingPoint != null)
        {
            objectGrid[startingPoint.getX()][startingPoint.getY()] = startingPoint;
        }
        if (target != null)
        {
            objectGrid[target.getX()][target.getY()] = target;
        }
        for (GridObstacle obstacle : obstacles)
        {
            objectGrid[obstacle.getX()][obstacle.getY()] = obstacle;
        }

        // Fill the remainder of the grid in with (temporary) GridSpace objects
        for (int y = height; y >= 1; y--)
        {
            for (int x = 1; x <= width; x++)
            {
                if (objectGrid[x][y] == null)
                {
                    objectGrid[x][y] = new GridSpace(x, y);
                }
            }
        }
        return objectGrid;
    }

    @Override
    public String toString()
    {
        GridObject[][] objectGrid = convertToXYGridObjectArray();
        String buffer = "";

        for (int y = height; y >= 1; y--)
        {
            for (int x = 1; x <= width; x++)
            {
                GridObject object = objectGrid[x][y];
                String objectString = object.toInt() + "";

                if (objectString.length() == 1)
                {
                    objectString = " " + objectString;
                }

                buffer += objectString + " ";
            }
            buffer += "\n";
        }

        return buffer;
    }

    private static class OutOfBoundsException extends RuntimeException
    {
        public OutOfBoundsException(int x, int y)
        {
            System.err.println("Co-ordinates (" + x + "," + y + ") are outside the grid boundary!");
        }
    }
}
