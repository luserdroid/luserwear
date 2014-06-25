package de.pizzeria.gargano.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import de.pizzeria.gargano.R;

public class PizzaView extends View {

	
	private Drawable mIcon;
	private float mPosX;
	private float mPosY;

	private float mLastTouchX;
	private float mLastTouchY;

	private static final int INVALID_POINTER_ID = -1;

	// The ‘active pointer’ is the one currently moving our object.
	private int mActivePointerId = INVALID_POINTER_ID;

	public PizzaView(Context context) {
		this(context, null, 0);
	}

	public PizzaView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PizzaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mIcon = context.getResources().getDrawable(R.drawable.icon);
		mIcon.setBounds(0, 0, mIcon.getIntrinsicWidth(),
				mIcon.getIntrinsicHeight());
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();
		canvas.translate(mPosX, mPosY);
		mIcon.draw(canvas);
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
	    final int action = ev.getAction();
	    switch (action & MotionEvent.ACTION_MASK) {
	    case MotionEvent.ACTION_DOWN: {
	        final float x = ev.getX();
	        final float y = ev.getY();
	        
	        mLastTouchX = x;
	        mLastTouchY = y;

	        // Save the ID of this pointer
	        mActivePointerId = ev.getPointerId(0);
	        break;
	    }
	        
	    case MotionEvent.ACTION_MOVE: {
	        // Find the index of the active pointer and fetch its position
	        final int pointerIndex = ev.findPointerIndex(mActivePointerId);
	        final float x = ev.getX(pointerIndex);
	        final float y = ev.getY(pointerIndex);
	        
	        final float dx = x - mLastTouchX;
	        final float dy = y - mLastTouchY;
	        
	        mPosX += dx;
	        mPosY += dy;
	        
	        mLastTouchX = x;
	        mLastTouchY = y;
	        
	        invalidate();
	        break;
	    }
	        
	    case MotionEvent.ACTION_UP: {
	        mActivePointerId = INVALID_POINTER_ID;
	        break;
	    }
	        
	    case MotionEvent.ACTION_CANCEL: {
	        mActivePointerId = INVALID_POINTER_ID;
	        break;
	    }
	    
	    case MotionEvent.ACTION_POINTER_UP: {
	        // Extract the index of the pointer that left the touch sensor
	        final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) 
	                >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
	        final int pointerId = ev.getPointerId(pointerIndex);
	        if (pointerId == mActivePointerId) {
	            // This was our active pointer going up. Choose a new
	            // active pointer and adjust accordingly.
	            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
	            mLastTouchX = ev.getX(newPointerIndex);
	            mLastTouchY = ev.getY(newPointerIndex);
	            mActivePointerId = ev.getPointerId(newPointerIndex);
	        }
	        break;
	    }
	    }
	    
	    return true;
	}
}
