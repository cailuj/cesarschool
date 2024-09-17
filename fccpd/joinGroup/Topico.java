import java.io.IOException;
import java.net.*;

public class Topico implements Runnable {
    
    String ip;
    String topico;
    int porta;

    public Topico(String ip, int topico, int porta){
        this.ip = ip;
        if (topico ==1){
            this.topico = "Avisos Gerais";
        } else if (topico ==2){
            this.topico = "Atividades Extracurriculares";
        }
        this.porta = porta;
    }


    @Override
    public void run() {
        //pedir end de ip, nome do topico, num porta
        //criar thread topico no aluno
        //fazer metodo que recebe mensagem do servidor e encerra servidor quando msg = serv encerrado

        String mensagem = " ";
        String rcvmensagem = "";

        try {
            InetAddress ipGrupo = InetAddress.getByName(this.ip);
            InetSocketAddress grupo = new InetSocketAddress(ipGrupo, this.porta);
            NetworkInterface ni = NetworkInterface.getByInetAddress(ipGrupo);
            MulticastSocket socket = new MulticastSocket(this.porta);
            socket.joinGroup(grupo, ni);

            while(!mensagem.contains("Servidor Encerrado!")){
                System.out.println("[" + this.topico + "] Esperando mensagem...");
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                rcvmensagem = new String(packet.getData());
                mensagem = rcvmensagem.replaceAll("\0", "");
                System.out.println("[" + this.topico + "] Mensagem recebida: "+ mensagem);
            }
            socket.leaveGroup(grupo, ni);
            socket.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
