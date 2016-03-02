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

import controller.*;
import model.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class PathFinder
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Grid grid = new Grid(10, 10);

        grid.setStartintPoint(4, 5);
        grid.setTarget(7, 5);
        
        grid.addObstacle(6, 3);
        grid.addObstacle(6, 4);
        grid.addObstacle(6, 5);
        grid.addObstacle(6, 6);
        grid.addObstacle(6, 7);


        

        System.out.print(grid.toString());

        //System.out.println(new Expander(grid, 5, 5, 0).doExpand());
        
        Queue queue = new Queue(grid);
        queue.doSearch();
        
        System.out.print(queue.toString());
    }

    
    
}
