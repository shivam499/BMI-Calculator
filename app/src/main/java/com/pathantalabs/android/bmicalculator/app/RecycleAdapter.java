package com.pathantalabs.android.bmicalculator.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.NativeExpressAdView;
import com.pathantalabs.android.bmicalculator.R;
import com.pathantalabs.android.bmicalculator.tabLayouts.BmiCalculator;
import com.pathantalabs.android.bmicalculator.tabLayouts.BmrCalculator;
import com.pathantalabs.android.bmicalculator.tabLayouts.BsaCalculator;
import com.pathantalabs.android.bmicalculator.tabLayouts.CalorieCalculator;
import com.pathantalabs.android.bmicalculator.tabLayouts.IbwCalculator;
import com.pathantalabs.android.bmicalculator.tabLayouts.LbmCalculator;

import java.util.List;

class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // --Commented out by Inspection (07-08-2017 12:22 AM):private final Context context;
    private static final int NATIVE_ADS_VIEW = 1;
    private static final int RECYCLER_VIEW_ITEM = 0;
    private final List<Object> mRecyclerViewItems;

    RecycleAdapter(Context context, List<Object> mRecyclerViewItems) {
        this.mRecyclerViewItems = mRecyclerViewItems;
    }


    private class NativeAdsHolder extends RecyclerView.ViewHolder {

        private NativeAdsHolder(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView HeadTextView;
        private final TextView DescriptionTextView;
        private final ImageView imageView;
        private final CardView dataCardVIew;

        ViewHolder(final View itemView) {
            super(itemView);

            HeadTextView = (TextView) itemView.findViewById(R.id.HeadText);
            DescriptionTextView = (TextView) itemView.findViewById(R.id.descriptionText);
            imageView = (ImageView) itemView.findViewById(R.id.ImageView);
            dataCardVIew = (CardView) itemView.findViewById(R.id.dataCardView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case RECYCLER_VIEW_ITEM:
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.recylerview_item, viewGroup, false);

                return new ViewHolder(view);
            case NATIVE_ADS_VIEW:
            default:
                View nativeExpressLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.native_ads_container,
                        viewGroup, false);
                return new NativeAdsHolder(nativeExpressLayoutView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        int viewType = getItemViewType(pos);
        switch (viewType) {
            case RECYCLER_VIEW_ITEM:
            default:
                ViewHolder dataHolder = (ViewHolder) viewHolder;
                DataItem dataItem = (DataItem) mRecyclerViewItems.get(pos);

                dataHolder.HeadTextView.setText(dataItem.getHeadText());
                dataHolder.DescriptionTextView.setText(dataItem.getDescriptionText());
                dataHolder.imageView.setImageResource(dataItem.getImageView());
                final int cardPos = dataHolder.getAdapterPosition();
                dataHolder.dataCardVIew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch(cardPos){
                            /*case 1:
                            case 4:
                            case 7:
                                   break; */
                            case 0:
                                view.getContext().startActivity(new Intent(view.getContext(), BmiCalculator.class));
                                break;
                            case 2:
                                view.getContext().startActivity(new Intent(view.getContext(), BmrCalculator.class));
                                break;
                            case 3:
                                view.getContext().startActivity(new Intent(view.getContext(), CalorieCalculator.class));
                                break;
                            case 4:
                                view.getContext().startActivity(new Intent(view.getContext(), BsaCalculator.class));
                                break;
                            case 6:
                                view.getContext().startActivity(new Intent(view.getContext(), IbwCalculator.class));
                                break;
                            case 7:
                                view.getContext().startActivity(new Intent(view.getContext(), LbmCalculator.class));
                                break;
                        }
                    }
                });
                break;
            case NATIVE_ADS_VIEW:
                NativeAdsHolder nativeExpressHolder =
                        (NativeAdsHolder) viewHolder;
                NativeExpressAdView adView =
                        (NativeExpressAdView) mRecyclerViewItems.get(pos);
                ViewGroup adCardView = (ViewGroup) nativeExpressHolder.itemView;

                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                // Add the Native Express ad to the native express ad view.
                adCardView.addView(adView);
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position % MainActivity.ITEMS_PER_AD == 1) ? NATIVE_ADS_VIEW
                : RECYCLER_VIEW_ITEM;
    }
}