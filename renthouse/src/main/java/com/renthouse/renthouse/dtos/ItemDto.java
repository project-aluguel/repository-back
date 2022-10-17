package com.renthouse.renthouse.dtos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ItemDto {

    private String nome;

    private String categoria;

    private String descricao;

    private String manualUso;

    private Double valorGarantia;

    private Double valorItem;

    private Boolean alugado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getManualUso() {
        return manualUso;
    }

    public void setManualUso(String manualUso) {
        this.manualUso = manualUso;
    }

    public Double getValorGarantia() {
        return valorGarantia;
    }

    public void setValorGarantia(Double valorGarantia) {
        this.valorGarantia = valorGarantia;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }

    public static void gravaArquivoCsv(ListaObjDto<ItemDto> lista, String nomeArquivo) {
        FileWriter arq = null; // objeto que representa o arquivo de gravacao
        Formatter saida = null; // objeto usado para gravar o arquivo
        Boolean deuRuim = false;
        nomeArquivo += ".csv"; // acrescenta a extensao ao arquivo

        // bloco para abrir o arquivo
        try {
            arq = new FileWriter(nomeArquivo);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
            deuRuim = true;
        }

        // bloco para gravar o arquivo

        try {
//            saida.format("ID;NOME;VALOR;QUANTIDADE");
            for (int i = 0; i < lista.getTamanho(); i++) {
                ItemDto item = lista.getElemento(i);
                saida.format(
                        "%s;%s;%.2f;%.2f;%b\n",
                        item.getNome(),
                        item.getManualUso(),
                        item.getValorItem(),
                        item.getValorGarantia(),
                        item.getAlugado()
                );
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            System.exit(1);
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
