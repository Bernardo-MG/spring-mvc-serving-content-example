/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2018 the original author or authors.
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

package com.bernardomg.example.spring.mvc.content.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.bernardomg.example.spring.mvc.content.model.ExampleEntity;
import com.bernardomg.example.spring.mvc.content.model.persistence.DefaultExampleEntity;
import com.bernardomg.example.spring.mvc.content.service.ExampleEntityService;

/**
 * Controller for home view.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Controller("/")
public class ContentController {

    /**
     * Name for the view.
     */
    private static final String        VIEW = "views/list";

    /**
     * Example entity service.
     */
    private final ExampleEntityService exampleEntityService;

    /**
     * Constructs a controller with the specified dependencies.
     * 
     * @param service
     *            example entity service
     */
    @Autowired
    public ContentController(final ExampleEntityService service) {
        super();

        exampleEntityService = checkNotNull(service,
                "Received a null pointer as service");
    }

    /**
     * Shows the welcome view.
     * 
     * @param model
     *            model map
     * @return the welcome view
     */
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public final String showHtml(final ModelMap model) {
        // Loads required data into the model
        loadViewModel(model);

        return VIEW;
    }

    /**
     * Shows the entities names as a string.
     * 
     * @param model
     *            model map
     * @return the entities names as a string
     */
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public final String showString(final ModelMap model) {
        final String result;
        final Iterable<DefaultExampleEntity> read;

        read = getExampleEntityService().getAllEntities();
        result = StreamSupport.stream(read.spliterator(), false)
                .map(ExampleEntity::getName).collect(Collectors.joining(","));

        return result;
    }

    /**
     * Returns the example entity service.
     * 
     * @return the example entity service
     */
    private final ExampleEntityService getExampleEntityService() {
        return exampleEntityService;
    }

    /**
     * Loads the model data required for the entities listing view.
     * <p>
     * As the view will list all the entities, it requires these entities as one
     * of the parameters.
     * 
     * @param model
     *            model map
     */
    private final void loadViewModel(final ModelMap model) {
        model.put(ExampleEntityViewConstants.PARAM_ENTITIES,
                getExampleEntityService().getAllEntities());
    }

}
