package com.sda.project.bookingapp.converter;

import com.sda.project.bookingapp.entity.NewsletterEntity;
import com.sda.project.bookingapp.model.NewsletterModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleEntityToModelConverterUnitTest {

    private SimpleEntityToModelConverter simpleEntityToModelConverter;

    @Before
    public void setup() {
        simpleEntityToModelConverter = new SimpleEntityToModelConverter();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void shouldConvertNewsletterEntityToNewsLetterModel() {
        //given
        NewsletterEntity newsletterentity = NewsletterEntity.builder().id(1L).email("merkurisa@hotmail.com").build();

        //when
        NewsletterModel actualNewsletterModel = simpleEntityToModelConverter.newsletterEntityToModel(newsletterentity);

        //then
        Assert.assertEquals("merkurisa@hotmail.com", actualNewsletterModel.getEmail());
        Assert.assertEquals(1L, actualNewsletterModel.getId());
    }

    @Test
    public void should() {
        //given

        //when

        //then
    }
}
