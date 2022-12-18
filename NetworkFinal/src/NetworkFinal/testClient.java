package NetworkFinal;

import java.util.*;
import java.net.*;
import java.io.*;

public class testClient {
				
	public static void main(String[] args) {
		NetClient c = new NetClient();
		c.sendDisconnectRequest();
	}
}
