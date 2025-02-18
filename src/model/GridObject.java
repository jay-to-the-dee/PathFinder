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

import java.awt.Color;

/**
 * Abstract class that hold all object types to be stored within the grid
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public abstract class GridObject
{
    private final int xPosition;
    private final int yPosition;

    public GridObject(int xPosition, int yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    abstract public int toInt();
    abstract public Color getColor();

    public int getX()
    {
        return xPosition;
    }

    public int getY()
    {
        return yPosition;
    }
}
