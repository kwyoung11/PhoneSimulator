import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public abstract class Phone implements Runnable{

	protected String mobile_id;
	
	// states
	protected boolean incoming_call;
	protected int arrival_of_caller_id;
	protected String caller_id;
	protected boolean outgoing_call;
	protected Phone connectedPhone;
	protected String incoming_message;
	protected boolean ongoing_call;
	protected Frame frame;
	protected Label timerLabel;
	
	public void run(){
		createFrame();
		addToFrame();
	}
	
	public abstract void centerFrame();
	
	public abstract void addToFrame();
	
	/** 
	 * Update any fields that need to be updated within the frame here
	 * 
	 */
	public abstract void updateGUI();
	
	public abstract Object createLayout();
	
	public void createFrame() {
		Label timerLabel = new Label("        ");
		Label callDurationLabel = new Label("                           ");
		this.timerLabel = timerLabel;
		Label caller_id_label = new Label("                       ");
		Label callerIDLabel = new Label("                            ");
		
		// generate the GUI
		Frame  interfaceFrame = new Frame(caller_id);
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
	    // update rows parameter for the number of rows.
	    Object layout = createLayout();
	    if (this instanceof ClientPhone) {
	    	interfaceFrame.setLayout((GridBagLayout)layout);
	    }
	    else {
	    	interfaceFrame.setLayout((GridBagLayout)layout);
	    }
	    
	    // Add to frame
	    Panel panel = new Panel(new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    panel.add(timerLabel, constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    panel.add(callDurationLabel, constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    panel.add(callerIDLabel, constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 2;
	    constraints.gridy = 2;
	    panel.add(caller_id_label, constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 0;
	    constraints.gridy = 5;
	    constraints.insets = new Insets(5,5,5,5);
	    panel.add(answer_button, constraints);
	    
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.gridx = 1;
	    constraints.gridy = 5;
	    constraints.insets = new Insets(5,5,5,5);
	    panel.add(end_button, constraints);
	    
	    interfaceFrame.add(panel);
	    this.frame = interfaceFrame;
	    this.addToFrame();
	    interfaceFrame.pack();

	    
	   
	    // Set X,Y location
	   

	    
	    interfaceFrame.setVisible(true);
	    this.centerFrame();
	    
	    System.out.println("done creating GUI");
	   
	    
	    while (true) {
	    	Phone connectedPhone = this.getConnectedPhone();
		    if (connectedPhone != null) {
				 caller_id_label.setText("HELLO");
				 int j = 0;
				 
				 synchronized (this) {
			    	  
					    if (this instanceof ClientPhone) { // if ClientPhone
					    	if (incoming_call) {
					    		callerIDLabel.setText("Incoming ...");
					    	} else if (ongoing_call) {
					    		callDurationLabel.setText("Call Duration: ");
					    		callerIDLabel.setText("Connected to...");
					    	} else {
					    		callerIDLabel.setText("                          ");
					    	}
					    } else { // else if SimplePhone
					    	if (outgoing_call) {
							    callerIDLabel.setText("Calling...");
							} else if (ongoing_call) {
								callDurationLabel.setText("Call Duration: ");
								callerIDLabel.setText("Connected to ...");
							} else {
								callerIDLabel.setText("                          ");
							}
					   }
			    }
				 
				 
				while (ongoing_call) {
					callerIDLabel.setText("Connected to ...");
					callDurationLabel.setText("Call Duration: ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					j++;
					this.maintainTimer(j);
			    }
				timerLabel.setText("     ");
			} else {
		    	callDurationLabel.setText("                      ");
				callerIDLabel.setText("                           ");
		    	caller_id_label.setText("");
		    	
		    }
		    int i = 0;
		    while (incoming_call) {
		    	 if (this instanceof ClientPhone) { // if ClientPhone
		    		 callerIDLabel.setText("Incoming call ...");
		    	 } else {
		    		 callerIDLabel.setText("Calling ...");
		    	 }
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
		    this.updateGUI();
		    
		  
		       
	    }
    }
		
	
	
	
	public synchronized void call(Phone phone, String message) {
	    this.outgoing_call = true;
	    this.setConnectedPhone(phone);
	    phone.setConnectedPhone(this);
		phone.incoming_call = true;
		phone.incoming_message = message;
		System.out.println("incoming call is " + phone.incoming_call);
	}
	
	public synchronized void end_call() {
		this.incoming_call = false;
		boolean call_ended = false;
		if(ongoing_call == true){
			call_ended = true;
		}
		this.ongoing_call = false;
		this.outgoing_call = false;
		if (this.getConnectedPhone() != null) {
			this.getConnectedPhone().incoming_call = false;
			this.getConnectedPhone().ongoing_call = false;
			this.getConnectedPhone().setConnectedPhone(null);
			this.setConnectedPhone(null);
		}
		if(call_ended){
			System.out.println("Call ended");
		}
	
	}
	
	public synchronized void answer() {
		if (incoming_call) {
			incoming_call = false;
			ongoing_call = true;
			System.out.println("Client answered");
			this.getConnectedPhone().ongoing_call = true;
		}
	}
	
	private Phone getConnectedPhone(){
		synchronized(this){
			return this.connectedPhone;
		}
	}
	
	private void setConnectedPhone(Phone phone){
		synchronized(this){
			this.connectedPhone = phone;
		}
	}
	private void maintainTimer(int i) {

		if (ongoing_call) {
			
			String time = getTimeInMMSS(i);
	        this.timerLabel.setText(time);
	        if(this instanceof ClientPhone){
	        	System.out.println(caller_id + " in call with " + this.getConnectedPhone().caller_id + " for " + i + " second(s)");
	        }
		}
		else{
			this.timerLabel.setText("        ");
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
		if(this instanceof ClientPhone){
			System.out.println(minutesString + ":" + secondsString);	
		}
		return minutesString + ":" + secondsString;
	}
	
}
