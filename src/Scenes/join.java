package Scenes;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class join{

	Socket sock;
	BufferedReader reader;
	PrintWriter writer;
	int joinstat;
	
	public int A;
	public int C = 30;
	public int D = 40;
	public boolean roof=true;
	
	// // 0�� ���� ���� ����, 1�� ���� ���� ����
	
	public join(){
		Thread joinroom = new Thread(new joinroom());
		joinroom.start();
	}
	
	public class joinroom implements Runnable{
		public void run(){
			try{
				System.out.println("���� ���� �õ�...");
				sock = new Socket("203.230.91.204",6666);
				
				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(streamReader);
				writer = new PrintWriter(sock.getOutputStream());
				joinstat = 1;
				System.out.println("���� ���� ���� !!!");
		
				InetAddress address = InetAddress.getLocalHost();
				System.out.println("Host Ip:("+address.getHostName()+")"+address.getHostAddress());
					        
			} catch(IOException ex){
				joinstat = 0;
				ex.printStackTrace();
				System.out.println("���� ���� ���� !!!");
			}
		}
	}
}

 


// �Ʒ��� ������

//package Scenes;
//
//import java.io.*;
//import java.net.*;
//import java.util.*;
//
//import javax.swing.*;
//
//import java.awt.*;
//import java.awt.event.*;
//
//public class join {
//	
//	Socket sock;
//	BufferedReader reader;
//	PrintWriter writer;
//	int joinstat;
//	// // 0�� ���� ���� ����, 1�� ���� ���� ����
//	
//	public class joinserver{
//		Thread netThread = new Thread();
//	}
//	
//	public void setupNetworking(){
//		try{
//			System.out.println("���� ���� �õ�...");
//			sock = new Socket("127.0.0.1",6666);
//					
//			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
//			reader = new BufferedReader(streamReader);
//			writer = new PrintWriter(sock.getOutputStream());
//			System.out.println("���� ���� ����");
//			System.out.println("���� IP : "+sock.getInetAddress().getHostAddress());
//			joinstat = 1;
//		} catch(IOException ex){
//			joinstat = 0;
//			ex.printStackTrace();
//			System.out.println("���� ���� ����!");
//		} 
//	} // SetupNetworking ��
//}
