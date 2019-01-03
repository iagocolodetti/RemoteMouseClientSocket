[Descrição](https://github.com/iagocolodetti/RemoteMouseClientSocket/blob/master/README.md#descri%C3%A7%C3%A3o "Descrição")
<br>
[Downloads](https://github.com/iagocolodetti/RemoteMouseClientSocket/blob/master/README.md#downloads "Downloads")
<br>
[Sobre](https://github.com/iagocolodetti/RemoteMouseClientSocket/blob/master/README.md#sobre "Sobre")
<br>
[Funcionamento](https://github.com/iagocolodetti/RemoteMouseClientSocket/blob/master/README.md#funcionamento "Funcionamento")
<br>
[Requisitos mínimos](https://github.com/iagocolodetti/RemoteMouseClientSocket/blob/master/README.md#requisitos "Requisitos mínimos")
<br>
[Projeto](https://github.com/iagocolodetti/RemoteMouseClientSocket/blob/master/README.md#projeto "Projeto")
<br>
<br>
# Descrição
Projeto desenvolvido com o único objetivo de controlar remotamente as ações do mouse de um PC utilizando um Smartphone Android.
<br>
<br>
# Downloads
https://github.com/iagocolodetti/RemoteMouseClientSocket/releases
* [RemoteMouseClient.apk](https://github.com/iagocolodetti/RemoteMouseClientSocket/releases/download/v1.0/RemoteMouseClient.apk "RemoteMouseClient.apk")
* [Código-fonte](https://github.com/iagocolodetti/RemoteMouseClientSocket/archive/v1.0.zip "v1.0.zip")
* [Servidor](https://github.com/iagocolodetti/RemoteMouseServerSocket/blob/master/README.md#downloads "RemoteMouseServerSocket#Downloads")
# Sobre
Aplicação cliente (Android) para controlar remotamente as ações do mouse do servidor (PC).
<br>
<br>
<img src="https://github.com/iagocolodetti/imagens/blob/master/rmcsocket1.jpg" alt="RemoteMouseClient OFF" height="700" width="394">
<img src="https://github.com/iagocolodetti/imagens/blob/master/rmcsocket2.jpg" alt="RemoteMouseClient ON" height="700" width="394">
<br>
<br>
# Funcionamento
Ao ligar o servidor e obter um IP, utilize esse IP e porta para conectar-se ao servidor.
- Pixels: Representa o número de pixels que o ponteiro do mouse vai "pular" a cada ciclo. O que significa? Segurando o botão para que o mouse se movimente e o valor estiver definido em 10 pixels, ele vai se movimentar 10 pixels direto por vez.
- Delay: Representa o tempo em milissegundos (ms) de duração de cada ciclo. O que isso quer dizer? Ao segurar para o ponteiro do mouse andar e o valor de delay estiver definido em 500 milissegundos, o ponteiro vai se mover uma vez a cada 0.5 segundo.

# Requisitos
Versão: Android 4.1 (Jelly Bean)
<br>
Resolução: 1280x720 (HD)
<br>
O cliente (Android) deve estar conectado a mesma rede que o servidor (PC).
<br>
<br>
# Projeto
Desenvolvido no Android Studio 3 fazendo uso de Socket e Thread.
