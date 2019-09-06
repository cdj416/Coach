package com.hongyuan.coach.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.activity.MyPlayActivity;

public class JumpUtils {

    /**
     * 跳转到视频播放
     *
     * @param activity
     * @param view
     */
    public static void goToVideoPlayer(Activity activity, View view, Bundle bundle) {
        Intent intent = new Intent(activity, MyPlayActivity.class);
        intent.putExtra(MyPlayActivity.TRANSITION, true);
        intent.putExtras(bundle);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(view, MyPlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    /*
    *
    * 扫码和消息跳转工具
    * */
    /*public static void goAtherPage(CustomViewModel viewModel,String href_code,String href_id){
        Bundle bundle = new Bundle();
        switch (href_code){
            case "cs_info"://团课详情
                bundle.putString("cs_id",href_id);
                viewModel.startActivity(MissionDetailActivity.class,bundle);
                break;
            case "cp_info"://私教课详情
                bundle.putString("cp_id",href_id);
                viewModel.startActivity(CourseDetailsActivity.class,bundle);
                break;
            case "os_qd"://进店打卡
                bundle.putString("os_id",href_id);
                bundle.putString("bg_img","");
                viewModel.startActivity(PunchActivity.class,bundle);
                break;
            case "os_info"://健身房首页详情
                bundle.putString("os_id",href_id);
                viewModel.startActivity(StoreDetailActivity.class,bundle);
                break;
            case "goods_list"://商品列表
                //bundle.putString("os_id",href_id);
                viewModel.startActivity(GoodActivity.class,null);
                break;
            case "goods_info"://商品详情
                bundle.putString("g_id",href_id);
                viewModel.startActivity(GoodDetailsActivity.class,bundle);
                break;
            case "os_list"://门店列表
                break;
            case "circle_list"://圈子帖子列表
                bundle.putInt("showNum",1);
                viewModel.startActivity(MainActivity.class,bundle);
                break;
            case "circle_info"://帖子详情
                bundle.putString("circle_id",href_id);
                viewModel.startActivity(PostDetailsActivity.class,bundle);
                break;
            case "coach_list"://教练列表
                break;
            case "coach_info"://教练主页
                bundle.putString("coach_mid",href_id);
                viewModel.startActivity(CoachHomePageActivity.class,bundle);
                break;
            case "msg_list"://消息列表
                //bundle.putString("coach_mid",href_id);
                viewModel.startActivity(MineMessageActivity.class,null);
                break;
            case "msg_info"://消息详情
                break;
            case "order_list"://订单列表
                viewModel.startActivity(MineOrderActivity.class,null);
                break;
            case "order_info"://订单详情
                bundle.putString("o_id",href_id);
                viewModel.startActivity(MineOrderDetailsActivity.class,bundle);
                break;

        }
    }*/

}
