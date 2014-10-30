package clocktimer.com;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.util.Date;
import java.util.Timer;
import java.lang.Thread;

public class MainActivity extends Activity {
	private Chronometer txtClock;
	private TextView txttime1;
	private TextView txttime2;
	ClockThread TimerThread = new ClockThread();
	ClockThread TimerThread2 = new ClockThread();
	Handler TimerHandler1;
	//Boolean flagStart1 = false;
	//Boolean flagStart2 = false;
	private int Sec=0, TSec1=0, TMin1=0;
	private int Sec2=0, TSec2=0, TMin2=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtClock = (Chronometer)findViewById(R.id.Clock);
        txttime1 = (TextView)findViewById(R.id.Timer1);
        txttime2 = (TextView)findViewById(R.id.Timer2);
        txtClock.setOnChronometerTickListener(chrolisten);
        txtClock.start();     
    }
    
    private Button.OnClickListener listener = new Button.OnClickListener()
    {
    	public void onClick(View v)
    	{
    		switch(v.getId())
    		{
	    		case R.id.BtnStart1:
	    			TimerHandler1.post(TimerThread);
	    			break;
	    		case R.id.BtnStop1:
	    			TimerHandler1.removeCallbacks(TimerThread);
	    			break;
	    		case R.id.Btnzero1:
	    			Sec = 0;
	    			txttime1.setText("00 : 00");
	    			break;
	    		case R.id.BtnStart2:
	    			TimerHandler1.post(TimerThread2);
	    			break;
	    		case R.id.BtnStop2:
	    			TimerHandler1.removeCallbacks(TimerThread2);
	    			break;
	    		case R.id.BtnZero2:
	    			Sec2 = 0;
	    			txttime2.setText("00 : 00");
	    			break;
	    		case R.id.BtnExit:
	    			finish();
    		}
    	}
    };
    

    
    public class ClockThread extends Thread{
    	public void run() {
    		Sec++;
    		TSec1 = Sec%60;
    		TMin1 = Sec/60;
    		String str = "";
    		
    		if(TMin1<10)	str = "0" + TMin1;
    		else	str = "" + TMin1;
    		
    		if(TSec1<10)	str = str + " : 0" + TSec1;
    		else	str = str + " : " + TSec1;
    		
    		txttime1.setText(str);
    		TimerHandler1.postDelayed(TimerThread, 1000);
    	}
	}	
    

    
    public class ClockThread2 extends Thread{
    	public void run() {
    		Sec2++;
    		TSec2 = Sec2%60;
    		TMin2 = Sec2/60;
    		String str = "";
    		
    		if(TMin2<10)	str = "0" + TMin2;
    		else	str = "" + TMin1;
    		
    		if(TSec2<10)	str = str + " : 0" + TSec2;
    		else	str = str + " : " + TSec2;
    		
    		txttime2.setText(str);
    		TimerHandler1.postDelayed(TimerThread2, 1000);
    	}
	}	
    
    private Chronometer.OnChronometerTickListener chrolisten=new 
    		Chronometer.OnChronometerTickListener() {
				@Override
				public void onChronometerTick(Chronometer chronometer) {
					// TODO Auto-generated method stub
					SimpleDateFormat sformat=new SimpleDateFormat("HH:mm:ss");
					txtClock.setText(sformat.format(new Date()));
					
				}
	};
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
