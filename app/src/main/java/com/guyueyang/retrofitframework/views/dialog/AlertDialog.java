package com.guyueyang.retrofitframework.views.dialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.databinding.DialogAlertBinding;
import com.guyueyang.retrofitframework.utils.StringUtils;


/**
 * 类说明：提示框
 */
public class AlertDialog extends BaseDialog {
    private DialogAlertBinding mDataBinding;
    private String mContent;
    private CallbackListener mListener;
    private boolean mCancelable = true;
    private String sure;

    public interface CallbackListener {
        void clickYes();
    }

    public AlertDialog(Context context, String content, AlertDialog.CallbackListener listener) {
        this(context, content, listener, null);
    }

    public AlertDialog(Context context, String content, AlertDialog.CallbackListener listener, String sure) {
        super(context);
        setCancelable(false);
        this.mContent = content;
        this.mListener = listener;
        this.sure = sure;
    }

    @Override
    protected View onInflateView(LayoutInflater inflater) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_alert, null, false);
        return mDataBinding.getRoot();
    }

    @Override
    protected void onInitView(View view) {
        mDataBinding.content.setText(StringUtils.filterNullAndTrim(mContent));
        if(!StringUtils.isNullEmpty(sure)){
            mDataBinding.sure.setText(StringUtils.filterNullAndTrim(sure));
        }
        mDataBinding.llSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) mListener.clickYes();
            }
        });
    }

    public void setCanDissmiss(boolean cancelable) {
        this.mCancelable = cancelable;
    }

    //    public void setContent(String content){
//        this.content = content;
//        mHeadDataBinding.content.setText(StringUtils.filterNullAndTrim(content));
//    }
//
//    public void setYesText(String yes){
//        mHeadDataBinding.sure.setText(StringUtils.filterNullAndTrim(yes));
//    }
//
//    public void setYesOnClickListener(View.OnClickListener listener){
//        mHeadDataBinding.sure.setOnClickListener(listener);
//    }


    @Override
    public void onBackPressed() {
        if (mCancelable) {
            super.onBackPressed();
        }
    }
}
