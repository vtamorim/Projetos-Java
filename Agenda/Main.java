import java.util.*;
import java.time.LocalDate;


public class Main
{
    static Map <String, String> dicionary =  new HashMap<>();
	static Scanner input = new Scanner(System.in);
	static Set<String> keys = dicionary.keySet();
	static LocalDate data_atual = LocalDate.now();
	static Set<String> chave_prox= new HashSet<>();
	static int dia;
	static int mes;
	static int ano;
	static int dia_prox;
    static int mes_prox;
    static int ano_prox;
    static String dataFormatada;
    
    static void ad_evento() {
        System.out.println("Qual Evento você quer adicionar?");
        
        System.out.println("Nome do Evento:");
        String name_event = input.nextLine();

        System.out.println("Data do Evento: (ex: 2025/10/25)");
        String data_event = input.nextLine();

        String[] dataParts = data_event.split("/");
        dia = Integer.parseInt(dataParts[2]);
        mes = Integer.parseInt(dataParts[1]);
        ano = Integer.parseInt(dataParts[0]);

        // Converte a data para uma string formatada
        String dataFormatada = String.format("%04d/%02d/%02d", ano, mes, dia);

        // Adiciona o evento ao mapa estático
        dicionary.put(name_event, dataFormatada);

        System.out.println(name_event + "; Data: " + dicionary.get(name_event));
    }

    static void ed_evento(){
        System.out.println("Qual Evento você queria Mudar?");
        for (Map.Entry<String, String> eventos : dicionary.entrySet()) {
            System.out.println(eventos.getKey() + " " + eventos.getValue());
        }
        
        String key_edit = input.nextLine();
        if (dicionary.containsKey(key_edit)){
            String datetime_old = dicionary.get(key_edit);
            dicionary.remove(key_edit);
            String key_new = input.nextLine();
            dicionary.put(key_new,datetime_old);
            
        }
        else{
            System.out.println("Erro na Validação");
        }
    }
    
    
    static void prox_evento() {
        System.out.println("Data atual: " + data_atual);

        if (dicionary.isEmpty()) {
            System.out.println("Nenhum evento foi adicionado ainda.");
            return;
        }

        // Aqui você pode armazenar a data mais próxima inicializada como data longe no futuro
        LocalDate dataProxima = LocalDate.of(3000, 1, 1);
        String eventoProximo = "";

        for (Map.Entry<String, String> evento : dicionary.entrySet()) {
            String data_event = evento.getValue(); // Ex: "2025/05/22"
            
            // Divide a string e pega ano, mes e dia
            String[] dataParts = data_event.split("/");

            int ano_event = Integer.parseInt(dataParts[0]);
            int mes_event = Integer.parseInt(dataParts[1]);
            int dia_event = Integer.parseInt(dataParts[2]);

            LocalDate dataEvento = LocalDate.of(ano_event, mes_event, dia_event);

            // Verifica se a data do evento é depois da data atual e se é a mais próxima
            if (!dataEvento.isBefore(data_atual) && dataEvento.isBefore(dataProxima)) {
                dataProxima = dataEvento;
                eventoProximo = evento.getKey();
            }
        }

        if (!eventoProximo.isEmpty()) {
            dia = dataProxima.getDayOfMonth();
            mes = dataProxima.getMonthValue();
            ano = dataProxima.getYear();

            System.out.println("Próximo evento: " + eventoProximo + " em " 
                + String.format("%04d-%02d-%02d", ano, mes, dia));
        } else {
            System.out.println("Nenhum evento futuro encontrado.");
        }
    }
    static void comp_day(){
        
        
        if ( dia_prox == dia && mes_prox == mes && ano_prox == ano){
            for (Map.Entry<String, String> entry : dicionary.entrySet()) {
                if (entry.getValue().equals(dataFormatada)){
                    chave_prox.add(entry.getKey());
                }
            
        }
            System.out.println("Compromissos de Hoje: " + chave_prox);
            
        }
        else {
            System.out.println("Inválido");
        }
    }
    
	public static void main(String[] args) {
		
		System.out.println("Opa, aqui é uma Agenda \nO que desejas?");
		boolean condition = true;
		while(condition){
		String[] lista_do = {"Adicionar Evento","Editar Evento","Próximo Evento","Compromissos do Dia"};
		for (int i=0;i < lista_do.length ; i++ ) {
		    System.out.println("[" + i + "]" + lista_do[i]);
		}
		
	    int escolha = input.nextInt();
	    input.nextLine();
	    switch(escolha){
	       case 0:
	            ad_evento();
	            break;
	       case 1:
	            ed_evento();
	            break;
	       case 2: 
				prox_evento();
	            break;
	       case 3:
	           comp_day();
	           break;
	       default:
	           System.out.println("Número inválido");
	    }
		/*
		
		System.out.println("Nome que Deseja Cadastrar:");
		String name = input.nextLine();
		System.out.println("Aniversário da Pessoa \n (ex: 00/00/0000 : \n Dia/Mês/Ano");
		dicionary.put("Djonga","173");
		
        


		
		//Por enquanto, esse arquivo apenas servirá como teste para um projeto breve
		*/
		
		}
		input.close();
	}
}
