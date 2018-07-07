package com.epam.gwt.client;

import com.epam.gwt.client.i18n.GithubAnalyzerConstants;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GithubAnalizer implements EntryPoint {

  private final GithubAnalyzerConstants constants = GWT.create(GithubAnalyzerConstants.class);

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
      String locale = LocaleInfo.getCurrentLocale().getLocaleName();
      Window.alert("Successful login: " + userNameField.getValue() + " "+ locale);
    });

    RootPanel.get().add(dialogBox);

    dialogBox.show();
    dialogBox.center();
  }

}
