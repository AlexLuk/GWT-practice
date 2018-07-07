package com.epam.gwt.client;

import com.epam.gwt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GithubAnalizer implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    TextBox userNameField = new TextBox();
    PasswordTextBox passwordField = new PasswordTextBox();
    Button loginButton = new Button();
    loginButton.setText("Login");

    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.add(userNameField);
    verticalPanel.add(passwordField);
    verticalPanel.add(loginButton);

    DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Login to server");
    dialogBox.setAnimationEnabled(true);
    dialogBox.add(verticalPanel);

    loginButton.addClickHandler(clickEvent -> {
      dialogBox.hide();
      Window.alert("Successful login " + userNameField.getValue()  + " " + passwordField.getValue());
    });

    RootPanel.get().add(dialogBox);

    dialogBox.show();
    dialogBox.center();
  }

}
