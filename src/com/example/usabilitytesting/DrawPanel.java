package com.example.usabilitytesting;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class DrawPanel extends View{
	 
	 private Paint paint;
	 private ArrayList points;
	 private ArrayList strokes;
	 private Paint dotPaint;
	public DrawPanel(Context context) {
		  super(context);
		  points = new ArrayList();
		  strokes = new ArrayList();
		  paint = createPaint(Color.BLACK, 16);
		  System.out.println(paint.getStrokeCap());
		  paint.setStrokeCap(Cap.ROUND);

		  dotPaint = createPaint(Color.BLACK, 10);
		 }

	 @Override
	 public void onDraw(Canvas c){
	  super.onDraw(c);
	  this.setBackgroundColor(Color.WHITE);
	  for(Object obj: strokes){
	   drawStroke((ArrayList)obj, c);
	  }
	  
	  drawStroke(points, c);
	 }
	 
	 @Override
	 public boolean onTouchEvent(MotionEvent event){
	  if(event.getActionMasked() == MotionEvent.ACTION_MOVE){
	   points.add(new Point((int)event.getX(), (int)event.getY()));
	   invalidate();
	  }
	  
	  if(event.getActionMasked() == MotionEvent.ACTION_UP){
	   this.strokes.add(points);
	   points = new ArrayList();
	  }
	  
	  return true;
	 }
	 
	 private void drawStroke(ArrayList stroke, Canvas c){
	  if (stroke.size() > 0) {
	   Point p0 = (Point)stroke.get(0);
	   for (int i = 1; i < stroke.size(); i++) {
	    Point p1 = (Point)stroke.get(i);
	    c.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
	    
//		paint.setStrokeWidth(paint.getStrokeWidth());
//		c.drawCircle(p0.x, p0.y, (float) 2.5, dotPaint);
		
	    p0 = p1;
	   }
	  }
	 }
	 
	 private Paint createPaint(int color, float width){
	  Paint temp = new Paint();
	  temp.setStyle(Paint.Style.STROKE);
	  temp.setAntiAlias(true);
	  temp.setColor(color);
	  temp.setStrokeWidth(width);
	  temp.setStrokeCap(Cap.ROUND);
	  
	  return temp;
	 }


}
