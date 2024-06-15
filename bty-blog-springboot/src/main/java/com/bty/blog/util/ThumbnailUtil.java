package com.bty.blog.util;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author bty
 * @date 2023/1/21
 * @since 1.8
 **/
public class ThumbnailUtil {


    public static InputStream createImageThumbnail(InputStream inputStream,String ext) throws IOException {
        BufferedImage bufferedImage = Thumbnails.of(inputStream).scale(0.4, 0.4).outputQuality(0.5).asBufferedImage();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, ext, os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
        return new ByteArrayInputStream(os.toByteArray());

    }
}
