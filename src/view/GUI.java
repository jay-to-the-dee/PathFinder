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

    private JScrollPane scrollPane;
    private JTable queueJTable;
    private JPanel gridJPanel;
    private JPanel buttonControls;

    public GUI(Grid grid, Queue queue)
    {
        super("PathFinder GUI");
        this.grid = grid;
        this.queue = queue;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        addStuff(this.getContentPane());

        this.setPreferredSize(new Dimension(1280, 1040));
        this.pack();
        this.setVisible(true);
    }

    private void addStuff(Container contentPane)
    {
        queueJTable = new QueueJTable(queue).getTable();
        scrollPane = new JScrollPane(queueJTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        queueJTable.setFillsViewportHeight(true);

        gridJPanel = new GridJPanel(grid);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                gridJPanel, scrollPane);

        contentPane.add(splitPane, BorderLayout.CENTER);

        buttonControls = getButtons();
        contentPane.add(buttonControls, BorderLayout.SOUTH);
    }

    private JPanel getButtons()
    {
        JPanel newButtons = new JPanel();
        newButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton doSearchButton = new JButton("Start Search");
        doSearchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new SwingWorker<Void, Void>()
                {
                    @Override
                    protected Void doInBackground() throws Exception
                    {
                        queue.doSearch();
                        return null;
                    }

                    @Override
                    public void done()
                    {
                        repaint();
                        queueJTable.revalidate();
                    }
                }.execute();
            }
        });
        newButtons.add(doSearchButton);

        return newButtons;
    }
}
