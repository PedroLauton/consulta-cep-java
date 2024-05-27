# Projeto de Consulta de CEP

Este projeto Java permite a consulta de endereços a partir de CEPs e a busca de endereços por estado, cidade e rua. Utiliza a API ViaCEP para obter os dados e permite salvar os resultados em um arquivo JSON.    

## Estrutura do Projeto

O projeto é composto pelas seguintes classes:

- Menu: Classe principal que contém o menu interativo e a lógica para manipulação de CEPs e endereços.
  
- Cep: Representa um endereço com atributos como CEP, logradouro, complemento, bairro, localidade e UF.
  
- Conexao: Responsável por fazer a conexão com a API ViaCEP.
  
- Filtro: Representa o formato de resposta da API ViaCEP.
<br><br>

## Funcionalidades

- Consultar CEP: Permite ao usuário inserir um CEP e obter o endereço correspondente.

- Consultar Endereço: Permite ao usuário buscar endereços por estado, cidade e rua.

- Visualizar CEPs: Permite visualizar todos os CEPs consultados ou buscar um CEP específico. Vale ressaltar que todos os CEPs são ordenados pelo menor ao maior CEP. 

- Extrair JSON: Salva todos os endereços consultados em um arquivo JSON.

- Sair: Encerra o programa.
<br><br>

## Conhecimentos inerentes ao projeto

1. **Programação Orientada a Objetos (POO)**:
   - Uso de classes, objetos, encapsulamento e herança.
   - Criação de métodos e atributos para manipular dados.

2. **Manipulação de Strings**:
   - Métodos para formatação e modificação de strings, como replace(), toLowerCase(), toUpperCase(), e substring().

3. **Estruturas de Dados**:
   - Uso de listas (ArrayList) para armazenar objetos.
   - Ordenação de listas utilizando Collections.sort().

4. **Tratamento de Exceções**:
   - Uso de blocos try-catch para capturar e tratar exceções.

5. **Entrada e Saída (I/O)**:
   - Leitura de dados do console utilizando Scanner.
   - Escrita de dados em arquivos utilizando FileWriter.

6. **APIs e Redes**:
   - Uso da API HTTP Client do Java para fazer requisições HTTP.
   - Consumo de APIs RESTful (ViaCEP).

7. **JSON**:
   - Uso da biblioteca Gson para conversão entre objetos Java e JSON.
   - Serialização e desserialização de dados JSON.
<br><br>

## Dependências

- [Gson](https://github.com/google/gson) para serialização e desserialização de objetos JSON.
- Java 11 ou superior para utilizar a API de HttpClient.
<br><br>

## Screenshots

### Pesquisa por CEP
![image](https://github.com/PedroLauton/consulta-cep-java/assets/129104265/ee28953a-100f-4cac-ad6e-96e81fcde5d8) 
![image](https://github.com/PedroLauton/consulta-cep-java/assets/129104265/aaaa5ec7-766c-4c67-b552-ad1289b0616d)

### Pesquisa por endereço
![image](https://github.com/PedroLauton/consulta-cep-java/assets/129104265/bc2d48fa-7aad-45e2-9e26-a3283bf3d2f6)

### Consulta específica por CEP
![image](https://github.com/PedroLauton/consulta-cep-java/assets/129104265/de5218c3-7072-418d-8066-b874904c8e64)

### Dados salvos em Json
![image](https://github.com/PedroLauton/consulta-cep-java/assets/129104265/224549f7-a9d3-49c2-8622-8b01e09639a9)
