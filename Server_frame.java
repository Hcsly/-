package ������;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.midi.Receiver;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class Server_frame extends JFrame {
	public static boolean isstart = false;
	public static boolean clientconnect=false;
	public static Socket socket;
	Server_qipan qipan;
	Server_talk talk;
	public static ServerSocket server;

	public Server_frame(){
		qipan = new Server_qipan();
		talk = new Server_talk();//���촰����ʧ
		this.setSize(1100, 550);
		this.setTitle("������_������");
		this.add(qipan, BorderLayout.CENTER);
		this.add(talk, BorderLayout.EAST);////���촰����ʧ
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu gameMenu = new JMenu("��Ϸ(G)"); // ������Ϸ�˵�
		JMenu serMenu = new JMenu("����(H)"); // ������Ϸ�˵�
		JMenu cdMenu = new JMenu("�˵�(D)"); // ������Ϸ�˵�
		JMenu ltMenu = new JMenu("����(O)"); // ��������˵�
		gameMenu.setMnemonic('G');
		serMenu.setMnemonic('H');//G
		//
		cdMenu.setMnemonic('D');
		ltMenu.setMnemonic('O');	
		// ������Ϸ�Ӳ˵�������
		JMenuItem connect_ser = new JMenuItem("����������", 'F');
		connect_ser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		connect_ser.addActionListener(new AbstractAction("����������") {
			public void actionPerformed(ActionEvent event) {
				boolean flag = Serverstart();
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "����ʧ��", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {

					// �����������ɹ������߳�
					Recive_thred rth = new Recive_thred(socket,
							Server_talk.chantAear,qipan);
					// Server_panel_center_qipan.control=rth.control();
					// System.out.println("Server_panel_center_qipan="+Server_panel_center_qipan.control);
					rth.start();
				}

			}
		});
		JMenuItem waitclient = new JMenuItem("�ȴ��ͻ�������", 'E');//E
		waitclient.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		waitclient.addActionListener(new AbstractAction("�ȴ��ͻ�������") {
			public void actionPerformed(ActionEvent event) {
				try {
					socket = server.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // �ȴ��ͻ�������
			}
		});
		JMenuItem exitItem = new JMenuItem("�˳�", 'E');
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		exitItem.addActionListener(new AbstractAction("�˳�") {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		JMenuItem chatItem = new JMenuItem("����", 'C');
		chatItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		chatItem.addActionListener(new AbstractAction("����") {
			public void actionPerformed(ActionEvent event) {
				JFrame nw = new NewWindow();
				nw.setVisible(true);//��ʾ����
			}
		});
		
		//
		gameMenu.add(connect_ser);
		gameMenu.addSeparator();
		gameMenu.add(waitclient);
		gameMenu.addSeparator();
		gameMenu.add(exitItem);
		menuBar.add(gameMenu);
		menuBar.add(serMenu);
		menuBar.add(cdMenu);
		gameMenu.add(chatItem);
		this.setVisible(true);
	}

	/*
	 * ����������,���ҵȴ��ͻ�������
	 */
	public static boolean Serverstart() {
		try {
			// ����������
			server = new ServerSocket(6666);
			isstart = true; // ��ʾ�����������˶�
			Server_talk.chantAear.append("�����������ɹ������������ȴ��ͻ�������");
			socket = server.accept();
			return isstart;
		} catch (Exception e) {
			isstart = false;// δ������
			return false;
		}
	}

}
