package com.mashensoft.main;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.mashensoft.service.IIPService;
import com.mashensoft.service.IPServiceImpl;

public class AllKnowMain {
	public static void main(String[] args) {
		try {
			dealUserInput();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dealUserInput() throws Exception {
		ServerSocket serverSocket = new ServerSocket(6688);
		while (true) {
			Socket socket = serverSocket.accept();
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			printMainMenu(pw);
			Scanner scanner = new Scanner(socket.getInputStream());
			while (scanner.hasNextLine()) {
				String inputMsg = scanner.nextLine();
				switchService(scanner, pw, inputMsg);
			}
		}
	}

	public static void printMainMenu(PrintWriter pw) {
		String content = "码神百事通主菜单：\r\n";
		content+="1：查询IP地址\r\n";
		content+="2：查询身份证号：\r\n";
		content+="3：查询电影下载地址：\r\n";
		content+="4：查询天气：\r\n";
		pw.println(content);
		pw.flush();
//		pw.println("码神百事通主菜单：");
//		pw.println("1：查询IP地址");
//		pw.println("2：查询身份证号：");
//		pw.println("3：查询电影下载地址：");
//		pw.println("4：查询天气：");
	}

	public static void switchService(Scanner scanner,PrintWriter pw, String inputMsg) {
		switch (inputMsg) {
		case "1":
			IIPService ipService = new IPServiceImpl(scanner,pw);
			ipService.mainControl(pw);
			break;

		default:
			printMainMenu(pw);
			break;
		}
	}

}
