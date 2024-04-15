# inChurch Recruitment Process - Android Developer

### Decisões adotadas no desenvolvimento do teste

### Arquitetura - Projeto
* A arquitetura de projeto escolhida para o desenvolvimento do teste foi o Clean proposto pelo Google [link](https://developer.android.com/topic/architecture#recommended-app-arch) onde o projeto é dividido nos seguintes módulos:
  * **data**: Camada responsável por fornecer os dados para a aplicação, seja ela local ou remota.
  * **presentation**: Camada responsável por conter a lógica de apresentação da aplicação.
  * **DI**: Camada responsável por conter a lógica de injeção de dependência.
* **Ponto de atenção**: A camada de domínio foi omitida, pois como não haviam regras de negócio para serem abstraidas, então a camada de apresetação acessa a camada de dados diretamente.

### Arquitetura - UI
* A arquitetura de UI escolhida para o desenvolvimento do teste foi o MVVM proposto pelo Google [link](https://developer.android.com/topic/architecture/ui-layer) e usando o Jetpack Compose para a construção da UI.

### Libs
* **Jetpack Compose**: Utilizado para a construção da UI.
* **Navigation**: Utilizado para realizar a navegação entre as telas.
* **Coroutines**: Utilizado para realizar o acesso a camada de dados de forma assíncrona.
* **Retrofit**: Utilizado para realizar as requisições HTTP.
* **LoggingInterceptor**: Utilizado para logar as requisições HTTP.
* **Koin**: Utilizado para realizar a injeção de dependência. Optei pelo Koin ao invés do Dagger/Hilt por conta da simplicidade de construção e também por ser uma DI runtime, não compilada.
* **Room**: Utilizado para realizar o armazenamento local dos filmes favoritos. Usei o KSP para a geração de código ao invés do KAPT por conta da performance.
* **Coil**: Utilizado para o carregamento de imagens.
* **JUnit**: Utilizado para a realização de testes unitários.
* **KotlinX Coroutines Test**: Utilizado para a realização de testes de coroutines.
* **Espresso**: Utilizado para a realização de testes de integração.
