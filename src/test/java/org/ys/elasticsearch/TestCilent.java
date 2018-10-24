package org.ys.elasticsearch;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.ys.elasticsearch.utils.TranUtil;

public class TestCilent {
	
	@Test
	public void testPost(){
		TransportClient cilent = null;
		try {
			cilent = TranUtil.getCilet();
			IndexRequestBuilder indexRequestBuilder = cilent.prepareIndex("test", "core_user");
			Map<String, Object> source = new HashMap<String,Object>();
			source.put("name", "this is a test");
			source.put("birthday", "1987-09-08");
			indexRequestBuilder.setSource(source );
			IndexResponse res = indexRequestBuilder.get();
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != cilent){
				cilent.close();
			}
		}
	}
	
	@Test
	public void testGet(){
		TransportClient cilent = null;
		try {
			cilent = TranUtil.getCilet();
			GetRequestBuilder getRequestBuilder = cilent.prepareGet("test", "core_user", "Hi2HpmYBIS8DKjxzi8B8");
			GetResponse res = getRequestBuilder.get();
			System.out.println(res.getSourceAsString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != cilent){
				cilent.close();
			}
		}
	}	
	
	@Test
	public void testUpdate(){
		TransportClient cilent = null;
		try {
			cilent = TranUtil.getCilet();
			UpdateRequestBuilder updateRequestBuilder = cilent.prepareUpdate("test", "core_user", "Hi2HpmYBIS8DKjxzi8B8");
			Map<String, Object> source = new HashMap<String,Object>();
			source.put("name", "修改过了");
			updateRequestBuilder.setDoc(source);
			UpdateResponse res = updateRequestBuilder.get();
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != cilent){
				cilent.close();
			}
		}
	}	
	
	@Test
	public void testDelete(){
		TransportClient cilent = null;
		try {
			cilent = TranUtil.getCilet();
			DeleteRequestBuilder deleteRequestBuilder = cilent.prepareDelete("test", "core_user","Hi2HpmYBIS8DKjxzi8B8");
			DeleteResponse res = deleteRequestBuilder.get();
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != cilent){
				cilent.close();
			}
		}
	}		
	
	@Test
	public void testQuery(){
		TransportClient cilent = null;
		try {
			cilent = TranUtil.getCilet();
			SearchRequestBuilder searchRequestBuilder = cilent.prepareSearch("test");
			searchRequestBuilder.setTypes("core_user");
			BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
			queryBuilder.must(QueryBuilders.matchAllQuery());
			queryBuilder.filter(QueryBuilders.rangeQuery("birthday").gte("1980-01-01").lte("2018-10-30"));
			searchRequestBuilder.setQuery(queryBuilder);
			searchRequestBuilder.addSort("birthday",SortOrder.ASC);
			searchRequestBuilder.setFrom(0).setSize(10);
			SearchResponse searchResponse = searchRequestBuilder.get();
			SearchHits searchHits = searchResponse.getHits();
			System.out.println("命中数："+searchHits.totalHits);
			SearchHit[] hists = searchHits.getHits();
			for (SearchHit hist : hists) {
				System.out.println(hist.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != cilent){
				cilent.close();
			}
		}
	}	
}
