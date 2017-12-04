package nl.hu.pafr.ass2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *   This code was edited or generated using CloudGarden's Jigloo
 *   SWT/Swing GUI Builder, which is free for non-commercial
 *   use. If Jigloo is being used commercially (ie, by a corporation,
 *   company or business for any purpose whatever) then you
 *   should purchase a license for each developer using Jigloo.
 *   Please visit www.cloudgarden.com for details.
 *   Use of Jigloo implies acceptance of these licensing terms.
 *   A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 *   THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 *   LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

public class PoorRail extends javax.swing.JFrame implements ActionListener {
	
	private JButton btnDeleteWagon1;
	private JButton btnDeleteWagon2;
	private JButton btnDeleteWagon3;

	private JButton btnAddWagon1;
	private JButton btnAddWagon2;
	private JButton btnAddWagon3;

	private JButton btnDeleteTrain;
	private JButton btnChooseTrain;
	private JButton btnNewTrain;
	
	private JPanel pnlWagons;
	private JPanel jPanel2;
	private JPanel drawPanel;
	private JPanel jPanel1;
	
	private JTextField tfCurrentTrain;
	private JTextField tfNewTrain;
	
	private JComboBox cbAllTrains;
	
	private HashMap numberOfWagons;
	
	private JTextPane tpTextTrain;
	
	private Train selectedTrain = null;
	
	private int currentNumberOfWagons;
	private int currentTrain = -1;
	private final int OFFSET = 100;
	private final int TRAINLENGTH = 100;
		
	private double[] weights = new double[] { 0.1, 0.1, 0.1, 0.1 };
	private int[]    heights = new int[]    { 7,   7,   7,   7   }; 
	
	private ArrayList<Wagon> wagons = new ArrayList<Wagon>();
	
	
	// On run
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PoorRail inst = new PoorRail();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	
	public PoorRail() {
		super();
		initGUI();
	}
	
	
	
	// Create the GUI
	private void initGUI() {
		try {
			this.setTitle("PoorRail");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			thisLayout.rowWeights = weights;
			thisLayout.rowHeights = heights;
			thisLayout.columnWeights = weights;
			thisLayout.columnWidths = heights;
			getContentPane().setLayout(thisLayout);
			
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BorderLayout());
			getContentPane().add(jPanel1, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			
			drawPanel = new JPanel();
			drawPanel.setBackground(Color.WHITE);
			jPanel1.add(drawPanel, BorderLayout.CENTER);
			
			
			jPanel2 = new JPanel();
			GridBagLayout jPanel2Layout = new GridBagLayout();
			
			jPanel2.setLayout(jPanel2Layout);
			getContentPane().add(jPanel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			
			tpTextTrain = new JTextPane();
			jPanel2.add(tpTextTrain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			jPanel2.setBounds(10, 10, 100, 15);
			jPanel2Layout.rowWeights = weights;
			jPanel2Layout.rowHeights = heights;
			jPanel2Layout.columnWeights = weights;
			jPanel2Layout.columnWidths = heights;
			tpTextTrain.setText("Train Name: ");
			
			
			
			tfNewTrain = new JTextField(20);
			jPanel2.add(tfNewTrain, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			
			btnNewTrain = new JButton();
			jPanel2.add(btnNewTrain, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnNewTrain.setText("Create New Train");
			btnNewTrain.addActionListener(this);
			
			ComboBoxModel cbAllTrainsModel = new DefaultComboBoxModel(new String[] { });
			cbAllTrains = new JComboBox();
			jPanel2.add(cbAllTrains, new GridBagConstraints(1, 1, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			cbAllTrains.setModel(cbAllTrainsModel);
			
			btnChooseTrain = new JButton();
			jPanel2.add(btnChooseTrain, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnChooseTrain.setText("Select Train");
			btnChooseTrain.addActionListener(this);
			
			btnDeleteTrain = new JButton();
			jPanel2.add(btnDeleteTrain, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnDeleteTrain.setText("Delete Train");
			btnDeleteTrain.addActionListener(this);
			
			pnlWagons = new JPanel();
			GridBagLayout jPanel3Layout = new GridBagLayout();
			getContentPane().add(pnlWagons, new GridBagConstraints(1, 2, 2, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			jPanel3Layout.rowWeights = weights;
			jPanel3Layout.rowHeights = heights;
			jPanel3Layout.columnWeights = weights;
			jPanel3Layout.columnWidths = heights;
			pnlWagons.setLayout(jPanel3Layout);
			pnlWagons.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
		
			
			
			tfCurrentTrain = new JTextField();
			pnlWagons.add(tfCurrentTrain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			tfCurrentTrain.setText("Selected: ");
		
			
			
			// Add Buttons for Adding Wagons
			
			btnAddWagon1 = new JButton();
			pnlWagons.add(btnAddWagon1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnAddWagon1.setText("Add Wagon 1");
			btnAddWagon1.addActionListener(this);
			
			btnAddWagon2 = new JButton();
			pnlWagons.add(btnAddWagon2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnAddWagon2.setText("Add Wagon 2");
			btnAddWagon2.addActionListener(this);
			
			btnAddWagon3 = new JButton();
			pnlWagons.add(btnAddWagon3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnAddWagon3.setText("Add Wagon 3");
			btnAddWagon3.addActionListener(this);
			
			
			
			// Add Buttons for Deleting Wagons 
			
			btnDeleteWagon1 = new JButton();
			pnlWagons.add(btnDeleteWagon1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnDeleteWagon1.setText("Delete Wagon 1");
			btnDeleteWagon1.addActionListener(this);
			
			btnDeleteWagon2 = new JButton();
			pnlWagons.add(btnDeleteWagon2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnDeleteWagon2.setText("Delete Wagon 2");
			btnDeleteWagon2.addActionListener(this);
			
			btnDeleteWagon3 = new JButton();
			pnlWagons.add(btnDeleteWagon3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnDeleteWagon3.setText("Delete Wagon 3");
			btnDeleteWagon3.addActionListener(this);
				
			
			
			
			pack();
			setSize(800, 600);
			numberOfWagons = new HashMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnNewTrain) {
			Train train = new Train(tfNewTrain.getText());
			
			if (train.getName() != null && train.getName().trim().length() > 0)	{
				addTrain(train);
				currentTrain = cbAllTrains.getSelectedIndex();
				drawTrain(train);
			}
		} else if (event.getSource() == btnChooseTrain) {
			if (cbAllTrains.getItemCount() > 0) {
				String selection = (String) cbAllTrains.getSelectedItem();
				
				selectedTrain = new Train(selection);
				
				tfCurrentTrain.setText("Selected: " + selection);
				int ti = cbAllTrains.getSelectedIndex();
				if (ti != currentTrain)	numberOfWagons.put(currentTrain, currentNumberOfWagons);
				currentTrain = ti;
				try	{
					currentNumberOfWagons = (Integer) numberOfWagons.get(currentTrain);
				} catch (Exception e) {
					currentNumberOfWagons = 0;
				}			
			}
		} else if (event.getSource() == btnDeleteTrain) {			
			if (cbAllTrains.getItemCount() > 0) {				
				String selectedTrain = (String) cbAllTrains.getSelectedItem();			
				cbAllTrains.removeItemAt(cbAllTrains.getSelectedIndex());				
				numberOfWagons.remove(selectedTrain);
				repaint();				
				
				if (selectedTrain != null) {
					currentTrain = cbAllTrains.getSelectedIndex();
					tfCurrentTrain.setText("Selected: " + selectedTrain);
				} else {
					currentTrain = 0;
					tfCurrentTrain.setText("Selected: ");
				}
			}
		} else if (event.getSource() == btnAddWagon1) {
			currentNumberOfWagons++;
			drawWagon(new Wagon("Wagon1"));
		} else if (event.getSource() == btnAddWagon2) {
			currentNumberOfWagons++;
			drawWagon(new Wagon("Wagon2"));
		} else if (event.getSource() == btnAddWagon3) {
			currentNumberOfWagons++;
			drawWagon(new Wagon("Wagon3"));
		} else if (event.getSource() == btnDeleteWagon1) {			
			
			
			ArrayList<Wagon> wagonsList = new ArrayList<Wagon>();	
			
			// Loop through the array of current wagons
			for(int i = 0; i < wagons.size(); i++) {
				
				// Get the wagon object from the array
				Wagon wagon__ = wagons.get(i);
				
				if((wagon__.getName().equals("Wagon1")) && (wagon__.getTrain().getName().equals(selectedTrain.getName()))) {
										 
				} else {
					wagonsList.add(wagon__);
				}
			}
			
			
			// Reset the current number
			currentNumberOfWagons = 0;
					
			
			// Clear the drawings
			repaint(((30 + TRAINLENGTH)  + wagonsList.size() * 100), ((80 + currentTrain * OFFSET)), 1, 1);			
			
			
			// set lengthArray, otherwise the loop will continue FOREVER
			int lengthArray = wagonsList.size();
			
			wagons.clear();						
			
			
			
			// Redraw the other wagons - loop through the array of wagons
			for(int i = 0; i < lengthArray; i++) {
				
				// Get the wagon object
				Wagon wagon_ = wagonsList.get(i);		
				
				
				// Add a number for wagons
				currentNumberOfWagons++;
								
				// Draw the wagon
				drawWagon(wagon_);	
				
			}	
			
			
		} else if (event.getSource() == btnDeleteWagon2) {
			
			ArrayList<Wagon> wagonsList = new ArrayList<Wagon>();	
			
			// Loop through the array of current wagons
			for(int i = 0; i < wagons.size(); i++) {
				
				// Get the wagon object from the array
				Wagon wagon__ = wagons.get(i);
				
				if((wagon__.getName().equals("Wagon2")) && (wagon__.getTrain().getName().equals(selectedTrain.getName()))) {
										 
				} else {
					wagonsList.add(wagon__);
				}
			}
			
			
			// Reset the current number
			currentNumberOfWagons = 0;
					
			
			// Clear the drawings
			repaint(((30 + TRAINLENGTH)  + wagonsList.size() * 100), ((80 + currentTrain * OFFSET)), 1, 1);			
			
			
			// set lengthArray, otherwise the loop will continue FOREVER
			int lengthArray = wagonsList.size();
			
			wagons.clear();						
			
			
			
			// Redraw the other wagons - loop through the array of wagons
			for(int i = 0; i < lengthArray; i++) {
				
				// Get the wagon object
				Wagon wagon_ = wagonsList.get(i);		
				
				
				// Add a number for wagons
				currentNumberOfWagons++;
								
				// Draw the wagon
				drawWagon(wagon_);	
				
			}	
			
			
		} else if (event.getSource() == btnDeleteWagon3) {
			

			ArrayList<Wagon> wagonsList = new ArrayList<Wagon>();	
			
			// Loop through the array of current wagons
			for(int i = 0; i < wagons.size(); i++) {
				
				// Get the wagon object from the array
				Wagon wagon__ = wagons.get(i);
				
				if((wagon__.getName().equals("Wagon3")) && (wagon__.getTrain().getName().equals(selectedTrain.getName()))) {
										 
				} else {
					wagonsList.add(wagon__);
				}
			}
			
			
			// Reset the current number
			currentNumberOfWagons = 0;
					
			
			// Clear the drawings
			repaint(((30 + TRAINLENGTH)  + wagonsList.size() * 100), ((80 + currentTrain * OFFSET)), 1, 1);			
			
			
			// set lengthArray, otherwise the loop will continue FOREVER
			int lengthArray = wagonsList.size();
			
			wagons.clear();						
			
			
			
			// Redraw the other wagons - loop through the array of wagons
			for(int i = 0; i < lengthArray; i++) {
				
				// Get the wagon object
				Wagon wagon_ = wagonsList.get(i);		
				
				
				// Add a number for wagons
				currentNumberOfWagons++;
								
				// Draw the wagon
				drawWagon(wagon_);	
				
			}	
		}
	}
	
	
	
	// Create a train
	
	public String addTrain(Train train) {
		String t = "";
		
		try {
			t = train.getName().trim();
			for (int i = 0; i < cbAllTrains.getItemCount();i++ ) {
				String cbTrain = (String) cbAllTrains.getItemAt(i);
				if (cbTrain.equalsIgnoreCase(t)) {
					t = "";
					break;
				}
			}
			if (t != "") {
				if (currentTrain >= 0) numberOfWagons.put(currentTrain, currentNumberOfWagons);
				
				cbAllTrains.addItem(t);
				cbAllTrains.setSelectedItem(t);
				numberOfWagons.put(t, 0);
			}
		} catch (Exception e) {	}
		return t;			
	}
	
	
	
	
	// Draw the Train 
	
	public void drawTrain(Train train)	{
		if (train.hasName()) {
			Graphics g = drawPanel.getGraphics();
			
			// Draw train
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(30, (80 + currentTrain * OFFSET), 80, 40);
			g.fillRect(80, (60 + currentTrain * OFFSET), 30, 30);
			g.drawRoundRect(85, (40 + currentTrain * OFFSET), 20, 20, 20, 20);
			g.drawRoundRect(85, (currentTrain * OFFSET), 40, 40, 40, 40);
			
			// Draw wheels
			g.setColor(Color.BLACK);
			g.fillRoundRect(35, (120 + currentTrain * OFFSET), 20, 20, 20, 20);
			g.fillRoundRect(80, (120 + currentTrain * OFFSET), 20, 20, 20, 20);
			g.drawString(train.getName(), 40, (105 + currentTrain * OFFSET));
		}
    }
	
	
	
	// Draws Wagon
	
	public void drawWagon(Wagon wagon) {
		// If a train is selected
		if(selectedTrain != null) {
			
			// Add the train to the wagon object
			wagon.setTrain(selectedTrain);
			
			// Add to the list
			wagons.add(wagon);			
			
			
			// Just for testing, print out the name of the given wagon and the current number of wagons
			System.out.println(wagon.getName() + ", " + currentNumberOfWagons);
			
			
			// Get graphics panel
			Graphics g = drawPanel.getGraphics();
			
			// Draw Wagon
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect((30 + currentNumberOfWagons * TRAINLENGTH), (80 + currentTrain * OFFSET), 80, 40);
			
			// Draw Wheels
			g.setColor(Color.BLACK);
			g.fillRoundRect((35 + currentNumberOfWagons * TRAINLENGTH), (120 + currentTrain * OFFSET), 20, 20, 20, 20);
			g.fillRoundRect((80 + currentNumberOfWagons * TRAINLENGTH), (120 + currentTrain * OFFSET), 20, 20, 20, 20);
			g.drawString(wagon.getName(), (40 + currentNumberOfWagons * TRAINLENGTH), (105 + currentTrain * OFFSET));
		} 
    }
}
