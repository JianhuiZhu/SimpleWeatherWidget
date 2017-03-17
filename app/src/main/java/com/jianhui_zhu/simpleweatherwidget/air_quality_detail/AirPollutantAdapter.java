package com.jianhui_zhu.simpleweatherwidget.air_quality_detail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Iaqi;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.PollutantWrapper;
import com.jianhui_zhu.simpleweatherwidget.utils.ActivityFragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public class AirPollutantAdapter extends RecyclerView.Adapter<AirPollutantAdapter.ViewHolder> {
    private List<PollutantWrapper> pollutants = new ArrayList<>();
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PollutantWrapper wrapper = pollutants.get(position);
        holder.nameLabel.setText(wrapper.getHtmlFormattedName());
        holder.value.setText(wrapper.getVelocity());
        holder.itemCard.setOnClickListener(v -> ActivityFragmentUtil.openBrowserToShowSearchResult(v.getContext(),wrapper.getName()));
    }

    @Override
    public int getItemCount() {
        return pollutants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.aqi_card_view)
        CardView itemCard;
        @BindView(R.id.name_label_text_view)
        TextView nameLabel;
        @BindView(R.id.value_text_view)
        TextView value;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public AirPollutantAdapter(Context context, Iaqi airPollutantData){
        if(airPollutantData.getCo() != null){
            pollutants.add(new PollutantWrapper(context,airPollutantData.getCo()));
        }
        if(airPollutantData.getSo2() != null){
            pollutants.add(new PollutantWrapper(context,airPollutantData.getSo2()));
        }
        if(airPollutantData.getNo2() != null){
            pollutants.add(new PollutantWrapper(context,airPollutantData.getNo2()));
        }
        if(airPollutantData.getPm25() != null){
            pollutants.add(new PollutantWrapper(context,airPollutantData.getPm25()));
        }
        if(airPollutantData.getPm10() != null){
            pollutants.add(new PollutantWrapper(context,airPollutantData.getPm10()));
        }
        if(airPollutantData.getO3() != null){
            pollutants.add(new PollutantWrapper(context,airPollutantData.getO3()));
        }
    }
}
