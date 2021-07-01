# Backend

### Preparação

- Verifique se existe alguma instalação do JDK em sua maquina. Abra o CMD e digite `java --version`
- Caso nao exista, instalar o [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) para execução do projeto.
- Após a instalação adicione a variavel de ambiente `JAVA_HOME` com o caminho da instalação do JDK onde no meu caso foi a pasta `C:\Program Files\Java\jdk-11.0.11`.

#### Database
Seguir o readme da pasta [Database](https://github.com/romulofgouvea/desafio-fundecc/tree/main/backend/database)


### Execução

Na pasta backend, abrir o terminal e executar `./mvnw install` para baixar as dependencias e logo após `./mvnw spring-boot:run` para executar a aplicação, a api ja estará pronta para acesso na url [http://localhost:3333](http://localhost:3333).

### Comandos Úteis

- Instalação das dependencias
```bash
./mvnw dependency:tree
```
