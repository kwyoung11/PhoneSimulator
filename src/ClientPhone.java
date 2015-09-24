// Import AWT classes
import java.awt.*;


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
		return new GridLayout(3, 1);
	}
	
	
}
