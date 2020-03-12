package com.kishannareshpal.stateview;

import android.view.animation.AccelerateDecelerateInterpolator;

public class AnimationUtils {

    // Text View utils
    public static class TextView {

        /**
         * Swaps the current text with the newText with a vertical slide-to-top animation.
         *
         * @param textView textview of which you want to animate.
         * @param newText the new text to be changed.
         */
        public static void changeTextWithSlideToTop(android.widget.TextView textView, String newText) {
            textView.animate()
                    .translationY(-15f)
                    .alpha(0f)
                    .setDuration(150)
                    .withEndAction(() -> {
                        textView.setText(newText);
                        textView.setTranslationY(15f);
                        textView.animate()
                                .translationY(0f)
                                .alpha(1f)
                                .setDuration(150)
                                .setInterpolator(new AccelerateDecelerateInterpolator());
                    });
        }


        /**
         * Swaps the current text with the newText with a vertical slide-to-bottom animation.
         * similar to {@link AnimationUtils.TextView#changeTextWithSlideToTop(android.widget.TextView, String)}
         *
         * @param textView textview of which you want to animate.
         * @param newText the new text to be changed.
         */
        public static void changeTextWithSlideToBottom(android.widget.TextView textView, String newText) {
            textView.animate()
                    .translationY(15f)
                    .alpha(0f)
                    .setDuration(150)
                    .withEndAction(() -> {
                        textView.setText(newText);
                        textView.setTranslationY(-15f);
                        textView.animate()
                                .translationY(0f)
                                .alpha(1f)
                                .setDuration(150)
                                .setInterpolator(new AccelerateDecelerateInterpolator());
                    });
        }


        /**
         * Swaps the current text with the newText with a fade animation.
         *
         * @param textView textView of which you want to animate.
         * @param newText the new text to be changed.
         */
        public static void changeTextWithFade(android.widget.TextView textView, String newText) {
            textView.animate()
                    .alpha(0.2f)
                    .setDuration(144)
                    .withEndAction(() -> {
                        textView.animate()
                                .withStartAction(() -> {
                                    textView.setText(newText);
                                })
                                .alpha(1f)
                                .setDuration(144)
                                .setInterpolator(new AccelerateDecelerateInterpolator());
                    });
        }


    }
}
