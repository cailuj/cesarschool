import java.io.IOException;
import java.util.Scanner;

public class Aluno {
    
    public static int inscrever() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o topico: 1- Avisos Gerais; 2 - Atividades Extracurriculares; 3 - Ambos tópicos;");
        int topico = sc.nextInt();
        System.out.println("Digite o usuario: ");
        @SuppressWarnings("unused")
        String usuario = sc.nextLine();
        return topico;
    }


    public static void main() throws IOException {

        String ip = "230.0.0.0";
        int porta1 = 6000;
        int porta2 = 6001;
        int topico = inscrever();

        Topico thread1 = new Topico(ip, 1, porta1);
        Topico thread2 = new Topico(ip, 2, porta2);

        if(topico == 1){

            thread1.run();
        }
        if(topico == 2){

            thread2.run();
        }
        if(topico == 3){

            thread1.run();
            thread2.run();
        }

    }

}
