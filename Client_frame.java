package ������;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;

import javax.management.timer.Timer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

//
import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet; 
import java.net.MalformedURLException;
import java.net.Socket;


public class Client_frame extends JFrame{
	/**************************************/
//	
//	public static boolean connect = false; // �������ӷ�������boolean��true��ʾ���ӳɹ���
//	static TextArea chatAear; // ������Ϣ��������
//	public static Socket socket; // ���ӷ�������socket = new
//    //Socket(serverip.getText(), 5555);
//	private String message = "ok"; // Ҫ���͸�������������
//	Send_message sendmessage; // ���͵ķ���
//	private String who = "�ͻ���˵:";
//	private JTextField txt_message; // ��Ҫ���͸�������������
//	private JTextField serverip; // ��������IP��ַ
//	static Cilent_talk cilentPanelWestTalk = new Cilent_talk();
//	public static Cilent_talk getCilent_panel_west_talk(){
//		return cilentPanelWestTalk;
//	}
	/**************************************/
	public static Client_qipan qipan;
	Cilent_talk talk;
	public Client_frame(){
		qipan=new Client_qipan(); 
		talk=new Cilent_talk();//888888
		this.setSize(1250, 560);	
		//���ùر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���þ���
		//this.setLocationRelativeTo(null);//66666666666666666666666
		this.setTitle("������_�ͻ���");
		this.add(qipan,BorderLayout.CENTER); 
		this.add(talk,BorderLayout.EAST);//EAST������Ի��򽻻�
		this.setVisible(true);
		//this.setResizable(false);//�̶�����
		/**************************************************/
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
		JMenuItem jb_connectserver = new JMenuItem("���ӷ�����", 'F');
		jb_connectserver.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		jb_connectserver.addActionListener(new AbstractAction("���ӷ�����") {
			public void actionPerformed(ActionEvent event) {
				//
				//Cilent_talk.Cilent_talk();
							}
			
		});
		
    /**********************************/
				JMenuItem exitItem = new JMenuItem("�˳�", 'E');
				exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
						InputEvent.CTRL_MASK));
				exitItem.addActionListener(new AbstractAction("�˳�") {
					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				});
				JMenuItem chatItem = new JMenuItem("����", 'E');
				chatItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
						InputEvent.CTRL_MASK));
				chatItem.addActionListener(new AbstractAction("����") {
					public void actionPerformed(ActionEvent event) {
						JFrame nw = new NewWindow();
						nw.setVisible(true);
					}
				});
				//
				
				
				gameMenu.addSeparator();
				//gameMenu.add(jb_connectserver);
				gameMenu.add(exitItem);
				menuBar.add(gameMenu);
				menuBar.add(serMenu);
				menuBar.add(cdMenu);
				gameMenu.add(chatItem);
				//cdMenu.add(chatItem);
				this.setVisible(true);
				/************************************************/
		
		
	    //play();//һ���оͲ�������
		/**************************************/
		// ��������������Ϣ��ť
//					MouseAdapter send = new MouseAdapter() {
//						@Override
//						public void mouseClicked(MouseEvent e) {
//							// TODO Auto-generated method stub
//							super.mouseClicked(e);
//							chatAear.append("\n�ͻ���˵:" + txt_message.getText());
//							message = "CHANT" + "-" + who + txt_message.getText() + "\n";
//							System.out.println("�ͻ���Ҫ���͵ĵ�messageΪ" + message);
//							sendmessage.send(message, socket);
//						}
//					};
//					this.add(serverip);
//					//jb_connectserver.addMouseListener(connect);
//					this.add(jb_connectserver);
//
//					//jb_send.addMouseListener(send);
//					this.add(chatAear);
//					this.add(txt_message);
//					//this.add(jb_send);
//
//				}
//
//				/*
//				 * ���ӷ�����
//				 */
//				public boolean connectserver() {
//					try {
//						// ��ȡ�����IP��ַ�����ҽ�������
//						socket = new Socket(serverip.getText(), 6666);
//						chatAear.append("\n���ӷ������ɹ���");
//						connect = true;
//						return true;
//					} catch (UnknownHostException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						chatAear.append("\n����ʧ��");
//					}
//					return false;
//				}
//				
//		
//	
	};
	/************************************************/
	
	//��ӱ�������
	public static void play(){

		  String fileUrl = "Z:\\mp3\\test.wav";
		  try{
		           AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileUrl));
		           AudioFormat aif = ais.getFormat();
		           SourceDataLine sdl = null;
		           DataLine.Info info = new DataLine.Info(SourceDataLine.class,aif);
		           sdl = (SourceDataLine)AudioSystem.getLine(info);
		           sdl.open(aif);
		           sdl.start();
		           
		           int nByte = 0;
		           byte[] buffer = new byte[128];  //������
		           while(nByte != -1){//��ȡ���һ��#��

		               nByte = ais.read(buffer,0,128);
		               if(nByte >= 0)
		                  sdl.write(buffer, 0, nByte);                       
		           }
		           sdl.stop();

		    }catch(UnsupportedAudioFileException e){
		           e.printStackTrace();
		    } catch (IOException e) {
		           e.printStackTrace();
		    } catch (LineUnavailableException e) {

		           e.printStackTrace();
		    }

		 }  
	
	}