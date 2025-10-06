package me.penguin.wlauncher.UI;

import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.image.BufferedImage;

public class BufferedImageTranscoder extends ImageTranscoder {
    private BufferedImage image;

    @Override
    public BufferedImage createImage(int w, int h) {
        return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput out) {
        this.image = img;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
}