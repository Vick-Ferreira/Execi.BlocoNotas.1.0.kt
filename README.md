<<<<<<< HEAD
# Aplicativo de Gerenciamento de Tarefas - Desenvolvido em Kotlin

Este é um exemplo de um aplicativo de gerenciamento de tarefas desenvolvido em Kotlin para Android. O aplicativo demonstra boas práticas de programação e oferece uma experiência de usuário funcional e agradável.

## Visão Geral

O aplicativo foi construído com as seguintes características:

### Navegação Intuitiva com Fragments

O aplicativo possui um design de interface de usuário moderno e responsivo. Utilizamos um `FragmentContainerView` em conjunto com o componente de gráfico de navegação para criar uma navegação fluida entre as diferentes telas do aplicativo.

### Persistência de Dados com SharedPreferences

Os dados do aplicativo são armazenados de forma segura usando o `SharedPreferences`. Utilizamos essa tecnologia para persistir os dados locais, incluindo o cadastro de e-mails que serão usados para login e também para salvar e atualizar a lista de tarefas. Isso garante que os usuários mantenham seus dados mesmo após o fechamento do aplicativo.

### Feedback Instantâneo com Toasts

Implementei o uso de Toasts para fornecer feedback imediato aos usuários sobre suas ações. Isso garante que os usuários estejam sempre cientes do resultado de suas ações.

### Segurança e Tratamento de Erros

A segurança é uma prioridade e, portanto, o aplicativo exige que os usuários cadastrem um e-mail válido para fazer login. Além disso,foi implementado um tratamento de erro eficiente usando blocos try-catch, garantindo que o aplicativo funcione sem interrupções.

### Finalização de Dados no Ciclo de Vida

Ao voltar para a tela de login, os dados são finalizados de acordo com o ciclo de vida do aplicativo, garantindo uma experiência de uso eficiente e segura.

## Funcionalidades Principais

O aplicativo inclui as seguintes funcionalidades principais:

- Cadastro de Usuário: Os usuários devem cadastrar um e-mail para uso no aplicativo.
- Login Seguro: O login só é permitido com um e-mail cadastrado e usando uma senha padrão (123456).
- Lista de Tarefas: Após o login, os usuários têm acesso à sua lista de tarefas, onde podem adicionar, editar e excluir tarefas conforme necessário.
- Menu Pop: O menu expõe opções ao usuário.

Todos os textos do aplicativo, bem como as cores do tema, estão armazenados de forma organizada e gerenciável no arquivo `strings.xml`.

## Futuras Implementações


Projeto foi desenvolvido como trabalho bimestral da faculdade.Fortalecendo técnicas aprendidas.


