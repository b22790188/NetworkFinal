package Msg;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {
	
	public static final int STICKMAN_MOVE_MSG = 1; //stickman移動訊息
	public static final int NEW_STICKMAN_MSG = 2;  //新stickman訊息
	public static final int NEW_BULLET_MSG = 3;
	
	public void send(DatagramSocket ds, String IP, int UDP_Port);
	public void parse(DataInputStream dis);
}
