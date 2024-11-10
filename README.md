<h1>Desafio Android - PicPay</h1>

<p>Este é um projeto de migração que implementa melhorias utilizando <strong>Clean Architecture</strong>, os princípios <strong>SOLID</strong> e <strong>design patterns</strong>. A aplicação foi migrada para uma estrutura mais organizada e escalável, facilitando a manutenção e extensibilidade.</p>


<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay2.gif" width="300"/>

<h2>Estrutura do Projeto</h2>

<p>O projeto está dividido em módulos e pacotes específicos que seguem os princípios do Clean Architecture, organizados da seguinte maneira:</p>

<pre>
com
└── picpay
    └── desafio
        └── android
            ├── data
            │   ├── di
            │   │   └── UserRepositoryModule.kt
            │   ├── remote
            │   │   ├── di
            │   │   │   └── RetrofitModule.kt
            │   │   └── PicPayService.kt
            │   └── repository
            │       └── UserRepositoryImpl.kt
            ├── domain
            │   ├── di
            │   │   └── UserRepository.kt
            │   ├── entity
            │   │   └── User.kt
            │   └── usecase
            │       ├── di
            │       │   └── UserUseCaseModule.kt
            │       ├── GetUsers.kt
            │       └── GetUsersImpl.kt
            ├── ui
            │   ├── activity
            │   │   └── MainActivity.kt
            │   ├── components
            │   │   ├── BasicComponents.kt
            │   │   ├── MainScreen.kt
            │   │   └── UserList.kt
            │   ├── navigation
            │   │   ├── AppNavigation.kt
            │   │   └── AppRoutes.kt
            │   ├── theme
            │   │   ├── Color.kt
            │   │   └── PicPayTheme.kt
            │   └── viewmodel
            │       └── MainViewModel.kt
            └── CustomApplication.kt
</pre>

<h2>Tecnologias Utilizadas</h2>

<ul>
  <li><strong>Kotlin</strong> - Linguagem principal para o desenvolvimento Android.</li>
  <li><strong>Jetpack Compose</strong> - Ferramenta de UI declarativa para Android.</li>
  <li><strong>Hilt</strong> - Framework para injeção de dependência.</li>
  <li><strong>Retrofit</strong> - Biblioteca para consumo de APIs REST.</li>
  <li><strong>LiveData</strong> - Observabilidade para MVVM.</li>
  <li><strong>JUnit & Mockito</strong> - Ferramentas para testes unitários.</li>
  <li><strong>CircleCI</strong> - Integração contínua e automação de builds e testes.</li>
</ul>

<h2>Integração Contínua com CircleCI</h2>

<p>O projeto utiliza o <strong>CircleCI</strong> para executar testes e automação de builds. O arquivo de configuração (<code>.circleci/config.yml</code>) inclui:</p>

<ul>
  <li><strong>Instalação de dependências</strong> - Configura o ambiente de desenvolvimento Android.</li>
  <li><strong>Execução de testes</strong> - Realiza testes unitários e instrumentados.</li>
</ul>

<p>Para visualizar o status do build e os relatórios de testes, acesse o painel do CircleCI associado ao repositório.</p>

<h2>Princípios SOLID no Projeto</h2>

<p>O projeto segue os princípios <strong>SOLID</strong>, promovendo uma arquitetura modular e sustentável. Abaixo, estão os princípios e sua aplicação no projeto:</p>

<h3>1. Single Responsibility Principle (SRP)</h3>

<p>Cada classe tem uma responsabilidade única:</p>

<ul>
  <li><code>UserRepositoryImpl</code> gerencia a lógica de acesso aos dados.</li>
  <li><code>GetUsers</code> encapsula a lógica de obtenção de dados do usuário.</li>
  <li><code>MainViewModel</code> coordena o estado da UI.</li>
</ul>

<h3>2. Open/Closed Principle (OCP)</h3>

<p>As classes estão abertas para extensão, mas fechadas para modificação:</p>

<ul>
  <li><code>UserRepository</code> permite adicionar novas fontes de dados implementando a interface, sem modificar classes existentes.</li>
</ul>

<h3>3. Liskov Substitution Principle (LSP)</h3>

<p>As implementações de <code>UserRepository</code> substituem a interface base sem alterar o comportamento esperado, permitindo o uso de implementações diferentes em <code>MainViewModel</code>.</p>

<h3>4. Interface Segregation Principle (ISP)</h3>

<p>Interfaces específicas são criadas para cada caso de uso, evitando que as classes implementem métodos desnecessários.</p>

<ul>
  <li><code>UserRepository</code> define métodos para acesso a dados de usuários.</li>
  <li><code>GetUsers</code> define a lógica de obtenção de usuários.</li>
</ul>

<h3>5. Dependency Inversion Principle (DIP)</h3>

<p>As dependências são baseadas em abstrações e injetadas por meio de Hilt, garantindo baixo acoplamento e permitindo fácil substituição de implementações para testes.</p>

<h2>Arquitetura Clean</h2>

<p>O projeto segue a arquitetura Clean, dividindo responsabilidades em camadas distintas:</p>

<ul>
  <li><strong>Data</strong>: Implementação de repositórios, comunicação com serviços remotos (API) e módulos de DI.</li>
  <li><strong>Domain</strong>: Definição de casos de uso e entidades principais da aplicação.</li>
  <li><strong>UI</strong>: Interface do usuário e lógica de apresentação (ViewModel).</li>
</ul>

<h3>Camadas</h3>

<ul>
  <li><strong>Data Layer</strong>: Implementa o repositório de dados (<code>UserRepositoryImpl</code>), consumindo a API via Retrofit.</li>
  <li><strong>Domain Layer</strong>: Define os casos de uso (<code>GetUsers</code>) e entidades (<code>User</code>) principais.</li>
  <li><strong>Presentation Layer (UI)</strong>: Composta pela interface de usuário em Jetpack Compose, gerenciada por <code>MainViewModel</code>.</li>
</ul>

<h2>Design Patterns</h2>

<h3>Repository Pattern</h3>

<p>O padrão de repositório é utilizado para abstrair o acesso aos dados, permitindo que o <code>UserRepositoryImpl</code> forneça uma interface para obter dados de usuários, independentemente de onde esses dados venham (API, cache, etc.).</p>

<ul>
  <li><strong>Data Layer</strong>: <code>UserRepositoryImpl</code> implementa a interface <code>UserRepository</code> e encapsula a lógica de recuperação dos dados, delegando ao serviço remoto (Retrofit).</li>
  <li><strong>Domain Layer</strong>: <code>UserRepository</code> é a abstração que permite a utilização de diferentes fontes de dados sem modificar o caso de uso <code>GetUsers</code>.</li>
</ul>

<h2>Configuração e Instalação</h2>

<ol>
  <li>Clone o repositório:
    <pre><code>git clone https://github.com/seu-usuario/desafio-android-picpay.git</code></pre>
  </li>
  <li>Abra o projeto no Android Studio.</li>
  <li>Sincronize as dependências do Gradle.</li>
  <li>Construa e execute o projeto em um dispositivo/emulador Android.</li>
</ol>

<h2>Executando Testes</h2>

<p>Para executar os testes unitários:</p>

<pre><code>./gradlew test</code></pre>

<p>Para executar os testes instrumentados:</p>

<pre><code>./gradlew connectedAndroidTest</code></pre>

<h2>Contribuindo</h2>

<p>Sinta-se à vontade para contribuir com o projeto, sugerindo melhorias e novos recursos. Abra uma issue ou um pull request para discutirmos suas ideias!</p>

---

Este README está pronto para ser copiado e colado no GitHub, com formatação em HTML para melhor visualização e compreensão dos detalhes do projeto.

