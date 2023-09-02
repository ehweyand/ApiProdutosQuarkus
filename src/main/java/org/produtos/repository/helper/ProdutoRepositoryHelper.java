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

    public static String queryForFindResumoProdutosClientes() {
        return "WITH ClientesMaisAtivos AS (\n" +
                "    SELECT\n" +
                "        cp.clientes_id,\n" +
                "        COUNT(DISTINCT cp.pedidos_id) AS total_pedidos\n" +
                "    FROM\n" +
                "        produtos_pedidos cp\n" +
                "    JOIN pedidos pe ON cp.pedidos_id = pe.id\n" +
                "    WHERE\n" +
                "        pe.data_pedido > current_date - INTERVAL '1 year'\n" +
                "    GROUP BY\n" +
                "        cp.clientes_id\n" +
                "    ORDER BY\n" +
                "        total_pedidos DESC\n" +
                "    LIMIT 100\n" +
                "),\n" +
                "\n" +
                "ProdutosMaisCaros AS (\n" +
                "    SELECT\n" +
                "        p.id AS produto_id,\n" +
                "        p.valor_unitario,\n" +
                "        ROW_NUMBER() OVER (ORDER BY p.valor_unitario DESC) AS rank_valor\n" +
                "    FROM\n" +
                "        produtos p\n" +
                "    WHERE\n" +
                "        p.valor_unitario > (SELECT AVG(valor_unitario) FROM produtos)\n" +
                "),\n" +
                "\n" +
                "PedidosRecentes AS (\n" +
                "    SELECT\n" +
                "        cp.clientes_id,\n" +
                "        cp.pedidos_id,\n" +
                "        SUM(p.valor_unitario) AS total_pedido\n" +
                "    FROM\n" +
                "        produtos_pedidos cp\n" +
                "    JOIN produtos p ON cp.produtos_id = p.id\n" +
                "    WHERE\n" +
                "        cp.produtos_id IN (SELECT produto_id FROM ProdutosMaisCaros WHERE rank_valor <= 50)\n" +
                "    GROUP BY\n" +
                "        cp.clientes_id, cp.pedidos_id\n" +
                ")\n" +
                "\n" +
                "SELECT\n" +
                "    cma.clientes_id,\n" +
                "    c.nome,\n" +
                "    c.endereco,\n" +
                "    pr.pedidos_id,\n" +
                "    pr.total_pedido,\n" +
                "    pcm.produto_id,\n" +
                "    p.descricao,\n" +
                "    pcm.valor_unitario AS valor_produto,\n" +
                "    pcm.rank_valor\n" +
                "FROM\n" +
                "    ClientesMaisAtivos cma\n" +
                "JOIN clientes c ON cma.clientes_id = c.id\n" +
                "JOIN PedidosRecentes pr ON cma.clientes_id = pr.clientes_id\n" +
                "JOIN produtos_pedidos cp ON pr.pedidos_id = cp.pedidos_id AND pr.clientes_id = cp.clientes_id\n" +
                "JOIN ProdutosMaisCaros pcm ON cp.produtos_id = pcm.produto_id\n" +
                "JOIN produtos p ON pcm.produto_id = p.id\n" +
                "ORDER BY\n" +
                "    cma.total_pedidos DESC, pr.total_pedido DESC, pcm.rank_valor;";
    }
}
