package com.crowd.animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Animate a fade in left effect on a node
 * 
 * Port of FadeInLeft from Animate.css http://daneden.me/animate by Dan Eden
 * 
 * {@literal @}keyframes fadeInLeft {
 * 	0% {
 * 		opacity: 0;
 * 		transform: translateX(-20px);
 * 	}
 * 	100% {
 * 		opacity: 1;
 * 		transform: translateX(0);
 * 	}
 * }
 * 
 * @author Jasper Potts
 */
@SuppressWarnings("deprecation")
public class FadeInLeftTransition extends ConfigAnimasi {
    /**
     * Create new FadeInLeftTransition
     * 
     * @param node The node to affect
     */
    public FadeInLeftTransition(final Node node) {
        super(
            node,
            TimelineBuilder.create()
                .keyFrames(
                    new KeyFrame(Duration.millis(0),    
                        new KeyValue(node.opacityProperty(), 0, WEB_EASE),
                        new KeyValue(node.translateXProperty(), 180, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(1000),    
                        new KeyValue(node.opacityProperty(), 1, WEB_EASE),
                        new KeyValue(node.translateXProperty(), 0, WEB_EASE)
                    )
                )
                .build()
            );
        setCycleDuration(Duration.seconds(5));
        setDelay(Duration.seconds(0));
    }
}
