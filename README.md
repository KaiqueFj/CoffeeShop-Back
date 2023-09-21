# CoffeeShop-Back-End

# 📕 Sobre o projeto

#### Esse projeto foi desenvolvido como proposta de um aplicativo ERP voltado para uma empresa que vende café por assinatura. Sendo assim, com o nosso aplicativo, é possível gerenciar e ter uma visão da empresa em aspectos como: cliente, funcionario, estoque, produtos, pedidos e lucros. Além disso, o aplicativo contém todas as dicas para a segurança de uma aplicação que foram orientadas pela empresa Palo Alto.

# Tecnologias utilizadas

- Java (Version 20)
- Spring Boot (Version 3.13.3)
- Spring Security
- Maven
- MysqlWorkbench

# Features

- Create, Read, Update, delete de todos os models do projeto, sendo alguns deles: Funcionario, Produto, Pedido, Nota-Fiscal...
- Encriptação de senha assim que o registro do usuário é criado, onde nem mesmo o desenvolvedor e ninguem da empresa tem acesso, garantindo assim, dados sensíveis seguros e integros.
- Todas as rotas estão protegidas e configuradas em um arquivo de segurança, onde apenas o usuário que tem funções específicas pode acessar, ou realizar alguma ação na página. Com isso, é possível bloquear e proteger a aplicação de ações mal intencionadas
- Criação de um token jwt no momento do login, garantindo mais uma camada de segurança e autentificação na aplicação.
- Interface agradável e de fácil uso, garantindo uma fácil gestão, entendimento e utilização do aplicativo.

# 💻 Como instalar?

```
# Clone the repository
$ git clone https://github.com/KaiqueFj/CoffeeShop-Back.git
# cd coffee-shop
# Agora de um start na aplicação utilizando o botão de iniciar da sua IDE

```

**That´s all folks ;)**

# Exemplo da tabela Pedidos

![image](https://github.com/KaiqueFj/CoffeeShop-Back/assets/78966558/caec2697-b768-412d-b944-9782a4f6767d)

# Exemplo das senhas encriptadas no banco

![image](https://github.com/KaiqueFj/CoffeeShop-Back/assets/78966558/cc751407-03d0-4c76-81b8-5993af87a8c7)

# Estruturação do projeto

![image](https://github.com/KaiqueFj/CoffeeShop-Back/assets/78966558/2e7f7f0d-5d21-44fd-b24e-9981b0ec0659)
