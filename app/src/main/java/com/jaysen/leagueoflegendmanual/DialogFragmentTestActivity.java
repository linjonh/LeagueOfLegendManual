package com.jaysen.leagueoflegendmanual;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogFragmentTestActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags();
        setContentView(R.layout.content_main2);
        ButterKnife.bind(this);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyDialogFragment myDialogFragment = new MyDialogFragment();

        myDialogFragment.show(getSupportFragmentManager(), "d");

    }

    public static class MyDialogFragment extends android.support.v4.app.DialogFragment {

        public MyDialogFragment() {
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialog);
//            setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dialog_fragment, null, false);
            return view;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setWindowParam();
//            setWindowParam1();

        }

        private void setWindowParam1() {
            WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
            attributes.gravity = Gravity.BOTTOM;
//            void setFlags ( int flags,
//            int                 mask)
//            Set the flags of the window, as per the WindowManager.LayoutParams flags.
//            Note that some flags must be set before the window decoration is created (by the first call to setContentView(View, android.view.ViewGroup.LayoutParams)
// or getDecorView(): FLAG_LAYOUT_IN_SCREEN and FLAG_LAYOUT_INSET_DECOR. These will be set for you based on the windowIsFloating attribute.

            attributes.width = getResources().getDisplayMetrics().widthPixels;//it doesn't works when window is floating mode

            int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400,
                                                    getResources().getDisplayMetrics());

            attributes.height = h;//it doesn't works when window is floating mode
            getDialog().getWindow().setAttributes(attributes);

            getView().setMinimumWidth(
                    getResources().getDisplayMetrics().widthPixels);//it works when window is floating
            getView().setMinimumHeight(h);//it works when window is floating
            /**
             * if window is floating mode , the input method will pan, if window is not floating mode input method will adjust resize.
             */}

        private void setWindowParam() {
            int h = (int) (getResources().getDisplayMetrics().heightPixels * 0.8f + 0.5f);
            int w = getResources().getDisplayMetrics().widthPixels;
            if (getDialog() != null && getDialog().getWindow() != null) {
                WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
                attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                attributes.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
                getDialog().getWindow().setAttributes(attributes);
                getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                getDialog().getWindow().setLayout(w, h);
            }
            if (getView() != null) {
                getView().setMinimumWidth(w);//it works when window is floating
                getView().setMinimumHeight(h);//it works when window is floating
            }
//            PluLog.d("setWindowParam");
        }
    }
}
