package Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Window {

	private Frame frame;
	private TextField text;
	private TimerV2 timer;
	private Timer lp = new Timer();
	private Button go,res;
	private boolean flag;
	private String org="00:00";
	
	public Window() {
		frame = new Frame("Timer");
		text = new TextField("00:00");
		go = new Button("Go");
		res = new Button("Reset");
		frame.setBounds(50,50,240,80);
		frame.add(text);
		frame.add(go);
		frame.add(res);
		text.setBounds(20,40,100,20);
		setBeside(go,text);
		go.setSize(30,20);
		setBeside(res,go);
		res.setSize(50,20);
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				goAction();
			}
		});
		res.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (flag!=false)
					timerStop();
				text.setText(org);
				isNeg(Service.getSeconds(org)<0);
			}
			
		});
		text.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
					goAction();
				
			}
		});
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void goAction() {
		if (flag)
			timerStop();
		else
			timerStart();
	}
	
	private void timerStop() {
		lp.cancel();
		lp = new Timer();
		text.setEditable(true);
		flag=!flag;
	}
	
	private void timerStart() {
		String tmp = text.getText();
		if (tmp.isEmpty() || Service.isNAN(tmp))
			return;
		int sec = Service.getSeconds(tmp);
		flag=!flag;
		timer = new TimerV2(sec);
		org=timer.toString();
		text.setText(timer.toString());
		text.setEditable(false);
		isNeg();
		lp.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				timer.tickDown();
				isNeg();
				text.setText(timer.toString());
			}
			
		}, 1000, 1000);
	}
	
	public void isNeg() {
		if (timer.getS()<0) {
			text.setForeground(Color.white);
			text.setBackground(Color.gray);
		}
		else {
			text.setForeground(Color.black);
			text.setBackground(Color.white);
		}
	}
	
	public void isNeg(boolean flag) {
		if (flag) {
			text.setForeground(Color.white);
			text.setBackground(Color.gray);
		}
		else {
			text.setForeground(Color.black);
			text.setBackground(Color.white);
		}
	}
	
	private void setBeside(Component a,Component b) {
		a.setLocation(b.getX()+b.getWidth()+10,b.getY());
	}
	
}
