import java.util.ArrayList;


public class Network {
	
	public static ArrayList<Phone> phones;
	
	public Network() {
		
	}
	
	public Phone add_phone(String mobile_id, String caller_id) {
		Phone phone = new Phone(mobile_id, caller_id, 1);
		phones.add(phone);
		return phone;
	}
	
	public Phone add_phone_with_interface(String mobile_id, String caller_id) {
		Phone phone = new Phone(mobile_id, caller_id, 1);
		phones.add(phone);
		return phone;
	}
	
}
