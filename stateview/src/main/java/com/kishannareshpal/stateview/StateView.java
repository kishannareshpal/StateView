package com.kishannareshpal.stateview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.kishannareshpal.circularprogressview.CircularProgressView;

public class StateView extends ViewSwitcher {

    // default values
    public static final int NO_VALUE = -102;
    private static final int DEFAULT_CONTENT_PADDING = 18; // dp
    private static final int DEFAULT_TITLE_TEXTSIZE = 24; // sp
    private static final int DEFAULT_DESCRIPTION_TEXTSIZE = 16; // sp
    private static final int DEFAULT_ACTIONBUTTON_CORNERRADIUS = 64; // dp

    // Utils
    private Context ctx;

    // Components
    private CardView cv_root;
    private LinearLayout ll_iconHolder, ll_descriptionHolder, ll_actionButtonHolder; // root
    private CircularProgressView cpv_mainProgress, cpv_smallProgress;
    private ImageView iv_mainIcon;
    private TextView tv_title, tv_description;
    private MaterialButton btn_action;
    private Space space;

    private State state;
    private @Nullable @DrawableRes Integer iconRes;
    private boolean stateChangeAnimated = true;
    private CharSequence title, description, actionButtonText;
    private @ColorInt int backgroundColor;
    private @ColorInt int titleTextColor, descriptionTextColor;
    private @ColorInt int actionButtonColor, actionButtonTextColor;
    private AnimationType titleTextChangeAnimationType = AnimationType.NO_ANIMATION,
                   descriptionTextChangeAnimationType = AnimationType.NO_ANIMATION;
    private boolean mainProgressEnabled, smallProgressEnabled;
    private @ColorInt int progressStrokeColor, progressBackgroundColor;
    private ComponentGravity gravity;
    private OnActionButtonClickListener onActionButtonClickListener;
    private int titleTextSize, descriptionTextSize;
    private int actionButtonCornerRadius;
    private int contentPaddingLeft, contentPaddingRight, contentPaddingBottom, contentPaddingTop;
    private @StyleRes int titleTextAppearance, descriptionTextAppearance, actionButtonTextAppearance;


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
     * Change the horizontal gravity of the icon, title, and description with smallProgress all at once.
     * @param gravity {@link ComponentGravity} horizontal gravity.
     */
    public StateView gravity(ComponentGravity gravity) {
        this.gravity = gravity;
        changeGravity(gravity);
        return this;
    }

    /**
     * Change the content padding for individual sides.
     * @param left left padding
     * @param top top padding
     * @param right right padding
     * @param bottom bottom padding
     */
    public StateView contentPadding(int left, int top, int right, int bottom) {
        this.contentPaddingLeft = left;
        this.contentPaddingTop = top;
        this.contentPaddingRight = right;
        this.contentPaddingBottom = bottom;
        changeContentPadding(left, top, right, bottom);
        return this;
    }

    /**
     * Change the content padding for all sides at once.
     * @param padding padding to be applied for all sides.
     */
    public StateView contentPadding(int padding) {
        this.contentPaddingLeft = padding;
        this.contentPaddingTop = padding;
        this.contentPaddingRight = padding;
        this.contentPaddingBottom = padding;
        changeContentPadding(padding, padding, padding, padding);
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
     * Change the state with a fade animation by default.
     * - {@link State#NORMAL} shows the content.
     * - {@link State#ALTERNATE} shows the alternate content, such as empty message.
     *
     * @param state the state to which you want to change.
     * @see StateView#state(State, boolean) if you want to disable or enable the fade animation when changing states.
     */
    public StateView state(State state) {
        this.state = state;

        changeState(state, stateChangeAnimated);
        return this;
    }

    /**
     * Change the state animating as specified.
     * - {@link State#NORMAL} shows the content.
     * - {@link State#ALTERNATE} shows the alternate content, such as empty message.
     *
     * @param state the state to which you want to change.
     * @param animated the delay in milliseconds to wait before changing the
     */
    public StateView state(State state, boolean animated) {
        this.state = state;
        this.stateChangeAnimated = animated;

        changeState(state, animated);
        return this;
    }

    /**
     * Change the title, instantly.
     * @param title the new title, or pass {@code null} to hide the title.
     */
    public StateView title(@Nullable CharSequence title) {
        this.title = title;
        this.titleTextChangeAnimationType = AnimationType.NO_ANIMATION;

        changeTitle(title, titleTextChangeAnimationType);
        return this;
    }

    /**
     * Change the title, with some animation.
     * @param title the new title, or pass {@code null} to hide the title.
     * @param titleTextChangeAnimationType the animation type {@link AnimationType} to use when changing the title
     */
    public StateView title(@Nullable CharSequence title, AnimationType titleTextChangeAnimationType) {
        this.title = title;
        this.titleTextChangeAnimationType = titleTextChangeAnimationType;

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
     * Change the title text color.
     * @param titleTextColor the color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)})
     */
    public StateView titleTextColor(@ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
        changeTitleTextColor(titleTextColor);
        return this;
    }


    /**
     * Change the description text, instantly (without animation).
     * @param description the new description, or pass {@code null} to hide the description.
     */
    public StateView description(@Nullable CharSequence description) {
        this.description = description;
        this.descriptionTextChangeAnimationType = AnimationType.NO_ANIMATION;
        changeDescription(description, descriptionTextChangeAnimationType);
        return this;
    }

    /**
     * Change the description text, with some animation.
     * @param description the new description, or pass {@code null} to hide the description.
     * @param descriptionTextChangeAnimationType the animation type {@link AnimationType} to use when changing the description
     */
    public StateView description(@Nullable CharSequence description, AnimationType descriptionTextChangeAnimationType) {
        this.description = description;
        this.descriptionTextChangeAnimationType = descriptionTextChangeAnimationType;
        changeDescription(description, descriptionTextChangeAnimationType);
        return this;
    }

    /**
     * Change the description text color.
     * @param descriptionTextColor the color you want (use {@link androidx.core.content.ContextCompat#getColor(Context, int)}).
     */
    public StateView descriptionTextColor(@ColorInt int descriptionTextColor) {
        this.descriptionTextColor = descriptionTextColor;
        changeDescriptionTextColor(descriptionTextColor);
        return this;
    }

    /**
     * Change the size of the description text
     * @param descriptionTextSize description text size.
     */
    public StateView descriptionTextSize(int descriptionTextSize) {
        this.descriptionTextSize = descriptionTextSize;
        changeDescriptionTextSize(descriptionTextSize, false);
        return this;
    }


    /**
     * Change the action button text.
     * @param actionButtonText the action button text, or pass {@code null} to hide the button.
     */
    public StateView actionButtonText(@Nullable CharSequence actionButtonText) {
        this.actionButtonText = actionButtonText;
        changeActionButtonText(actionButtonText);
        return this;
    }

    /**
     * Change the action button corner radius (all corners)
     * @param actionButtonCornerRadius corner radius
     */
    public StateView actionButtonCornerRadius(int actionButtonCornerRadius) {
        this.actionButtonCornerRadius = actionButtonCornerRadius;
        changeActionButtonCornerRadius(actionButtonCornerRadius);
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
     * Shows or hides the action button.
     *
     * @param isVisible true, makes it visible. false, hides it.
     */
    public StateView actionButtonIsVisible(boolean isVisible) {
        changeActionButtonVisibility(isVisible);
        return this;
    }




    // Getters

    /**
     * Retrieve the state mode.
     * @return the {@link State} it is currently on.
     */
    public State getState() {
        return this.state;
    }

    /**
     * Retrieve the title text currently set.
     * @return the title text. {@code null} if title is not set.
     */
    public @Nullable CharSequence getTitleText() {
        return this.title;
    }

    /**
     * Retrieve the description text currently set.
     * @return the desription text. {@code null} if description is not set.
     */
    public @Nullable CharSequence getDescriptionText() {
        return  this.description;
    }


    /**
     * Exposes the action button, to allow easy customisation and tweaking.
     * @return {@link MaterialButton} the action button.
     */
    public MaterialButton getActionButton() {
        return this.btn_action;
    }




    // Default Constructor
    public StateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init(ctx, attrs);
    }






    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    public void addView(View child) {
        assertOnlyOneChildView(); // checks if stateview contains more than one view, if it does throw an exception.
        super.addView(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        assertOnlyOneChildView(); // checks if stateview contains more than one view, if it does throw an exception.
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        assertOnlyOneChildView(); // checks if stateview contains more than one view, if it does throw an exception.
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index) {
        assertOnlyOneChildView(); // checks if stateview contains more than one view, if it does throw an exception.
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        assertOnlyOneChildView(); // checks if stateview contains more than one view, if it does throw an exception.
        super.addView(child, width, height);
    }

    /**
     * Main Method
     */
    private void init(Context ctx, @Nullable AttributeSet attrs){
        inflate(ctx, R.layout.stateview_layout, this);
        TypedArray ta = null;

        // Init Inside Components
        cv_root               = findViewById(R.id.cv_root);
        space                 = findViewById(R.id.space);
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
            ta                              = getContext().obtainStyledAttributes(attrs, R.styleable.StateView);
            this.backgroundColor            = ta.getColor(R.styleable.StateView_stateBackgroundColor, NO_VALUE);

            this.state                      = State.fromId(ta.getInt(R.styleable.StateView_stateMode, State.NORMAL.getId()));
            this.iconRes                    = ta.getResourceId(R.styleable.StateView_stateIcon, NO_VALUE);
            if (this.iconRes == NO_VALUE) this.iconRes = null;
            this.gravity                    = ComponentGravity.fromId(ta.getInt(R.styleable.StateView_stateGravity, ComponentGravity.CENTER.getId()));

            this.contentPaddingLeft         = ta.getDimensionPixelSize(R.styleable.StateView_stateContentPaddingLeft, DEFAULT_CONTENT_PADDING);
            this.contentPaddingTop          = ta.getDimensionPixelSize(R.styleable.StateView_stateContentPaddingTop, DEFAULT_CONTENT_PADDING);
            this.contentPaddingRight        = ta.getDimensionPixelSize(R.styleable.StateView_stateContentPaddingRight, DEFAULT_CONTENT_PADDING);
            this.contentPaddingBottom       = ta.getDimensionPixelSize(R.styleable.StateView_stateContentPaddingBottom, DEFAULT_CONTENT_PADDING);

            this.mainProgressEnabled        = ta.getBoolean(R.styleable.StateView_stateMainProgressEnabled, false);
            this.smallProgressEnabled       = ta.getBoolean(R.styleable.StateView_stateSmallProgressEnabled, false);
            this.progressStrokeColor        = ta.getColor(R.styleable.StateView_stateProgressStrokeColor, NO_VALUE);
            this.progressBackgroundColor    = ta.getColor(R.styleable.StateView_stateProgressBackgroundColor, NO_VALUE);

            this.title                      = ta.getString(R.styleable.StateView_stateTitle);
            this.titleTextSize              = ta.getDimensionPixelSize(R.styleable.StateView_stateTitleTextSize, NO_VALUE);
            this.titleTextColor             = ta.getColor(R.styleable.StateView_stateTitleTextColor, NO_VALUE);

            this.description                = ta.getString(R.styleable.StateView_stateDescription);
            this.descriptionTextSize        = ta.getDimensionPixelSize(R.styleable.StateView_stateDescriptionTextSize, NO_VALUE);
            this.descriptionTextColor       = ta.getColor(R.styleable.StateView_stateDescriptionTextColor, NO_VALUE);

            this.actionButtonText           = ta.getString(R.styleable.StateView_stateActionButtonText);
            this.actionButtonTextColor      = ta.getColor(R.styleable.StateView_stateActionButtonTextColor, NO_VALUE);
            this.actionButtonCornerRadius   = ta.getDimensionPixelSize(R.styleable.StateView_stateActionButtonCornerRadius, NO_VALUE);
            this.actionButtonColor          = ta.getColor(R.styleable.StateView_stateActionButtonColor, NO_VALUE);

            this.titleTextAppearance        = ta.getResourceId(R.styleable.StateView_stateTitleTextAppearance, NO_VALUE);
            this.descriptionTextAppearance  = ta.getResourceId(R.styleable.StateView_stateDescriptionTextAppearance, NO_VALUE);
            this.actionButtonTextAppearance = ta.getResourceId(R.styleable.StateView_stateActionButtonTextAppearance, NO_VALUE);
        }

        changeContentPadding(contentPaddingLeft, contentPaddingTop, contentPaddingRight, contentPaddingBottom);
        changeBackgroundColor(backgroundColor); // white is the default color set in theme.
        changeIcon(iconRes, false); // hidden by default.
        changeMainProgressEnabled(mainProgressEnabled); // hidden by default.

        changeTitle(title, AnimationType.NO_ANIMATION); // black is the default color set in theme.
        changeTitleTextSize(titleTextSize, true); // 28sp
        changeTitleTextColor(titleTextColor); // black is the default color set in theme.
        changeTitleTextAppearance(titleTextAppearance); // default set in theme.


        changeDescription(description, AnimationType.NO_ANIMATION);
        changeDescriptionTextSize(descriptionTextSize, true);
        changeDescriptionTextColor(descriptionTextColor); // white is the default color set in theme.
        changeDescriptionTextAppearance(descriptionTextAppearance); // default set in theme.
        changeSmallProgressEnabled(smallProgressEnabled); // hidden by default.

        changeActionButtonText(actionButtonText); // hidden by default.
        changeActionButtonTextColor(actionButtonTextColor); // white is the default color set in theme.
        changeActionButtonColor(actionButtonColor); // black is the default color set in theme.
        changeActionButtonCornerRadius(actionButtonCornerRadius);
        changeActionButtonTextAppearance(actionButtonTextAppearance); // default set in theme.
        changeOnActionButtonClick(this, onActionButtonClickListener); // nothing happens by default.

        changeProgressStrokeColor(progressStrokeColor); // black is the default color set in theme.
        changeProgressBackgroundColor(progressBackgroundColor); // transparent is the default color set in theme.

        changeGravity(gravity); // centralized.

        if (attrs != null){
            ta.recycle();
        }

    }


    /**
     * Checks if the StateView contains more than one child.
     * @throws IllegalStateException when more than one child view is set to StateView.
     */
    private void assertOnlyOneChildView() {
        if (getChildCount() >= 2) {
            throw new IllegalStateException("Cannot add more than 1 child view to StateView. Hint: Wrap your views in one Layout View and set it as a child of StateView.");
        }
    }


    /**
     * Setup Background Color change
     */
    private void changeBackgroundColor(int backgroundColor) {
        if (backgroundColor == NO_VALUE) return;
        cv_root.setCardBackgroundColor(backgroundColor);
    }

    /**
     * Setup content padding
     */
    private void changeContentPadding(int left, int top, int right, int bottom) {
        if (cv_root == null) return;
        cv_root.setContentPadding(left, top, right, bottom);
    }


    /**
     * Setup Title
     */
    private void changeTitle(CharSequence title, AnimationType titleTextChangeAnimationType){
        if (tv_title == null) return;

        if (!TextUtils.isEmpty(title)) {
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

            if (iconRes == null || !mainProgressEnabled) {
                space.setVisibility(GONE);

            } else {
                space.setVisibility(VISIBLE);
            }

        } else {
            tv_title.setVisibility(View.GONE);
            space.setVisibility(GONE);
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

    private void changeTitleTextColor(@ColorInt int titleColor){
        if (titleColor == NO_VALUE) return;
        if (tv_title != null) tv_title.setTextColor(titleColor);
    }

    private void changeTitleTextAppearance(@StyleRes int titleTextAppearance) {
        if (titleTextAppearance == NO_VALUE) return;
        if (tv_title != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv_title.setTextAppearance(titleTextAppearance);
        } else {
            tv_title.setTextAppearance(ctx, titleTextAppearance);
        }
    }



    /**
     * Setup Description
     */
    private void changeDescription(CharSequence message, AnimationType descriptionTextChangeAnimationType) {
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

    private void changeDescriptionTextSize(int textSize, boolean fromXML) {
        if (textSize == NO_VALUE) return;
        if (tv_description != null) {
            if (textSize > 0) {
                if (fromXML) {
                    tv_description.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

                } else {
                    tv_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }
            } else {
                tv_description.setTextSize(TypedValue.COMPLEX_UNIT_PX, DEFAULT_DESCRIPTION_TEXTSIZE); // default
            }
        }
    }

    private void changeDescriptionTextColor(@ColorInt int descriptionColor){
        if (descriptionColor == NO_VALUE) return;
        if (tv_description != null) tv_description.setTextColor(descriptionColor);
    }

    private void changeDescriptionTextAppearance(@StyleRes int descriptionTextAppearance) {
        if (descriptionTextAppearance == NO_VALUE) return;
        if (tv_description != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv_description.setTextAppearance(descriptionTextAppearance);
        } else {
            tv_description.setTextAppearance(ctx, descriptionTextAppearance);
        }
    }




    /**
     * Setup Action Button
     */
    private void changeActionButtonText(CharSequence actionButtonText) {
        if (btn_action == null) return;

        if (actionButtonText == null) {
            changeActionButtonVisibility(false); // make the action button invisible/gone
            return;
        }

        btn_action.setText(actionButtonText);
        changeActionButtonVisibility(true); // make the action button visible
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
                    new int[] {-android.R.attr.state_enabled} // disabled
            };

            int[] colors = new int[] {
                    actionButtonTextColor,
                    Color.DKGRAY
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

    private void changeActionButtonVisibility(boolean isVisible) {
        if (btn_action == null) return;
        int visibility = isVisible ? View.VISIBLE : View.GONE;
        btn_action.setVisibility(visibility);
    }

    private void changeActionButtonCornerRadius(int actionButtonCornerRadius) {
        if (actionButtonCornerRadius == NO_VALUE) return;
        if (btn_action != null) {
            if (actionButtonCornerRadius > 0) {
                btn_action.setCornerRadius(actionButtonCornerRadius);
            } else {
                btn_action.setCornerRadius(DEFAULT_ACTIONBUTTON_CORNERRADIUS);

            }
        }
    }

    private void changeActionButtonTextAppearance(@StyleRes int actionButtonTextAppearance) {
        if (actionButtonTextAppearance == NO_VALUE) return;
        if (btn_action != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btn_action.setTextAppearance(actionButtonTextAppearance);
        } else {
            btn_action.setTextAppearance(ctx, actionButtonTextAppearance);
        }
    }


    /**
     * Setup Icon
     */
    private void changeIcon(@Nullable @DrawableRes Integer iconRes, boolean isGif){
        if (iv_mainIcon == null) return;

        if (iconRes == null) {
            iv_mainIcon.setImageBitmap(null);
            iv_mainIcon.setVisibility(View.GONE);
            space.setVisibility(GONE);
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

        if (!mainProgressEnabled) {
            space.setVisibility(GONE);

        } else {
            space.setVisibility(VISIBLE);
        }
    }


    /**
     * Setup progress indicator
     */
    private void changeMainProgressEnabled(boolean enabled) {
        if (cpv_mainProgress == null) return;
        if (enabled) {
            if (isInEditMode()) {
                cpv_mainProgress.setVisibility(View.VISIBLE);
                if (iv_mainIcon == null) return;
                iv_mainIcon.setVisibility(View.GONE);

            } else {
                cpv_mainProgress.animate()
                        .withStartAction(() -> {
                            cpv_mainProgress.resumeIndeterminateAnimation();
                            if (iv_mainIcon == null) return;
                            iv_mainIcon.setVisibility(View.GONE);
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
                if (iv_mainIcon == null) return;
                if (iconRes != null) iv_mainIcon.setVisibility(View.VISIBLE);

            } else {
                cpv_mainProgress.animate()
                        .alpha(0f)
                        .setDuration(230)
                        .withEndAction(() -> {
                            cpv_mainProgress.pauseIndeterminateAnimation(true);
                            cpv_mainProgress.setVisibility(View.GONE);
                            if (iv_mainIcon == null) return;
                            if (iconRes != null) iv_mainIcon.setVisibility(View.VISIBLE);
                        })
                        .setInterpolator(new AccelerateInterpolator())
                        .start();
            }
        }

        if (iconRes == null || !mainProgressEnabled) {
            space.setVisibility(GONE);

        } else {
            space.setVisibility(VISIBLE);
        }
    }

    private void changeSmallProgressEnabled(boolean enabled) {
        if (cpv_smallProgress == null) return;
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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        changeState(this.state, this.stateChangeAnimated);
    }

    /**
     * Setup State changing
     */
    private void changeState(State state, boolean animated) {

        if (!isInEditMode()) {
            if (animated) {

                Animation fadeOutAnimation = new AlphaAnimation(1f, .2f);
                fadeOutAnimation.setDuration(139);
                setOutAnimation(fadeOutAnimation);

            } else {
                setOutAnimation(null);
                setInAnimation(null);
            }
        }

        switch (state) {
            case NORMAL:
                setDisplayedChild(1);
                tv_title.setVisibility(GONE);
                tv_description.setVisibility(GONE);
                iv_mainIcon.setVisibility(GONE);
                cpv_smallProgress.setVisibility(GONE);
                cpv_mainProgress.setVisibility(GONE);
                btn_action.setVisibility(GONE);
                space.setVisibility(GONE);
                break;

            case ALTERNATE:
                setDisplayedChild(0);
                if (title != null) tv_title.setVisibility(VISIBLE);
                if (description != null) tv_description.setVisibility(VISIBLE);
                if (iconRes != null) iv_mainIcon.setVisibility(VISIBLE);
                if (smallProgressEnabled) cpv_smallProgress.setVisibility(VISIBLE);
                if (mainProgressEnabled) cpv_mainProgress.setVisibility(VISIBLE);
                if (actionButtonText != null) btn_action.setVisibility(VISIBLE);
                if ((iconRes != null || mainProgressEnabled) && (title != null)) space.setVisibility(VISIBLE);
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
        if (tv_description != null) tv_description.setGravity(grvt);
        if (ll_descriptionHolder != null) ll_descriptionHolder.setGravity(grvt);
        if (ll_actionButtonHolder != null) ll_actionButtonHolder.setGravity(grvt);
    }



    private boolean isColorDark(@ColorInt int colorInt){
        return ColorUtils.calculateLuminance(colorInt) < 0.6F;
    }

}
