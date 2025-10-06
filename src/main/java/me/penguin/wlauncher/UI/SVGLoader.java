package me.penguin.wlauncher.UI;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class SVGLoader {

    /**
     * Загружает SVG и конвертирует его в BufferedImage заданного размера.
     * @param resourcePath путь к SVG в ресурсах
     * @param width желаемая ширина
     * @param height желаемая высота
     * @return BufferedImage с изображением
     * @throws Exception если файл не найден или произошла ошибка при транскодировании
     */
    public static BufferedImage loadSVG(String resourcePath, int width, int height) throws Exception {
        try (InputStream svgStream = SVGLoader.class.getResourceAsStream(resourcePath)) {
            if (svgStream == null) {
                throw new RuntimeException("SVG not found: " + resourcePath);
            }

            TranscoderInput input = new TranscoderInput(svgStream);
            BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
            transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, (float) width);
            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, (float) height);
            transcoder.transcode(input, null);

            return transcoder.getBufferedImage();
        }
    }
}
