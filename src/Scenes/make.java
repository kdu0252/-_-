package Scenes;

import java.io.*;
import java.net.*;
import java.util.*;

public class make{

	public int port = 6666;
	public int status = 0;
	// 0은 아무것도 시도하지 않았을 때, 1은 소켓 개방 실패, 2는 소켓 개방 성공
	// 3은 클라이언트 접속 대기, 4는 클라이언트 연결 실패, 5는 클라이언트 연결 성공
	
	public int A = 10;
	public int B = 20;
	public int C;
	
	private boolean roof = true;
	private int fps = 16;
	
	
	public String user;
	public ServerSocket server;
	public InetAddress otherIP;
	
	public make(){
		Thread makeroom = new Thread(new makeroom());
		makeroom.start();
	}
	


	
	public class makeroom implements Runnable{
		public void run(){
			try {
				System.out.println(port+" 포트 개방 시도 ... ");
				server = new ServerSocket(port, 2); // 2명까지만 접속 가능
				status = 1;
				System.out.println(port+" 포트 개방 성공 !!!");
				status = 2;
				
				status = 3;
				System.out.println(port+" 포트 신호 대기 ...");

				Socket rival = server.accept();
				System.out.println(port+" 포트로 신호 들어옴 !!!");
				System.out.println("접속자 IP : "+rival.getInetAddress().getHostAddress());
				status = 5;
				// 메소드가 클라이언트와 연결될 시 새로운 Socket 객체를 반환
				// 새로운 객체로 상호 통신 할 수 있음
				// 신호를 받는 동시에 새로운 객체(rival)로 전송
				
				System.out.println("상대와의 송수신을 위한 input, output 생성 시도 ...");
				InputStream input = rival.getInputStream();
				OutputStream output = rival.getOutputStream();
				System.out.println("상대와의 송수신을 위한 input, output 생성 성공 !!!");
				// rival 에서 클라이언트와 바이트 교환을 위해 생성
				// input은 클라이언트 정보 획득, output은 클라이언트로 정보 전송

				DataInputStream din = new DataInputStream(rival.getInputStream());
				C = din.readInt();
		        System.out.println(C);
		        
				DataOutputStream out = new DataOutputStream(rival.getOutputStream());
		        out.writeInt(A);
		        
//				server.close();
//				System.out.println("포트 닫음 !!!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
		
	}
	
}



//	public void makesocket(){
//		try {
//			ServerSocket RoomSocket = new ServerSocket(port);
//			Socket waitaccept = RoomSocket.accept(); // 연결 성공하면 accept가 출력
//		} catch (IOException e) {
//			e.printStackTrace(); // 이미 포트가 있거나 한 경우에 에러 출력
//		}
//	}




