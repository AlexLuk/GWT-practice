package com.epam.gwt;

import com.epam.gwt.client.GithubAnalizerTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GithubAnalizerSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for GithubAnalizer");
    suite.addTestSuite(GithubAnalizerTest.class);
    return suite;
  }
}
