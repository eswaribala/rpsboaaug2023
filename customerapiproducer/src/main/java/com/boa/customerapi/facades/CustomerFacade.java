package com.boa.customerapi.facades;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerFacade {

    String channelName="message-out";

    @Output(channelName)
    MessageChannel outChannel();

}
