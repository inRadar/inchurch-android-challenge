# inChurch Recruitment Process - Android Developer

Nessa parte do processo de recrutamento você desenvolverá uma aplicação Android. O desafio deve ser desenvolvido em Kotlin e utilizando libs conhecidas de mercado. A aplicação será um catálogo dos filmes populares, utilizando a [API](https://developers.themoviedb.org/3/getting-started/introduction) do [TheMovieDB](https://www.themoviedb.org/).

* * *

## Requisitos

+ ### O que deve ter:
	* Tela de Listagem de Filmes exibindo os filmes melhores classificados. Utilizar esse [endpoint](https://developers.themoviedb.org/3/movies/get-popular-movies).
	* Loading no carregamento da listagem de filmes.
	* Tratamento de erros(falta de internet e erros na api) na tela de Listagem de Filmes.
 	* Ação de favoritar um filme na tela de Listagem de Filmes. Essa ação deverá ser disparada ao clicar no ícone da estrela do card. Todo o controle será em armazenamento local.
	* Tela de Favoritos com a listagem dos filmes marcados como favorito. Essa tela será acessada no ícone de favoritos na toolbar da Listagem de Filmes.
 	* Testes de unidade.
	* Testes de integração.

#
+ ### Pontos extras:
	* Paginação com scroll infinito na tela de Listagem de Filmes.
 	* Inserir um texto abaixo do título do filme, na tela de Listagem de Filmes, com informações de gêneros do filme, utilize esse [endpoint](https://developers.themoviedb.org/3/genres/get-movie-list).
  	* Tela de Detalhe do Filme. Para as informações de gêneros do filme, utilize esse [endpoint](https://developers.themoviedb.org/3/genres/get-movie-list).
	* Filtro de busca pelo nome do filme na tela de Favoritos. Exibir uma tela diferente para quando não houver resultado na busca.
	* Ação de remover o filme da lista de Favoritos.
	

#
* * *

## Sugestão de layout

Para facilitar a sua vida, padronizar e explorar algumas habilidades suas, estamos disponibilizando um mockup para que você possa segui-lo. Lembre-se, você pode(e deve) mudar cor, tipo e tamanho de fonte, ícones e outras coisa que fizerem sentido, mas não esqueça de seguir a estrutura proposta.  

![Alt text](./images/img1.png) ![Alt text](./images/img2.png) ![Alt text](./images/img3.png)

* * *

## O que devo fazer?

* Realizar o fork desse repositório.
* Desenvolver a aplicação usando as melhores práticas de desenvolvimento e totalmente em inglês.
* Sobrescreva o README falando sobre o que foi utilizado na arquitetura e libs - com uma pequena explicação das decisões adotadas.
* Enviar seu pull request para o nosso repositório quando finalizar.

* * *

## O que será avaliado?

* Qualidade do código.
* Organização do projeto.
* Arquitetura utilizada.
* Boas práticas de desenvolvimento Android.
* Crash-safe code, consumo de memória e desempenho.

***É uma avaliação, desenvolva o projeto com qualidade de produção. ;)**

* * *

## Alguma dúvida?

* E-mail: fernando.lima@inchurch.com.br
