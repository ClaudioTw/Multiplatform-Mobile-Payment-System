package com.billme.util;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.billme.ui.R;

public class MyAdapter extends BaseAdapter{
	
	private List<HashMap<String, String>> arrays = null;
	private Context mContext;
	private Button curDel_btn;
	private float x,ux;
	
	public MyAdapter(Context mContext, List<HashMap<String, String>> arrays){
		this.mContext = mContext;
		this.arrays = arrays;
	}
	
	@Override
	public int getCount() {
		return this.arrays.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if(view == null){
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.listview_itemrecords, null);
			viewHolder.tvTitle = (TextView)view.findViewById(R.id.tv_item_name);
			viewHolder.tvPrice = (TextView)view.findViewById(R.id.tv_item_price);
			viewHolder.btnDel = (Button) view.findViewById(R.id.del);
			view.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) view.getTag();
		}
		
		//Ϊÿ��view�����ô��ؼ���
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final ViewHolder holder = (ViewHolder)v.getTag();
				//������ʱ����
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					v.setBackgroundResource(R.drawable.bg_yellow);
					//��ȡ����ʱ��x������
					x = event.getX();
					//�ж�֮ǰ�Ƿ������ɾ����ť������ھ�����
					if(curDel_btn != null){
						curDel_btn.setVisibility(View.GONE);
					}
				}else if(event.getAction() == MotionEvent.ACTION_UP){
					v.setBackgroundResource(0);
					//��ȡ�ɿ�ʱ��x����
					ux = event.getX();
					//�жϵ�ǰ���а�ť�ؼ���Ϊ��ʱ
					if(holder.btnDel != null){
						//���º��ɿ�����ֵ�����20ʱ��ʾɾ����ť��������ʾ
						if(Math.abs(x-ux)>20){
							holder.btnDel.setVisibility(View.VISIBLE);
							curDel_btn = holder.btnDel;
						}
					}
				}else if(event.getAction() == MotionEvent.ACTION_MOVE){
					v.setBackgroundResource(R.drawable.bg_yellow);
				}else{
					v.setBackgroundResource(0);
				}
				return true;
			}
		});
		
		viewHolder.tvTitle.setText(this.arrays.get(position).get("itemName"));
		viewHolder.tvPrice.setText(this.arrays.get(position).get("itemPrice"));
		//Ϊɾ����ť��Ӽ����¼�
		viewHolder.btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(curDel_btn != null){
					curDel_btn.setVisibility(View.GONE);
					arrays.remove(position);
					notifyDataSetChanged();
				}
				
			}
		});
		return view;
	}

	final static class ViewHolder{
		TextView tvTitle;
		TextView tvPrice;
		Button btnDel;
	}
}
