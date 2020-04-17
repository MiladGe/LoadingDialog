package com.android.milad.loadingdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tjy on 2017/6/19.
 */
public class LoadingDialog extends Dialog {


    private Handler handler;
    private Runnable autoCloseRunnable;
    private int waitTime;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    public static class Builder {

        private Context context;
        private String message;
        private boolean isShowMessage = true;
        private boolean isCancelable = false;
        private boolean isCancelOutside = false;
        private int maxWaitTime = 15000;
        private Typeface typeface;


        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         *
         * @param message
         * @return
         */

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置是否显示提示信息
         *
         * @param isShowMessage
         * @return
         */
        public Builder setShowMessage(boolean isShowMessage) {
            this.isShowMessage = isShowMessage;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         *
         * @param isCancelable
         * @return
         */

        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public Builder setMaxWaitTime(int seconds) {
            this.maxWaitTime = seconds * 1000;
            return this;
        }

        public Builder setTypeFace(Typeface typeFace) {
            this.typeface = typeFace;
            return this;
        }

        /**
         * 设置是否可以取消
         *
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside) {
            this.isCancelOutside = isCancelOutside;
            return this;
        }

        public LoadingDialog create() {

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_loading, null);
            LoadingDialog loadingDailog = new LoadingDialog(context, R.style.MyDialogStyle);
            TextView msgText = (TextView) view.findViewById(R.id.tipTextView);

            if (typeface != null) {
                msgText.setTypeface(typeface);
            }
            if (isShowMessage) {
                msgText.setText(message);
            } else {
                msgText.setVisibility(View.GONE);
            }
            loadingDailog.setContentView(view);
            loadingDailog.setCancelable(isCancelable);
            loadingDailog.setCanceledOnTouchOutside(isCancelOutside);
            loadingDailog.enableTimer(maxWaitTime);
            return loadingDailog;

        }


    }

    private void enableTimer(int time) {

        this.waitTime = time;
        handler = new Handler();

        autoCloseRunnable = new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        };
    }

    @Override
    public void show() {
        super.show();

        if (handler != null && autoCloseRunnable != null) {
            handler.postDelayed(autoCloseRunnable, waitTime);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (handler != null && autoCloseRunnable != null) {
            handler.removeCallbacks(autoCloseRunnable);
        }
    }

    public void onDestroy() {

        if (handler != null && autoCloseRunnable != null) {
            handler.removeCallbacks(autoCloseRunnable);
        }

        handler = null;
        autoCloseRunnable = null;
    }
}
