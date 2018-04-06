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

import com.bernardomg.example.spring.mvc.content.model.persistence.DefaultExampleEntity;

/**
 * Service for the content controller.
 * <p>
 * This contains all the operations needed for the controller.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public interface ContentService {

    /**
     * Returns all the entities from the DB.
     * 
     * @return the persisted entities
     */
    public Iterable<DefaultExampleEntity> getAllEntities();

    /**
     * Returns the string content.
     * <p>
     * This will be generated from the DB entities.
     * 
     * @return the string content
     */
    public String getStringContent();

    /**
     * Returns the view for the HTML content.
     * 
     * @return the string content
     */
    public String getViewContent();

}
