package com.smontiel.kanbanboard.base;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

/**
 * Provides a {@link CoordinatorLayout.Behavior} that mimics the standard
 * {@link android.support.design.widget.FloatingActionButton} behaviour, i.e. moving out of the way for a {@link Snackbar}.
 * <p/>
 * Code copy pasted from the Google {@link android.support.design.widget.FloatingActionButton} implementation and adapted
 * slightly.
 * This is for com.github.clans.fab.FloatingActionMenu
 *
 * Created by Salvador Montiel on 16/11/17.
 */
public class FabMenuBehavior extends CoordinatorLayout.Behavior<FloatingActionMenu> {

    private static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final boolean SNACKBAR_BEHAVIOR_ENABLED = Build.VERSION.SDK_INT >= 11;
    private float mTranslationY;

    public FabMenuBehavior(Context context, AttributeSet attrs) { }

    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionMenu child, View dependency) {
        return SNACKBAR_BEHAVIOR_ENABLED && dependency instanceof Snackbar.SnackbarLayout;
    }

    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionMenu child, View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            updateFabTranslationForSnackbar(parent, child, dependency);
        }

        return false;
    }

    public void onDependentViewRemoved(CoordinatorLayout parent, FloatingActionMenu child, View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            ViewCompat
                    .animate(child)
                    .translationY(0.0F)
                    .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                    .setListener(null);
        }
    }

    private void updateFabTranslationForSnackbar(CoordinatorLayout parent, FloatingActionMenu fab,
                                                 View snackbar) {
        if (fab.getVisibility() == View.VISIBLE) {
            float translationY = getFabTranslationYForSnackbar(parent, fab);
            if (translationY != mTranslationY) {
                ViewCompat.animate(fab).cancel();
                ViewCompat.setTranslationY(fab, translationY);
                mTranslationY = translationY;
            }

        }
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent, FloatingActionMenu fab) {
        float minOffset = 0.0F;
        List dependencies = parent.getDependencies(fab);

        for (int i = 0, z = dependencies.size(); i < z; ++i) {
            View view = (View) dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset, ViewCompat.getTranslationY(view) -
                        (float) view.getHeight());
            }
        }

        return minOffset;
    }

    public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionMenu child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);

        return true;
    }
}
