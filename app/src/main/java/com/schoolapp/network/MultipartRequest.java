package com.schoolapp.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class MultipartRequest extends Request<String> {
    private static final String LINE = "\r\n", TWO = "--";
    private final String boundary = "apiclient-" + System.currentTimeMillis();
    private final Map<String, String> params;
    private final File file;
    private final Response.Listener<String> listener;
    private final String token;

    public MultipartRequest(String url, File f, Map<String, String> p, String jwt,
                            Response.Listener<String> ok, Response.ErrorListener er) {
        super(Method.POST, url, er);
        file = f;
        params = p;
        listener = ok;
        token = jwt;
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data;boundary=" + boundary;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Map.of("Authorization", "Bearer " + token);
    }

    @Override
    public byte[] getBody() {
        try (var bos = new ByteArrayOutputStream(); var dos = new DataOutputStream(bos);
             var fis = new FileInputStream(file)) {
            for (var e : params.entrySet()) {
                dos.writeBytes(TWO + boundary + LINE);
                dos.writeBytes("Content-Disposition: form-data; name=\"" + e.getKey() + "\"" + LINE + LINE);
                dos.writeBytes(e.getValue() + LINE);
            }
            dos.writeBytes(TWO + boundary + LINE);
            dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + LINE);
            dos.writeBytes("Content-Type: application/octet-stream" + LINE + LINE);
            byte[] buf = new byte[4096];
            int len;
            while ((len = fis.read(buf)) != -1) dos.write(buf, 0, len);
            dos.writeBytes(LINE + TWO + boundary + TWO + LINE);
            return bos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }


    @Override
    protected void deliverResponse(String s) {
        listener.onResponse(s);
    }
}
