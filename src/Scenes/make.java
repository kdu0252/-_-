package Scenes;

import java.io.*;
import java.net.*;
import java.util.*;

public class make{

	public int port = 6666;
	public int status = 0;
	// 0�� �ƹ��͵� �õ����� �ʾ��� ��, 1�� ���� ���� ����, 2�� ���� ���� ����
	// 3�� Ŭ���̾�Ʈ ���� ���, 4�� Ŭ���̾�Ʈ ���� ����, 5�� Ŭ���̾�Ʈ ���� ����
	
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
				System.out.println(port+" ��Ʈ ���� �õ� ... ");
				server = new ServerSocket(port, 2); // 2������� ���� ����
				status = 1;
				System.out.println(port+" ��Ʈ ���� ���� !!!");
				status = 2;
				
				status = 3;
				System.out.println(port+" ��Ʈ ��ȣ ��� ...");

				Socket rival = server.accept();
				System.out.println(port+" ��Ʈ�� ��ȣ ���� !!!");
				System.out.println("������ IP : "+rival.getInetAddress().getHostAddress());
				status = 5;
				// �޼ҵ尡 Ŭ���̾�Ʈ�� ����� �� ���ο� Socket ��ü�� ��ȯ
				// ���ο� ��ü�� ��ȣ ��� �� �� ����
				// ��ȣ�� �޴� ���ÿ� ���ο� ��ü(rival)�� ����
				
				System.out.println("������ �ۼ����� ���� input, output ���� �õ� ...");
				InputStream input = rival.getInputStream();
				OutputStream output = rival.getOutputStream();
				System.out.println("������ �ۼ����� ���� input, output ���� ���� !!!");
				// rival ���� Ŭ���̾�Ʈ�� ����Ʈ ��ȯ�� ���� ����
				// input�� Ŭ���̾�Ʈ ���� ȹ��, output�� Ŭ���̾�Ʈ�� ���� ����

				DataInputStream din = new DataInputStream(rival.getInputStream());
				C = din.readInt();
		        System.out.println(C);
		        
				DataOutputStream out = new DataOutputStream(rival.getOutputStream());
		        out.writeInt(A);
		        
//				server.close();
//				System.out.println("��Ʈ ���� !!!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
		
	}
	
}



//	public void makesocket(){
//		try {
//			ServerSocket RoomSocket = new ServerSocket(port);
//			Socket waitaccept = RoomSocket.accept(); // ���� �����ϸ� accept�� ���
//		} catch (IOException e) {
//			e.printStackTrace(); // �̹� ��Ʈ�� �ְų� �� ��쿡 ���� ���
//		}
//	}




