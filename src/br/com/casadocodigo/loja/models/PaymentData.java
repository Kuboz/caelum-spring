package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class PaymentData {

	public PaymentData(BigDecimal value) {
		super();
		this.value = value;
	}

	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
