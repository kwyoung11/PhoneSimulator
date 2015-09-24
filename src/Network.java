import java.util.ArrayList;


public class Network {
	
	public static ArrayList<Phone> phones = new ArrayList<Phone>();
	
	
	private static Network instance = null;
	private Network() {
		
	}
	
	public static Network getInstance(){
		if(instance == null){
			instance = new Network();
		}
		return instance;
	}
	
	
	public Phone add_phone(Phone phone){
		phones.add(phone);
		return phone;
	}
	
	public ClientPhone getClientPhone(){
		for(Phone p : phones){
			if(p instanceof ClientPhone){
				return (ClientPhone) p;
			}
		}
		return null;
		
	}
	
}
