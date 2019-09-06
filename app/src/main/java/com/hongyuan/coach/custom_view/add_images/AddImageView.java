package com.hongyuan.coach.custom_view.add_images;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.FileBean;
import com.hongyuan.coach.util.CustomDialog;
import com.hongyuan.coach.util.DividerItemDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddImageView extends LinearLayout {

    private RecyclerView mRecycler;
    private AddImgAdapter adapter;

    private List<FileBean> mList;

    public AddImageView(Context context) {
        super(context);
        initLayoutView();
    }

    public AddImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public AddImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        mList = new ArrayList<>();
        mList.add(new FileBean());

        View view = View.inflate(getContext(), R.layout.view_add_images, this);
        mRecycler = view.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VISIBLE);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,30,0x00000000));
        mRecycler.setLayoutManager(manager);
        adapter = new AddImgAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter, view1, position) -> {
            if(view1.getId() == R.id.closeImg){
                if(mList != null && mList.size() > 0){
                    mList.remove(position);
                    adapter.setNewData(mList);
                }
            }else if(view1.getId() == R.id.tvBg){
                if(mList.size() >= 10 ){
                    CustomDialog.showMessage(getContext(),"最多只能上传9张！");
                }else{
                    selectContent();
                }
            }else if(view1.getId() == R.id.contentImg){
                //点击查看大图功能
                GPreviewBuilder.from((Activity) getContext())
                        .setData(getInfoList((ImageView) view1))
                        .setCurrentIndex(position)
                        .setType(GPreviewBuilder.IndicatorType.Dot)
                        .start();
            }

        });
    }

    /*
    * 提供要上传的数据
    * */
    public List<FileBean> getImgList(){
        return this.mList;
    }

    /*
    * 初始化图片集合
    * */
    public void setNoimg(){
        mList.clear();
        mList.add(new FileBean());
        adapter.setNewData(mList);
    }

    /*
    * 打开选择图片或者视频节目
    * */
    public void selectContent(){
        PictureSelector.create((Activity) getContext())
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
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
        for(int i = 0 ; i < (mList.size()-1) ; i++){
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
                        for (LocalMedia bean:selectList){
                            FileBean imageBean = new FileBean();
                            if(bean.isCompressed()){
                                imageBean.setmFile(new File(bean.getCompressPath()));
                                imageBean.setFileKey("oss_file[]");
                            }else{
                                imageBean.setmFile(new File(bean.getPath()));
                                imageBean.setFileKey("oss_file[]");
                            }

                            if(mList.size() <= 9){
                                mList.add((mList.size()-1),imageBean);
                            }
                        }
                        adapter.setNewData(mList);

                    break;
            }
        }
    }
}
