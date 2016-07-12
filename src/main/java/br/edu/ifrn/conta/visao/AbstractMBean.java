/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.edu.ifrn.conta.visao;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Maganed Bean Abstrato de Visao.
 * @author Marcelo Fernandes
 */
public abstract class AbstractMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected void addInfoMessage(String idMessages, String summary, String detail) {
		addMessage(idMessages, FacesMessage.SEVERITY_INFO, summary, detail);
	}

	protected void addWarnMessage(String idMessages, String summary, String detail) {
		addMessage(idMessages, FacesMessage.SEVERITY_WARN, summary, detail);
	}

	protected void addErrorMessage(String idMessages, String summary, String detail) {
		addMessage(idMessages, FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	protected void addMessage(String idMessages, Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(idMessages,
			new FacesMessage(severity, summary, detail));
	}

	@PostConstruct
	protected void init() {
	}

	protected Boolean isRemovingFlash() {
		return false;
	}

	private Flash flash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	protected void flashPut(String key, Object value) {
		Flash flash = flash();
		flash.put(key, value);
	}

	protected Object flashGet(String key) {
		Flash flash = flash();

		if (flash.size() > 0) {
			Object result = flash.get(key);

			if (isRemovingFlash()) {
				flash.values().remove(result);
			}

			return result;
		}
		return null;
	}

	protected String redirect(String url) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		try {
			ec.redirect(url);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		fc.responseComplete();

		return null;
	}

	protected String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}

	protected FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected boolean empty(String s) {
		return s == null || s.trim().isEmpty();
	}

	@SuppressWarnings("rawtypes")
	protected void tratarException(String idMessages, String evento, Exception e) {
		e.printStackTrace();

		if (e instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException) e;
			// adicionar mensagem de falha
			for (ConstraintViolation cv : cve.getConstraintViolations()) {
				String message = evento + " com falha: " + cv.getMessage();
				addErrorMessage(idMessages, message, message);
			}

		}
		else {
			// adicionar mensagem de falha
			String message = null;

			if (!evento.equalsIgnoreCase(e.getMessage())) {
				message = evento + " com falha: " + e.getMessage();
			}
			else {
				message = evento;
			}

			addErrorMessage(idMessages, message, message);
		}

		FacesContext.getCurrentInstance().validationFailed();
	}

	public <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}

}
