package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import entities.Sales;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Map<String, Double> totalVendas = new LinkedHashMap<>();

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sales> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sales(Integer.parseInt(fields[0]), 
						Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), 
						Double.parseDouble(fields[4])));     
				
				if (totalVendas.containsKey((fields[2]))) {
					double sum = totalVendas.get(fields[2]);
					totalVendas.put((fields[2]), Double.parseDouble(fields[4]) + sum);
				} else {
					totalVendas.put((fields[2]), Double.parseDouble(fields[4]));
				}

				line = br.readLine();
			}

			System.out.println();
			System.out.println("Total de vendas por vendedor:");

			for (String key : totalVendas.keySet()) {
				System.out.println(key + " - R$ " + String.format("%.2f", totalVendas.get(key)));
			}

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		sc.close();
	}
}
