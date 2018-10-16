package com.jt.search.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.search.pojo.Item;

public class SearchServiceImpl implements SearchService {

	@Autowired
	HttpSolrServer httpSolrServer;
	@Override
	public List<Item> findItemByKey(String key) {
		List<Item> itemList = null;
		try {
			SolrQuery solrQuery = new SolrQuery(key);
			solrQuery.setStart(0);
			solrQuery.setRows(20);
			QueryResponse response = httpSolrServer.query(solrQuery);
			itemList = response.getBeans(Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}

}
