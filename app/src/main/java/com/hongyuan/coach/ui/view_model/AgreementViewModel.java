package com.hongyuan.coach.ui.view_model;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.coach.R;
import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.databinding.ActivityAgreementBinding;
import com.hongyuan.coach.ui.beans.AgreementBeans;

public class AgreementViewModel extends CustomViewModel {

    private ActivityAgreementBinding binding;

    public AgreementViewModel(CustomActivity mActivity, ActivityAgreementBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        clearParams().setParams("img_code","app_xy").setParams("img_num","1");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), AgreementBeans.class,this);
    }

    @Override
    public void onSuccess(int code,Object data) {
        if(data instanceof AgreementBeans){
            AgreementBeans beans = (AgreementBeans)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.agreement_img).error(R.mipmap.agreement_img);
            Glide.with(mActivity).load(beans.getData().getList().get(0).getImg_src()).apply(options).into(binding.ivBig);
        }
    }
}
