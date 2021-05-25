package Timer;

public class TimerV2 {
	
	private int s;


	public TimerV2(int m,int s) {
		setS(s+m*60);
	}
	
	public TimerV2(int s) {
		setS(s);
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getM() {
		return s/60;
	}
	
	public void tickDown() {
		s--;
	}
	
	public TimerV2 diff(TimerV2 other) {
		int res = this.getS()-other.getS();
		return res>=0? new TimerV2(res):null;
	}

	public String toString() {
		int num = Math.abs(s);
		return String.format("%s%02d:%02d", s<0? "-":"",num/60,num%60);
	}
}
