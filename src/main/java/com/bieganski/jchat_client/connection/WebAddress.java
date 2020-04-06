package com.bieganski.jchat_client.connection;

class WebAddress {
    private final String address;
    private final int port;

    public WebAddress(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
