import java.io.*;
import java.applet.Applet; 
import java.awt.*;
import javax.swing.*;


public class JavaPingyp extends JApplet{

	public boolean isWindows(){
		 
		String os=System.getProperty("os.name").toLowerCase();
	    return (os.indexOf( "win" )>=0); 
 
	}

	
	public void pause(int seconds) throws InterruptedException{
		Thread.sleep(seconds*1000);
	}
	
	public void start(){
	}
	public void stop(){
	}
	
	public void paint(Graphics g){
		
		
		String string,cmd;	
		int x=0;
		double latency=0;
		if (isWindows())
			cmd="ping -n 1 yahoo.com";
		else
			cmd="ping -c 1 yahoo.com";
		
		while (x<1){
			try{
				Process ping= Runtime.getRuntime().exec(cmd);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(ping.getInputStream()));
			
			 	while ((string=stdInput.readLine()) != null){
	                	if (string.indexOf("time=")>-1){
	                		String strlatencyA= (string.substring(string.indexOf("time=")));
	                		latency= Double.parseDouble(strlatencyA.substring((strlatencyA.indexOf('=')+1),(strlatencyA.indexOf(' '))));
	                		break;
	                	}
	                }
			}
			catch (IOException e) {
            	x=1;
				System.out.println("exception happened - here's what I know: ");
            	e.printStackTrace();
            	System.exit(-1);
        	}
			
			g.clearRect(getX(), getY(), getWidth(), getHeight());
			if (latency<75)
				g.setColor(Color.GREEN);
			if ((latency>75)&&(latency<100))
				g.setColor(Color.YELLOW);
			if (latency>100)
				g.setColor(Color.RED);
			
			g.drawString(latency+" ms",20,20);
	     	
			try{
				pause(3);
			}
			catch (InterruptedException f){
				System.out.println("exception happened - here's what I know: ");
            	f.printStackTrace();
            	System.exit(-1);
			}
		}
    } 
	
	public void init(){
		
		
	}
}
