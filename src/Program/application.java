package Program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class application {
	public static void main(String [] args) throws ParseException {
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Criando novo formato de data
		//Criação do Trabalhador (Worker)
		System.out.println("Enter department's name: ");
		String departmentName = sc.nextLine();
		System.out.println("Enter Worker Data: ");
		System.out.println("Name: ");
		String workerName = sc.nextLine();
		System.out.println("Level: ");
		String workerLevel = sc.next();
		System.out.println("Base Salary: ");
		double baseSalary = sc.nextDouble();
		//Após dar todas as Informações, cria um novo Objeto Worker, com a Classe Worker
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
		
		//Definindo quantos contratos esse Worker vai ter
		System.out.println("How many contracts to this worker?");
		int n = sc.nextInt();
		
		//Para cada contrato menor ou igual ao número n, adicione mais contratos na variável i
		for(int i = 1; i<=n; i++) {
			System.out.println("Enter contract #" + i + " data:"); //Primeiro Contrato
			System.out.print("Date (DD/MM/YYYY: "); //Inserindo a data no Contrato
			
			Date contractDate = sdf.parse(sc.next()); //Instânciando a data do Contrato, informado no começo do programa
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			HourContract contract = new HourContract(contractDate, valuePerHour, hours); 
			/*Cria um novo contrato,
			*Com as informações dadas acima, 
			*contractDate,
			*valuePerHour,
			*hours,
			*/
			worker.addContract(contract);//Associa esse contrato, ao Worker criado
		}
		
		System.out.println();
		System.out.println("Enter month and year to calculate income (MM/YYYY):" );
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for: " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
		
		sc.close();
	}
}
