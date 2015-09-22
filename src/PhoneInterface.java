import java.util.ArrayList;


public class PhoneInterface {
	// attributes
	public String ring_through_time_range;
	public ArrayList<String> pass_code_challenge_caller_ids;
	public String mobile_id;
	public ArrayList<String> caller_id_blacklist;
	
	
	// states
	public int incoming_call;
	public int arrival_of_caller_id;
	public int connection_established;
	
	public PhoneInterface() {
		
	}
	
	public PhoneInterface(String mobile_id) {
		this.mobile_id = mobile_id;
	}
	
	// behaviors
	public void ring_client_phone() {
		
	}
	
	public void goto_answering_machine() {
		
	}
}
