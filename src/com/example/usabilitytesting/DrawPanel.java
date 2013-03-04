package com.example.usabilitytesting;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class DrawPanel extends View{
	 
	 private Paint paint;
	 private ArrayList<MyPoint> points;
	 private ArrayList<ArrayList<MyPoint>> strokes;
	 private Paint dotPaint;
	public DrawPanel(Context context) {
		  super(context);
		  points = new ArrayList<MyPoint>();
		  strokes = new ArrayList<ArrayList<MyPoint>>();
		  paint = createPaint(Color.BLACK, 16);
		  System.out.println(paint.getStrokeCap());
		  paint.setStrokeCap(Cap.ROUND);

		  dotPaint = createPaint(Color.BLACK, 10);
		 }

	 @Override

//	 
	 public void onDraw(Canvas canvas) {
		    Path path = new Path();
		    boolean first = true;
		    for(int i = 0; i < points.size(); i += 2){
		        Point point = points.get(i);
		        if(first){
		            first = false;
		            path.moveTo(point.x, point.y);
		        }

		        else if(i < points.size() - 1){
		            Point next = points.get(i + 1);
		            path.quadTo(point.x, point.y, next.x, next.y);
		        }
		        else{
		            path.lineTo(point.x, point.y);
		        }
		    }

		    canvas.drawPath(path, paint);
		}
//	 public void onDraw(Canvas canvas) {
//		    Path path = new Path();
//
//		    if(points.size() > 1){
//		        for(int i = points.size() - 2; i < points.size(); i++){
//		            if(i >= 0){
//		            	MyPoint point = points.get(i);
//
//		                if(i == 0){
//		                	MyPoint next = points.get(i + 1);
//		                    point.dx = ((next.x - point.x) / 3);
//		                    point.dy = ((next.y - point.y) / 3);
//		                }
//		                else if(i == points.size() - 1){
//		                	MyPoint prev = points.get(i - 1);
//		                    point.dx = ((point.x - prev.x) / 3);
//		                    point.dy = ((point.y - prev.y) / 3);
//		                }
//		                else{
//		                	MyPoint next = points.get(i + 1);
//		                	MyPoint prev = points.get(i - 1);
//		                    point.dx = ((next.x - prev.x) / 3);
//		                    point.dy = ((next.y - prev.y) / 3);
//		                }
//		            }
//		        }
//		    }
//
//		    boolean first = true;
//		    for(int i = 0; i < points.size(); i++){
//		    	MyPoint point = points.get(i);
//		        if(first){
//		            first = false;
//		            path.moveTo(point.x, point.y);
//		        }
//		        else{
//		        	MyPoint prev = points.get(i - 1);
//		            path.cubicTo(prev.x + prev.dx, prev.y + prev.dy, point.x - point.dx, point.y - point.dy, point.x, point.y);
//		        }
//		    }
//		    canvas.drawPath(path, paint);
//		}
//	 
	 @Override
	 public boolean onTouchEvent(MotionEvent event){
	  if(event.getActionMasked() == MotionEvent.ACTION_MOVE){
	   points.add(new MyPoint((int)event.getX(), (int)event.getY()));
	   invalidate();
	  }
	  
	  if(event.getActionMasked() == MotionEvent.ACTION_UP){
	   this.strokes.add(points);
	   points = new ArrayList<MyPoint>();
	  }
	  
	  return true;
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
