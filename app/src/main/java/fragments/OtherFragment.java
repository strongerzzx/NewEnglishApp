package fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.WordSimple;
import com.example.englishapp_bishe.Chinese2EnglishActivity;
import com.example.englishapp_bishe.GameActivity;
import com.example.englishapp_bishe.Listen2SelectorActivity;
import com.example.englishapp_bishe.OtherTranslateActivity;
import com.example.englishapp_bishe.R;
import com.example.englishapp_bishe.SpeechRecogizeActivity;
import com.example.englishapp_bishe.SpellWordActivity;
import com.example.englishapp_bishe.TakePhotoGetWordActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.OtherAdapter;
import apis.ApiService;
import beans.BaiduTokenBeans;
import beans.OtherBeans;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import commonparms.Commons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.AlbumUtil;
import utils.Base64Util;
import utils.LogUtil;
import views.CustomProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {


    private static final String TAG = "OtherFragment";
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_ALBUM = 2;
    private View mInflate;
    private OtherAdapter mOtherAdapter;
    private RecyclerView mOtherRv;
    private List<OtherBeans> mOtherList=new ArrayList<>();

    private int[] imags=new int[]{R.mipmap.ic_selector_chinese_word,R.mipmap.ic_listner_2_yi,R.mipmap.ic_word_pin_xie};
    private String[] title=new String[]{"中文选词","听音选意","填空拼写"};
    private String[] smallTitle=new String[]{"阅读","听力","写作"};
    private TextView mTvGetText;
    private Uri imagUri;//拍照后的图片 -->  Uri
    private File mImgFile;
    private String mToken;
    private StringBuffer sb=new StringBuffer();
    private Unbinder mBind;
    private CustomProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;

    private int index=-1;
    private String[] item={"拍照","从相机选择"};


    @BindView(R.id.other_take_photo_layout)
    public RelativeLayout photoLayout;
    @BindView(R.id.other_helper_translate_layout)
    public RelativeLayout translateLayout;
    @BindView(R.id.other_helper_yuyin_layout)
    public RelativeLayout yuyinLayout;
    @BindView(R.id.other_game_layout)
    public RelativeLayout gameLayout;


    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_other, container, false);

        mBind = ButterKnife.bind(this, mInflate);


        mProgressDialog = new CustomProgressDialog(getContext());
        //初始化 百度OCR
        OCR.getInstance(getContext()).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                mToken = accessToken.getAccessToken();
             //   LogUtil.d(TAG,"token -->"+ mToken);
            }

            @Override
            public void onError(OCRError ocrError) {
                int errorCode = ocrError.getErrorCode();
                long logId = ocrError.getLogId();
                LogUtil.d(TAG,"error code --> "+errorCode+":"+logId);
            }
        },getContext());


        doGetBaiduToken();
        initView();

        return mInflate;
    }

    private void doGetBaiduToken() {
        Map<String,String> map=new HashMap<>();
        map.put("grant_type","client_credentials");
        map.put("client_id","KKZlP6iOf7ZkL89KA6Njujg5");
        map.put("client_secret","ul1DfOHQ4vs9VriE1eWW9oORdK5Fe03B");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Commons.OCR_BAI_DU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<BaiduTokenBeans> token = api.getAccessToken(map);
        token.enqueue(new Callback<BaiduTokenBeans>() {
            @Override
            public void onResponse(Call<BaiduTokenBeans> call, Response<BaiduTokenBeans> response) {
                int code = response.code();
                LogUtil.d(TAG,"token code --> "+code);
                if (code==HttpURLConnection.HTTP_OK){
                    BaiduTokenBeans tokenBeans = response.body();
                    Commons.accessToken=tokenBeans.getAccess_token();
                    LogUtil.d(TAG,"access --> "+tokenBeans.getAccess_token());
                }
            }

            @Override
            public void onFailure(Call<BaiduTokenBeans> call, Throwable t) {
                LogUtil.d(TAG,"error --> "+t.toString());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                //获取到图片
                    try {
                        mAlertDialog.dismiss();
                        mProgressDialog.show();
                        InputStream is = getContext().getContentResolver().openInputStream(imagUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        String baseParms = bitmap2Base64(bitmap);
                        String encode = URLEncoder.encode(baseParms, "UTF-8");
                        //百度OCR
                        doLocalBaiDuOcr(mImgFile.getPath());

                        LogUtil.d(TAG,"encode -->"+encode);
                    } catch (Exception e) {
                        e.printStackTrace();
                }
                break;

                    //相册选择照片
            case CHOOSE_ALBUM:
                mProgressDialog.show();
                Uri uri = data.getData();
                String filePathByUri = AlbumUtil.getFilePathByUri(getContext(), uri);
                LogUtil.d(TAG,"filePathByUri --> "+filePathByUri);
                doLocalBaiDuOcr(filePathByUri);
                break;
        }
    }

    private void doLocalBaiDuOcr(String picPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                GeneralBasicParams params=new GeneralBasicParams();
                params.setImageFile(new File(picPath));
                OCR.getInstance(getContext()).recognizeAccurateBasic(params, new OnResultListener<GeneralResult>() {
                    @Override
                    public void onResult(GeneralResult generalResult) {
                        LogUtil.d(TAG,"num --> "+generalResult.getWordsResultNumber());
                        List<? extends WordSimple> wordList = generalResult.getWordList();
                        if (wordList != null && generalResult.getWordsResultNumber()>0) {
                            for (WordSimple wordSimple : wordList) {
                                LogUtil.d(TAG,"本地百度OCR -->"+wordSimple);
                                sb.append(wordSimple);
                                mProgressDialog.dismiss();
                                //跳转到取词Act
                            }
                            Intent intent=new Intent(getActivity(), TakePhotoGetWordActivity.class);
                            intent.putExtra("photo",sb.toString());
                            startActivity(intent);
                        }else{
                            mProgressDialog.dismiss();
                            Toast.makeText(getContext(), "图片识别失败...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(OCRError ocrError) {
                        LogUtil.d(TAG,"error --> "+ocrError.getMessage()+":"+ocrError.getErrorCode());
                    }
                });

                long end = System.currentTimeMillis();
                LogUtil.d(TAG,"持续时间 --> "+(end-start));
            }
        }).start();
    }

    private String bitmap2Base64(Bitmap bitmap) {
        String result="";
        if (bitmap != null) {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            try {
                baos.flush();
                baos.close();
                byte[] bytes = baos.toByteArray();
                result = Base64Util.encode(bytes);
                LogUtil.d(TAG,"baiduBase64 ---> "+result);
              //  result= Base64.encodeToString(bytes,Base64.DEFAULT);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        initEvent();

        mOtherAdapter.setData(mOtherList);


        mOtherAdapter.setOnOtherItemClickListner(new OtherAdapter.onOtherItemClickListner() {
            @Override
            public void onOtherItemClick(int position) {
                switch (position) {
                    case 0:
                        Intent intent=new Intent(getActivity(), Chinese2EnglishActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(getActivity(), Listen2SelectorActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(getActivity(), SpellWordActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }

    private void initEvent() {

        //创建AlertDialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("请选择照片方式");
        alert.setSingleChoiceItems(item, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        //拍照选取
                        doTakePhoto();
                        break;
                    case 1:
                        //相册选取
                        doSelectPhotoInAlbum();
                        break;
                }
            }
        });

        mAlertDialog = alert.create();

        photoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.show();
            }
        });

        //翻译
        translateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), OtherTranslateActivity.class);
                startActivity(intent);
            }
        });

       //语音识别
        yuyinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SpeechRecogizeActivity.class);
                startActivity(intent);
            }
        });

        //龟兔赛跑
        gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }


    private void doSelectPhotoInAlbum() {
        Intent pickPhotot=new Intent(Intent.ACTION_PICK,null);
        pickPhotot.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(pickPhotot,CHOOSE_ALBUM);

        mAlertDialog.dismiss();
    }

    private void doTakePhoto() {
        //创建File --> 存储照片
        File exterFile = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mImgFile = new File(exterFile,"ocr.jpg");
        if (mImgFile.exists()){
            mImgFile.delete();
        }
        try {
            mImgFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据版本来拍照 -->
        if (Build.VERSION.SDK_INT>=24){
            imagUri= FileProvider.getUriForFile(getContext(),"com.example.englishapp_bishe.fileprovider", mImgFile);
        }else{
            imagUri= Uri.fromFile(mImgFile);
        }

        //启用相机
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imagUri);
        startActivityForResult(intent,TAKE_PHOTO);

        LogUtil.d(TAG,"图片地址 --> "+ mImgFile.getPath());
    }

    private void initData() {
        for (int i = 0; i < smallTitle.length; i++) {
            OtherBeans otherBeans=new OtherBeans.Builder(title[i])
                        .setSmallTitle(smallTitle[i])
                        .setImag(imags[i])
                        .setCurrentProgress(0)
                        .setFinalyProgress(0).build();
            mOtherList.add(otherBeans);
        }
    }

    private void initView() {
        mTvGetText = mInflate.findViewById(R.id.other_take_photo);
        mOtherRv = mInflate.findViewById(R.id.other_rv);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mOtherRv.setLayoutManager(manager);
        mOtherAdapter = new OtherAdapter();
        mOtherRv.setAdapter(mOtherAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
