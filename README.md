# PsicoDaily

Repositório para projeto da disciplina de Projeto de Software.

### Mudanças na aula do dia 8/03
1. Criação da classe abstrata Usuario, que é herdada pelas classes Psicologo e Paciente, usando conceitos de herança.
2. Exibição de perfil é uma função comum a todos os Usuarios do sistema, então é um método abstrato da classe Usuario, implementamos isso.
3. Marcar consultas, além do Psicologo, apartir da classe do Paciente.

### Bad Smells identificados

1. Introduce Parameter Object: 
- Patient ✅
- addRecord ✅
- editRecord ✅

Todos eles deveriam receber um Record.

2. Duplicated coding

3. Introduce parameter object ✅

4. Command ✅

5. State:
- Vinculo ✅
- Record ✅
- Consulta (marcada ou não) ✅

# Entrega: 1 padrão simples e 2 complexos: utilizando herança e polimorfismo
