import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
		Button callButton = new Button("call");
		callButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPhone client = Network.getInstance().getClientPhone();
				SimplePhone.this.call(client, "My message");
				
			}
		});
		frame.add(callButton);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createLayout() {
		return new FlowLayout();
	}
	
	
}
