package com.hongyuan.coach.ui.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.coach.R;
import com.hongyuan.coach.ui.beans.CourseDetailsBean;
import com.hongyuan.coach.util.BaseUtil;


public class EditPriceAdapter extends BaseQuickAdapter<CourseDetailsBean.DataBean.PriceListBean, BaseViewHolder> {
    public EditPriceAdapter(){
        super(R.layout.item_edit_price);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetailsBean.DataBean.PriceListBean item) {
        if(BaseUtil.isValue(item.getPrice())){
            helper.setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getPrice()))
                    .setText(R.id.courseNum,BaseUtil.getNoZoon(item.getMin_num()));
        }else{
            helper.setText(R.id.coursePrice, "")
                    .setText(R.id.courseNum,"");
        }

        if(helper.getPosition() == (getData().size()-1) && getData().size() > 1){
            helper.setVisible(R.id.deleteImg,true).addOnClickListener(R.id.deleteImg);
        }else{
            helper.setVisible(R.id.deleteImg,false);
        }
    }

}
