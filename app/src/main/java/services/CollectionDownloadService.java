package services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.englishapp_bishe.HomeActivity;
import com.example.englishapp_bishe.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import commonparms.Commons;
import entirys.Words;
import utils.LogUtil;

public class CollectionDownloadService extends Service {
    private static final String TAG = "CollectionDownloadService";
    private static final String CHANNE_ID = "My Collection Download Channe ID";
    private String mCurrentTitle;
    private NotificationManager mNotificationManager;


    public CollectionDownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //获取收藏夹的名称
        String name = intent.getStringExtra("name");
        this.mCurrentTitle= name ;
        new Thread(new Runnable() {
            @Override
            public void run() {

                doDownWordInCollection();
            }
        }).start();

        createChanneID();

        createNotify();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createChanneID() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNE_ID, "收藏夹下载服务", importance);
            notificationChannel.setDescription("下载各自的收藏夹中的单词");
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    private void createNotify() {
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pdIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification notify = new NotificationCompat.Builder(this, CHANNE_ID)
                .setContentText("下载已完成")
                .setContentTitle(mCurrentTitle)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_background)
              //  .setContentIntent(pdIntent)
                .build();
        mNotificationManager.notify(1,notify);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(0,notify, ServiceInfo.FOREGROUND_SERVICE_TYPE_NONE);
        }else {
            startForeground(0,notify);
        }
    }

    private void doDownWordInCollection() {
        //创建下载的文件
        long start = System.currentTimeMillis();
        File externFile = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File collectionFile = new File(externFile,mCurrentTitle+".txt");
        if (collectionFile.exists()){
            collectionFile.delete();
        }
        try {
            collectionFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取对应收藏夹中的单词
        List<Words> wordsList = Commons.getWordsList();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(collectionFile));
            for (Words words : wordsList) {
                bw.write(words.getHeadWord());
                bw.write(" -- "+words.getTran());
                bw.flush();
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();
        int i = (int) (end - start);
        LogUtil.d(TAG,"during --> "+i);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}