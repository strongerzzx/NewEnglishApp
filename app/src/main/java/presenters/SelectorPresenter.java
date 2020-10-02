package presenters;

import android.os.Environment;
import android.os.Message;

import com.example.englishapp_bishe.SelectorBookActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import apis.ApiService;
import bases.BaseAppciation;
import beans.ContentUrl;
import beans.SelectBookBeans;
import beans.ZipBeans;
import commonparms.Commons;
import interfaces.ISelectBookCallback;
import interfaces.ISelectBookPresent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.ListPageUtils;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/9/29 13:58
 * <p>
 * 作用： xxxx
 */
public class SelectorPresenter implements ISelectBookPresent {

    private static final String TAG = "SelectBookPresent";
    private List<ISelectBookCallback> mCallbackList =new ArrayList<>();
    private List<SelectBookBeans.CatesBean.BookListBean> mAllBookList=new ArrayList<>();//全部的数据放在一起
    private List<SelectBookBeans.CatesBean.BookListBean> mCurrentBookList=new ArrayList<>();//记录当前刷新加载的
    private int mCurrentPage=1;//初始页
    private ListPageUtils mPageUtils;
    private int mCurrentPosition;
    private String mDownloadZipName;
    private String mRequestParms;
    private File mDownFile;
    private List<ZipBeans> mZipList=new ArrayList<>();
    private String mCurrentJsonName;

    //单线程池 用来顺序执行 -->  用来处理IO
    private ExecutorService singleExector=Executors.newSingleThreadExecutor();

    private SelectorPresenter() {
    }

    private static  volatile SelectorPresenter instance=null;

    public static SelectorPresenter getInstance() {
        if (instance==null){
            synchronized (SelectorPresenter.class){
                if (instance==null){
                    instance=new SelectorPresenter();
                }
            }
        }
        return instance;
    }
    @Override
    public void requestBook() {

        requestLoading();

        Retrofit mannager = new Retrofit.Builder().baseUrl(Commons.BASE_YOU_DAO).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService api = mannager.create(ApiService.class);
        Call<SelectBookBeans> task = api.getSelectJson();
        task.enqueue(new Callback<SelectBookBeans>() {
            @Override
            public void onResponse(Call<SelectBookBeans> call, Response<SelectBookBeans> response) {
                int code = response.code();
                LogUtil.d(TAG,"code --> "+code);
                if (code == HttpURLConnection.HTTP_OK){
                    try {
                        List<SelectBookBeans.CatesBean> catesBeanList = response.body().getCates();
                        //把分段的数据全部放在这数据
                        for (SelectBookBeans.CatesBean catesBean : catesBeanList) {
                            mAllBookList.addAll(catesBean.getBookList());
                        }
                        //把全部的数据分割成 每页只显示 --> 10条数据
                        mPageUtils = new ListPageUtils(mAllBookList,10);
                        List defaultPageList = mPageUtils.getPagedList(mCurrentPage);
                        for (ISelectBookCallback iSelectBookCallback : mCallbackList) {
                            iSelectBookCallback.showBookList(defaultPageList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<SelectBookBeans> call, Throwable t) {
                for (ISelectBookCallback iSelectBookCallback : mCallbackList) {
                    iSelectBookCallback.onNetworkError();
                }
                LogUtil.d(TAG,"error msg --> "+t.toString());
            }
        });
    }

    private void requestLoading() {
        for (ISelectBookCallback iSelectBookCallback : mCallbackList) {
            iSelectBookCallback.onLoading();
        }
    }

    @Override
    public void doLoadMore() {
        mCurrentPage++;
        refreshMore(true);
    }

    private void refreshMore(boolean loader) {
        if (loader){
            mCurrentBookList.clear();
            LogUtil.d(TAG,"mCurrentPage --> "+mCurrentPage);
            List pagedList = mPageUtils.getPagedList(mCurrentPage);
            mCurrentBookList.addAll(pagedList);
            for (ISelectBookCallback iSelectBookCallback : mCallbackList) {
                iSelectBookCallback.showBookList(mCurrentBookList);
                iSelectBookCallback.pullRefreshMore(mCurrentBookList.size());
            }
        }
    }


    @Override
    public void regestierSelectBookCallback(ISelectBookCallback callback) {
        if (mCallbackList != null && !mCallbackList.contains(callback)) {
            mCallbackList.add(callback);
        }
    }

    @Override
    public void unRegestierSelectBookCallback(ISelectBookCallback callback) {
        if (mCallbackList != null) {
            mCallbackList.remove(callback);
        }
        if (singleExector != null) {
            singleExector.shutdown();
        }
    }

    @Override
    public void requestPositionZip(int pos) {
        this.mCurrentPosition=pos;
        String zipUrl = mAllBookList.get(pos).getOfflinedata();



        //下载zip
        downloadZip(zipUrl);

        //解压Zip

        //读取SD中的Json --> 到数据库


    }


    Runnable jsonRunable=new Runnable() {
        @Override
        public void run() {
            FileInputStream fis=null;
            try {
                //组装json文件
                String replace = mDownloadZipName.replace(".zip", "");
                String jsonName=replace+".json";

                //读取json文件
                File jsonFile=new File(BaseAppciation.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),jsonName);
                fis=new FileInputStream(jsonFile);
                List<ZipBeans> mZipList=new ArrayList<>();
                String len="";
                BufferedReader br=new BufferedReader(new InputStreamReader(fis));
                Gson gson = new Gson();
                while ((len=br.readLine())!=null){
                    ZipBeans beans = gson.fromJson(len, ZipBeans.class);
                    mZipList.add(beans);
                }

                //TODO:写入到数据库
                for (ZipBeans beans : mZipList) {
                    String headWord = beans.getHeadWord();
                    LogUtil.d(TAG,"hearwords -->"+headWord);//英语

                    String phone = beans.getContent().getWord().getContent().getPhone();//英标

                }


                Message msg=Message.obtain();
                msg.what=SelectorBookActivity.TYPE_READ_JSON_PROGRESS;
                SelectorBookActivity.mSelectorHandler.sendMessage(msg);




            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    Runnable releaseZipRunnable =new Runnable() {
        @Override
        public void run() {
            try {
                upZipFile(mDownFile,BaseAppciation.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void releaseZip() {
        try {
            upZipFile(mDownFile,BaseAppciation.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void upZipFile(File zipFile, String folderPath) throws ZipException, IOException {
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
            ZipEntry entry = ((ZipEntry)entries.nextElement());
            InputStream in = zf.getInputStream(entry);
            String str = folderPath + File.separator + entry.getName();
            this.mCurrentJsonName =entry.getName();
            str = new String(str.getBytes("8859_1"), "GB2312");
            File desFile = new File(str);
            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    fileParentDir.mkdirs();
                }
                desFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(desFile);
            byte buffer[] = new byte[1024];
            int len=0;
            int totalLen=0;
            int available = in.available();
            while ((len= in.read(buffer)) !=-1) {
                out.write(buffer, 0, len);
                totalLen+=len;
                Message msg=Message.obtain();
                msg.arg1=totalLen;
                msg.arg2=available;
                msg.what= SelectorBookActivity.TYPE_RELEASE_ZIP_PROGRESS;
                SelectorBookActivity.mSelectorHandler.sendMessage(msg);
            }
            in.close();
            out.close();
        }
    }

    private void downloadZip(String zipUrl) {
        //获取zip名字
        getDownZipName(zipUrl);

        //组装请求参数
        mixRequestParms(zipUrl);

        File externFile = BaseAppciation.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        mDownFile = new File(externFile,mRequestParms);
        if (!mDownFile.exists()) {
            try {
                mDownFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //下载
        Retrofit build = new Retrofit.Builder().baseUrl(ContentUrl.BASE_SELECT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService api = build.create(ApiService.class);
        Call<ResponseBody> call = api.downloaZip(mRequestParms);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtil.d(TAG,"zip code --> "+code);
                if (code==HttpURLConnection.HTTP_OK){
                    FileOutputStream fos=null;
                    InputStream is=null;
                    try {
                        fos =new FileOutputStream(mDownFile);
                        int len=0;
                        int downloadLen=0;//已下载的字节数
                        int totalLen=0;//总下载长度
                        byte[] bytes=new byte[1024];
                        is = response.body().byteStream();
                        totalLen = is.available();
                        while ((len=is.read(bytes))!=-1){
                            fos.write(bytes,0,len);
                            downloadLen+=len;
                            //int value=(downloadLen*100)/totalLen; 0-100
                            for (ISelectBookCallback iSelectBookCallback : mCallbackList) {
                                iSelectBookCallback.downZipProgress(downloadLen, totalLen);
                            }
                        }

                        //解压zip --> 读取json文件
                        singleExector.submit(releaseZipRunnable);
                        singleExector.submit(jsonRunable);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (is!=null){
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.d(TAG,"error msg --> "+t.toString());
            }
        });
    }



    private void mixRequestParms(String zipUrl) {
        StringBuffer sb=new StringBuffer();
        int i = zipUrl.lastIndexOf("/");
        mRequestParms = sb.append(zipUrl).replace(0, i + 1, "").toString();
        LogUtil.d(TAG,"requesetParm -->"+ mRequestParms);
    }

    private void getDownZipName(String zipUrl) {
        StringBuffer sb=new StringBuffer();
        LogUtil.d(TAG,"zip url --> "+zipUrl);
        CharSequence charSequence = zipUrl.subSequence(zipUrl.indexOf("_"), zipUrl.length());
        mDownloadZipName = sb.append(charSequence.toString()).substring(1);
        LogUtil.d(TAG,"downloadZipName -->"+ mDownloadZipName);
    }

}
