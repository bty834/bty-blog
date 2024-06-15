package com.bty.blog.wrapper;

import com.bty.blog.util.ServletUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author bty
 * @date 2022/12/15
 * @since 1.8
 **/
public class MultiReadRequestWrapper extends HttpServletRequestWrapper {


    private final byte[] newBody;

    public MultiReadRequestWrapper(HttpServletRequest request, ServletResponse response) throws IOException {
        super(request);
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        newBody = ServletUtil.extractBodyAsString(request).getBytes(StandardCharsets.UTF_8);
    }


    @Override
    public BufferedReader getReader(){
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream newStream = new ByteArrayInputStream(newBody);
        return new ServletInputStream() {
            @Override
            public int read() {
                return newStream.read();
            }

            @Override
            public int available() {
                return newBody.length;
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
