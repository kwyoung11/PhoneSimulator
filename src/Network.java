import java.util.ArrayList;


public class Network {
	
	public static ArrayList<Phone> phones = new ArrayList<Phone>();
	
	public Network() {
		
	}
	
	public Phone add_phone(Phone phone){
		phones.add(phone);
		return phone;
	}
	
}
