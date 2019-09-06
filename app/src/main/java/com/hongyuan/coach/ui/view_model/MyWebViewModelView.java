package com.hongyuan.coach.ui.view_model;

import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.databinding.ActivityMyWebviewBinding;

public class MyWebViewModelView extends CustomViewModel {

    private ActivityMyWebviewBinding binding;

    public MyWebViewModelView(CustomActivity mActivity, ActivityMyWebviewBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        binding.webView.loadUrl("http://www.hongyuangood.com/xy/xy.html");
        //WebViewCacheInterceptorInst.getInstance().loadUrl(binding.webView, "http://www.hongyuangood.com/xy/xy.html");
        //clearParams().setParams("img_code","app_xy");
        //Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }
}
