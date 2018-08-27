package ������;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Server_qipan extends JPanel {
	Image board, black, white,asd;
	int hgex, hgey; // ��������ı���
	int bgex, bgey; // ��������ı���
	int cou = 0; // ��ʾ�������ӵ�����

	Send_message sendmessage; // ���͵ķ���
	String message; // Ҫ���͵���Ϣ
	public static String control;
	public static boolean isgamestart = false; // ��Ϸ��ʼ����
	public static boolean myselfok = false; // �Լ�׼�����ˣ�����
	public static boolean duifangok = false; // �Է�׼�����ˣ���
	public static boolean ismyselfxiaqi = false; // �Լ������ˣ�����Ϊtrue��ʾ����false��ʾû�£����Լ�
	public static boolean isduifangxiaqi = true; // �Է�������???
	// Ϊtrue��ʾ�Է����ˣ�false��ʾû��
	private MouseEvent xiaqie;
	public static ArrayList<String> myself_list = new ArrayList<String>(); // ��
	public static ArrayList<String> duifang_list = new ArrayList<String>(); // ��

	public Server_qipan() {

		sendmessage = new Send_message(message, Cilent_talk.socket);

		this.setLayout(null); // ����
		JButton ks = new JButton("��ʼ/��ͣ");
		ks.setBounds(460, 30, 100, 25);
		this.add(ks);
		JButton rs = new JButton("����");
		rs.setBounds(460, 90, 100, 25);
		this.add(rs);
		JButton ht = new JButton("����");
		ht.setBounds(460, 150, 100, 25);
		this.add(ht);
		JButton cxks = new JButton("���¿�ʼ");
		cxks.setBounds(460, 210, 100, 25);
		this.add(cxks);
		//
		JButton s = new JButton("����");
		s.setBounds(460, 270, 100, 25);
		this.add(s);
		JButton khd = new JButton("�ͻ���ʣ��ʱ�䣺"+30);
		khd.setBounds(0, 457, 180,25);
		this.add(khd);
			
		JButton fwd = new JButton("�����ʣ��ʱ�䣺"+30);
		fwd.setBounds(270, 457, 180,25);
		this.add(fwd);

		hgex = 0;
		hgey = 0;
		bgex = 0;
		bgey = 0;
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
				if (Server_frame.isstart) {
//					System.out.println("\n-----\nisgamestart=" + isgamestart
//							+ "\nismyselfxiaqi=" + ismyselfxiaqi
//							+ "\nisduifangxiaqi=" + isduifangxiaqi
//							+ "\nismyselfok=" + myselfok + "\nduifangok="
//							+ duifangok);
					if (isgamestart) {
						xiaqie = e; // �������λ�ø�ֵ��ȫ�ֱ���
						getmouse(xiaqie);
					} else {
						Server_talk.chantAear
								.append("\n---ϵͳ��Ϣ˫��������һ��û�п�ʼ��Ϸ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȿ�������������");
				}
			}
		};
		// ��������--------------��ʼ/��ͣ
		MouseAdapter start = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// System.out.println("kaishi��갴��ȥ��");
				if (Server_frame.isstart) {
					myselfok = true; // ��ʾ�Լ�׼����
					// ������Ϣ���Է�˵�Լ�׼������
					message = "ANNIU" + "-" + "SERVER" + "-" + "START";
					sendmessage.send(message, Server_frame.socket);
					System.out.println("duifangok=" + duifangok + "myselfok="
							+ myselfok);
					if (!duifangok) {
						Server_talk.chantAear
								.append("\n---ϵͳ��Ϣ���Է���û�е����ʼ����ȴ�---");
					} else{
						isgamestart = true;
						Server_talk.chantAear
								.append("\n---˫��׼����������Ϸ������ʼ������������---");
					}

				} else {
					Server_talk.chantAear
							.append("\n---ϵͳ��Ϣ����������û���������ȿ���������---");
				}
			}
		};
		MouseAdapter renshu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
					if (isgamestart) {
						// System.out.println("renshu��갴��ȥ��");
						myself_list.clear();
						duifang_list.clear();
						message = "ANNIU" + "-" + "SERVER" + "-" + "TOUXIANG";
						sendmessage.send(message, Server_frame.socket);
						repaint();
					} else {
						Server_talk.chantAear.append("\n---ϵͳ��Ϣ����Ϸ��û��ʼ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȿ�������������");
				}
			}
		};
		// ����
		MouseAdapter houtui = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
					if (isgamestart) {
						if (!ismyselfxiaqi && isduifangxiaqi) {
							Server_talk.chantAear
									.append("\n---ϵͳ��Ϣ�����Լ������ʱ�򣬲��ܻ���---");
						} else {

							ismyselfxiaqi = false;
							isduifangxiaqi = true;
							myself_list.remove(myself_list.get(myself_list
									.size() - 1));// ����
							Server_talk.chantAear
									.append("\n---ϵͳ��Ϣ���Է�����һ���壡---");
							message = "ANNIU" + "-" + "SERVER" + "-" + "HUIQI";
							sendmessage.send(message, Server_frame.socket);
							repaint();
						}
					} else {
						Server_talk.chantAear.append("\n---ϵͳ��Ϣ����Ϸ��û��ʼ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȿ�������������");
				}

			}
		};
		// ���¿�ʼ
		MouseAdapter chongxinkaishi = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
					if (isgamestart) {
						myself_list.clear();
						duifang_list.clear();
						message = "ANNIU" + "-" + "SERVER" + "-"
								+ "CHONGXINLAI";
						sendmessage.send(message, Server_frame.socket);
						repaint();
					} else {
						Server_talk.chantAear.append("\n---ϵͳ��Ϣ����Ϸ��û��ʼ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȿ�������������");
				}
			}
		};
		this.addMouseListener(mouse);
		cxks.addMouseListener(chongxinkaishi);
		ks.addMouseListener(start); // ��ʼ��ť���������
		rs.addMouseListener(renshu); // ���䰴ť���������
		ht.addMouseListener(houtui); // ���˰�ť���������

	}

	/*
	 * ��ȡ���λ�� �������λ�õ���������
	 */
	public void getmouse(MouseEvent e) {
		//
		if (ismyselfxiaqi||!isduifangxiaqi) {
			JOptionPane.showMessageDialog(null, "��ȴ��Է����꣬�ټ�������");
	 }
		if (!ismyselfxiaqi && isduifangxiaqi) {
			// isgamestart��ʾ��Ϸ��ʼ��������������������
			// if(isgamestart){
			int x = e.getX();
			int y = e.getY();
			// ��ȥ�߾࣬���Ը��ӵĿ�ȣ�ȡ�����õ��ڼ�����
			hgex = (x - 18) / 25;
			hgey = (y - 18) / 25;
			if ((x - 18) % 25 > 16)
				hgex = hgex + 1;
			if ((y - 18) % 25 > 16)
				hgey = hgey + 1;
			/*********************************/        
			//�����Ƿ񳬳����̱߽�
			if(hgex < 0 || hgex> 16||hgey < 0 || hgey > 16)
			        {
					     JOptionPane.showMessageDialog(this, "�������̱߽�");
					    return;
			        }
			/******************************************/
			// �Ѹ��ӵ�x�����y�������ַ����ö������ӣ����浽list����
			if (!myself_list.contains(hgex + "," + hgey)
					&& !duifang_list.contains(hgex + "," + hgey)) {
				myself_list.add(hgex + "," + hgey);
				// �Լ������˾Ͳ����£�Ҫ�ȶԷ�����֮�������
				ismyselfxiaqi = true; // �Լ�Ϊfalse��ʾ�Լ�û�£����Լ���
				isduifangxiaqi = false; // �Է�Ϊtrue����ʾ�Է�������
				// ���µ����ӷ��͸��Է�
				message = "XIAQI" + "-" + hgex + "," + hgey + "-" + "SERVER";
				System.out.println("�����������꣺" + message);
				sendmessage.send(message, Server_frame.socket);
			}
			repaint();
			// if (myself_list.size() >= 5) {
			heipanduan();
			// }

		}
		 
	}

	/*
	 * 
	 * ������
	 */
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
		//
		for (int i = 0; i < 17; i++)// ��������
		{
			 g.drawLine(16, 16 + 26 * i, 433, 16 + 26 * i);// heng ��
		}
		 for (int i = 0; i < 17; i++) {
			 g.drawLine(16 + 26 * i, 16, 16 + 26 * i, 433);// cong ��
		}
		 	g.fillOval(65, 65, 6, 6); 
		    g.fillOval(377, 65, 6, 6); 
		    g.fillOval(65, 377, 6, 6); 
		    g.fillOval(377, 377, 6, 6); 
		    g.fillOval(221, 221, 6, 6); 
		// �����ȡ�����λ��,������
		for (int i = 0; i < myself_list.size(); i++) {
			String s = myself_list.get(i); // ��ü�������ĵ����ַ���
			String[] a = s.split(",");
			hgex = Integer.parseInt(a[0]);
			hgey = Integer.parseInt(a[1]);
			g.drawImage(black, hgex * 26 + 18 - 10, hgey * 26 + 18 - 10, null);
		}
		// �����ȡ�����λ�ã�������
		for (int i = 0; i < duifang_list.size(); i++) {
			String s = duifang_list.get(i); // ��ü�������ĵ����ַ���
			String[] b = s.split(",");
			bgex = Integer.parseInt(b[0]);
			bgey = Integer.parseInt(b[1]);
			g.drawImage(white, bgex * 26 + 18 - 10, bgey * 26 + 18 - 10, null);
		}
	}

	// ��Ӯ�жϵķ���,���ж�
	public void heipanduan() {
		// if (h_list.size() > 5) {// �������ٵ������֮���ִ��
		// f�ж��ǰ��廹�Ǻ���,true��ʾ����,false��ʾ����
		// �����ж���Ӯ
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
			for (int j = 0; j < 4; j++) {
				hgex++;
				if (myself_list.contains(hgex + "," + hgey)) {
					cou++;
				}
			}
			// �����ж�
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
		}
	}

}
