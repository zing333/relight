package com.ittianyu.relight.widget.stateless;

import android.content.Context;
import android.view.View;

import com.ittianyu.relight.widget.ContainerWidget;
import com.ittianyu.relight.widget.Widget;
import com.ittianyu.relight.widget.native_.AndroidWidget;
import com.ittianyu.relight.widget.stateful.StatefulWidget;

public abstract class StatelessWidget<V extends View, T extends Widget<V>>
    extends Widget<V> implements ContainerWidget<V, T> {

    protected T widget;

    public StatelessWidget(Context context) {
        super(context);
    }

    protected abstract T build(Context context);

    public void update(Widget widget) {
        // stateless 的实例 widget 并不是直接渲染的 widget，所以这里对里面的实际 widget 进行获取
        if (widget instanceof StatelessWidget) {
            widget = ((StatelessWidget) widget).widget;
        }

        if (widget instanceof AndroidWidget) {
            //noinspection unchecked
            ((AndroidWidget) widget).updateView(widget.render());
        } else if (widget instanceof StatefulWidget) {
            ((StatefulWidget) widget).setState(null);
        } else if (widget instanceof StatelessWidget) {
            ((StatelessWidget) widget).update(widget);
        }
    }

    public void updateProps(Widget widget) {
        // stateless 的实例 widget 并不是直接渲染的 widget，所以这里对里面的实际 widget 进行获取
        if (widget instanceof StatelessWidget) {
            widget = ((StatelessWidget) widget).widget;
        }

        if (widget instanceof StatefulWidget) {
            ((StatefulWidget) widget).updateProps(widget);
        } else if (widget instanceof StatelessWidget) {
            ((StatelessWidget) widget).updateProps(widget);
        }
    }

    @Override
    public void initWidget(T widget) {
    }

    @Override
    public V render() {
        if (null == widget) {
            widget = build(context);
            initWidget(widget);
        }
        return widget.render();
    }
}
