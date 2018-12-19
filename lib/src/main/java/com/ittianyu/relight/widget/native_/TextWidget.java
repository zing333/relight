package com.ittianyu.relight.widget.native_;

import android.content.Context;
import android.widget.TextView;

import android.arch.lifecycle.Lifecycle;

public class TextWidget extends BaseTextWidget<TextView, TextWidget> {

    public TextWidget(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    public TextWidget(Context context, Lifecycle lifecycle, String text) {
        super(context, lifecycle, text);
    }

    @Override
    public TextView createView(Context context) {
        return new TextView(context);
    }

}
