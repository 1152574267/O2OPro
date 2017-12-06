package com.mrk.banner.base;

import android.support.v4.view.ViewPager.PageTransformer;

import com.mrk.banner.transformer.AccordionTransformer;
import com.mrk.banner.transformer.BackgroundToForegroundTransformer;
import com.mrk.banner.transformer.CubeInTransformer;
import com.mrk.banner.transformer.CubeOutTransformer;
import com.mrk.banner.transformer.DefaultTransformer;
import com.mrk.banner.transformer.DepthPageTransformer;
import com.mrk.banner.transformer.FlipHorizontalTransformer;
import com.mrk.banner.transformer.FlipVerticalTransformer;
import com.mrk.banner.transformer.ForegroundToBackgroundTransformer;
import com.mrk.banner.transformer.RotateDownTransformer;
import com.mrk.banner.transformer.RotateUpTransformer;
import com.mrk.banner.transformer.ScaleInOutTransformer;
import com.mrk.banner.transformer.StackTransformer;
import com.mrk.banner.transformer.TabletTransformer;
import com.mrk.banner.transformer.ZoomInTransformer;
import com.mrk.banner.transformer.ZoomOutSlideTransformer;
import com.mrk.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
