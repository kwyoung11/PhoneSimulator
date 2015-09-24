import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public abstract class Phone implements Runnable{

	protected String mobile_id;
	
	// states
	protected boolean incoming_call;
	protected int arrival_of_caller_id;
	protected boolean connection_established;
	protected String caller_id;
	protected String current_caller_id;
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
	
	public void createFrame() {
		
	    Label simpleCallingLabel = new Label("");
	    
		Label timerLabel = new Label("           ");
		this.timerLabel = timerLabel;
		Label caller_id_label = new Label("");
		
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
	    interfaceFrame.add(timerLabel);
	    interfaceFrame.add(answer_button);
	    interfaceFrame.add(end_button);
	    interfaceFrame.pack();

	    // Set X,Y location
	   

	    this.frame = interfaceFrame;
	    interfaceFrame.setVisible(true);
	    this.centerFrame();
	    
	    
	    int i = 0;
	    while(true){
	    	
			    if (connectedPhone != null) {
					 if (ongoing_call) {
						 simpleCallingLabel.setText(connectedPhone.caller_id);
					 }
					while (ongoing_call) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String time = getTimeInMMSS(i);
						timerLabel.setText(time);
						i++;
						this.maintainTimer(i);
				    }
					timerLabel.setText("         ");
				}
			    
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
		
	}
	
	
	public void call(Phone phone, String message) {
	    this.outgoing_call = true;
	    this.connectedPhone = phone;
		this.current_caller_id = phone.caller_id;
		phone.incoming_call = true;
		phone.incoming_message = message;
		phone.current_caller_id = this.caller_id;
		phone.connectedPhone = this;
	}
	
	public void end_call() {
		this.incoming_call = false;
		boolean call_ended = false;
		if(ongoing_call == true){
			call_ended = true;
		}
		ongoing_call = false;
		outgoing_call = false;
		if (connectedPhone != null) {
			connectedPhone.incoming_call = false;
			connectedPhone.ongoing_call = false;
		}
		if(call_ended){
			System.out.println("Call ended");
		}
	}
	
	public void answer() {
		if (incoming_call) {
			incoming_call = false;
			ongoing_call = true;
			System.out.println("Client answered");
			this.connectedPhone.ongoing_call = true;
		}
	}
	
	private void maintainTimer(int i){
		
		if (ongoing_call) {
			String time = getTimeInMMSS(i);
	        this.timerLabel.setText(time);
	        if(this instanceof ClientPhone){
	        	System.out.println(caller_id + " in call with " + current_caller_id + " for " + i + " second(s)");
	        }
		}
	    this.timerLabel.setText("        ");
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
	
}
