package com.boa.transactionapi.facades;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CustomerFacade {

    String channelName="message-in";

    @Input(channelName)
    MessageChannel inChannel();
}
