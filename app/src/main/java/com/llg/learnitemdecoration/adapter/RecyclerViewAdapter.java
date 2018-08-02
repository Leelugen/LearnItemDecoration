package com.llg.learnitemdecoration.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.llg.learnitemdecoration.R;
import com.llg.learnitemdecoration.model.FileItemData;

import java.util.List;

/**
 * create by lilugen on 2018/7/31
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<FileItemData> datas;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<FileItemData> datas) {
        this.datas = datas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_layout;
        CheckBox cb_select;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_item_layout = itemView.findViewById(R.id.tv_item);
            cb_select = itemView.findViewById(R.id.cb_item);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final FileItemData data = datas.get(position);
        final String str = data.getFileName();
        final boolean selected = data.isSelected();
        holder.tv_item_layout.setText(str);
        holder.cb_select.setChecked(selected);

        holder.tv_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
            }
        });

        holder.cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setSelected(!selected);
                holder.cb_select.setChecked(!selected);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * item decoration 的头部布局
     */
    public class HeaderHolder extends RecyclerView.ViewHolder {
        TextView group;
        CheckBox checkGroup;

        HeaderHolder(View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.group);
            checkGroup = itemView.findViewById(R.id.cb_group);
        }
    }

    //采用xml方式来实现ItemDecoration，可以更方便的定制ItemDecoration的内容，生成head布局
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) { 
        return new HeaderHolder(LayoutInflater.from(mContext).inflate(R.layout.item_decoration, parent, false));
    }

    //绑定head的数据
    public void onBindHeaderViewHolder(final HeaderHolder holder, final int position) {
        holder.group.setText(getHeaderId(position));
        holder.checkGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String createDate = datas.get(position).getCreateDate();
                boolean selected = datas.get(position).isSelected();

                //向后遍历
                for (int i = position; i < datas.size(); i++) {
                    FileItemData data = datas.get(i);
                    if (data.getCreateDate().equals(createDate)) {
                        data.setSelected(!selected);
                    } else {
                        break;
                    }
                }

                //向前遍历
                for (int j = position; j >= 0; j--) {
                    FileItemData data = datas.get(j);
                    if (data.getCreateDate().equals(createDate)) {
                        data.setSelected(!selected);
                    } else {
                        break;
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    //是否存在分组的头部
    public boolean hasHeader(int pos) {
        //分组逻辑
        if (pos == 0) {
            return true;
        }
        String currentDate = datas.get(pos).getCreateDate();
        String preDate = datas.get(pos - 1).getCreateDate();
        return !currentDate.equals(preDate);
    }

    //数据项属于哪一分组
    public String getHeaderId(int position) {
        return datas.get(position).getCreateDate();
    }
}
