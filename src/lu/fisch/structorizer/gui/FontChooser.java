/*
    Structorizer
    A little tool which you can use to create Nassi-Schneiderman Diagrams (NSD)

    Copyright (C) 2009  Bob Fisch

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or any
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package lu.fisch.structorizer.gui;

/******************************************************************************************************
 *
 *      Author:         Bob Fisch
 *
 *      Description:    This is the font chooser dialog.
 *
 ******************************************************************************************************
 *
 *      Revision List
 *
 *      Author          Date			Description
 *      ------			----			-----------
 *      Bob Fisch       2007.12.31      First Issue
 *      Kay Gürtzig     2016.10.11      Minimum font size 2 dropped from the sizes array.
 *      Kay Gürtzig     2016.11.02      Issue #81: Scaling Factor considered (DPI awareness workarond)
 *      Kay Güertig     2016.11.09      Issue #81: Scaling factor no longer rounded, ensured to be >= 1
 *
 ******************************************************************************************************
 *
 *      Comment:		While setting up this class, I had a deep look at the following package:
 *
 * 		*********************************************************	
 *		* Package: ZoeloeSoft.projects.JFontChooser		
 *		* Id: JFontChooser.java		 	
 * 		* Date: 23:39 19/04/2004	
 *	 	* Creator: Tim Eeckhaut				
 *		* Alias: zoeloeboeloe					
 * 		* Company: ZoeloeSoft				
 *	 	* Website: http://users.pandora.be/ZoeloeSoft	
 *		*********************************************************		
 *
 ******************************************************************************************************///


import lu.fisch.structorizer.io.Ini;
import lu.fisch.structorizer.locales.LangDialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/*
 * Created by JFormDesigner on Mon Dec 31 12:44:23 CET 2007
 */



/**
 * @author Robert Fisch
 */
@SuppressWarnings("serial")
public class FontChooser extends LangDialog
{
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Robert Fisch
	protected JPanel pnlChooser;
	protected JPanel contentPanel;
	protected JLabel lblTest;
	protected JPanel pnlName;
	protected JLabel lblName;
	protected JScrollPane scrollPane1;
	protected JList<String> lsNames;
	protected JPanel pnlSize;
	protected JLabel lblSize;
	protected JScrollPane scrollPane2;
	protected JList<String> lsSizes;
	protected JPanel buttonBar;
	protected JButton btnOK;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	private String[] sizes = new String[] { "4","6","8","10","12","14","16","18","20","22","24","30","36","48","72" };

        /*
	public FontChooser() {
		super();
		setModal(true);
		initComponents();
	}*/
	
	public FontChooser(Frame owner) {
		super(owner);
                setModal(true);
		initComponents();
	}
	
	/*public FontChooser(Dialog owner) {
		super(owner);
		initComponents();
	}*/

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Robert Fisch
		pnlChooser = new JPanel();
		contentPanel = new JPanel();
		lblTest = new JLabel();
		pnlName = new JPanel();
		lblName = new JLabel();
		scrollPane1 = new JScrollPane();
		lsNames = new JList<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
		pnlSize = new JPanel();
		lblSize = new JLabel();
		scrollPane2 = new JScrollPane();
		lsSizes = new JList<String>(sizes);
		buttonBar = new JPanel();
		btnOK = new JButton();
		
		// START KGU#287 2016-11-02: Issue #81 (DPI awareness workaround)
		double scaleFactor = Double.valueOf(Ini.getInstance().getProperty("scaleFactor","1"));
		if (scaleFactor < 1) scaleFactor = 1.0;
		// END KGU#287 2016-11-02

		//======== fontChooser ========
		{
			this.setResizable(false);
			this.setTitle("Font");
			Container fontChooserContentPane = getContentPane();
			fontChooserContentPane.setLayout(new BorderLayout());

			//======== FontChooser ========
			{
				
				// START KGU#287 2016-11-02: Issue #81 (DPI awareness workaround)
				//pnlChooser.setBorder(new EmptyBorder(12, 12, 12, 12));
				int border = (int)(12 * scaleFactor);
				pnlChooser.setBorder(new EmptyBorder(border, border, border, border));
				// END KGU#287 2016-11-02

				// JFormDesigner evaluation mark
				/*FontChooser.setBorder(new javax.swing.border.CompoundBorder(
					new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
						"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
						javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
						java.awt.Color.red), FontChooser.getBorder())); FontChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});
				 */
				pnlChooser.setLayout(new BorderLayout());

				//======== contentPanel ========
				{
					// START KGU#287 2016-11-02: Issue #81 (DPI awareness workaround)
					//contentPanel.setLayout(new BorderLayout(8, 8));
					border = (int)(8 * scaleFactor);
					contentPanel.setLayout(new BorderLayout(border, border));
					// END KGU#287 2016-11-02

					//---- lblTest ----
					lblTest.setText("Test: Structorizer (Symboltest: [\u2190 - \u2205])");
					contentPanel.add(lblTest, BorderLayout.SOUTH);

					//======== pnlName ========
					{
						pnlName.setPreferredSize(new Dimension((int)(250*scaleFactor), (int)(150*scaleFactor)));
						pnlName.setLayout(new BorderLayout(border, border));

						//---- lblName ----
						lblName.setText("Name");
						pnlName.add(lblName, BorderLayout.NORTH);

						//======== scrollPane1 ========
						{
							scrollPane1.setViewportView(lsNames);
						}
						pnlName.add(scrollPane1, BorderLayout.CENTER);
					}
					contentPanel.add(pnlName, BorderLayout.WEST);

					//======== pnlSize ========
					{
						pnlSize.setPreferredSize(new Dimension((int)(100*scaleFactor), (int)(150*scaleFactor)));
						pnlSize.setLayout(new BorderLayout(border, border));

						//---- lblSize ----
						lblSize.setText("Size");
						pnlSize.add(lblSize, BorderLayout.NORTH);

						//======== scrollPane2 ========
						{
							scrollPane2.setViewportView(lsSizes);
						}
						pnlSize.add(scrollPane2, BorderLayout.CENTER);
					}
					contentPanel.add(pnlSize, BorderLayout.CENTER);
				}
				pnlChooser.add(contentPanel, BorderLayout.CENTER);

				//======== buttonBar ========
				{
					// START KGU#287 2016-11-02: Issue #81 (DPI awareness workaround)
					//buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
					buttonBar.setBorder(new EmptyBorder((int)(12*scaleFactor), 0, 0, 0));
					// END KGU#287 2016-11-02
					buttonBar.setLayout(new GridBagLayout());
					((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, (int)(80*scaleFactor)};
					((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

					//---- btnOK ----
					btnOK.setText("OK");
					buttonBar.add(btnOK, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));
			        // START KGU#287 2017-01-09: Issues #81/#330 GUI scaling
			        GUIScaler.rescaleComponents(buttonBar);
			        // END KGU#287 2017-01-09
				}
				pnlChooser.add(buttonBar, BorderLayout.SOUTH);
			}
			fontChooserContentPane.add(pnlChooser, BorderLayout.CENTER);
			
			this.pack();
			this.setLocationRelativeTo(this.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		// BOB thinks
		
		// add the LIST-listeners
		ListSelectionListener listListener = new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{ 
				lblTest.setFont(getCurrentFont()); 
			}
		};
		lsSizes.addListSelectionListener(listListener);
		lsNames.addListSelectionListener(listListener);
		
		// add the KEY-listeners
		KeyListener keyListener = new KeyListener()
		{
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					setVisible(false);
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER && (e.isShiftDown() || e.isControlDown()))
				{
					setVisible(false);
				}
			}
			
			public void keyReleased(KeyEvent ke) {} 
			public void keyTyped(KeyEvent kevt) {}
		};
		lsSizes.addKeyListener(keyListener);
		lsNames.addKeyListener(keyListener);
		btnOK.addKeyListener(keyListener);
		
		// add the ACTION-listeners
		ActionListener actionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		};
		btnOK.addActionListener(actionListener);
	}
	
	public Font getCurrentFont()
	{	
		Font font = new Font("Helvetica", Font.PLAIN, 12);

		
		try
		{
			String fontFamily = (String)lsNames.getSelectedValue();
			int fontSize = Integer.parseInt((String)lsSizes.getSelectedValue());
			
			int fontType = Font.PLAIN;
			
			//if (cbBold.isSelected()) fontType += Font.BOLD;
			//if (cbItalic.isSelected()) fontType += Font.ITALIC;
			
			font= new Font(fontFamily, fontType, fontSize);		
		}
		catch (Exception e) 
		{
		}
	
		return font;
	}
	
	public void setFont(Font font)
	{
		if (font == null) font = lblTest.getFont();
		
		lsNames.setSelectedValue(font.getName(), true);
		lsNames.ensureIndexIsVisible(lsNames.getSelectedIndex()); 
		lsSizes.setSelectedValue("" + font.getSize(), true);
		lsSizes.ensureIndexIsVisible(lsSizes.getSelectedIndex());
		
		//cbBold.setSelected(font.isBold());
		//cbItalic.setSelected(font.isItalic());
	}
	

}
