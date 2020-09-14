package br.com.intuitiv.templatespring.core.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;

public abstract class BaseCtrl<T> implements Serializable {

  private static final long serialVersionUID = 3253269462850159292L;

  @Autowired ObjectMapper objectMapper;

  protected T patchEntity(final T sourceEntity, final T targetEntity)
      throws JsonProcessingException {

    this.objectMapper.setVisibilityChecker(
        this.objectMapper
            .getSerializationConfig()
            .getDefaultVisibilityChecker()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

    this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    final ObjectReader readerForUpdating = this.objectMapper.readerForUpdating(sourceEntity);
    final String json = new Gson().toJson(targetEntity);
    final T value = readerForUpdating.readValue(json);
    return value;
  }
}
