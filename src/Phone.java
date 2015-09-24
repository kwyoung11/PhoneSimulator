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
	Phone callee;
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
		
		Label simpleTimerLabel = new Label("          ");
	    Label simpleCallingLabel = new Label("");

					// create the GUI for the outside phone
					if (!hasInterface) {
						// generate the GUI
						Frame  frame = new Frame(caller_id);
					    Label label = new Label(current_caller_id);
//					    Button button0 = new Button("0");
//					    Button button1 = new Button("1");
//					    Button button2 = new Button("2");
//					    Button button3 = new Button("3");
//					    Button button4 = new Button("4");
//					    Button button5 = new Button("5");
//					    Button button6 = new Button("6");
//					    Button button7 = new Button("7");
//					    Button button8 = new Button("8");
//					    Button button9 = new Button("9");
					    Button answer_button = new Button("Call");
					    Button end_button = new Button("End");
					    
					    if (outgoing_call) {
					    	simpleCallingLabel.setText("Calling " + callee.caller_id);
					    }
					    
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
					    frame.add(simpleCallingLabel);
					    frame.add(simpleTimerLabel);
					    frame.add(answer_button);
					    frame.add(end_button);
//					    frame.add(button0);
//					    frame.add(button1);
//					    frame.add(button2);
//					    frame.add(button3);
//					    frame.add(button4);
//					    frame.add(button5);
//					    frame.add(button6);
//					    frame.add(button7);
//					    frame.add(button8);
//					    frame.add(button9);
					    frame.pack();
				
					    // Center the frame
					    Toolkit toolkit = Toolkit.getDefaultToolkit();
				
					    // Get the current screen size
					    Dimension scrnsize = toolkit.getScreenSize();
				
					    // Get the frame size
					    Dimension framesize = frame.getSize();
				
					    // Set X,Y location
					    frame.setLocation ((int) (scrnsize.getWidth()/2 - frame.getWidth()), (int) (scrnsize.getHeight() - frame.getHeight()) / 2);
					    frame.setVisible(true);
					    
					   
					    
					    
					}
					
					
		
		Label interfaceTimerLabel = new Label("           ");
		Label caller_id_label = new Label("");
		
		
		
		while (true) {
			
			 if (callee != null) {
				 if (callee.ongoing_call) {
					 simpleCallingLabel.setText(callee.caller_id);
				 }
					int i = 0;
					while (callee.ongoing_call) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String time = getTimeInMMSS(i);
						simpleTimerLabel.setText(time);
						i++;
				    }
					simpleTimerLabel.setText("         ");
				}
			
			
			int i = 0;
			if (incoming_call) {
				
				// generate the GUI
				Frame  interfaceFrame = new Frame(caller_id);
			    Label label = new Label(current_caller_id);
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
			    interfaceFrame.addWindowListener(new WindowAdapter() {
			    	  public void windowClosing(WindowEvent we) {
			    	    System.exit(0);
			    	  }
			    	});
			    
			    // Set layout manager
			    interfaceFrame.setLayout(new FlowLayout());
		
			    // Add to frame
			    interfaceFrame.add(label);
			    interfaceFrame.add(interfaceTimerLabel);
			    interfaceFrame.add(answer_button);
			    interfaceFrame.add(end_button);
			    interfaceFrame.pack();
		
			    // Center the frame
			    Toolkit toolkit = Toolkit.getDefaultToolkit();
		
			    // Get the current screen size
			    Dimension scrnsize = toolkit.getScreenSize();
		
			    // Get the frame size
			    Dimension framesize = interfaceFrame.getSize();
		
			    // Set X,Y location
			    interfaceFrame.setLocation ((int) (scrnsize.getWidth()/2) , (int) (scrnsize.getHeight() - interfaceFrame.getHeight()) / 2);
			    interfaceFrame.setVisible(true);
			    
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
					String time = getTimeInMMSS(j);
			        interfaceTimerLabel.setText(time);
					System.out.println(caller_id + " in call with " + current_caller_id + " for " + j + " second(s)");
				}
				j++;
			}
	        interfaceTimerLabel.setText("        ");
			
		}
		
	}
	
	public String getTimeInMMSS(int secondsIn) {
		int minutes = (int) Math.floor(secondsIn/60);
		int seconds = secondsIn - (minutes * 60);
		String secondsString = "" + seconds;
		String minutesString = "" + minutes;
		
		if (minutes < 10) {
			minutesString = "0" + minutes;
		} 
		if (seconds < 10) {
			secondsString = "0" + seconds;
		}
		System.out.println(minutesString + ":" + secondsString);
		return minutesString + ":" + secondsString;
	}
	
	public void call(Phone phone, String message) {
		if (phone.hasInterface) {
			phone.phone_interface.establish_connection();
			phone.phone_interface.receive(caller_id, message);
		}
	    outgoing_call = true;
	    callee = phone;
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
		outgoing_call = false;
		if (callee != null) {
			callee.incoming_call = false;
			callee.ongoing_call = false;
		}
		
		System.out.println("Call ended");
	}
}
