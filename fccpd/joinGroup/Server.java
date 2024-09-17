import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    
    public static void main(String[] args) throws IOException {
        String mensagem = " ";
        byte[] envio = new byte[1024];
        Scanner sc = new Scanner(System.in);
        LocalDateTime dataHora = LocalDateTime.now();
        DateTimeFormatter dataHoraF = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm");
        String dataFormatada = dataHora.format(dataHoraF);

        MulticastSocket socket1 = new MulticastSocket(6000);
        MulticastSocket socket2 = new MulticastSocket(6001);
        InetAddress ipGrupo = InetAddress.getByName("230.0.0.0");

        while (!mensagem.contains("Servidor Encerrado!")) {

           System.out.println("[Servidor] Digite o topico: 1- Avisos Gerais; 2 - Atividades Extracurriculares;");
           int topico = sc.nextInt();
           sc.nextLine();
           System.out.println("[Servidor] Digite a mensagem:");
           mensagem = sc.nextLine();
           if(mensagem.equals("encerrar"))
               mensagem = "Servidor Encerrado!";
           String msg = "[" + dataFormatada + "]" + topico + ": " + mensagem;
           envio = msg.getBytes();

            if (topico == 1) {
                DatagramPacket pacote = new DatagramPacket(envio, envio.length, ipGrupo, socket1.getLocalPort());
                socket1.send(pacote);

                socket1.close();
            }

            if (topico == 2) {
                DatagramPacket pacote = new DatagramPacket(envio, envio.length, ipGrupo, socket2.getLocalPort());
                socket2.send(pacote);

                socket2.close();
            }

        }
    }

} 
