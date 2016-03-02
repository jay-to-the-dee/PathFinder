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
 * This class stores and manages the search queue
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Queue
{
    Grid grid;
    private ArrayList<QueueNode> queue;
    private boolean pathExists;
    private double pathCost;

    //Not really part of the data structure - for convinience
    private int startX, startY, targetX, targetY;

    public Queue(Grid grid)
    {
        this.grid = grid;

        //Just for convinenince
        startX = grid.getStartingPoint().getX();
        startY = grid.getStartingPoint().getY();
        targetX = grid.getTarget().getX();
        targetY = grid.getTarget().getY();

        queue = new ArrayList<>();
        pathExists = true; //assume there exists a path

        SingleNodeCostFN initialNodeCost = new SingleNodeCostFN(startX, startY);
        initialNodeCost.setG(pathCost); //Will be 0 here
        initialNodeCost.setH(Expander.distance(startX, startY, targetX, targetY));
        QueueNode initialQueueNode = new QueueNode(startX, startY, initialNodeCost);
        initialQueueNode.markExplored();
        queue.add(initialQueueNode);
    }

    public ArrayList<QueueNode> getQueue()
    {
        return queue;
    }
    
    @Override
    /**
     * This will produce the current queue contents
     */
    public String toString()
    {
        String buffer = "";
        int i = 0;
        for (QueueNode queueNode : queue)
        {
            buffer += ++i + "\t" + queueNode + "\n";
        }
        return buffer;
    }

    public void doSearch()
    {
        int currentX = startX;
        int currentY = startY;
        boolean flag;

        while (!(currentX == targetX && currentY == targetY) && pathExists == true)
        {
            Set<SingleNodeCostFN> expandedNodes = new Expander(grid, currentX, currentY, pathCost).doExpand();
            for (SingleNodeCostFN exp : expandedNodes)
            {
                flag = false;
                for (QueueNode currentQueueNode : queue)
                {
                    if (exp.getXPosition() == currentQueueNode.getNodeCost().getXPosition()
                            && exp.getYPosition() == currentQueueNode.getNodeCost().getYPosition())
                    {
                        //This should be ensuring the minimum cost - survives in the queue
                        if (currentQueueNode.getNodeCost().getF() >= exp.getF())
                        {
                            currentQueueNode.update(currentX, currentY, exp.getG(), exp.getH());
                            flag = true;
                        }
                    }
                }
                if (flag == false)
                {
                    queue.add(new QueueNode(currentX, currentY, exp));
                }
            }
            QueueNode minimumNode = getMinimumQueueNode();
            if (minimumNode != null)
            {
                currentX = minimumNode.getNodeCost().getXPosition();
                currentY = minimumNode.getNodeCost().getYPosition();
                pathCost = minimumNode.getNodeCost().getG();

                //Add current node to obstacles
                //TODO: Make different type of machine generated obstacle object later
                grid.addObstacle(currentX, currentY);
                minimumNode.markExplored(); //Mark explored
            }
            else
            {
                pathExists = false;
            }
        }
    }

    private QueueNode getMinimumQueueNode()
    {
        QueueNode runningSmallestNode = null;

        for (QueueNode currentQueueNode : queue)
        {
            if (!currentQueueNode.isExplored())
            {
                if (runningSmallestNode == null || currentQueueNode.getNodeCost().getF() < runningSmallestNode.getNodeCost().getF())
                {
                    runningSmallestNode = currentQueueNode;
                }
            }
        }

        return runningSmallestNode;
    }
}
