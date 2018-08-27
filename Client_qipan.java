package ������;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class Client_qipan extends JPanel {
	
	Image board, black, white;
	int getx = 0, gety = 0; // ��ȡ�Լ���zuo
	static boolean xiaqi = true; // xiaqu=true�������£��������°�����
	int cou = 0; // ��ʾ�������ӵ�����
	String message = "ok"; // ���͵���Ϣ
	Send_message sendmessage; // ���͵ķ���
	int hgex = 0, hgey = 0;
	boolean isconnect = false;
	public static String control; // ��ť����
	public static boolean isgamestart = false;// true��Ϸ��ʼ
	public static boolean myselfok = false; // Ϊtrue��ʾ�Լ�׼������
	public static boolean duifangok = false; // Ϊtrue��ʾ�Է�׼������
	public static boolean ismyselfxiaqi = true; // �Լ����ˣ�����ishei=true��ʾ����false��ʾû�£����Լ�
	public static boolean isduifangxiaqi = false; // �Է����˵� Ϊtrue��ʾ�Է����ˣ�false��ʾû��	
	private MouseEvent xiaqie; // �°����ʱ�򣬰��������λ�õ�ȫ�ֱ���
	public static ArrayList<String> myself_list = new ArrayList<String>(); // �Լ����������
	public static ArrayList<String> duifang_list = new ArrayList<String>(); // �Է����������
	
	public Client_qipan() {
		// ����һ�����󣬲���ʵ����
		System.out.println(Cilent_talk.socket + "���췽�����socket");
		sendmessage = new Send_message(message, Cilent_talk.socket);
		System.out.println(sendmessage + "���췽�����sendmessage");
		this.setLayout(null); // ����
//		/**********************************************/
//		//���ð�ť���Ӱ�ť
//		JMenu gameMenu = new JMenu("��Ϸ(G)");
//		
//		
//		JMenuItem exitItem = new JMenuItem("�˳�", 'E');
//		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
//				InputEvent.CTRL_MASK));
//		exitItem.addActionListener(new AbstractAction("�˳�") {
//			public void actionPerformed(ActionEvent event) {
//				System.exit(0);
//			}
//		});
//		gameMenu.add(exitItem);
//		/********************************************/
		JButton ks = new JButton("��ʼ/��ͣ");
		ks.setBounds(460, 30,100,25);
		this.add(ks);
		JButton rs = new JButton("����");
		rs.setBounds(460, 90, 100, 25);
		this.add(rs);
		JButton ht = new JButton("����");
		ht.setBounds(460, 150, 100, 25);
		this.add(ht);
		JButton cxks = new JButton("���¿�ʼ");
		cxks.setBounds(460, 200, 100, 25);
		this.add(cxks);
		//
		JButton hq = new JButton("����");
		hq.setBounds(460, 260, 100, 25);
		this.add(hq);
		//
		JButton bf = new JButton("��������");
		bf.setBounds(460, 320, 100, 25);
		this.add(bf);
		
	    JButton khd = new JButton("�ͻ���ʣ��ʱ�䣺"+30);
		khd.setBounds(0, 460, 180,25);
		this.add(khd);
		
		JButton fwd = new JButton("�����ʣ��ʱ�䣺"+30);
		fwd.setBounds(270, 460, 180,25);
		this.add(fwd);
		//
		try {
			board = ImageIO.read(new File("img/board.gif"));
			black = ImageIO.read(new File("img/black.gif"));
			white = ImageIO.read(new File("img/white.gif"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��������������λ��
		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// System.out.println("xiaqi��갴��ȥ��");
				if (Cilent_talk.connect) {
					System.out.println("\n-----\nisgamestart=" + isgamestart
							+ "\nismyselfxiaqi=" + ismyselfxiaqi
							+ "\nisduifangxiaqi=" + isduifangxiaqi
							+ "\nismyselfok=" + myselfok + "\nduifangok="
							+ duifangok);
					if (isgamestart) {
						xiaqie = e; // �������λ�ø�ֵ��ȫ�ֱ���
						getmouse(xiaqie);
					} else {
						Cilent_talk.chatAear
								.append("\n---ϵͳ��Ϣ˫��������һ��û�п�ʼ��Ϸ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "��û���ӷ���������");
				}

			}

		};
		// ��������--------------��ʼ/��ͣ
		MouseAdapter start = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Cilent_talk.connect) {
					myselfok = true; // ��ʾ�Լ�׼����
					// ������Ϣ���Է�˵�Լ�׼������
					message = "ANNIU" + "-" + "CLIENT" + "-" + "START";
					sendmessage.send(message, Cilent_talk.socket);
					System.out.println("duifangok=" + duifangok + "myselfok="
							+ myselfok);
					if (!duifangok) {

						Cilent_talk.chatAear
								.append("\n---ϵͳ��Ϣ���Է���û��׼���ã���ȴ��Է���ʼ��Ϸ��---");
					} else if (duifangok) {
						isgamestart = true;
						Cilent_talk.chatAear
								.append("\n---ϵͳ��Ϣ��˫��׼����������Ϸ������ʼ,�Է����£���ȴ�������---");
					}
				} else {
					Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���������ӷ�������---");
				}
			}
		};
		//����
		MouseAdapter renshu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// System.out.println("renshu��갴��ȥ��");
				if (Cilent_talk.connect) {
					if (isgamestart) {

						if (myself_list.size() < 5) {
							Cilent_talk.chatAear
									.append("\n----ϵͳ��Ϣ���㻹û�ߵ���������Ͷ���ˣ�---");
						}
						// ������������
						myself_list.clear();
						duifang_list.clear();
						message = "ANNIU" + "-" + "CLIENT" + "-" + "TOUXIANG";
						sendmessage.send(message, Cilent_talk.socket);
						repaint();
					} else {
						Cilent_talk.chatAear.append("\n----ϵͳ��Ϣ���㻹û��ʼ��Ϸ��---");
					}
				} else {
					Cilent_talk.chatAear.append("\n----ϵͳ��Ϣ���㻹û�����ӷ���������---");
				}
			}
		};
		// ����
		MouseAdapter houtui = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Cilent_talk.connect) {
					if (isgamestart) {
						if (!ismyselfxiaqi && isduifangxiaqi) {
							Cilent_talk.chatAear
									.append("\n---ϵͳ���Լ������ʱ�򣬲��ܻ���---");
						} else {
							myself_list.remove(myself_list.get(myself_list
									.size() - 1));// ����
							isduifangxiaqi = true;
							ismyselfxiaqi = false;
							message = "ANNIU" + "-" + "CLIENT" + "-" + "HUIQI";
							sendmessage.send(message, Cilent_talk.socket);
							repaint();
						}
					} else {
						Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���㻹û��ʼ��Ϸ��---");
					}
				} else {
					Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���㻹û���ӷ���������---");
				}
			}
		};
		// ���¿�ʼ
		MouseAdapter chongxinkaishi = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Cilent_talk.connect) {
					if (isgamestart) {
						myself_list.clear();
						duifang_list.clear();
						message = "ANNIU" + "-" + "CLIENT" + "-"
								+ "CHONGXINLAI";
						sendmessage.send(message, Cilent_talk.socket);
						repaint();
					} else {
						Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ����Ϸ��δ��ʼ��--");
					}
				} else {
					Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ����������û������--");
				}
			}
		};
		//����
		MouseAdapter huiqi = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Cilent_talk.connect) {
					if (isgamestart) {
						if (!ismyselfxiaqi && isduifangxiaqi) {
							Cilent_talk.chatAear
									.append("\n---ϵͳ���Լ������ʱ�򣬲��ܻ���---");
						} else {
							myself_list.remove(myself_list.get(myself_list
									.size() - 1));// ����
							isduifangxiaqi = true;
							ismyselfxiaqi = false;
							message = "ANNIU" + "-" + "CLIENT" + "-" + "HUIQI";
							sendmessage.send(message, Cilent_talk.socket);
							repaint();
						}
					} else {
						Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���㻹û��ʼ��Ϸ��---");
					}
				} else {
					Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���㻹û���ӷ���������---");
				}
			}
		};
		//����
		MouseAdapter bofang = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
					if(Cilent_talk.connect&&isconnect) {
						Client_frame.play();
					}
					else {
						Client_frame.play();
						Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���㻹û���ӷ���������---");
					}
			}
			
		};
		//����ʱ��
		/*MouseAdapter khdsjxz = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Cilent_talk.connect) {
					//run();
					Scanner input = new Scanner(System.in);
					String sj = input.nextLine();
					JOptionPane.showMessageDialog(null,"�����뵹��ʱʱ��", sj, cou);
				
					
					
				}
				

				else {
						Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���㻹û���ӷ���������---");
					}
			}
			
		};*/
		this.addMouseListener(mouse);
		ks.addMouseListener(start); // ��ʼ��ť���������
		rs.addMouseListener(renshu); // ���䰴ť���������
		ht.addMouseListener(houtui); // ���˰�ť���������
		cxks.addMouseListener(chongxinkaishi);
		hq.addMouseListener(huiqi); // ���尴ť���������
		bf.addMouseListener(bofang); // ���Ű�ť���������
		//khd.addMouseListener(khdsjxz); // �ͻ���ʱ�����ð�ť���������
		

		
	}

	

	/*
	 * 
	 * ��ȡ���λ�� �������λ�õ���������
	 */

	public void getmouse(MouseEvent e) {

		System.out.println("��ոյ���һ�¿ͻ��˵����");
		for (int i = 0; i < Client_qipan.duifang_list.size(); i++) {
			System.out.println();
			System.out.println("�������֮��ʹ�ӡ�Է�������������Ķ���"
					+ Client_qipan.duifang_list.get(i)); // ��ü�������ĵ����ַ���
		}
		System.out.println("��ӡ���������������֮��ismyselfxiaqi=" + ismyselfxiaqi
				+ "===" + "isduifangxiaqi" + isduifangxiaqi);
		//
		if (ismyselfxiaqi||!isduifangxiaqi) {
			 JOptionPane.showMessageDialog(null, "��ȴ��Է����꣬�ټ�������");
			 }
		if (!ismyselfxiaqi && isduifangxiaqi) {
			
			int x = e.getX();
			int y = e.getY();
				
			// ��ȥ�߾࣬���Ը��ӵĿ�ȣ�ȡ�����õ��ڼ�����
			getx = (x - 18) / 25;
			gety = (y - 18) / 25;
			if ((x - 18) % 25 > 16)
				getx = getx + 1;
			if ((y - 18) % 25 > 16)
				gety = gety + 1;
			/*********************************/        
			//�����Ƿ񳬳����̱߽�
			if(getx < 0 || getx> 16||gety < 0 || gety > 16)
			        {
					     JOptionPane.showMessageDialog(this, "�������̱߽�");
					    return;
			        }
			/******************************************/
			// �Ѹ��ӵ�x�����y�������ַ����ö������ӣ����浽list����
			if (!myself_list.contains(getx + "," + gety)&& !duifang_list.contains(getx + "," + gety)) {
				myself_list.add(getx + "," + gety);
				// �Լ������˾Ͳ����£�Ҫ�ȶԷ�����֮�������
				ismyselfxiaqi = true; // �Լ�Ϊfalse��ʾ�Լ�û�£����Լ���
				isduifangxiaqi = false; // �Է�Ϊtrue����ʾ�Է�������
				// ���µ����ӷ��͸��Է�
				message = "XIAQI" + "-" + getx + "," + gety + "-" + "CLIENT";
				System.out.println("�����������꣺" + message);
				sendmessage.send(message, Cilent_talk
						.getCilent_panel_west_talk().socket);
			}
			repaint();
			
			//if (myself_list.size() > 5)
			baipanduan();
			//
		
		}
	}

	/*
	 * ������
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
		// ������ 
		    for (int i = 0; i <17; i++) 
		    { 
		      g.drawLine(16,16 + 26 * i, 433, 16 + 26 * i); 
		    } 
		    //g.drawLine(40, 400, 400, 400); 
		    for (int i = 0; i < 17; i++) 
		    { 
		      g.drawLine(16 + 26 * i, 16, 16 + 26 * i, 433); 
		    } 
		   // g.drawLine(400, 40, 400, 400); ����
		    g.fillOval(65, 65, 6, 6); 
		    g.fillOval(377, 65, 6, 6); 
		    g.fillOval(65, 377, 6, 6); 
		    g.fillOval(377, 377, 6, 6); 
		    g.fillOval(221, 221, 6, 6); 
		// ���Լ�
		for (int i = 0; i < myself_list.size(); i++) {
			String s = myself_list.get(i); // ��ü�������ĵ����ַ���
			String[] a = s.split(",");
			getx = Integer.parseInt(a[0]);//Integer����int��String��ת��
			gety = Integer.parseInt(a[1]);
			g.drawImage(white, getx * 26 + 18-10, gety * 26 + 18-10, null);
		}
		// ���Է�
		for (int i = 0; i < duifang_list.size(); i++) {
			String s = duifang_list.get(i); // ��ü�������ĵ����ַ���
			String[] b = s.split(",");
			getx = Integer.parseInt(b[0]);
			gety = Integer.parseInt(b[1]);
			g.drawImage(black, getx * 26 + 18-10, gety * 26 + 18-10,null);//26 18-16
		}
	}
	// ��Ӯ�жϵķ���
	public void baipanduan() {
		 //if (h_list.size() > 5) {
		 // �������ٵ������֮���ִ��}
		// f�ж��ǰ��廹�Ǻ���,true��ʾ����,false��ʾ����
		for (int i = 0; i < myself_list.size(); i++) {
			cou = 0; // ���ж�ÿһ�����ӵ�ʱ�򣬶�Ҫ��cou��ֵΪ0
			String s = myself_list.get(i); // ��ü�������ĵ����ַ���
			String[] a = s.split(",");
			hgex = Integer.parseInt(a[0]);
			int tempx = hgex;
			hgey = Integer.parseInt(a[1]);
			int tempy = hgey;
			// �����ж�
			// �����ĳ����x����+1��Y���䣬��ʾ�����ж���û����������
			//��������
			for (int j = 0; j < 4; j++) {
				hgex++;
				if (myself_list.contains(hgex + "," + hgey)) {
					cou++;
				}
			}
//			//��������
//			for (int j = 0; j < 4; j++) {
//				hgex--;
//				if (myself_list.contains(hgex + "," + hgey)) {
//					cou++;
//				}
//			}
			// �����ж���
			if (cou < 4) {
				hgex = tempx;
				hgey = tempy;
				cou = 0;
				for (int j = 0; j < 4; j++) {
					hgey++;
					if (myself_list.contains(hgex + "," + hgey))
						cou++;
				}
			}
			// �����ж���
//			if (cou < 4) {
//				hgex = tempx;
//				hgey = tempy;
//				cou = 0;
//				for (int j = 0; j < 4; j++) {
//					hgey--;
//					if (myself_list.contains(hgex + "," + hgey))
//						cou++;
//				}
//			}
			// ���Ϸ����ж�
//			if (cou < 4) { // С��4��ʾ����û�����ӣ��Ͱ�couֵ��Ϊ0�����¿�ʼ
//				cou = 0;
//				hgex = tempx;
//				hgey = tempy;
//				for (int j = 0; j < 4; j++) {
//					hgey--;
//					hgex--;
//					if (myself_list.contains(hgex + "," + hgey))
//						cou++;
//				}
//			}
//			// ���·����ж�
//			if (cou < 4) { // С��4��ʾ����û�����ӣ��Ͱ�couֵ��Ϊ0�����¿�ʼ
//				cou = 0;
//				hgex = tempx;
//				hgey = tempy;
//				for (int j = 0; j < 4; j++) {
//					hgey++;
//					hgex--;
//					if (myself_list.contains(hgex + "," + hgey))
//						cou++;
//				}
//			}
			// ���Ϸ����ж�
			if (cou < 4) { // С��4��ʾ����û�����ӣ��Ͱ�couֵ��Ϊ0�����¿�ʼ
				cou = 0;
				hgex = tempx;
				hgey = tempy;
				for (int j = 0; j < 4; j++) {
					hgey--;
					hgex++;
					if (myself_list.contains(hgex + "," + hgey))
						cou++;
				}
			}
			// �����ж�
			if (cou < 4) {
				cou = 0;
				hgex = tempx;
				hgey = tempy;
				for (int j = 0; j < 4; j++) {
					hgey++;
					hgex++;
					if (myself_list.contains(hgex + "," + hgey))
						cou++;
				}
			}
			
			if (cou == 4) {
				JOptionPane.showMessageDialog(null, "��ϲ����Ӯ��");
				isgamestart = false;
				// ���Է������Լ�Ӯ����Ϣ

				message = "XIAQI" + "-" + "WIN" + "-" + "SERVER";
				sendmessage.send(message, Server_frame.socket);

			}
//			if (cou == 4) {
//				/*isgamestart = false;
//				JOptionPane.showMessageDialog(null, "��ϲ����Ӯ��");
//				message = "XIAQI" + "-" + "WIN"+ "-" + "CLIENT";
//				sendmessage.send(message, Server_frame.socket);
//				isgamestart = false;*/
		}
	}
}
