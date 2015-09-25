// Import AWT classes
import java.awt.*;

import javax.swing.JTextField;


public class ClientPhone extends Phone {
	
	SimplePhone phone_interface;
	
	// "dumb" outside phone
	public ClientPhone(String mobile_id, String caller_id) {
		this.mobile_id = mobile_id;
		this.caller_id = caller_id;
	}
	
	public void go_to_answering_machine(){
		
	}

	@Override
	public void addToFrame() {
		// TODO Auto-generated method stub
		Button add_button = new Button("Add");
	    Button remove_button = new Button("Remove");
	    Button view_button = new Button("View List");
	    Label callerIDLabel = new Label("Caller ID:");
	    Label callerNumberLabel = new Label("Caller Number:");
	    JTextField callerIDTextBox = new JTextField(10);
		JTextField callerNumberTextBox = new JTextField(10);
	    
	    GridBagConstraints constraints = new GridBagConstraints();
	    Panel panel = new Panel(new GridBagLayout());
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 2;
	    constraints.insets = new Insets(0,0,0,100);
	    
	    panel.add(callerIDLabel,constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 2;
	    constraints.insets = new Insets(0,120,0,0);
	    
	    panel.add(callerIDTextBox,constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 3;
	    constraints.insets = new Insets(0,0,0,100);
	    
	    panel.add(callerNumberLabel,constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 3;
	    constraints.insets = new Insets(0,120,0,0);
	    
	    panel.add(callerNumberTextBox,constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 5;
	    constraints.insets = new Insets(5,5,5,150);
	    
	    panel.add(add_button,constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 5;
	    constraints.insets = new Insets(5,5,5,5);
	    
	    panel.add(remove_button, constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 5;
	    constraints.insets = new Insets(5,150,5,5);
	    
	    panel.add(view_button, constraints);
	    
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
	    this.frame.setLocation ((int) (scrnsize.getWidth()/2) , (int) (scrnsize.getHeight() - interfaceFrame.getHeight()) / 2);
	}

	@Override
	public void updateGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createLayout() {
		return new GridBagLayout();
	}
	
	
}
