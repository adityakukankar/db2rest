SELECT
    [# th:each="column : ${columns}"]
    [(${column.name})][/]
FROM
    [(${rootTable.name})]
[# th:if="${rootWhere}"]WHERE
    [(${rootWhere})]
[/]
[# th:if="${limit}"]LIMIT [(${limit})][/] [# th:if="${offset}"]LIMIT [(${offset})][/]