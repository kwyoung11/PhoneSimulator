// Import AWT classes
import java.awt.*;
// Import AWT event classes
import java.awt.event.*;

// Import Swing classes
import javax.swing.*;


public class Phone extends Thread {
	public String mobile_id;
	public String caller_id;
	public boolean hasInterface;
	
	boolean incoming_call;
	boolean outgoing_call;
	boolean ongoing_call;
	String current_caller_id;
	String incoming_message;
	PhoneInterface phone_interface;
	
	// "dumb" outside phone
	public Phone(String mobile_id, String caller_id) {
		this.mobile_id = mobile_id;
		this.caller_id = caller_id;
	}
	
	// client phone with interface
	public Phone(String mobile_id, String caller_id, boolean hasInterface) {
		this.mobile_id = mobile_id;
		this.caller_id = caller_id;
		this.hasInterface = hasInterface;
	}
	
	// tasks to be performed by phone in separate thread (e.g. ringing, call active, etc.)
	public void run() {
		// create the GUI for the outside phone
					if (!hasInterface) {
						// generate the GUI
						Frame  frame = new Frame(caller_id);
					    Label  label = new Label("Phone");
					    Label label2 = new Label(current_caller_id);
//					    Label label3 = new Label(timer);
					    Button answer_button = new Button("Answer");
					    Button end_button = new Button("End");
					    
					    // add event listeners
					    answer_button.addActionListener( new ActionListener() {
						  public void actionPerformed(ActionEvent evt) {
						        answer();
						  }
						});
					    
					    end_button.addActionListener( new ActionListener() {
					      public void actionPerformed(ActionEvent evt) {
					        end_call();
					      }
					    });
					    
					    // close phone gui on "x" press
					    frame.addWindowListener(new WindowAdapter() {
					    	  public void windowClosing(WindowEvent we) {
					    	    System.exit(0);
					    	  }
					    	});
					    
					    // Set layout manager
					    frame.setLayout(new FlowLayout());
				
					    // Add to frame
					    frame.add(label);
					    frame.add(label2);
					    frame.add(answer_button);
					    frame.add(end_button);
					    frame.pack();
				
					    // Center the frame
					    Toolkit toolkit = Toolkit.getDefaultToolkit();
				
					    // Get the current screen size
					    Dimension scrnsize = toolkit.getScreenSize();
				
					    // Get the frame size
					    Dimension framesize = frame.getSize();
				
					    // Set X,Y location
					    frame.setLocation ((int) (scrnsize.getWidth() - frame.getWidth() ) / 2 , (int) (scrnsize.getHeight() - frame.getHeight()) / 2);
					    frame.setVisible(true);
					}
		
		
		while (true) {
			int i = 0;
			if (incoming_call) {
				
				// generate the GUI
				Frame  frame = new Frame(caller_id);
			    Label  label = new Label("Phone");
			    Label label2 = new Label(current_caller_id);
//			    Label label3 = new Label(timer);
			    Button answer_button = new Button("Answer");
			    Button end_button = new Button("End");
			    
			    // add event listeners
			    answer_button.addActionListener( new ActionListener() {
				  public void actionPerformed(ActionEvent evt) {
				        answer();
				  }
				});
			    
			    end_button.addActionListener( new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			        end_call();
			      }
			    });
			    
			    // close phone gui on "x" press
			    frame.addWindowListener(new WindowAdapter() {
			    	  public void windowClosing(WindowEvent we) {
			    	    System.exit(0);
			    	  }
			    	});
			    
			    // Set layout manager
			    frame.setLayout(new FlowLayout());
		
			    // Add to frame
			    frame.add(label);
			    frame.add(label2);
			    frame.add(answer_button);
			    frame.add(end_button);
			    frame.pack();
		
			    // Center the frame
			    Toolkit toolkit = Toolkit.getDefaultToolkit();
		
			    // Get the current screen size
			    Dimension scrnsize = toolkit.getScreenSize();
		
			    // Get the frame size
			    Dimension framesize = frame.getSize();
		
			    // Set X,Y location
			    frame.setLocation ((int) (scrnsize.getWidth() - frame.getWidth() ) / 2 , (int) (scrnsize.getHeight() - frame.getHeight()) / 2);
			    frame.setVisible(true);
			    
			    while (incoming_call) {
			    	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	if (incoming_call) {
			    		System.out.println(caller_id + "'s phone is ringing ... Ring #" + (i+1));
			    	}
					i++;
				}
			   
			    
			}
			int j = 0;
			while (ongoing_call) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (ongoing_call) {
					System.out.println(caller_id + " in call with " + current_caller_id + " for " + j + " second(s)");
				}
				j++;
			}
			
			
			
		}
		
	}
	
	public void call(Phone phone, String message) {
		if (phone.hasInterface) {
			phone.phone_interface.establish_connection();
			phone.phone_interface.receive(caller_id, message);
		}
	    
		phone.incoming_call = true;
		phone.incoming_message = message;
	}
	
	public void answer() {
		if (incoming_call) {
			incoming_call = false;
			ongoing_call = true;
			System.out.println("Client answered");
		}
	}
	
	public void ignore() {
		
	}
	
	public void end_call() {
		incoming_call = false;
		ongoing_call = false;
		System.out.println("Call ended");
	}
}
