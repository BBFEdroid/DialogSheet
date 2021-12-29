package com.bb.dialogsheet;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class DialogSheet {
    private final Context context;
    private boolean useNewStyle;
    private boolean isTransparent = false;
    public ExpandedBottomSheetDialog bottomSheetDialog;
    private boolean roundedCorners = true;
    private int backgroundColor = 0;
    private int titleTextColor = 0;
    private int messageTextColor = 0;
    private AppCompatTextView titleTextView = null, messageTextView = null;
    private AppCompatImageView iconImageView = null;
    private MaterialButton positiveButton = null, negativeButton = null, neutralButton = null;
    private View textContainer = null;
    private LinearLayout messageContainer = null;
    private MaterialCardView iconCardView = null;
    private View inflatedView;

    public interface OnPositiveClickListener {
        void onClick(View v);
    }

    public interface OnNegativeClickListener {
        void onClick(View v);
    }

    public interface OnNeutralClickListener {
        void onClick(View v);
    }


    public DialogSheet setNewDialogStyle() {
        this.useNewStyle = true;
        init(context);
        return this;
    }

    public DialogSheet setTitle(CharSequence title) {
        if (title == null) {
            titleTextView.setVisibility(View.GONE);
        } else {
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText(title);
        }
        return this;
    }

    public DialogSheet setTitle(int titleRes) {
        setTitle(context.getResources().getString(titleRes));
        return this;
    }

    public DialogSheet setMessage(CharSequence message) {
        if (message == null) {
            messageTextView.setVisibility(View.GONE);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            messageTextView.setText(message);
        }
        return this;
    }

    public DialogSheet setMessage(int messageRes) {
        setMessage(context.getResources().getString(messageRes));
        return  this;
    }

    public DialogSheet setIconDrawable(Drawable icon) {
        if (icon == null) {
            hideIcon();
        } else {
            showIcon();
            iconImageView.setImageDrawable(icon);
        }

        return this;
    }

    public DialogSheet setIconBitmap(Bitmap icon) {
        if (icon == null) {
            hideIcon();
        } else {
            showIcon();
            iconImageView.setImageBitmap(icon);
        }
        return this;
    }

    public DialogSheet setIconResource(int iconRes) {
        showIcon();
        iconImageView.setImageResource(iconRes);
        return this;
    }

    public DialogSheet setPositiveButton(CharSequence text, boolean shouldDismiss, OnPositiveClickListener onPositiveClickListener) {
        if (text == null) {
            positiveButton.setVisibility(View.GONE);
        } else {
            positiveButton.setVisibility(View.VISIBLE);
            positiveButton.setText(text);
            positiveButton.setOnClickListener(v -> {
                if (shouldDismiss) {
                    bottomSheetDialog.dismiss();
                }

                onPositiveClickListener.onClick(v);
            });
        }

        return this;
    }
    public DialogSheet setPositiveButton(CharSequence text, OnPositiveClickListener onPositiveClickListener) {
        setPositiveButton(text, true, onPositiveClickListener);
        return this;
    }

    public DialogSheet setPositiveButton(int textRes, OnPositiveClickListener onPositiveClickListener) {
        setPositiveButton(context.getResources().getString(textRes), true, onPositiveClickListener);
        return this;
    }


    public DialogSheet setNegativeButton(CharSequence text, boolean shouldDismiss, OnNegativeClickListener onNegativeClickListener) {
        if (text == null) {
            negativeButton.setVisibility(View.GONE);
        } else {
            negativeButton.setVisibility(View.VISIBLE);
            negativeButton.setText(text);
            negativeButton.setOnClickListener(v -> {
                if (shouldDismiss) {
                    bottomSheetDialog.dismiss();
                }

                onNegativeClickListener.onClick(v);
            });
        }

        return this;
    }
    public DialogSheet setNegativeButton(CharSequence text, OnNegativeClickListener onNegativeClickListener) {
        setNegativeButton(text, true, onNegativeClickListener);
        return this;
    }

    public DialogSheet setNegativeButton(int textRes, OnNegativeClickListener onNegativeClickListener) {
        setNegativeButton(context.getResources().getString(textRes), true, onNegativeClickListener);
        return this;
    }

    public DialogSheet setNeutralButton(CharSequence text, boolean shouldDismiss, OnNeutralClickListener onNeutralClickListener) {
        if (text == null) {
            neutralButton.setVisibility(View.GONE);
        } else {
            neutralButton.setVisibility(View.VISIBLE);
            neutralButton.setText(text);
            neutralButton.setOnClickListener(v -> {
                if (shouldDismiss) {
                    bottomSheetDialog.dismiss();
                }

                onNeutralClickListener.onClick(v);
            });
        }

        return this;
    }
    public DialogSheet setNeutralButton(CharSequence text, OnNeutralClickListener onNeutralClickListener) {
        setNeutralButton(text, true, onNeutralClickListener);
        return this;
    }

    public DialogSheet setNeutralButton(int textRes, OnNeutralClickListener onNeutralClickListener) {
        setNeutralButton(context.getResources().getString(textRes), true, onNeutralClickListener);
        return this;
    }

    public DialogSheet setButtonsTextAllCaps(boolean textAllCaps) {
        positiveButton.setAllCaps(textAllCaps);
        negativeButton.setAllCaps(textAllCaps);
        neutralButton.setAllCaps(textAllCaps);

        return this;
    }


    public DialogSheet setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public DialogSheet setBackgroundColorRes(int backgroundColorRes) {
        setBackgroundColor(ContextCompat.getColor(context, backgroundColorRes));
        return this;
    }

    public DialogSheet setTransparentBackground() {
        isTransparent = true;
        setBackgroundColor(Color.TRANSPARENT);
        View bgView = bottomSheetDialog.findViewById(R.id.mainDialogContainer);
        Drawable bgDrawable = null;
        if (bgView != null) {
            bgDrawable = bgView.getBackground();
        }
        if (bgDrawable != null) {
            bgDrawable.setAlpha(0);
        }
        return this;
    }
    public DialogSheet setCancelable(boolean cancelable) {
        bottomSheetDialog.setCancelable(cancelable);
        return this;
    }

    //    public DialogSheet setDraggable(boolean b) {
//        bottomSheetDialog.setDraggable(b);
//        return this;
//    }
    public void setRoundedCorners(boolean roundedCorners) {
        if (roundedCorners) {
            View bgView = bottomSheetDialog.findViewById(R.id.mainDialogContainer);
            assert bgView != null;
            bgView.setBackgroundResource(
                    iconCardView.getVisibility() != View.GONE ?
                            useNewStyle ? R.drawable.dialog_sheet_main_background_round_margin :
                                    R.drawable.dialog_sheet_main_background_round
                            : R.drawable.dialog_sheet_main_background_round
            );
        } else {
            View bgView = bottomSheetDialog.findViewById(R.id.mainDialogContainer);
            assert bgView != null;
            bgView.setBackgroundResource(
                    iconCardView.getVisibility() != View.GONE ?
                            useNewStyle ? R.drawable.dialog_sheet_main_background_margin :
                                    R.drawable.dialog_sheet_main_background
                            : R.drawable.dialog_sheet_main_background
            );
        }

        this.roundedCorners = roundedCorners;
    }

    public DialogSheet setView(View view) {
        removePreviousMessageViews();
        messageContainer.addView(view);
        inflatedView = view;
        return  this;
    }

    public DialogSheet setView(int layoutRes) {
        removePreviousMessageViews();
        inflatedView = View.inflate(context, layoutRes, null);
        setView(inflatedView);
        return this;
    }


    public void show() {

        if (backgroundColor == 0 && !isTransparent) {
            backgroundColor = Utils.getWindowBackground(context);
        }

        if (backgroundColor != 0) {
            Drawable bgDrawable = null;
            View bgView = bottomSheetDialog.findViewById(R.id.mainDialogContainer);
            if (bgView != null) {
                bgDrawable = bgView.getBackground();
            }

            if (bgDrawable != null) {
                Drawable wrappedDrawable = DrawableCompat.wrap(bgDrawable);
                DrawableCompat.setTint(wrappedDrawable, backgroundColor);
            }

            iconCardView.setBackgroundColor( useNewStyle ? Color.parseColor("#DADADA") : backgroundColor);
        }

        if (titleTextColor == 0) titleTextColor = Utils.getTextColor(backgroundColor);
        if (messageTextColor == 0) messageTextColor = Utils.getTextColorSec(backgroundColor);

        titleTextView.setTextColor(titleTextColor);
        messageTextView.setTextColor(messageTextColor);

        if (positiveButton.getVisibility() != View.VISIBLE) {
            RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) negativeButton.getLayoutParams();
            mParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }

        if (!areButtonsVisible()) {
            int bottomPadding = 0;
            int topPadding = 0;
            if (messageTextView.getText() != null && !TextUtils.isEmpty(messageTextView.getText())) {
                bottomPadding = Utils.dpToPx(24);
                if (titleTextView.getText() == null || TextUtils.isEmpty(titleTextView.getText())) {
                    topPadding = Utils.dpToPx(24);
                }
            }

            textContainer.setPadding(0, topPadding, 0, bottomPadding);
        } else {
            if ( (titleTextView.getText() == null || TextUtils.isEmpty(titleTextView.getText()))
                    &&( messageTextView.getText() == null && !TextUtils.isEmpty(messageTextView.getText()) ) ) {
                textContainer.setPadding(0, Utils.dpToPx(24), 0, 0);
            }
        }

        if (!isTransparent) {
            setRoundedCorners(roundedCorners);
        }
        bottomSheetDialog.show();

        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && configuration.screenWidthDp > 600) {
            if (bottomSheetDialog.getWindow() != null) {
                bottomSheetDialog.getWindow().setLayout(Utils.dpToPx(600), -1);
            }
        }

        if (bottomSheetDialog.getWindow() != null) {
            bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        }

    }


    public void dismiss() {
        if (bottomSheetDialog.getWindow() != null) {
            bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        bottomSheetDialog.dismiss();
    }

    public View getInflatedView() {
        return inflatedView;
    }

    private void init(Context context) {
        bottomSheetDialog = new ExpandedBottomSheetDialog(context, R.style.DialogSheetTheme);
        bottomSheetDialog.setContentView(useNewStyle ? R.layout.layout_dialog_sheet_v2 : R.layout.layout_dialog_sheet);

        titleTextView = bottomSheetDialog.findViewById(R.id.dialogTitle);
        messageTextView = bottomSheetDialog.findViewById(R.id.dialogMessage);
        iconImageView = bottomSheetDialog.findViewById(R.id.dialogIcon);
        positiveButton = bottomSheetDialog.findViewById(R.id.buttonPositive);
        negativeButton = bottomSheetDialog.findViewById(R.id.buttonNegative);
        neutralButton = bottomSheetDialog.findViewById(R.id.buttonNeutral);
        textContainer = bottomSheetDialog.findViewById(R.id.textContainer);
        messageContainer = bottomSheetDialog.findViewById(R.id.messageContainer);
        iconCardView = bottomSheetDialog.findViewById(R.id.iconCardView);

        int posButtonTextColor = Color.parseColor("#DADADA");
        positiveButton.setTextColor(posButtonTextColor);
    }
    private boolean areButtonsVisible() {
        return positiveButton.getVisibility() == View.VISIBLE || negativeButton.getVisibility() == View.VISIBLE || neutralButton.getVisibility() == View.VISIBLE;
    }

    private void removePreviousMessageViews() {
        for (int i = 1; i < messageContainer.getChildCount(); i++) {
            messageContainer.removeViewAt(i);
        }
    }

    private void showIcon() {
        iconCardView.setVisibility(View.VISIBLE);
    }

    private void hideIcon() {
        iconCardView.setVisibility(View.GONE);
    }

    public DialogSheet(Context context) {
        this.context = context;
        init(context);
    }

}
