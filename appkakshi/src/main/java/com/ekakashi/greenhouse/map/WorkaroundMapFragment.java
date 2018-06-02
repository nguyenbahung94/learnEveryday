package com.ekakashi.greenhouse.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.SupportMapFragment;


public class WorkaroundMapFragment extends SupportMapFragment {
    private static OnTouchListener mListener;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View layout = super.onCreateView(layoutInflater, viewGroup, bundle);

        TouchableWrapper frameLayout = new TouchableWrapper(getActivity());

        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (layout instanceof ViewGroup) {
            ((ViewGroup) layout).addView(frameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return layout;
    }


    public void setListener(OnTouchListener listener) {
        mListener = listener;
    }

    public interface OnTouchListener {
        void onTouch();
    }

    public static class TouchableWrapper extends FrameLayout {

        public TouchableWrapper(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mListener != null) {
                        mListener.onTouch();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (mListener != null) {
                        mListener.onTouch();
                    }
                    break;
                default:
                    break;
            }
            return super.dispatchTouchEvent(event);
        }
    }
}
