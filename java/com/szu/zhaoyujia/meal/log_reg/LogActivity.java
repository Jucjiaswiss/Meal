package com.szu.zhaoyujia.meal.log_reg;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.MainActivity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;


/**
 * Created by zhaoyujia on 2017/8/24.
 */

@RuntimePermissions
public class LogActivity extends Activity {
    private TextView tv_checkCode;
    private TextView tv_log;
    private EditText et_phone;
    private EditText et_pass;
    private ImageView iv_logwechat;
    private ImageView iv_logqq;
    private Context context;
    private int TOTAL_TIME=60000;
    private int ONECE_TIME=1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        Utils.BmobSMSInitializ(context,this);
        setContentView(R.layout.activity_login_login);

        tv_checkCode=(TextView) findViewById(R.id.log_tv_checkcode);
        tv_log=(TextView) findViewById(R.id.log_tv_log);
        et_phone=(EditText) findViewById(R.id.log_et_phone);
        et_pass=(EditText) findViewById(R.id.log_et_pass);
        iv_logwechat=(ImageView) findViewById(R.id.log_iv_weichatlog);
        iv_logqq=(ImageView) findViewById(R.id.log_iv_qqlog);

        tv_checkCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogActivityPermissionsDispatcher.sendSMSCodeWithPermissionCheck(LogActivity.this);
            }
        });

        tv_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogActivityPermissionsDispatcher.checkSMSCodeWithPermissionCheck(LogActivity.this);
            }
        });

        iv_logwechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.toastShort(context,"微信登录，官网做好以后再做");
            }
        });
        iv_logqq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.toastShort(context,"QQ登录，官网做好以后再做");
            }
        });

    }

    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    public void sendSMSCode(){
        String inputphone=et_phone.getText().toString();
        if(inputphone.length()!=11){
            Utils.toastShort(context,"手机号有误！");
            return;
        }
        BmobSMS.requestSMSCode(context,inputphone, "LogCode",new RequestSMSCodeListener() {

            @Override
            public void done(Integer smsId,BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//验证码发送成功
                    //change the button form
                    countDownTimer.start();
                    tv_checkCode.setEnabled(false);
                    Utils.toastShort(context,"短信发送成功，短信id："+smsId);
                }
            }
        });


    }
    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    public void checkSMSCode(){
        String inputcode=et_pass.getText().toString();
        final String inputphone=et_phone.getText().toString();


        //check the <code></code>
        BmobSMS.verifySmsCode(context,inputphone,inputcode, new VerifySMSCodeListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//短信验证码已验证成功
                    Utils.toastShort(context,"登录通过");
                    Intent intent=new Intent(context, MainActivity.class);

                    //setTheUserInfo;
                    setTheUserInfo(inputphone);

                    startActivity(intent);
                }else{
                    Utils.toastShort(context,"验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                }
            }
        });
    }

    /**
     * 从登录获取的用户信息，保存在app中
     */
    private void setTheUserInfo(String phoneNumber) {


    }

    /**
     * CountDownTimer 实现倒计时
     */
    private CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONECE_TIME) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            //timer计时60s后重发
            tv_checkCode.setText("已发送("+value+"s)");
        }

        @Override
        public void onFinish() {
            tv_checkCode.setEnabled(true);
            tv_checkCode.setText("获取验证码");
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void goBack(View v){
        this.finish();
    }
}
