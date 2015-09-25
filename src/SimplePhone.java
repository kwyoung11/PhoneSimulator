import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;


public class SimplePhone extends Phone{
	// attributes
	public String ring_through_time_range;
	public ArrayList<String> pass_code_challenge_caller_ids;
	public String mobile_id;
	public ArrayList<String> caller_id_blacklist;
	
	public SimplePhone() {
		
	}
	
	public SimplePhone(String mobile_id, String caller_id) {
		this.mobile_id = mobile_id;
		this.caller_id = caller_id;
	}

	@Override
	public void addToFrame() {
		Button callButton = new Button("Call Client");
		JTextField numberTextBox = new JTextField(8);
		JTextField timeTextBox = new JTextField(4);
		
		Label numberLabel = new Label("Number");
		Label timeLabel = new Label("Time");
		
		callButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPhone client = Network.getInstance().getClientPhone();
				SimplePhone.this.call(client, "My message");				
			}
		});
		
		Panel panel = new Panel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
	    
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    panel.add(numberLabel,constraints);
	    
	    constraints.gridx = 2;
	    constraints.gridy = 1;
	    panel.add(numberTextBox, constraints);
	    
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    panel.add(timeLabel,constraints);
	    
	    constraints.gridx = 2;
	    constraints.gridy = 2;
	    panel.add(timeTextBox, constraints);

	    constraints.gridx = 2;
	    constraints.gridy = 3;
	    constraints.insets = new Insets(5,5,5,5);
		panel.add(callButton, constraints);
		

	    frame.add(panel);
	    

	}
	
	@Override
	public void centerFrame() {
	    // Center the frame
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
		Frame interfaceFrame = this.frame;
		 // Get the current screen size
	    Dimension scrnsize = toolkit.getScreenSize();
	    // Get the frame size
	    Dimension framesize = interfaceFrame.getSize();
	    this.frame.setLocation ((int) (scrnsize.getWidth()/2) - frame.getWidth() , (int) (scrnsize.getHeight() - interfaceFrame.getHeight()) / 2);
	}

	@Override
	public void updateGUI() {
		
	}

	@Override
	public Object createLayout() {
		return new GridBagLayout();
	}
	
	
}
