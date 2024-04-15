### Arquitetura
- #### Optei por seguir um padrao com base no Clean Architecture onde temos a camada de ***DATA*** que fica responsavel por disponibilizar o dado para a interface, consumindo de uma API na camada ***REMOTE*** ou de um banco de dados local na camada de ***CACHE*** e na camada de ***UI*** utilizei o padrao ***MVVM***

### Libs

* Jetpack Compose - Utilizado para construcao das telas
* Voyager - utilizada para fazer a navegação entre as telas, com uma implementacao mais simples.
* Room - para o armazenamento de dados local
* Retrofit - para o consumo da API
* Coil - uma maneira mais simples de carregar imagens da web.
* Dagger Hilt - para injecao de dependencia.
* Paging - para carregamento de dados paginados e scroll infinito