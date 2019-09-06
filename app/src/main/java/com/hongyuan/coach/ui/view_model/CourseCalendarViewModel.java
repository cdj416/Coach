package com.hongyuan.coach.ui.view_model;

import com.hongyuan.coach.base.Constants;
import com.hongyuan.coach.base.Controller;
import com.hongyuan.coach.base.CustomActivity;
import com.hongyuan.coach.base.CustomViewModel;
import com.hongyuan.coach.databinding.ActivityCourseCalendarBinding;
import com.hongyuan.coach.ui.activity.EditTimeActivity;
import com.hongyuan.coach.ui.beans.CourseCalendarBeans;
import com.hongyuan.coach.ui.beans.CourseCalendarUseBeans;
import com.hongyuan.coach.ui.view_page_adapter.CourseCalendarPagerAdapter;
import com.hongyuan.coach.util.BaseUtil;
import com.hongyuan.coach.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseCalendarViewModel extends CustomViewModel {

    private ActivityCourseCalendarBinding binding;
    private CourseCalendarPagerAdapter meunAdapter;

    //所以日历时间数据
    private CourseCalendarBeans calendarBeans;

    public CourseCalendarViewModel(CustomActivity mActivity, ActivityCourseCalendarBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        meunAdapter = new CourseCalendarPagerAdapter(mActivity,mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.mViewPager.setCurrentItem(0);

        binding.editTime.setOnClickListener(view -> {
            startActivity(EditTimeActivity.class,null);
        });

        //回到第一页
        binding.goOnePage.setOnClickListener(view -> binding.mViewPager.setCurrentItem(0));
    }

    @Override
    public void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        //教练端--教练的课程日历
        Controller.myRequest(Constants.GET_COACH_TIMEPLAN_LIST,Controller.TYPE_POST,getParams(), CourseCalendarBeans.class,this);
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == Constants.GET_COACH_TIMEPLAN_LIST){
            calendarBeans = (CourseCalendarBeans)data;

            //开始试试看看哈哈，希望老子脑子没烧坏。

            List<CourseCalendarUseBeans> showList = getHeandData(calendarBeans.getData().getList());

            meunAdapter.setData(showList);
            //解决页面重复绘制导致抛除重复绑定子项问题
            binding.mViewPager.setOffscreenPageLimit(showList.size());
        }
    }

    /*
    * 组装所有数据集
    * */
    private List<CourseCalendarUseBeans> getHeandData(List<List<CourseCalendarBeans.DataBean.ListBean>> allList){

        //全部数据集合
        List<CourseCalendarUseBeans> useBeansList = new ArrayList<>();
        CourseCalendarUseBeans pageData;

        //头部数据结合
        List<CourseCalendarUseBeans.PageTitleBeans> useTitleList = new ArrayList<>();

        //类容数据集合
        List<CourseCalendarUseBeans.PageContentBeans> contentBeansList = new ArrayList<>();

        //记录页数（从零开始算）
        int pageNum = 0;
        for(int i = 0 ; i < allList.size() ; i++){

            if(i%7 == 0 || i == allList.size()){//当i+1是7的倍数时添加一页或者最后一条数据结束添加最后一页

                    if(i != 0){
                        //要放在里面来创建，不能在外面创建，否则所有对象都是最后一个数据。
                        pageData = new CourseCalendarUseBeans();
                        //先把前一页的数据添加进去
                        pageData.setPageNum(pageNum);
                        pageData.setPageTitleBeansList(useTitleList);
                        pageData.setPageContentBeansList(contentBeansList);
                        useBeansList.add(pageData);

                        //头部标题栏数据
                        useTitleList = new ArrayList<>();
                        //类容部分数据
                        contentBeansList = new ArrayList<>();
                    }

                    //最后一页数据添加完成直接退出
                    /*if(i == allList.size()){
                        return useBeansList;
                    }*/

                    //插入一条空数据
                    insertEmptyData(useTitleList,contentBeansList,allList.get(i).size());

                pageNum++;
            }

            //组合数据
            inserHaveData(useTitleList,contentBeansList,allList,i);
        }

        return useBeansList;
    }

    /*
    * 星期字符串转换成下标
    * */
    private int getWeekIndex(String weekStr){
        switch (weekStr){
            case "一":
                return 0;

            case "二":
                return 1;

            case "三":
                return 2;

            case "四":
                return 3;

            case "五":
                return 4;

            case "六":
                return 5;

            case "日":
                return 6;

        }
        return -1;
    }

    /*
    * 插入实际数据
    * */
    private void inserHaveData(List<CourseCalendarUseBeans.PageTitleBeans> useTitleList,
                               List<CourseCalendarUseBeans.PageContentBeans> contentBeansList,List<List<CourseCalendarBeans.DataBean.ListBean>> allList,int i){

        CourseCalendarUseBeans.PageTitleBeans pageTitleBeans = new CourseCalendarUseBeans.PageTitleBeans();

        //这部分是标题数据部分
        pageTitleBeans.setIs_cur_date(allList.get(i).get(0).getIs_cur_date());
        pageTitleBeans.setNow_day(allList.get(i).get(0).getNow_day());
        pageTitleBeans.setWeek(allList.get(i).get(0).getWeek());
        useTitleList.add(pageTitleBeans);


        //下面部分是内容部分
        for(int j = 0 ; j < (allList.get(i).size()+2) ; j++){
            CourseCalendarUseBeans.PageContentBeans contentBeans
                    = new CourseCalendarUseBeans.PageContentBeans();
            if(j == 0|| j == 1){
                contentBeans.setWeekIndex(-1);
                contentBeans.setIs_kong(-1);
                contentBeans.setCp_name("");
                contentBeans.setM_name("");
                contentBeans.setMi_head("");
                contentBeans.setMi_realname("");
                contentBeans.setStart_date("");
                contentBeans.setState(-1);
                contentBeans.setWeek("");
                contentBeans.setNow_day("");

            }else{
                contentBeans.setWeekIndex(getWeekIndex(allList.get(i).get(0).getWeek()));
                contentBeans.setIs_kong(allList.get(i).get(j-2).getIs_kong());
                contentBeans.setCp_name(allList.get(i).get(j-2).getItem_cpa().getCp_name());
                contentBeans.setM_name(allList.get(i).get(j-2).getItem_cpa().getM_name());
                contentBeans.setMi_head(allList.get(i).get(j-2).getItem_cpa().getMi_head());
                contentBeans.setMi_realname(allList.get(i).get(j-2).getItem_cpa().getMi_realname());
                contentBeans.setStart_date(allList.get(i).get(j-2).getItem_cpa().getStart_date());
                contentBeans.setState(allList.get(i).get(j-2).getItem_cpa().getState());
                contentBeans.setWeek(allList.get(i).get(j-2).getWeek());
                contentBeans.setNow_day(allList.get(i).get(j-2).getNow_day());
                contentBeans.setShowNowDay(TimeUtil.formatDate(allList.get(i).get(j-2).getNow_day(),TimeUtil.dateFormatYMD,TimeUtil.dateFormatDotNoZoonMD));
            }
            //布局控制值
            //设置竖向值
            contentBeans.setRow(j);
            //设置横向值(加一是因为i为0已经设置了)
            contentBeans.setColumn(i%7+1);
            //设置竖向合并值
            contentBeans.setRowSpan(1);
            //设置横向合并值
            contentBeans.setColumnSpan(1);
            //设置是否有点击事件
            if(BaseUtil.isValue(contentBeans.getM_name())){
                contentBeans.setAddClick(true);
            }else{
                contentBeans.setAddClick(false);
            }
            contentBeansList.add(contentBeans);



        }
    }

    /*
    * 插入一页空数据
    * */
    private void insertEmptyData(List<CourseCalendarUseBeans.PageTitleBeans> useTitleList,List<CourseCalendarUseBeans.PageContentBeans> contentBeansList,int allListSize){

        CourseCalendarUseBeans.PageTitleBeans pageTitleBeans = new CourseCalendarUseBeans.PageTitleBeans();

        //标题部分
        pageTitleBeans.setIs_cur_date(-1);
        pageTitleBeans.setNow_day("");
        pageTitleBeans.setWeek("");
        useTitleList.add(pageTitleBeans);

        //下面部分是内容部分
        for(int j = 0 ; j < (allListSize+2) ; j++){

            CourseCalendarUseBeans.PageContentBeans contentBeans
                    = new CourseCalendarUseBeans.PageContentBeans();
            contentBeans.setWeekIndex(getWeekIndex("-1"));
            contentBeans.setIs_kong(0);
            contentBeans.setCp_name("");
            if(j%2 != 0 && j != (allListSize -1)){
                contentBeans.setM_name((9+(j-1)/2)+"点");
            }else{
                contentBeans.setM_name("");
            }
            contentBeans.setMi_head("");
            contentBeans.setMi_realname("");
            contentBeans.setStart_date("");
            contentBeans.setState(0);
            contentBeans.setWeek("");
            contentBeans.setNow_day("");

            //布局控制值
            //设置竖向值
            contentBeans.setRow(j);
            //设置横向值（因为第一项为显示时间用，所以都设为0）
            contentBeans.setColumn(0);
            //设置竖向合并值
            contentBeans.setRowSpan(2);
            //设置横向合并值
            contentBeans.setColumnSpan(1);
            //设置是否有点击事件
            contentBeans.setAddClick(false);

            contentBeansList.add(contentBeans);
        }
    }
}
