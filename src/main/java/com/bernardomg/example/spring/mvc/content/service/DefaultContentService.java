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

package com.bernardomg.example.spring.mvc.content.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardomg.example.spring.mvc.content.model.ExampleEntity;
import com.bernardomg.example.spring.mvc.content.model.persistence.DefaultExampleEntity;
import com.bernardomg.example.spring.mvc.content.repository.ExampleEntityRepository;

/**
 * Default implementation of the example entity service.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Service
public class DefaultContentService implements ContentService {

    /**
     * Name for the view.
     */
    private static final String           VIEW = "views/list";

    /**
     * Repository for the domain entities handled by the service.
     */
    private final ExampleEntityRepository entityRepository;

    /**
     * Constructs an entities service with the specified repository.
     *
     * @param repository
     *            the repository for the entity instances
     */
    @Autowired
    public DefaultContentService(final ExampleEntityRepository repository) {
        super();

        entityRepository = checkNotNull(repository,
                "Received a null pointer as repository");
    }

    @Override
    public final Iterable<DefaultExampleEntity> getAllEntities() {
        return getExampleEntityRepository().findAll();
    }

    @Override
    public final String getStringContent() {
        final String result;
        final Iterable<DefaultExampleEntity> read;

        read = getExampleEntityRepository().findAll();
        result = StreamSupport.stream(read.spliterator(), false)
                .map(ExampleEntity::getName).collect(Collectors.joining(","));

        return result;
    }

    @Override
    public final String getViewContent() {
        return VIEW;
    }

    /**
     * Returns the repository used to acquire the domain entities.
     *
     * @return the repository used to acquire the domain entities
     */
    private final ExampleEntityRepository getExampleEntityRepository() {
        return entityRepository;
    }

}
