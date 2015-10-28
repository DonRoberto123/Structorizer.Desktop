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
 *      Description:    This the dialog that allows editing the properties of any element.
 *
 ******************************************************************************************************
 *
 *      Revision List
 *
 *      Author          Date			Description
 *      ------			----			-----------
 *      Bob Fisch       2007.12.23      First Issue
 *      Kay Gürtzig     2015-10-12      A checkbox added for breakpoint control 
 *
 ******************************************************************************************************
 *
 *      Comment:		/
 *
 ******************************************************************************************************///


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import lu.fisch.utils.StringList;


public class InputBox extends LangDialog implements ActionListener, KeyListener
{
    public boolean OK = false;
    
    // Buttons
    protected JButton btnOK = new JButton("OK"); 
    protected JButton btnCancel = new JButton("Cancel"); 
	
    // Labels
    protected JLabel lblText = new JLabel("Please enter a text");
    protected JLabel lblComment = new JLabel("Comment");

    // Textarea
    protected JTextArea txtText = new JTextArea();
    protected JTextArea txtComment = new JTextArea();

    // Scrollpanes
    protected JScrollPane scrText = new JScrollPane(txtText);
    protected JScrollPane scrComment = new JScrollPane(txtComment);
    
    // Checkbox
    // START GU 2015-10-12: Additional possibility to control the breakpoint setting
    protected JCheckBox chkBreakpoint = new JCheckBox("Breakpoint");
    // END KGU 2015-10-12
    
    // START KGU 2015-10-14: Additional information for data-specific title translation
    public String elementType = new String();	// The (lower-case) class name of the element type to be edited here
    public boolean forInsertion = false;		// If this dialog is used to setup a new element (in contrast to updating an existing element)
    private boolean gotSpecificTitle = false;	// class-specific title translation already done? (prevents setTitle() from spoiling it)
    // END KGU 2015-10-14


    private void create()
    {
            // set window title
            setTitle("Content");
            // set layout (OS default)
            setLayout(null);
            // set windows size
            setSize(500, 400);
            // show form
            setVisible(false);
            // set action to perfom if closed
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // set icon

            btnOK.addActionListener(this);
            btnCancel.addActionListener(this);

            btnOK.addKeyListener(this);
            btnCancel.addKeyListener(this);
            txtText.addKeyListener(this);
            txtComment.addKeyListener(this);
            addKeyListener(this);

            Border emptyBorder = BorderFactory.createEmptyBorder(4,4,4,4);
            txtText.setBorder(emptyBorder);
            txtComment.setBorder(emptyBorder);

            JPanel pnPanel0 = new JPanel();
            GridBagLayout gbPanel0 = new GridBagLayout();
            GridBagConstraints gbcPanel0 = new GridBagConstraints();
            gbcPanel0.insets=new Insets(10,10,0,10);
            pnPanel0.setLayout( gbPanel0 );

            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 3;
            gbcPanel0.gridwidth = 18;
            gbcPanel0.gridheight = 7;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 1;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( scrText, gbcPanel0 );
            pnPanel0.add( scrText );

            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 13;
            gbcPanel0.gridwidth = 18;
            gbcPanel0.gridheight = 4;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 1;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( scrComment, gbcPanel0 );
            pnPanel0.add( scrComment );

            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 1;
            gbcPanel0.gridwidth = 18;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( lblText, gbcPanel0 );
            pnPanel0.add( lblText );

            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 11;
            gbcPanel0.gridwidth = 18;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( lblComment, gbcPanel0 );
            pnPanel0.add( lblComment );

            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 18;
            gbcPanel0.gridwidth = 18;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( chkBreakpoint, gbcPanel0 );
            pnPanel0.add( chkBreakpoint );

            gbcPanel0.insets=new Insets(10,10,10,10);

            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 19;
            gbcPanel0.gridwidth = 7;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( btnCancel, gbcPanel0 );
            pnPanel0.add( btnCancel );

            gbcPanel0.gridx = 12;
            gbcPanel0.gridy = 19;
            gbcPanel0.gridwidth = 7;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints( btnOK, gbcPanel0 );
            pnPanel0.add( btnOK );

            Container container = getContentPane();
            container.setLayout(new BorderLayout());
            container.add(pnPanel0,BorderLayout.CENTER);

            txtText.requestFocus(true);
    }

    // listen to actions
    public void actionPerformed(ActionEvent event)
    {
            Object source=event.getSource();

            if (source == btnOK)
            {
                    OK=true;
            }
            else if (source == btnCancel)
            {
                    OK=false;
            }
            setVisible(false);
    }

    public void keyTyped(KeyEvent kevt)
    {
    }
	
    public void keyPressed(KeyEvent e)
    {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                    OK=false;
                    setVisible(false);
            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER && (e.isShiftDown() || e.isControlDown()))
            {
                    OK=true;
                    setVisible(false);
            }
    }

    public void keyReleased(KeyEvent ke)
    {
    }
	
    // constructors
    public InputBox(Frame owner, boolean modal)
    {
        super(owner,modal);
        create();
    }
	
    // constructors
    /*public InputBox()
    {
        super();
	this.setModal(true);
        create();
    }*/

    // START KGU 2015-10-14: data-specific title localisation
    /**
     * Replaces the title string by translation if keys match some internal state information
     * @see lu.fisch.structorizer.gui.LangDialog#setLangSpecific(lu.fisch.utils.StringList, java.lang.String)
     */
    @Override
	protected void setLangSpecific(StringList keys, String translation)
	{
		if (!keys.get(2).isEmpty() && keys.get(2).equalsIgnoreCase(this.elementType))
		{
			String discriminator = keys.get(3);
			if (discriminator.isEmpty() ||
					discriminator.equals("insert") && this.forInsertion ||
					discriminator.equals("update") && !this.forInsertion)
			{
				this.setTitle(translation);
				this.gotSpecificTitle = true;
			}
			if (discriminator.equalsIgnoreCase("lblText"))
			{
				this.lblText.setText(translation);
			}
		}
	}
    
    /**
     * Sets the title of the Dialog if no specific translation had already taken place
	 * @param title - the title to be displayed in the dialog's border; a null value is ignored here
     */
    @Override
    public void setTitle(String title)
    {
    	if (title != null && !this.gotSpecificTitle)
    	{
    		super.setTitle(title);
    	}
    }
    // END KGU 2015-10-14
    
}
