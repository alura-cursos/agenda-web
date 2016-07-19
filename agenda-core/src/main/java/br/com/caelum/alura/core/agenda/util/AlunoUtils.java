package br.com.caelum.alura.core.agenda.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlunoUtils {

	private static List<Double> notas = new ArrayList<>(
			Arrays.asList(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 }));

	public static List<Double> getNotas() {
		return Collections.unmodifiableList(notas);
	}
}
