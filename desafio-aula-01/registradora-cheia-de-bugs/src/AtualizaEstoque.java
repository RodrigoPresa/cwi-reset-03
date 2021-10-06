public class AtualizaEstoque {

    static void atualizaItem(String item, int qtd) {
        if ("paes".equals(item)) {
            ItensPorQuantidade.pao -= qtd;
        }
        if ("torta".equals(item)) {
            ItensPorQuantidade.torta -= qtd;
        }
        if ("sanduiche".equals(item)) {
            ItensPorQuantidade.sanduiche -= qtd;
        }
        if ("leite".equals(item)) {
            ItensPorQuantidade.leite -= qtd;
        }
        if ("cafe".equals(item)) {
            ItensPorQuantidade.cafe -= qtd;
        }
    }
}
