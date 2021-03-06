package com.supinfo.supcrowdfunder.util.rest;

import com.google.gson.*;
import com.supinfo.supcrowdfunder.util.Global;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Robin on 14/12/13.
 */
public abstract class AbstractRestClient {

    protected ArrayList<NameValuePair> params;
    protected ArrayList<NameValuePair> headers;

    public enum RequestMethod {GET, POST}

    protected String url;
    protected int responseCode;
    protected String message;
    protected String response;
    protected Gson gson;
    protected Boolean success = false;

    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public AbstractRestClient(String url) {
        this.url = Global.getAPIUrl() + url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
        this
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache");
        gson = new Gson();
    }

    public AbstractRestClient addParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
        return this;
    }

    public AbstractRestClient addHeader(String name, String value) {
        headers.add(new BasicNameValuePair(name, value));
        return this;
    }

    public AbstractRestClient Execute(RequestMethod method) throws Exception {
        switch (method) {
            case GET: {
                //add parameters
                String combinedParams = "";
                if (!params.isEmpty()) {
                    combinedParams += "?";
                    for (NameValuePair p : params) {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF - 8");
                        if (combinedParams.length() > 1)
                            combinedParams += "&" + paramString;
                        else
                            combinedParams += paramString;
                    }
                }

                HttpGet request = new HttpGet(url + combinedParams);
                //add headers
                for (NameValuePair h : headers)
                    request.addHeader(h.getName(), h.getValue());
                executeRequest(request);
                break;
            }
            case POST: {
                HttpPost request = new HttpPost(url);

                //add headers
                for (NameValuePair h : headers)
                    request.addHeader(h.getName(), h.getValue());

                if (!params.isEmpty())
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                executeRequest(request);
                break;
            }
        }
        return this;
    }

    protected void executeRequest(HttpUriRequest request) {
        HttpClient client = new DefaultHttpClient();

        HttpResponse httpResponse;

        try {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {

                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);

                // Closing the input stream will trigger connection release
                instream.close();
            }

        } catch (ClientProtocolException e) {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
    }

    protected static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public Boolean isSuccess() {
        return success;
    }
}