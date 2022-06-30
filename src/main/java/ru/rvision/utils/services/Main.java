package ru.rvision.utils.services;

import com.atlassian.jira.component.ComponentAccessor;
import ru.rvision.utils.model.Message;

import java.io.IOException;

/**
 * @author Vladimir Troshin on 30.06.2022
 * https://jira.rvision.pro/browse/
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Message message = new Message();
        message.setChannel("@vtroshin");
        message.setText("ASD");
        RocketSenderInit rocketSenderInit = new RocketSenderInit(message, ComponentAccessor.getApplicationProperties());
        rocketSenderInit.send();
    }
}
