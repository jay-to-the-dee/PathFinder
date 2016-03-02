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
import javax.swing.JTable;
import javax.swing.table.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class QueueJTable
{
    private final Queue queue;
    private final JTable table;

    public QueueJTable(Queue queue)
    {
        table = new JTable(new QueueTableModel());
        this.queue = queue;
    }

    public JTable getTable()
    {
        return table;
    }

    private class QueueTableModel extends AbstractTableModel
    {
        private final String[] columnNames =
        {
            "Explored", "X", "Y", "Parent X", "Parent Y", "g(n)", "h(n)", "f(n)"
        };

        
        
        @Override
        public int getRowCount()
        {
            return queue.getQueue().size();
        }

        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col)
        {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            QueueNode node = queue.getQueue().get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    return node.isExplored();
                case 1:
                    return node.getNodeCost().getXPosition();
                case 2:
                    return node.getNodeCost().getYPosition();
                case 3:
                    return node.getParentX();
                case 4:
                    return node.getParentY();
                case 5:
                    return node.getNodeCost().getG();
                case 6:
                    return node.getNodeCost().getH();
                case 7:
                    return node.getNodeCost().getF();
                default:
                    return "";
            }
        }

        @Override
        public Class getColumnClass(int c)
        {
            switch (c)
            {
                case 0:
                    return Boolean.class;
                case 1:
                case 2:
                case 3:
                case 4:
                    return Integer.class;
                case 5:
                case 6:
                case 7:
                    return Double.class;
                default:
                    return Object.class;
            }
        }
    }

}
