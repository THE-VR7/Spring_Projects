package com.reactive;

import java.util.function.Consumer;

public class VaccineConsumer implements Consumer<Vaccine> {

	@Override
	public void accept(Vaccine t) {
		System.out.println(t.getName());
		System.out.println(t.isDelivered());
	}

}
