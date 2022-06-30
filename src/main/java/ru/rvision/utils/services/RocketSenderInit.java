package ru.rvision.utils.services;

import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import ru.rvision.utils.model.Attachments;
import ru.rvision.utils.model.AttachmentsField;
import ru.rvision.utils.model.Message;

import javax.inject.Named;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

/**
 * @author Vladimir Troshin on 29.06.2022
 * https://jira.rvision.pro/browse/
 */
@ExportAsService({RocketSenderInit.class})
@Named
@Component
public class RocketSenderInit {
    private final String url = "https://rchat.rvision.ru/api/v1/";
    private String authToken;
    private String userId;

    private final RocketChatRestsV1 rocketChatRestsV1;
    private final Object body;
    private final ObjectMapper objectMapper;
    private final ApplicationProperties applicationProperties;

    public RocketSenderInit(Object body, @ComponentImport ApplicationProperties applicationProperties) {
        this.body = body;
        this.rocketChatRestsV1 = RocketChatRestsV1.ChatPostMessage;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.applicationProperties = applicationProperties;
    }

    void send() throws IOException {
        if (rocketChatRestsV1.isRequireAuth() && (authToken == null || userId == null)) {
            try {
                login();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            if (body != null) {
                try {
                    System.out.println("Body: " + body);
                    System.out.println("token " + authToken + " us " + userId);
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url + rocketChatRestsV1.getMethodName()))
                            .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                            .build();

                    java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

//                    RequestBodyEntity request = Unirest.post(url + rocketChatRestsV1.getMethodName())
//                            .header("X-Auth-Token", authToken)
//                            .header("X-User-Id", userId)
//                            .header("Content-Type", "application/json")
//                            .body(objectMapper.writeValueAsString(body));
//                    HttpResponse<String> response = request.asString();

                    RocketChatResponse rocketChatResponse = objectMapper.readValue(response.body(), RocketChatResponse.class);

                    int respStatus = response.statusCode();

                    if (respStatus != 200) {
                        System.out.println(applicationProperties.getJiraBaseUrl());
                        System.out.println(response.body());
                        notifyAdmins(body, response);
                    }
                } catch (InterruptedException e) {
                    throw new IOException(e);
                } finally {
                    logout();
                }
            }
        }
    }

    private void notifyAdmins(Object body, java.net.http.HttpResponse<String> response) throws IOException {
        System.out.println("C");
        if (rocketChatRestsV1.isRequireAuth() && (authToken == null || userId == null)) {
            try {
                login();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {

            System.out.println("B");
            AttachmentsField attachmentsField = new AttachmentsField();
            attachmentsField.setTitle("Текст запроса");
            attachmentsField.setValue(body.toString());

            AttachmentsField[] fields = new AttachmentsField[1];
            fields[0] = attachmentsField;

            Attachments attachment = Attachments.AttachmentsBuilder.builder()
                    .withColor("#ff000")
                    .withFields(fields)
                    .build();

            Attachments[] attachments = new Attachments[1];
            attachments[0] = attachment;


            Message message = new Message("Сообщение не отправлено");
            message.setChannel("@vtroshin");
            message.setAttachments(attachments);

            /*RequestBodyEntity request = Unirest.post(url + rocketChatRestsV1.getMethodName())
                    .header("X-Auth-Token", authToken)
                    .header("X-User-Id", userId)
                    .header("Content-Type", "application/json")
                    .body(objectMapper.writeValueAsString(message));
            HttpResponse<String> adminResponse = request.asString();
            System.out.println(adminResponse.getBody());*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login() throws IOException {
        HttpResponse<JsonNode> result;
        try {
            String username = "s-rocketchat_jira";
            String password = "PzBeUVj1v5G657QvqXCR";
            result = Unirest.post(url + "login").field("username", username).field("password", password).asJson();

            if (result.getStatus() == 401) {
                throw new IOException("Incorrect user creeds");
            } else if (result.getStatus() != 200) {
                throw new IOException("Failed login with code: " + result.getStatus() + " " + result.getStatusText());
            }

            JSONObject jsonObject = result.getBody().getObject().getJSONObject("data");
            this.authToken = jsonObject.getString("authToken");
            this.userId = jsonObject.getString("userId");

        } catch (UnirestException e) {
            throw new IOException(e);
        }
    }

    protected void logout() {
        try {
            Unirest.post(url + "logout").asString();
            this.authToken = "";
            this.userId = "";
        } catch (UnirestException e) {
        }
    }
}
