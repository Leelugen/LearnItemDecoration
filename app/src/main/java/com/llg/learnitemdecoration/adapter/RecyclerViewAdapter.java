package com.llg.learnitemdecoration.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.llg.learnitemdecoration.R;
import com.llg.learnitemdecoration.model.GroupInfo;

import java.util.List;

/**
 * create by lilugen on 2018/7/31
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> datas;

    private GroupInfoCallback infoCallback;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.populate(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //是否存在分组的头部
    public boolean hasHeader(int pos) {
        //TODO 分组的逻辑
        if (pos % 5 == 0) {
            return true;
        } else {
            return false;
        }

    }

    //获取每条数据属于哪一分组
    public int getHeaderId(int position) {
        //TODO 某一项是否属于某组
        return position / 5;
    }

    //采用xml方式来实现ItemDecoration，可以更方便的定制ItemDecoration的内容，生成head布局
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(LayoutInflater.from(mContext).inflate(R.layout.item_decoration, parent, false));
    }

    //绑定head的数据
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.group.setText("分组" + getHeaderId(position));
       // viewholder.checkGroup.setText("点击分组" + getHeaderId(position));
    }


    public class HeaderHolder extends RecyclerView.ViewHolder {
        TextView group;
        CheckBox checkGroup;

        public HeaderHolder(View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.group);
            final CheckBox checkGroup = itemView.findViewById(R.id.cb_group);
            checkGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                    checkGroup.setChecked(true);
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_layout;
        String str;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_layout = (TextView) itemView.findViewById(R.id.tv_item);
            tv_item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void populate(String str) {
            tv_item_layout.setText(str);
            this.str = str;
        }
    }

    public GroupInfoCallback getInfoCallback() {
        return infoCallback;
    }

    public void setInfoCallback(GroupInfoCallback infoCallback) {
        this.infoCallback = infoCallback;
    }

    public interface GroupInfoCallback {
        GroupInfo getGroupInfo(int position);
    }
}
