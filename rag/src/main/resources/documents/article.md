# Título: Sistema de Recomendação de Pontos de Interesse em Recife com Filtragem Baseada em Conteúdo e Passaporte Pernambuco

**Autores: Douglas I. F. Ferreira¹, Everton Gomede²**

¹ Escola Superior de Agricultura Luiz de Queiroz (Esalq), Pecege, Universidade de São Paulo (USP) – Piracicaba – SP – Brasil  
² Faculdade de Engenharia Elétrica e de Computação, Universidade Estadual de Campinas (FEEC/UNICAMP) – Campinas – SP – Brasil  
Email: douglas.iff@usp.br, gomede@unicamp.br

## Abstract

Recife has significant tourism potential but requires digital solutions to promote its Points of Interest (POIs). Thus, implementing an intelligent system represents an innovation opportunity for the sector. A Content-Based Recommendation System was proposed, which uses similarity metrics to correlate user preferences with POI characteristics. The system achieved 79% precision, 100% minimum hit rate, and 86% coverage, demonstrating strong performance in *cold start* scenarios despite the overexposure of some POIs. Therefore, the model proved effective in providing personalized recommendations, showing potential to boost local tourism.

## Resumo

Recife tem grande potencial turístico, mas necessita de soluções digitais para promover seus Pontos de Interesse (POIs). A implementação de um sistema inteligente representa uma oportunidade de inovação no setor. Foi proposto um Sistema de Recomendação via Filtragem Baseada em Conteúdo, que usa TF-IDF e métricas de similaridade para correlacionar as preferências dos usuários com as características dos POIs. O sistema obteve 79% de precisão, 100% de taxa mínima de acerto e 86% de cobertura, demonstrando bom desempenho em cenários de *cold start*, apesar da superexposição de alguns POIs. Sendo assim, o modelo mostrou eficácia na recomendação personalizada, apresentando potencial para impulsionar o turismo local.

## 1. Introdução

Um grande volume de informações é gerado diariamente, levantando um desafio crítico: filtrar conteúdos relevantes para o usuário [@li:21]. Nesse contexto, os Sistemas de Recomendação (SRs) surgem como uma solução eficaz para sugerir itens alinhados às preferências dos usuários, contribuindo para a mitigação da sobrecarga informacional. Plataformas como *Netflix*, *Amazon* e *Tripadvisor* exemplificam a aplicação bem-sucedida de sistemas altamente personalizados [@aggarwal:16].

No turismo, os SRs são utilizados para oferecer sugestões sobre transporte, hospedagem e atrações, Pontos de Interesse (POIs), redes sociais, rotas e planos de viagem [@borras:14; @sarkar:23]. Apesar dos avanços, ainda há desafios tecnológicos em grandes centros urbanos brasileiros, como Recife, que demandam soluções integradas para conectar os SRs aos seus respectivos POIs.

Análises em bases de dados como Google Acadêmico, SciELO e Periódicos da CAPES revelam uma lacuna na literatura sobre o tema, especialmente no contexto brasileiro. A implementação de SRs de POIs representam uma oportunidade de inovação digital no setor turístico, especialmente no cenário pós-pandemia da Covid-19 [@ferreira:24; @trigo:20].

Recife, capital de Pernambuco e objeto de estudo, tem investido em iniciativas que promovem a adoção de soluções tecnológicas no turismo, destacando o potencial dessa área [@campos:22]. A cidade possui importantes POIs, muitos com valor histórico e acesso gratuito, além de uma diversidade de festividades culturais. Essa variedade, embora vantajosa, intensifica a sobrecarga informacional enfrentada pelos turistas. Alguns atrativos estão integrados no Passaporte Pernambuco, um documento não oficial que estimula o turismo no estado ao permitir o registro simbólico das visitas [@ferreira:24; @fonseca:20].

Diante da lacuna na literatura e da relevância do turismo regional, este estudo visa investigar a implementação de técnicas de recomendação de POIs na política pública do Passaporte Pernambuco, utilizando a Filtragem Baseada em Conteúdo (FBC). Essa abordagem permite personalizar sugestões com base em atributos descritivos dos POIs e nas preferências individuais dos usuários [@aggarwal:16].

Este estudo baseia-se nas seguintes hipóteses: (i) um Sistema de Recomendação (SR) utilizando FBC pode gerar recomendações relevantes, mesmo em contextos de dados limitados (*cold start*); e (ii) a personalização das recomendações de POIs conforme as preferências do usuário pode aumentar a cobertura e a diversidade dos itens sugeridos.

## 2. Trabalhos Relacionados

SRs são softwares que utilizam métodos estatísticos e técnicas de aprendizado de máquina. O principal objetivo dos SRs é sugerir itens alinhados às preferências individuais dos usuários, com base nas informações de seus perfis. Esses perfis contêm dados relevantes que permitem ao sistema oferecer recomendações mais precisas e personalizadas [@aggarwal:16; @castells:23; @gao:10].

Os SRs podem ser classificados em dois modelos principais. O modelo de (i) previsão visa estimar a compatibilidade entre um usuário e um item, utilizando algoritmos de aprendizado treinados com dados de históricos de interações. Em contrapartida, o modelo de (ii) classificação foca na seleção direta dos itens mais relevantes, com base nas características do perfil do usuário [@aggarwal:16].

Além disso, os SRs utilizam (i) dados de interação, que registram comportamentos como avaliações, cliques e compras; e (ii) dados de atributos, que descrevem usuários e itens por meio de perfis, descrições textuais e palavras-chave. Esses dados são processados por diferentes abordagens de recomendação, destacando-se o modelo colaborativo, o modelo baseado em conteúdo e os modelos híbridos [@aggarwal:16; @borras:14; @castells:23; @li:21].

Para avaliar o desempenho das recomendações, são coletadas avaliações contínuas, intervalares, ordinais, binárias ou unárias. Podendo ser expressas de forma explícita (interação direta) ou implícita (quando derivadas de comportamentos) [@aggarwal:16].

Recentemente, destacam-se o uso de *Large Language Models* (LLMs), como *DeepSeek*, para interagir com aplicações. O uso das LLMs demonstra um grande potencial de revolucionar os SRs por compreenderem melhor a linguagem e intenções humanas com maior naturalidade [@zhao:24]. Além de SRs baseados em *Deep Learning*, como os *Autoencoders*, que têm demonstrado alta eficácia na personalização de recomendações para domínios complexos [@gomede:21]

No turismo, os SRs são essenciais para personalizar sugestões de destinos, atividades e serviços. A aplicação das abordagens de filtragem e avaliação discutidas pode ser adaptada, um exemplo são os SRs de POIs, que se destacam como ferramentas relevantes na conexão entre turistas e atrações locais.

### 2.1 Sistemas de Recomendação de Pontos de Interesse

Os SRs de POIs visam sugerir novos locais para exploração pelos usuários, especialmente diante do aumento contínuo de POIs disponíveis. Além de facilitar a descoberta de destinos, essas recomendações elevam a probabilidade de uma experiência turística satisfatória, promovendo a integração entre os ambientes físico e digital [@zhao:16].

A recomendação de POIs pode ser dividida em dois objetivos principais: (i) a Recomendação Geral, que sugere os *top*-k POIs com base nas preferências globais do usuário; e (ii) a Recomendação Sucessiva, que considera o histórico de locais visitados para identificar e recomendar POIs com características semelhantes, explorando relações de similaridade [@zhao:16].

Neste contexto, a informação baseada em conteúdo é fundamental, pois permite estabelecer relações entre as preferências dos usuários e as características dos POIs [@gao:15]. Segundo a literatura, os SRs de POIs fundamentados na abordagem de conteúdo dependem de dois conjuntos de atributos: os dos POIs, que incluem categoria, localização e descrição textual, e os dos usuários, que abrangem interesses e comportamentos anteriores [@kang:06].

## 3. Filtragem Baseada em Conteúdo

A FBC utiliza informações descritivas para recomendar itens ao usuário. Essa abordagem combina as preferências, expressas ou inferidas, do usuário com as características dos itens disponíveis, gerando recomendações personalizadas [@aggarwal:16; @borras:14; @castells:23; @li:21].

Este modelo oferece vantagens significativas em contextos de escassez de dados, como nos problemas de *cold-start* de itens e de usuários. Nesses casos, os métodos de filtragem colaborativa costumam ter desempenho inferior devido à falta de informações. Por outro lado, a FBC apresenta limitações, especialmente em relação à diversidade: como as recomendações se baseiam apenas em características conhecidas, o sistema tende a sugerir itens muito semelhantes aos já consumidos, resultando em recomendações previsíveis e repetitivas [@aggarwal:16; @castells:23; @li:21].

O ranqueamento dos itens candidatos é baseado na distância ou similaridade entre as descrições dos itens e o perfil do usuário, que pode ser construído a partir do histórico de interações ou de um modelo explícito de preferências [@li:21]. Para representar as informações descritivas, utiliza-se técnicas de vetorização textual, como o *Term Frequency–Inverse Document Frequency* (TF-IDF). O algoritmo do TF-IDF é simples e eficiente, sendo uma técnica numérica de estatística que gera o resultado da relevância de um termo em um conjunto de documentos [@salton:88; @qaiser:18].

A comparação entre os vetores gerados pelo TF-IDF é realizada por meio de métricas de similaridade, incluindo Distância Euclidiana (ED), Similaridade do Cosseno (COS), Coeficiente de Correlação de Pearson (PCC) e Coeficiente de Jaccard (JC) [@castells:23; @magara:18; @salton:88; @sondur:16].

## 4. Passaporte Pernambuco

Recife possui uma economia diversificada, destacando-se recentemente o setor de turismo, que tem se consolidado como um eixo estratégico para o desenvolvimento local [@morais:22]. Apenas no carnaval de 2024, a cidade recebeu mais de 3,4 milhões de visitantes, impactando a economia em cerca de 2,4 bilhões de reais [@costa:24].

Nos últimos anos, Recife tem adotado práticas de turismo criativo e sustentável, assim como demonstrou uma demanda por inovação digital, promovendo uma "nova forma de fazer turismo". Essa abordagem valoriza a imersão cultural dos visitantes, incentivando experiências autênticas e interativas que fortalecem os laços entre os turistas e a identidade local [@campos:22; @ferreira:24].

Dessa forma, os POIs deste SR foram extraídos do Passaporte Pernambuco, lançado em 2020 para promover o turismo local. Com esse passaporte, os visitantes podem registrar simbolicamente suas visitas aos atrativos turísticos das cidades pernambucanas, carimbando as páginas correspondentes a cada POI visitado. O passaporte oferece atrativos como restaurantes, igrejas, museus, teatros e embaixadas [@fonseca:20; @valenca:22].

## 5. Materiais e Métodos

Este estudo utilizou uma abordagem mista. A abordagem qualitativa ajudou a definir o domínio de aplicação do SR, por meio de uma análise da literatura e da compreensão do contexto do Passaporte Pernambuco. A abordagem quantitativa, por sua vez, tratou dados numéricos, incluindo o cálculo de similaridades e a aplicação de métricas para avaliar o sistema [@creswell:21].

A pesquisa é considerada de natureza aplicada, visando gerar um impacto prático na resolução de um problema específico. O foco está na prototipagem e validação técnica do sistema por meio de um Mínimo Produto Viável (MVP) [@ries:12], cujo código, equações, banco de dados, documentação e *prompts* estão disponívéis no [GitHub](https://github.com/douglasfragoso/recommender-pe) para reprodução.

Para calcular as métricas de similaridade da FBC, utilizou-se uma combinação de TF-IDF, COS, ED, JC e PCC [@gao:10; @qaiser:18; @salton:88; @sondur:16]. Por fim, foram utilizadas as métricas de avaliação: Precisão@k, *Hit Rate*@k, Cobertura, Similaridade Intra-Lista e Frequência de POI [@castells:23; @deshpande:04; @gomede:21; @jesse:23; @li:21].

Para a geração de dados, foram utilizados os 36 POIs do Passaporte Pernambuco em Recife e criados 30 usuários fictícios utilizando LLMs. Esta abordagem pode ser caracterizada como uma simulação social simples baseada em *prompts*, essa técnica é considerada promissora para estudos exploratórios e validação de protótipos [@anthis:25].

## 6. Implementação do Sistema

O foco principal do MVP foi a Recomendação Geral de POIs, utilizando um modelo de classificação baseado em conteúdo, fundamentado nos atributos descritivos dos POIs e no perfil do usuário.

![Fluxo do Sistema](diagrama_recomendacao_pt.png)

*Figura 1: Fluxo do Sistema*

Os POIs, como museus, igrejas e restaurantes, são registrados com atributos como nome, descrição e categorias. Quando o usuário solicita uma recomendação, o sistema compara seu perfil com as descrições dos POIs utilizando a técnica TF-IDF e métricas de similaridade, retornando os 5 POIs mais relevantes com base na similaridade dos termos.

Após receber as recomendações, o usuário pode avaliá-las de forma binária, atribuindo um *like* ou *dislike*. Essas avaliações são registradas no banco de dados e utilizadas no cálculo de métricas de avaliação do sistema.

*Tabela 1: Exemplo de recomendação por métrica de similaridade*

| POI ID | Cosseno | Euclidiana | Pearson | Jaccard | Média |
|--------|---------|------------|---------|---------|-------|
| 4      | 0.6019  | 0.5284     | 0.7534  | 0.3416  | 0.5563 |
| 36     | 0.5588  | 0.5156     | 0.7201  | 0.3032  | 0.5244 |
| 7      | 0.3601  | 0.4692     | 0.5891  | 0.1749  | 0.3983 |
| 29     | 0.3681  | 0.4708     | 0.6068  | 0.1454  | 0.3978 |
| 30     | 0.3681  | 0.4708     | 0.6068  | 0.1454  | 0.3978 |

A comparação entre o vetor do usuário e os vetores dos POIs é realizada por um híbrido de métricas de similaridade. A pontuação final de recomendação é a média aritmética das quatro métricas normalizadas para o intervalo (0, 1). Essa abordagem combinada captura diferentes facetas da similaridade entre o perfil do usuário e cada item, resultando em um ranqueamento mais holístico [@magara:18; @sondur:16].

## 7. Coleta de Dados e Métricas de Avaliação

Foram utilizados os 36 POIs do Passaporte Pernambuco em Recife junto com 30 usuários fictícios (com suas preferências e avaliações), criados por LLMs (*DeepSeek*). Esses dados visam simular comportamentos de turistas e mostraram-se adequados para a validação técnica inicial do algoritmo proposto [@anthis:25].

Cada usuário recebeu uma lista personalizada de POIs recomendados com base em seu perfil e interações, registradas no banco de dados por meio de avaliações binária do tipo *like*/*dislike*.

Com os dados simulados, foram extraídas estatísticas básicas e identificadas tendências no comportamento do algoritmo. O SR apresentou uma precisão média de 79% na relevância dos POIs recomendados, com um Intervalo de Confiança (IC) de 95% entre 73% e 85%. Além disso, 100% dos usuários receberam pelo menos um POI relevante.

Em termos de cobertura de itens, 86% dos POIs foram recomendados ao menos uma vez, e a similaridade média intra-lista entre os itens recomendados foi de 41%.

*Tabela 2: Frequência de recomendação por POI*

| POI ID | Freq. | POI ID | Freq. | POI ID | Freq. | POI ID | Freq. |
|--------|-------|--------|-------|--------|-------|--------|-------|
| 1      | 0.10  | 10     | 0.13  | 19     | 0.10  | 28     | 0.37  |
| 2      | 0.10  | 11     | 0.03  | 20     | 0.27  | 29     | 0.13  |
| 3      | 0.10  | 12     | 0.10  | 21     | 0.23  | 30     | 0.13  |
| 4      | 0.20  | 13     | 0.00  | 22     | 0.23  | 31     | 0.10  |
| 5      | 0.03  | 14     | 0.10  | 23     | 0.00  | 32     | 0.33  |
| 6      | 0.20  | 15     | 0.00  | 24     | 0.00  | 33     | 0.00  |
| 7      | 0.13  | 16     | 0.07  | 25     | 0.03  | 34     | 0.20  |
| 8      | 0.27  | 17     | 0.20  | 26     | 0.17  | 35     | 0.10  |
| 9      | 0.03  | 18     | 0.37  | 27     | 0.17  | 36     | 0.27  |

Como mostrado na Tabela 2, o sistema deixou de recomendar 13,88% dos POIs, com os itens 13, 15, 23, 24 e 33 apresentando 0% de ocorrência nas listas recomendadas. Em contrapartida, alguns POIs se destacaram pela popularidade excessiva: os itens 8, 18, 20, 28, 32 e 36 foram recomendados entre 27% e 37% das vezes.

A superexposição e subutilização de certos POIs podem resultar na saturação de recomendações, prejudicando a experiência personalizada de turistas à medida que suas preferências mudam. Essas distorções estão possivelmente ligadas à cobertura desigual de categorias e ao viés algorítmico decorrente da similaridade textual.

Como alternativa, propõe-se a introdução de pesos ajustáveis por termo de interesse, o que permitirá maior controle sobre o balanceamento das recomendações. Além disso, destacam-se as necessidades de expansão do conjunto de POIs e termos associados, assim como a incorporação de novas *features* (filtragem híbrida e avaliações textuais).

Por fim, a adoção da técnica de *Latent Semantic Analysis* (LSA), por meio da *Singular Value Decomposition* (SVD), é uma alternativa promissora para identificar relações semânticas mais profundas [@achakulvisut:16] entre as preferências dos usuários e as descrições dos POIs.

## 8. Conclusão e Trabalhos Futuros

Este trabalho teve como objetivo investigar a implementação de técnicas de recomendação de POIs na política pública do Passaporte Pernambuco. Para isso, foi realizado um estudo abrangente sobre SRs, SRs aplicados a POIs e a técnica de FBC, além da análise da política pública relacionada ao Passaporte Pernambuco.

A pesquisa utilizou uma metodologia mista, com abordagem exploratória e natureza aplicada, resultando na implementação do MVP. O algoritmo desenvolvido empregou técnicas estatísticas de similaridade vetorial, como TF-IDF, COS, ED, JC e PCC.

O sistema utilizou 36 POIs localizados em Recife, presentes no Passaporte Pernambuco, e dados simulados de 30 usuários gerados por meio de LLMs. A precisão média alcançada foi de 79%, com um IC de 95%, variando entre 73% e 85%, além de um *Hit Rate* de 100%. Observou-se que 86% dos POIs foram recomendados ao menos uma vez e a similaridade intra-lista entre os itens recomendados no *top*-5 foi de 41%.

Os resultados indicam que o sistema teve um desempenho compatível com o previsto na literatura, especialmente em cenários de *cold start*. No entanto, notou-se uma certa repetição nas recomendações, atribuída à limitação do número de POIs e à semelhança de seus atributos descritivos.

As limitações da versão atual do MVP, embora esperadas, indicam caminhos claros para melhorias futuras. Entre as propostas dentro da FBC, destacam-se a expansão do conjunto de POIs, o balanceamento da cobertura entre categorias e o uso de pesos ajustáveis para os atributos dos itens. A médio prazo, sugere-se a adoção de LSA por meio de SVD, visando capturar relações semânticas mais profundas.

Já para futuras integrações de SR Híbrido, sugere-se o uso de LLMs, que possibilitará uma interpretação mais precisa das preferências, capturando nuances contextuais de forma mais eficaz. Além de integração de abordagens colaborativas, como *Neighborhood-Based*, *Model-Based* e *Deep Auto Encoders*, para captar preferências implícitas ou comportamentais.

## Referências

- Aggarwal, C. C. (2016). *Recommender Systems: The Textbook*. Springer.
- Anthis, J. R. et al. (2025). *Position: LLM Social Simulations Are a Promising Research Method*. Proceedings of the 42nd International Conference on Machine Learning.
- Achakulvisut, T. et al. (2016). *Science Concierge: A Fast Content-Based Recommendation System for Scientific Publications*. PLOS ONE.
- Borràs, J. et al. (2014). *Intelligent tourism recommender systems: a survey*. Expert Systems with Applications.
- Campos, J. et al. (2022). *Plano de Turismo Criativo 2022-2024*. Prefeitura do Recife.
- Castells, P. e Jannach, D. (2023). *Recommender systems: a primer*. In Advanced Topics for Information Retrieval.
- Costa, I. (2024). *Carnaval do Recife movimentou R$2,4 bilhões e recebeu mais de 3,4 milhões de foliões*. Portal G1.
- Creswell, J. W. e Creswell, J. D. (2021). *Projeto de pesquisa: Métodos qualitativo, quantitativo e misto*. Penso.
- Deshpande, M. e Karypis, G. (2004). *Item-based top-N recommendation algorithms*. ACM Transactions on Information Systems.
- Ferreira, D. I. F. e Ramos, C. (2024). *Uma análise estratégica com base na matriz SWOT: a aplicabilidade do projeto TheRoute a Pernambuco*. Anais do 15º Congresso de Administração, Sociedade e Inovação (CASI).
- Fonseca, D. (2020). *Pernambuco lança passaporte para incentivar turismo no estado entre moradores e turistas*. Portal G1.
- Gao, M. et al. (2010). *Personalisation in web computing and informatics: the techniques, applications, and future research*. Information Systems Frontiers.
- Gao, H. et al. (2015). *Context-aware point of interest recommendations on location-based social networks*. Proceedings of the Twenty-Ninth AAAI Conference on Artificial Intelligence.
- Gomede, E. et al. (2021). *Deep auto encoders to adaptive E-learning recommender system*. Computers and Education: Artificial Intelligence.
- Jesse, M. et al. (2023). *Intra-list similarity and human diversity perceptions of recommendations: The details matter*. User Modeling and User-Adapted Interaction.
- Kang, E. et al. (2006). *Personalization method for tourist point of interest (POI) recommendation*. Knowledge-Based Intelligent Information and Engineering Systems.
- Li, Y. et al. (2021). *Recent developments in recommender systems: A survey*. Journal of LaTeX Class Files.
- Magara, M. B. et al. (2018). *A Comparative Analysis of Text Similarity Measures and Algorithms in Research Paper Recommender Systems*. Proceedings of the 2018 Conference on Information Communications Technology and Society (ICTAS).
- Morais, I. et al. (2022). *Novas formas de fazer turismo: desde a prática às políticas na construção do Plano de Turismo Criativo do Recife*. Interações.
- Qaiser, S. e Ali, R. (2018). *Text mining: use of TF-IDF to examine the relevance of words to documents*. International Journal of Computer Applications.
- Ries, E. (2012). *A startup enxuta: Como empreendedores atuais utilizam a inovação contínua para criar empresas extremamente bem-sucedidas*. Leya.
- Salton, G. e Buckley, C. (1988). *Term-weighting approaches in automatic text retrieval*. Information Processing & Management.
- Sarkar, J. P. et al. (2023). *Tourism recommendation system: a survey and future research directions*. Multimedia Tools and Applications.
- Sondur, S. D. e Chigadani, A. P. (2016). *Similarity measures for recommender systems: a comparative study*. Journal for Research.
- Trigo, L. G. G. (2020). *Viagens e turismo: dos cenários imaginados às realidades disruptivas*. RBTUR.
- Valença, J. (2022). *Passaporte Pernambuco: saiba onde e como conseguir o caderno turístico*. JC.
- Wazlawick, R. S. (2009). *Metodologia de pesquisa para ciência da computação*. Elsevier.
- Zhao, S. et al. (2016). *A survey of point-of-interest recommendation in location-based social networks*. ACM Computing Surveys.
- Zhao, Z. et al. (2024). *Recommender systems in the era of large language models (LLMs)*. IEEE Transactions on Knowledge and Data Engineering.