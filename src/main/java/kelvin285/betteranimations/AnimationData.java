package kelvin285.betteranimations;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import org.jetbrains.annotations.NotNull;

public class AnimationData {
    private KeyframeAnimation animation;
    private final float speed;
    private final int fade;

    public AnimationData(@NotNull KeyframeAnimation animation, float speed, int fade) {
        this.animation = animation;
        this.speed = speed;
        this.fade = fade;
    }

    public KeyframeAnimation getKeyframeAnimation() {
        return this.animation;
    }
    public void setKeyframeAnimation(KeyframeAnimation keyframeAnimation) {
        this.animation = keyframeAnimation;
    }

    public void setAnimation(ModifierLayer<IAnimation> animationContainer) {
        if(animationContainer.size() > 0) {
            animationContainer.removeModifier(0);
        }

        if(this.speed != 1.0f) {
            animationContainer.addModifier(new SpeedModifier(this.speed), 0);
        }

        IAnimation animationPlayer = new KeyframeAnimationPlayer(this.animation);

        if(this.fade == 0) {
            animationContainer.setAnimation(animationPlayer);
        } else {
            animationContainer.replaceAnimationWithFade(
                    AbstractFadeModifier.standardFadeIn(this.fade, Ease.LINEAR), animationPlayer
            );
        }
    }
}
