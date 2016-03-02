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
package view;

import java.awt.*;
import javax.swing.*;
import model.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class GridJPanel extends JPanel
{
    private final Grid grid;

    public GridJPanel(Grid grid)
    {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.grid = grid;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int size = Math.min(getWidth(), getHeight());
        int objectWidth = size / grid.width;
        int objectHeight = size / grid.height;

        GridObject[][] gridArray = grid.convertToXYGridObjectArray();

        for (int y = grid.height; y >= 1; y--)
        {
            for (int x = 1; x <= grid.width; x++)
            {
                g.setColor(gridArray[x][y].getColor());
                g.fillRect((x - 1) * objectWidth, (grid.width-y) * objectHeight, objectWidth, objectHeight);

                g.setColor(Color.black);
                g.drawRect((x - 1) * objectWidth, (grid.width-y) * objectHeight, objectWidth, objectHeight);
            }
        }
    }
}
