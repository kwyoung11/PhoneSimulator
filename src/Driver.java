
public class Driver {

		public static void main(String[] args) {
			Network network = new Network();
			Phone outside_phone = network.add_phone("3015182536", "Kevin Young");
			Phone client_phone = network.add_phone_with_interface("3016578590", "Home");
			
			outside_phone.call(client_phone, "Calling Home...");
		}
	
}
