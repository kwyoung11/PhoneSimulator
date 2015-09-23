import java.util.ArrayList;


public class PhoneInterface {
	// attributes
	public String ring_through_time_range;
	public ArrayList<String> pass_code_challenge_caller_ids;
	public String mobile_id;
	public ArrayList<String> caller_id_blacklist;
	public Phone phone;
	
	// states
	public int incoming_call;
	public int arrival_of_caller_id;
	public boolean connection_established;
	
	public PhoneInterface() {
		
	}
	
	public PhoneInterface(String mobile_id, String caller_id) {
		this.mobile_id = mobile_id;
		this.phone = new Phone(mobile_id, caller_id, true);
	}
	
	public void establish_connection() {
		connection_established = true;
	}
	
	public void receive(String caller_id, String message) {
		phone.current_caller_id = caller_id;
		ring_client_phone();
	}
	
	// behaviors
	public void ring_client_phone() {
		phone.incoming_call = true;
		
	}
	
	public void goto_answering_machine() {
		
	}
}
