package com.pethome.jwt;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应包装器，用于监控响应是否已被使用
 */
class ResponseWrapper extends javax.servlet.http.HttpServletResponseWrapper {
    private final Runnable onResponseUsedCallback;
    private boolean writerUsed = false;
    private boolean outputStreamUsed = false;

    public ResponseWrapper(HttpServletResponse response, Runnable onResponseUsedCallback) {
        super(response);
        this.onResponseUsedCallback = onResponseUsedCallback;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (outputStreamUsed) {
            throw new IllegalStateException("OutputStream has already been called for this response");
        }
        writerUsed = true;
        onResponseUsedCallback.run();
        return super.getWriter();
    }

    @Override
    public javax.servlet.ServletOutputStream getOutputStream() throws IOException {
        if (writerUsed) {
            throw new IllegalStateException("Writer has already been called for this response");
        }
        outputStreamUsed = true;
        onResponseUsedCallback.run();
        return super.getOutputStream();
    }

    @Override
    public void sendError(int sc) throws IOException {
        onResponseUsedCallback.run();
        super.sendError(sc);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        onResponseUsedCallback.run();
        super.sendError(sc, msg);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        onResponseUsedCallback.run();
        super.sendRedirect(location);
    }
}