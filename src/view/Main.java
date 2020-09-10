package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		
		RedesController redes = new RedesController();
		String os = System.getProperty("os.name");
		
		int Opc = 0;
        while (Opc != 10){
            Opc = Integer.parseInt(JOptionPane.showInputDialog("\n      Atividade 1 \n 1 – Verificar ip \n 2 –Media de ping \n 10 - Finalizar"));
            switch (Opc){
                case 1:
                	//faz a chamada da função ip
                	redes.Ip(os);
                	System.out.println(" ");
                    break;
                case 2:
                	//faz a chamada da função ping 
                	redes.Ping(os);
                	System.out.println("\n ");
                    break;
                case 10:
                	//O programa é finalizado 
                    JOptionPane.showMessageDialog(null, "Fim!");
                    break;
                default:
                    System.err.println("Erro, digite novamente");
            }
        }
	}
}

