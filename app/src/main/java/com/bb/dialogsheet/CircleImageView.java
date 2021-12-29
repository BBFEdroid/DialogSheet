package com.bb.dialogsheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {
    private static final int DEFAULT_FRAME_WIDTH_DP = 3;
    private static final int DEFAULT_FRAME_COLOR = Color.WHITE;
    private Paint framePaint = null;
    private float frameWidth = 0f;
    private int x = 0;
    private int y = 0;
    private float radius = 0f;
    private Path frameClip = null;

    public CircleImageView(@NonNull Context context) {
        super(context);
        init();
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        float scale = getResources().getDisplayMetrics().density;
        frameWidth = (DEFAULT_FRAME_WIDTH_DP * scale + 0.5f);
        framePaint = new Paint();
        framePaint.setAntiAlias(true);
        framePaint.setColor(DEFAULT_FRAME_COLOR);
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setStrokeWidth(frameWidth);
        frameClip = new Path();
    }

    @Override
    protected void onMeasure(int measuredWidth, int measuredHeight) {
        super.onMeasure(measuredWidth, measuredHeight);
        if (measuredWidth / 2 == x && measuredHeight / 2 == y) {
            return;
        }
        x = measuredWidth / 2;
        y = measuredHeight / 2;

        radius = Math.min(x, y) - frameWidth/2;
        frameClip.reset();
        frameClip.addCircle((float)x, (float)y, radius, Path.Direction.CW);
        frameClip.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int save = canvas.save();
        if (frameClip != null) {
            canvas.clipPath(frameClip);
        }

        super.onDraw(canvas);
        canvas.restoreToCount(save);

        if (framePaint != null) {
            canvas.drawCircle((float) x, (float) y, radius, framePaint);
        }
    }
}
