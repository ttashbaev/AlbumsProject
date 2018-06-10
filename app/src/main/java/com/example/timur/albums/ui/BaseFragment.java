package com.example.timur.albums.ui;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.example.timur.albums.R;

public abstract class BaseFragment extends Fragment{

    private ProgressDialog mDialog;

    protected void showProgressBar() {
        mDialog = new ProgressDialog(getContext());
        mDialog.setMessage(getString(R.string.please_wait));
        mDialog.show();
    }

    protected void dismissProgressBar() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        mDialog = null;
    }
}
