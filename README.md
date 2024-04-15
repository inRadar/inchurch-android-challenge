# inChurch Recruitment Process - Android Developer

### Arquitetura

* A arquitetura escolhida para o projeto foi a MVVM. Proposta pelo próprio google, tal arquitetura facilita o desenvolvimento e escalabilidade do projeto
  * **model** -> Nesse pacote, foram adicionados todos os DTOs do projeto, bem como os services e repositórios da camada de dados
  * **view** -> Aqui foram adicionados todas as activities e adapters necessários para os componentes visuais da aplicação
  * **viewModel** -> Neste caso, ficou apenas um viewModel, que foi utilizado para a aplicação
 
### Libs
* **Retrofit** -> Biblioteca para requests http
* **gson** -> Associado ao retrofit, para cuidar dos parses da API
* **Koin** -> Usada para injeção de dependência
* **swipeRefresh** -> Usada para o reload da página de filmes populares
* **Glide** -> Usada para carregar todas as imagens dos filmes
* **Lottie** -> Utilizada para exibir animações de emptyState e Loading

### Funcionalidades
* **Tela de filmes populares** -> Nela é listada os filmes populares retornados pela API
* **Favoritar filmes** -> Ao clicar na estrela, em cada filme, na tela de filmes populares, o usuário poderá salvar esse filme como favorito.
* **Remover filme dos favoritos** -> Ao clicar na estrela de um filme já favoritado, o mesmo será excluído dos favoritos
* **Tela de favoritos** -> Ao clicar na estrela do canto superior direito, o usuário é direcionado para a tela de favoritos, onde pode encontrar uma listagem com uma breve descrição dos filmes salvos
* **Tela de detalhes** -> Ao clicar em um filme na tela de favoritos, o usuário será direcionado para a tela de detalhes desse filmes com informações mais detalhadas sobre o mesmo
* **Loader de carregamento** -> Ao ser direcionado para a tela de favoritos, o usuário poderá ver uma tela amigável de carregamento
* **Empty State** -> Caso o usuário não tenha favoritado nenhum filme, ao entrar na tela de favoritos, aparecerá um eptyState amigável o informando como favoritar filmes
* **Tratamento de erro** -> Caso ocorra algum erro na listagem de filmes populares, será exibido uma mensagem de erro amigável ao usuário
