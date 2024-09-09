# API de cadastros de usuários
Este microsserviço é responsável pelo cadastro de usuários e persistência de dados da aplicação.

Deve ser acessado através dos endpoints da aplicação principal:
[Banco Javer](https://github.com/williamsaranjr/banco-javer)

## Configuração
Para o funcionamento da aplicação, algumas mudanças devem
ser feitas no arquivo `src/main/resources/application.yml`.
- Porta: A porta padrão é a porta **8000**, mas pode ser alterada se for necessário.
- Datasource: O datasource padrão é uma base de dados **MySQL** e os seguintes valores podem ser alterados:
  - url: `jdbc:mysql://localhost:3306/banco-javer`
  - user: `root`
  - password: `admin123`
## Endpoints
Com o serviço em execução, os endpoints
podem ser visualizados através do Swagger UI, acessando a
[documentação](http://localhost:8000/swagger-ui.html).