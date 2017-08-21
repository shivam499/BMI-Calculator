package com.pathantalabs.android.bmicalculator.app;


class DataItem {

    private final int HeadText;
    private final int DescriptionText;
    private final int ImageView;

    public DataItem(int headText, int descriptionText, int imageView) {
        HeadText = headText;
        DescriptionText = descriptionText;
        ImageView = imageView;
    }

    public int getHeadText() {
        return HeadText;
    }

    public int getDescriptionText() {
        return DescriptionText;
    }

    public int getImageView() {
        return ImageView;
    }

}
