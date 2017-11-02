# TestAlarmManager
AlarmManager介绍：
AlarmManager是Android中常用的一种系统级别的提示服务，在特定的时刻为我们广播一个指定的Intent。简单的说就是我们设定一个时间，然后在该时间到来时，AlarmManager为我们广播一个我们设定的Intent,通常我们使用 PendingIntent，PendingIntent可以理解为Intent的封装包，简单的说就是在Intent上在加个指定的动作。在使用Intent的时候，我们还需要在执行startActivity、startService或sendBroadcast才能使Intent有用。而PendingIntent的话就是将这个动作包含在内了。
定义一个PendingIntent对象。
PendingIntent pi = PendingIntent.getBroadcast(this,0,intent,0);
2、AlarmManager的常用方法有三个：
（1）set(int type，long startTime，PendingIntent pi)；
该方法用于设置一次性闹钟，第一个参数表示闹钟类型，第二个参数表示闹钟执行时间，第三个参数表示闹钟响应动作。
（2）setRepeating(int type，long startTime，long intervalTime，PendingIntent pi)；
该方法用于设置重复闹钟，第一个参数表示闹钟类型，第二个参数表示闹钟首次执行时间，第三个参数表示闹钟两次执行的间隔时间，第三个参数表示闹钟响应动作。
（3）setInexactRepeating（int type，long startTime，long intervalTime，PendingIntent pi）；
该方法也用于设置重复闹钟，与第二个方法相似，不过其两个闹钟执行的间隔时间不是固定的而已。
3、三个方法各个参数详悉：
（1）int type： 闹钟的类型，常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。
AlarmManager.ELAPSED_REALTIME表示闹钟在手机睡眠状态下不可用，该状态下闹钟使用相对时间（相对于系统启动开始），状态值为3；
AlarmManager.ELAPSED_REALTIME_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟也使用相对时间，状态值为2；
AlarmManager.RTC表示闹钟在睡眠状态下不可用，该状态下闹钟使用绝对时间，即当前系统时间，状态值为1；
AlarmManager.RTC_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用绝对时间，状态值为0；
AlarmManager.POWER_OFF_WAKEUP表示闹钟在手机关机状态下也能正常进行提示功能，所以是5个状态中用的最多的状态之一，该状态下闹钟也是用绝对时间，状态值为4；不过本状态好像受SDK版本影响，某些版本并不支持；
（2）long startTime： 闹钟的第一次执行时间，以毫秒为单位，可以自定义时间，不过一般使用当前时间。需要注意的是，本属性与第一个属性（type）密切相关，如果第一个参数对 应的闹钟使用的是相对时间（ELAPSED_REALTIME和ELAPSED_REALTIME_WAKEUP），那么本属性就得使用相对时间（相对于 系统启动时间来说），比如当前时间就表示为：SystemClock.elapsedRealtime()；如果第一个参数对应的闹钟使用的是绝对时间 （RTC、RTC_WAKEUP、POWER_OFF_WAKEUP），那么本属性就得使用绝对时间，比如当前时间就表示 为：System.currentTimeMillis()。
（3）long intervalTime：对于后两个方法来说，存在本属性，表示两次闹钟执行的间隔时间，也是以毫秒为单位。
（4）PendingIntent pi： 绑定了闹钟的执行动作，比如发送一个广播、给出提示等等。PendingIntent是Intent的封装类。需要注意的是，如果是通过启动服务来实现闹钟提 示的话，PendingIntent对象的获取就应该采用Pending.getService(Context c,int i,Intent intent,int j)方法；如果是通过广播来实现闹钟提示的话，PendingIntent对象的获取就应该采用 PendingIntent.getBroadcast(Context c,int i,Intent intent,int j)方法；如果是采用Activity的方式来实现闹钟提示的话，PendingIntent对象的获取就应该采用 PendingIntent.getActivity(Context c,int i,Intent intent,int j)方法。如果这三种方法错用了的话，虽然不会报错，但是看不到闹钟提示效果。
     
属性或方法名称	说明                                   
 ELAPSED_REALTIME  	设置闹钟时间，从系统启动开  始    
ELAPSED_REALTIME_WAKEUP	同上，如果设备休眠则唤醒
INTERVAL_DAY	设置闹钟，间隔一天
INTERVAL_HALF_DAY	设置闹钟，间隔半天
INTERVAL_FIFTEEN_MINUTES	设置闹钟，间隔15分钟
INTERVAL_HALF_HOUR	设置闹钟，间隔半个小时
INTERVAL_HOUR	设置闹钟，间隔一个小时
RTC	设置闹钟时间从系统当前时间开（System.currentTimeMillis()）
RTC_WAKEUP	同上，设备休眠则唤醒
set(int type,long triggerAtTime,PendingIntent operation)	设置在某个时间执行闹钟
setRepeating(int type,long triggerAtTime,long interval PendingIntent operation)	设置在某个时间重复执行闹钟
setInexactRepeating(int type,long triggerAtTime,long interval PendingIntent operation)	
设置在某个时间重复执行闹钟，但不是间隔固定时间
注意：
设置闹钟使用AlarmManager.set()函数，它的triggerAtTime参数，如果要用Calendar.getTimesInMillis()获得，就必须先设置Calendar对象，例如要让闹钟在当天的16:30分启动，就要设置HOUR_OF_DAY（16）、MINUTE（30）、MILLISECOND（0），特别是HOUR_OF_DAY，我一开始误用了HOUR，这是12进制计时方法，HOUR_OF_DAY是24进制计时方法。
针对同一个PendingIntent，AlarmManager.set()函数不能设置多个alarm。调用该函数时，假如已经有old alarm使用相同的PendingIntent，会先取消（cancel）old alarm，然后再设置新的alarm。如何判断是否已经有相同的PendingIntent，请看下条。
取消alarm使用AlarmManager.cancel()函数，传入参数是个PendingIntent实例。该函数会将所有跟这个PendingIntent相同的Alarm全部取消，怎么判断两者是否相同，android使用的是intent.filterEquals()，具体就是判断两个PendingIntent的action、data、type、class和category是否完全相同。
在AndroidManifest.xml中静态注册BroadcastReceiver时，一定使用android：process=":xxx"属性，因为SDK已注明：If the name assigned to this attribute begins with a colon (':'), a new process, private to the application, is created when it's needed and the broadcast receiver runs in that process.
在此讨论一下process属性，它规定了组件(activity, service, receiver等)所在的进程。

　　通常情况下，没有指定这个属性，一个应用所有的组件都运行在应用的默认进程中，进程的名字和应用的包名一致。

　　比如manifest的package="com.example.helloalarm"，则默认进程名就是com.example.helloalarm。

　　<application>元素的process属性可以为全部的组件设置一个不同的默认进程。

　　组件可以override这个默认的进程设置，这样你的应用就可以是多进程的。

 

　　如果你的process属性以一个冒号开头，进程名会在原来的进程名之后附加冒号之后的字符串作为新的进程名。当组件需要时，会自动创建这个进程。这个进程是应用私有的进程。

　　如果process属性以小写字母开头，将会直接以属性中的这个名字作为进程名，这是一个全局进程，这样的进程可以被多个不同应用中的组件共享。



使用：
AlarmManager的使用步骤
1）获得ALarmManager实例 ALarmManager am=(ALarmManager)getSystemService(ALARM_SERVICE);
2）定义一个PendingIntent发出广播
3）调用ALarmManager方法，设置定时或重复提醒
4）取消提醒：
取消alarm使用AlarmManager.cancel()函数，传入参数是个PendingIntent实例。

该函数会将所有跟这个PendingIntent相同的Alarm全部取消，怎么判断两者是否相同，android使用的是intent.filterEquals()，具体就是判断两个PendingIntent的action、data、type、class和category是否完全相同。

例如：
启动提醒：
// 指定启动AlarmActivity组件
                                Intent intent = new Intent(AlarmTest.this,
                                        AlarmActivity.class);
                                intent.setAction("111111");
                                // 创建PendingIntent对象
                                PendingIntent pi = PendingIntent.getActivity(
                                        AlarmTest.this, 0, intent, 0);
                                Calendar c = Calendar.getInstance();
                                // 根据用户选择时间来设置Calendar对象
                                System.out.println("hourOfDay = " + hourOfDay);
                                System.out.println("minute = " + minute);
                                c.set(Calendar.HOUR, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                // 设置AlarmManager将在Calendar对应的时间启动指定组件
                                aManager.set(AlarmManager.RTC_WAKEUP,
                                        c.getTimeInMillis(), pi);
取消提醒：对应的action必须要一样
 //用于取消的
aManager= (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(AlarmTest.this, AlarmActivity.class);        intent.setAction("111111");        // 创建PendingIntent对象        PendingIntent pendingIntent = PendingIntent.getActivity(                AlarmTest.this, 0, intent, 0);        aManager.cancel(pendingIntent);


案例一：实现6后秒提醒一次的功能：
下面的代码写在Activity或Service里面都行
[html] view plain copy
AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);//获取AlarmManager实例  
        int anHour =  6 * 1000 ;  // 6秒  
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;  
        Intent intent2 = new Intent(this, AlarmReceiver.class);  
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent2, 0);  
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);//开启提醒  
定义广播接收器：
[html] view plain copy
public class AlarmReceiver extends BroadcastReceiver {  
  
    @Override  
    public void onReceive(Context context, Intent intent) {  
  
        Toast.makeText(context, "收到定时广播", 1).show();  
        Intent i = new Intent(context, LongRunningService.class);  
        context.startService(i);  
  
    }  
  
}  
在配置文件中注册
注意：这里的process一定要写，内容貌似可以随便写
如果设置定时器的进程被杀死之后，定时器事件就不会触发。而在Android中，系统在需要时会自动终止后台进程，因此在定时过程中，进程被杀死的可能性是非常之大的，特别是在一些内存较少的设备中，基本上后台进程所设置的定时器很难被触发。
为了让定时器在进程被终止后还能触发，需要对上述实现做一个小的修改：在AndroidMefest.xml中如下定义广播接收类：
[html] view plain copy
<receiver android:name=".MyReceiver" android:process=":newinst">  
</receiver>  

[html] view plain copy
<receiver android:name=".AlarmReceiver"android:process=":remote" >  
        </receiver>  
案例二：定时提醒功能（提醒一次）。实现原理与上面的一样，只是计算出了指定的时刻到现在的时间差
补充：Calendar的使用方法：
Calendar c=Calendar.getInstance();
      c.set(Calendar.YEAR,2016);
      c.set(Calendar.MONTH,04);//也可以填数字，0-11,一月为0
      c.set(Calendar.DAY_OF_MONTH, 26);
      c.set(Calendar.HOUR_OF_DAY, 21);
      c.set(Calendar.MINUTE, 40);
      c.set(Calendar.SECOND, 0);
//设定时间为 2011年6月28日19点50分0秒
//c.set(2011, 05,28, 19,50, 0);
Calendar myCal1 = Calendar.getInstance();  
long     nowTime  = myCal1.getTimeInMillis();//这是当前的时间  
Calendar myCal2 = Calendar.getInstance();  
myCal2.set(Calendar.HOUR_OF_DAY,8);  
myCal2.set(Calendar.MINUTE,12);  
myCal2.set(2016, 03, 27, 9, 40);//日期要减去一，比如：你要设置4月几号几点提醒，这里要填写03，它默认是从0开始
long shutDownTime = myCal2.getTimeInMillis();   
long d = shutDownTime - nowTime;
System.out.println(shutDownTime+"-"+nowTime+"=" +d);
[html] view plain copy
Calendar myCal1 = Calendar.getInstance();    
        long     nowTime  = myCal1.getTimeInMillis();//这是当前的时间    
        Calendar myCal2 = Calendar.getInstance();    
        //      myCal.set(Calendar.HOUR_OF_DAY,hour);    
        //      myCal.set(Calendar.MINUTE,minutes);    
        myCal2.set(2016, 03, 27, 11, 49);  
        long    shutDownTime = myCal2.getTimeInMillis();     
  
  
        Intent intent3=new Intent(this,AlarmReceiver2.class);  
        intent3.putExtra("time", shutDownTime-nowTime+"");  
        PendingIntent pi3=PendingIntent.getBroadcast(this, 0, intent3,0);  
        //设置一个PendingIntent对象，发送广播  
        AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);  
        //获取AlarmManager对象  
        long triggerAtTime = SystemClock.elapsedRealtime() + shutDownTime-nowTime;  
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime , pi3);  
案例三：重复提醒功能：5秒后提醒第一次，以后每5秒提醒一次

[html] view plain copy
//重复提醒  
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5*1000+ SystemClock.elapsedRealtime(), 5*1000, pi3);  

终极案例：设置每天8：00提醒
[html] view plain copy
package activity.MyWeather;  
import java.util.Calendar;  
import java.util.TimeZone;  
  
import service.Alarm_Service;  
import android.app.Activity;  
import android.app.AlarmManager;  
import android.app.PendingIntent;  
import android.content.Intent;  
import android.os.Bundle;  
import android.os.SystemClock;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.Toast;  
  
public class AlarmActivity extends Activity implements OnClickListener{  
    private Button alarm_bt_YES;  
    private Button alarm_bt_quxiao;  
    AlarmManager am;  
    //PendingIntent pi;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_alarm);  
        alarm_bt_YES = (Button) findViewById(R.id.alarm_bt_YES);  
        alarm_bt_quxiao = (Button) findViewById(R.id.alarm_bt_quxiao);  
  
        alarm_bt_quxiao.setOnClickListener(this);  
        alarm_bt_YES.setOnClickListener(this);  
    }  
    @Override  
    public void onClick(View v) {  
        // TODO Auto-generated method stub  
        int id = v.getId();  
        if(id == R.id.alarm_bt_YES){  
  
            Toast.makeText(this, "已开启提醒", 0).show();  
  
            Intent intent=new Intent(this,Alarm_Service.class);  
            intent.setAction("activity.MyWeathe.alarm");  
            PendingIntent pi=PendingIntent.getService(this, 0, intent,0);  
  
            long firstTime = SystemClock.elapsedRealtime(); //获取系统当前时间   
            long systemTime = System.currentTimeMillis();//java.lang.System.currentTimeMillis()，它返回从 UTC 1970 年 1 月 1 日午夜开始经过的毫秒数。  
  
            Calendar calendar = Calendar.getInstance();  
            calendar.setTimeInMillis(System.currentTimeMillis());  
            calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); //  这里时区需要设置一下，不然会有8个小时的时间差  
            calendar.set(Calendar.MINUTE, 0);  
            calendar.set(Calendar.HOUR_OF_DAY, 8);//设置为8：00点提醒  
            calendar.set(Calendar.SECOND, 0);  
            calendar.set(Calendar.MILLISECOND, 0);  
            //选择的定时时间  
            long selectTime = calendar.getTimeInMillis();   //计算出设定的时间  
  
            //  如果当前时间大于设置的时间，那么就从第二天的设定时间开始  
            if(systemTime > selectTime) {  
                calendar.add(Calendar.DAY_OF_MONTH, 1);  
                selectTime = calendar.getTimeInMillis();  
            }  
  
            long time = selectTime - systemTime;// 计算现在时间到设定时间的时间差  
            long my_Time = firstTime + time;//系统 当前的时间+时间差  
  
            // 进行闹铃注册  
            am=(AlarmManager)getSystemService(ALARM_SERVICE);  
  
  
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, my_Time, AlarmManager.INTERVAL_DAY, pi);  
        }  
        else if(id == R.id.alarm_bt_quxiao){  
            Toast.makeText(this, "已关闭提醒", 0).show();  
[html] view plain copy
am = (AlarmManager)getSystemService(ALARM_SERVICE);  
            Intent intent=new Intent(this,Alarm_Service.class);  
            intent.setAction("activity.MyWeathe.alarm");  
            PendingIntent pi=PendingIntent.getService(this, 0, intent,0);  
            am.cancel(pi);  
        }  
  
    }  
  
}  

注意在Activity中取消alarm时
一定要再重新创建所有的对象包括：Intent,PendingIntent，AlarmManager对象
am = (AlarmManager)getSystemService(ALARM_SERVICE);
Intent intent=new Intent(AlarmActivity.this,Alarm_Service.class);


intent.setAction("activity.MyWeather.alarm");
pi=PendingIntent.getService(AlarmActivity.this, 0, intent,0);
am.cancel(pi);
因为在退出该Activity时或关闭该应用程序时，该Activity会被销毁里面的所有变量，全局变量都会被销毁。。。。
而在Service里面就不用这样每次都创建新的对象了，直接设置为成员变量（全局变量）就行了，因为它不会随着程序的退出而销毁
