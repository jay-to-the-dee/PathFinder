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

import controller.Queue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Grid;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class GUI extends JFrame
{
    private final Grid grid;
    private final controller.Queue queue;

    public GUI(Grid grid, Queue queue)
    {
        super("PathFinder GUI");
        this.grid = grid;
        this.queue = queue;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        addStuff(this.getContentPane());

        this.pack();
        this.setVisible(true);
    }

    private void addStuff(Container contentPane)
    {
        JTable queueJTable = new QueueJTable(queue).getTable();
        JScrollPane scrollPane = new JScrollPane(queueJTable);
        queueJTable.setFillsViewportHeight(true);

        JPanel gridJPanel = new GridJPanel(grid);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                gridJPanel, scrollPane);

        contentPane.add(splitPane, BorderLayout.CENTER);
        
        JPanel buttonControls = getButtons();
        contentPane.add(buttonControls, BorderLayout.SOUTH);
    }

    private JPanel getButtons()
    {
        JPanel newButtons = new JPanel();
        newButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton doSearchButton = new JButton("Start Search");
        doSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                queue.doSearch();
                repaint();
            }
        });
        newButtons.add(doSearchButton);

        return newButtons;
    }
}
