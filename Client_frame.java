package 五子棋;

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
//	public static boolean connect = false; // 给出连接服务器的boolean，true表示连接成功了
//	static TextArea chatAear; // 所有消息内容区域
//	public static Socket socket; // 连接服务器的socket = new
//    //Socket(serverip.getText(), 5555);
//	private String message = "ok"; // 要发送给服务器的内容
//	Send_message sendmessage; // 发送的方法
//	private String who = "客户端说:";
//	private JTextField txt_message; // 将要发送给服务器的内容
//	private JTextField serverip; // 服务器的IP地址
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
		//设置关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置居中
		//this.setLocationRelativeTo(null);//66666666666666666666666
		this.setTitle("五子棋_客户端");
		this.add(qipan,BorderLayout.CENTER); 
		this.add(talk,BorderLayout.EAST);//EAST界面与对话框交换
		this.setVisible(true);
		//this.setResizable(false);//固定窗口
		/**************************************************/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu gameMenu = new JMenu("游戏(G)"); // 创建游戏菜单
		JMenu serMenu = new JMenu("帮助(H)"); // 创建游戏菜单
		JMenu cdMenu = new JMenu("菜单(D)"); // 创建游戏菜单
		JMenu ltMenu = new JMenu("聊天(O)"); // 创建聊天菜单
		gameMenu.setMnemonic('G');
		serMenu.setMnemonic('H');//G
		//
		cdMenu.setMnemonic('D');
		ltMenu.setMnemonic('O');	
		// 创建游戏子菜单及监听
		JMenuItem jb_connectserver = new JMenuItem("连接服务器", 'F');
		jb_connectserver.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		jb_connectserver.addActionListener(new AbstractAction("连接服务器") {
			public void actionPerformed(ActionEvent event) {
				//
				//Cilent_talk.Cilent_talk();
							}
			
		});
		
    /**********************************/
				JMenuItem exitItem = new JMenuItem("退出", 'E');
				exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
						InputEvent.CTRL_MASK));
				exitItem.addActionListener(new AbstractAction("退出") {
					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				});
				JMenuItem chatItem = new JMenuItem("聊天", 'E');
				chatItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
						InputEvent.CTRL_MASK));
				chatItem.addActionListener(new AbstractAction("聊天") {
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
		
		
	    //play();//一运行就播放音乐
		/**************************************/
		// 给服务器发送消息按钮
//					MouseAdapter send = new MouseAdapter() {
//						@Override
//						public void mouseClicked(MouseEvent e) {
//							// TODO Auto-generated method stub
//							super.mouseClicked(e);
//							chatAear.append("\n客户端说:" + txt_message.getText());
//							message = "CHANT" + "-" + who + txt_message.getText() + "\n";
//							System.out.println("客户端要发送的的message为" + message);
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
//				 * 连接服务器
//				 */
//				public boolean connectserver() {
//					try {
//						// 获取输入的IP地址，并且进行连接
//						socket = new Socket(serverip.getText(), 6666);
//						chatAear.append("\n连接服务器成功！");
//						connect = true;
//						return true;
//					} catch (UnknownHostException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						chatAear.append("\n连接失败");
//					}
//					return false;
//				}
//				
//		
//	
	};
	/************************************************/
	
	//添加背景音乐
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
		           byte[] buffer = new byte[128];  //缓冲区
		           while(nByte != -1){//获取最后一个#；

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