package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController(){
		super();
	}
	
	//função Ip windows 
	public void Ip (String sistemaOperacional) {
		
		if(sistemaOperacional.contains("Linux")){//verifica o sistema operacional 
			LinuxIp();
		}else{
			try {
				
				int i = 0;
				int ips = 0;
				int nomes = 0;
				String linha;
	
				Process p = Runtime.getRuntime().exec("ipconfig");//comando mostra redes ativas 
				
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				
				BufferedReader buffer = new BufferedReader(leitor);
				
				while (i < 4) {
					linha = buffer.readLine();
					i++;
				}
				linha = buffer.readLine();
				while (linha != null) {
					//verifica se contem Adaptador Ethernet
					if (linha.contains("Adaptador Ethernet")) {
						nomes++;
						
					} else if (linha.contains("IPv4")) {//verifica se contem IPv4
						ips++;
					}
					linha = buffer.readLine();
					if (linha == null) {
						linha = buffer.readLine();
					}
				}
				String Nome[] = new String[nomes];
				String ip[] = new String[ips];
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
	//Chamando processo ip novamente 
				
				i = 0;
				ips = 0;
				nomes = 0;
				
				Process p2 = Runtime.getRuntime().exec("ipconfig");
				
				InputStream fluxo2 = p2.getInputStream();
				InputStreamReader leitor2 = new InputStreamReader(fluxo2);
				
				BufferedReader buffer2 = new BufferedReader(leitor2);
				
				while (i < 4) {
					linha = buffer2.readLine();
					i++;
				}
				linha = buffer2.readLine();
				while (linha != null) {
					if (linha.contains("Adaptador Ethernet")) {
						Nome[nomes] = linha;
						nomes++;
					} else if (linha.contains("IPv4")) {
						ip[ips] = linha.substring(49);
						ips++;
					}
					linha = buffer2.readLine();
					if (linha == null) {
						linha = buffer2.readLine();
					}
				}
				
				buffer2.close();
				leitor2.close();
				fluxo2.close();
				
				for (i = 0; i < ips; i++) {
					System.out.println(Nome[i]);
					System.out.println(ip[i]);
				}
	// Fim do processo  ip 
				
			} catch (Exception e) {
				String msgError = e.getMessage();
				System.err.println(msgError);
			}
		}
	}
	
	//função ping windows 
	public void Ping(String sistemaOperacional) {
		if(sistemaOperacional.contains("Linux")){//verifica o sistema operacional 
			LinuxPing();
		}else{
			try {
				int i = 0;
				int x = 0;
	
				Process p = Runtime.getRuntime().exec("PING -n 10 www.google.com.br");//comando que verifica o Ping dos pacotes
				
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				
				BufferedReader buffer = new BufferedReader(leitor);
				
				String linha = buffer.readLine();
				
				while (linha != null) {
					
					linha = buffer.readLine();
					if (linha.contains("dia")) {
						String[] vetorMS = linha.split(" ");
						for (String MS : vetorMS) {
							i++;
						}
						x = i;
						i = 0;
						String[] vetorMS2 = linha.split(" ");
						for (String MS2 : vetorMS2) {
							i++;
							if (i == x) {
								System.out.println(MS2);
							}
						}
						linha = null;
					}
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
			} catch (Exception e) {
				
				String msgError = e.getMessage();
				System.err.println(msgError);
			}
		}
	}
	
	//funcão ip para Linux 
	public void LinuxIp(){
		
		try {
			
			int i = 0;
			int ips = 0;
			int nomes = 0;
			String linha;

			Process p = Runtime.getRuntime().exec("ifconfig");//comando mostra redes ativas 
			
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			
			BufferedReader buffer = new BufferedReader(leitor);
			
			linha = buffer.readLine();
			
			String [] vetorPalavras = linha.split(" netmask");
			
//foreach = for (TipodoDado var : vetor)
			
			for (String palavra : vetorPalavras){
				System.out.println(palavra);
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
// Fim do processo  ip 
			
		} catch (Exception e) {
			String msgError = e.getMessage();
			System.err.println(msgError);
		}
	}
	
	//função Ping para Linux 
	public void LinuxPing(){
		try {
			
			Process p = Runtime.getRuntime().exec("ping -c10 www.google.com.br");//comando que verifica o Ping dos pacotes
			
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			
			String [] vetorPalavras = linha.split(" ms");
			
			//foreach = for (TipodoDado var : vetor)
			
			for (String palavra : vetorPalavras){
				System.out.println(palavra);
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} catch (Exception e) {
			
			String msgError = e.getMessage();
			System.err.println(msgError);
		}
		
	}
}


