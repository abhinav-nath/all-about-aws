# Search

OS Search [documentation](https://opensearch.org/docs/latest/opensearch/ux/ "OpenSearch Search Experience")

`from` and `size` are for [pagination](https://opensearch.org/docs/latest/opensearch/ux/#paginate-results "Paginate Results")

```json
GET product-catalog-index/_search
{
  "from": 0,
  "size": 10,
  "query": {
    "match": {
      "category": "LAPTOPS"
    }
  }
}
```

Facets:

```json
GET product-catalog-index/_search
{
  "from": 0,
  "size": 10,
  "query": {
    "match": {
      "name": "Macbook"
    }
  },
  "aggs": {
    "response_codes": {
      "terms": {
        "field": "category.keyword",
        "size": 10
      }
    }
  }
}
```