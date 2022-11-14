package com.soap.mtom_client;

import com.soap.mtom_client.wsdl.Stuff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.soap.mtom_client.wsdl.SendStuffRequest;
import com.soap.mtom_client.wsdl.SendStuffResponse;
public class StuffClient extends WebServiceGatewaySupport {

    private static final Logger LOG = LoggerFactory.getLogger(StuffClient.class);

    public SendStuffResponse sendStuff(Stuff stuff) {

        SendStuffRequest request = new SendStuffRequest();
        request.setStuff(stuff);

        return (SendStuffResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/stuff", request,
                        new SoapActionCallback(
                                "mtom-example-stuff"));
    }
}
