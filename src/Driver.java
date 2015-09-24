
public class Driver {

		public static void main(String[] args) {
			// create a network
			Network network = new Network();
			
			// instantiate outside phone
			Phone outside_phone = new SimplePhone("3015181111", "Outside Phone");
			// add outside phone to network
			network.add_phone(outside_phone); 
			// start thread of outside phone
			new Thread(outside_phone).start();
			
			
			ClientPhone client_phone = new ClientPhone("3016571111", "Client");
			// add outside phone to network
			network.add_phone(client_phone); 
			// start thread of outside phone
			new Thread(client_phone).start();
			
			// call client phone from outside phone
			outside_phone.call(client_phone, "This is my message...");
		}
	
}
