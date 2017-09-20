package com.mrmarapps.helloinnocv.adapters;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mrmarapps.helloinnocv.R;

/**
 * Created by mario on 20/09/17.
 */

public class InnoCvAdapter<Item extends AbstractItem> extends FastItemAdapter<Item> {

    public int positionSelected=0;



    public int getPositionSelected() {
        return positionSelected;
    }



    public void selectItem(final View v, int position) {
        positionSelected=position;
        final Drawable bgPrimary = v.getBackground();

        v.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorAccentLight));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setBackground(bgPrimary);
            }
        },100);
    }

}
