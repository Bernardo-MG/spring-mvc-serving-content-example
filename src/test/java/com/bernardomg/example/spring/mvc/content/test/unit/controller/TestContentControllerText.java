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

package com.bernardomg.example.spring.mvc.content.test.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bernardomg.example.spring.mvc.content.controller.ContentController;
import com.bernardomg.example.spring.mvc.content.service.ContentService;
import com.bernardomg.example.spring.mvc.content.test.config.UrlConfig;

/**
 * Integration tests for {@link ExampleEntityListController}, verifying the
 * controller returns text.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class TestContentControllerText {

    /**
     * Mocked MVC context.
     */
    private MockMvc        mockMvc;

    /**
     * Mocked service;
     */
    private ContentService service;

    /**
     * Default constructor.
     */
    public TestContentControllerText() {
        super();
    }

    /**
     * Sets up the mocked MVC context.
     * <p>
     * It expects all the responses to have the OK (200) HTTP code.
     */
    @BeforeEach
    public final void setUpMockContext() {
        service = Mockito.mock(ContentService.class);
        Mockito.when(service.getViewContent()).thenReturn("view:something");
        Mockito.when(service.getStringContent()).thenReturn("abc");

        mockMvc = MockMvcBuilders.standaloneSetup(getController())
                .alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
    }

    /**
     * Verifies that the controller returns the expected text.
     */
    @Test
    public final void testRequest_ExpectedText() throws Exception {
        mockMvc.perform(getViewRequest()).andReturn();

        Mockito.verify(service, Mockito.times(1)).getStringContent();
    }

    /**
     * Returns a controller with mocked dependencies.
     * 
     * @return a mocked controller
     */
    private final ContentController getController() {
        return new ContentController(service);
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
