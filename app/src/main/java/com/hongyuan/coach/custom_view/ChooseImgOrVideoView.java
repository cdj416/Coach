package com.hongyuan.coach.custom_view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.SingleClick;
import com.hongyuan.coach.ui.adapter.ChooseImgAdapter;
import com.hongyuan.coach.ui.beans.FileBean;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.hongyuan.coach.util.ImageFactory;
import com.hongyuan.coach.util.JumpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChooseImgOrVideoView extends LinearLayout {

    private RecyclerView mRecycler;
    private ChooseImgAdapter adapter;
    private FrameLayout videoBox;
    private RoundedImageView videoImg;
    private ImageView closeImg,playVideo;


    private List<FileBean> mList;
    private Bitmap vedioBitmap;
    private String videoPath;
    private File videoImgFile;

    //图片标识
    public static final int SHOW_IMG = 0X1;
    //视频标识码
    public static final int SHOW_VEDIO = 0X2;
    public int nowType = SHOW_IMG;

    public ChooseImgOrVideoView(Context context) {
        super(context);
        initLayoutView();
    }

    public ChooseImgOrVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public ChooseImgOrVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_choose_img_video, this);
        mRecycler = view.findViewById(R.id.mRecycler);
        videoBox = view.findViewById(R.id.videoBox);
        playVideo = view.findViewById(R.id.playVideo);
        videoImg = view.findViewById(R.id.videoImg);
        closeImg = view.findViewById(R.id.closeImg);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VISIBLE);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,30,0x00000000));
        mRecycler.setLayoutManager(manager);
        adapter = new ChooseImgAdapter();
        mRecycler.setAdapter(adapter);

        closeImg.setOnClickListener(new OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                videoBox.setVisibility(GONE);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.closeImg){
                    if(mList != null && mList.size() > 0){
                        mList.remove(position);
                        adapter.setNewData(mList);
                    }
                }else{
                    //点击查看大图功能
                    GPreviewBuilder.from((Activity) getContext())
                            .setData(getInfoList((ImageView) view))
                            .setCurrentIndex(position)
                            .setType(GPreviewBuilder.IndicatorType.Dot)
                            .start();
                }

            }
        });
        playVideo.setOnClickListener(new OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("videoPath",videoPath);
                bundle.putString("videoImgPath",videoImgFile.getPath());
                JumpUtils.goToVideoPlayer((Activity) getContext(), v,bundle);
            }
        });
    }

    /*
    * 提供要上传的数据
    * */
    public List<FileBean> getImgList(){
        return this.mList;
    }
    public String getVideoPath(){
        return this.videoPath;
    }
    public File getVideoImgFile(){
        return this.videoImgFile;
    }



    /*
    * 打开选择图片或者视频节目
    * */
    public void selectContent(int type){
        if(type != nowType){
            nowType = type;
            if(mList != null){
                mList.clear();
            }
            videoImgFile = null;
        }

        if(type == SHOW_IMG){
            videoBox.setVisibility(GONE);
            mRecycler.setVisibility(VISIBLE);
        }else{
            videoBox.setVisibility(VISIBLE);
            mRecycler.setVisibility(GONE);
        }

        int selectTpye;
        int numType;
        if(type == SHOW_IMG){
            videoBox.setVisibility(GONE);
            mRecycler.setVisibility(VISIBLE);
            selectTpye = PictureMimeType.ofImage();
            numType = PictureConfig.MULTIPLE;
        }else{
            videoBox.setVisibility(VISIBLE);
            mRecycler.setVisibility(GONE);
            selectTpye = PictureMimeType.ofVideo();
            numType = PictureConfig.SINGLE;
        }

        PictureSelector.create((Activity) getContext())
                .openGallery(selectTpye)
                .selectionMode(numType)// 多选 or 单选
                .maxSelectNum(9)// 最大图片选择数量 int
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .previewVideo(true)//是否可预览视频
                .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /*
     * 获取图片集和图片所处位置
     * */
    private List<UserViewInfo> getInfoList(ImageView imageView){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < mList.size() ; i++){
            imgList.add(new UserViewInfo(mList.get(i).getmFile().getPath()));
            Rect bounds = new Rect();
            imageView.getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

    /*
     * 图片选择回调
     * */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(mList == null){
            mList = new ArrayList<>();
        }
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if(nowType == SHOW_IMG){
                        videoPath = null;
                        for (LocalMedia bean:selectList){
                            FileBean imageBean = new FileBean();
                            if(nowType == SHOW_IMG){
                                if(bean.isCompressed()){
                                    imageBean.setmFile(new File(bean.getCompressPath()));
                                    imageBean.setFileKey("oss_file[]");
                                }else{
                                    imageBean.setmFile(new File(bean.getPath()));
                                    imageBean.setFileKey("oss_file[]");
                                }

                                if(mList.size() < 9){
                                    mList.add(imageBean);
                                }
                            }
                        }
                        adapter.setNewData(mList);
                    }else{
                        mList.clear();
                        if(selectList.get(0).getPath() != null && !selectList.get(0).getPath().equals("")){
                            videoPath = selectList.get(0).getPath();
                            MediaMetadataRetriever media = new MediaMetadataRetriever();
                            media.setDataSource(selectList.get(0).getPath());// videoPath 本地视频的路径
                            vedioBitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC );

                            //压缩图片
                            //videoFile = ImageFactory.compressImage(vedioBitmap);

                            videoImgFile = ImageFactory.saveImageToGallery(getContext(),vedioBitmap);
                            RequestOptions options = new RequestOptions().error(R.mipmap.a_test2);
                            Glide.with(getContext()).load(vedioBitmap).apply(options).into(videoImg);

                            Log.e("cdj","========视频文件地址=-====="+ Environment.getExternalStorageDirectory().getPath()+"===="+videoPath);
                            //视频文件
                            addVideoFile(new File(videoPath));
                            //视频第一帧图片文件
                            addVideoFile(videoImgFile);
                        }
                    }
                    break;
            }
        }
    }

    /*
    * 把视频和视频第一帧添加到文件集合中
    * */
    private void addVideoFile(File mFile){
        FileBean videoFile = new FileBean();
        videoFile.setmFile(mFile);
        videoFile.setFileKey("oss_file[]");
        mList.add(videoFile);
    }
}
