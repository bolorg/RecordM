package pers.adlered.recordm.modules;

import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;

import java.io.IOException;

public class Log extends DefaultHandle {

    @Override
    public void GET(IHttpRequest request, IHttpResponse response) throws IOException {
        response.write("hello world");
    }
}
