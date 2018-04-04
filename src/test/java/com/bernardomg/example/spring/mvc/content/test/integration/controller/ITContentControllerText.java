/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2017 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.spring.mvc.content.test.integration.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bernardomg.example.spring.mvc.content.test.config.UrlConfig;

/**
 * Integration tests for {@link ExampleEntityListController}, verifying the
 * controller returns text.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:context/application-context.xml",
        "classpath:context/servlet.xml" })
@TestPropertySource({ "classpath:config/persistence-access.properties",
        "classpath:config/service.properties" })
public final class ITContentControllerText {

    /**
     * Mocked MVC context.
     */
    private MockMvc               mockMvc;

    /**
     * Web application context.
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * Default constructor.
     */
    public ITContentControllerText() {
        super();
    }

    /**
     * Sets up the mocked MVC context.
     * <p>
     * It expects all the responses to have the OK (200) HTTP code.
     */
    @Before
    public final void setUpMockContext() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
    }

    /**
     * Verifies that the controller returns the expected text.
     */
    @Test
    public final void testRequest_ExpectedText() throws Exception {
        final MvcResult result; // Request result
        final String text;      // Returned text

        result = mockMvc.perform(getViewRequest()).andReturn();

        text = result.getResponse().getContentAsString();

        // The response is the expected text
        Assert.assertEquals("", text);
    }

    /**
     * Returns a request builder for getting the entities list view.
     * 
     * @return a request builder for the entities list view
     */
    private final RequestBuilder getViewRequest() {
        return MockMvcRequestBuilders.get(UrlConfig.CONTENT)
                .accept(MediaType.TEXT_PLAIN_VALUE);
    }

}
