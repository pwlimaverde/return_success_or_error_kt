[1.5.0] - 15/09/2024.
1 - Atualização para catalog version.
2 - Removido função ```invokeThread```.
3 - Função ```invoke``` da class ```DataSource``` convertida para suspend fun, assim como seus
derivados.
4 - Refatoração dos Testes.

[1.4.0] - 04/09/2024.
1 - Refatoração da Classes ```UseCaseBase``` e ```UseCaseCallData``` .
2 - Implementação nas Classes UseCase da função ```invokeCoroutine``` que executa o invoke em uma
nova Thread compativel com ```Coroutine```.
3 - Refatoração dos testes, e atualização dos testes das Classes UseCase para as novas
funcionalidades ```invokeCoroutine```.

[1.3.0] - 03/09/2024.
1 - Implementação da Classes ```UseCaseBase```.
2 - Implementação nas Classes UseCase da função ```invokeThread``` que executa o invoke em uma nova
Thread.
3 - Refatoração dos testes, e atualização dos testes das Classes UseCase para as novas
funcionalidades ```invokeThread```.

[1.2.0] - 01/09/2024.
1 - Correção ortografica README.
2 - Implementação das Classes ```ReturnSuccessOrError```, ```ErrorReturn```, ```SuccessReturn```,
```AppError```, ```ErrorGeneric```, ```NoParams```, ```DataSource```, ```UseCaseCallData``` e
```ParametersReturnResult```, assim como suas respectivas Classes test.

[1.1.0] - 29/08/2024.
Inicio do desenvolvimento.
