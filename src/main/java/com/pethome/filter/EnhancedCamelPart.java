package com.pethome.filter;

import com.pethome.util.StringUtil;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 13 16:19
 */


public class EnhancedCamelPart implements Part {

    private final Part part;

    public EnhancedCamelPart(Part part) {
        this.part = part;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return part.getInputStream();
    }

    @Override
    public String getContentType() {
        return part.getContentType();
    }

    @Override
    public String getName() {
        return StringUtil.snakeToCamel(part.getName());
    }

    @Override
    public String getSubmittedFileName() {
        return part.getSubmittedFileName();
    }

    @Override
    public long getSize() {
        return part.getSize();
    }

    @Override
    public void write(String fileName) throws IOException {
        part.write(fileName);
    }

    @Override
    public void delete() throws IOException {
        part.delete();
    }

    @Override
    public String getHeader(String name) {
        return part.getHeader(name);
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return part.getHeaders(name);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return part.getHeaderNames();
    }
}