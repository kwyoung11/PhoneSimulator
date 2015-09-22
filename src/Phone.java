
public class Phone {
	public String mobile_id;
	public String caller_id;
	public int hasInterface;
	
	boolean incoming_call;
	boolean outgoing_call;
	String incoming_message;
	
	public Phone(String mobile_id, String caller_id) {
		this.mobile_id = mobile_id;
		this.caller_id = caller_id;
		listen();
	}
	
	public Phone(String mobile_id, String caller_id, int hasInterface) {
		this.mobile_id = mobile_id;
		this.caller_id = caller_id;
		this.hasInterface = hasInterface;
		listen();
	}
	
	public void call(Phone phone, String message) {
		phone.incoming_call = true;
		phone.incoming_message = message;
	}
	
	public void listen() {
		while (true) {
			if (incoming_call) {
				
			}
		}
	}
	
	public void answer() {
		
	}
	
	public void ignore() {
		
	}
	
	public void end_call() {
		
	}
}
