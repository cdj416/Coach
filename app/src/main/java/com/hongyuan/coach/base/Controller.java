package com.hongyuan.coach.base;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hongyuan.coach.ui.beans.FileBean;
import com.hongyuan.coach.util.GsonUtil;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    //请求类型
    public static final int TYPE_GET = 0x1;
    public static final int TYPE_POST = 0x2;

    //请求成功
    private static final String success_code = "1";

    /*
    * post,get键值对请求方式
    * */
    public static synchronized <T> void myRequest(int pathCode, int requestTpey,
                                                  Map<String, String> maps,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        //增加接口请求数
        listener.requestsNum();

        RequestParams params = new RequestParams(Constants.getInstance().getPath(pathCode));
        Log.e("cdj","=============path========"+Constants.getInstance().getPath(pathCode));
        if(maps != null){
            //组装常规参数
            Set<String> set = maps.keySet();
            for (String s : set) {
                String key = s;
                String value = maps.get(s);
                params.addBodyParameter(key, value);
                Log.e("cdj","=====key===="+key+"=====value===="+value);
            }
        }
        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(Constants.getInstance().getPath(pathCode),pathCode,dataBean,listener));
        }else{
            x.http().post(params, getCallback(Constants.getInstance().getPath(pathCode),pathCode,dataBean,listener));
        }

    }

    /*
    * 多文件上传
    * */
    public static synchronized <T> void myRequest(int pathCode , int requestTpey,
                                                  List<KeyValue> files,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(Constants.getInstance().getPath(pathCode));
        if(files != null){
            MultipartBody body= new MultipartBody(files,"UTF-8");
            params.setRequestBody(body);
            params.setMultipart(true);
        }
        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(Constants.getInstance().getPath(pathCode),pathCode,dataBean,listener));
        }else{
            x.http().post(params, getCallback(Constants.getInstance().getPath(pathCode),pathCode,dataBean,listener));
        }

    }
    /*
    * 单文件上传
    * */
    public static synchronized <T> void myRequest(int pathCode ,int requestTpey,
                                                  Map<String, String> maps,
                                                  FileBean mFile,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(Constants.getInstance().getPath(pathCode));
        if(maps != null){
            //组装常规参数
            Set<String> set = maps.keySet();
            for (String s : set) {
                String key = s;
                String value = maps.get(s);
                params.addBodyParameter(key, value);
            }
        }
        if(mFile != null){
            params.addBodyParameter(mFile.getFileKey(), mFile.getmFile());
        }

        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(Constants.getInstance().getPath(pathCode),pathCode,dataBean,listener));
        }else{
            x.http().post(params, getCallback(Constants.getInstance().getPath(pathCode),pathCode,dataBean,listener));
        }

    }

    /*
    * callback
    * */
    private static <T> Callback.CommonCallback<String> getCallback(String path,int code, final Class<T> dataBean,
                                                                   final RetrofitListener<T> listener){
        return new Callback.CommonCallback<String>() {
            BaseBean baseBean;
            @Override
            public void onSuccess(String result) {
                Log.e("cdj","====path===="+path+"===result==="+result);
                try {
                    baseBean = GsonUtil.getGson().fromJson(result, new TypeToken<BaseBean>(){}.getType());
                    if(baseBean.getStatus().getSucceed().equals(success_code)){
                        T data = GsonUtil.getGson().fromJson(result,dataBean);
                        //请求成功并解析成功反馈结果
                        if(listener != null){
                            listener.onSuccess(code,data);
                        }
                    }else{
                        listener.onError(code,baseBean.getStatus().getError_code(),baseBean.getStatus().getError_desc());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //解析出错
                    if(listener != null){
                        listener.onError(code,baseBean.getStatus().getError_code(),"解析异常！");
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                //请求错误反馈信息
                if(listener != null){
                    listener.onError(code,baseBean.getStatus().getError_code(),ex.getMessage());
                }
            }
            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
                //取消请求反馈信息
                if(listener != null){
                    listener.onError(code,baseBean.getStatus().getError_code(),cex.getMessage());
                }
            }
            @Override
            public void onFinished() {
                //关闭刷新
                if(listener != null){
                    listener.closeRefresh();
                }
            }
        };
    }

}
