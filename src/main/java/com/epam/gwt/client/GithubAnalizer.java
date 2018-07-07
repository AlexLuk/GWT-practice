package com.epam.gwt.client;

import com.epam.gwt.client.i18n.GithubAnalizerMessages;
import com.epam.gwt.client.i18n.GithubAnalyzerConstants;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.PopupLogHandler;
import com.google.gwt.logging.client.SimpleRemoteLogHandler;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GithubAnalizer implements EntryPoint {

  private static final Logger LOG = Logger.getLogger("com.epam.gwt.client.GithubAnalizer");

  private final GithubAnalyzerConstants constants = GWT.create(GithubAnalyzerConstants.class);
  private final GithubAnalizerMessages messages = GWT.create(GithubAnalizerMessages.class);

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

    loginButton.addClickHandler(clickEvent -> {
      dialogBox.hide();

      LOG.info(messages.successfululLogin(userNameField.getValue()));
    });

    RootPanel.get().add(dialogBox);

    dialogBox.show();
    dialogBox.center();
  }

}
