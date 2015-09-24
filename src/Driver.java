
public class Driver {

		public static void main(String[] args) {
			// create a network
			Network network = new Network();
			
			// instantiate outside phone
			Phone outside_phone = network.add_phone("3015181111", "Outside Phone"); // add outside phone to network
			outside_phone.start(); // start thread of outside phone
			
			// instantiate client phone
			PhoneInterface client_phone_interface = new PhoneInterface("3016571111", "Client");
			client_phone_interface.phone.phone_interface = client_phone_interface;
			client_phone_interface.phone.start(); // start thread of client phone
			Network.phones.add(client_phone_interface.phone); // add client phone to network
			
			// call client phone from outside phone
			outside_phone.call(client_phone_interface.phone, "This is my message...");
			
			System.out.println("This is test from Rae");
		}
	
}
