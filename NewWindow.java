package ������;


import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;
public class NewWindow extends JFrame {
	JButton sendBt;

	JTextField inputField;

	JTextArea chatContent;
	public NewWindow() {
		this.setSize(300,400);
		this.setTitle("���촰��");
		this.setVisible(true);
		this.setLayout(new BorderLayout());

		chatContent = new JTextArea(12, 34); // ����һ���ı���

		

		// ����һ��������壬���ı�����Ϊ����ʾ���

		JScrollPane showPanel = new JScrollPane(chatContent);

		

		chatContent.setEditable(false);    // �����ı��򲻿ɱ༭

		JPanel inputPanel = new JPanel(); // ����һ��JPanel���

		inputField = new JTextField(20);  // ����һ���ı���

		
/*****************************/
		Cilent_talk ct = new Cilent_talk();
	
		/********************************/
		sendBt = new JButton("����"); // ����һ�����Ͱ�ť

		// Ϊ��ť����¼�

		sendBt.addActionListener(new ActionListener() {// Ϊ��ť���һ�������¼�

			public void actionPerformed(ActionEvent e) {// ��дactionPerformed����

				String content = inputField.getText();// ��ȡ������ı���Ϣ

				// �ж��������Ϣ�Ƿ�Ϊ��

				if(content!=null && !content.trim().equals("")){//trim��ע��

					// �����Ϊ�գ���������ı�׷�ӵ������촰��

					chatContent.append("���ˣ�"+content+"\n");

				}

				else{// ���Ϊ�գ���ʾ������Ϣ����Ϊ��

					chatContent.append("������Ϣ����Ϊ��" + "\n");

				}

				inputField.setText("");// ��������ı���������Ϊ��

			}

		});

		

		Label label = new Label("������Ϣ"); // ����һ����ǩ

		inputPanel.add(label);  // ����ǩ��ӵ�JPanel���

		inputPanel.add(inputField); // ���ı�����ӵ�JPanel���

		inputPanel.add(sendBt);  // ����ť��ӵ�JPanel���

		

		

		// ����������JPanel�����ӵ�JFrame����

		this.add(showPanel,BorderLayout.CENTER);

		this.add(inputPanel,BorderLayout.SOUTH);

		this.setTitle("���촰��");

		this.setSize(400, 300);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}
	

    public static void main(String[] args) {

        new NewWindow();
       
    }	
  
	}

