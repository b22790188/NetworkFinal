package Msg;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {
	
	public static final int STICKMAN_MOVE_MSG = 1; 	//stickman移動訊息
	public static final int NEW_STICKMAN_MSG = 2;   //新stickman訊息
	public static final int STICKMAN_EXIST_MSG = 3; //用來告知新用戶舊客戶的存在
	public static final int NEW_BULLET_MSG = 4;
	public static final int STICKMAN_DEAD_MSG = 5;
	
	public void send(DatagramSocket ds, String IP, int server_UDP_Port);
	public void parse(DataInputStream dis);
}
