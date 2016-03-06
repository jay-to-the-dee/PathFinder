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
        initColumnSizes();
        this.queue = queue;
    }

    public JTable getTable()
    {
        return table;
    }

    private void initColumnSizes()
    {
        final int BASEWIDTH = 50;

        for (int i = 0; i < table.getColumnCount(); i++)
        {
            TableColumn column = table.getColumnModel().getColumn(i);
            if (i == 0)
            {
                column.setPreferredWidth(BASEWIDTH);
            }
            else if (i < 6)
            {
                column.setPreferredWidth(BASEWIDTH * 2);
            }
            else
            {
                column.setPreferredWidth(BASEWIDTH * 4);
            }
        }
    }

    private class QueueTableModel extends AbstractTableModel
    {
        private final String[] columnNames =
        {
            "#", "Explored", "X", "Y", "Parent X", "Parent Y", "g(n)", "h(n)", "f(n)"
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
                    return rowIndex + 1;
                case 1:
                    return node.isExplored();
                case 2:
                    return node.getNodeCost().getXPosition();
                case 3:
                    return node.getNodeCost().getYPosition();
                case 4:
                    return node.getParentX();
                case 5:
                    return node.getParentY();
                case 6:
                    return node.getNodeCost().getG();
                case 7:
                    return node.getNodeCost().getH();
                case 8:
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
                    return Integer.class;
                case 1:
                    return Boolean.class;
                case 2:
                case 3:
                case 4:
                case 5:
                    return Integer.class;
                case 6:
                case 7:
                case 8:
                    return Double.class;
                default:
                    return Object.class;
            }
        }
    }

}
