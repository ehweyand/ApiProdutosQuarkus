package org.produtos.repository.helper;

public class ProdutoRepositoryHelper {

    public static String queryForHistoricoProdutosClientes() {
        return "WITH total_gasto AS (" +
                "    SELECT date_trunc('month', pd.data_pedido) AS mes, c.id AS cliente_id, c.nome AS cliente_nome, \n" +
                "           SUM(p.valor_unitario) AS total \n" +
                "    FROM clientes c\n" +
                "    JOIN produtos_pedidos pp ON c.id = pp.clientes_id\n" +
                "    JOIN pedidos pd ON pp.pedidos_id = pd.id\n" +
                "    JOIN produtos p ON pp.produtos_id = p.id\n" +
                "    GROUP BY mes, cliente_id, cliente_nome\n" +
                "), \n" +
                "\n" +
                "produto_mais_comprado AS (\n" +
                "    SELECT date_trunc('month', pd.data_pedido) AS mes, c.id AS cliente_id, p.descricao AS produto_descricao,\n" +
                "           COUNT(*) AS qtd\n" +
                "    FROM clientes c\n" +
                "    JOIN produtos_pedidos pp ON c.id = pp.clientes_id\n" +
                "    JOIN pedidos pd ON pp.pedidos_id = pd.id\n" +
                "    JOIN produtos p ON pp.produtos_id = p.id\n" +
                "    GROUP BY mes, cliente_id, produto_descricao\n" +
                ")\n" +
                "\n" +
                "SELECT tg.mes, tg.cliente_nome, tg.total, pmc.produto_descricao\n" +
                "FROM total_gasto tg\n" +
                "JOIN (\n" +
                "    SELECT mes, cliente_id, MAX(total) as max_total\n" +
                "    FROM total_gasto\n" +
                "    GROUP BY mes, cliente_id\n" +
                ") top_gasto ON tg.mes = top_gasto.mes AND tg.cliente_id = top_gasto.cliente_id AND tg.total = top_gasto.max_total\n" +
                "JOIN produto_mais_comprado pmc ON tg.mes = pmc.mes AND tg.cliente_id = pmc.cliente_id\n" +
                "WHERE (\n" +
                "    SELECT COUNT(*) \n" +
                "    FROM total_gasto \n" +
                "    WHERE mes = tg.mes AND total > tg.total\n" +
                ") < 10\n" +
                "ORDER BY tg.mes, tg.total DESC;";
    }
}
