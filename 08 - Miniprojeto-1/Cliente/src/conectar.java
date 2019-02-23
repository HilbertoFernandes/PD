
import java.io.IOException;
import java.net.UnknownHostException;

public class conectar {
	public static void main(String[] args) 
			throws UnknownHostException,	IOException {
		new Cliente("localhost", 6500).conecta();
	}
}
