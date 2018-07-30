package cn.weiyii.uilib.arch;

import cn.weiyii.uilib.R;

/**
 * 转场动画控制
 */
public final class TransitionConfig {

    public final int enter, out, popEnter, popOut;

    public TransitionConfig(int enter, int popOut) {
        this(enter, 0, 0, popOut);
    }

    public TransitionConfig(int enter, int out, int popEnter, int popOut) {
        this.enter = enter;
        this.out = out;
        this.popEnter = popEnter;
        this.popOut = popOut;
    }

    /**
     * 界面跳转动画
     */
    interface Config {

        // === 提供两种默认的进入退出动画 ===
        TransitionConfig SLIDE_TRANSITION_CONFIG = new TransitionConfig(
                R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right);

        TransitionConfig SCALE_TRANSITION_CONFIG = new TransitionConfig(
                R.anim.scale_enter, R.anim.slide_still,
                R.anim.slide_still, R.anim.scale_exit);
    }
}
