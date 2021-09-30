package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (!str.contains("msg=")) {
                            continue;
                        }
                        if (str.contains("msg=Exit")) {
                            server.close();
                            break;
                        } else {
                            out.write(str.contains("msg=Hello") ? "Hello\r\n".getBytes() : "What\r\n".getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Socket creation error: ", e);
        }
    }
}