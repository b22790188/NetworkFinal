package Msg;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {
	public static final int PLAYER_MOVE_MSG = 1;
	public static final int BULLET_MSG = 2;
	
	public void send(DatagramSocket ds, String IP, int UDP_Port);
	public void parse(DataInputStream dis);
}
