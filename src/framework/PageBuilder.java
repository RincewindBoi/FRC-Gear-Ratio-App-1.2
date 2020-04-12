package framework;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import framework.Motor;
import framework.MotorHandler;


@SuppressWarnings("serial")
public class PageBuilder extends JFrame implements ActionListener, ChangeListener{
	
	
	JPanel panel = new JPanel();
	@SuppressWarnings("rawtypes")
	JComboBox motordropdown = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox interface0 = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox interface1 = new JComboBox();

	JLabel lblText0 = new JLabel();
	JLabel lblText1 = new JLabel();
	JLabel lblText2 = new JLabel();
	JLabel lblText3 = new JLabel();
	JLabel lblText4 = new JLabel();
	JLabel lblText5 = new JLabel();
	JLabel lblText6 = new JLabel();

	JSpinner loadInput = new JSpinner(new SpinnerNumberModel(0.01, 0.0, 10000.0, 0.01));

	public double spinnerLoadInput;
	public int motorindex ;
	public int ratioindex0;
	public int ratioindex1;
	public double ratio0;
	public double ratio1;
	public double weight0;
	public double weight1;
	public double weight2;
	public double freespeed;
	public double freecurrent;
	public double stallcurrent;
	public double stalltorque;
	public double loadedSpeed;
	public double freeOverRatio;
	public double torqueStallTimesRatio;
	public double loadInputOverTorqueStallTimesRatio;
	public double stallCurrentMinusFreeCurrent;
	public double outputStallCurrent;
	public double firststageratio;
	public double totalweight;
	public double roundedLoadedSpeed;
	public double roundedOutputStallCurrent;

	
	Motor motor = new Motor();
	Interfaces interfaces = new Interfaces();
	Interfaces2 interfaces2 = new Interfaces2();
	
	
	
	public static void main(String[] args) {
		
		PageBuilder frame = new PageBuilder();
		frame.setVisible(true);
			
	}
	
	@SuppressWarnings("unchecked")
	public PageBuilder() {
		
		Iterator<MotorHandler> iterator = motor.motorlist.iterator();
		while (iterator.hasNext()) {
			MotorHandler motor= iterator.next();
			motordropdown.addItem(motor.name); //= new JComboBox((ComboBoxModel) motor);
		}

		
		
		motordropdown.addActionListener(this);
		loadInput.addChangeListener(this);
		setSize(new Dimension(1920, 100));
		setTitle("Gear Ratio App");
		setLayout(new GridLayout(2, 3, 0, 0));
		add(motordropdown);
		add(lblText1);
		add(lblText2);
		add(lblText3);
		add(lblText4);
		add(lblText5);
		add(lblText6);
		add(loadInput);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public static double round(double value, int places) {
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	

	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == motordropdown) {
			@SuppressWarnings("rawtypes")
			JComboBox cb = (JComboBox)e.getSource();
			String msg = (String)cb.getSelectedItem();

		}
		if(e.getSource() == interface0) {
			@SuppressWarnings("rawtypes")
			JComboBox cb = (JComboBox)e.getSource();
			@SuppressWarnings("unused")
			String msg = (String)cb.getSelectedItem();
				
			}
		
		if(e.getSource() == interface1 || e.getSource() == motordropdown || e.getSource() == interface0) {
			@SuppressWarnings({ "rawtypes", "unused" })
			JComboBox cb = (JComboBox)e.getSource();
			
			motorindex = motordropdown.getSelectedIndex();
			ratioindex0 = interface0.getSelectedIndex();
			ratioindex1 = interface1.getSelectedIndex();

			ratio0 = interfaces.interfaceList.get(ratioindex0).ratiospread;
			ratio1 = interfaces2.interfaceList2.get(ratioindex1).ratiospread;
			
			weight0 = motor.motorlist.get(motorindex).weight_lbs;
			weight1 = interfaces.interfaceList.get(ratioindex0).weight_lbs;
			weight2 = interfaces2.interfaceList2.get(ratioindex1).weight_lbs;
			
			freespeed = motor.motorlist.get(motorindex).freespeed_rpm;
			freecurrent = motor.motorlist.get(motorindex).freecurrent_a;
			stallcurrent = motor.motorlist.get(motorindex).stallcurrent_a;
			stalltorque = motor.motorlist.get(motorindex).stalltorque_nm;
					
			doCalculations();		
			}
		}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == loadInput) {
			double spinnerLoadInputUnround = (double) loadInput.getValue();
			spinnerLoadInput = round(spinnerLoadInputUnround, 2);
			doCalculations();
		}	
	}
	
	public void doCalculations() {
		
		firststageratio = (ratio0 / ratio1);
				
		totalweight = round((weight0 + weight1 + weight2), 2);
		
		freeOverRatio = freespeed/firststageratio;
		torqueStallTimesRatio = stalltorque * firststageratio;
		loadInputOverTorqueStallTimesRatio = (spinnerLoadInput / torqueStallTimesRatio);
		loadedSpeed = freeOverRatio * (1.0 - loadInputOverTorqueStallTimesRatio);
		roundedLoadedSpeed = round(loadedSpeed, 2);
		stallCurrentMinusFreeCurrent = (stallcurrent - freecurrent);
		outputStallCurrent = (stallCurrentMinusFreeCurrent *  loadInputOverTorqueStallTimesRatio) + freecurrent;
		roundedOutputStallCurrent = round(outputStallCurrent, 2);
		
		if (roundedOutputStallCurrent <= 40) {
			lblText6.setText("Your ratio works for this load at all times!");
		}
		else if (roundedOutputStallCurrent > 100) {
			lblText6.setText("Your motor will fail instantly!");
		}
		else if (roundedOutputStallCurrent > 75) {
			lblText6.setText("Your motor will fail after 1 second of stress!");
		}
		else if (roundedOutputStallCurrent > 41) {
			lblText6.setText("Your motor will fail after 10 seconds of stress!");
		}


		System.out.println("OutputStallCurrent   " + outputStallCurrent);
		System.out.println("FreeOverRatio  " + freeOverRatio);
		System.out.println("TorqueStallTimesRatio  " + torqueStallTimesRatio);
		System.out.println(spinnerLoadInput);		
		
		lblText2.setText("Your present ratio is " + String.valueOf(firststageratio));
		lblText3.setText("Your present weight is " + String.valueOf(totalweight) + " lbs");
		lblText4.setText("Your loaded speed is " + String.valueOf(roundedLoadedSpeed) + "RPM");
		lblText5.setText("Your present stall current is  " + String.valueOf(roundedOutputStallCurrent + "Amps"));
		
	}
}



