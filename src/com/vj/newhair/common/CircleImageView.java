package com.vj.newhair.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.vj.newhair.R;

/**
 * 图形图片控件
 * 
 * 使用方法：<br/>
 * 继承自Android原生控件ImageView后，在xml layout文件中以类全名引用定义：<br/>
 * &lt com.lan.nicehair.common.CircleImageView
            xmlns:app="http://schemas.android.com/apk/res/com.lan.nicehair"
            android:id="@+id/iv_zone_item_profile"
   />         
 *
 */
public class CircleImageView extends ImageView {
	
	//TODO ScaleType作用是什么
	
	private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;
	
	private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
	private static final int COLORDRAWABLE_DIMENSION = 0;
	
	private static int DEFAULT_BORDER_WIDTH = 0;
	private static final int DEFAULT_BORDER_COLOR = Color.WHITE; //TODO Colr.WHTIE, Color.color什么区别；统一使用大写类型吧。
	
	private final RectF mDrawableRect = new RectF();
	private final RectF mBorderRect = new RectF();
	
	//TODO Matrix类作用是？
	private final Matrix mShaderMatrix = new Matrix();
	private final Paint mBitmapPaint = new Paint();
	private final Paint mBorderPaint = new Paint();
	
	private int mBorderColor = DEFAULT_BORDER_COLOR;
	private int mBorderWidth = DEFAULT_BORDER_WIDTH;
	
	private Bitmap mBitmap;
	/**
	 * BitmapShader是什么作用
	 */
	private BitmapShader mBitmapShader;
	private int mBitmapWidth;
	private int mBitmapHeight;
	
	private float mDrawableRadius;
	private float mBorderRadius;
	
	private boolean mReady;
	private boolean mSetupPending;
	
	public CircleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public CircleImageView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		super.setScaleType(SCALE_TYPE);
		TypedArray a= context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
		
		mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_border_width, DEFAULT_BORDER_WIDTH);
		mBorderColor = a.getColor(R.styleable.CircleImageView_border_color, DEFAULT_BORDER_COLOR);
		
		a.recycle();//TODO what for?
		
		mReady = true;
		
		if(mSetupPending){
			setup();
			mSetupPending = false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////
	private void setup(){
		if(!mReady){
			mSetupPending=true;
			return;
		}
		
		if(mBitmap==null){
			return;
		}
		
		mBitmapShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
		
		//TODO 这里的逻辑不明白
		mBitmapPaint.setAntiAlias(true);
		mBitmapPaint.setShader(mBitmapShader);
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBitmapPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setColor(mBorderColor);
		mBorderPaint.setStrokeWidth(mBorderWidth);
		
		mBitmapHeight=mBitmap.getHeight();
		mBitmapWidth=mBitmap.getWidth();
		
		mBorderRect.set(0, 0, getWidth(), getHeight());
		mBorderRadius=Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);
		
		mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);
		mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);
		
		updateShaderMatrix();
		invalidate();
	}

	private void updateShaderMatrix() {
		// TODO Auto-generated method stub
		float scale;
		float dx=0;
		float dy=0;
		
		mShaderMatrix.set(null);
		
		//TODO 这里的逻辑是？
		if(mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight){
			scale=mDrawableRect.height() / (float)mBitmapHeight;
			dx=(mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
		} else {
			scale=mDrawableRect.width()/(float)mBitmapWidth;
			dy=(mDrawableRect.height() - mBitmapHeight*scale) * 0.5f;
		}
		
		mShaderMatrix.setScale(scale, scale);
		//TODO 这里方法是个动画效果？
		mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);
		mBitmapShader.setLocalMatrix(mShaderMatrix);
	}
	
}
