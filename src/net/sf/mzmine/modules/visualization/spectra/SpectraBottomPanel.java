/*
 * Copyright 2006-2008 The MZmine Development Team
 * 
 * This file is part of MZmine.
 * 
 * MZmine is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package net.sf.mzmine.modules.visualization.spectra;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.mzmine.util.GUIUtils;

/**
 * Spectra visualizer's bottom panel
 */
class SpectraBottomPanel extends JPanel {

    private static final Font smallFont = new Font("SansSerif", Font.PLAIN, 10);

    private JComboBox msmsSelector, peakListSelector;

    SpectraBottomPanel(SpectraVisualizerWindow masterFrame) {

        setBackground(Color.white);

        JButton prevScanBtn = GUIUtils.addButton(this, "Previous scan", null,
                masterFrame, "PREVIOUS_SCAN");
        prevScanBtn.setBackground(Color.white);
        prevScanBtn.setFont(smallFont);

        add(Box.createHorizontalGlue());
        
        GUIUtils.addLabel(this, "MS/MS:", SwingConstants.RIGHT, smallFont);

        msmsSelector = new JComboBox();
        msmsSelector.setEnabled(false);
        msmsSelector.setBackground(Color.white);
        msmsSelector.setFont(smallFont);
        add(msmsSelector);

        JButton showButton = GUIUtils.addButton(this, "Show", null,
                masterFrame, "SHOW_MSMS");
        showButton.setBackground(Color.white);
        showButton.setFont(smallFont);

        GUIUtils.addLabel(this, "Peak list:", SwingConstants.RIGHT, smallFont);

        peakListSelector = new JComboBox();
        peakListSelector.setEnabled(false);
        peakListSelector.setBackground(Color.white);
        peakListSelector.setFont(smallFont);
        peakListSelector.addActionListener(masterFrame);
        peakListSelector.setActionCommand("PEAKLIST_CHANGE");
        add(peakListSelector);

        add(Box.createHorizontalGlue());
        
        JButton nextScanBtn = GUIUtils.addButton(this, "Next scan", null,
                masterFrame, "NEXT_SCAN");
        nextScanBtn.setBackground(Color.white);
        nextScanBtn.setFont(smallFont);

    }

    JComboBox getMSMSSelector() {
        return msmsSelector;
    }

    JComboBox getPeakListSelector() {
        return peakListSelector;
    }
}
