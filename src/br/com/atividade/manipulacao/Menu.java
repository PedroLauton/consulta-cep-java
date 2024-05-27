package br.com.atividade.manipulacao;

import br.com.atividade.modelos.Cep;
import br.com.atividade.modelos.Conexao;
import br.com.atividade.modelos.Filtro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.*;

public class Menu {
    public static void main(String[] args) {
        int escolha1, escolha2;
        Scanner leitura = new Scanner(System.in);
        List<Cep> listaGeral = new ArrayList<>();
        do {
            imprimiMenu();
            escolha1 = leitura.nextInt();
            leitura.nextLine();
            switch (escolha1){
                case 1:
                        System.out.println("\n*****************************");
                        System.out.println("\nDigite o CEP a ser buscado: ");
                        String dado = leitura.nextLine().replace("-", "");
                        if(dado.length() != 8){
                            System.out.println("\nDigite um CEP válido.");
                            continue;
                        }
                    try{
                        String url = "https://viacep.com.br/ws/"+dado+"/json/";
                        Conexao conexao = new Conexao();
                        HttpResponse<String> response = conexao.consultaApi(url);
                        if(response.statusCode() == 400){
                            System.out.println("\nO valor digitado está incorreto. Por favor, digite números e tente novamente.");
                            continue;
                        }
                        Gson gson = new GsonBuilder()
                                .setPrettyPrinting()
                                .create();
                        Filtro filtro = gson.fromJson(response.body(), Filtro.class);
                        if(filtro.erro()){
                            System.out.println("\nO valor digitado está incorreto. Por favor, tente novamente.");
                            continue;
                        }
                        Cep cep = new Cep(filtro);
                        listaGeral.add(cep);
                        System.out.println("\n---------- Endereço ----------\n\n" + cep);
                    }catch (Exception e){
                        System.out.println("\nErro.");
                    }
                    break;
                case 2:
                        List<Cep> listaCep = new ArrayList<>();
                        System.out.println("\n*****************************");
                        System.out.println("\nDigite o estado a ser buscado: ");
                        String estado = leitura.nextLine().toUpperCase(Locale.ROOT);
                        System.out.println("\nDigite a cidade a ser buscada (sem acentuação): ");
                        String cidade = leitura.nextLine().replace(" ", "%20").toLowerCase();
                        System.out.println("\nDigite a rua a ser buscada (sem acentuação): ");
                        String rua = leitura.nextLine().replace(" ", "+").toLowerCase();
                        if(estado.length() != 2){
                            System.out.println("Digite uma unidade federativa válida.");
                            continue;
                        }
                    try{
                        String url = "https://viacep.com.br/ws/"+estado+"/"+cidade+"/"+rua+"/json/";
                        Conexao conexao = new Conexao();
                        HttpResponse<String> response = conexao.consultaApi(url);
                        if(response.statusCode() == 400){
                            System.out.println("Não foi possível achar o seu endereço. Por favor, digite números e tente novamente.");
                            continue;
                        }
                        Gson gson = new GsonBuilder()
                                .setPrettyPrinting()
                                .create();
                        Type filtroListType = new TypeToken<ArrayList<Filtro>>(){}.getType();
                        List<Filtro> filtros = gson.fromJson(response.body(), filtroListType);
                        for(int i = 0; i < 3; i++){
                            Filtro filtro = filtros.get(i);
                            Cep cep = new Cep(filtro);
                            listaCep.add(cep);
                            listaGeral.add(cep);
                        }
                        System.out.println("\n---------- Endereços prováveis ----------\n\n" + listaCep);
                    }catch (InputMismatchException e){
                        System.out.println("\nDigite apenas um dos números listados no menu!");
                    }catch (IllegalArgumentException | IndexOutOfBoundsException e){
                        System.out.println("\nParâmetros inconsistentes. Verifique se os dados inseridos estão corretos.");
                    }
                    break;
                case 3:
                    Collections.sort(listaGeral);
                    imprimiMenuCase3();
                    escolha2 = leitura.nextInt();
                    leitura.nextLine();
                    System.out.println("\n*****************************");
                    switch (escolha2){
                        case 1:
                            System.out.println("\n---------- Endereços salvos ----------\n\n" + listaGeral);
                            break;
                        case 2:
                            System.out.println("\nDigite o CEP: ");
                            String pesquisa  = leitura.nextLine();
                            pesquisa = formataCep(pesquisa);
                            int aux = 0;
                            if(pesquisa.length() != 8){
                                System.out.println("\nDigite um CEP com 8 números.");
                                continue;
                            }
                            System.out.println("\n---------- Endereço salvo ----------");
                            for(int i = 0; i < listaGeral.size(); i++){
                                if(pesquisa.equals(listaGeral.get(i).getCep())){
                                    System.out.println("\n" + listaGeral.get(i));
                                    aux++;
                                }
                            }
                            if(aux == 0){
                                System.out.println("\nNão existe o CEP informado na base de dados pesquisada.");
                                continue;
                            }
                            break;
                        case 3:
                            System.out.println("Voltando...");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                    break;
                case 4:
                    try {
                        Gson gson = new GsonBuilder()
                                .setPrettyPrinting()
                                .create();
                        FileWriter escrita = new FileWriter("CEPs.json");
                        escrita.write(gson.toJson(listaGeral));
                        escrita.close();
                        System.out.println("\nExtração concluída!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        } while (escolha1 != 5);
    }

    public static String formataCep(String cep){
        if(cep.length() == 9){
            return cep.substring(0, 5) +  cep.substring(6, 9);
        }
        return cep;
    }
    public static void imprimiMenu() {
        String menuInicial = """
                
                *****************************
                MENU
                
                1. Consultar CEP
                2. Consultar endereço
                3. Visualizar CEPs
                4. Extrair Json
                5. Sair
                *****************************
                
                Escolha:""";
        System.out.println(menuInicial);
    }
    public static void imprimiMenuCase3() {
        String menuInicial = """
                
                ******************************
                Visualizar CEPs
                
                1. Visualizar todos
                2. Consultar CEP
                3. Voltar
                *****************************
                
                Escolha:""";
        System.out.println(menuInicial);
    }
}
