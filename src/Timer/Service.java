package Timer;

public class Service {
	
	public static int getSeconds(String str) {
		boolean flag=false;
		if (str.charAt(0)=='-') {
			flag = true;
			str=str.substring(1);
		}
		int res=0,len=str.length();
		if (len<3)
			res+=Integer.parseInt(str);
		else if (len==3) {
			res+=Integer.parseInt(str.substring(0,1))*60;
			res+=Integer.parseInt(str.substring(1));
		}
		else {
			res+=Integer.parseInt(str.substring(0,2))*60;
			res+=Integer.parseInt(str.substring(len>3? 3:2));
		}
		if (!flag)
			return res;
		return res*-1;
	}
	
	public static boolean isNAN(String str) {
		if (str.charAt(0)=='-')
			str=str.substring(1);
		int res=0;
		for (char c:str.toCharArray())
			if (!Character.isDigit(c) && c!=':')
				return true;
			else if (Character.isDigit(c))
				res++;
		if (str.indexOf(':')==str.lastIndexOf(':'))
			return !(res!=0);
		return true;
	}

}
