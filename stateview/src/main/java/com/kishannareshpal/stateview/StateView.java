package com.kishannareshpal.stateview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.kishannareshpal.circularprogressview.CircularProgressView;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

public class StateView extends LinearLayout {

    // default values
    public static final int NO_VALUE = -102;
    // TODO private static final int DEFAULT_ACTION_CORNER_RADIUS = 24;
    private static final int DEFAULT_TITLE_TEXTSIZE = 24; // sp
    private static final Hashtable<String, SoftReference<Typeface>> fontCache = new Hashtable<>(); // prevents memory leak for font changing.

    // Utils
    private Context ctx;

    // Components
    private LinearLayout ll_root, ll_iconHolder, ll_descriptionHolder, ll_actionButtonHolder; // root
    private CircularProgressView cpv_mainProgress, cpv_smallProgress;
    private ImageView iv_mainIcon;
    private TextView tv_title, tv_description;
    private MaterialButton btn_action;


    private State state;
    private @Nullable @DrawableRes Integer iconRes;
    private int stateChangeDelayInMillis = 0;
    private String title, description, actionButtonText;
    private @ColorInt int backgroundColor;
    private @ColorInt int titleColor, descriptionColor;
    private @ColorInt int actionButtonColor, actionButtonTextColor;
    private AnimationType titleTextChangeAnimationType = AnimationType.NO_ANIMATION,
                   descriptionTextChangeAnimationType = AnimationType.NO_ANIMATION;
    private boolean mainProgressEnabled, smallProgressEnabled;
    private @ColorInt int progressStrokeColor, progressBackgroundColor;
    private ComponentGravity gravity;
    private OnActionButtonClickListener onActionButtonClickListener;
    private int titleTextSize;
    private String titleFontFilename, descriptionFontFilename, actionButtonFontFilename;

    // TODO: private @Px int actionCornerRadius;


    public interface OnActionButtonClickListener {
        void OnActionButtonClick(StateView stateView, View actionButton);
    }



    // Methods

    /**
     * Change the background color.
     *
     * @param backgroundColor the background color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView backgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        changeBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * Change the icon image.
     * @param iconRes the image drawable resource identifier {@code R.drawable.…} Or pass {@code null} if you want to hide the icon.
     * @param isGif if the image drawable is a .gif
     */
    public StateView mainIcon(@Nullable @DrawableRes Integer iconRes, boolean isGif) {
        this.iconRes = iconRes;
        changeIcon(iconRes, isGif);
        return this;
    }

    /**
     * If enabled, shows an indeterminate circular progress where the main icon is drawn.
     * @param mainProgressEnabled true, shows the indeterminate, false, hides the progress and shows the icon image if it was previously set.
     */
    public StateView mainProgressEnabled(boolean mainProgressEnabled) {
        this.mainProgressEnabled = mainProgressEnabled;
        changeMainProgressEnabled(mainProgressEnabled);
        return this;
    }

    /**
     * If enabled, shows an indeterminate circular progress to the left of the description text.
     * @param smallProgressEnabled true, shows the indeterminate, otherwise simply hides the progress.
     */
    public StateView smallProgressEnabled(boolean smallProgressEnabled) {
        this.smallProgressEnabled = smallProgressEnabled;
        changeSmallProgressEnabled(smallProgressEnabled);
        return this;
    }

    /**
     * Change the stroke color of the indeterminate progress for both, main and small progress.
     * @param progressStrokeColor the progress stroke color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView progressStrokeColor(@ColorInt int progressStrokeColor) {
        this.progressStrokeColor = progressStrokeColor;
        changeProgressStrokeColor(progressStrokeColor);
        return this;
    }

    /**
     * Change the background color of the indeterminate progress for both, main and small progress.
     * @param progressBackgroundColor the progress background color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView progressBackgroundColor(@ColorInt int progressBackgroundColor) {
        this.progressBackgroundColor = progressBackgroundColor;
        changeProgressBackgroundColor(progressBackgroundColor);
        return this;
    }


    /**
     * Change the state.
     * - {@link State#NORMAL} shows the content.
     * - {@link State#ALTERNATE} shows the alternate content, such as empty message.
     *
     * @param state the state to which you want to change.
     * @see StateView#state(State, int) if you want to add a delay before changing to the other state.
     */
    public StateView state(State state) {
        this.state = state;
        this.stateChangeDelayInMillis = 0;

        changeState(state, 0);
        return this;
    }

    /**
     * Change the state.
     * - {@link State#NORMAL} shows the content.
     * - {@link State#ALTERNATE} shows the alternate content, such as empty message.
     *
     * @param state the state to which you want to change.
     * @param stateChangeDelayInMillis the delay in milliseconds to wait before changing the
     */
    public StateView state(State state, int stateChangeDelayInMillis) {
        this.state = state;
        this.stateChangeDelayInMillis = stateChangeDelayInMillis;

        changeState(state, stateChangeDelayInMillis);
        return this;
    }

    /**
     * Change the title, instantly.
     * @param title the new title, or pass {@code null} to hide the title.
     */
    public StateView title(String title) {
        this.title = title;
        this.titleTextChangeAnimationType = AnimationType.NO_ANIMATION;

        changeTitle(title, titleTextChangeAnimationType);
        return this;
    }

    /**
     * Change the size of the title text
     * @param titleTextSize title text size.
     */
    public StateView titleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
        changeTitleTextSize(titleTextSize, false);
        return this;
    }


    /**
     * Change the title, with some animation.
     * @param title the new title, or pass {@code null} to hide the title.
     * @param titleTextChangeAnimationType the animation type {@link AnimationType} to use when changing the title
     */
    public StateView title(@Nullable String title, AnimationType titleTextChangeAnimationType) {
        this.title = title;
        this.titleTextChangeAnimationType = titleTextChangeAnimationType;

        changeTitle(title, titleTextChangeAnimationType);
        return this;
    }

    /**
     * Change the title text color.
     * @param titleColor the color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)})
     */
    public StateView titleColor(@ColorInt int titleColor) {
        this.titleColor = titleColor;
        changeTitleColor(titleColor);
        return this;
    }

    /**
     * Change the title text font family.
     *
     * @param fontFilename the font file name including the extension such as ".ttf"
     */
    public StateView titleFont(String fontFilename) {
        this.titleFontFilename = fontFilename;
        changeTitleFont(fontFilename);
        return this;
    }

    /**
     * Change the description, instantly.
     * @param description the new description, or pass {@code null} to hide the description.
     */
    public StateView description(@Nullable String description) {
        this.description = description;
        this.descriptionTextChangeAnimationType = AnimationType.NO_ANIMATION;
        changeDescription(description, descriptionTextChangeAnimationType);
        return this;
    }

    /**
     * Change the description, with some animation.
     * @param description the new description, or pass {@code null} to hide the description.
     * @param descriptionTextChangeAnimationType the animation type {@link AnimationType} to use when changing the description
     */
    public StateView description(@Nullable String description, AnimationType descriptionTextChangeAnimationType) {
        this.description = description;
        this.descriptionTextChangeAnimationType = descriptionTextChangeAnimationType;
        changeDescription(description, descriptionTextChangeAnimationType);
        return this;
    }

    /**
     * Change the description text color.
     * @param descriptionColor the color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView descriptionColor(@ColorInt int descriptionColor) {
        this.descriptionColor = descriptionColor;
        changeDescriptionColor(descriptionColor);
        return this;
    }

    /**
     * Change the description text font family.
     *
     * @param fontFilename the font file name including the extension such as ".ttf"
     */
    public StateView descriptionFont(String fontFilename) {
        this.descriptionFontFilename = fontFilename;
        changeDescriptionFont(fontFilename);
        return this;
    }


    /**
     * Change the action button text.
     * @param actionButtonText th enew action button text, or pass {@code null} to hide the description.
     */
    public StateView actionButtonText(String actionButtonText) {
        this.actionButtonText = actionButtonText;
        changeActionButtonText(actionButtonText);
        return this;
    }

    /**
     * Change the action button text color.
     * @param actionButtonTextColor the action button text color (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView actionButtonTextColor(@ColorInt int actionButtonTextColor) {
        this.actionButtonTextColor = actionButtonTextColor;
        changeActionButtonTextColor(actionButtonTextColor);
        return this;
    }

    /**
     * Change the action button color.
     * @param actionButtonColor the action button color (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView actionButtonColor(@ColorInt int actionButtonColor) {
        this.actionButtonColor = actionButtonColor;
        changeActionButtonColor(actionButtonColor);
        return this;
    }

    /**
     * Change the action button text font family.
     *
     * @param fontFilename the font file name including the extension such as ".ttf"
     */
    public StateView actionButtonFont(String fontFilename) {
        this.descriptionFontFilename = fontFilename;
        changeActionButtonFont(fontFilename);
        return this;
    }

    /**
     * Set what to do when the action button is clicked.
     * @param onActionButtonClickListener listener {@link OnActionButtonClickListener}, or pass {@code null} to remove the listener.
     */
    public StateView onActionButtonClick(OnActionButtonClickListener onActionButtonClickListener) {
        this.onActionButtonClickListener = onActionButtonClickListener;
        changeOnActionButtonClick(this, onActionButtonClickListener);
        return this;
    }


    /**
     * Enables, or disables the action button.
     * @param enabled the state.
     */
    public StateView actionButtonEnabled(boolean enabled) {
        changeActionButtonEnabled(enabled);
        return this;
    }


    /**
     * Change the horizontal gravity of the icon, title, and description with smallProgress all at once.
     * @param gravity {@link ComponentGravity} horizontal gravity.
     */
    public StateView gravity(ComponentGravity gravity) {
        this.gravity = gravity;
        changeGravity(gravity);
        return this;
    }


    // Default Constructor
    public StateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init(context, attrs);
    }

    /**
     * Main Method
     */
    private void init(Context ctx, @Nullable AttributeSet attrs){
        inflate(ctx, R.layout.stateview_layout, this);
        TypedArray ta = null;

        // Init Inside Components
        ll_root               = findViewById(R.id.ll_root);
        iv_mainIcon           = findViewById(R.id.iv_mainIcon);
        cpv_mainProgress      = findViewById(R.id.cpv_mainProgress);
        ll_iconHolder         = findViewById(R.id.ll_iconHolder);
        tv_title              = findViewById(R.id.tv_title);
        tv_description        = findViewById(R.id.tv_description);
        ll_descriptionHolder  = findViewById(R.id.ll_descriptionHolder);
        cpv_smallProgress     = findViewById(R.id.cpv_smallProgress);
        btn_action            = findViewById(R.id.btn_action);
        ll_actionButtonHolder = findViewById(R.id.ll_actionButtonHolder);

        // if attrs were set via xml
        if (attrs != null) {
            // get the attributes from the attrs.xml
            ta                            = getContext().obtainStyledAttributes(attrs, R.styleable.StateView);
            this.backgroundColor          = ta.getColor(R.styleable.StateView_stateBackgroundColor, NO_VALUE);

            this.state                    = State.fromId(ta.getInt(R.styleable.StateView_stateMode, State.NORMAL.getId()));
            this.iconRes                  = ta.getResourceId(R.styleable.StateView_stateIcon, NO_VALUE);
            if (this.iconRes == NO_VALUE) this.iconRes = null;

            this.mainProgressEnabled      = ta.getBoolean(R.styleable.StateView_stateMainProgressEnabled, false);
            this.smallProgressEnabled     = ta.getBoolean(R.styleable.StateView_stateSmallProgressEnabled, false);
            this.progressStrokeColor      = ta.getColor(R.styleable.StateView_stateProgressStrokeColor, NO_VALUE);
            this.progressBackgroundColor  = ta.getColor(R.styleable.StateView_stateProgressBackgroundColor, NO_VALUE);

            this.titleFontFilename        = ta.getString(R.styleable.StateView_stateTitleFontFilename);
            this.descriptionFontFilename  = ta.getString(R.styleable.StateView_stateDescriptionFontFilename);
            this.actionButtonFontFilename = ta.getString(R.styleable.StateView_stateActionButtonFontFilename);

            this.title                    = ta.getString(R.styleable.StateView_stateTitle);
            this.titleTextSize            = ta.getDimensionPixelSize(R.styleable.StateView_stateTitleTextSize, NO_VALUE);
            this.titleColor               = ta.getColor(R.styleable.StateView_stateTitleColor, NO_VALUE);

            this.description              = ta.getString(R.styleable.StateView_stateDescription);
            this.descriptionColor         = ta.getColor(R.styleable.StateView_stateDescriptionColor, NO_VALUE);

            this.gravity                  = ComponentGravity.fromId(ta.getInt(R.styleable.StateView_stateMode, ComponentGravity.CENTER.getId()));
            this.actionButtonText         = ta.getString(R.styleable.StateView_stateActionButtonText);
            this.actionButtonTextColor    = ta.getColor(R.styleable.StateView_stateActionButtonTextColor, NO_VALUE);
            this.actionButtonColor        = ta.getColor(R.styleable.StateView_stateActionButtonColor, NO_VALUE);
        }

        changeBackgroundColor(backgroundColor); // white is the default color set in theme.
        changeState(state, stateChangeDelayInMillis); // normal state by default.
        changeIcon(iconRes, false); // hidden by default.
        changeMainProgressEnabled(mainProgressEnabled); // hidden by default.

        changeTitle(title, AnimationType.NO_ANIMATION); // black is the default color set in theme.
        changeTitleTextSize(titleTextSize, true); // 28sp
        changeTitleColor(titleColor); // black is the default color set in theme.

        changeDescription(description, AnimationType.NO_ANIMATION);
        changeDescriptionColor(descriptionColor); // white is the default color set in theme.
        changeSmallProgressEnabled(smallProgressEnabled); // hidden by default.

        changeActionButtonText(actionButtonText); // hidden by default.
        changeActionButtonTextColor(actionButtonTextColor); // white is the default color set in theme.
        changeActionButtonColor(actionButtonColor); // black is the default color set in theme.
        changeOnActionButtonClick(this, onActionButtonClickListener); // nothing happens by default.

        changeProgressStrokeColor(progressStrokeColor); // black is the default color set in theme.
        changeProgressBackgroundColor(progressBackgroundColor); // transparent is the default color set in theme.

        changeGravity(gravity); // centralized.

        changeTitleFont(titleFontFilename); // uses the app theme font by default.
        changeDescriptionFont(descriptionFontFilename); // uses the app theme font by default.
        changeActionButtonFont(actionButtonFontFilename); // uses the app theme font by default.

        if (attrs != null){
            ta.recycle();
        }
    }


    /**
     * Setup Background Color change
     */
    private void changeBackgroundColor(int backgroundColor) {
        if (backgroundColor == NO_VALUE) return;
        ll_root.setBackgroundColor(backgroundColor);
    }


    /**
     * Setup Action Button
     */
    private void changeActionButtonText(String actionButtonText) {
        if (btn_action == null) return;

        if (actionButtonText == null) {
            btn_action.setVisibility(View.GONE);
            return;
        }

        btn_action.setText(actionButtonText);
        btn_action.setVisibility(View.VISIBLE);
    }

    public void changeActionButtonColor(@ColorInt int actionButtonColor){
        if (actionButtonColor == NO_VALUE) return;
        if (btn_action != null) {
            /*
             * If you want to set colors for disabled, unfocused, unchecked states etc. just negate the state.
             *
             * @param actionButtonColor
             * @see {http://developer.android.com/reference/android/R.attr.html#state_above_anchor} for a list of available states.
             */
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_enabled}, // enabled
                    new int[] {-android.R.attr.state_enabled}, // disabled
            };

            int[] colors = new int[] {
                    actionButtonColor,
                    Color.LTGRAY,
            };

            ColorStateList backgroundTintList = new ColorStateList(states, colors);
            btn_action.setBackgroundTintList(backgroundTintList);

            if (actionButtonTextColor == NO_VALUE) {
                boolean useWhiteTextColor = this.isColorDark(actionButtonColor);
                changeActionButtonTextColor(useWhiteTextColor ? Color.WHITE : Color.BLACK);
            }
        }
    }

    public void changeActionButtonTextColor(@ColorInt int actionButtonTextColor){
        if (actionButtonTextColor == NO_VALUE) return;
        if (btn_action != null) {

            /*
             * If you want to set colors for disabled, unfocused, unchecked states etc. just negate the state.
             *
             * @param actionButtonColor
             * @see {http://developer.android.com/reference/android/R.attr.html#state_above_anchor} for a list of available states.
             */
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_enabled}, // enabled
                    new int[] {-android.R.attr.state_enabled}, // disabled
            };

            int[] colors = new int[] {
                    actionButtonTextColor,
                    Color.DKGRAY,
            };

            ColorStateList textColorList = new ColorStateList(states, colors);
            btn_action.setTextColor(textColorList);
        }
    }

    private void changeActionButtonEnabled(boolean enabled) {
        if (btn_action != null) btn_action.setEnabled(enabled);
    }

    private void changeOnActionButtonClick(StateView stateView, OnActionButtonClickListener onActionButtonClickListener){
        if (btn_action == null) return;
        if (onActionButtonClickListener == null) {
            // remove the click action listener.
            btn_action.setOnClickListener(null);
        } else {
            // add the click action listener.
            btn_action.setOnClickListener(v -> onActionButtonClickListener.OnActionButtonClick(stateView, v));
        }
    }

    // TODO
    //    private void changeActionCornerRadius(int cornerRadius) {
    //        if (btn_action == null) return;
    //        btn_action.setCornerRadius(cornerRadius);
    //    }



    /**
     * Setup Description
     */
    private void changeDescription(String message, AnimationType descriptionTextChangeAnimationType) {
        if (tv_description == null) return;

        if (message != null) {
            tv_description.setVisibility(View.VISIBLE);

            switch (descriptionTextChangeAnimationType) {
                case NO_ANIMATION:
                    tv_description.setText(message);
                    break;

                case SLIDE_TO_TOP:
                    AnimationUtils.TextView.changeTextWithSlideToTop(tv_description, message);
                    break;

                case SLIDE_TO_BOTTOM:
                    AnimationUtils.TextView.changeTextWithSlideToBottom(tv_description, message);
                    break;

                case FADE:
                    AnimationUtils.TextView.changeTextWithFade(tv_description, message);
                    break;
            }

        } else {
            tv_description.setVisibility(View.GONE);
        }
    }

    private void changeDescriptionColor(@ColorInt int descriptionColor){
        if (descriptionColor == NO_VALUE) return;
        if (tv_description != null) tv_description.setTextColor(descriptionColor);
    }



    /**
     * Setup Title
     */
    private void changeTitle(String title, AnimationType titleTextChangeAnimationType){
        if (tv_title == null) return;

        if (title != null) {
            if (tv_title.getVisibility() != View.VISIBLE){
                tv_title.setVisibility(View.VISIBLE);
            }

            switch (titleTextChangeAnimationType) {
                case NO_ANIMATION:
                    tv_title.setText(title);
                    break;

                case SLIDE_TO_TOP:
                    AnimationUtils.TextView.changeTextWithSlideToTop(tv_title, title);
                    break;

                case SLIDE_TO_BOTTOM:
                    AnimationUtils.TextView.changeTextWithSlideToBottom(tv_title, title);
                    break;

                case FADE:
                    AnimationUtils.TextView.changeTextWithFade(tv_title, title);
                    break;
            }

        } else {
            tv_title.setVisibility(View.GONE);
        }
    }

    private void changeTitleTextSize(int textSize, boolean fromXML) {
        if (textSize == NO_VALUE) return;
        if (tv_title != null) {
            if (textSize > 0) {
                if (fromXML) {
                    tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

                } else {
                    tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }
            } else {
                tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, DEFAULT_TITLE_TEXTSIZE); // default
            }
        }
    }

    private void changeTitleColor(@ColorInt int titleColor){
        if (titleColor == NO_VALUE) return;
        if (tv_title != null) tv_title.setTextColor(titleColor);
    }


    /**
     * Setup Icon
     */
    private void changeIcon(@Nullable @DrawableRes Integer iconRes, boolean isGif){
        if (iv_mainIcon == null) return;

        if (iconRes == null) {
            iv_mainIcon.setImageBitmap(null);
            iv_mainIcon.setVisibility(View.GONE);
            return;
        }

        if (!isGif) {
            iv_mainIcon.setImageResource(iconRes);

        } else {
            Glide.with(ctx)
                    .asGif()
                    .load(iconRes)
                    .into(iv_mainIcon);
        }
        iv_mainIcon.setVisibility(View.VISIBLE);
    }


    /**
     * Setup progress indicator
     */
    private void changeMainProgressEnabled(boolean enabled) {
        if (enabled) {
            if (isInEditMode()) {
                cpv_mainProgress.setVisibility(View.VISIBLE);
                iv_mainIcon.setVisibility(View.GONE);

            } else {
                cpv_mainProgress.animate()
                        .withStartAction(() -> {
                            iv_mainIcon.setVisibility(View.GONE);
                            cpv_mainProgress.resumeIndeterminateAnimation();
                        })
                        .alpha(1f)
                        .setDuration(220)
                        .withEndAction(() -> cpv_mainProgress.setVisibility(View.VISIBLE))
                        .setInterpolator(new AccelerateInterpolator())
                        .start();
            }

        } else {
            if (isInEditMode()) {
                cpv_mainProgress.setVisibility(View.GONE);
                if (iconRes != null) iv_mainIcon.setVisibility(View.VISIBLE);

            } else {
                cpv_mainProgress.animate()
                        .alpha(0f)
                        .setDuration(230)
                        .withEndAction(() -> {
                            cpv_mainProgress.pauseIndeterminateAnimation(true);
                            cpv_mainProgress.setVisibility(View.GONE);
                            if (iconRes != null) iv_mainIcon.setVisibility(View.VISIBLE);
                        })
                        .setInterpolator(new AccelerateInterpolator())
                        .start();
            }
        }
    }

    private void changeSmallProgressEnabled(boolean enabled) {
        if (enabled) {
            if (isInEditMode()) {
                cpv_smallProgress.setVisibility(View.VISIBLE);

            } else {
                cpv_smallProgress.animate()
                        .withStartAction(() -> cpv_smallProgress.resumeIndeterminateAnimation())
                        .alpha(1f)
                        .setDuration(220)
                        .withEndAction(() -> cpv_smallProgress.setVisibility(View.VISIBLE))
                        .setInterpolator(new AccelerateInterpolator())
                        .start();
            }
        } else {
            if (isInEditMode()) {
                cpv_smallProgress.setVisibility(View.GONE);

            } else {
                cpv_smallProgress.animate()
                        .alpha(0f)
                        .setDuration(230)
                        .withEndAction(() -> {
                            cpv_smallProgress.pauseIndeterminateAnimation(true);
                            cpv_smallProgress.setVisibility(View.GONE);
                        })
                        .setInterpolator(new AccelerateInterpolator())
                        .start();
            }

        }
    }

    private void changeProgressStrokeColor(@ColorInt int strokeColor) {
        if (strokeColor == NO_VALUE) return;
        if (cpv_mainProgress != null) cpv_mainProgress.setProgressStrokeColor(strokeColor);
        if (cpv_smallProgress != null) cpv_smallProgress.setProgressStrokeColor(strokeColor);
    }

    private void changeProgressBackgroundColor(@ColorInt int backgroundColor) {
        if (backgroundColor == NO_VALUE) return;
        if (cpv_mainProgress != null) cpv_mainProgress.setBackgroundColor(backgroundColor);
        if (cpv_smallProgress != null) cpv_smallProgress.setBackgroundColor(backgroundColor);
    }



    /**
     * Setup State changing
     */
    private void changeState(State state, int delayMillis) {
        if (ll_root == null) return;
        switch (state) {
            case NORMAL:
                if (delayMillis > 0) {
                    ll_root.animate()
                            .alpha(0f)
                            .setDuration(143)
                            .setStartDelay(delayMillis)
                            .withEndAction(() -> ll_root.setVisibility(View.GONE));
                } else {
                    ll_root.setVisibility(View.GONE);
                }
                break;

            case ALTERNATE:
                if (delayMillis > 0) {
                    ll_root.animate()
                            .alpha(1f)
                            .setDuration(143)
                            .setStartDelay(delayMillis)
                            .withEndAction(() -> ll_root.setVisibility(View.VISIBLE));
                } else {
                    ll_root.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    /**
     * Change gravity of all components
     */
    private void changeGravity(ComponentGravity gravity) {
        int grvt;

        switch (gravity) {
            case LEFT:
                grvt = Gravity.START;
                break;

            case CENTER:
                grvt = Gravity.CENTER;
                break;

            case RIGHT:
                grvt = Gravity.END;
                break;

            default:
                grvt = Gravity.CENTER;
                break;
        }

//        if (ll_root != null) ll_root.setGravity(grvt);
        if (ll_iconHolder != null) ll_iconHolder.setGravity(grvt);
        if (tv_title != null) tv_title.setGravity(grvt);
        if (ll_descriptionHolder != null) ll_descriptionHolder.setGravity(grvt);
        if (ll_actionButtonHolder != null) ll_actionButtonHolder.setGravity(grvt);
    }



    private boolean isColorDark(@ColorInt int colorInt){
        return ColorUtils.calculateLuminance(colorInt) < 0.6F;
    }




    private void changeTitleFont(String fontFilename) {
        if (TextUtils.isEmpty(fontFilename)) return;

        try {
            Typeface typeface = getFontFromAssets(fontFilename);
            tv_title.setTypeface(typeface);

        } catch (Exception e) {
            Log.e("SttVw.FONTCHANGE.TITLE","Could not change the font to: `" + fontFilename + "`. Please check if the font is stored inside assts/fonts/<fontfilename>");
        }
    }
    private void changeDescriptionFont(String fontFilename) {
        if (TextUtils.isEmpty(fontFilename)) return;

        try {
            Typeface typeface = getFontFromAssets(fontFilename);
            tv_description.setTypeface(typeface);

        } catch (Exception e) {
            Log.e("SttVw.FONTCHANGE.TITLE","Could not change the font to: `" + fontFilename + "`. Please check if the font is stored inside assts/fonts/<fontfilename>");
        }
    }
    private void changeActionButtonFont(String fontFilename) {
        if (TextUtils.isEmpty(fontFilename)) return;

        try {
            Typeface typeface = getFontFromAssets(fontFilename);
            btn_action.setTypeface(typeface);

        } catch (Exception e) {
            Log.e("SttVw.FONTCHANGE.TITLE","Could not find the font: `" + fontFilename + "` stored inside /assets/fonts/. Please look at the file there and try again");
        }
    }


     private Typeface getFontFromAssets(String fontFilename) {
        synchronized (fontCache) {
            if (fontCache.get(fontFilename) != null) {
                SoftReference<Typeface> ref = fontCache.get(fontFilename);
                if (ref != null && ref.get() != null) {
                    return ref.get();
                }
            }

            final String FONTFILE_DIRECTORY = "fonts"; // this is /res/fonts/<put_you_custom_.ttf_font_file_here>
            Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), FONTFILE_DIRECTORY + File.separator + fontFilename);
            fontCache.put(fontFilename, new SoftReference<>(typeface));
            return typeface;
        }
    }

}
