package ������;

import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cilent_talk extends JPanel {
	Client_qipan qipan;
	public static boolean connect = false; // �������ӷ�������boolean��true��ʾ���ӳɹ���
	static TextArea chatAear; // ������Ϣ��������
	public static Socket socket; // ���ӷ�������socket = new
    //Socket(serverip.getText(), 5555);
	private String message = "ok"; // Ҫ���͸�������������
	Send_message sendmessage; // ���͵ķ���
	private String who = "�ͻ���˵:";
	private JTextField txt_message; // ��Ҫ���͸�������������
	private JTextField serverip; // ��������IP��ַ
	static Cilent_talk cilentPanelWestTalk = new Cilent_talk();
	public static Cilent_talk getCilent_panel_west_talk(){
		return cilentPanelWestTalk;
	}
	public  Cilent_talk() {
		socket =new Socket();
		sendmessage = new Send_message(message, socket);
		chatAear = new TextArea("----------------------\n", 20, 40);//20 40
		/*******************************/
		
		/**********************************/
		txt_message = new JTextField("��������Ҫ���͵���Ϣ");
		JButton jb_connectserver = new JButton("���ӷ�����");
		serverip = new JTextField("127.0.0.1");
		JButton jb_send = new JButton("����");
		MouseAdapter connect = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (connectserver()) {
					JOptionPane.showMessageDialog(null, "\n�ҳɹ�����!!!!");
					message = "CHANT" + "-"+"OK"+"-" + "���ǿͻ��ˣ����Ѿ����ӳɹ���" + "\n";
					System.out.println("�ͻ��˵�messageΪ" + message);
					// �ͻ������ӷ�������Ŵ��߳�
					Recive_thred rth = new Recive_thred(socket, chatAear,Client_frame.qipan);
					rth.start();
					sendmessage.send(message, socket);
				}
			}

		};
		// ��������������Ϣ��ť
		MouseAdapter send = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				chatAear.append("\n�ͻ���˵:" + txt_message.getText());
				message = "CHANT" + "-" + who + txt_message.getText() + "\n";
				System.out.println("�ͻ���Ҫ���͵ĵ�messageΪ" + message);
				sendmessage.send(message, socket);
			}
		};
		this.add(serverip);
		jb_connectserver.addMouseListener(connect);
		this.add(jb_connectserver);

		jb_send.addMouseListener(send);
		this.add(chatAear);
		this.add(txt_message);
		this.add(jb_send);

	}

	/*
	 * ���ӷ�����
	 */
	public boolean connectserver() {
		try {
			// ��ȡ�����IP��ַ�����ҽ�������
			socket = new Socket(serverip.getText(), 6666);
			chatAear.append("\n���ӷ������ɹ���");
			connect = true;
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			chatAear.append("\n����ʧ��");
		}
		return false;
	}
}
