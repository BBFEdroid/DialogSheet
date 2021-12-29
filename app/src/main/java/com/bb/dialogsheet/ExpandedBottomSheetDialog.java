package com.bb.dialogsheet;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ExpandedBottomSheetDialog extends BottomSheetDialog {
    private BottomSheetBehavior<FrameLayout> dialogBehavior = null;

    public ExpandedBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
//            Field privateField = BottomSheetDialog.class.getDeclaredField("behavior");
//            privateField.setAccessible(true);
            dialogBehavior = getBehavior(); //(BottomSheetBehavior<FrameLayout>) privateField.get(this);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dialogBehavior != null) {
            dialogBehavior.setSkipCollapsed(true);
            dialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

//    public void setDraggable(boolean b) {
//        getBehavior().setDraggable(b);
//
//        if (!b) {
//            getBehavior().addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//                @Override
//                public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                        getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
//                    }
//                }
//
//                @Override
//                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//                }
//            });
//        }
//    }
}
