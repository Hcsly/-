package ������;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Server_talk extends JPanel{
	public static String who="������˵��";
	public static  TextArea chantAear;
	private JTextField txt_message;
	public static String message="ok"; // Ҫ���Ϳͻ��˵�����
	Send_message sendmessage;		//���͵ķ���
	public Server_talk(){
			sendmessage = new Send_message(message, Server_frame.socket);
			JButton jb_send = new JButton("����");
			//this.setLayout(new FlowLayout(FlowLayout.LEFT));//ǰע��
			
			txt_message = new JTextField("��������Ҫ��������");
			//txt_message.setPreferredSize(new Dimension(200,30));//ǰע��
			chantAear = new TextArea("------------------\n", 20, 40);
			MouseAdapter send = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					send(); // �������Լ���textarea�����Ϣ
				}
			};
			
			jb_send.addMouseListener(send);
			this.add(chantAear);
			this.add(txt_message);
			this.add(jb_send);
	}


	private void send() {
		// TODO Auto-generated method stub
		if (!Server_frame.isstart) {
			JOptionPane.showMessageDialog(null, "��������δ����,���ܷ�����Ϣ��", "����",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			String message = txt_message.getText();
			if (message == null || message.equals("")) {
				JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			chantAear.append("\n������˵��" + txt_message.getText() + "");
			message = "CHANT" + "-" +who+ txt_message.getText() + "\n";
			//System.out.println("������Ҫ���͵ĵ�messageΪ"+message);
			sendmessage.send(message, Server_frame.socket);
		}
	}

}
