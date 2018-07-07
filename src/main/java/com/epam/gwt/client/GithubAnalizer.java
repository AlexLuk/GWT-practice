package com.epam.gwt.client;

import com.epam.gwt.client.i18n.GithubAnalizerMessages;
import com.epam.gwt.client.i18n.GithubAnalyzerConstants;
import com.epam.gwt.client.services.AccountService;
import com.epam.gwt.client.services.InMemoryAccountService;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GithubAnalizer implements EntryPoint {

    private static final Logger LOG = Logger.getLogger("com.epam.gwt.client.GithubAnalizer");

    private final GithubAnalyzerConstants constants = GWT.create(GithubAnalyzerConstants.class);
    private final GithubAnalizerMessages messages = GWT.create(GithubAnalizerMessages.class);
    private AccountService accountService = new InMemoryAccountService();

    public void onModuleLoad() {
        TextBox userNameField = new TextBox();
        PasswordTextBox passwordField = new PasswordTextBox();
        Button loginButton = new Button(constants.loginButtonText());

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(userNameField);
        verticalPanel.add(passwordField);
        verticalPanel.add(loginButton);

        DialogBox dialogBox = new DialogBox();
        dialogBox.setText(constants.dialogBoxTitle());
        dialogBox.setAnimationEnabled(true);
        dialogBox.add(verticalPanel);

        //REST
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "https://api.github.com/users");
        builder.setHeader("Content-Type", "application/json;charset=utf-8");

        try {
            builder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    int statusCode = response.getStatusCode();
                    if (statusCode == 200) {
                        LOG.log(Level.INFO, response.getText());
                    } else {
                        LOG.log(Level.SEVERE, "Response error code: " + statusCode);
                    }
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    LOG.log(Level.SEVERE, throwable.getMessage());
                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }

        loginButton.addClickHandler(clickEvent -> {
            boolean isLoggedIn = accountService.login(userNameField.getValue(), passwordField.getValue());
            if (isLoggedIn) {
                dialogBox.hide();
                RootPanel.get().remove(dialogBox);
                LOG.info(messages.successfulLogin(userNameField.getValue()));

            } else {
                LOG.info("Logging in failed!");
            }

        });

        RootPanel.get().add(dialogBox);

        dialogBox.show();
        dialogBox.center();
    }

}
