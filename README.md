<h1 align="left">Desafio Android - PicPay</h1>

<p align="left">
    <strong>Este projeto é uma migração que implementa melhorias utilizando Clean Architecture, princípios SOLID e design patterns.</strong>
    <br>Organização e escalabilidade foram priorizadas para facilitar a manutenção e extensibilidade da aplicação.
</p>

<p align="center">
    <img src="https://github.com/victorhugobenevides/desafio-picpay/blob/main/desafio-picpay-2.gif" alt="Demonstração" width="300">
</p>

<h2>📁 Estrutura do Projeto</h2>

<p>O projeto está organizado em módulos e pacotes seguindo os princípios de Clean Architecture:</p>

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

<h2>🛠️ Tecnologias Utilizadas</h2>

<ul>
  <li><strong>Kotlin</strong> - Linguagem principal para o desenvolvimento Android.</li>
  <li><strong>Jetpack Compose</strong> - Ferramenta de UI declarativa para Android.</li>
  <li><strong>Hilt</strong> - Framework para injeção de dependência.</li>
  <li><strong>Retrofit</strong> - Biblioteca para consumo de APIs REST.</li>
  <li><strong>LiveData</strong> - Observabilidade para MVVM.</li>
  <li><strong>JUnit & Mockito</strong> - Ferramentas para testes unitários.</li>
  <li><strong>CircleCI</strong> - Integração contínua e automação de builds e testes.</li>
</ul>

<h2>🔄 Integração Contínua com CircleCI</h2>

<p>O projeto utiliza o <strong>CircleCI</strong> para executar testes e automação de builds. O arquivo de configuração (<code>.circleci/config.yml</code>) inclui:</p>

<ul>
  <li><strong>Instalação de dependências</strong> - Configura o ambiente de desenvolvimento Android.</li>
  <li><strong>Execução de testes</strong> - Realiza testes unitários e instrumentados.</li>
</ul>

<h2>🧩 Princípios SOLID no Projeto</h2>

<p>O projeto segue os princípios <strong>SOLID</strong>, promovendo uma arquitetura modular e sustentável.</p>

<h3>1. Single Responsibility Principle (SRP)</h3>
<ul>
  <li><code>UserRepositoryImpl</code> gerencia a lógica de acesso aos dados.</li>
  <li><code>GetUsers</code> encapsula a lógica de obtenção de dados do usuário.</li>
  <li><code>MainViewModel</code> coordena o estado da UI.</li>
</ul>

<h3>2. Open/Closed Principle (OCP)</h3>
<ul>
  <li><code>UserRepository</code> permite adicionar novas fontes de dados implementando a interface, sem modificar classes existentes.</li>
</ul>

<h3>3. Liskov Substitution Principle (LSP)</h3>
<ul>
  <li>Implementações de <code>UserRepository</code> substituem a interface base sem alterar o comportamento, permitindo uso flexível.</li>
</ul>

<h3>4. Interface Segregation Principle (ISP)</h3>
<ul>
  <li>Interfaces específicas são criadas para cada caso de uso, evitando métodos desnecessários em classes.</li>
</ul>

<h3>5. Dependency Inversion Principle (DIP)</h3>
<ul>
  <li>As dependências são baseadas em abstrações e injetadas por meio de Hilt, garantindo baixo acoplamento.</li>
</ul>

<h2>📐 Arquitetura Clean</h2>

<p>O projeto segue a arquitetura Clean, dividindo responsabilidades em camadas distintas:</p>

<ul>
  <li><strong>Data Layer</strong>: Implementa o repositório de dados (<code>UserRepositoryImpl</code>), consumindo a API via Retrofit.</li>
  <li><strong>Domain Layer</strong>: Define os casos de uso (<code>GetUsers</code>) e entidades (<code>User</code>) principais.</li>
  <li><strong>Presentation Layer (UI)</strong>: Composta pela interface de usuário em Jetpack Compose, gerenciada por <code>MainViewModel</code>.</li>
</ul>

<h2>🔖 Design Patterns</h2>

<h3>1. Repository Pattern</h3>
<ul>
  <li>Gerencia a lógica de acesso a dados, facilitando a troca de fontes de dados sem modificar o restante da aplicação.</li>
</ul>

<h3>2. Use Case Pattern (Interactors)</h3>
<ul>
  <li>Encapsula a lógica de negócios específica em operações, mantendo-a separada da UI.</li>
</ul>

<h3>3. Dependency Injection (DI) - Hilt</h3>
<ul>
  <li>Reduz a necessidade de instanciar classes manualmente e facilita testes.</li>
</ul>

<h3>4. ViewModel Pattern</h3>
<ul>
  <li>Desacopla a lógica de apresentação e mantém o estado da UI, sobrevivendo a mudanças de configuração.</li>
</ul>

<h3>5. Observer Pattern</h3>
<ul>
  <li>Utilizado com LiveData para observar mudanças no estado da UI, permitindo reatividade.</li>
</ul>

<h3>6. Factory Pattern</h3>
<ul>
  <li>Utilizado pelo Hilt para fornecer instâncias de classes, promovendo desacoplamento.</li>
</ul>

<h3>7. Adapter Pattern</h3>
<ul>
  <li>Transforma objetos de domínio em elementos visuais na UI, facilitando exibições complexas.</li>
</ul>

<h2>⚙️ Configuração e Instalação</h2>

<ol>
  <li>Clone o repositório:
    <pre><code>git clone https://github.com/victorhugobenevides/desafio-picpay.git</code></pre>
  </li>
  <li>Abra o projeto no Android Studio.</li>
  <li>Sincronize as dependências do Gradle.</li>
  <li>Construa e execute o projeto em um dispositivo/emulador Android.</li>
</ol>

<h2>🧪 Executando Testes</h2>

<p>Para executar os testes unitários:</p>
<pre><code>./gradlew test</code></pre>

<p>Para executar os testes instrumentados:</p>
<pre><code>./gradlew connectedAndroidTest</code></pre>

<h2>🤝 Contribuindo</h2>

<p>Sinta-se à vontade para contribuir com o projeto, sugerindo melhorias e novos recursos. Abra uma issue ou um pull request para discutirmos suas ideias!</p>
