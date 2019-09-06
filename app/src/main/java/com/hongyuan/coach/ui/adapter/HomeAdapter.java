package com.hongyuan.coach.ui.adapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.AppointmentCoursePrivitListBeans;
import com.hongyuan.coach.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeAdapter extends BaseQuickAdapter<AppointmentCoursePrivitListBeans.DataBean.ListBean, BaseViewHolder> {

    public HomeAdapter(){
        super(R.layout.item_homefragment_appointment_privite_course_list);
    }

    @Override
    protected void convert(final BaseViewHolder helper, AppointmentCoursePrivitListBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.showTime,getShowTime(item.getStart_time(),item.getEnd_time()))
                .setText(R.id.studentName,item.getM_name())
                .setText(R.id.courseNme,"预约课程："+item.getCp_name()+"/"+item.getNum()+"节");

        if(helper.getPosition() == 0){
            helper.setVisible(R.id.topLine,false);
        }else{
            helper.setVisible(R.id.topLine,true);
        }
        int minutes = TimeUtil.getOffectMinutes(item.getStart_time(),TimeUtil.dateFormatYMDHMS);
        if(minutes > 60){
            helper.setVisible(R.id.telStudent,true)
                    .setVisible(R.id.checkIn,false)
                    .setVisible(R.id.noCompleteBox,true)
                    .setVisible(R.id.completeText,false)
                    .setImageResource(R.id.statusImg,R.mipmap.home_wait_state_img);
        }else if(minutes < 60 && minutes > 0 && item.getJl_qd_state() != 1){
            helper.setVisible(R.id.telStudent,true)
                    .setVisible(R.id.checkIn,true)
                    .setVisible(R.id.noCompleteBox,true)
                    .setVisible(R.id.completeText,false)
                    .setImageResource(R.id.statusImg,R.mipmap.home_current_state_img);
        }else{
            helper.setVisible(R.id.telStudent,false)
                    .setVisible(R.id.checkIn,false)
                    .setVisible(R.id.noCompleteBox,false)
                    .setVisible(R.id.completeText,true)
                    .setImageResource(R.id.statusImg,R.mipmap.home_complete_status_img);
        }

        helper.addOnClickListener(R.id.telStudent).addOnClickListener(R.id.checkIn);
    }

    /*
    * 获取
    * */
    private String getShowTime(String startTime,String endTime){
        String showTime;
        showTime = TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM) + "-" +
                TimeUtil.formatDate(endTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        return showTime;
    }
}
