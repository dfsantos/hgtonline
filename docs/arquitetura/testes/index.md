# Estrutura dos testes unitários

Todo teste unitário é dividido em etapas para facilitar a leitura e a manutenção do código. Temos
duas variantes de estrutura de teste unitário:

- BDD
- Expectation

## BDD

Deve seguir o modelo básico de BDD, que é:

- Given / When / Then;
- Esses blocos podem ser subdividodos em blocos menores com 'And';

### Given

O _given_ deve ter apenas código que configura o cenário de teste. Neste bloco pode ter (sempre
nesta
ordem):

1. Instanciação dos componentes sob teste (concretos e mocks);
2. Setup de stubs;
3. Setup de comportamentos de mocks;

### When

O _when_ deve conter apenas o acionamento do cenário de teste. Em geral, teremos apenas uma linha de
código invocando o método (unidade) sob teste. Também, no _when_ capturamos o retorno do método, se
necessário.

### Then

O _then_ deve conter apenas as asserções do teste. Aqui devemos validar o resultado esperado do
teste

Exemplos:

**Estruturação básica**

```java

@Test
@DisplayName("Deve registrar glicemia com sucesso")
void deve_registrar_glicemia_com_sucesso() {
    // given
    var repository = mock(GlicemiaRepository.class);
    var useCase = new RegistrarGlicemiaUseCaseImpl(repository);

    // and
    var dataHora = LocalDateTime.now().minusMinutes(1);
    var input = new RegistrarGlicemiaUseCase.Input(120, dataHora, ANTES_CAFE_DA_MANHA);
    var glicemia = new Glicemia(randomUUID(), input.valor(), input.dataHora(), input.momentoDia());

    // and
    when(repository.salvar(any(Glicemia.class))).thenReturn(glicemia);

    // when
    var output = useCase.execute(input);

    // then
    assertNotNull(output.id());
    assertEquals(input.valor(), output.valor());
    assertEquals(input.dataHora(), output.dataHora());
    assertEquals(input.momentoDia(), output.momentoDia());
    verify(repository, times(1)).salvar(any(Glicemia.class));
}
```

## Expectation

A variante Expectation foca em descrever o comportamento esperado do sistema sob teste de forma direta, utilizando nomes de métodos e descrições que evidenciem o resultado esperado. A estrutura geralmente segue:

- Setup (preparação do cenário);
- Execução (ação principal);
- Expectation (validação do comportamento esperado);

### Setup

No bloco de setup, configuramos o cenário de teste, instanciando mocks, stubs e objetos necessários.

### Expectation

Neste bloco, executamos a ação principal que será validada. Aqui, realizamos asserções para garantir que o comportamento esperado ocorreu, podendo também verificar interações com mocks.

Exemplo:

```java
@Test
  @DisplayName("Não deve permitir data/hora nula no registro de glicemia")
  void nao_deve_permitir_data_hora_nula() {
    // setup
    var id = UUID.randomUUID();

    // expect
    assertThrows(
        NullPointerException.class,
        () -> new Glicemia(id, 100, null, MomentoDia.ANTES_CAFE_DA_MANHA));
  }
```
