package example.com.framedtext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by AGNAMC on 7/5/2016.
 */
public class FramedText extends View {

    // create attributes objects
    String mText;
    float mTextSize;
    String mTextColor;
    String mFrameColor;

    // bounds of text
    Rect mBounds;
    // for drawing
    Paint mPaint;

    public FramedText(Context context) {
        super(context);
    }

    public FramedText(Context context, AttributeSet attrs) {
        super(context, attrs);

        // retrieve attributes values from attrs.xml file
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FramedText, 0, 0);

        try{
            mText = typedArray.getString(R.styleable.FramedText_text);
            mTextSize = typedArray.getDimension(R.styleable.FramedText_textSize, 0);
            mTextColor = typedArray.getString(R.styleable.FramedText_textColor);
            mFrameColor = typedArray.getString(R.styleable.FramedText_frameColor);
        }
        finally {
            typedArray.recycle();
        }

        if (mText == null) mText = " ";
        mTextSize = (mTextSize == 0) ? 24f : mTextSize;
        if (mTextColor == null) mTextColor = "#000000";
        if (mFrameColor == null) mFrameColor = "#000000";

        init(context);
    }

    private void init(Context context) {
        float density = context.getResources().getDisplayMetrics().density;

        mBounds = new Rect();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setTextSize(mTextSize * density);

        // get bounds of text , stored im mBounds
        mPaint.getTextBounds(mText, 0, mText.length(), mBounds);


    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        invalidate();
        requestLayout();
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float textSize) {
        mTextSize = textSize;
        invalidate();
        requestLayout();
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(String textColor) {
        mTextColor = textColor;
        invalidate();
        requestLayout();
    }

    public String getFrameColor() {
        return mFrameColor;
    }

    public void setFrameColor(String frameColor) {
        mFrameColor = frameColor;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(
                (int) ((getWidth() - mBounds.width()) / 2.0f),
                (int) ((getHeight() - mBounds.height()) / 2.0f));

        mPaint.setColor(Color.parseColor(mTextColor));
        canvas.drawText(mText, 0.0f, 0.0f, mPaint);
        mPaint.setColor(Color.parseColor(mFrameColor));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mBounds, mPaint);

    }
}
