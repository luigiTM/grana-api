package com.lughtech.grana.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.dominio.GastoPessoa;
import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.servicos.interfaces.EmailServico;

public abstract class EmailServicoAbstrato implements EmailServico {

	@Value("${default.sender}")
	private String remetente;

	@Override
	public void enviarGranaParaPessoas(Grana grana) {
		List<SimpleMailMessage> mensagemEmail = prepararMensagemEmail(grana);
		enviarEmail(mensagemEmail);

	}

	protected List<SimpleMailMessage> prepararMensagemEmail(Grana grana) {
		List<String> emails = new ArrayList<String>();
		List<SimpleMailMessage> listaSMM = new ArrayList<>();
		for (Gasto gasto : grana.getGastos()) {
			for (GastoPessoa gastoPessoa : gasto.getGastosPessoas()) {
				if (!emails.contains(gastoPessoa.getPessoa().getEmail())) {
					emails.add(gastoPessoa.getPessoa().getEmail());
					SimpleMailMessage mensagemEmailPreparada = new SimpleMailMessage();
					mensagemEmailPreparada.setTo(gastoPessoa.getPessoa().getEmail());
					mensagemEmailPreparada.setFrom(remetente);
					mensagemEmailPreparada.setText(gastoPessoa.getIdGastoPessoa().toString());
					listaSMM.add(mensagemEmailPreparada);
				}
			}
		}
		return listaSMM;
	}

}